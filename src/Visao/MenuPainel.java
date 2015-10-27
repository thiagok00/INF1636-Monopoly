package Visao;
import javax.swing.*;

import Controlador.Dados;
import Controlador.Jogo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPainel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton dado,inicio;
	
	public MenuPainel () {
		super();	
		
		this.setBounds(700, 0, 100, 700);
		setLayout(new GridLayout(0,1));
		
		dado = new JButton("Rolar dado");
		dado.addActionListener(this);
		
		inicio = new JButton("Iniciar Jogo");
		inicio.addActionListener(this);
		
		add(inicio);
		add(dado);
		
		
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
		    Jogo.getIstance().iniciaJogo(Integer.parseInt(input));
			  
		}
		else if (arg0.getSource() == dado) {
			Dados nvDado = new Dados();
			Jogo.getIstance().andarJogadorAtual(nvDado);
		}
	}
	
}
