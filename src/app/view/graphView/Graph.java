package app.view.graphView;

import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;



public class Graph extends AnchorPane {
	
	public Graph() {
		super();
		try {
			
			FXMLLoader fxl=new FXMLLoader();
			AnchorPane graph=fxl.load(getClass().getResource("Graph.fxml").openStream());
			GraphController graphController=fxl.getController();
			//aileron=joystickController.aileron;
			//elevators=joystickController.elevators;
			this.getChildren().add(graph);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	

}
