package app.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class FlightSettings {
    private List<FlightFeature> flightFeatureList;
    private String validFlightPath;
    private String chosenAlgorithmPath;
    private String simulatorIp;
    private long simulatorPort;
    private double simulatorSpeed;
    private String settingsFile;
    private List<String> settingsKeys = Arrays.asList("port", "ip", "featuresSettings",
            "chosenAlgorithmPath", "samplingRatePerSec", "validFlightPath");

    public FlightSettings(String file) {
        this.settingsFile = file;
    }

    public void loadSettings() throws Exception {
        this.flightFeatureList = new ArrayList<>();

        Object obj = new JSONParser().parse(new FileReader(this.settingsFile));
        JSONObject jo = (JSONObject) obj;
        if (!jo.keySet().containsAll(this.settingsKeys)) {
            throw new Exception("Settings json File doesn't contain all keys");
        }

        this.simulatorIp = (String) jo.get("ip");
        this.chosenAlgorithmPath = (String) jo.get("chosenAlgorithmPath");
        this.validFlightPath = (String) jo.get("validFlightPath");
        this.simulatorPort = (long) jo.get("port");
        this.simulatorSpeed = ((Long) jo.get("samplingRatePerSec")).doubleValue() / 10; // divide by 10 for x per sec

        if (this.simulatorSpeed < 0.1) {
            throw new Exception("samplingRatePerSec is below 0.1");
        }

        JSONArray featuresSettings = (JSONArray) jo.get("featuresSettings");

        for (Object featuresSetting : featuresSettings) {
            JSONObject feature = (JSONObject) featuresSetting;
            FlightFeature ff = new FlightFeature((String) feature.get("FeatureName"), ((Long) feature.get("min")).doubleValue(),
                    ((Long) feature.get("max")).doubleValue(), ((Long) feature.get("ColumnIndex")).intValue());
            flightFeatureList.add(ff);
        }

    }

    // getters and setters
    public List<FlightFeature> getFlightFeatureList() {
        return flightFeatureList;
    }

    public void setFlightFeatureList(List<FlightFeature> flightFeatureList) {
        this.flightFeatureList = flightFeatureList;
    }

    public String getSimulatorIp() {
        return simulatorIp;
    }

    public void setSimulatorIp(String simulatorIp) {
        this.simulatorIp = simulatorIp;
    }

    public double getSimulatorSpeed() {
        return simulatorSpeed;
    }

    public void setSimulatorSpeed(double simulatorSpeed) {
        this.simulatorSpeed = simulatorSpeed;
    }

    public long getSimulatorPort() {
        return simulatorPort;
    }

    public void setSimulatorPort(long simulatorPort) {
        this.simulatorPort = simulatorPort;
    }

    public String getSettingsFile() {
        return settingsFile;
    }

    public void setSettingsFile(String settingsFile) {
        this.settingsFile = settingsFile;
    }


    public String getValidFlightPath() {
        return validFlightPath;
    }

    public void setValidFlightPath(String validFlightPath) {
        this.validFlightPath = validFlightPath;
    }

    public String getChosenAlgorithmPath() {
        return chosenAlgorithmPath;
    }

    public void setChosenAlgorithmPath(String chosenAlgorithmPath) {
        this.chosenAlgorithmPath = chosenAlgorithmPath;
    }

}
