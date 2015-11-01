package Controlador;

class BancoImobiliario {

	int jogadorRodada= 0;
	int qtdJogadoresTotal=0;
	
	Dados dado = new Dados();
	Jogador[] jogadores;
	Casa[] casas;
	
	BancoImobiliario() {	
	}

	void iniciarJogo(int qtdJogadores){
		this.qtdJogadoresTotal = qtdJogadores;	
		this.casas = Casa.criarCasasBancoImobiliario();
		carregaPinos(qtdJogadores);
	}
	
	private void carregaPinos(int qtdJogadores) {
		this.qtdJogadoresTotal = qtdJogadores;
		this.jogadorRodada = 0;
		this.jogadores = new Jogador[qtdJogadores];	
		for(int i=0; i < qtdJogadores; i++) {
			Ponto pos = this.casas[0].getPos(i);
			jogadores[i] = new Jogador(pos.x,pos.y,i);
		}
	}
	
	void andarJogadorAtual(Dados dado) {
		this.dado = dado;
		if (jogadores != null) {
			int qtdCasas = dado.getSoma();
			Jogador jogadorVez = jogadores[this.jogadorRodada];
			int novaCasa = (jogadorVez.casaAtual+qtdCasas)%36;
			jogadorVez.casaAtual = novaCasa;
			Ponto pos = this.casas[novaCasa].getPos(jogadorRodada);
			jogadorVez.setPosition(pos);
		
			jogadorRodada++;
			jogadorRodada = jogadorRodada%qtdJogadoresTotal;
			this.acaoJogador();
		}
	}
	
	private void acaoJogador() {
		
		Jogador jogadorVez = jogadores[this.jogadorRodada];
		Casa casaAtual = this.casas[jogadorVez.casaAtual];	
	}
	
	

	
}//End of Class
