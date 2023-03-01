package buisness;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
	

	ArrayList<Track> songs = new ArrayList<>();
	
	
	String name;
	int id;
	
	

	
	public Playlist(String name, int id) {
		
		this.name = name;
		this.id = id;
		

		

	}
	
	public Playlist() {
		
	}
	
	
	public int getId() {
		return id;
	}



	
	public ArrayList<Track> getSongs() {
		return songs;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		System.out.println(name);
		return name;
	}
	

	


	public void addTrack(Track t) {
		songs.add(t);
		
	}
	
	public String toString() {
		return name;
		
	}
}
