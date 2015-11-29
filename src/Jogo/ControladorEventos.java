package Jogo;

import Pe�as.Jogador;

public interface ControladorEventos {

	/* Oferece comprar um terreno por preco */
	public boolean oferecerCompra(double preco);
	
	public boolean oferecerHipoteca(String nome, double preco);
	public boolean oferecerRecuperarHipoteca(String nome, double preco);
	
	/* Retorna 0 para Construir e 1 para Hipotecar */
	public int oferecerConstruirOuHipotecar();
	/* Retorna 0 para Construir e 1 para Vender constru��o */
	public int oferecerConstruirOuVender();
	
	/* Retorna numero da casa a ser feita a��o, -1 caso n�o tenha selecionado nada e -10 caso n�o haja op��es */
	public int desfazerBens(Jogador jogador,double divida);
	
	public boolean oferecerUsarPassePrisao();

}
