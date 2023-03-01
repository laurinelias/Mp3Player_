package presentation.uicomponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Cover extends StackPane{

	//public StackPane playlisticon;
	public VBox icons;
	final String DEFAULT_COVER_PATH = "cover_x.jpg";
	
	public Cover() {
		
		icons = new VBox();
		this.getStyleClass().add("cover");
		setCover(DEFAULT_COVER_PATH);
		icons.getStyleClass().add("icons-cover");
		this.getChildren().add(icons);
	}
	
	public void setCover(String url) {
		try {
			this.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream(url)),null,null, 
					BackgroundPosition.CENTER,null)));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void setCover(Image img){
		this.setBackground(new Background(new BackgroundImage(img, null, null, BackgroundPosition.CENTER, null)));
	}
	
	
}
