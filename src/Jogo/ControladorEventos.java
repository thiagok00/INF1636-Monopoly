package Jogo;

public interface ControladorEventos {

	/* Oferece comprar um terreno por preco */
	public boolean oferecerCompra(double preco);
	

	public boolean pagarDividas();
	
	public boolean oferecerHipoteca(String nome, double preco);
	public boolean oferecerRecuperarHipoteca(String nome, double preco);
	
	/* Retorna 0 para Construir e 1 para Hipotecar */
	public int oferecerConstruirOuHipotecar();
	/* Retorna 0 para Construir e 1 para Vender construção */
	public int oferecerConstruirOuVender();
	

}
