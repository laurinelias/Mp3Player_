package application;
	
import java.util.HashMap;

import buisness.Mp3Player;
import controller.PlayerViewController;
import controller.PlaylistViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import presentation.uicomponents.Scenes;
import presentation.uicomponents.ViewController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class MainApp extends Application {
	
	private Scene scene;
	private Pane activeScene;
	private HashMap<Scenes, Pane> scenes;
	private Mp3Player mp3player ;
	
	public void init() {
		mp3player = new Mp3Player();
		scenes = new HashMap<>();
	}
	
	/**
	 * Start der Gui mit beiden Views
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			
			ViewController<MainApp> controller;
			
			controller = new PlayerViewController(this, mp3player);
			scenes.put(Scenes.PLAYERVIEW, controller.getRootView());
			
			controller = new PlaylistViewController(this, mp3player);
			scenes.put(Scenes.PLAYLISTVIEW, controller.getRootView());
			
			Pane root = scenes.get(Scenes.PLAYERVIEW);
			scene = new Scene(root,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image icon = new Image("file:mp3PLAYER.jpg");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("MP3PLAYER");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Methode zum wechseln zwischen den verschiedenen Views
	 * @param sceneName
	 */
	
	public void switchScene(Scenes sceneName) {
		System.out.println("test");
		
		Pane alternativeScene;
		
		if(scenes.containsKey(sceneName)) {
			alternativeScene = scenes.get(sceneName);
			scene.setRoot(alternativeScene);
			activeScene = alternativeScene;
			System.out.println("!");
		}
		
	}
	
	public void stop() {
		
		mp3player.stop();
		System.out.println("stop");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
