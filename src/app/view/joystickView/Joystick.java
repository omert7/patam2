package app.view.joystickView;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.util.Objects;


public class Joystick extends GridPane {

    JoystickController joystickController;
    DoubleProperty throttle;
    DoubleProperty rudder;
    DoubleProperty aileron;
    DoubleProperty elevator;

    public Joystick() {
        // TODO Auto-generated constructor stub
        super();
        try {

            FXMLLoader fxl = new FXMLLoader();
            GridPane joy = fxl.load(Objects.requireNonNull(getClass().getResource("Joystick.fxml")).openStream());
            joystickController = fxl.getController();
            
        
            throttle = new SimpleDoubleProperty(joystickController.getThrottle().getValue());
            rudder = new SimpleDoubleProperty(joystickController.getRudder().getValue());
            aileron = new SimpleDoubleProperty(joystickController.getAileron().getValue());
            elevator = new SimpleDoubleProperty(joystickController.getElevator().getValue());

            joystickController.getThrottle().valueProperty().bind(throttle);
            joystickController.getRudder().valueProperty().bind(rudder);
            joystickController.getAileron().bind(aileron);
            joystickController.getElevator().bind(elevator);

            this.getChildren().add(joy);
            
        } catch (Exception e) {
            e.printStackTrace();

        }
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
}
