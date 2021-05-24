package app.view.joystickView;


import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;


public class Joystick extends GridPane  {
	 
     JoystickController joystickController;
	public Joystick() {
		// TODO Auto-generated constructor stub
		super();
		try {
			
			FXMLLoader fxl=new FXMLLoader();
			GridPane joy=fxl.load(getClass().getResource("Joystick.fxml").openStream());
			 joystickController=fxl.getController();
			this.getChildren().add(joy);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	public JoystickController getJoystickController() {
		return joystickController;
	}
	public void setJoystickController(JoystickController joystickController) {
		this.joystickController = joystickController;
	}
	
}
