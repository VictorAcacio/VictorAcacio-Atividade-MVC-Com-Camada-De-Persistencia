package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.ListaEventoController;
import core.Model;
import core.View;

public class ListaEventoView extends JPanel implements View {

	public ListaEventoController listaEventoController;
	public JTable tabela;
	
	
	public ListaEventoView(ListaEventoController listaEventoController, JTable tabela) {
		
		this.listaEventoController = listaEventoController;
		this.tabela = tabela;
		
		makeFrame();
	}
	
	private void makeFrame() {
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}



	@Override
	public void update(Model model, Object data) {
		// TODO Auto-generated method stub
		
	}

}
