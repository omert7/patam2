package app.model;


import app.model.algorithms.TimeSeries;

import java.util.Observable;

public class AppModel extends Observable {
    private FlightSettings flightSettings;
    private TimeSeries timeSeries;

    public void loadSettings(String settingsFile){
        flightSettings = new FlightSettings(settingsFile);
        flightSettings.loadSettings();
    }

    public FlightSettings getFlightSettings() {
        return flightSettings;
    }

    public void setFlightSettings(FlightSettings flightSettings) {
        this.flightSettings = flightSettings;
    }

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }
}
