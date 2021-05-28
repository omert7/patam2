package app.viewModel;

import app.model.AppModel;
import app.model.algorithms.TimeSeries;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Observable;
import java.util.Observer;

public class AppViewModel extends Observable implements Observer {
    private AppModel appModel;
    private TimeSeries ts;
    private SimpleDoubleProperty aileron, elevator, rudder, throttle, airspeed, heading;
    private SimpleDoubleProperty altitude, yaw, roll, pitch;
    private DoubleProperty timeStamp;
    private StringProperty algoFile, csvFile, settingFile;
    private ListProperty<String> listView;

    public AppViewModel(AppModel am) {
        //joystick
        this.aileron = new SimpleDoubleProperty();
        this.elevator = new SimpleDoubleProperty();
        this.throttle = new SimpleDoubleProperty();
        //dashboard
        this.rudder = new SimpleDoubleProperty();
        this.airspeed = new SimpleDoubleProperty();
        this.heading = new SimpleDoubleProperty();
        this.yaw = new SimpleDoubleProperty();
        this.pitch = new SimpleDoubleProperty();
        this.roll = new SimpleDoubleProperty();
        this.altitude = new SimpleDoubleProperty();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        this.listView = new SimpleListProperty<>(observableList);
        this.timeStamp = new SimpleDoubleProperty();

        //menu button
        algoFile = new SimpleStringProperty();
        csvFile = new SimpleStringProperty();
        settingFile = new SimpleStringProperty(); //TODO add listener and function for settingsFile
        csvFile.addListener(v -> createTimeSeries());
        //listView.addListener((ListChangeListener)v->creatList());
        am.addObserver(this);
        this.appModel = am;
    }


    private void createTimeSeries() {
        listView.clear();
        String s = this.csvFile.getValue();
        //	if(checkCsvFile())
        if (true) {
            this.appModel.setTimeSeries(new TimeSeries(s));
            this.listView.clear();
            this.listView.addAll(appModel.getTimeSeries().namesOfFeatures);
            resetFilghtProp();
        } else {
            //handel with wrong file!!
        }
    }


    private void resetFilghtProp() {
        //joystick
        this.aileron.set(75); ///TODO fix
        this.elevator.set(75);
        this.throttle.set(0);
        //dashbord

        this.rudder.set(0);
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

    public void play(int start, int rate) {
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

    public SimpleDoubleProperty aileronProperty() {
        return aileron;
    }

    public void setAileron(double aileron) {
        this.aileron.set(aileron);
    }

    public double getElevator() {
        return elevator.get();
    }

    public SimpleDoubleProperty elevatorProperty() {
        return elevator;
    }

    public void setElevator(double elevator) {
        this.elevator.set(elevator);

    }

    public double getRudder() {
        return rudder.get();
    }

    public SimpleDoubleProperty rudderProperty() {
        return rudder;
    }

    public void setRudder(double rudder) {
        this.rudder.set(rudder);
    }

    public double getThrottle() {
        return throttle.get();
    }

    public SimpleDoubleProperty throttleProperty() {
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

    public SimpleDoubleProperty airspeedProperty() {
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


}
