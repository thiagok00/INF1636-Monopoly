package Controlador;

public abstract class Terreno extends Casa {
	
	protected double valorCompra;
	protected double hipoteca;
	protected Jogador dono = null;	
	protected double taxaAluguel = 0.0;
	protected double valorHipoteca = 0.0;
	protected Boolean isHipotecado = false;
	
	public Terreno(int posX,int posY,int preco){
		super(posX,posY,preco);
	}
	
	
	Boolean comprar(Jogador comprador) {			
		if (dono == null){
			if(comprador.debita(this.valorCompra)) {		
				this.dono = comprador;		
				return true;	
			}		
		}
		return false;
	}
	
	Boolean hipotecar() {
		if(!isHipotecado){
			isHipotecado = true;
			dono.credita(valorHipoteca);
			return true;
		}
		return false;
	}
	
	Boolean pagarHipoteca(Jogador dono) {
		if(isHipotecado) {
			if(dono.debita(valor*1.2)){
				isHipotecado = false;
				return true;
			}	
		}
		return false;
	}
	
	abstract Boolean pagarTaxa(Jogador pagador, Dados dado);
	

}//End of Class
