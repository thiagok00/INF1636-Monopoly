package Peças;

import java.util.ListIterator;

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
		this.valorHipoteca = preco;
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
			
			if (!checaSePodeConstruir())
				return false;

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
			
			if (!checaSePodeConstruir())
				return false;
			
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
	
	private boolean checaSePodeConstruir() {
		
		int qtdCor = 0;
		ListIterator<Terreno> listIterator = dono.lstTerrenos.listIterator();
		while (listIterator.hasNext()) { 
			Terreno terreno = listIterator.next();
				if ( terreno instanceof Propriedade) {	
					Propriedade propriedade = (Propriedade) terreno;
					if (propriedade.cor == this.cor) {
						if (propriedade.getQtdSedes() < this.getQtdSedes() || propriedade.isHipotecado) {
							return false;		
						}
					qtdCor++;		
					}									
				}		
		}
		
		if (qtdCor < CASA_POR_COR ) {
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public double devolverBanco() {
		this.dono = null;
		this.isHipotecado = false;
		this.qtdSedes = 0;
		this.temComite = false;		
		return this.valorCompra;
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
	public Double getTaxa(Dados dado) {
		if(this.temComite)
			return vetorTaxas[5]*1.0;
		else
			return vetorTaxas[this.qtdSedes]*1.0;
	}
	
	
}//End of Class
