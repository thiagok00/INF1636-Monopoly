package Peças;


public class Companhia extends Terreno {
	protected double taxaAluguel = 0.0;

	
	
	Companhia(int numeroCasa, double preco){
		
		super(numeroCasa, preco);
		
		if(valorCompra <= 150) {
			taxaAluguel = 40;
			valorHipoteca = 75;
		}
		else {
			taxaAluguel = 50;
			valorHipoteca = 100;
		}
	}

	@Override
	public Boolean pagarTaxa(Jogador pagador, Dados dado) {
		if(dono != null && !isHipotecado) {
			double valorPagar = taxaAluguel*dado.getSoma();	
			if(pagador.debita(valorPagar)) {
				dono.credita(valorPagar);
			}		
		}
		return false;
	}

	@Override
	public Double getTaxa() {
		return this.taxaAluguel*1.0;
	}

	
	
	
}
