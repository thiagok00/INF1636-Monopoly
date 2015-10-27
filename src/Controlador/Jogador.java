package Controlador;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Jogador {

	private Image imagemPino;
	private int posX, posY;
	private static final String[] arrayCores = {"pinos/red_pin.png","pinos/blue_pin.png","pinos/orange_pin.png","pinos/yellow_pin.png","pinos/purple_pin.png","pinos/black_pin.png"};
	public int casaAtual = 0;
	
	public Jogador(int posX, int posY, int numeroPino) {
		
		this.posX = posX;
		this.posY = posY;
			
		try{		
			this.imagemPino = ImageIO.read(new File(arrayCores[numeroPino]));	
		}
		catch(IOException e) {
			   System.out.println(e.getMessage());
			   System.exit(1);
			}	
	}
	
	public Image getImagemPino() {
		return this.imagemPino;
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
