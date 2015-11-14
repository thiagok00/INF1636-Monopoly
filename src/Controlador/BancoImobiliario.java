package Controlador;

import java.util.ArrayList;
import java.util.List;
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
	
	private ControladorEventos controlador = null;
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
			Ponto pos = this.casas[0].getPos(i);
			jogadores[i] = new Jogador(pos.x,pos.y,i);
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
				if (jogadorVez.rodadasPreso == 0) {
					jogadorVez.isPreso = false;
				}
				else {
					jogadorVez.rodadasPreso--;
					this.estadoAtual = EstadosJogo.PosDado;
					return false;
				}
			
			}
			else if(dado.getDado1() == dado.getDado2() && this.numeroRepeticoes == 3) {
				prenderJogador(jogadorVez);	
				this.estadoAtual = EstadosJogo.PosDado;
				return false;
			} 			
			
			int novaCasa = jogadorVez.casaAtual+qtdCasas;
			
			if (novaCasa>=36) {
				jogadorVez.credita(200);

				String titulo = "Pagamento de Pró-Labore";
				String msg = "Você recebeu R$200!";//Popup avisando que recebeu 200
				this.notificarMensagens(msg, titulo);
				novaCasa = (novaCasa)%36;
			}
			
			jogadorVez.casaAtual = novaCasa;
			Ponto pos = this.casas[novaCasa].getPos(jogadorRodada);
			jogadorVez.setPosition(pos);
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
		 
		 this.estadoAtual = EstadosJogo.PreDado;
		 this.notificarObservadores();
		 return true;
	}
	
	private void acaoJogador() {
		
		Jogador jogadorVez = jogadores[this.jogadorRodada];
		Casa casaAtual = this.casas[jogadorVez.casaAtual];	
		
		if(casaAtual instanceof Terreno) {
			Terreno terreno = (Terreno) casaAtual;
			if(terreno.getDono() == null) {
				this.notificarObservadores(casas[jogadores[jogadorRodada].casaAtual].imagem);
				if (controlador.oferecerCompra(terreno.getValorCompra())){
					terreno.comprar(jogadorVez);
					this.notificarObservadores();
				}
				else {
					this.notificarObservadores();
				}
			}
			else {
				terreno.pagarTaxa(jogadorVez, this.dado);
				
				String donoString = Jogador.getCorJogador(terreno.getDono().getNumPino());
				String msg = "Você pagou R$"+terreno.getTaxa()+" para o Jogador "+donoString;
				this.notificarObservadores();
				this.notificarMensagens(msg, "Pagamento de Aluguel");
				
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

			this.notificarObservadores(cartaAtual.imagem);
			cartas.add(cartaAtual);
			cartaAtual = null;
			}
		else {
			this.notificarObservadores();
		}
		
	}
	
	private void prenderJogador(Jogador jogador) {
		Ponto pos = this.casas[9].getPos(jogadorRodada);
		jogador.casaAtual = 9;
		jogador.setPosition(pos);
		jogador.prender();
	}
/*	
 *	Metodos interface ObservadoJogo
 *
 */
	@Override
	public void register(ObservadorJogo obj) {
		if (obj != null) {
			lst.add(obj);System.out.println("adicionou"); 	}	
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
	public void notificarObservadores(Object img) {
		int i;
		for (i = 0; i < lst.size() ; i ++) {
			lst.get(i).update(img);
		}
		
	}

	@Override
	public void notificarMensagens(String msg, String titulo) {
		int i;
		for (i = 0; i < lst.size() ; i ++) {
			lst.get(i).mostraMsg(msg, titulo);
		}
		
	}

	@Override
	public Object getUpdate(ObservadorJogo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
}//End of Class
