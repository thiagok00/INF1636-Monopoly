package Artefatos;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Pino {

	private Image imagem;
	private int posX, posY;
	private static final String[] arrayCores = {"pinos/red_pin.png","pinos/blue_pin.png","pinos/orange_pin.png","pinos/yellow_pin.png","pinos/purple_pin.png","pinos/black_pin.png"};
	public int casaAtual = 0;
	
	public Pino(int posX, int posY, int numeroPino) {
		
		this.posX = posX;
		this.posY = posY;
			
		try{		
			this.imagem = ImageIO.read(new File(arrayCores[numeroPino]));	
		}
		catch(IOException e) {
			   System.out.println(e.getMessage());
			   System.exit(1);
			}	
	}
	
	public Image getImage() {
		return this.imagem;
	}
	
	public void setPosition(Ponto pos){
		posX = pos.x;
		posY = pos.y;
	}
	public int getX() {
		return this.posX;
	}
	public int getY() {
		return this.posY;
	}
	
}//End of Class
