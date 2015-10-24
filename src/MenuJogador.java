import javax.swing.*;

import Artefatos.Dados;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.stream.IntStream;

public class MenuJogador extends JPanel implements ActionListener{

	
	
	Principal frame;
	JButton dado,inicio;
	
	public MenuJogador (Principal frame) {
		super();	
		
		this.frame = frame;
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
		        frame.iniciaJogo(Integer.parseInt(input));
			  
		}
		else if (arg0.getSource() == dado) {
			Dados nvDado = new Dados();
			System.out.println("Primeiro dado:" + nvDado.getDado1() + "Segundo dado:" + nvDado.getDado2());
			frame.andarJogadorAtual(nvDado.getSoma());
		}
	}
	
}
