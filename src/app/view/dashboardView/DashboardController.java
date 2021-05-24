package app.view.dashboardView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DashboardController {

    @FXML
    private Text roll;
    @FXML
    private Text yaw;
    @FXML
    private Text pitch;
    @FXML
    private Text altitude;
    @FXML
    private ImageView heading;
    @FXML
    private ImageView airspeed;


    // Add a public no-args constructor
    public DashboardController() {
    }

    @FXML
    private void initialize() {
        roll.setText("0");
        yaw.setText("0");
        pitch.setText("0");
        altitude.setText("0");
        heading.setRotate(0);
        airspeed.setRotate(0);

    }

    public void updateText() {
        roll.setText("0");
        yaw.setText("0");
        pitch.setText("0");
        altitude.setText("0");
    }


    public Text getRoll() {
        return roll;
    }

    public void setRoll(Text roll) {
        this.roll = roll;
    }

    public Text getYaw() {
        return yaw;
    }

    public void setYaw(Text yaw) {
        this.yaw = yaw;
    }

    public Text getPitch() {
        return pitch;
    }

    public void setPitch(Text pitch) {
        this.pitch = pitch;
    }

    public Text getAltitude() {
        return altitude;
    }

    public void setAltitude(Text altitude) {
        this.altitude = altitude;
    }

    public ImageView getHeading() {
        return heading;
    }

    public void setHeading(ImageView heading) {
        this.heading = heading;
    }

    public ImageView getAirspeed() {
        return airspeed;
    }

    public void setAirspeed(ImageView airspeed) {
        this.airspeed = airspeed;
    }

//    public DoubleProperty spRollProperty() {
//        return SpRoll;
//    }
//
//    public DoubleProperty spYawProperty() {
//        return SpYaw;
//    }
//
//    public DoubleProperty spPitchProperty() {
//        return SpPitch;
//    }
//
//    public DoubleProperty spAltProperty() {
//        return SpAlt;
//    }
//
//    public DoubleProperty dirProperty() {
//        return dir;
//    }
//
//    public void setDir(double dir) {
//        this.dir.set(dir);
//    }
//
//    public DoubleProperty speedProperty() {
//        return speed;
//    }
//
//    public void setSpeed(double speed) {
//        this.speed.set(speed);
//    }
}
