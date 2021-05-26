package app.view.dashboardView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class Dashboard extends AnchorPane  {

	  private  DashboardController dashboardController;
	  private  DoubleProperty altitude, yaw  , pitch, roll;
	  private  DoubleProperty airspeed,heading;


    public Dashboard() {
        super();
        try {

            FXMLLoader fxl = new FXMLLoader();
            AnchorPane dash = fxl.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")).openStream());
            dashboardController = fxl.getController();
           
            roll = new SimpleDoubleProperty(dashboardController.getDpRoll().getValue());
            pitch = new SimpleDoubleProperty(dashboardController.getDpPitch().getValue());
            heading = new SimpleDoubleProperty(dashboardController.getDpHeading().getValue());
            yaw = new SimpleDoubleProperty(dashboardController.getDpYaw().getValue());
            airspeed = new SimpleDoubleProperty(dashboardController.getDpAirspeed().getValue());
            altitude = new SimpleDoubleProperty(dashboardController.getDpAltitude().getValue());
            
            dashboardController.getDpAirspeed().bind(airspeed);
            dashboardController.getDpHeading().bind(heading);
            dashboardController.getDpYaw().bind(yaw);
            dashboardController.getDpRoll().bind(roll);
            dashboardController.getDpPitch().bind(pitch);
            dashboardController.getDpAltitude().bind(altitude);

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

	public DoubleProperty getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude.set(altitude);
	}

	public DoubleProperty getYaw() {
		return yaw;
	}

	public void setYaw(double yaw) {
        this.yaw.set(yaw);

	}

	public DoubleProperty getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
        this.pitch.set(pitch);

	}

	public DoubleProperty getRoll() {
		return roll;
	}

	public void setRoll(double roll) {
        this.roll.set(roll);

	}

	public DoubleProperty getAirspeed() {
		return airspeed;
	}

	public void setAirspeed(double airspeed) {
        this.airspeed.set(airspeed);

	}

	public DoubleProperty getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
        this.heading.set(heading);

	}

	

    
}
