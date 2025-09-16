package controllers;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.Controller;
import model.EventoIO;
import views.ListaEventoView;

public class ListaEventoController extends Controller{

	public ListaEventoView listaEventoView;
	public JTable tabela;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		tabela = new JTable(getDataColumns(), getNameColumns());
		listaEventoView = new ListaEventoView(this, tabela);
	}

	public void addNovaLinha(Object[] values) {
		
		((DefaultTableModel) tabela.getModel()).addRow(values);
		
	}
	
	public Vector<String> getNameColumns(){
		
		Vector<String> nameColumns = new Vector<String>();
		
		nameColumns.add("Data");
		nameColumns.add("Descrição");
		nameColumns.add("Frequência");
		nameColumns.add("Email");
		nameColumns.add("Alarme");
		
		return nameColumns;
		
	}
	
	public Vector<Vector<Object>> getDataColumns(){
		
		Vector<Vector<Object>> dataColumns = null;
		
		try {
			
			EventoIO eventoIO = new EventoIO();
			eventoIO.attach(listaEventoView);
			dataColumns = eventoIO.getEventos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataColumns;
		
	}
	
	public ListaEventoView getView() {
		return listaEventoView;
	}
	
	
	
	
	
	
	
	
	

	
	
}
