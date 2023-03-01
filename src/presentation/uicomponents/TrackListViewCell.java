package presentation.uicomponents;

import buisness.Track;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class TrackListViewCell extends ListCell<Track> {

	private Label titleLabel;
	private Label interpretLabel;
	private ImageView coverImg;
	
	private HBox root;
	
	
	@Override
	public void updateItem(Track track, boolean empty) {
		super.updateItem(track, empty);
		
		if (empty || track == null) {
			setText(null);
			setGraphic(null);
		}else {
			interpretLabel = new Label(track.getIngterpret());
            titleLabel = new Label(track.getTitle());
            coverImg = new ImageView(track.getCoverImage());
            coverImg.setFitHeight(30);
            coverImg.setFitWidth(30);
            new Region();
            new Rectangle(5,5,5,5);

            root = new HBox(coverImg, new VBox(titleLabel, interpretLabel));
            root.setSpacing(10);
            root.setPadding(new Insets(10));
            root.setId("playlistCell");

            setText(null);
            setGraphic(root);
        }
		}
	
	public void addplay(){
		root = new HBox(coverImg, new VBox(titleLabel, interpretLabel), new Rectangle(5,5,5,5));
	}
	}
	