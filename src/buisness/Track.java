package buisness;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import javafx.scene.image.Image;

public class Track {

	
	String path;
	String title;
	String interpret;
	int songId;
	BufferedImage coverImg;
	Image covert;
	final String  DEFAULT_IMG = "artworks-000171597968-6ou25w-t500x500.jpg";
	int lengthsec;
	Mp3File mp3;
	
	public Track(String path, ID3v2 data, int id, Mp3File mp3) {
		
		this.path = path;
		this.songId = songId;
		this.title = title;
		this.interpret = interpret;
		this.title = data.getTitle() != null ? data.getTitle() : "Unbekannt";
		this.interpret = data.getArtist() != null ? data.getArtist() : "Unbekannt";	
		this.lengthsec = (int)(mp3.getLengthInSeconds());
		
		try {
			covert = new Image(new ByteArrayInputStream(data.getAlbumImage()));
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		
		
		try {
			this.coverImg = data.getAlbumImage() != null ? toBufferedImage(data.getAlbumImage()) 
					: ImageIO.read(new File(DEFAULT_IMG));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Track() {
		
	}

	private BufferedImage toBufferedImage(byte[] imageData) {
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getPath() {
		return path;
	}

	public int getSongId() {
		return songId;
	}
	public String getTitle() {
		return title;
	}
	public int getlength() {
		return lengthsec;
	}

	public String getIngterpret() {
		return interpret;
	}
	public String toString() {
		return title;
	}
	public Image getCoverImage() {
		return covert;
	}
	public BufferedImage getCover() {
		return coverImg;
	}
	public static void test() {
		System.out.println("test");
	}
	
	public String legthAsString(int sek) {
		return String.format("%02d:%02d", sek/60, sek%60 );
	}
	
}



