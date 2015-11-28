package Peças;


enum Cores {
	Laranja,
	Vermelho,
	Amarelo,
	Roxo,
	Azul,
	Verde
}
public class Propriedade extends Terreno {
	
	
	static final int CASA_POR_COR = 3;
	
	
	private int qtdSedes = 0;
	public Boolean temComite = false;
	//Preço compra/valorsede/valorcomite
	public final double precoComite;
	public final double precoSede;
	
	private final double vetorTaxas[]; 
	final Cores cor;
	
	Propriedade(int numeroCasa,double preco, double precoComite,double precoSede, double vetorPrecos[],Cores cor){
		super(numeroCasa,preco);
		this.precoComite = precoComite;
		this.precoSede = precoSede;
		this.vetorTaxas = vetorPrecos; 
		this.cor = cor;
	}


	
	public Jogador getDono() {
		return dono;
	}
	
	public int getQtdSedes() {
		return qtdSedes;
	}
	public void setQtdSedes(int qtdSedes) {
		this.qtdSedes=qtdSedes;
	}
	public void setComite(boolean Comite){
		temComite=Comite;
	}
	
	public Boolean construirSede() {
		if (dono != null && !dono.isPreso) {
			if (qtdSedes<4) {
				if(dono.debita(precoSede)){
					qtdSedes++;
					return true;
				}
			}
		}
		return false;	
	}
	
	public Boolean construirComite() {	
		
		if(temComite)
			return false;
		if (dono != null && !dono.isPreso) {
			if (qtdSedes==4) {
				if(dono.debita(precoComite)){
					temComite = true;
					return true;
				}
			}
		}
		return false;	
	}
	
	public Boolean venderConstrucao() {
		if (dono != null) {
			if (qtdSedes>0) {
				
				if (temComite) {
					dono.credita(precoComite/2);
					temComite = false;
				}
				else {
					dono.credita(precoSede/2);
					qtdSedes--;
				}
				return true;
			}
			
		}
		return false;
	}
	@Override
	public Boolean pagarTaxa(Jogador pagador, Dados dado) {
		if(dono != null) 
			if(temComite)
			{
				if(pagador.debita(vetorTaxas[5])) {
					dono.credita(vetorTaxas[5]);
				}
			}
			else
			if(pagador.debita(vetorTaxas[qtdSedes])) {
				dono.credita(vetorTaxas[qtdSedes]);
			}	
		return false;
	}
	@Override
	public Double getTaxa() {
		if(this.temComite)
			return vetorTaxas[5]*1.0;
		else
			return vetorTaxas[this.qtdSedes]*1.0;
	}
	
	
}//End of Class
