import javax.swing.*;
import Artefatos.Pino;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.*;

public class Tabuleiro extends JPanel {

	private static final long serialVersionUID = 1L;
	private Principal frame;

	public Tabuleiro(Principal frame) {
		this.frame = frame;
		this.setBounds(0,0,700,700);		
	}
	
	/* Paint Component */
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

		if (frame.pinos != null) {
			
			for (Pino pino : frame.pinos) {
				g.drawImage(pino.getImage(), pino.getX(), pino.getY(), 16, 25, null);
			}
		}
	
	}
	

	
	
}//End of Class