package app.viewModel;

import app.model.AppModel;
import app.model.algorithms.TimeSeries;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.Observable;
import java.util.Observer;

public class AppViewModel implements Observer {
    private AppModel appModel;
    private SimpleDoubleProperty aileron, elevator, rudder, throttle, altitude, airspeed, heading, yaw, roll, pitch;
    private TimeSeries timeSeries;
    private double timeStamp;

    public AppViewModel(TimeSeries ts) {
        this.timeSeries = ts;
        appModel = new AppModel();
        this.appModel.setTimeSeries(ts);
    }

    public AppViewModel(AppModel am) {
        this.appModel = am;
        am.addObserver(this);
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

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }

    public double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }

}
