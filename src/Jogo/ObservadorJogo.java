package Jogo;


public interface ObservadorJogo {

	void update();
	void update(int numero,boolean isSorteReves);
	void mostraMsg(String msg, String titulo);
}
