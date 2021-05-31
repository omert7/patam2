package app.model;


import app.model.algorithms.TimeSeries;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

import java.util.Observable;

public class AppModel extends Observable {
    private FlightSettings flightSettings;
    private TimeSeries timeSeries;
    private SimulatorPlayer sp;
    private FloatProperty timestamp;
    private int aileronIndex,throttleIndex,rudderIndex,elevatorIndex,
            yawIndex,pitchIndex,headingIndex,altitudeIndex,airspeedIndex,rollIndex;



    public AppModel() {
        this.timestamp = new SimpleFloatProperty();
        this.sp = new SimulatorPlayer();
    }

    public boolean isReady() {
        return (timeSeries != null && flightSettings != null);
    }

    public FlightSettings getFlightSettings() {
        return flightSettings;
    }

    public void setFlightSettings(FlightSettings flightSettings) {
        this.flightSettings = flightSettings;
        this.sp.setFlightSettings(flightSettings);
        loadIndexes();
        this.timestamp.bindBidirectional(sp.timeStampProperty());
    }

    private void loadIndexes(){
        this.aileronIndex = this.flightSettings.getFlightFeatureHashMap().get("aileron").getFeatureIndex();
        this.throttleIndex = this.flightSettings.getFlightFeatureHashMap().get("throttle").getFeatureIndex();
        this.rudderIndex =this.flightSettings.getFlightFeatureHashMap().get("rudder").getFeatureIndex();
        this.elevatorIndex = this.flightSettings.getFlightFeatureHashMap().get("elevator").getFeatureIndex();
        this.yawIndex = this.flightSettings.getFlightFeatureHashMap().get("yaw").getFeatureIndex();
        this.pitchIndex = this.flightSettings.getFlightFeatureHashMap().get("pitch").getFeatureIndex();
        this.headingIndex = this.flightSettings.getFlightFeatureHashMap().get("heading").getFeatureIndex();
        this.altitudeIndex = this.flightSettings.getFlightFeatureHashMap().get("altitude").getFeatureIndex();
        this.airspeedIndex = this.flightSettings.getFlightFeatureHashMap().get("airspeed").getFeatureIndex();
        this.rollIndex = this.flightSettings.getFlightFeatureHashMap().get("roll").getFeatureIndex();
    }
    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
        this.sp.setTimeSeries(this.timeSeries);
    }

    public void play() {
        sp.play(1, 1);
    }

    public float getTimestamp() {
        return timestamp.get();
    }

    public FloatProperty timestampProperty() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp.set(timestamp);
    }


    public SimulatorPlayer getSp() {
        return sp;
    }

    public void setSp(SimulatorPlayer sp) {
        this.sp = sp;
    }

    public int getAileronIndex() {
        return aileronIndex;
    }

    public void setAileronIndex(int aileronIndex) {
        this.aileronIndex = aileronIndex;
    }

    public int getThrottleIndex() {
        return throttleIndex;
    }

    public void setThrottleIndex(int throttleIndex) {
        this.throttleIndex = throttleIndex;
    }

    public int getRudderIndex() {
        return rudderIndex;
    }

    public void setRudderIndex(int rudderIndex) {
        this.rudderIndex = rudderIndex;
    }

    public int getElevatorIndex() {
        return elevatorIndex;
    }

    public void setElevatorIndex(int elevatorIndex) {
        this.elevatorIndex = elevatorIndex;
    }

    public int getYawIndex() {
        return yawIndex;
    }

    public void setYawIndex(int yawIndex) {
        this.yawIndex = yawIndex;
    }

    public int getPitchIndex() {
        return pitchIndex;
    }

    public void setPitchIndex(int pitchIndex) {
        this.pitchIndex = pitchIndex;
    }

    public int getHeadingIndex() {
        return headingIndex;
    }

    public void setHeadingIndex(int headingIndex) {
        this.headingIndex = headingIndex;
    }

    public int getAltitudeIndex() {
        return altitudeIndex;
    }

    public void setAltitudeIndex(int altitudeIndex) {
        this.altitudeIndex = altitudeIndex;
    }

    public int getAirspeedIndex() {
        return airspeedIndex;
    }

    public void setAirspeedIndex(int airspeedIndex) {
        this.airspeedIndex = airspeedIndex;
    }

    public int getRollIndex() {
        return rollIndex;
    }

    public void setRollIndex(int rollIndex) {
        this.rollIndex = rollIndex;
    }
}
