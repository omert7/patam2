package app.view.timeLineView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class TimeLineController {
    @FXML
    private Slider time;
    @FXML
    private Text timeStamp;
    @FXML
    private Button back;
    @FXML
    private Button stop;
    @FXML
    private Button play;
    @FXML
    private Button pause;
    @FXML
    private Button next;
    @FXML
    private ComboBox<String> speed;

    // Add a public no-args constructor
    public TimeLineController() {
    }

    @FXML
    private void initialize() {
        timeStamp.setText("00:00:00");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "x0.5",
                        "x1",
                        "x1.5",
                        "x2"
                );
        speed.getItems().addAll(options);
        speed.getSelectionModel().select(1);

    }

    public Slider getTime() {
        return time;
    }

    public void setTime(Slider time) {
        this.time = time;
    }

    public Text getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Text timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Button getBack() {
        return back;
    }

    public void setBack(Button back) {
        this.back = back;
    }

    public Button getStop() {
        return stop;
    }

    public void setStop(Button stop) {
        this.stop = stop;
    }

    public Button getPlay() {
        return play;
    }

    public void setPlay(Button play) {
        this.play = play;
    }

    public Button getPause() {
        return pause;
    }

    public void setPause(Button pause) {
        this.pause = pause;
    }

    public Button getNext() {
        return next;
    }

    public void setNext(Button next) {
        this.next = next;
    }

    public ComboBox<String> getSpeed() {
        return speed;
    }

    public void setSpeed(ComboBox<String> speed) {
        this.speed = speed;
    }
}
