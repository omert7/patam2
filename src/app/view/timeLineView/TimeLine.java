package app.view.timeLineView;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class TimeLine extends AnchorPane {
	
	public TimeLine() {
		super();
		try {
			
			FXMLLoader fxl=new FXMLLoader();
			AnchorPane timeLine=fxl.load(getClass().getResource("TimeLine.fxml").openStream());
			TimeLineController timeLineController=fxl.getController();
			this.getChildren().add(timeLine);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
}
