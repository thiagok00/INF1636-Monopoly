package Controlador;

import javax.swing.ImageIcon;

public class Casa {

	protected int posX, posY;
	
	Boolean vaiPrisao = false;
	Boolean sorteReves = false;
	public ImageIcon imagem;
	
	static Casa[] criarCasasBancoImobiliario(){
		
		Casa casas[] = new Casa[36];
		int xInicial = 15;
		int x = xInicial;
		int yInicial = 610;
		int y = yInicial;
		

		casas[0] = new Casa(15,610);
		casas[1] = new Propriedade(15,545,220,200,200,null);
		casas[2] = new Casa(15,480);
		casas[2].sorteReves = true;
		casas[3] = new Companhia(15,415,200);
		casas[4] = new Propriedade(15,350,300,200,200,null);
		casas[5] = new Propriedade(15,285,220,150,150,null);
		casas[6] = new Casa(15,220);
		casas[6].sorteReves = true;
		casas[7] = new Companhia(15,155,150);
		casas[8] = new Propriedade(15,90,140,100,100,null);
		casas[9] = new Casa(15,25);
		casas[10] = new Propriedade(85,25,60,50,50,null);
		casas[11] = new Propriedade(155,25,180,100,100,null);
		casas[12] = new Propriedade(225,25,300,200,200,null);
		casas[13] = new Casa(290,25);
		casas[13].sorteReves = true;
		casas[14] = new Propriedade(355,25,260,150,150,null);
		casas[15] = new Companhia(420,25,200);
		casas[16] = new Propriedade(485,25,220,150,150,null);
		casas[17] = new Noticia(550,25, 200);
		casas[18] = new Casa(615,25);
		casas[19] = new Companhia(615,90,200);
		casas[20] = new Propriedade(615,155,180,100,100,null);
		casas[21] = new Propriedade(615,220,140,100,100,null);
		casas[22] = new Propriedade(615,285,120,50,50,null);
		casas[23] = new Casa (615,350);
		casas[23].sorteReves = true;
		casas[24] = new Companhia(615,415,200);
		casas[25] = new Propriedade(615,480,60,50,50,null);
		casas[26] = new Propriedade(615,545,260,150,150,null);
		casas[27] = new Casa(615,610);
		casas[27].vaiPrisao = true;
		casas[28] = new Propriedade(550,610,160,100,100,null);
		casas[29] = new Casa(485,610);
		casas[29].sorteReves = true;
		casas[30] = new Propriedade(420,610,240,150,150,null);
		casas[31] = new Companhia(355,610,150);
		casas[32] = new Propriedade(290,610,100,50,50,null);
		casas[33] = new Casa(225,610);
		casas[33].sorteReves = true;
		casas[34] = new Noticia(160,610,-200);
		casas[35] = new Propriedade(95,610,100,50,50,null);
		
		for(Casa casa : casas) {
			
			System.out.println("("+casa.posX+","+casa.posY+")");
		}
		
		return casas;
	}
	



	protected Casa(int posX,int posY){
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
	
	public void setImagem (String path) {
		imagem = new ImageIcon(path);
	}
	
} //End of Class