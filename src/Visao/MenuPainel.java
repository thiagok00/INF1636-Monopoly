package Visao;
import javax.swing.*;
import Jogo.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPainel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton dado,inicio,passe,dadoViciado,carregaJogo;
	JogoFrame frame;
	
	public MenuPainel () {
		super();	
		
		this.setBounds(700, 0, 150, 700);
		setLayout(new GridLayout(0,1));
		
		dado = new JButton("Rolar dado");
		dado.addActionListener(this);

		inicio = new JButton("Iniciar Jogo");
		inicio.addActionListener(this);
		
		passe = new JButton("Passar Vez");
		passe.addActionListener(this);		
		passe.setEnabled(false);
		
		dadoViciado = new JButton("Dado Viciado");
		dadoViciado.addActionListener(this);		
		//dadoViciado.setEnabled(false);

		carregaJogo = new JButton("Carrega Jogo");
		carregaJogo.addActionListener(this);		
		
		
		add(inicio);
		add(dado);
		add(passe);
		add(dadoViciado);
		add(carregaJogo);
	 

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
		    dadoViciado.setEnabled(true);
		    passe.setEnabled(false);
		    jogo.iniciarJogo(input,frame.controladorEventos,frame.tabuleiro);
			  
		}
		else if (arg0.getSource() == dado) {
			
			if (!jogo.rolarDado(0,0)) {
				dado.setEnabled(false);
				dadoViciado.setEnabled(false);
			    passe.setEnabled(true);
			}
			else if (!jogo.isJogoIniciado()) {
				frame.tabuleiro.update();
			}
		}
		else if(arg0.getSource() == passe) {
			if (BancoImobiliarioFacade.getIstance().PassarRodada()) {
				dado.setEnabled(true);
				dadoViciado.setEnabled(true);
				passe.setEnabled(false);
			}
		}
		else if(arg0.getSource() == dadoViciado) {
			
			int n1 = -1;
			int n2 = -1;
			String fNumber = JOptionPane.showInputDialog("Valor do primeiro dado");	             
			if (fNumber != null)
				n1 = Integer.parseInt( fNumber );

		
			String fNumber2 = JOptionPane.showInputDialog("Valor do segundo dado");	  
			if (fNumber2 != null)
				n2 = Integer.parseInt( fNumber2 );
			
			if (n1 < 1 || n1 > 6 || n2 < 1 || n2 > 6)
				 return;
			 
			if (!jogo.rolarDado(n1,n2)) {
				dado.setEnabled(false);
				dadoViciado.setEnabled(false);
			    passe.setEnabled(true);
			}
			else if (!jogo.isJogoIniciado()) {
				frame.tabuleiro.update();
			}
		}
		else if (arg0.getSource() == carregaJogo) {
			jogo.iniciarJogoArq(frame.controladorEventos,frame.tabuleiro);
			
			//TODO CARREGAR
			
			
		}
	}
	
	
}//End of Class
