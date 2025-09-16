package core;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import views.HomeView;

public abstract class Controller {

	// tela principal
	protected static final JFrame mainFrame = new JFrame();
	
	// armazena as views que serão carregadas na tela principal
	private static final JPanel viewsViewer = new JPanel(new CardLayout());
	
	// armazena os componentes que poderão sofrer mudanças durante a 
	// execução do programa. Normalmente JMenuBar.
	private static final Map<String, Component> mainFrameComponents = new HashMap<>();
	
	{
		mainFrame.add(viewsViewer);
	}
	
	public abstract void run();
	
	public static final void addView(String viewName, HomeView homeView) {
		viewsViewer.add(homeView, viewName);
	}
	
	public static final void loadView(String viewName) {
		CardLayout cardLayout = (CardLayout) viewsViewer.getLayout();
		cardLayout.show(viewsViewer, viewName);
	}
	
	public static final void addComponent(String name, Component component) {
		mainFrameComponents.put(name, component);
	}
	
	public static final void removeComponent(String name) {
		mainFrameComponents.remove(name);
	}
	
	public static final Component getComponent(String name) {
		return mainFrameComponents.get(name);
	}
	
	
}
