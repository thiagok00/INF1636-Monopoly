package Controlador;

public abstract class Terreno extends Casa {
	
	protected double valorCompra;
	protected double hipoteca;
	protected Jogador dono = null;	
	protected double valorHipoteca = 0.0;
	protected Boolean isHipotecado = false;
	
	public double getValorCompra() {
		return this.valorCompra;
	}
	
	public Terreno(int posX,int posY,int preco){
		super(posX,posY);
	}
	
	
	Boolean comprar(Jogador comprador) {			
		if (dono == null) {
			if(comprador.debita(this.valorCompra)) {		
				this.dono = comprador;		
				return true;	
			}		
		}
		return false;
	}
	
	Boolean hipotecar() {
		if(dono!= null) {
			if(!isHipotecado){
				isHipotecado = true;
				dono.credita(valorHipoteca);
				return true;
			}
		}
		return false;
	}
	
	Boolean pagarHipoteca() {
		if(dono != null) { 
			if(isHipotecado) {
				if(dono.debita(valorCompra*1.2)){
					isHipotecado = false;
					return true;
				}	
			}
		}
		return false;
	}
	
	Jogador getDono() {
		return this.dono;
	}
	
	abstract Boolean pagarTaxa(Jogador pagador, Dados dado);
	

}//End of Class
