package Jogo;
import Peças.*;

public class BancoImobiliarioFacade {

	private static BancoImobiliarioFacade singleton = new BancoImobiliarioFacade();
	private BancoImobiliario jogo = null;
	
	public Dados dado = new Dados();
	
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
	public boolean rolarDado(int dado1, int dado2) {
		if(dado1 > 0 && dado2 > 0)
			dado = new Dados(dado1,dado2);
		else 
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
	public Double getJogadorVezSaldo() {
		return jogo.jogadores[jogo.jogadorRodada].getSaldo();
	}
		
	public void registraObserver(ObservadorJogo obj) {
		if(isJogoIniciado())
			jogo.register(obj);
	}
	
	public void clickouCasa (int numeroCasa) {
		jogo.acaoCasa(numeroCasa);
	}

	
	
}
