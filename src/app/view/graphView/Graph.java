package app.view.graphView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;



public class Graph extends AnchorPane {
	private StringProperty nameOfFeatureA;
	private StringProperty nameOfFeatureB;

	private GraphController graphController;

	public Graph() {
		super();
		try {

			FXMLLoader fxl=new FXMLLoader();
			AnchorPane graph=fxl.load(getClass().getResource("Graph.fxml").openStream());
			graphController=fxl.getController();
			nameOfFeatureA=new SimpleStringProperty(graphController.getGraphNameOfFeatureA().getValue());
			nameOfFeatureB=new SimpleStringProperty(graphController.getGraphNameOfFeatureB().getValue());
			graphController.getGraphNameOfFeatureA().bindBidirectional(nameOfFeatureA);
			graphController.getGraphNameOfFeatureB().bindBidirectional(nameOfFeatureB);
			this.getChildren().add(graph);
		}
		catch(Exception e){
			e.printStackTrace();

		}
	}

	public StringProperty getNameOfFeatureA() {
		return nameOfFeatureA;
	}

	public void setNameOfFeatureA(StringProperty nameOfFeatureA) {
		this.nameOfFeatureA = nameOfFeatureA;
	}

	public StringProperty getNameOfFeatureB() {
		return nameOfFeatureB;
	}

	public void setNameOfFeatureB(StringProperty nameOfFeatureB) {
		this.nameOfFeatureB = nameOfFeatureB;
	}

	public GraphController getGraphController() {
		return graphController;
	}

	public void setGraphController(GraphController graphController) {
		this.graphController = graphController;
	}





}
