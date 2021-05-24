package app.view.application;

import app.view.dashbordView.Dashboard;
import app.view.featureListView.FeatureList;
import app.view.graphView.Graph;
import app.view.joystickView.Joystick;
import app.view.menuBarView.MenuBarButton;
import app.view.timeLineView.TimeLine;
import app.viewModel.AppViewModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class GUIController  {
	
    private SimpleDoubleProperty aileron, elevator, rudder, throttle, altitude, airspeed, heading, yaw, roll, pitch;
    private AppViewModel vm;
    @FXML private FeatureList list;
    @FXML private Joystick joystick;
    @FXML private Dashboard dashbord;
    @FXML private  Graph graph;
    @FXML private MenuBarButton menuButton;
    @FXML private TimeLine timeLine;
    
    public AppViewModel getVm() {
        return vm;
    }

    public void setVm(AppViewModel vm) {
        this.vm = vm;
    }

    public GUIController(){
        this.aileron = new SimpleDoubleProperty();
        this.elevator = new SimpleDoubleProperty();
        this.throttle = new SimpleDoubleProperty();
        this.rudder = new SimpleDoubleProperty();
        this.airspeed = new SimpleDoubleProperty();
        this.heading = new SimpleDoubleProperty();
        this.yaw = new SimpleDoubleProperty();
        this.pitch = new SimpleDoubleProperty();
        this.roll = new SimpleDoubleProperty();
        this.altitude = new SimpleDoubleProperty();

    }

    public void init(AppViewModel vm){
        this.vm = vm;
        this.vm.throttleProperty().bind(this.throttle);
        this.vm.aileronProperty().bind(this.aileron);
        this.vm.elevatorProperty().bind(this.elevator);
        this.vm.rudderProperty().bind(this.rudder);
        this.vm.airspeedProperty().bind(this.airspeed);
        this.vm.altitudeProperty().bind(this.altitude);
        this.vm.rollProperty().bind(this.roll);
        this.vm.pitchProperty().bind(this.pitch);
        this.vm.yawProperty().bind(this.yaw);
        this.vm.headingProperty().bind(this.heading);
    }

    public double getAileron() {
        return aileron.get();
    }

    public SimpleDoubleProperty aileronProperty() {
        return aileron;
    }

    public void setAileron(double aileron) {
        this.aileron.set(aileron);
    }

    public double getElevator() {
        return elevator.get();
    }

    public SimpleDoubleProperty elevatorProperty() {
        return elevator;
    }

    public void setElevator(double elevator) {
        this.elevator.set(elevator);
    }

    public double getRudder() {
        return rudder.get();
    }

    public SimpleDoubleProperty rudderProperty() {
        return rudder;
    }

    public void setRudder(double rudder) {
        this.rudder.set(rudder);
    }

    public double getThrottle() {
        return throttle.get();
    }

    public SimpleDoubleProperty throttleProperty() {
        return throttle;
    }

    public void setThrottle(double throttle) {
        this.throttle.set(throttle);
    }

    public double getAltitude() {
        return altitude.get();
    }

    public SimpleDoubleProperty altitudeProperty() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude.set(altitude);
    }

    public double getAirspeed() {
        return airspeed.get();
    }

    public SimpleDoubleProperty airspeedProperty() {
        return airspeed;
    }

    public void setAirspeed(double airspeed) {
        this.airspeed.set(airspeed);
    }

    public double getHeading() {
        return heading.get();
    }

    public SimpleDoubleProperty headingProperty() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading.set(heading);
    }

    public double getYaw() {
        return yaw.get();
    }

    public SimpleDoubleProperty yawProperty() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw.set(yaw);
    }

    public double getRoll() {
        return roll.get();
    }

    public SimpleDoubleProperty rollProperty() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll.set(roll);
    }

    public double getPitch() {
        return pitch.get();
    }

    public SimpleDoubleProperty pitchProperty() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch.set(pitch);
    }
}
