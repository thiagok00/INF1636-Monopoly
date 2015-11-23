package Peças;

public class Noticia extends Casa {

	final double valor;
	Noticia(int numeroCasa, int valor) {
		super(numeroCasa);
		this.valor = valor;
	}

	public Boolean fazerAcao(Jogador jogador) {
		if(valor>=0){
			return receba(jogador, valor);
		}
		else {
			return pague(jogador, valor);
		}
	}
	
	public String getMensagem() {
		String msg;
		if(valor>=0){
			msg = "Você recebeu ";
		}
		else {
			msg = "Você perdeu ";
		}
		msg = msg + "$" + valor + ".";
		return msg;
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
