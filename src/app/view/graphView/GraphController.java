package app.view.graphView;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

public class GraphController {
	@FXML private LineChart featureA;
	@FXML private LineChart featureB;
	@FXML private LineChart anomalyDetec;

		
		// Add a public no-args constructor
	    public GraphController() 
	    {
	    }
	     
	    @FXML
	    private void initialize() 
	    {
	    	
	    	
	    }
	    
	    private void drawGraph(String title,float[] data ) {
	    	featureA.setTitle(title);
	    }

}
