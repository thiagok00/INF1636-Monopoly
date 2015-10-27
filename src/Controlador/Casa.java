package Controlador;

public class Casa {

	private Jogador dono = null;
	private double preco = 0.0;
	private double taxaAluguel = 0.0;
	private Boolean isHipotecado = false;
	
	private int posX, posY;
	
	public Casa(int posX,int posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	public Ponto getPos(int numPino) {
		
		int xPadding = 20;
		int yPadding = 30;
		int x= this.posX,y = this.posY;
	
		x+= xPadding*(numPino%3);
		if (numPino>2) {
			y+= yPadding;
		} 
		
		return new Ponto(x,y);
	}
	
	public Boolean comprarCasa(Jogador jogador) {
		if (dono != null)
			return false;
		
		if(jogador.debita(this.preco)) {
			this.dono = jogador;
			return true;
		}
		return false;
	}
	public Boolean hipotecarCasa(Jogador jogador) {
		if(this.dono == null || this.dono != jogador)
			return false;
		
		jogador.credita(preco);
		this.isHipotecado = true;
		return true;
	}
	public Boolean recuperarCasa(Jogador jogador) {
		if(this.dono == null || !this.isHipotecado)
			return false;
					
		if (jogador.debita(preco*1.2)) {
			this.isHipotecado = false;
			return true;
		}
		return false;
	}
	public Jogador getDono() {
		return this.dono;
	}
	
	public Boolean pagarAluguel(Jogador jogador) {
		if(dono == null)
			return false;
		if(jogador.debita(taxaAluguel)) {
			dono.credita(taxaAluguel);
			return true;
		}
		//Jogador sem dinheiro pra pagar??
		return false;
	}
} //End of Class