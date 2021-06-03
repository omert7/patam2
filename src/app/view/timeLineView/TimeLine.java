package app.view.timeLineView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class TimeLine extends AnchorPane {
    TimeLineController timeLineController;
    private DoubleProperty maxTimeLine;
    private DoubleProperty timeStamp;

    public TimeLine() {
        super();
        try {

            FXMLLoader fxl = new FXMLLoader();
            AnchorPane timeLine = fxl.load(getClass().getResource("TimeLine.fxml").openStream());
            timeLineController = fxl.getController();
            timeStamp = new SimpleDoubleProperty();
            maxTimeLine = new SimpleDoubleProperty(timeLineController.getMaxTimeLine());
            timeLineController.getTime().maxProperty().bind(maxTimeLine);

            maxTimeLine.addListener(v -> {
                timeLineController.getTextTimeStamp().setText(this.createTextTime());
            });
            timeStamp.addListener(v -> {
                timeLineController.getTime().setValue(timeStamp.getValue());
                timeLineController.getTextTimeStamp().setText(this.createTextTime());
            });

            timeLineController.getTime().setOnMouseReleased(event -> {
                System.out.println(timeLineController.getTime().getValue());
            });

            this.getChildren().add(timeLine);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public TimeLineController getTimeLineController() {
        return this.timeLineController;
    }

    public double getMaxTimeLine() {
        return maxTimeLine.get();
    }

    public DoubleProperty maxTimeLineProperty() {
        return maxTimeLine;
    }

    public void setMaxTimeLine(double maxTimeLine) {
        this.maxTimeLine.set(maxTimeLine);
    }

    public static String secondsToTime(double seconds) {
        int hours = (int) (seconds / 3600);
        int minutes = (int) ((seconds % 3600) / 60);
        int secs = (int) (seconds % 60);
        System.out.println(String.format("%02d:%02d:%02d", hours, minutes, secs));
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    public String createTextTime() {
        return secondsToTime(timeStamp.getValue()) + " / " + secondsToTime(maxTimeLine.getValue());
    }

    public double getTimeStamp() {
        return timeStamp.get();
    }

    public DoubleProperty timeStampProperty() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
        this.timeStamp.set(timeStamp);
    }
}
