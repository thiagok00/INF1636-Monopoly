import java.awt.*;
import Artefatos.*;

import javax.swing.*;

public class Principal extends JFrame  {

	private static final long serialVersionUID = 1L;
	public final int WIDTH_DEFAULT = 800;
	public final int HEIGHT_DEFAULT =720;
	public Casa[] casas;
	private int jogadorRodada= 0;
	private int qtdJogadoresTotal=0;
	Dados dado = new Dados();
	Pino[] pinos;
	
	private Tabuleiro tabuleiro;
	private MenuJogador menu;
	
	public Principal(){
		this.setLayout(null);
		setSize(WIDTH_DEFAULT, HEIGHT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		tabuleiro = new Tabuleiro(this);
		getContentPane().add(tabuleiro);
		
		menu = new MenuJogador(this);
		getContentPane().add(menu);
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
			y-=60;					
		}
		for(;i<18;i++){			
			x+=80;
			casas[i] = new Casa(x,y);						
		}
		for(;i<28;i++){			
			y+=60;
			casas[i] = new Casa(x,y);					
		}
		for(;i<36;i++){			
			x-=80;
			casas[i] = new Casa(x,y);					
		}
		carregaPinos(qtdJogadores);
		tabuleiro.repaint();
	}
	
	public void andarJogadorAtual(Dados dado) {
		this.dado = dado;
		if (pinos != null) {
			int qtdCasas = dado.getSoma();
			Pino pino = pinos[this.jogadorRodada];
			int novaCasa = (pino.casaAtual+qtdCasas)%36;
			pino.casaAtual = novaCasa;
			Ponto pos = this.casas[novaCasa].getPos(jogadorRodada);
			pino.setPosition(pos);
			
		
			jogadorRodada++;
			jogadorRodada = jogadorRodada%6;
		}
		tabuleiro.repaint();
	}
	public void carregaPinos(int qtdJogadores) {
	
		this.pinos = new Pino[qtdJogadores];	
		for(int i=0; i < qtdJogadores; i++) {
			Ponto pos = this.casas[0].getPos(i);
			pinos[i] = new Pino(pos.x,pos.y,i);
		}
		
		
	}
	public static void main(String[] args) {
	
		Principal tab = new Principal();
		tab.setVisible(true);
	}

	
}
