package Visao;
import javax.swing.*;

import Controlador.Dados;
import Controlador.BancoImobiliarioFacade;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPainel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton dado,inicio,venda,compra,passe;
	
	public MenuPainel () {
		super();	
		
		this.setBounds(700, 0, 100, 700);
		setLayout(new GridLayout(0,1));
		
		dado = new JButton("Rolar dado");
		dado.addActionListener(this);

		inicio = new JButton("Iniciar Jogo");
		inicio.addActionListener(this);
		
		/*compra = new JButton("Comprar");
		compra.addActionListener(this);*/
		
		
		venda = new JButton("Vender");
		venda.addActionListener(this);
		
		passe = new JButton("Passar Vez");
		passe.addActionListener(this);
		
		add(inicio);
		add(dado);
		//add(compra);
		add(venda);
		add(passe);
		
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource() == inicio) {
			String[] choices = { "2", "3", "4", "5", "6" };
		    String input = (String) JOptionPane.showInputDialog(null, "Quantidade de jogadores?",
		            "Iniciar Jogo", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);
		    if (input == null){
		    	return;
		    }    
		    BancoImobiliarioFacade.getIstance().iniciarJogo(Integer.parseInt(input));
		    
			  
		}
		else if (arg0.getSource() == dado) {
			
			BancoImobiliarioFacade.getIstance().rolarDado();
			dado.setEnabled(false);
		}
		else if(arg0.getSource() == compra) {
			
			//IMPLEMENTAR A OPÇAO DE COMPRA DE TERRENO/NEGOCIO
		}
		else if(arg0.getSource() == venda) {
			
			//IMPLEMENTAR A OPÇAO DE VENDA DE TERRENO/NEGOCIO
			
		}
		else if(arg0.getSource() == passe) {
			
			BancoImobiliarioFacade.getIstance().PassarRodada();
			dado.setEnabled(true);
		}
	}
	
}//End of Class
