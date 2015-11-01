package Controlador;



import java.awt.Image;

import javax.swing.ImageIcon;

import Visao.JogoFrame;

public class BancoImobiliarioFacade {

	private static BancoImobiliarioFacade singleton = new BancoImobiliarioFacade();
	private BancoImobiliario jogo = null;
	
	public Dados dado = new Dados();
	public JogoFrame frame;
	public Image casaImg;
	
	
	public static BancoImobiliarioFacade getIstance() {
		return BancoImobiliarioFacade.singleton;		
	}
	private BancoImobiliarioFacade() {
	}
	
	public void iniciarJogo(int qtdJogadores) {
		
		this.jogo = new BancoImobiliario();
		jogo.facade = this;
		jogo.iniciarJogo(qtdJogadores);
		
		frame.repaint();
				
	}
	public void rolarDado() {
		dado = new Dados();
		if (isJogoIniciado())
			jogo.andarJogadorAtual(this.dado);	
		frame.atualizaVisao();
	}
	
	public Boolean isJogoIniciado() {
		if(this.jogo == null)
			return false;
		return true;
	}
	
	public Jogador getJogadorRodada() {
		if(isJogoIniciado()) 
			return jogo.jogadores[jogo.jogadorRodada];
		else
			return null;
	}
	public Jogador[] getJogadores() {
		if(isJogoIniciado()) 
			return jogo.jogadores;
		else
			return null;
	}
	
	public String getJogadorVezCor() {
		return Jogador.getCorJogador(jogo.jogadorRodada);
	}
	public Double getJogadorVezSaldo() {
		return jogo.jogadores[jogo.jogadorRodada].getSaldo();
	}
	
	public void atualizaComCasa() {
		casaImg =  jogo.casas[jogo.jogadores[jogo.jogadorRodada].casaAtual].imagem;
		frame.atualizaVisao();
	}
	public void atualizaTabuleiro() {
		casaImg = null;		
		frame.atualizaVisao();
	}
	
	public Boolean popUpCompra() {
		
		Terreno terreno = (Terreno) jogo.casas[jogo.jogadores[jogo.jogadorRodada].casaAtual];
		frame.atualizaVisao();
		return frame.oferecerCompra(terreno.getValorCompra());
	}
	
	public void mostrarPagamento(Jogador dono, double taxa) {
		
		frame.mostrarPagamento(Jogador.getCorJogador(dono.getNumPino()),taxa);
	}
	
	
	
	
}
