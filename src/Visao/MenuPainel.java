package Visao;
import javax.swing.*;

import Controlador.Dados;
import Controlador.BancoImobiliarioFacade;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPainel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton dado,inicio,venda,construir,passe;
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
		
		passe = new JButton("Passar Vez");
		passe.addActionListener(this);
		
		
		
		passe.setEnabled(false);
	    construir.setEnabled(false);
	    venda.setEnabled(false);
		
		add(inicio);
		add(dado);
		add(construir);
		add(venda);
		add(passe);
	 

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource() == inicio) {
			String[] choices = { "2", "3", "4", "5", "6" };
		    String input = (String) JOptionPane.showInputDialog(null, "Quantidade de jogadores?",
		            "Iniciar Jogo", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);
		    if (input == null){
		    	return;
		    }    
		    
		    BancoImobiliarioFacade jogo = BancoImobiliarioFacade.getIstance();
		    dado.setEnabled(true);
		    passe.setEnabled(false);
		    construir.setEnabled(false);
		    venda.setEnabled(false);
		    jogo.iniciarJogo(Integer.parseInt(input),frame,frame);
			  
		}
		else if (arg0.getSource() == dado) {
			
			if (!BancoImobiliarioFacade.getIstance().rolarDado()) {
				dado.setEnabled(false);
			    passe.setEnabled(true);
			    construir.setEnabled(true);
			    venda.setEnabled(true);
			}
		}
		else if(arg0.getSource() == construir) {
			
			//IMPLEMENTAR A OPÇAO DE CONSTRUIR COMITE ETC
		}
		else if(arg0.getSource() == venda) {
			
			//IMPLEMENTAR A OPÇAO DE VENDA DE TERRENO/NEGOCIO
			
		}
		else if(arg0.getSource() == passe) {
			
			if (BancoImobiliarioFacade.getIstance().PassarRodada()) {
				dado.setEnabled(true);
				passe.setEnabled(false);
				construir.setEnabled(false);
				venda.setEnabled(false);
			}
		}
	}
	
}//End of Class
