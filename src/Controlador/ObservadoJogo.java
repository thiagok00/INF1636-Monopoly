package Controlador;


public interface ObservadoJogo {

	 public void register(ObservadorJogo obj);
	    public void unregister(ObservadorJogo obj);
	     
	    //method to notify observers of change
	    public void notificarObservadores();
	    public void notificarObservadores(Object img);
	    public void notificarMensagens(String msg, String titulo);

	    //method to get updates from subject
	    public Object getUpdate(ObservadorJogo obj);
		
}
	