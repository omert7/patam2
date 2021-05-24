package app.viewModel;

import app.model.AppModel;
import app.model.algorithms.TimeSeries;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Observable;
import java.util.Observer;

public class AppViewModel implements Observer {
    private AppModel appModel;
    private SimpleDoubleProperty aileron, elevator, rudder, throttle, airspeed, heading;
    private SimpleStringProperty altitude, yaw, roll, pitch;
    private DoubleProperty timeStamp;

    public AppViewModel(AppModel am) {
        this.aileron = new SimpleDoubleProperty();
        this.elevator = new SimpleDoubleProperty();
        this.throttle = new SimpleDoubleProperty();
        this.rudder = new SimpleDoubleProperty();
        this.airspeed = new SimpleDoubleProperty();
        this.heading = new SimpleDoubleProperty();
        this.yaw = new SimpleStringProperty();
        this.pitch = new SimpleStringProperty();
        this.roll = new SimpleStringProperty();
        this.altitude = new SimpleStringProperty();
        this.timeStamp = new SimpleDoubleProperty();
        this.appModel = am;
        am.addObserver(this);
    }

    public void setTimeSeries(String timeSeries) {
        this.appModel.setTimeSeries(new TimeSeries(timeSeries));
    }

    public void loadSettings(String settingsFile) {
        appModel.loadSettings(settingsFile);
    }

    public void play(int start, int rate) {
    }


    @Override
    public void update(Observable o, Object arg) {

    }

    public AppModel getAppModel() {
        return appModel;
    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
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

    public String getAltitude() {
        return altitude.get();
    }

    public SimpleStringProperty altitudeProperty() {
        return altitude;
    }

    public void setAltitude(String altitude) {
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

    public Double getHeading() {
        return heading.get();
    }

    public SimpleDoubleProperty headingProperty() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.heading.set(heading);
    }

    public String getYaw() {
        return yaw.get();
    }

    public SimpleStringProperty yawProperty() {
        return yaw;
    }

    public void setYaw(String yaw) {
        this.yaw.set(yaw);
    }

    public String getRoll() {
        return roll.get();
    }

    public SimpleStringProperty rollProperty() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll.set(roll);
    }

    public String getPitch() {
        return pitch.get();
    }

    public SimpleStringProperty pitchProperty() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch.set(pitch);
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
