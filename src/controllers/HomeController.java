package controllers;

import core.Controller;
import views.HomeView;
import views.ListaEventoView;
import views.NovoEventoView;

public class HomeController extends Controller{

	private HomeView homeView;

	private ListaEventoController listaEventoController = new ListaEventoController();
	private NovoEventoController novoEventoController = new NovoEventoController(listaEventoController);
	
	public ListaEventoView getListaEventoView() {
		
		return listaEventoController.getView();
		
	}
	
	public NovoEventoView getNovoEventoView() {
		
		return novoEventoController.getView();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		listaEventoController.run();
		novoEventoController.run();
		
		homeView = new HomeView(this, mainFrame);
		addView("HomeView", homeView);
		
		mainFrame.setVisible(true);
		
	}

}
