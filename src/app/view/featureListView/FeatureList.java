package app.view.featureListView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;


public class FeatureList extends AnchorPane {
	
public FeatureList() {
	super();
	try {
		
		FXMLLoader fxl=new FXMLLoader();
		AnchorPane fList=fxl.load(getClass().getResource("FeatureList.fxml").openStream());
		FeatureListController featureController=fxl.getController();
		//aileron=joystickController.aileron;
		//elevators=joystickController.elevators;
		this.getChildren().add(fList);
	}
	catch(Exception e){
		e.printStackTrace();
		
	}
}	

	

}
