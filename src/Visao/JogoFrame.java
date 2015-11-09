package Visao;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Controlador.BancoImobiliarioFacade;
import Controlador.Jogador;

public class JogoFrame extends JFrame  {

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
	
	public Boolean oferecerCompra(double valor) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		String msg = "Deseja comprar o terreno por R$"+valor+"?";
		
		int dialogResult =JOptionPane.showConfirmDialog (null, msg,"Compra de Terreno",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION)
			return true;
		else 
			return false;
		
	}
	
	public void mostrarPagamento(String dono, double taxa) {
		
		String msg = "Você pagou R$"+taxa+" para o Jogador "+dono;
		JOptionPane.showMessageDialog(this,msg,"Pagamento de Aluguel",JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public void mostrarProLabore() {
		String msg = "Você recebeu R$200!";
		JOptionPane.showMessageDialog(this,msg,"Pagamento de Pró-Labore",JOptionPane.PLAIN_MESSAGE);
	}
	
	
}//End of Class
