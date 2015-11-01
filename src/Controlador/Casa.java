package Controlador;

public class Casa {

	
	protected double valor = 0.0;
	protected int posX, posY;
	
	Boolean vaiPrisao = false;
	Boolean sorteReves = false;
	
	static Casa[] criarCasasBancoImobiliario(){
		
		Casa casas[] = new Casa[36];
		int xInicial = 15;
		int x = xInicial;
		int yInicial = 610;
		int y = yInicial;
		int vetPrecos[] = {0,220,0,200,300,220,300,220,0,150,140,0,60,180,300,0,260,200,220,0,0,200,180,140,120,0,200,60,260,0,160,0,240,150,100,0,0,100};
		
		//Inicializando casas
		int i;
		for(i=0;i<10;i++){	
			casas[i] = new Casa(x,y,vetPrecos[i]);
			if(i!=9)
			y-=65;
			else{}
							
		}
		for(;i<19;i++){	
			if(i>=13)
				x+=65;
			else
			x+=70;
			casas[i] = new Casa(x,y,vetPrecos[i]);						
		}
		for(;i<28;i++){			
			y+=65;
			casas[i] = new Casa(x,y,vetPrecos[i]);					
		}
		for(;i<36;i++){			
			x-=65;
			casas[i] = new Casa(x,y,vetPrecos[i]);					
		}
		return casas;
	}
	
	
	protected Casa(int posX,int posY,int valor){
		this.posX = posX;
		this.posY = posY;
		this.valor = (double) valor;
	}
	
	public Ponto getPos(int numPino) {
		
		int xPadding = 20;
		int yPadding = 30;
		int x= this.posX,y = this.posY;
	
		x+= xPadding*(numPino%3);
		if (numPino>2) {
			y+= yPadding;
		} 
		
		return new Ponto(x,y);
	}
	
	
	
} //End of Class