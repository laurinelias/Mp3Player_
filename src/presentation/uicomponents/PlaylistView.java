package presentation.uicomponents;

import java.io.File;

import buisness.Mp3Player;
import buisness.Playlist;
import buisness.Track;
import buisness.PlaylistManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PlaylistView extends VBox{

	
	Mp3Player mp3Player;
	PlaylistManager playlistManager;
	Playlist playlist;
	VBox rootplaylist;
	HBox menuBar;
		public Button home;
		public Button playlistbtn;
		public Button search;
		
	HBox playlistview;
	VBox playlistoneView;
		public ListView<Track> playlistone;
		public ListCell<Track> trackCell;
		public Label currentlyPlaying;
		public Label pl;
		public HBox song;
		public Button song1;
		public Button song2;
			public Label songInfo;
			public Button playbtn;
			Label a;
			Label b;
		VBox playlisttwo;
			Label songInfo1;
			Label c;
			Label d;
			
	HBox controlls;
		public Button repeatButton;
		public Button skipbackButton;
		public Button playButton;
		public Button pauseButton;
		public Button skipButton;
		public Button shuffleButton;
		
	
			/**
			 * Erstellt alle Controllelemente für den PlaylistView
			 * @param playlistoneView 
			 */
public PlaylistView() {
	
	
	rootplaylist = new VBox();
	
	menuBar = new HBox();
	menuBar.getStyleClass().add("menubarplaylist");
	menuBar.setSpacing(235);
	home = new Button("HOME");
	home.getStyleClass().add("homebtnplaylist");
	playlistbtn = new Button("PLAYLIST");
	playlistbtn.getStyleClass().add("playlistbtnplaylist");
	search = new Button("SEARCH");
	search.getStyleClass().add("searchtnplaylist");
	
	menuBar.getChildren().addAll(home, playlistbtn, search);
	
	playlistview = new HBox();
	playlistview.setId("playlistview");
	playlistview.setSpacing(100);
	
	playlistoneView = new VBox();
	playlistone = new ListView<Track>();
	
	playlistone.setPrefWidth(305);
	playlistone.setPrefHeight(182);

	pl = new Label();
	pl.setText("Playlist1:");
	trackCell = new ListCell<Track>();
	currentlyPlaying = new Label();
	
	playlistoneView.getChildren().addAll(pl,playlistone);
	
	
	song1 = new Button();
	song1.setText("01 Bring mich nach Hause");
	song1.setStyle("-fx-background-color: white");
	song1.setMinWidth(400);
	song2 = new Button();
	song2.setText("02 Drei Worte");
	song2.setStyle("-fx-background-color: white");
	song2.setMinWidth(400);
	song = new HBox();
	playbtn = new Button("play");
	playbtn.getStyleClass().add("playbtn_plv");

	
	
	playlisttwo = new VBox();
	Label f = new Label("PlaylistName");
	songInfo1 = new Label("Titel, Interpret, Album");
	songInfo1.getStyleClass().add("labelplayllist");
	c = new Label("Titel, Interpret, Album");
	c.getStyleClass().add("labelplayllist");
	d = new Label("Titel, Interpret, Album");
	d.getStyleClass().add("labelplayllist");
	playlisttwo.getChildren().addAll(f,songInfo1, c, d);
	
	playlistview.getChildren().addAll(playlistoneView, playlisttwo);
	
	
	getChildren().addAll(menuBar, playlistview, currentlyPlaying);
	
	
	
}

public void add(String name, int id) {
	playlistview.getChildren().add(playlistone);
	
}
public String getPlaylistName() {
	System.out.println(playlist.getName());
	return playlist.getName();
}


}
