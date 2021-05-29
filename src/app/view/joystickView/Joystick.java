package app.view.joystickView;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.util.Objects;


public class Joystick extends GridPane {

    JoystickController joystickController;
    private DoubleProperty throttle;
    private DoubleProperty rudder;
    private DoubleProperty aileron;
    private DoubleProperty elevator;
    private DoubleProperty centerCircle;
    private DoubleProperty minThrottle, maxThrottle, minRudder, maxRudder;
    private DoubleProperty minElevator, maxElevator, minAileron, maxAileron;

    public Joystick() {
        // TODO Auto-generated constructor stub
        super();
        try {

            FXMLLoader fxl = new FXMLLoader();
            GridPane joy = fxl.load(Objects.requireNonNull(getClass().getResource("Joystick.fxml")).openStream());
            joystickController = fxl.getController();
            initProperties();
            bindProperties();

            this.getChildren().add(joy);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void bindProperties(){
        joystickController.getThrottle().valueProperty().bind(throttle);
        joystickController.getRudder().valueProperty().bind(rudder);
        joystickController.aileronProperty().bind(aileron);
        joystickController.elevatorProperty().bind(elevator);

        joystickController.centerCircleProperty().bind(centerCircle);

        //min max values
        joystickController.minThrottleProperty().bind(minThrottle);
        joystickController.maxThrottleProperty().bind(maxThrottle);
        joystickController.minRudderProperty().bind(minRudder);
        joystickController.maxRudderProperty().bind(maxRudder);
        joystickController.minElevatorProperty().bind(minElevator);
        joystickController.maxElevatorProperty().bind(maxElevator);
        joystickController.minAileronProperty().bind(minAileron);
        joystickController.maxAileronProperty().bind(maxAileron);
    }

    public void initProperties(){
        throttle = new SimpleDoubleProperty(joystickController.getThrottle().getValue());
        rudder = new SimpleDoubleProperty(joystickController.getRudder().getValue());
        aileron = new SimpleDoubleProperty(joystickController.aileronProperty().getValue());
        elevator = new SimpleDoubleProperty(joystickController.elevatorProperty().getValue());

        centerCircle = new SimpleDoubleProperty(joystickController.centerCircleProperty().getValue());

        minThrottle = new SimpleDoubleProperty(joystickController.minThrottleProperty().getValue());
        maxThrottle = new SimpleDoubleProperty(joystickController.maxThrottleProperty().getValue());
        minRudder = new SimpleDoubleProperty(joystickController.minRudderProperty().getValue());
        maxRudder = new SimpleDoubleProperty(joystickController.maxRudderProperty().getValue());
        minElevator = new SimpleDoubleProperty(joystickController.minElevatorProperty().getValue());
        maxElevator = new SimpleDoubleProperty(joystickController.maxElevatorProperty().getValue());
        minAileron = new SimpleDoubleProperty(joystickController.minAileronProperty().getValue());
        maxAileron = new SimpleDoubleProperty(joystickController.maxAileronProperty().getValue());
    }


    public JoystickController getJoystickController() {
        return joystickController;
    }

    public void setJoystickController(JoystickController joystickController) {
        this.joystickController = joystickController;
    }

    public double getThrottle() {
        return throttle.get();
    }

    public DoubleProperty throttleProperty() {
        return throttle;
    }

    public void setThrottle(double throttle) {
        this.throttle.set(throttle);
    }

    public double getRudder() {
        return rudder.get();
    }

    public DoubleProperty rudderProperty() {
        return rudder;
    }

    public void setRudder(double rudder) {
        this.rudder.set(rudder);
    }

    public double getAileron() {
        return aileron.get();
    }

    public DoubleProperty aileronProperty() {
        return aileron;
    }

    public void setAileron(double aileron) {
        this.aileron.set(aileron);
    }

    public double getElevator() {
        return elevator.get();
    }

    public DoubleProperty elevatorProperty() {
        return elevator;
    }

    public void setElevator(double elevator) {
        this.elevator.set(elevator);
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

    public double getMinThrottle() {
        return minThrottle.get();
    }

    public DoubleProperty minThrottleProperty() {
        return minThrottle;
    }

    public void setMinThrottle(double minThrottle) {
        this.minThrottle.set(minThrottle);
    }

    public double getMaxThrottle() {
        return maxThrottle.get();
    }

    public DoubleProperty maxThrottleProperty() {
        return maxThrottle;
    }

    public void setMaxThrottle(double maxThrottle) {
        this.maxThrottle.set(maxThrottle);
    }

    public double getMinRudder() {
        return minRudder.get();
    }

    public DoubleProperty minRudderProperty() {
        return minRudder;
    }

    public void setMinRudder(double minRudder) {
        this.minRudder.set(minRudder);
    }

    public double getMaxRudder() {
        return maxRudder.get();
    }

    public DoubleProperty maxRudderProperty() {
        return maxRudder;
    }

    public void setMaxRudder(double maxRudder) {
        this.maxRudder.set(maxRudder);
    }

    public double getMinElevator() {
        return minElevator.get();
    }

    public DoubleProperty minElevatorProperty() {
        return minElevator;
    }

    public void setMinElevator(double minElevator) {
        this.minElevator.set(minElevator);
    }

    public double getMaxElevator() {
        return maxElevator.get();
    }

    public DoubleProperty maxElevatorProperty() {
        return maxElevator;
    }

    public void setMaxElevator(double maxElevator) {
        this.maxElevator.set(maxElevator);
    }

    public double getMinAileron() {
        return minAileron.get();
    }

    public DoubleProperty minAileronProperty() {
        return minAileron;
    }

    public void setMinAileron(double minAileron) {
        this.minAileron.set(minAileron);
    }

    public double getMaxAileron() {
        return maxAileron.get();
    }

    public DoubleProperty maxAileronProperty() {
        return maxAileron;
    }

    public void setMaxAileron(double maxAileron) {
        this.maxAileron.set(maxAileron);
    }
}
