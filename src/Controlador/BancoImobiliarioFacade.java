package Controlador;



import java.awt.Image;

import Visao.JogoFrame;

public class BancoImobiliarioFacade {

	private static BancoImobiliarioFacade singleton = new BancoImobiliarioFacade();
	private BancoImobiliario jogo = null;
	
	public Dados dado = new Dados();
	public Image casaImg;
	
	
	public static BancoImobiliarioFacade getIstance() {
		
		return BancoImobiliarioFacade.singleton;		
	}
	private BancoImobiliarioFacade() {
	}
	
	public void iniciarJogo(int qtdJogadores,ControladorEventos controlador, ObservadorJogo obj) {
		
		this.jogo = new BancoImobiliario(controlador);
		jogo.register(obj);
		jogo.iniciarJogo(qtdJogadores);
		
	}
	public boolean rolarDado() {
		dado = new Dados();		
		if (isJogoIniciado())
			return jogo.andarJogadorAtual(this.dado);	
		return true;
	}
	
	//Ativado pelo botao de Passar Rodada
	public boolean PassarRodada() {
		if (isJogoIniciado())
			return jogo.passarRodada();
		return false;
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
		
	public void registraObserver(ObservadorJogo obj) {
		System.out.println("registrando facade");
		if(isJogoIniciado())
			jogo.register(obj);
	}
	
	
	
}
