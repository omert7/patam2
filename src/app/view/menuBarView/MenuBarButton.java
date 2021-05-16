package app.view.menuBarView;	

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class MenuBarButton extends AnchorPane 
{
	public MenuBarButton() {
		super();
		try {
			
			FXMLLoader fxl=new FXMLLoader();
			AnchorPane menuBar=fxl.load(getClass().getResource("MenuBar.fxml").openStream());
			MenuBarController menuBarController=fxl.getController();
			//aileron=joystickController.aileron;
			//elevators=joystickController.elevators;
			this.getChildren().add(menuBar);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
}
