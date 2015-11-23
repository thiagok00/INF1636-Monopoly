package Jogo;


public interface ObservadoJogo {

	 public void register(ObservadorJogo obj);
	    public void unregister(ObservadorJogo obj);
	     
	    //method to notify observers of change
	    public void notificarObservadores();
	    public void notificarObservadores(int numero, boolean isSorteReves);
	    public void notificarMensagens(String msg, String titulo);
		
}
	