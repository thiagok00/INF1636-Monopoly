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
	private double saldo;
	private int numeroPino;

	
	int passesPrisao = 0;
	Boolean isPreso = false;
	int rodadasPreso=0;
	
	public Jogador(int posX, int posY, int numeroPino) {
		
		this.posX = posX;
		this.posY = posY;
		this.numeroPino = numeroPino;
		this.saldo = 25000.0;
		
		try{		
			this.imagemPino = ImageIO.read(new File(arrayCores[numeroPino]));	
		}
		catch(IOException e) {
			   System.out.println(e.getMessage());
			   System.exit(1);
			}	
	}
	
	static public String getCorJogador(int numeroPino) {
		switch(numeroPino){
			case 0:
				return "Vermelho";
			case 1:
				return "Azul";
			case 2:
				return "Laranja";
			case 3:
				return "Amarelo";
			case 4:
				return "Roxo";
			case 5:
				return "Preto";
			default:
				return "Invalido";
		}
	}	
		
	public double getSaldo() {
		return this.saldo;
	}
	
	public Boolean debita(double valor) {
		if(valor > saldo)
			return false;
		this.saldo -= valor;
		return true;
	}
	
	public void credita(double valor) {
		this.saldo += valor;
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
	
	public int getNumPino(){
		return this.numeroPino;
	}
	public void prender() {
		if(!isPreso) {
			isPreso = true;
			rodadasPreso = 3;
		}
	}
	
	
	
}//End of Class
