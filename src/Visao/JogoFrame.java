package Visao;
import javax.swing.*;
import Jogo.*;

public class JogoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public final int WIDTH_DEFAULT = 850;
	public final int HEIGHT_DEFAULT =720;
	
	TabuleiroPainel tabuleiro;
	MenuPainel menu;
	BancoImobiliarioFacade jogo;
	ControladorEventos controladorEventos;
	
	public JogoFrame(){
		this.setLayout(null);
		setSize(WIDTH_DEFAULT, HEIGHT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		controladorEventos = new ControladorEventosSwing(this);
		
		jogo = BancoImobiliarioFacade.getIstance();
		
		tabuleiro = new TabuleiroPainel();
		getContentPane().add(tabuleiro);
		
		menu = new MenuPainel();
		menu.frame = this;
		getContentPane().add(menu);
	}
	
	public static void main(String[] args) {
	
		JogoFrame tab = new JogoFrame();
		tab.setVisible(true);
	}

}//End of Class
