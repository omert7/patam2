package app.view.joystickView;


import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;


public class Joystick extends GridPane  {
	 
public DoubleProperty aileron,elevators;

	public Joystick() {
		// TODO Auto-generated constructor stub
		super();
		try {
			
			FXMLLoader fxl=new FXMLLoader();
			GridPane joy=fxl.load(getClass().getResource("Joystick.fxml").openStream());
			JoystickController joystickController=fxl.getController();
			//aileron=joystickController.aileron;
			//elevators=joystickController.elevators;
			this.getChildren().add(joy);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
}
