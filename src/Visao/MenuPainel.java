package Visao;
import javax.swing.*;
import Jogo.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPainel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton dado,inicio,venda,construir,passe,hipotecas;
	JogoFrame frame;
	
	public MenuPainel () {
		super();	
		
		this.setBounds(700, 0, 100, 700);
		setLayout(new GridLayout(0,1));
		
		dado = new JButton("Rolar dado");
		dado.addActionListener(this);

		inicio = new JButton("Iniciar Jogo");
		inicio.addActionListener(this);
		
		construir = new JButton("Construir");
		construir.addActionListener(this);
		
		
		venda = new JButton("Vender");
		venda.addActionListener(this);
		
		hipotecas = new JButton("Hipotecas");
		venda.addActionListener(this);
		
		passe = new JButton("Passar Vez");
		passe.addActionListener(this);
		
		
		
		passe.setEnabled(false);
	    construir.setEnabled(false);
	    venda.setEnabled(false);
		hipotecas.setEnabled(false);
	    
		add(inicio);
		add(dado);
		add(construir);
		add(venda);
		add(hipotecas);
		add(passe);
	 

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		BancoImobiliarioFacade jogo = BancoImobiliarioFacade.getIstance();
		
		if (arg0.getSource() == inicio) {
			Integer[] choices = { 2, 3, 4, 5, 6};
		    Integer input = (Integer) JOptionPane.showInputDialog(this.getParent(), "Quantidade de jogadores?",
		            "Iniciar Jogo", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);
		    if (input == null){
		    	return;
		    }    
		   
		    dado.setEnabled(true);
		    passe.setEnabled(false);
		    construir.setEnabled(false);
		    venda.setEnabled(false);
		    hipotecas.setEnabled(false);
		    jogo.iniciarJogo(input,frame.controladorEventos,frame.tabuleiro);
			  
		}
		else if (arg0.getSource() == dado) {
			
			if (!jogo.rolarDado(0,0)) {
				dado.setEnabled(false);
			    passe.setEnabled(true);
			    construir.setEnabled(true);
			    venda.setEnabled(true);
			    hipotecas.setEnabled(true);
			}
			else if (!jogo.isJogoIniciado()) {
				frame.tabuleiro.update();
			}
		}
		else if(arg0.getSource() == construir) {
			
		}
		else if(arg0.getSource() == venda) {
			
			//IMPLEMENTAR A OPÇAO DE VENDA DE TERRENO/NEGOCIO
			
		}
		else if (arg0.getSource() == hipotecas){
			
		}
		else if(arg0.getSource() == passe) {
			
			if (BancoImobiliarioFacade.getIstance().PassarRodada()) {
				dado.setEnabled(true);
				passe.setEnabled(false);
				construir.setEnabled(false);
				venda.setEnabled(false);
				hipotecas.setEnabled(false);
			}
		}
	}
	
}//End of Class
