package app.view.timeLineView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class TimeLineController {
	@FXML private Slider time;
	@FXML private Text timeStemp;
	@FXML private Button back;
	@FXML private Button stop;
	@FXML private Button play;
	@FXML private Button pause;
	@FXML private Button next;
	@FXML private ComboBox<String> speed;
	
	// Add a public no-args constructor
    public TimeLineController() 
    {
    }
     
    @FXML
    private void initialize() 
    {
    	timeStemp.setText("00:00:00");
    	ObservableList<String> options = 
    		    FXCollections.observableArrayList(
    		        "x0.5",
    		        "x1",
    		        "x1.5",
    		        "x2"
    		    );
    	speed.getItems().addAll(options);
    	speed.getSelectionModel().select(1);
    	
    }
}
