package Visao;
import javax.swing.*;

import Peças.*;
import Jogo.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;

import javax.imageio.*;

public class TabuleiroPainel extends JPanel implements ObservadorJogo, MouseListener {

	private static final long serialVersionUID = 1L;
	private JLabel jogadorLabel = null;
	private JLabel saldoLabel = null;
	
	
	
	private Image cartaImg;
	
	public TabuleiroPainel() {
		this.setBounds(0,0,700,700);	
		this.addMouseListener(this);
	}
	
	public void repaintWithImage(Image img) {
		cartaImg = img;
		this.repaint();
	}
	
	private void updateJogadorLabel(Jogador jogador) {
		
		String corJogador = Jogador.getJogadorCor(jogador);
		Double saldo = jogador.getSaldo();
		
		if(jogadorLabel == null) {
			jogadorLabel = new JLabel();
			jogadorLabel.setFont(jogadorLabel.getFont ().deriveFont (30.0f));
			
			saldoLabel = new JLabel();
			saldoLabel.setFont(saldoLabel.getFont ().deriveFont (30.0f));
			this.add(jogadorLabel);
			this.add(saldoLabel);
		}
			jogadorLabel.setText("Vez do jogador "+corJogador);;
			saldoLabel.setText("Saldo: "+saldo);
			jogadorLabel.setBounds(110, 100, 500, 200);
			saldoLabel.setBounds(110, 150, 500, 200);		
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		
		BufferedImage i=null;
		try {
		   i=ImageIO.read(new File("tabuleiroRJ.jpg"));
		}
		catch(IOException e) {
		   System.out.println(e.getMessage());
		   System.exit(1);
		}
		g.drawImage(i, 0, 0, 700, 700, null);	

		BancoImobiliarioFacade jogo = BancoImobiliarioFacade.getIstance();
		if (jogo.isJogoIniciado()) {
			
			this.updateJogadorLabel(jogo.getJogadorRodada());
			
			Jogador jogadores[] = jogo.getJogadores();
			for (Jogador jogador : jogadores) {
				Ponto pos = this.getPosicaoJogador(jogador);
				g.drawImage(getImagemPino(jogador), pos.x, pos.y, 16, 25, null);
			}
			
			Propriedade propriedades[] = jogo.getPropriedades();
			for (Propriedade prop : propriedades) {
				Image  propImg = getPropImagem(prop);
				if (propImg != null) {
					Ponto p = getPropImgPos(prop);
					g.drawImage(propImg, p.x, p.y, 25, 25, null);		
				}
			}
			
		}
		
		int dado1 = jogo.dado.getDado1();
		int dado2 = jogo.dado.getDado2();
		String d1name = "Dado/"+Integer.toString(dado1)+".png";
		String d2name = "Dado/"+Integer.toString(dado2)+".png";;
		Image d1img=null,d2img=null;
		try {
			   d1img=ImageIO.read(new File(d1name));
			   d2img=ImageIO.read(new File(d2name));
			   
			}
			catch(IOException e) {
			   System.out.println(d1name+"|  |"+d2name);
			   System.out.println(e.getMessage());
			   System.exit(1);
			}
		g.drawImage(d1img, 304, 500, 32, 30, null);
		g.drawImage(d2img, 344, 500, 32, 30, null);	
		
	
		if (cartaImg != null) {			
			g.drawImage(cartaImg,304, 250, 280, 350, null);	
		}	
	}
	
	Image getImagemPino(Jogador jogador) {
		
		String imgPath;
		switch(jogador.getNumPino()) {
		case 0:
			imgPath = "Pinos/red_pin.png";
			break;
		case 1:
			imgPath = "Pinos/blue_pin.png";
			break;
		case 2:
			imgPath = "Pinos/orange_pin.png";
			break;
		case 3:
			imgPath = "Pinos/yellow_pin.png";
			break;
		case 4:
			imgPath = "Pinos/purple_pin.png";
			break;
		case 5:
			imgPath = "Pinos/black_pin.png";
			break;
		default:
			imgPath = "Pinos/black_pin.png";
			break;
		}
		Image img=null;
		try {
			   img=ImageIO.read(new File(imgPath));;
			   
			}
			catch(IOException e) {
			   System.out.println(imgPath+"/"+e.getMessage());
			   System.exit(1);
			}
		
		return img;
	}
	
	Ponto getPosicaoJogador(Jogador jogador) {

		int primColunaX = 15;
		int primLinhaY = 25;
		int segColunaX = 615;
		int segLinhaY = 610;
		int posCasa = jogador.casaAtual;
		int numPino = jogador.getNumPino();
		Ponto pos;
		
		if (posCasa < 10) {
			int posY[] = {610,545,480,415,350,285,220,155,90,primLinhaY};
			pos = new Ponto(primColunaX,posY[posCasa]);
		}
		else if (posCasa < 19) {
			int posX[] = {85,155,225,290,355,420,485,550,segColunaX};
			pos = new Ponto(posX[posCasa-10],primLinhaY);
		}
		else if (posCasa < 28) {
			int posY[] = {90,155,220,285,350,415,480,545,segLinhaY};
			pos = new Ponto(segColunaX,posY[posCasa-19]);
		}
		else if (posCasa < 36) {
			int posX[] = {550,485,420,355,290,225,160,95};
			pos = new Ponto(posX[posCasa-28],segLinhaY);
		}
		else 
			return new Ponto(0,0);
		
		int xPadding = 20;
		int yPadding = 30;
	
		pos.x+= xPadding*(numPino%3);
		if (numPino>2) 
			pos.y+= yPadding;
		
		return pos;
	}	
	
	
	private Image getPropImagem(Propriedade prop) {
		
		Image img = null;
		if (prop.getDono() != null) {
			String imgPath = "Construcoes/";
			String strCor = Jogador.getJogadorCor(prop.getDono());
			int qtdSedes = prop.getQtdSedes();
		
			if (prop.isHipotecado)
				imgPath =  imgPath+strCor+"Hipotecado";
			else if (prop.temComite)
				imgPath = imgPath+strCor+" Comite";
			else
				imgPath = imgPath+strCor+"Casa "+qtdSedes;
			
			imgPath = imgPath+".png";
			try {
				img=ImageIO.read(new File(imgPath));;
			}	
			catch(IOException e) {
				System.out.println(imgPath+"/"+e.getMessage());
				System.exit(1);	
			}	
		}		
		return img;
	}
	
	private Ponto getPropImgPos(Propriedade prop) {
		
		int numCasa = prop.numeroCasa;
		
		if (numCasa == 1)
			return new Ponto(34,563);
		if (numCasa == 4)
			return new Ponto(34,370);
		if (numCasa == 5)
			return new Ponto(34,300);
		if (numCasa == 8)
			return new Ponto(34,105);
		if (numCasa == 10)
			return new Ponto(106,35);
		if (numCasa == 11)
			return new Ponto(175,35);		
		if (numCasa == 12)
			return new Ponto(238,35);
		if (numCasa == 14)
			return new Ponto(368,35);
		if (numCasa == 16)
			return new Ponto(496,35);
		if (numCasa == 20)
			return new Ponto(640,176);
		if (numCasa == 21)
			return new Ponto(640,238);
		if (numCasa == 22)
			return new Ponto(640,300);
		if (numCasa == 25)
			return new Ponto(640,500);
		if (numCasa == 26)
			return new Ponto(640,563);
		if (numCasa == 28)
			return new Ponto(563,638);
		if (numCasa == 30)
			return new Ponto(434,638);
		if (numCasa == 32)
			return new Ponto(306,638);
		if (numCasa == 35)
			return new Ponto(108,638);		
		
		return new Ponto(100,100);
	}
	
	
	
	/*
	 * Metodos ObservadorJogo
	 */
	@Override
	public void update() {
		this.repaintWithImage(null);
	}

	@Override
	public void update(int numero, boolean isSorteReves) {
		
		String imgPath;
		DecimalFormat df = new DecimalFormat("00");
		if (isSorteReves) {
			
			if (numero > 15) { 
				imgPath = "sorteReves/sorte";
				numero = numero%15;
				if (numero==0)
					numero = 15;
			}
			else 
				imgPath = "sorteReves/reves";
			
			imgPath = imgPath+df.format(numero)+".jpg";
		}
		else {
			imgPath = "Terrenos/";
			if (numero == 1)
				imgPath = imgPath + "curicica.jpg";
			else if (numero == 3)
				imgPath = imgPath + "empresa3.jpg";
			else if (numero == 4)
				imgPath = imgPath + "leme.jpg";
			else if (numero == 5)
				imgPath = imgPath + "vilarcarioca.jpg";
			else if (numero == 7)
				imgPath = imgPath + "empresa2.jpg";
			else if (numero == 8)
				imgPath = imgPath + "morro18.jpg";
			else if (numero == 10)
				imgPath = imgPath + "guapore.jpg";
			else if (numero == 11)
				imgPath = imgPath + "tanque.jpg";
			else if (numero == 12)
				imgPath = imgPath + "botafogo.jpg";
			else if (numero == 14)
				imgPath = imgPath + "batan.jpg";
			else if (numero == 15)
				imgPath = imgPath + "empresa6.jpg";
			else if (numero == 16)
				imgPath = imgPath + "barbante.jpg";
			else if (numero == 19)
				imgPath = imgPath + "empresa5.jpg";
			else if (numero == 20)
				imgPath = imgPath + "gardeniaazul.jpg";
			else if (numero == 21)
				imgPath = imgPath + "caixaagua.jpg";
			else if (numero == 22)
				imgPath = imgPath + "kelsons.jpg";
			else if (numero == 24)
				imgPath = imgPath + "empresa4.jpg";
			else if (numero == 25)
				imgPath = imgPath + "quitungo.jpg";
			else if (numero == 26)
				imgPath = imgPath + "riodaspedras.jpg";
			else if (numero == 27)
				imgPath = imgPath + "fuba.jpg";
			else if (numero == 31)
				imgPath = imgPath + "empresa1.jpg";
			else if (numero == 32)
				imgPath = imgPath + "fumace.jpg";
			else if (numero == 35)
				imgPath = imgPath + "cidadealta.jpg";
			else {
				this.repaintWithImage(null);
				return;
			}
		}
		
		Image img=null;
		try {
			   img=ImageIO.read(new File(imgPath));;
			   
			}
			catch(IOException e) {
			   System.out.println(imgPath+"/"+e.getMessage());
			   System.exit(1);
			}
		
		
		repaintWithImage(img);
	}
	
	
	
	

	@Override
	public void mostraMsg(String msg, String titulo) {
		JOptionPane.showMessageDialog(this.getParent(),msg,titulo,JOptionPane.PLAIN_MESSAGE);		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		int xPos = arg0.getX();
		int yPos = arg0.getY();
		BancoImobiliarioFacade jogo = BancoImobiliarioFacade.getIstance();
		int numeroCasa = -1;		
		
		if (xPos > 5 && xPos < 90) {
		
			if (yPos < 90)
				numeroCasa = 9;
			else if (yPos < 154)
				numeroCasa = 8;
			else if (yPos < 220)
				numeroCasa = 7;
			else if (yPos < 284)
				numeroCasa = 6;
			else if (yPos < 349)
				numeroCasa = 5;
			else if (yPos < 414)
				numeroCasa = 4;
			else if (yPos < 479)
				numeroCasa = 3;
			else if (yPos < 544)
				numeroCasa = 2;
			else if (yPos < 609 )
				numeroCasa = 1;
			else if (yPos < 691)
				numeroCasa = 0;
			}
		else if(yPos < 89 && yPos > 6) {
			if(xPos < 154)
			numeroCasa=10;
			else if(xPos < 220)
			numeroCasa=11;
			else if(xPos < 284)
			numeroCasa=12;
			else if(xPos < 348)
			numeroCasa=13;
			else if(xPos < 412)
			numeroCasa=14;
			else if(xPos < 476)
			numeroCasa=15;
			else if(xPos < 540)
			numeroCasa=16;
			else if(xPos < 604)
			numeroCasa=17;
			else if(xPos < 693)
			numeroCasa=18;
			}
		else if(xPos > 609 && xPos < 693) {
			if( yPos < 155)
			numeroCasa= 19;
			else if(yPos < 220)
			numeroCasa=20;
			else if(yPos < 285)
			numeroCasa=21;
			else if(yPos < 350)
			numeroCasa=22;
			else if(yPos < 415)
			numeroCasa=23;
			else if(yPos < 482)
			numeroCasa=24;
			else if(yPos < 544)
			numeroCasa=25;
			else if(yPos < 610)
			numeroCasa=26;
			else if(yPos < 693)
			numeroCasa=27;
		}
		else if(yPos > 609 && yPos < 690) {
			if(xPos < 156)
			numeroCasa=35;
			else if(xPos < 222)
			numeroCasa=34;
			else if(xPos < 286)
			numeroCasa=33;
			else if(xPos < 350)
			numeroCasa=32;
			else if(xPos < 414)
			numeroCasa=31;
			else if(xPos < 478)
			numeroCasa=30;
			else if(xPos < 543)
			numeroCasa=29;
			else if(xPos < 608)
			numeroCasa=28;			
		}
	
		if (numeroCasa != -1) {
			//printar coord System.out.println(xPos+" "+yPos+" casa: "+numeroCasa);
			jogo.clickouCasa(numeroCasa);	
		}	
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}			
		

}//End of Class

