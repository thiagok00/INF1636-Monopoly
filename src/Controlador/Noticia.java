package Controlador;

public class Noticia extends Casa {

	double valor = 0.0;
	Noticia(int posX, int posY, int valor) {
		super(posX, posY);
	}

	Boolean fazerAcao(Jogador jogador) {
		if(valor>=0){
			return receba(jogador, valor);
		}
		else {
			return pague(jogador, valor);
		}
	}
	
	private Boolean receba(Jogador jogador, double valor) {
		if (jogador != null) {
			jogador.credita(valor);
			return true;
		}
		return false;
	}
	private Boolean pague(Jogador jogador, double valor) {	
		if (jogador != null){	
			if(jogador.debita(valor));	
				return true;	
		}
		return false;
	}


}//End of Class
