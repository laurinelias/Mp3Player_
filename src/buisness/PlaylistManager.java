package buisness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class PlaylistManager {
	
	/**
	 * Klasee um die Playlists zu verwalten/erstellen und einzulesen von der Textdatei 
	 */
	
	Playlist playList;

	public PlaylistManager() {
		initialize();
	}
	
	private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
	
	public ArrayList<Playlist> getPlaylist(){
		return playlists;
	}
	
	public Playlist getPlaylist(String name) {
		for(Playlist p1 : playlists) {
			if(p1.getName() == name) {
				return p1;
			}
		}
		return null;
	}
	
	public String getPlaylistName() {
		return playList.getName();
	}
	
	public Playlist getAllSongs() {
		Playlist allSongs = new Playlist("Alle Songs",playlists.size()-1);
		
		for(Playlist p1 : playlists) {
			for(Track t : p1.songs) {
				allSongs.addTrack(t);
			}
		}
		System.out.println(allSongs.getSongs());
		return allSongs;
	}
	
	public void initialize() {


//		addPlaylistbyFile("songs/songs.m3u", "Playlist2");
		addPlaylistbyFile("music/songs.m3u", "Playlist1");
	}
	
	private void addPlaylistbyFile(String path, String playlistName) {
		
		BufferedReader reader = null;
		String line;
		Playlist p1 = new Playlist(playlistName, playlists.size());
		p1.setName(playlistName);

		
		try {
			reader = new BufferedReader(new FileReader(path));
			int idCounter = 0;
			while((line = reader.readLine()) != null) {
				
				Mp3File file = new Mp3File(line);
				
				if(file.hasId3v2Tag()) {
					p1.addTrack(new Track(line, file.getId3v2Tag(), idCounter, file));
				} else {
					System.out.println("No valid track found on this path : " + line);
				}
				
				idCounter++;
			}
		} catch ( UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		
		playlists.add(p1);
		
	}
}
