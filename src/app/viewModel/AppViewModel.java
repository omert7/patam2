package app.viewModel;

import app.model.AppModel;
import app.model.FlightSettings;
import app.model.algorithms.TimeSeries;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.Observable;
import java.util.Observer;

public class AppViewModel extends Observable implements Observer {

    private AppModel appModel;
    private TimeSeries ts;
    private DoubleProperty timeStamp;
    private StringProperty algoFile, csvFile, settingFile;
    private ListProperty<String> listView;
    private FloatProperty altitude, yaw, roll, pitch, airspeed, heading;
    private FloatProperty aileron, elevator, rudder, throttle, centerCircle;
    private FloatProperty minThrottle, maxThrottle, minRudder, maxRudder;
    private FloatProperty minElevator, maxElevator, minAileron, maxAileron;

    Thread startThread;

    public AppViewModel(AppModel am) {
        //joystick
        initJoyStickProperties();
        initDashBoardProperties();

        //menu button
        algoFile = new SimpleStringProperty();
        csvFile = new SimpleStringProperty();
        settingFile = new SimpleStringProperty();

        csvFile.addListener(v -> createTimeSeries());
        settingFile.addListener(v -> createSettings());

        ObservableList<String> observableList = FXCollections.observableArrayList();
        this.listView = new SimpleListProperty<>(observableList);

        this.timeStamp = new SimpleDoubleProperty();

        am.addObserver(this);
        this.appModel = am;
        this.timeStamp.bindBidirectional(am.timestampProperty());
        this.timeStamp.addListener(v -> updateParams());
    }

    private void updateParams() {
        if (this.timeStamp.getValue() == 0) {
            resetFlightProp();
        } else {
            int time = (int) (this.timeStamp.getValue() * 10);
            this.yaw.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getYawIndex()));
            this.pitch.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getPitchIndex()));
            this.roll.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getRollIndex()));
            this.heading.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getHeadingIndex()));
            this.altitude.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getAltitudeIndex()));
            this.airspeed.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getAirspeedIndex()));
            this.throttle.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getThrottleIndex()));
            this.rudder.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getRudderIndex()));
            this.aileron.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getAileronIndex()));
            this.elevator.setValue(this.appModel.getTimeSeries().getValAtSpecificTime(time, this.appModel.getElevatorIndex()));
        }
    }

    private void initJoyStickProperties() {
        this.aileron = new SimpleFloatProperty();
        this.elevator = new SimpleFloatProperty();
        this.throttle = new SimpleFloatProperty();
        this.rudder = new SimpleFloatProperty();
        this.centerCircle = new SimpleFloatProperty();
        this.minThrottle = new SimpleFloatProperty();
        this.maxThrottle = new SimpleFloatProperty();
        this.minRudder = new SimpleFloatProperty();
        this.maxRudder = new SimpleFloatProperty();
        this.minElevator = new SimpleFloatProperty();
        this.maxElevator = new SimpleFloatProperty();
        this.minAileron = new SimpleFloatProperty();
        this.maxAileron = new SimpleFloatProperty();
    }

    private void initDashBoardProperties() {
        //dashboard
        this.airspeed = new SimpleFloatProperty();
        this.heading = new SimpleFloatProperty();
        this.yaw = new SimpleFloatProperty();
        this.pitch = new SimpleFloatProperty();
        this.roll = new SimpleFloatProperty();
        this.altitude = new SimpleFloatProperty();
    }

    private void createSettings() {
        // call pause
        resetFlightProp();
        FlightSettings fs = new FlightSettings(settingFile.getValue());

        try {
            fs.loadSettings();
            appModel.setFlightSettings(fs);
            this.maxAileron.setValue(fs.getFlightFeatureHashMap().get("aileron").getMax());
            this.minAileron.setValue(fs.getFlightFeatureHashMap().get("aileron").getMin());
            this.maxElevator.setValue(fs.getFlightFeatureHashMap().get("elevator").getMax());
            this.minElevator.setValue(fs.getFlightFeatureHashMap().get("elevator").getMin());
            this.maxThrottle.setValue(fs.getFlightFeatureHashMap().get("throttle").getMax());
            this.minThrottle.setValue(fs.getFlightFeatureHashMap().get("throttle").getMin());
            this.maxRudder.setValue(fs.getFlightFeatureHashMap().get("rudder").getMax());
            this.minRudder.setValue(fs.getFlightFeatureHashMap().get("rudder").getMin());
            myGoodAlert("Settings.json");
        } catch (Exception exception) {
            myErrorAlert("Choose Flight Settings.json file ERROR", exception.toString());
        }
    }


    private void createTimeSeries() {
        // call pause
        String s = this.csvFile.getValue();
        String check = checkCsvFile();
        if (check.equals("OK")) {
            this.appModel.setTimeSeries(new TimeSeries(s));
            this.listView.clear();
            this.listView.addAll(appModel.getTimeSeries().namesOfFeatures);
            resetFlightProp();
            myGoodAlert("csv flight");
        } else {
            myErrorAlert("Choose Flight Csv file ERROR", check);
        }

    }

    private void myErrorAlert(String title, String text) {
        Alert a1 = new Alert(Alert.AlertType.ERROR, text, ButtonType.CLOSE);
        a1.setTitle(title);
        a1.show();
    }

    private void myGoodAlert(String fileName) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Managed To Load " + fileName + " File", ButtonType.OK);
        alert.show();
    }


    private String checkCsvFile() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.csvFile.getValue()));
            String line = reader.readLine();
            int counter=1;
            while (line != null) {
                String[] s;
                s = line.split(",");

                if (s.length!=42){
                    return "flight csv row: " + counter +" expected to have 42 column";
                }
                // read next line
                counter++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return e.getMessage();
        }

        return "OK"; // if all is good return OK
    }

    private void resetFlightProp() {
        //joystick
        this.aileron.set(centerCircle.getValue()); ///TODO fix
        this.elevator.set(centerCircle.getValue());
        this.throttle.set((minThrottle.getValue() + maxThrottle.getValue()) / 2);
        this.rudder.set((minRudder.getValue() + maxRudder.getValue()) / 2);

        //dashboard
        this.airspeed.set(0);
        this.heading.set(0);
        this.yaw.set(0);
        this.pitch.set(0);
        this.roll.set(0);
        this.altitude.set(0);

        this.timeStamp.set(0);

    }


    public void setTimeSeries(String timeSeries) {
        this.appModel.setTimeSeries(new TimeSeries(timeSeries));
    }


    public void play() {
        if (!appModel.isReady()) {
            myErrorAlert("Start ERROR", "Flight is missing at least 1 of the followings:\n1) Settings.json file\n2) flight.csv file");
        }
        else if (this.startThread!=null && this.startThread.isAlive()){
            myErrorAlert("Start Error", "Flight is Running\n" +
                    "please press 'Pause' Or 'Stop' flight and run again");
        } else {
            try {
                this.appModel.getSp().createSocket();
                this.startThread = new Thread(() -> {
                    this.appModel.play();
                });
                this.startThread.start();
            }catch (Exception e){
                myErrorAlert("Start ERROR", e.getMessage() + "\nPlease make sure simulator is set\n" +
                        "IP: " + this.appModel.getSp().getIp() + "\nPORT: " +this.appModel.getSp().getPort() + "\n*" +
                        "Parameters are taken from the settings file given by you");
            }
        }
    }


    public void pause() {
        if (this.startThread != null)
            this.startThread.interrupt();
    }

    public void runNext(double value){
        setTimeStamp(this.timeStamp.getValue() + value);

    }

    public void runBack(double value){
        double val = this.timeStamp.getValue() - value;
        if (val<=0){
            this.timeStamp.setValue(0);
        }else{
            this.timeStamp.setValue(val);
        }

    }

    public AppModel getAppModel() {
        return appModel;
    }

    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    public float getAileron() {
        return aileron.get();
    }

    public FloatProperty aileronProperty() {
        return aileron;
    }

    public void setAileron(float aileron) {
        this.aileron.set(aileron);
    }

    public float getElevator() {
        return elevator.get();
    }

    public FloatProperty elevatorProperty() {
        return elevator;
    }

    public void setElevator(float elevator) {
        this.elevator.set(elevator);

    }

    public float getRudder() {
        return rudder.get();
    }

    public FloatProperty rudderProperty() {
        return rudder;
    }

    public void setRudder(float rudder) {
        this.rudder.set(rudder);
    }

    public float getThrottle() {
        return throttle.get();
    }

    public FloatProperty throttleProperty() {
        return throttle;
    }

    public void setThrottle(float throttle) {
        this.throttle.set(throttle);
    }

    public float getAltitude() {
        return altitude.get();
    }

    public FloatProperty altitudeProperty() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude.set(altitude);
    }

    public float getAirspeed() {
        return airspeed.get();
    }

    public FloatProperty airspeedProperty() {
        return airspeed;
    }

    public void setAirspeed(float airspeed) {
        this.airspeed.set(airspeed);
    }

    public float getHeading() {
        return heading.get();
    }

    public FloatProperty headingProperty() {
        return heading;
    }

    public void setHeading(float heading) {
        this.heading.set(heading);
    }

    public float getYaw() {
        return yaw.get();
    }

    public FloatProperty yawProperty() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw.set(yaw);
    }

    public double getRoll() {
        return roll.get();
    }

    public FloatProperty rollProperty() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll.set(roll);
    }

    public float getPitch() {
        return pitch.get();
    }

    public FloatProperty pitchProperty() {
        return pitch;
    }

    public void setPitch(float pitch) {
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

    public StringProperty getAlgoFile() {
        return algoFile;
    }


    public void setAlgoFile(StringProperty algoFile) {
        this.algoFile = algoFile;
    }


    public StringProperty getCsvFile() {
        return csvFile;
    }


    public void setCsvFile(StringProperty csvFile) {
        this.csvFile = csvFile;
    }


    public StringProperty getSettingFile() {
        return settingFile;
    }


    public void setSettingFile(StringProperty settingFile) {
        this.settingFile = settingFile;
    }


    public ListProperty<String> getListView() {
        return listView;
    }

    public void setListView(SimpleListProperty<String> listView) {
        this.listView = listView;
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }


    public float getMinThrottle() {
        return minThrottle.get();
    }

    public FloatProperty minThrottleProperty() {
        return minThrottle;
    }

    public void setMinThrottle(float minThrottle) {
        this.minThrottle.set(minThrottle);
    }

    public float getMaxThrottle() {
        return maxThrottle.get();
    }

    public FloatProperty maxThrottleProperty() {
        return maxThrottle;
    }

    public void setMaxThrottle(float maxThrottle) {
        this.maxThrottle.set(maxThrottle);
    }

    public float getMinRudder() {
        return minRudder.get();
    }

    public FloatProperty minRudderProperty() {
        return minRudder;
    }

    public void setMinRudder(float minRudder) {
        this.minRudder.set(minRudder);
    }

    public float getMaxRudder() {
        return maxRudder.get();
    }

    public FloatProperty maxRudderProperty() {
        return maxRudder;
    }

    public void setMaxRudder(float maxRudder) {
        this.maxRudder.set(maxRudder);
    }

    public float getMinElevator() {
        return minElevator.get();
    }

    public FloatProperty minElevatorProperty() {
        return minElevator;
    }

    public void setMinElevator(float minElevator) {
        this.minElevator.set(minElevator);
    }

    public float getMaxElevator() {
        return maxElevator.get();
    }

    public FloatProperty maxElevatorProperty() {
        return maxElevator;
    }

    public void setMaxElevator(float maxElevator) {
        this.maxElevator.set(maxElevator);
    }

    public float getMinAileron() {
        return minAileron.get();
    }

    public FloatProperty minAileronProperty() {
        return minAileron;
    }

    public void setMinAileron(float minAileron) {
        this.minAileron.set(minAileron);
    }

    public float getMaxAileron() {
        return maxAileron.get();
    }

    public FloatProperty maxAileronProperty() {
        return maxAileron;
    }

    public void setMaxAileron(float maxAileron) {
        this.maxAileron.set(maxAileron);
    }

    public float getCenterCircle() {
        return centerCircle.get();
    }

    public FloatProperty centerCircleProperty() {
        return centerCircle;
    }

    public void setCenterCircle(float centerCircle) {
        this.centerCircle.set(centerCircle);
    }
}
