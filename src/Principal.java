import java.awt.*;
import javax.swing.*;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	public final int WIDTH_DEFAULT = 800;
	public final int HEIGHT_DEFAULT = 800;
	
	public Principal(){
		setSize(WIDTH_DEFAULT, HEIGHT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(new Tabuleiro());
		this.setResizable(false);
	}
	public static void main(String[] args) {
	
		Principal tab = new Principal();
		//tab.setLayout(null);
		tab.setVisible(true);

		MenuJogador menu = new MenuJogador("Menu Jogador");
		menu.setVisible(true);
		
	}

}
