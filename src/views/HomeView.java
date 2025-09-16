package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controllers.HomeController;
import core.Model;
import core.View;

public class HomeView extends JPanel implements View {

	private HomeController homeController;
	private JFrame mainFrame;
	private final static int MAIN_FRAME_WIDTH = 500;
	private final static int MAIN_FRAME_HEIGHT = 350;
	private final static int MAIN_FRAME_X = 100;
	private final static int MAIN_FRAME_Y = 100;
	
	public HomeView(HomeController homeController, JFrame mainFrame) {
		
		this.homeController = homeController;
		this.mainFrame = mainFrame;
		
		makeMainFrame();
		makeTabs();
	}
	
	private void makeMainFrame() {
		
		// makeMainFrame = fazer tela principal
		
		mainFrame.setOpacity(1.0f);
		mainFrame.setBounds(MAIN_FRAME_X, MAIN_FRAME_Y, MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));
		
	}
	
	private void makeTabs() {
		
		// makeTabs = fazerAbas
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Novo Evento", homeController.getNovoEventoView());
		tabbedPane.addTab("Agenda", homeController.getListaEventoView());
		add(tabbedPane, BorderLayout.CENTER);
		
	}
	
	@Override
	public void update(Model model, Object data) {
		// TODO Auto-generated method stub
		
	}

}
