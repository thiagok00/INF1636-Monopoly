import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.stream.IntStream;

public class MenuJogador extends JFrame implements ActionListener{

	
	public MenuJogador (String s) {
		super(s);	
		
		JPanel p = new JPanel();
		this.getContentPane().add(p);
		p.setLayout(new GridLayout(0,1));
		
		JButton dado = new JButton("Rolar dado");
		dado.addActionListener(this);
		
		JButton inicio = new JButton("Iniciar Jogo");
		
		p.add(inicio);
		p.add(dado);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Dados nvDado = new Dados();
		System.out.println("Primeiro dado:" + nvDado.getDado1() + "Segundo dado:" + nvDado.getDado2());
	}
	
	
}
