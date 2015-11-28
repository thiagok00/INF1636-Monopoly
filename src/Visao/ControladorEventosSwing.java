package Visao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Jogo.ControladorEventos;

public class ControladorEventosSwing implements ControladorEventos {

	private JFrame frame;
	
	
	public ControladorEventosSwing(JFrame frame) {
		this.frame = frame;		
	}
	
	@Override
	public boolean oferecerCompra(double preco) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		String msg = "Deseja comprar o terreno por R$"+preco+"?";
		
		int dialogResult =JOptionPane.showConfirmDialog (frame, msg,"Compra de Terreno",dialogButton);
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

	@Override
	public boolean oferecerHipoteca(String nome, double preco) {
		
		int dialogButton = JOptionPane.YES_NO_OPTION;
		String msg = "Deseja hipotecar "+nome+" por R$"+preco+"?";
		
		int dialogResult =JOptionPane.showConfirmDialog (frame, msg,"Hipoteca",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION)
			return true;
		else 
			return false;
	}

	@Override
	public boolean oferecerRecuperarHipoteca(String nome, double preco) {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		String msg = "Deseja recuperar hipoteca "+nome+" por R$"+preco+"?";
		
		int dialogResult =JOptionPane.showConfirmDialog (frame, msg,"Pagar Hipoteca",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION)
			return true;
		else 
			return false;
	}

	@Override
	public int oferecerConstruirOuHipotecar() {
		Object[] options = { "Construir", "Hipotecar" };
		
		int resp = JOptionPane.showOptionDialog(frame, "Escolha uma das ações", "Ação",
		JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, options, options[0]);
		
		return resp;
	}

	@Override
	public int oferecerConstruirOuVender() {
		Object[] options = { "Construir", "Vender" };
		
		int resp = JOptionPane.showOptionDialog(frame, "Escolha uma das ações", "Construções",
		JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, options, options[0]);
		
		return resp;
	}



}//End of Class