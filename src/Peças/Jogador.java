package Peças;

import java.util.LinkedList;

public class Jogador {

	public int casaAtual = 0;
	private double saldo;
	private int numeroPino;
	
	public LinkedList<Terreno> lstTerrenos = new LinkedList<Terreno>();

	
	
	public int passesPrisao = 0;
	public Boolean isFalido = false;
	public Boolean isPreso = false;
	public int rodadasPreso=0;
	
	public Jogador(int numeroPino) {
		
		this.numeroPino = numeroPino;
		this.saldo = 2500.0;

	}
			
	public double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(double preco) {
		this.saldo=preco;
	}
	
	public Boolean debita(double valor) {
		if(valor > saldo)
			return false;
		this.saldo -= valor;
		return true;
	}
	
	public void credita(double valor) {
		this.saldo += valor;
	}
		
	public int getNumPino(){
		return this.numeroPino;
	}

	public void prender() {
		if(!isPreso) {
			isPreso = true;
			rodadasPreso = 3;
		}
	}
	
	public void comprarTerreno(Terreno trno) {
		if(trno.getValorCompra() < this.saldo) {
			trno.comprar(this);
			lstTerrenos.add(trno);
		}
	}

	static public String getJogadorCor(Jogador jogador) {
		switch (jogador.getNumPino()) {
		case 0:
			return "Vermelho";
		case 1:
			return "Azul";
		case 2:
			return "Laranja";
		case 3:
			return "Amarelo";
		case 4:
			return "Roxo";
		case 5:
			return "Preto";
		default:
			return "SemCor";
		}		
	}
	
}//End of Class
