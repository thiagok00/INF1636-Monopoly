package Jogo;

import Peças.Jogador;

public interface ControladorEventos {

	/* Oferece comprar um terreno por preco */
	public boolean oferecerCompra(double preco);
	
	public boolean oferecerHipoteca(String nome, double preco);
	public boolean oferecerRecuperarHipoteca(String nome, double preco);
	
	/* Retorna 0 para Construir e 1 para Hipotecar */
	public int oferecerConstruirOuHipotecar();
	/* Retorna 0 para Construir e 1 para Vender construção */
	public int oferecerConstruirOuVender();
	
	/* Retorna numero da casa a ser feita ação, -1 caso não tenha selecionado nada e -10 caso não haja opções */
	public int desfazerBens(Jogador jogador,double divida);
	
	public boolean oferecerUsarPassePrisao();

}
