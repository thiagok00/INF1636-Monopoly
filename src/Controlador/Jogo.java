package Controlador;

import Visao.JogoFrame;

public class Jogo {

	private static Jogo jogo_instance = null;
	private int jogadorRodada= 0;
	private int qtdJogadoresTotal=0;
	
	public Dados dado = new Dados();
	public Jogador[] jogadores;
	public JogoFrame frame;
	public Casa[] casas;
	
	public static Jogo getIstance() {
		if (jogo_instance == null)
			jogo_instance = new Jogo();
		return jogo_instance;
	} 
	private Jogo() {	
	}

	public void iniciaJogo(int qtdJogadores){
		this.qtdJogadoresTotal = qtdJogadores;
				
		casas = new Casa[36];
		int xInicial = 15;
		int x = xInicial;
		int yInicial = 610;
		int y = yInicial;
		
		//Inicializando casas
		int i;
		for(i=0;i<10;i++){	
			casas[i] = new Casa(x,y);
			if(i!=9)
			y-=65;
			else{}
							
		}
		for(;i<19;i++){	
			if(i>=13)
				x+=65;
			else
			x+=70;
			casas[i] = new Casa(x,y);						
		}
		for(;i<28;i++){			
			y+=65;
			casas[i] = new Casa(x,y);					
		}
		for(;i<36;i++){			
			x-=65;
			casas[i] = new Casa(x,y);					
		}
		carregaPinos(qtdJogadores);
		
		//repaint
		this.frame.atualizaVisao();
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
	public void andarJogadorAtual(Dados dado) {
		this.dado = dado;
		if (jogadores != null) {
			int qtdCasas = dado.getSoma();
			Jogador jogadorVez = jogadores[this.jogadorRodada];
			int novaCasa = (jogadorVez.casaAtual+qtdCasas)%36;
			jogadorVez.casaAtual = novaCasa;
			Ponto pos = this.casas[novaCasa].getPos(jogadorRodada);
			jogadorVez.setPosition(pos);
		
			jogadorRodada++;
			jogadorRodada = jogadorRodada%qtdJogadoresTotal;
		}
		//repaint
		this.frame.atualizaVisao();
	}
	
}//End of Class
