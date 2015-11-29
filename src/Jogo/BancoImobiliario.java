package Jogo;
import Peças.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

enum EstadosJogo {
		PreDado,
		PosDado
}
class BancoImobiliario implements ObservadoJogo {

	int jogadorRodada= 0;
	int qtdJogadoresTotal=0;
	
	Dados dado = new Dados();
	Jogador[] jogadores;
	Casa[] casas;
	
	private Queue<SorteOuReves> cartas = null;
	SorteOuReves cartaAtual = null;
	private int numeroRepeticoes = 0;
	
	ControladorEventos controlador = null;
	private List<ObservadorJogo> lst = new ArrayList<ObservadorJogo>();
	private EstadosJogo estadoAtual;
	
	
	BancoImobiliario(ControladorEventos controlador) {
		this.controlador = controlador;
	}

	void iniciarJogo(int qtdJogadores){
		this.qtdJogadoresTotal = qtdJogadores;	
		this.casas = Casa.criarCasasBancoImobiliario();
		carregaPinos(qtdJogadores);
		cartas = SorteOuReves.criarCartasBancoImobiliario();
		estadoAtual = EstadosJogo.PreDado;
		this.notificarObservadores();
	}
	
	private void carregaPinos(int qtdJogadores) {
		this.qtdJogadoresTotal = qtdJogadores;
		this.jogadorRodada = 0;
		this.jogadores = new Jogador[qtdJogadores];	
		for(int i=0; i < qtdJogadores; i++) {
			jogadores[i] = new Jogador(i);
		}
	}
	
	boolean andarJogadorAtual(Dados dado) {
		this.dado = dado;
		if (jogadores != null && this.estadoAtual == EstadosJogo.PreDado) {
						
			int qtdCasas = dado.getSoma();
			Jogador jogadorVez = jogadores[this.jogadorRodada];
			if (jogadorVez.isPreso) {
				if (dado.getDado1() == dado.getDado2()) {
					jogadorVez.isPreso = false;
					jogadorVez.rodadasPreso = 0;
				}
				else {
					jogadorVez.rodadasPreso--;
					if (jogadorVez.rodadasPreso == 0)
						jogadorVez.isPreso = false;
					
					this.notificarObservadores();
					this.estadoAtual = EstadosJogo.PosDado;
					return false;
				}
			
			}
			else if(dado.getDado1() == dado.getDado2() && this.numeroRepeticoes == 2) {
				prenderJogador(jogadorVez);	
				this.estadoAtual = EstadosJogo.PosDado;
				return false;
			} 			
			
			int novaCasa = jogadorVez.casaAtual+qtdCasas;
			
			if (novaCasa>=36) {
				jogadorVez.credita(200);

				String titulo = "Pagamento de Pró-Labore";
				String msg = "Você recebeu R$200!";
				this.notificarMensagens(msg, titulo);
				novaCasa = (novaCasa)%36;
			}
			
			jogadorVez.casaAtual = novaCasa;
			this.notificarObservadores();
			this.acaoJogador();
			
			if (dado.getDado1() == dado.getDado2() && !jogadorVez.isPreso) {
				this.numeroRepeticoes++;
				this.estadoAtual = EstadosJogo.PreDado;
				return true;
			}
			else {
				this.estadoAtual = EstadosJogo.PosDado;
				return false;
			}
		}
		return false;
	}
	
	 boolean passarRodada() {	
		 if (this.estadoAtual == EstadosJogo.PreDado)
			 return false;
		 
		 if(jogadorRodada==(qtdJogadoresTotal-1))
			 jogadorRodada=0;
		 else
			 jogadorRodada++;
		 
		 while(jogadores[jogadorRodada].isFalido){
			 if(jogadorRodada==(qtdJogadoresTotal-1))
				 jogadorRodada=0;
			 else
				 jogadorRodada++;	 
		 }
		 	
		 Jogador nvJogador = jogadores[jogadorRodada];
		
		 if (nvJogador.isPreso){
			 if (nvJogador.passesPrisao > 0){
				 this.notificarObservadores();
				 if(controlador.oferecerUsarPassePrisao()){
					 nvJogador.isPreso = false;
					 nvJogador.rodadasPreso = 0;
					 nvJogador.passesPrisao--;
				 }		 
			 } 
		 }
					 
		 
		 this.estadoAtual = EstadosJogo.PreDado;
		 this.numeroRepeticoes = 0;
		 this.notificarObservadores();
		 return true;
	}
	

	boolean acaoCasa(int numeroCasa) {
		Jogador jogadorVez = jogadores[this.jogadorRodada];
		
		
		if (numeroCasa < 0 || numeroCasa > 35)
			return false;
		Casa casa = casas[numeroCasa];
		if (jogadorVez.isPreso || this.estadoAtual == EstadosJogo.PreDado)
			return false;
			
		
		
		if (casa instanceof Terreno) {
			Jogador dono = ((Terreno)casa).getDono();
			if (casa instanceof Companhia) {
				Companhia comp = (Companhia) casa;
				if (dono == jogadorVez) {
					if(comp.isHipotecado) {
						if(controlador.oferecerRecuperarHipoteca(comp.nome, comp.getPagamentoHipoteca())) 
							comp.pagarHipoteca();
					}	
					else {
						if(controlador.oferecerHipoteca(comp.nome, comp.getValorHipoteca())) 
							comp.hipotecar();
					}
					
				}
				else if (dono != null){
					System.out.println("//fazer oferta companhia");
				}
			}
			else if (casa instanceof Propriedade) {
				Propriedade prop = (Propriedade) casa;
				if (dono == jogadorVez) {
					if (prop.isHipotecado) {
						if(controlador.oferecerRecuperarHipoteca(prop.nome, prop.getPagamentoHipoteca()))
							prop.pagarHipoteca();
					}
					else {
						if(prop.getQtdSedes() == 0){
							int resp = controlador.oferecerConstruirOuHipotecar();
							if(resp == 0)  {
								prop.construirSede();
							}
							else if (resp == 1) {
								prop.hipotecar();
							}

						}
						else {
							int resp = controlador.oferecerConstruirOuVender();
							if(resp == 0)  {
								if (prop.getQtdSedes() < 4)
									prop.construirSede();
								else 
									prop.construirComite();
							}
							else if (resp == 1) {
								prop.venderConstrucao();
							}
						}
					}
				}
				else if (dono != null) {
					System.out.println("//fazer uma oferta");
				}
			}
		}
	
		this.notificarObservadores();
		return false;
	}
	 
	 
	 
	private void acaoJogador() {
		
		Jogador jogadorVez = jogadores[this.jogadorRodada];
		Casa casaAtual = this.casas[jogadorVez.casaAtual];	
		
		if(casaAtual instanceof Terreno) {
			Terreno terreno = (Terreno) casaAtual;
			if(terreno.getDono() == null) {
				this.notificarObservadores(terreno.numeroCasa,false);
				if (controlador.oferecerCompra(terreno.getValorCompra())){
					jogadorVez.comprarTerreno(terreno);
					this.notificarObservadores();
				}
				else {
					this.notificarObservadores();
				}
			}
			else {
				if (!terreno.isHipotecado && jogadorVez != terreno.getDono()) {
					
					if(terreno.getTaxa() > jogadorVez.getSaldo()){
						if (this.venderBensJogador(jogadorVez, terreno.getTaxa()) == false){
							this.falirJogador(jogadorVez,terreno.getDono(),terreno.getTaxa());
							return;
						}
					}
					terreno.pagarTaxa(jogadorVez, this.dado);
					String donoString = Jogador.getJogadorCor(terreno.getDono() ) ;
					String msg = "Você pagou R$"+terreno.getTaxa()+" para o Jogador "+donoString;
					this.notificarObservadores();
					this.notificarMensagens(msg, "Pagamento de Aluguel");
				}
				
			}
		}
		else if (casaAtual instanceof Noticia) {
			Noticia noticia = (Noticia) casaAtual;		
			noticia.fazerAcao(jogadorVez);
			this.notificarObservadores();
			this.notificarMensagens(noticia.getMensagem(), "Atenção");
		
		}
		else if (casaAtual.vaiPrisao) {
			prenderJogador(jogadorVez);
		}
		else if (casaAtual.sorteReves) {
			
			cartaAtual = this.cartas.poll();
			
			if(cartaAtual.irPrisao)
				prenderJogador(jogadorVez);
			else if(cartaAtual.passePrisao)
				jogadorVez.passesPrisao++;
			else if(cartaAtual.valor > 0)
				jogadorVez.credita(cartaAtual.valor);
			else if(cartaAtual.valor < 0)	
				jogadorVez.debita((-1)*cartaAtual.valor);

			this.notificarObservadores(cartaAtual.numCarta,true);
			cartas.add(cartaAtual);
			cartaAtual = null;
			}
		else {
			this.notificarObservadores();
		}
		
	}
	
	private void prenderJogador(Jogador jogador) {
		jogador.casaAtual = 9;
		jogador.prender();
		this.notificarObservadores();
	}

	private boolean venderBensJogador(Jogador devedor, double divida) {
		
		
		while (divida > devedor.getSaldo()) {
			
			int resp = controlador.desfazerBens(devedor,divida);
			if (resp >= 0 && resp < 36) { 
				Terreno trn = (Terreno) casas[resp];
				
				if (trn instanceof Propriedade) {
					Propriedade p = (Propriedade) trn;
					System.out.println(p.getQtdSedes());
					if (p.getQtdSedes() > 0) {
						System.out.println(p.getQtdSedes());
						p.venderConstrucao();
						}
					else
						p.hipotecar();
				}
				else
					trn.hipotecar();
			}
			
			if (resp == -10) {
				//FALIU
				return false;
			}
			
			this.notificarObservadores();
		}
		
		return true;
	}
	
	private void falirJogador(Jogador devedor, Jogador credor, double divida) {
		
		double dinheiro = devedor.getSaldo();
	
		ListIterator<Terreno> listIterator = devedor.lstTerrenos.listIterator();
		while (listIterator.hasNext()) { 
			Terreno terreno = listIterator.next();
			dinheiro += terreno.devolverBanco();
		}
		double pagamento;
		
		if (dinheiro >= divida)
			pagamento = divida;
		else
			pagamento = dinheiro;
		
		devedor.credita(pagamento);
		
		devedor.lstTerrenos = null;
		devedor.isFalido = true;
		String corCredor = Jogador.getJogadorCor(credor);
		
		int qtdNaoFalidos = 0;
		for(Jogador jogador : jogadores){
			if(!jogador.isFalido){
				qtdNaoFalidos++;
			}		
		}
		
		if(qtdNaoFalidos == 1) {
			this.notificarMensagens("Você faliu. O jogador "+corCredor+" é o grande milionário.", "Game Over.");
		}
		else {
			this.notificarMensagens("Você faliu. O jogador "+corCredor+" recebeu R$"+pagamento+".", "Falência.");
		}
	}
	
			
	
/*	
 *	Metodos interface ObservadoJogo
 *
 */
	@Override
	public void register(ObservadorJogo obj) {
		if (obj != null) 
			lst.add(obj);
	}

	@Override
	public void unregister(ObservadorJogo obj) {	
		if (obj != null)	
			lst.remove(obj);
	}

	@Override
	public void notificarObservadores() {
		int i;		
		for (i = 0; i < lst.size() ; i ++) {
			lst.get(i).update();
		}
	}

	@Override
	public void notificarObservadores(int numCarta, boolean isSorteReves) {
		int i;
		for (i = 0; i < lst.size() ; i ++) {
			lst.get(i).update(numCarta,isSorteReves);
		}
		
	}

	@Override
	public void notificarMensagens(String msg, String titulo) {
		int i;
		for (i = 0; i < lst.size() ; i ++) {
			lst.get(i).mostraMsg(msg, titulo);
		}
		
	}
	
}//End of Class
