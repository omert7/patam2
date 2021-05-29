package app.model;


import app.model.algorithms.TimeSeries;

import java.util.Observable;

public class AppModel extends Observable {
    private FlightSettings flightSettings;
    private TimeSeries timeSeries;
    private SimulatorPlayer sm;

    public boolean isReady(){
        return (timeSeries != null && flightSettings!=null);
    }

    public void loadSettings(String settingsFile) {
        flightSettings = new FlightSettings(settingsFile);
        flightSettings.loadSettings();
        sm = new SimulatorPlayer(flightSettings);
    }


    public FlightSettings getFlightSettings() {
        return flightSettings;
    }

    public void setFlightSettings(FlightSettings flightSettings) {
        this.flightSettings = flightSettings;
        this.flightSettings.loadSettings();
        sm = new SimulatorPlayer(flightSettings);
    }

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }

    public void setAileron(double value, int time) {

    }

    public void play(){
        if (isReady()){
            sm.play(1,1);
            System.out.println("READY");
        }else{
            System.out.println("NOT READY");
        }
    }

}
