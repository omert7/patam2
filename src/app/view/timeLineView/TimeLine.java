package app.view.timeLineView;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class TimeLine extends AnchorPane {
    TimeLineController timeLineController;

    public TimeLine() {
        super();
        try {

            FXMLLoader fxl = new FXMLLoader();
            AnchorPane timeLine = fxl.load(getClass().getResource("TimeLine.fxml").openStream());
            timeLineController = fxl.getController();
            this.getChildren().add(timeLine);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public TimeLineController getTimeLineController() {
        return this.timeLineController;
    }

}
