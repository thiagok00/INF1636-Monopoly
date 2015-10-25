package Artefatos;

public class Casa {

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
	
	
}
