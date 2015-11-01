package Controlador;

public class Propriedade extends Terreno {
	
	private int qtdSedes = 0;
	private Boolean temComite = false;
	private double valorSede = 0.0;
	private double valorComite = 0.0;
	
	
	private Propriedade(int posX,int posY,int preco){
		super(posX,posY,preco);
	}

	@Override
	Boolean pagarTaxa(Jogador pagador, Dados dado) {
		if(dono != null) {
			if(pagador.debita(taxaAluguel)) {
				dono.credita(taxaAluguel);
			}		
		}
		return false;
	}
	
	Jogador getDono() {
		return dono;
	}
	
	int getQtdSedes() {
		return qtdSedes;
	}
	
	Boolean construirSede() {
		if (dono != null && !dono.isPreso) {
			if (qtdSedes<4) {
				if(dono.debita(valorSede)){
					qtdSedes++;
					return true;
				}
			}
		}
		return false;	
	}
	
	Boolean construirComite() {	
		if (dono != null && !dono.isPreso) {
			if (qtdSedes==4) {
				if(dono.debita(valorComite)){
					temComite = true;
					return true;
				}
			}
		}
		return false;	
	}
	
	Boolean venderConstrucao() {
		if (dono != null) {
			if (qtdSedes>0) {
				
				if (temComite) {
					dono.credita(valorComite/2);
					temComite = false;
				}
				else {
					dono.credita(valorSede/2);
					qtdSedes--;
				}
				return true;
			}
			
		}
		return false;
	}
	
	
}//End of Class
