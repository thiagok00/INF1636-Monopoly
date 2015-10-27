package Visao;
import javax.swing.*;

import Controlador.Jogo;

public class JogoFrame extends JFrame  {

	private static final long serialVersionUID = 1L;
	public final int WIDTH_DEFAULT = 800;
	public final int HEIGHT_DEFAULT =720;

	
	private TabuleiroPainel tabuleiro;
	private MenuPainel menu;
	private Jogo jogo;
	
	public JogoFrame(){
		this.setLayout(null);
		setSize(WIDTH_DEFAULT, HEIGHT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		jogo = Jogo.getIstance();
		jogo.frame = this;
		
		tabuleiro = new TabuleiroPainel();
		getContentPane().add(tabuleiro);
		
		menu = new MenuPainel();
		getContentPane().add(menu);
	}
	
	public static void main(String[] args) {
	
		JogoFrame tab = new JogoFrame();
		tab.setVisible(true);
	}

	public void atualizaVisao() {
		tabuleiro.repaint();
	}
	
}
