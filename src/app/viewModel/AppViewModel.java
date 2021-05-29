package app.viewModel;

import app.model.AppModel;
import app.model.FlightSettings;
import app.model.algorithms.TimeSeries;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Observable;
import java.util.Observer;

public class AppViewModel extends Observable implements Observer {
    private AppModel appModel;
    private TimeSeries ts;
    private DoubleProperty aileron, elevator, rudder, throttle, centerCircle;
    private SimpleDoubleProperty altitude, yaw, roll, pitch, airspeed, heading;
    private DoubleProperty timeStamp;
    private StringProperty algoFile, csvFile, settingFile;
    private ListProperty<String> listView;
    private DoubleProperty minThrottle, maxThrottle, minRudder, maxRudder;
    private DoubleProperty minElevator, maxElevator, minAileron, maxAileron;


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
    }

    private void initJoyStickProperties(){
        this.aileron = new SimpleDoubleProperty();
        this.elevator = new SimpleDoubleProperty();
        this.throttle = new SimpleDoubleProperty();
        this.rudder = new SimpleDoubleProperty();

        this.centerCircle = new SimpleDoubleProperty();

        this.minThrottle = new SimpleDoubleProperty();
        this.maxThrottle = new SimpleDoubleProperty();
        this.minRudder = new SimpleDoubleProperty();
        this.maxRudder = new SimpleDoubleProperty();
        this.minElevator = new SimpleDoubleProperty();
        this.maxElevator = new SimpleDoubleProperty();
        this.minAileron = new SimpleDoubleProperty();
        this.maxAileron = new SimpleDoubleProperty();

    }
    private void initDashBoardProperties(){
        //dashboard
        this.airspeed = new SimpleDoubleProperty();
        this.heading = new SimpleDoubleProperty();
        this.yaw = new SimpleDoubleProperty();
        this.pitch = new SimpleDoubleProperty();
        this.roll = new SimpleDoubleProperty();
        this.altitude = new SimpleDoubleProperty();

    }


    private void createSettings() {
        // TODO check for wrong json format
        resetFlightProp();
        FlightSettings fs = new FlightSettings(settingFile.getValue());
        appModel.setFlightSettings(fs);
    }


    private void createTimeSeries() {
        listView.clear();
        String s = this.csvFile.getValue();
        if (checkCsvFile()) {
            this.appModel.setTimeSeries(new TimeSeries(s));
            this.listView.clear();
            this.listView.addAll(appModel.getTimeSeries().namesOfFeatures);

        } else {

            Alert a1 = new Alert(Alert.AlertType.ERROR,
                    "NOT A VALID CSV", ButtonType.CLOSE);
            // show the dialog
            a1.show();
        }
        resetFlightProp();
    }

    private boolean checkCsvFile() {
        return false;
        ///TODO omer please write this function
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

    public void loadSettings(String settingsFile) {
        appModel.loadSettings(settingsFile);
    }

    public void play() {
        this.appModel.play();

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

    public DoubleProperty aileronProperty() {
        return aileron;
    }

    public void setAileron(double aileron) {
        this.aileron.set(aileron);
    }

    public double getElevator() {
        return elevator.get();
    }

    public DoubleProperty elevatorProperty() {
        return elevator;
    }

    public void setElevator(double elevator) {
        this.elevator.set(elevator);

    }

    public double getRudder() {
        return rudder.get();
    }

    public DoubleProperty rudderProperty() {
        return rudder;
    }

    public void setRudder(double rudder) {
        this.rudder.set(rudder);
    }

    public double getThrottle() {
        return throttle.get();
    }

    public DoubleProperty throttleProperty() {
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

    public DoubleProperty airspeedProperty() {
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


    public double getMinThrottle() {
        return minThrottle.get();
    }

    public DoubleProperty minThrottleProperty() {
        return minThrottle;
    }

    public void setMinThrottle(double minThrottle) {
        this.minThrottle.set(minThrottle);
    }

    public double getMaxThrottle() {
        return maxThrottle.get();
    }

    public DoubleProperty maxThrottleProperty() {
        return maxThrottle;
    }

    public void setMaxThrottle(double maxThrottle) {
        this.maxThrottle.set(maxThrottle);
    }

    public double getMinRudder() {
        return minRudder.get();
    }

    public DoubleProperty minRudderProperty() {
        return minRudder;
    }

    public void setMinRudder(double minRudder) {
        this.minRudder.set(minRudder);
    }

    public double getMaxRudder() {
        return maxRudder.get();
    }

    public DoubleProperty maxRudderProperty() {
        return maxRudder;
    }

    public void setMaxRudder(double maxRudder) {
        this.maxRudder.set(maxRudder);
    }

    public double getMinElevator() {
        return minElevator.get();
    }

    public DoubleProperty minElevatorProperty() {
        return minElevator;
    }

    public void setMinElevator(double minElevator) {
        this.minElevator.set(minElevator);
    }

    public double getMaxElevator() {
        return maxElevator.get();
    }

    public DoubleProperty maxElevatorProperty() {
        return maxElevator;
    }

    public void setMaxElevator(double maxElevator) {
        this.maxElevator.set(maxElevator);
    }

    public double getMinAileron() {
        return minAileron.get();
    }

    public DoubleProperty minAileronProperty() {
        return minAileron;
    }

    public void setMinAileron(double minAileron) {
        this.minAileron.set(minAileron);
    }

    public double getMaxAileron() {
        return maxAileron.get();
    }

    public DoubleProperty maxAileronProperty() {
        return maxAileron;
    }

    public void setMaxAileron(double maxAileron) {
        this.maxAileron.set(maxAileron);
    }

    public double getCenterCircle() {
        return centerCircle.get();
    }

    public DoubleProperty centerCircleProperty() {
        return centerCircle;
    }

    public void setCenterCircle(double centerCircle) {
        this.centerCircle.set(centerCircle);
    }
}
