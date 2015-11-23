package Visao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Jogo.ControladorEventos;
import Peças.*;

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
	public int oferecerConstruir(String[] choices, int n) {
		String input = (String) JOptionPane.showInputDialog(frame, "Quantidade de jogadores?",
	            "Iniciar Jogo", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);
		if (input == null)  
	
			return -1;
		return input.charAt(0)-'0';
	}

	@Override
	public boolean pagarDividas() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int oferecerHipoteca(Jogador jogador ,Terreno terrenos[]) {
		//Terreno terrenos[] = jogador.get
		String choices[] = new String[terrenos.length];
		int i=0;
		for (Terreno terreno : terrenos) {
			if (terreno.isHipotecado) {
				if (jogador.getSaldo() >= terreno.getPagamentoHipoteca())
					choices[i] = "Pagar hipoteca de "+terreno.nome+" por R$"+terreno.getPagamentoHipoteca();
				else 			
					choices[i] = "Você não tem dinheiro para pagar a hipoteca de "+terreno.nome+"";
				}
			else{
				if (terreno instanceof Propriedade && ((Propriedade) terreno).getQtdSedes() > 0) 
					choices[i] = terreno.nome+" tem construções e não pode ser hipotecado.";
			
				else 
					choices[i] = "Hipotecar "+terreno.nome+" por R$"+terreno.getValorHipoteca();
			}
			i++;
		}
		
		String input = (String) JOptionPane.showInputDialog(frame, "Escolha uma opção:",
	            "Hipotecas", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);
		if (input == null)  
			return -1;
		
		return input.charAt(0)-'0';
	}

}//End of Class