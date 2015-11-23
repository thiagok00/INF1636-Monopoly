package Jogo;

import Peças.Jogador;
import Peças.Terreno;

public interface ControladorEventos {

	public boolean oferecerCompra(double preco);
	public int oferecerConstruir(String choices[],int n);
	public boolean pagarDividas();
	public int oferecerHipoteca(Jogador jogador ,Terreno terrenos[]);
}
