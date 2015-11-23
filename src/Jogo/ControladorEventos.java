package Jogo;

import Pe�as.Jogador;
import Pe�as.Terreno;

public interface ControladorEventos {

	public boolean oferecerCompra(double preco);
	public int oferecerConstruir(String choices[],int n);
	public boolean pagarDividas();
	public int oferecerHipoteca(Jogador jogador ,Terreno terrenos[]);
}
