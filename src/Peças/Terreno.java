package Peças;

public abstract class Terreno extends Casa {
	
	protected double valorCompra;
	protected double hipoteca;
	protected Jogador dono = null;	
	private double valorHipoteca;
	public Boolean isHipotecado = false;
	
	public double getValorCompra() {
		return this.valorCompra;
	}
	
	public Terreno(int numeroCasa,double preco) {
		super(numeroCasa);
		this.valorCompra = preco;
	}
	
	
	public Boolean comprar(Jogador comprador) {			
		if (dono == null) {
			if(comprador.debita(this.valorCompra)) {		
				this.dono = comprador;		
				return true;	
			}		
		}
		return false;
	}
	
	public Boolean hipotecar() {
		if(dono!= null) {
			if(!isHipotecado){
				isHipotecado = true;
				dono.credita(valorHipoteca);
				return true;
			}
		}
		return false;
	}
	
	public Boolean pagarHipoteca() {
		if(dono != null) { 
			if(isHipotecado) {
				if(dono.debita(getPagamentoHipoteca())){
					isHipotecado = false;
					return true;
				}	
			}
		}
		return false;
	}
	
	public double getPagamentoHipoteca() {
		return valorCompra*1.2;
	}
	public Jogador getDono() {
		return this.dono;
	}
	
	public double getValor() {
		return this.valorCompra;
	}
	public double getValorHipoteca() {
		return this.valorHipoteca;
	}

	
	public abstract Boolean pagarTaxa(Jogador pagador, Dados dado);
	public abstract Double getTaxa();

}//End of Class