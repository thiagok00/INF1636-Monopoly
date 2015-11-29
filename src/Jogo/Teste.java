package Jogo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import Peças.*;

public class Teste {

	static public void iniciarComArquivo(BancoImobiliario jogo,String nomeArq)
	{
		
		int qtdJogadores,numProp,qtdSede,dono,casaAtual = 0,i;
		double saldo;
		boolean isPreso=false,Comite=false;
		Scanner in = null; 
		try {
			in = new Scanner(new File(nomeArq));
		}
		catch(IOException e){
			System.out.println(nomeArq+"/"+e.getMessage());
			System.exit(1);
		}
		qtdJogadores=in.nextInt();
		jogo.iniciarJogo(qtdJogadores);
		for(i=0; i<qtdJogadores ; i++) {
			
			saldo=in.nextDouble();
			jogo.jogadores[i].setSaldo(saldo);
			casaAtual=in.nextInt();
			jogo.jogadores[i].casaAtual=casaAtual;
			isPreso=in.nextBoolean();
			jogo.jogadores[i].isPreso = isPreso;
			jogo.jogadores[i].passesPrisao = in.nextInt();
		}
		
		while(in.hasNext()) {
			numProp=in.nextInt();
			qtdSede=in.nextInt();
			Comite=in.nextBoolean();
			dono=in.nextInt();
			
			if(jogo.casas[numProp] instanceof Propriedade) {
				Propriedade prop = (Propriedade)jogo.casas[numProp];
				prop.setQtdSedes(qtdSede);
				prop.setComite(Comite);
				jogo.jogadores[dono].credita(prop.getValorCompra());
				jogo.jogadores[dono].comprarTerreno(prop);
			}
			else {	
				System.out.println("Arq com erro");
				System.exit(1);	
			}	
		}
	in.close();
	jogo.notificarObservadores();
	}
	
}//End of Class
