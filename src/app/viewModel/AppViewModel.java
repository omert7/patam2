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
    private DoubleProperty timeStamp;
    private StringProperty algoFile, csvFile, settingFile;
    private ListProperty<String> listView;
    private FloatProperty altitude, yaw, roll, pitch, airspeed, heading;
    private FloatProperty aileron, elevator, rudder, throttle, centerCircle;
    private FloatProperty minThrottle, maxThrottle, minRudder, maxRudder;
    private FloatProperty minElevator, maxElevator, minAileron, maxAileron;


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
