package Controlador;

public class Companhia extends Terreno {
	protected double taxaAluguel = 0.0;

	
	
	Companhia(int posX,int posY,int preco){
		super(posX,posY,preco);
		
		if(valorCompra <= 150) {
			taxaAluguel = 40;
			hipoteca = 75;
		}
		else {
			taxaAluguel = 50;
			hipoteca = 100;
		}
	}

	@Override
	Boolean pagarTaxa(Jogador pagador, Dados dado) {
		if(dono != null) {
			double valorPagar = taxaAluguel*dado.getSoma();	
			if(pagador.debita(valorPagar)) {
				dono.credita(valorPagar);
			}		
		}
		return false;
	}

	
	
	
}
