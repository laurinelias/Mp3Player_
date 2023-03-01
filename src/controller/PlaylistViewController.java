package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import application.MainApp;
import buisness.Mp3Player;

import buisness.PlaylistManager;
import buisness.Track;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import presentation.uicomponents.PlaylistView;

import presentation.uicomponents.Scenes;
import presentation.uicomponents.TrackListViewCell;
import presentation.uicomponents.ViewController;

public class PlaylistViewController extends ViewController<MainApp>{
	
	/**
	 * Controller für die Playlistview
	 */
	
	private PlayerViewController pvc;
	
	private TrackListViewCell tlvc;
	
	private Mp3Player mp3Player;
	Button homeButton;
	Button playlistButton;
	Button searchButton;
	VBox playlistone1;
	ListView<Track> playlistone;
	ListCell<Track> trackCell;
	Label pl;
	Label playing;
	Button playbtn;
	
	Button song1;
	Button song2;
	
	boolean play;
	boolean pause;
	boolean skip;
	boolean shufflepressed;
	boolean repeatpressed;
	
	
	Button repeatButton;
	Button skipbackButton;
	Button playButton;
	Button pauseButton;
	Button skipButton;
	Button shuffleButton;
	
	int selectedSong = 0;
	
	/**
	 * Steuert alle Events ders PlaylistViews
	 * @param application
	 * @param mp3player
	 */
	
	public PlaylistViewController(MainApp application, Mp3Player mp3player) {
		super(application);
		
		this.rootView = new PlaylistView();
		pvc = new PlayerViewController(application, mp3player);
		
		PlaylistView view = (PlaylistView) rootView;
		
		homeButton = view.home;
		playlistButton = view.playlistbtn;
		searchButton = view.search;
		
		/*playlistView cell*/
		pl = view.pl;
		playlistone = view.playlistone;
		trackCell = view.trackCell;
		playing = view.currentlyPlaying;
		
		song1 = view.song1;
		song2 = view.song2;

		
		this.mp3Player =  mp3player;
		initialize();
	}
	
	
	
	@Override
	public void initialize() {
		System.out.println(mp3Player.getPlaylistname()+ "t");
		
		
		
		List<Track> trackList = new LinkedList<Track>();
		trackList.addAll(mp3Player.PlaylistOne.get().getSongs());
		
		
		playlistone.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {
			@Override
			public ListCell<Track> call(ListView<Track> param) {
				return new TrackListViewCell();
			}
		});
		
		
		
		playlistone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Track>() {

			@Override
			public void changed(ObservableValue<? extends Track> observable, Track oldTrack, Track newTrack) {
			
				selectedSong = playlistone.getSelectionModel().getSelectedIndex();
				System.out.println(selectedSong);
				
				mp3Player.pause();
				mp3Player.putSong(selectedSong, 0);
				System.out.println("progsec---"+ pvc.countersec);
				
				
				
				
				
				playlistone.refresh();
			}
		});
		
		ObservableList<Track> playlistModel = playlistone.getItems();
		playlistModel.addAll(trackList);
		
		
		pvc.skipButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

			int index = 0;
			@Override
			public void handle(ActionEvent event) {
				if (index < playlistModel.size()) {
					playlistone.getSelectionModel().select(index);
				}
				
				index = (index + 1) % playlistModel.size();
				
			}
			
		});
		
		
		mp3Player.currentTrack.addListener((o,oldTrack, newTrack) ->{
			for(Track t : playlistModel) {
				if (mp3Player.currentTrack.get() == t) {
					System.out.println("#########################");
					trackCell.setStyle("-fx-background-color:red");
				}
			}
			playing.setText("CurrentlyPlaying:" +  mp3Player.currentTrack.get().getTitle());
			
		});
		
		
			
		homeButton.setOnAction(e ->  ((MainApp) application).switchScene(Scenes.PLAYERVIEW));
		
		playlistButton.setOnAction(e -> ((MainApp) application).switchScene(Scenes.PLAYLISTVIEW));
		
		

		
		song1.setOnAction(e ->{
			song2.setStyle("-fx-background-color: white");
			song1.setStyle("-fx-background-color: green");
			mp3Player.putSong(0, 0);
		});
		
		song2.setOnAction(e ->{
			song1.setStyle("-fx-background-color: white");
			song2.setStyle("-fx-background-color: green");
			//mp3Player.minim.dispose();
			mp3Player.putSong(1, 0);
		});

}
}
