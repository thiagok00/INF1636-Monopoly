package Controlador;

import java.util.Queue;

class BancoImobiliario {

	int jogadorRodada= 0;
	int qtdJogadoresTotal=0;
	
	Dados dado = new Dados();
	Jogador[] jogadores;
	Casa[] casas;
	
	private Queue<SorteOuReves> cartas = null;
	SorteOuReves cartaAtual = null;
	private int numeroRepeticoes = 0;
	
	BancoImobiliarioFacade facade = null;
	
	BancoImobiliario() {	
	}

	void iniciarJogo(int qtdJogadores){
		this.qtdJogadoresTotal = qtdJogadores;	
		this.casas = Casa.criarCasasBancoImobiliario();
		carregaPinos(qtdJogadores);
		cartas = SorteOuReves.criarCartasBancoImobiliario();
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
	
	void andarJogadorAtual(Dados dado) {
		this.dado = dado;
		if (jogadores != null) {
						
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
					passarRodada();
					return;
				}
			
			}
			else if(dado.getDado1() == dado.getDado2() && this.numeroRepeticoes == 3) {
				prenderJogador(jogadorVez);	
				passarRodada();
				return;
			} 			
			
			int novaCasa = jogadorVez.casaAtual+qtdCasas;
			
			if (novaCasa>=36) {
				jogadorVez.credita(200);
				//Popup avisando que recebeu 200
				novaCasa = (novaCasa)%36;
			}
			
			jogadorVez.casaAtual = novaCasa;
			Ponto pos = this.casas[novaCasa].getPos(jogadorRodada);
			jogadorVez.setPosition(pos);
			this.acaoJogador();
			
			if (dado.getDado1() == dado.getDado2() && !jogadorVez.isPreso)
				this.numeroRepeticoes++;
			else
				passarRodada();
			
		}
	}
	
	private void passarRodada() {
		jogadorRodada++;
		jogadorRodada = jogadorRodada%qtdJogadoresTotal;
		this.numeroRepeticoes = 0;
	}
	
	private void acaoJogador() {
		
		Jogador jogadorVez = jogadores[this.jogadorRodada];
		Casa casaAtual = this.casas[jogadorVez.casaAtual];	
		
		if(casaAtual instanceof Terreno) {
			Terreno terreno = (Terreno) casaAtual;
			if(terreno.getDono() == null) {
				facade.atualizaComCasa();
				if (facade.popUpCompra()){
					terreno.comprar(jogadorVez);
					facade.atualizaTabuleiro();
				}
				else {
					facade.atualizaTabuleiro();
				}
			}
			else {
				terreno.pagarTaxa(jogadorVez, this.dado);
				facade.atualizaTabuleiro();
				facade.mostrarPagamento(terreno.getDono(),terreno.getTaxa());
					
				
			}
		}
		else if (casaAtual instanceof Noticia) {
			((Noticia) casaAtual).fazerAcao(jogadorVez);
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
				jogadorVez.debita(cartaAtual.valor);
			
			//carta desenha
			cartas.add(cartaAtual);
			cartaAtual = null;
			//repinta sem carta
		}
		
		
	}
	
	private void prenderJogador(Jogador jogador) {
		Ponto pos = this.casas[9].getPos(jogadorRodada);
		jogador.casaAtual = 9;
		jogador.setPosition(pos);
		jogador.prender();
	}

	
}//End of Class
