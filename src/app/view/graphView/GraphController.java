package app.view.graphView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;

public class GraphController {
	@FXML private LineChart<Number, Number> featureA;
	@FXML private LineChart<Number, Number> featureB;
	@FXML private LineChart<Number, Number> anomalyDetec;


	@FXML private Label labelFeatureA,labelFeatureB,labelFeatureB1,labelFeatureA1;
	private StringProperty graphNameOfFeatureA;
	private StringProperty graphNameOfFeatureB;

	// Add a public no-args constructor
	public GraphController()
	{
	}

	@FXML
	private void initialize()
	{

		graphNameOfFeatureA=new SimpleStringProperty("");
		graphNameOfFeatureB=new SimpleStringProperty("");

		graphNameOfFeatureA.addListener(v->{

			labelFeatureA.setText(graphNameOfFeatureA.getValue());

		});
		graphNameOfFeatureB.addListener(v->{
			labelFeatureB.setText(graphNameOfFeatureB.getValue());
		});

	}


	public StringProperty getGraphNameOfFeatureA() {
		return graphNameOfFeatureA;
	}

	public void setGraphNameOfFeatureA(StringProperty graphNameOfFeatureA) {
		this.graphNameOfFeatureA = graphNameOfFeatureA;
	}

	public StringProperty getGraphNameOfFeatureB() {
		return graphNameOfFeatureB;
	}

	public void setGraphNameOfFeatureB(StringProperty graphNameOfFeatureB) {
		this.graphNameOfFeatureB = graphNameOfFeatureB;
	}

	public LineChart getFeatureA() {
		return featureA;
	}

	public void setFeatureA(LineChart featureA) {
		this.featureA = featureA;
	}

	public LineChart getFeatureB() {
		return featureB;
	}

	public void setFeatureB(LineChart featureB) {
		this.featureB = featureB;
	}

	public LineChart getAnomalyDetec() {
		return anomalyDetec;
	}

	public void setAnomalyDetec(LineChart anomalyDetec) {
		this.anomalyDetec = anomalyDetec;
	}




}
