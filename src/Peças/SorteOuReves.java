package Peças;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class SorteOuReves {
	
	public double valor = 0.0;
	public Boolean passePrisao = false;
	public Boolean irPrisao = false;
	public Boolean recebeOutros = false;
	public final int numCarta;
	
	
	private SorteOuReves(int numCarta, int i) {
		if(i==0)
			setPasse();
		else if(i==1)
			setIrPrisao();
		this.numCarta = numCarta;
	}
	private SorteOuReves(int numCarta, double valor, Boolean recebeOutros){
		this.valor = valor;
		this.numCarta = numCarta;
		if(recebeOutros)
			setRecebeOutros();
		
	}
	public void setRecebeOutros() {
		recebeOutros = true;
	}
	
	private void setPasse () {
		passePrisao = true;
	}
	
	private void setIrPrisao() {
		irPrisao = true;
	}
	
	static public Queue<SorteOuReves> criarCartasBancoImobiliario() {
		
		LinkedList<SorteOuReves> list = new LinkedList<SorteOuReves>();
			
		//reves
		list.add(new SorteOuReves(1,-25,false));
		list.add(new SorteOuReves(2,-45,false));
		list.add(new SorteOuReves(3,-45,false));
		list.add(new SorteOuReves(4,-30,false));
		list.add(new SorteOuReves(5,-40,false));
		list.add(new SorteOuReves(6,-50,false));
		list.add(new SorteOuReves(7,-100,false));
		list.add(new SorteOuReves(8,-50,false));
		list.add(new SorteOuReves(9,-30,false));
		list.add(new SorteOuReves(10,-30,false));
		list.add(new SorteOuReves(11,-25,false));
		list.add(new SorteOuReves(12,1));
		list.add(new SorteOuReves(13,-100,false));
		list.add(new SorteOuReves(14,-50,false));
		list.add(new SorteOuReves(15,-15,false));
		
		
		//sorte
		list.add(new SorteOuReves(16,150,false));
		list.add(new SorteOuReves(17,100,false));
		list.add(new SorteOuReves(18,0));
		list.add(new SorteOuReves(19,80,false));
		list.add(new SorteOuReves(20,100,false));
		list.add(new SorteOuReves(21,50,true));
		list.add(new SorteOuReves(22,50,false));
		list.add(new SorteOuReves(23,20,false));
		list.add(new SorteOuReves(24,25,false));
		list.add(new SorteOuReves(25,200,false));
		list.add(new SorteOuReves(26,100,false));
		list.add(new SorteOuReves(27,100,false));
		list.add(new SorteOuReves(28,45,false));
		list.add(new SorteOuReves(29,50,false));
		list.add(new SorteOuReves(30,200,false));
		
		Collections.shuffle(list);
		return list;
		
	}
	
	
}
