package app.view.dashboardView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.util.Objects;

public class Dashboard extends AnchorPane {

    DashboardController dashboardController;
    SimpleStringProperty roll;
    SimpleStringProperty pitch;
    SimpleStringProperty yaw;
    SimpleStringProperty altitude;
    DoubleProperty heading;
    DoubleProperty airspeed;

    public Dashboard() {
        super();
        try {

            FXMLLoader fxl = new FXMLLoader();
            AnchorPane dash = fxl.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")).openStream());
            dashboardController = fxl.getController();
            roll = new SimpleStringProperty();
            pitch = new SimpleStringProperty();
            heading = new SimpleDoubleProperty();
            yaw = new SimpleStringProperty();
            airspeed = new SimpleDoubleProperty();
            altitude = new SimpleStringProperty();
            dashboardController.getAirspeed().rotateProperty().bind(airspeed);
            dashboardController.getHeading().rotateProperty().bind(heading);
            dashboardController.getYaw().textProperty().bind(yaw);
            dashboardController.getRoll().textProperty().bind(roll);
            dashboardController.getPitch().textProperty().bind(pitch);
            dashboardController.getAltitude().textProperty().bind(altitude);

            this.getChildren().add(dash);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }


    public String getRoll() {
        return roll.get();
    }

    public SimpleStringProperty rollProperty() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll.set(roll);
    }

    public String getPitch() {
        return pitch.get();
    }

    public SimpleStringProperty pitchProperty() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch.set(pitch);
    }

    public String getYaw() {
        return yaw.get();
    }

    public SimpleStringProperty yawProperty() {
        return yaw;
    }

    public void setYaw(String yaw) {
        this.yaw.set(yaw);
    }

    public String getAltitude() {
        return altitude.get();
    }

    public SimpleStringProperty altitudeProperty() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude.set(altitude);
    }

    public double getHeading() {
        return heading.get();
    }

    public DoubleProperty headingProperty() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading.set(heading);
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
}
