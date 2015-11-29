package Visao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Jogo.ControladorEventos;
import Peças.Jogador;
import Peças.Propriedade;
import Peças.Terreno;

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

	@Override
	public int desfazerBens(Jogador jogador, double divida) {
		
		ArrayList<String> choices = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("00");
		
		ListIterator<Terreno> listIterator = jogador.lstTerrenos.listIterator();
		while (listIterator.hasNext()) { 
			Terreno terreno = listIterator.next();
			boolean semSede = true;
			if ( terreno instanceof Propriedade) {	
					Propriedade pr = (Propriedade) terreno;
					 if (pr.getQtdSedes() > 0) {
						if (pr.temComite){
							choices.add(df.format(pr.numeroCasa)+". Vender comite de "+pr.nome+" por R$"+(pr.precoComite/2));
							semSede = false;
						}
						else {
							choices.add(df.format(pr.numeroCasa)+". Vender sede de "+pr.nome+" por R$"+pr.precoSede/2);
							semSede = false;
						}
					 }				
			}
			if(!terreno.isHipotecado && semSede){
				choices.add(df.format(terreno.numeroCasa)+". Hipotecar "+terreno.nome+" por R$"+terreno.getValorHipoteca());
			}
		}
		
		if (choices.size() == 0)
			return -10;

		String[] choicesArray = choices.toArray(new String[choices.size()]);
		
		String msg = "Você tem que pagar uma divida de R$"+divida+". O que deseja fazer?";
	    String input = (String) JOptionPane.showInputDialog(frame, msg,
	            "DIVIDA", JOptionPane.QUESTION_MESSAGE, null,choicesArray,choicesArray[0]);
	    if (input == null){
	    	return -1;
	    }  
		
	    int resp = Integer.parseInt(input.substring(0, 2));
	    
	    return resp;
	}



}//End of Class