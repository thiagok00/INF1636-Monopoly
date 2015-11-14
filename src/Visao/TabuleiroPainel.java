package Visao;
import javax.swing.*;

import Controlador.Jogador;
import Controlador.BancoImobiliarioFacade;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class TabuleiroPainel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jogadorLabel = null;
	private JLabel saldoLabel = null;
	
	
	
	private Image cartaImg;
	
	public TabuleiroPainel() {
		this.setBounds(0,0,700,700);		
	}
	
	public void repaintWithImage(Image img) {
		cartaImg = img;
		this.repaint();
	}
	
	private void updateJogadorLabel(Jogador jogador) {
		
		String corJogador = Jogador.getCorJogador(jogador.getNumPino());
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
				g.drawImage(jogador.getImagemPino(), jogador.getX(), jogador.getY(), 16, 25, null);
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
		
}//End of Class

