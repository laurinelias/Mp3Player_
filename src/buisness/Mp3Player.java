package buisness;


import java.util.Timer;
import java.util.TimerTask;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

	public class Mp3Player {

		public PlaylistManager playlistmanager;
		public SimpleObjectProperty<Playlist> currentPlaylist;
		public SimpleObjectProperty<Playlist> PlaylistOne;
		public SimpleObjectProperty<Track> currentTrack;
		public SimpleMinim minim;
		public SimpleAudioPlayer audioPlayer;
		public SimpleBooleanProperty isPlaying = new SimpleBooleanProperty(false);
		public SimpleBooleanProperty repeat = new SimpleBooleanProperty(false);
		public SimpleBooleanProperty shuffle = new SimpleBooleanProperty(false);
		public int nextTrack;
		public int prevTrack;
		String playlistName;
		public SimpleIntegerProperty currentTime;
	
		
		int hilfe =0;
	
		
		public Mp3Player() {
			playlistmanager = new PlaylistManager();
			currentPlaylist = new SimpleObjectProperty<Playlist>();
			PlaylistOne = new SimpleObjectProperty<Playlist>();
			PlaylistOne.set(playlistmanager.getPlaylist().get(0));
			currentTrack = new SimpleObjectProperty<Track>();
			currentTime = new SimpleIntegerProperty();
			minim = new SimpleMinim();
			
			System.out.println(playlistmanager.getPlaylist());
			System.out.println(playlistmanager.getAllSongs());

		}
		
		/**
		 * setz den Song um den richtigen Song mit der PlayMethode zu starten
		 * @param songId
		 * @param playlistId
		 */
		
		
		public void putSong(int songId, int playlistId) {
			
			currentPlaylist.set(playlistmanager.getPlaylist().get(playlistId));
			currentTrack.set(playlistmanager.getPlaylist().get(playlistId).getSongs().get(songId));
			minim = new SimpleMinim();
			
			System.out.println(currentTrack.get().getPath());
			audioPlayer = minim.loadMP3File(currentTrack.get().getPath());
			
			
		}
		
		
		/**
		 * Methode um die Playlist erneut abzuspielen
		 */
		public void repeat() {
			if(isRepeatProperty().get() == true) {
				repeat.set(false);
			}else {
				repeat.set(true);
			}
			
			System.out.println("repeat");
		}
		
		public void shuffle() {
			if(isShuffleProperty().get()== true) {
				shuffle.set(false);
			}else {
				shuffle.set(true);
			}
		}
		
		
		/**
		 * Play um die Songs zum ersten mal abzuspielen
		 */
		public void play() {

			if(isPlayingProperty().get() != true) {
				
				isPlaying.set(true);
				System.out.println(isPlaying+"-------");
				if(currentTrack.get()==null) {
					putSong(0,0);
				}
				
				currentTrack.set(getCurrentTrack());
				audioPlayer.play();
				
				hilfe = 1;
		
			}else {
				System.out.println("Obacht");
			}
			
		}
		
		
		/**
		 * Methode um die Lieder zu pausieren
		 * später mit resume weiter zu spielen
		 */
		public void pause() {
			if(isPlaying.get() == true) {
				System.out.println("pause1");
				audioPlayer.pause();
				
			}else{
				System.out.println("else pause mp3");
			
			resume();
			
			}
			isPlaying.set(false);;
		}
		
		public void resume() {
			
			if(hilfe == 0) {
				System.out.println("Press play first");
			}else {
				isPlaying.set(true);
				
				audioPlayer.play();
				System.out.println("resume1");
				
			}
			
		}
		
		public void stop() {
			isPlaying.set(false);
			System.out.println("stop mp3");
			audioPlayer.pause();
		}
		
		public void volume(double value) {
			audioPlayer.setGain((float)(20 * Math.log10(value)));
		}
		
		public Track getCurrentTrack() {
			return currentTrack.get();
		}
		
		
		public int getNextTrack() {
			return currentTrack.get().getSongId() + 1;
		}
		
		
		public Playlist getCurrentPlaylist() {
			return currentPlaylist.get();
		}
		
		public boolean isPlaying() {
			return audioPlayer.isPlaying();
		}
		
		/**
		 * Methoden um in der Playlist den 
		 * nächsten bzw. letzten SOng zu spielen
		 */
		public void skip() {
			int maxPlaylistSongs = currentPlaylist.get().getSongs().size(); 
			nextTrack += currentTrack.get().getSongId() +1;
			System.out.println(nextTrack +"nextTrack");
			
			if(isRepeatProperty().get() == true) {
				if(nextTrack >= maxPlaylistSongs) {
					putSong(0,currentPlaylist.get().getId());
					nextTrack = 0;
					
				}else {
					putSong(nextTrack, currentPlaylist.get().getId());
					
				}
			}else {
				System.out.println("repeat !=true");
				if(nextTrack >= maxPlaylistSongs) {
					System.out.println("Playlist stop");
					

				} else {
					System.out.println("else skip");
					putSong(nextTrack, currentPlaylist.get().getId());
					
				}
			}
			currentTrack.set(currentPlaylist.get().getSongs().get(nextTrack)); 
		}
		
		public void skipback(){
			int maxPlaylistSongs = currentPlaylist.get().getSongs().size(); 
			
			System.out.println(prevTrack+"prevSong--------------");
			System.out.println(maxPlaylistSongs+"maxsongs----------");
			
			if(isRepeatProperty().get() == true){
				
				if(prevTrack <= 0) {
					System.out.println(prevTrack+"if backloop");
					putSong(maxPlaylistSongs-1,currentPlaylist.get().getId());
					prevTrack = maxPlaylistSongs-1;
		
				} else {
					putSong(prevTrack-1, currentPlaylist.get().getId());
					System.out.println(prevTrack+ ":prevtrack else repeat true");
					prevTrack = prevTrack-1;
					System.out.println("----------------");
				}
			}else {
				System.out.println("repeat!= true");
				if(prevTrack < 0) {			
					System.out.println("Playlist stop");
					prevTrack = 0;
				} else {
					putSong(prevTrack, currentPlaylist.get().getId());
					prevTrack = currentTrack.get().getSongId()-1;
				}
			}
			
		}
		
		
		public String getPlaylistname() {
			return currentPlaylist.getName();
			
		}
		public String setPlaylistName(String name) {
			return playlistName = name;
		}
		
		


		public double getSongLenght() {
			return currentTrack.get().getlength();
		}

		public SimpleIntegerProperty currentTime() {
			return currentTime;
		}
		
		public SimpleBooleanProperty isPlayingProperty() {
			return isPlaying;
		}
		
		public SimpleBooleanProperty isRepeatProperty() {
			return repeat;
		}
		
		public SimpleBooleanProperty isShuffleProperty() {
			return shuffle;
		}
		
		public String legthtoString(){
			return null;
			
		}
//		
}
