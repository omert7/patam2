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

    public AppModel() {
        this.timestamp = new SimpleFloatProperty();
    }

    public boolean isReady() {
        return (timeSeries != null && flightSettings != null);
    }

    public FlightSettings getFlightSettings() {
        return flightSettings;
    }

    public void setFlightSettings(FlightSettings flightSettings) {
        this.flightSettings = flightSettings;
        sp = new SimulatorPlayer(flightSettings);
        this.timestamp.bindBidirectional(sp.timeStampProperty());
    }

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
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
}
