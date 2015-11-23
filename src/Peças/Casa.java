package Peças;

/*Propriedade*/

public class Casa {
	
	public int numeroCasa;	
	public String nome = "";
	public Boolean vaiPrisao = false;
	public Boolean sorteReves = false;
	
	protected Casa(int numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	
	
	public static Casa[] criarCasasBancoImobiliario(){
		
		Casa casas[] = new Casa[36];
		int numeroCasa=0;
		
		String sorteRevesString = "Sorte-Reves";
		String nomes[] = {"Partida","Curicica",sorteRevesString,"Máquinas de Caça-Niquel","Leme","Villar Carioca",
				sorteRevesString,"Botijão de Gas","Morro do 15","Prisão", "Guaporé","Tanque","Botafogo",sorteRevesString,
				"Batan","Transporte Alternativo", "Barbante", "Propinas e Extorsões","Pagode Cerveja","Segurança Patrimonial",
				"Gardenia Azul","Caixa d'Agua", "Kelsons",sorteRevesString,"Serviço de Moto-Taxi","Quitungo","Rio das Pedras",
				"Bangu 1","Fubá", sorteRevesString,"Carobinha","Sinal de TV a Gato", "Fumacê", sorteRevesString,
				"Custo de Campanha","Cidade Alta"};
		
		double[] vetPrecos[] = { {28,150,450,1000,1200,1400}, {26,130,390,900,1100,1275}, {18,90,250,700,875,1050},
				{10,50,150,450,625,750}, {4,20,60,180,320,450}, {14,70,200,550,750,950}, {26,130,390,900,1100,1275},
				{22,110,330,800,975,1150},{18,90,250,700,875,1050}, {14,70,200,550,750,950}, {10,50,150,450,625,750},
				{8,40,100,300,450,600}, {4,20,60,180,320,450}, {22,110,330,800,975,1150}, {12,60,180,500,700,900},
				{20,100,300,750,925,1100}, {6,30,90,270,400,500}, {6,30,90,270,400,500} };


		casas[0] = new Casa(numeroCasa++);
		casas[1] = new Propriedade(numeroCasa++,220,200,200,vetPrecos[0], Cores.Laranja);
		casas[2] = new Casa(numeroCasa++);
		casas[2].sorteReves = true;
		casas[3] = new Companhia(numeroCasa++,200);
		casas[4] = new Propriedade(numeroCasa++,300,200,200,vetPrecos[1],Cores.Vermelho);
		casas[5] = new Propriedade(numeroCasa++,220,150,150,vetPrecos[2],Cores.Amarelo);
		casas[6] = new Casa(numeroCasa++);
		casas[6].sorteReves = true;
		casas[7] = new Companhia(numeroCasa++,150);
		casas[8] = new Propriedade(numeroCasa++,140,100,100,vetPrecos[3],Cores.Roxo);
		casas[9] = new Casa(numeroCasa++);
		casas[10] = new Propriedade(numeroCasa++,60,50,50,vetPrecos[4],Cores.Azul);
		casas[11] = new Propriedade(numeroCasa++,180,100,100,vetPrecos[5],Cores.Laranja);
		casas[12] = new Propriedade(numeroCasa++,300,200,200,vetPrecos[6],Cores.Vermelho);
		casas[13] = new Casa(numeroCasa++);
		casas[13].sorteReves = true;
		casas[14] = new Propriedade(numeroCasa++,260,150,150,vetPrecos[7],Cores.Verde);
		casas[15] = new Companhia(numeroCasa++,200);
		casas[16] = new Propriedade(numeroCasa++,220,150,150,vetPrecos[8],Cores.Amarelo);
		casas[17] = new Noticia(numeroCasa++, 200);
		casas[18] = new Casa(numeroCasa++);
		casas[19] = new Companhia(numeroCasa++,200);
		casas[20] = new Propriedade(numeroCasa++,180,100,100,vetPrecos[9],Cores.Laranja);
		casas[21] = new Propriedade(numeroCasa++,140,100,100,vetPrecos[10],Cores.Roxo);
		casas[22] = new Propriedade(numeroCasa++,120,50,50,vetPrecos[11],Cores.Verde);
		casas[23] = new Casa (numeroCasa++);
		casas[23].sorteReves = true;
		casas[24] = new Companhia(numeroCasa++,200);
		casas[25] = new Propriedade(numeroCasa++,60,50,50,vetPrecos[12],Cores.Azul);
		casas[26] = new Propriedade(numeroCasa++,260,150,150,vetPrecos[13],Cores.Vermelho);
		casas[27] = new Casa(numeroCasa++);
		casas[27].vaiPrisao = true;
		casas[28] = new Propriedade(numeroCasa++,160,100,100,vetPrecos[14],Cores.Roxo);
		casas[29] = new Casa(numeroCasa++);
		casas[29].sorteReves = true;
		casas[30] = new Propriedade(numeroCasa++,240,150,150,vetPrecos[15],Cores.Amarelo);
		casas[31] = new Companhia(numeroCasa++,150);
		casas[32] = new Propriedade(numeroCasa++,100,50,50,vetPrecos[16],Cores.Verde);
		casas[33] = new Casa(numeroCasa++);
		casas[33].sorteReves = true;
		casas[34] = new Noticia(numeroCasa++,-200);
		casas[35] = new Propriedade(numeroCasa,100,50,50,vetPrecos[17],Cores.Azul);
		
		for (int i=0;i<36;i++)
			casas[i].nome = nomes[i];	
		
		return casas;
	}

}//End of Class
