package presentation.uicomponents;

import javafx.scene.layout.Pane;

public abstract class ViewController<T> {

	protected Pane rootView;
	protected Pane rootViewpl;
	protected T application;
	
	public ViewController() {
		
	}
	
	public ViewController(T application) {
		this.application = application;
	}
	
	public Pane getRootView() {
		return rootView;
	}
	
	public Pane getRootViewpl() {
		return rootViewpl;
	}
	
	abstract public void initialize();
	
	public void setApplication(T application) {
		this.application = application;
	}
}
