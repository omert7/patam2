package app.viewModel;

import app.model.AppModel;
import app.model.algorithms.TimeSeries;
import app.view.application.GUIController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Observable;
import java.util.Observer;

public class AppViewModel extends Observable implements Observer {
    private AppModel appModel;
    private SimpleDoubleProperty aileron, elevator, rudder, throttle, airspeed, heading;
    private SimpleDoubleProperty altitude, yaw, roll, pitch;
    private DoubleProperty timeStamp;

    public AppViewModel(AppModel am) {
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

    public double getTimeStamp() {
        return timeStamp.get();
    }

    public DoubleProperty timeStampProperty() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
        this.timeStamp.set(timeStamp);
    }

    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


}
