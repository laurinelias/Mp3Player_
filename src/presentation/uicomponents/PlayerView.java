package presentation.uicomponents;

import java.io.File;

import buisness.Playlist;
import buisness.Track;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerView extends VBox{
VBox root;
	HBox menuBar;
		public Button homeButton;
		public Button playlistButton;
		public Button searchButton;
	
	public HBox songInfos;
		public Slider sliderVolume;
		public ImageView coverImage;
		Cover cover;
		public ImageView covertrack;
		VBox labels;
		public Label title;
		public Label interpret;
		public Label album;
	
	public Label time;
	public ProgressBar progressBarSong;
	
	HBox controlls;
		public Button repeatButton;
		public Button skipbackButton;
		public Button playButton;
		public Button pauseButton;
		public Button skipButton;
		public Button shuffleButton;
	
	

	public PlayerView() {
		
		/**
		 * Erstellt im Konstruktor alle Controllelemente die ich später verwende
		 */
		
		root = new VBox();

		menuBar = new HBox();
		menuBar.getStyleClass().add("menubar");
		homeButton = new Button("HOME");
		homeButton.getStyleClass().add("homebtn");
		Region region1 = new Region();
		playlistButton = new Button("PLAYLIST");
		playlistButton.getStyleClass().add("playlistbtn");
		Region region2 = new Region();
		searchButton = new Button("SEARCH");
		searchButton.getStyleClass().add("searchbtn");
		
		menuBar.getChildren().addAll(homeButton, region1, playlistButton, region2, searchButton);
		HBox.setHgrow(region1, Priority.ALWAYS);
		HBox.setHgrow(region2, Priority.ALWAYS);
		
		songInfos = new HBox();
		songInfos.getStyleClass().add("songinfos");
		sliderVolume = new Slider();
		sliderVolume.setOrientation(Orientation.VERTICAL);
		sliderVolume.setValue(100);
		sliderVolume.setPrefHeight(200);
		sliderVolume.getStyleClass().add("slidervolume");
		
		Region region3 = new Region();
		
		covertrack = new ImageView();
		Image c = new Image("file:x.png");
		covertrack.setId("image");
		covertrack.setImage(c);
		covertrack.maxWidth(c.getWidth());
		covertrack.fitHeightProperty().bind(this.heightProperty().divide(2).subtract(60));
		covertrack.setFitWidth(300);
		covertrack.setPreserveRatio(true);
		
		


		Region region4 = new Region();
		
		labels = new VBox();
		labels.setId("labels");
		title = new Label("title");
		title.getStyleClass().add("titel");
		interpret = new Label("interpret");
		interpret.getStyleClass().add("interpret");
		album = new Label("album");
		album.getStyleClass().add("album");
		labels.getChildren().addAll(title, interpret, album);
		
		songInfos.getChildren().addAll(sliderVolume,region3, covertrack, region4, labels);
		HBox.setHgrow(region3, Priority.ALWAYS);
		HBox.setHgrow(region4, Priority.ALWAYS);
		
		HBox pbar = new HBox();
		pbar.getStyleClass().add("pbar");
		progressBarSong = new ProgressBar();
		progressBarSong.setMaxWidth(300);
		progressBarSong.setPrefWidth(600);
		progressBarSong.setId("progressbar");
		
		Region region5 = new Region();
		time = new Label();
		
		time.getStyleClass().add("timelabel");
		pbar.getChildren().addAll(progressBarSong, region5, time);
		HBox.setHgrow(region5, Priority.ALWAYS);
		
			
		
		controlls = new HBox();
		controlls.setId("controlls");
		Region region11 = new Region();
		controlls.setSpacing(20);
	
		File imgrepeatFile = new File("exchange.png");
		ImageView imagerepeat = new ImageView(imgrepeatFile.toURI().toString());
		imagerepeat.setFitHeight(20);
		imagerepeat.setFitWidth(20);
		repeatButton = new Button("",imagerepeat);
		repeatButton.getStyleClass().add("repeatbtn");
		Region region6 = new Region();
		File imgskipbackFile = new File("skip-button.png");
		ImageView imageskipback = new ImageView(imgskipbackFile.toURI().toString());
		imageskipback.setFitHeight(20);
		imageskipback.setFitWidth(20);
		skipbackButton = new Button("",imageskipback);
		skipbackButton.getStyleClass().add("skipbackbtn");
		Region region7 = new Region();
		File imgplayFile = new File("play-button.png");
		ImageView imageplay = new ImageView(imgplayFile.toURI().toString());
		imageplay.setFitHeight(20);
		imageplay.setFitWidth(20);
		playButton = new Button("", imageplay);
		playButton.getStyleClass().add("playbtn");
		Region region8 = new Region();
		File imgpauseFile = new File("pause.png");
		ImageView imagepause = new ImageView(imgpauseFile.toURI().toString());
		imagepause.setFitHeight(20);
		imagepause.setFitWidth(20);
		pauseButton = new Button("", imagepause);
		pauseButton.getStyleClass().add("pausebtn");
		Region region9 = new Region();
		File imgskipFile = new File("skip-button.png");
		ImageView imageskip = new ImageView(imgskipFile.toURI().toString());
		imageskip.setFitHeight(20);
		imageskip.setFitWidth(20);
		skipButton = new Button("",imageskip);
		skipButton.getStyleClass().add("skipbtn");
		Region region10 = new Region();
		File imgshuffleFile = new File("shuffle.png");
		ImageView imageshuffle = new ImageView(imgshuffleFile.toURI().toString());
		imageshuffle.setFitHeight(20);
		imageshuffle.setFitWidth(20);
		shuffleButton = new Button("", imageshuffle);
		shuffleButton.getStyleClass().add("shufflebtn");
		Region region12 = new Region();
		
		controlls.getChildren().addAll(region11,repeatButton,region6, skipbackButton,region7, playButton,region8, pauseButton,region9, skipButton,region10, shuffleButton,region12);
		
		VBox.setVgrow(covertrack, Priority.ALWAYS);
		HBox.setHgrow(region11, Priority.ALWAYS);
		HBox.setHgrow(region6, Priority.ALWAYS);
		HBox.setHgrow(region7, Priority.ALWAYS);
		HBox.setHgrow(region8, Priority.ALWAYS);
		HBox.setHgrow(region9, Priority.ALWAYS);
		HBox.setHgrow(region10, Priority.ALWAYS);
		HBox.setHgrow(region12, Priority.ALWAYS);
		
		Region r1 = new Region();
		Region r2 = new Region();
		Region r3 = new Region();
		VBox.setVgrow(r1, Priority.ALWAYS);
		VBox.setVgrow(r2, Priority.ALWAYS);
		VBox.setVgrow(r3, Priority.ALWAYS);
		
		getChildren().add(menuBar);
		
		getChildren().add(songInfos);
		
		getChildren().add(pbar);
		
		getChildren().add(controlls);
		
	
		
	}
	

	

}

