package app.view.dashbordView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Dashboard extends AnchorPane {
	
	DashboardController dashboardController;
	public Dashboard() {
		super();
		try {
			
			FXMLLoader fxl=new FXMLLoader();
			AnchorPane dash=fxl.load(getClass().getResource("Dashboard.fxml").openStream());
			 dashboardController=fxl.getController();
			//aileron=joystickController.aileron;
			//elevators=joystickController.elevators;
			this.getChildren().add(dash);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public DashboardController getDashboardController() {
		return dashboardController;
	}
	public void setDashboardController(DashboardController dashboardController) {
		this.dashboardController = dashboardController;
	}	
	
	
	
	
	
}
