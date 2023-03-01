package controller;

import java.awt.Color;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import application.MainApp;
import buisness.Mp3Player;
import buisness.Track;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import presentation.uicomponents.Cover;
import presentation.uicomponents.PlayerView;
import presentation.uicomponents.PlaylistView;
import presentation.uicomponents.Scenes;
import presentation.uicomponents.ViewController;


public class PlayerViewController extends ViewController<MainApp>{

	
	/**
	 * Controller des Playerviews
	 */
	
	private PlayerView playerView;

	private Mp3Player mp3Player;
	private Track track;
	private Timer timer;
	private TimerTask task;
	
	boolean repeatpressed = false;
	boolean shufflepressed = false;
	boolean play;
	boolean pause;
	boolean skip;
	
	boolean running;
	Label currentTime;
	
	
	Button homeButton;
	Button playlistButton;
	Button searchButton;
	
	HBox songinfos;
	Slider sliderVolume;
	ImageView coverImage;
	ImageView covertrack;
	Cover cover;
	Label title ;
	Label interpret;
	Label album;
	
	ProgressBar progressBarSong;
	Label time;
	
	
	Button repeatButton;
	Button skipbackButton;
	Button playButton;
	Button pauseButton;
	Button skipButton;
	Button shuffleButton;
	double countersec = 0.0;
	
	
	
	private PlaylistView playlistView;
	
	
	
	public PlayerViewController(MainApp application, Mp3Player mp3player) {
		super(application);
		
		this.rootView = new PlayerView();
				
		PlayerView view = (PlayerView) rootView;
		

		homeButton = view.homeButton;
		playlistButton = view.playlistButton;
		searchButton = view.searchButton;
		
		sliderVolume = view.sliderVolume;
		
		
		covertrack = view.covertrack;
		
		songinfos = view.songInfos;
		title = view.title;
		interpret = view.interpret;
		album = view.album;
		
		progressBarSong = view.progressBarSong;
		time = view.time;
		
		currentTime = view.time;
		
		repeatButton = view.repeatButton;
		skipbackButton = view.skipbackButton;
		playButton = view.playButton;
		pauseButton = view.pauseButton;
		skipButton = view.skipButton;
		shuffleButton = view.shuffleButton;
		
		this.mp3Player = mp3player;
		initialize();
		
		
	}
	/**
	 * Beide controllerSkip und controllerPlay starten den jewaligen Thread um Nebenläufigkeit zu ermöglichen
	 */

	public void controllerPlay() {

		playThread();
		progressThread();
		
		
	}
	
	public void controllerSkip() {

		skipThread();
		progressThread();
		System.out.println("titel: "+ mp3Player.getCurrentTrack().getTitle());

	}
	
	
	@Override
	public void initialize() {
		
		/**
		 * Initialisierung der Butttons
		 */
		
		
		homeButton.setOnAction(event -> application.switchScene(Scenes.PLAYERVIEW));
		
		playlistButton.setOnAction(e -> application.switchScene(Scenes.PLAYLISTVIEW));
		
		
		
		sliderVolume.valueProperty().addListener( 

			
			( oV,  oldValue,  newValue) -> {
				
				double volume;
				volume = newValue.doubleValue()/100;
				mp3Player.volume(volume);
				System.out.println(newValue);
				
			}
			
		);
		
	
		progressBarSong.setProgress((double)countersec);
		
		System.out.println();
		
		repeatButton.setOnMouseClicked(e-> {
			mp3Player.repeat();
			
			if(repeatpressed == false) {

				repeatButton.getStyleClass().removeAll("shufflebtn");
				repeatButton.getStyleClass().add("shuffleButton");
				repeatpressed = true;
			}else {
				repeatButton.getStyleClass().removeAll("shuffleButton");
				repeatButton.getStyleClass().add("shufflebtn");
				repeatpressed = false;

			}
			
		});
		
		skipbackButton.setOnAction(e -> {
			skip = false;
			controllerSkip();

		});
		

		playButton.setOnAction(e -> {
			controllerPlay();
		});
		
		pauseButton.setOnAction(e -> {

			
			pauseThread();
			
		});
		
		skipButton.setOnAction(e -> {
			skip = true;

			controllerSkip();

		});
		
		shuffleButton.setOnAction(e ->{
			
			mp3Player.shuffle();
			
			if(shufflepressed == false) {

				shuffleButton.getStyleClass().removeAll("shufflebtn");
				shuffleButton.getStyleClass().add("shuffleButton");
				shufflepressed = true;
			}else {
				shuffleButton.getStyleClass().removeAll("shuffleButton");
				shuffleButton.getStyleClass().add("shufflebtn");
				shufflepressed = false;

			}
		});
		

		
		mp3Player.currentPlaylist.addListener((o,oldValue, newValue) ->{});
		
		mp3Player.currentTrack.addListener((o, oldTrack, newTrack) -> {
			 
			Platform.runLater(()->{
			covertrack.setImage(newTrack.getCoverImage());
			title.setText(newTrack.getTitle());
			interpret.setText(newTrack.getIngterpret());
			System.out.println(newTrack.getlength());
			time.setText("Dauer: "+newTrack.getlength());
			countersec = 0.0;
			});
			System.out.println(newTrack + " listener");
			
		});
		
		
		
		
		
	}
	
private Track asTrack(Object o) {
		
		return (Track) o;
	}


public void progressThread() {
	Task<Void> progressTask = new Task<Void>() {
		@Override
		protected Void call() throws Exception {
				while(mp3Player.isPlayingProperty().get() == true) {
					
					Thread.sleep(10);
					 countersec+= .01;
					 Platform.runLater(() ->{
							progressBarSong.setProgress(countersec / mp3Player.currentTrack.get().getlength() );

					 System.out.println(countersec+"sec");
					
					 });
		
			}
				return null;
			
		}
		
	};
	new Thread(progressTask).start();
}

public void playThread () {
		
		Task<Void> playTask = new Task<Void>() {
			protected Void call() throws Exception {
				System.out.println(play + "before");
				if (mp3Player.isPlayingProperty().get() == false) {
					
		
					System.out.println(play + "after");
		
					mp3Player.play();
					System.out.println(mp3Player.isPlaying()+"isPlaying");
					
				}
				
			
				return null;
			}
		};
		
		playTask.setOnSucceeded(e ->{
			
			
			System.out.println("set on succseeded play");
			
			
		});
		
		new Thread(playTask).start();
	}
public void pauseThread () {
	
	Task<Void> playTask = new Task<Void>() {
		protected Void call() throws Exception {
			System.out.println(pause + "before");
			

			
			if(pause == false) {
				
				
				progressThread();
				mp3Player.pause();
				
				pause = true;
				System.out.println(pause + " after pause");
			}else {
				System.out.println("else pause");
				pause = false;
				
				progressThread();
				mp3Player.resume();
				
				System.out.println(pause +" after resume");
			
				System.out.println(pause +"after resume");
			}
			System.out.println(pause +"after");
			return null;
		}
	};
	
	playTask.setOnSucceeded(e ->{
		
		System.out.println("set on succseeded pause");
		
	});
	
	new Thread(playTask).start();
}
public void skipThread () {
	
	Task<Void> skipTask = new Task<Void>() {
		protected Void call() throws Exception {
			
			if (skip == true) {
				
				progressThread();
				mp3Player.stop();
				System.out.println(skip+"skip");
				System.out.println(play+"play skip");
			
				mp3Player.skip();
				
				skip = false;;
			
				
			}else {
				skip = true;;
				
				progressThread();
				mp3Player.stop();
				mp3Player.skipback();
				System.out.println("skipback");
				
			}
			
			return null;
		}
	};
	
	new Thread(skipTask).start();
}
}
