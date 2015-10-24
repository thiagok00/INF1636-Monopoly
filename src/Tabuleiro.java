import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.*;

public class Tabuleiro extends JPanel {

	private static final long serialVersionUID = 1L;

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
	}
	
	
}