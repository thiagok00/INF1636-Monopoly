package Peças;

import java.util.LinkedList;
import java.util.ListIterator;

public class Jogador {

	public int casaAtual = 0;
	private double saldo;
	private int numeroPino;
	
	public LinkedList<Terreno> lstTerrenos = new LinkedList<Terreno>();

	
	
	public int passesPrisao = 0;
	public Boolean isPreso = false;
	public int rodadasPreso=0;
	
	public Jogador(int numeroPino) {
		
		this.numeroPino = numeroPino;
		this.saldo = 2500.0;

	}
			
	public double getSaldo() {
		return this.saldo;
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

	public boolean construirSede (Propriedade prp) {
		
		Cores cor = prp.cor;
		int qtdCor = 0;
		
		if (prp.getDono() != this){
			System.out.println("dono null");
			return false;	
		}
		if (prp.precoSede > this.getSaldo()){
			System.out.println("sem saldo");
			return false;
		}
			
		ListIterator<Terreno> listIterator = lstTerrenos.listIterator();
		while (listIterator.hasNext()) { 
			Terreno terreno = listIterator.next();
			if ( terreno instanceof Propriedade) {
				Propriedade propriedade = (Propriedade) terreno;
				if (propriedade.cor == cor)
					if (propriedade.getQtdSedes() < prp.getQtdSedes()) {
						System.out.println("outrasedetem+");
						return false;
					}
					qtdCor++;
			}  
		} 
		
		if (qtdCor == Propriedade.CASA_POR_COR)
			return prp.construirSede();
		
		System.out.println("nao tem todas as cores");
		return false;
	}
	
	public boolean construirHotel (Propriedade prp) {
		
		Cores cor = prp.cor;
		int qtdCor = 0;
		
		if (prp.getDono() != this)
			return false;	
			
		if (prp.precoComite > this.getSaldo())	
			return false;
		
		if (prp.getQtdSedes() != 4)
			return false;
			
		ListIterator<Terreno> listIterator = lstTerrenos.listIterator();
		while (listIterator.hasNext()) { 
			Terreno terreno = listIterator.next();
			if ( terreno instanceof Propriedade) {
				Propriedade propriedade = (Propriedade) terreno;
				if (propriedade.cor == cor)
					if (propriedade.getQtdSedes() != prp.getQtdSedes())
						return false;
					qtdCor++;
			}  
		} 
		
		if (qtdCor == Propriedade.CASA_POR_COR)
			return prp.construirComite();
		
		return false;
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
