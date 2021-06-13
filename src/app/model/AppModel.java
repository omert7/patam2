package app.model;



import app.CorrelatedFeaturesLine;
import app.model.algorithms.HybridAlgo;
import app.model.algorithms.LinearRegression;
import app.model.algorithms.TimeSeries;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import app.model.algorithms.TimeSeriesAnomalyDetector;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;

import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.List;


public class AppModel{
    private FlightSettings flightSettings;
    private TimeSeries timeSeriesTrain;
    private TimeSeries timeSeriesAnomaly;
    private SimulatorPlayer sp;
    private FloatProperty timestamp;
    private int aileronIndex,throttleIndex,rudderIndex,elevatorIndex,
            yawIndex,pitchIndex,headingIndex,altitudeIndex,airspeedIndex,rollIndex;
    private DoubleProperty speed;
    private TimeSeriesAnomalyDetector anomalDetect;

    private HashMap<String, List<Integer>> mapAnomaly;

    public AppModel() {
        this.timestamp = new SimpleFloatProperty();
        this.sp = new SimulatorPlayer();
        this.speed = new SimpleDoubleProperty(1.0);
        sp.speedProperty().bind(this.speed);
    }

    public boolean isReady() {
        return ( timeSeriesTrain != null && flightSettings != null&& timeSeriesAnomaly!=null);
    }





    public void addValueTilTime(String atribute, XYChart.Series s)
    {
        Platform.runLater( ()->{ s.getData().clear();
            int time = (int) (this.timestamp.getValue() * 10);
            for (int i = 1; i <= time; i++) {
                double temp = timeSeriesAnomaly.getValAtSpecificTime(i, atribute);
                s.getData().add(new XYChart.Data(i, temp));
            }} );

    }




    public void addValueAtTime(String atribute, XYChart.Series s) {
        Platform.runLater(()->{

            int time = (int) (this.timestamp.getValue() * 10);
            double temp = timeSeriesAnomaly.getValAtSpecificTime(time, atribute);
            s.getData().add(new XYChart.Data(time, temp));


        });

    }

    public XYChart.Series addValueAtTime2(String atribute, XYChart.Series s)
    {


           int time = (int) (this.timestamp.getValue() * 10);
           double temp = timeSeriesAnomaly.getValAtSpecificTime(time, atribute);
           s.getData().add(new XYChart.Data(time, temp));


           return s;

    }



    public void paint(String atribute) {///TODO paint

        if(this.anomalDetect.getClass()==LinearRegression.class) {

            LinearRegression line =(LinearRegression)this.anomalDetect;

            //get the reg Line
            CorrelatedFeaturesLine  corr=line.hashMap.get(atribute);

            //		LineChart ll = new LineChart(corr.lin_reg.a, corr.lin_reg.b);


        }else if(this.anomalDetect.getClass()==HybridAlgo.class)
        {

        }
        else {
            //zScore

        }

    }


    public FlightSettings getFlightSettings() {
        return flightSettings;
    }

    public void setFlightSettings(FlightSettings flightSettings) {
        this.flightSettings = flightSettings;
        this.sp.setFlightSettings(flightSettings);
        loadIndexes();
        this.timestamp.bindBidirectional(sp.timeStampProperty());
        this.timeSeriesTrain=new TimeSeries(flightSettings.getValidFlightPath());
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


    public TimeSeries getTimeSeriesAnomaly() {
		return timeSeriesAnomaly;
	}

    public TimeSeries getTimeSeriesTrain() {
        return timeSeriesTrain;
    }

    public void setTimeSeriesTrain(String timeSeries) {
        this.timeSeriesTrain =new TimeSeries(timeSeries) ;

    }




	public void setTimeSeriesAnomaly(String timeSeries) {
        this.timeSeriesAnomaly = new TimeSeries(timeSeries);
        this.sp.setTimeSeries(this.timeSeriesAnomaly);
    }


    public TimeSeriesAnomalyDetector getAnomalDetect() {
        return anomalDetect;
    }

    public void setAnomalDetect(TimeSeriesAnomalyDetector anomalDetect) {
        this.anomalDetect = anomalDetect;
        this.anomalDetect.learnNormal(timeSeriesTrain);
        this.mapAnomaly=this.anomalDetect.detect(timeSeriesAnomaly);

    }

    public void play() {
        sp.play();
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

    public double getSpeed() {
        return speed.get();
    }

    public DoubleProperty speedProperty() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed.set(speed);
    }
}
