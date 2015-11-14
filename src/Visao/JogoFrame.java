package Visao;
import java.awt.Image;

import javax.swing.*;

import Controlador.BancoImobiliarioFacade;
import Controlador.ControladorEventos;
import Controlador.ObservadorJogo;

public class JogoFrame extends JFrame implements ObservadorJogo , ControladorEventos {

	private static final long serialVersionUID = 1L;
	public final int WIDTH_DEFAULT = 800;
	public final int HEIGHT_DEFAULT =720;

	
	private TabuleiroPainel tabuleiro;
	private MenuPainel menu;
	private BancoImobiliarioFacade jogo;
	
	public JogoFrame(){
		this.setLayout(null);
		setSize(WIDTH_DEFAULT, HEIGHT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
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

	public void atualizaVisao() {
		tabuleiro.repaint();
	}

	@Override
	public void update() {
		this.tabuleiro.repaintWithImage(null);
	}

	@Override
	public void update(Object img) {
		tabuleiro.repaintWithImage((Image) img);
	}

	@Override
	public void mostraMsg(String msg, String titulo) {
		JOptionPane.showMessageDialog(this,msg,titulo,JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public boolean oferecerCompra(double preco) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		String msg = "Deseja comprar o terreno por R$"+preco+"?";
		
		int dialogResult =JOptionPane.showConfirmDialog (null, msg,"Compra de Terreno",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION)
			return true;
		else 
			return false;
	}

	@Override
	public boolean pagarDividas() {
		// TODO Auto-generated method stub
		return false;
	}
	
}//End of Class
