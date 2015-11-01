package Controlador;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Casa {

	protected int posX, posY;
	
	Boolean vaiPrisao = false;
	Boolean sorteReves = false;
	Image imagem = null;
	
	static Casa[] criarCasasBancoImobiliario(){
		
		Casa casas[] = new Casa[36];

		int vetPrecos1[] = {28,150,450,1000,1200,1400} ;
		int vetPrecos2[] = {26,130,390,900,1100,1275};
		int vetPrecos3[] = {18,90,250,700,875,1050};
		int vetPrecos4[] = {10,50,150,450,625,750};
		int vetPrecos5[] = {4,20,60,180,320,450};
		int vetPrecos6[] = {14,70,200,550,750,950};
		int vetPrecos7[] = {26,130,390,900,1100,1275};
		int vetPrecos8[] = {22,110,330,800,975,1150};
		int vetPrecos9[] = {18,90,250,700,875,1050};
		int vetPrecos10[] = {14,70,200,550,750,950};
		int vetPrecos11[] = {10,50,150,450,625,750};
		int vetPrecos12[] = {8,40,100,300,450,600};
		int vetPrecos13[] = {4,20,60,180,320,450};
		int vetPrecos14[] = {22,110,330,800,975,1150};
		int vetPrecos15[] = {12,60,180,500,700,900};
		int vetPrecos16[] = {20,100,300,750,925,1100};
		int vetPrecos17[] = {6,30,90,270,400,500};
		int vetPrecos18[] =	{6,30,90,270,400,500};
		casas[0] = new Casa(15,610);
		casas[1] = new Propriedade(15,545,220,200,200,vetPrecos1);
		casas[1].setImagem("Terrenos/curicica.jpg");
		casas[2] = new Casa(15,480);
		casas[2].sorteReves = true;
		casas[3] = new Companhia(15,415,200);
		casas[3].setImagem("Terrenos/empresa3.jpg");
		casas[4] = new Propriedade(15,350,300,200,200,vetPrecos2);
		casas[4].setImagem("Terrenos/leme.jpg");
		casas[5] = new Propriedade(15,285,220,150,150,vetPrecos3);
		casas[5].setImagem("Terrenos/vilarcarioca.jpg");
		casas[6] = new Casa(15,220);
		casas[6].sorteReves = true;
		casas[7] = new Companhia(15,155,150);
		casas[7].setImagem("Terrenos/empresa2.jpg");
		casas[8] = new Propriedade(15,90,140,100,100,vetPrecos4);
		casas[8].setImagem("Terrenos/morro18.jpg");
		casas[9] = new Casa(15,25);
		casas[10] = new Propriedade(85,25,60,50,50,vetPrecos5);
		casas[10].setImagem("Terrenos/guapore.jpg");
		casas[11] = new Propriedade(155,25,180,100,100,vetPrecos6);
		casas[11].setImagem("Terrenos/tanque.jpg");
		casas[12] = new Propriedade(225,25,300,200,200,vetPrecos7);
		casas[12].setImagem("Terrenos/botafogo.jpg");
		casas[13] = new Casa(290,25);
		casas[13].sorteReves = true;
		casas[14] = new Propriedade(355,25,260,150,150,vetPrecos8);
		casas[14].setImagem("Terrenos/batan.jpg");
		casas[15] = new Companhia(420,25,200);
		casas[15].setImagem("Terrenos/empresa6.jpg");
		casas[16] = new Propriedade(485,25,220,150,150,vetPrecos9);
		casas[16].setImagem("Terrenos/barbante.jpg");
		casas[17] = new Noticia(550,25, 200);
		casas[18] = new Casa(615,25);
		casas[19] = new Companhia(615,90,200);
		casas[19].setImagem("Terrenos/empresa5.jpg");
		casas[20] = new Propriedade(615,155,180,100,100,vetPrecos10);
		casas[20].setImagem("Terrenos/gardeniaazul.jpg");
		casas[21] = new Propriedade(615,220,140,100,100,vetPrecos11);
		casas[21].setImagem("Terrenos/caixaagua.jpg");
		casas[22] = new Propriedade(615,285,120,50,50,vetPrecos12);
		casas[22].setImagem("Terrenos/kelsons.jpg");
		casas[23] = new Casa (615,350);
		casas[23].sorteReves = true;
		casas[24] = new Companhia(615,415,200);
		casas[24].setImagem("Terrenos/empresa4.jpg");
		casas[25] = new Propriedade(615,480,60,50,50,vetPrecos13);
		casas[25].setImagem("Terrenos/quitungo.jpg");
		casas[26] = new Propriedade(615,545,260,150,150,vetPrecos14);
		casas[26].setImagem("Terrenos/riodaspedras.jpg");
		casas[27] = new Casa(615,610);
		casas[27].vaiPrisao = true;
		casas[28] = new Propriedade(550,610,160,100,100,vetPrecos15);
		casas[28].setImagem("Terrenos/fuba.jpg");
		casas[29] = new Casa(485,610);
		casas[29].sorteReves = true;
		casas[30] = new Propriedade(420,610,240,150,150,vetPrecos16);
		casas[30].setImagem("Terrenos/carobinha.jpg");
		casas[31] = new Companhia(355,610,150);
		casas[31].setImagem("Terrenos/empresa1.jpg");
		casas[32] = new Propriedade(290,610,100,50,50,vetPrecos17);
		casas[32].setImagem("Terrenos/fumace.jpg");
		casas[33] = new Casa(225,610);
		casas[33].sorteReves = true;
		casas[34] = new Noticia(160,610,-200);
		casas[35] = new Propriedade(95,610,100,50,50,vetPrecos18);
		casas[35].setImagem("Terrenos/cidadealta.jpg");
		
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
		try {
			this.imagem = ImageIO.read(new File(path));
			   
			}
			catch(IOException e) {
			   System.out.println(e.getMessage());
			   System.exit(1);
			}
	}




	Double getTaxa() {
		return null;
	}
	
} //End of Class