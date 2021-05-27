package app.view.featureListView;

import app.view.menuBarView.MenuBarController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;


public class FeatureList extends AnchorPane {
	private ListProperty<String> listViewP;
	private FeatureListController feaListController;
public FeatureList() {
	super();
	try {
		
		FXMLLoader fxl=new FXMLLoader();
		AnchorPane fList=fxl.load(getClass().getResource("FeatureList.fxml").openStream());
		FeatureListController featureController=fxl.getController();
		
		listViewP=new SimpleListProperty<>();
		featureController.getListViewP().bind(listViewP);
		
		this.getChildren().add(fList);
	}
	catch(Exception e){
		e.printStackTrace();
		
	}
}

public ListProperty<String> getListViewP() {
	return listViewP;
}
public void setListViewP(ListProperty<String> listViewP) {
	this.listViewP = listViewP;
}
public FeatureListController getFeaListController() {
	return feaListController;
}
public void setFeaListController(FeatureListController feaListController) {
	this.feaListController = feaListController;
}	

	

}
