package app.view.featureListView;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class FeatureListController {
	@FXML private ListView<String> listView;
	private ListProperty<String> listViewP;
	// Add a public no-args constructor
    public FeatureListController() 
    {
    }
     
    @FXML
    private void initialize() 
    {
    	
    	listView.getItems().clear();
    	listViewP = new SimpleListProperty<>();
    	
    }

	public ListProperty<String> getListViewP() {
		return listViewP;
	}

	public void setListViewP(ListProperty<String> listViewP) {
		this.listViewP = listViewP;
	}
    
    
    
}
