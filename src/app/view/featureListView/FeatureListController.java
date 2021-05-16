package app.view.featureListView;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class FeatureListController {
@FXML private ListView<String> listView;
	
	// Add a public no-args constructor
    public FeatureListController() 
    {
    }
     
    @FXML
    private void initialize() 
    {
    	listView.setAccessibleHelp(null);
    }
}
