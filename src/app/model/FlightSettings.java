package app.model;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class FlightSettings {
    private List<FlightFeature> flightFeatureList;
    private String simulatorIp;
    private long simulatorPort;
    private String settingsFile;

    public FlightSettings(String file) {
        this.settingsFile = file;
    }

    public void loadSettings() {
        this.flightFeatureList = new ArrayList<>();

        try {
            Object obj = new JSONParser().parse(new FileReader(this.settingsFile));

            JSONObject jo = (JSONObject) obj;

            this.simulatorIp = (String) jo.get("ip");
            this.simulatorPort = (long) jo.get("port");
            JSONArray featuresSettings = (JSONArray) jo.get("featuresSettings");

            for (Object featuresSetting : featuresSettings) {
                JSONObject feature = (JSONObject) featuresSetting;
                FlightFeature ff = new FlightFeature((String) feature.get("FeatureName"), ((Long) feature.get("min")).doubleValue(),
                        ((Long) feature.get("max")).doubleValue(), ((Long) feature.get("ColumnIndex")).intValue());
                flightFeatureList.add(ff);
            }

        } catch (Exception e) {
            // THROW ERROR TO VIEW
            System.out.println(e.toString());
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
}
