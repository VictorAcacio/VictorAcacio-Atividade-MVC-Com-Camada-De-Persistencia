package controllers;

import core.Controller;
import model.Evento;
import model.EventoIO;
import views.NovoEventoView;

public class NovoEventoController extends Controller {

	private NovoEventoView novoEventoView;
	
	public ListaEventoController listaEventoController;
	
	public NovoEventoController(ListaEventoController listaEventoController) {
		this.listaEventoController = listaEventoController;
	}
	
	public void addEvento(Evento evento) {
		
		Object[] metadados = new Object[5];
		
		metadados[0] = evento.getDate();
		metadados[1] = evento.getDescricaoEvento();
		metadados[2] = evento.getFrequencia();
		metadados[3] = evento.getEmail();
		metadados[4] = evento.isAlarme() ? "ON" : "OFF";
		
		try {
			
			EventoIO eventoIO = new EventoIO();
			eventoIO.attach(novoEventoView);
			eventoIO.salvarEvento(evento);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		listaEventoController.addNovaLinha(metadados);

		
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		novoEventoView = new NovoEventoView(this);
	}


	public NovoEventoView getView() {
		return novoEventoView;
	}
	
	

}
