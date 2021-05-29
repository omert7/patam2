package app.view.joystickView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class JoystickController implements Initializable {

    @FXML
    private Slider throttle;// zir y
    @FXML
    private Slider rudder;//zir x
    @FXML
    private Canvas joy;

    private DoubleProperty aileron, elevator;
    private DoubleProperty minThrottle, maxThrottle, minRudder, maxRudder;
    private DoubleProperty minElevator, maxElevator, minAileron, maxAileron;
    private DoubleProperty centerCircle;

    public JoystickController() {
        //do not write here!!!
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //get the setting value and initialize
        minThrottle = new SimpleDoubleProperty(-1);
        maxThrottle = new SimpleDoubleProperty(1);
        minRudder = new SimpleDoubleProperty(-1);
        maxRudder = new SimpleDoubleProperty(1);
        centerCircle = new SimpleDoubleProperty(joy.getHeight() / 2);
        minElevator = new SimpleDoubleProperty(0);
        maxElevator = new SimpleDoubleProperty(joy.getHeight());
        minAileron = new SimpleDoubleProperty(0);
        maxAileron = new SimpleDoubleProperty(joy.getWidth());

        this.throttle.setMajorTickUnit(1000);
        this.rudder.setMajorTickUnit(1000);

        minRudder.addListener(v -> {
            this.rudder.setMin(this.minRudder.getValue());
        });

        maxThrottle.addListener(v -> {
            this.throttle.setMax(this.maxThrottle.getValue());
        });
        maxRudder.addListener(v -> {
            this.rudder.setMax(this.maxRudder.getValue());
        });
        minRudder.addListener(v -> {
            this.rudder.setMin(this.minRudder.getValue());
        });

        //end
        this.throttle.setMin(minThrottle.getValue());
        this.throttle.setMax(maxThrottle.getValue());
        this.rudder.setMin(minRudder.getValue());
        this.rudder.setMax(maxRudder.getValue());

        this.throttle.setValue((maxThrottle.getValue() - minThrottle.getValue()) / 2);
        this.rudder.setValue((maxRudder.getValue() - minRudder.getValue()) / 2);
        this.aileron = new SimpleDoubleProperty();
        this.aileron.set(centerCircle.getValue());
        this.elevator = new SimpleDoubleProperty();
        this.elevator.set(centerCircle.getValue());

        this.elevator.addListener(v -> paint());
        this.aileron.addListener(v -> paint());


    }

    public void paint() {
        System.out.println("paint: " + this.aileron.getValue() + "  " + this.elevator.getValue());
        GraphicsContext gc = joy.getGraphicsContext2D();
        gc.clearRect(0, 0, joy.getWidth(), joy.getHeight());
        gc.strokeRect(0, 0, joy.getWidth(), joy.getHeight());
        gc.strokeOval(this.getAli() - 30, this.getEle() - 30, 60, 60);
    }

    public double getEle() {
        //get the value noramllaiz after get the canvas size
        double len = Math.abs(maxElevator.getValue() - minElevator.getValue());
        double temp = Math.abs(this.elevator.getValue() - minElevator.getValue());
        return (temp / len) * (joy.getHeight());
    }


    public double getAli() {
        //get the value noramllaiz after get the canvas size
        double len = Math.abs(maxAileron.getValue() - minAileron.getValue());
        double temp = Math.abs(this.aileron.getValue() - minAileron.getValue());
        return (temp / len) * (joy.getHeight());

    }

    public Slider getThrottle() {
        return throttle;
    }

    public void setThrottle(Slider throttle) {
        this.throttle = throttle;
    }

    public Slider getRudder() {
        return rudder;
    }

    public void setRudder(Slider rader) {
        this.rudder = rader;
    }

    public Canvas getJoy() {
        return joy;
    }

    public void setJoy(Canvas joy) {
        this.joy = joy;
    }

    public DoubleProperty getAileron() {
        return aileron;
    }

    public void setAileron(DoubleProperty dPali) {
        aileron = dPali;

    }

    public DoubleProperty getElevator() {
        return elevator;
    }

    public void setElevator(DoubleProperty dPele) {
        elevator = dPele;

    }

    public DoubleProperty getMinThrottle() {
        return minThrottle;
    }

    public void setMinThrottle(DoubleProperty minThrottle) {
        this.minThrottle = minThrottle;
    }

    public DoubleProperty getMaxThrottle() {
        return maxThrottle;
    }

    public void setMaxThrottle(DoubleProperty maxThrottle) {
        this.maxThrottle = maxThrottle;
    }

    public DoubleProperty getMinRudder() {
        return minRudder;
    }

    public void setMinRudder(DoubleProperty minRudder) {
        this.minRudder = minRudder;
    }

    public DoubleProperty getMaxRudder() {
        return maxRudder;
    }

    public void setMaxRudder(DoubleProperty maxRudder) {
        this.maxRudder = maxRudder;
    }

    public DoubleProperty getMinElevator() {
        return minElevator;
    }

    public void setMinElevator(DoubleProperty minElevator) {
        this.minElevator = minElevator;
    }

    public DoubleProperty getMaxElevator() {
        return maxElevator;
    }

    public void setMaxElevator(DoubleProperty maxElevator) {
        this.maxElevator = maxElevator;
    }

    public DoubleProperty getMinAileron() {
        return minAileron;
    }

    public void setMinAileron(DoubleProperty minAileron) {
        this.minAileron = minAileron;
    }

    public DoubleProperty getMaxAileron() {
        return maxAileron;
    }

    public void setMaxAileron(DoubleProperty maxAileron) {
        this.maxAileron = maxAileron;
    }


    public DoubleProperty minThrottleProperty() {
        return minThrottle;
    }

    public void setMinTrot(double minTrot) {
        this.minThrottle.set(minTrot);
    }

    public DoubleProperty maxThrottleProperty() {
        return maxThrottle;
    }

    public void setMaxTrot(double maxTrot) {
        this.maxThrottle.set(maxTrot);
    }

    public DoubleProperty minRudderProperty() {
        return minRudder;
    }

    public void setMinRad(double minRad) {
        this.minRudder.set(minRad);
    }

    public DoubleProperty maxRudderProperty() {
        return maxRudder;
    }

    public void setMaxRad(double maxRad) {
        this.maxRudder.set(maxRad);
    }

    public DoubleProperty minElevatorProperty() {
        return minElevator;
    }

    public void setMinele(double minele) {
        this.minElevator.set(minele);
    }

    public DoubleProperty maxElevatorProperty() {
        return maxElevator;
    }

    public void setMaxele(double maxele) {
        this.maxElevator.set(maxele);
    }

    public DoubleProperty minAileronProperty() {
        return minAileron;
    }

    public void setMinali(double minali) {
        this.minAileron.set(minali);
    }

    public DoubleProperty maxAileronProperty() {
        return maxAileron;
    }

    public void setMaxali(double maxali) {
        this.maxAileron.set(maxali);
    }

    public double getCenterCircle() {
        return centerCircle.get();
    }

    public DoubleProperty centerCircleProperty() {
        return centerCircle;
    }

    public void setCenterCircle(double centerCircle) {
        this.centerCircle.set(centerCircle);
    }
}
