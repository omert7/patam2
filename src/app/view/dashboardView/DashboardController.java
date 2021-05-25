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
    
    private DoubleProperty dpHeading,dpAirspeed , dpRoll, dpYaw,dpPitch,dpAltitude;

    // Add a public no-args constructor
    public DashboardController() {
    }

    @FXML
    private void initialize() {
 
    	init();

    }
    
    public void init() {
        //get the setting value and initialize

    	dpHeading = new SimpleDoubleProperty(0);
    	dpAirspeed = new SimpleDoubleProperty(0);
    	dpRoll = new SimpleDoubleProperty(0);
    	dpYaw = new SimpleDoubleProperty(0);
    	dpPitch = new SimpleDoubleProperty(0);
    	dpAltitude = new SimpleDoubleProperty(0);
    	
    	//end
    	
       updateImages();
       updateText();
    }
    
    public void updateImages() {
    	 heading.setRotate(dpHeading.getValue());
         airspeed.setRotate(dpAirspeed.getValue());
    }
    public void updateText() {
    	 roll.setText(dpRoll.getValue().toString());
         yaw.setText( dpYaw.getValue().toString() );
         pitch.setText( dpPitch.getValue().toString());
         altitude.setText(dpAltitude.getValue().toString());
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

	public DoubleProperty getDpHeading() {
		return dpHeading;
	}

	public void setDpHeading(DoubleProperty dpHeading) {
		this.dpHeading = dpHeading;
	}

	public DoubleProperty getDpAirspeed() {
		return dpAirspeed;
	}

	public void setDpAirspeed(DoubleProperty dpAirspeed) {
		this.dpAirspeed = dpAirspeed;
	}

	public DoubleProperty getDpRoll() {
		return dpRoll;
	}

	public void setDpRoll(DoubleProperty dpRoll) {
		this.dpRoll = dpRoll;
	}

	public DoubleProperty getDpYaw() {
		return dpYaw;
	}

	public void setDpYaw(DoubleProperty dpYaw) {
		this.dpYaw = dpYaw;
	}

	public DoubleProperty getDpPitch() {
		return dpPitch;
	}

	public void setDpPitch(DoubleProperty dpPitch) {
		this.dpPitch = dpPitch;
	}

	public DoubleProperty getDpAltitude() {
		return dpAltitude;
	}

	public void setDpAltitude(DoubleProperty dpAltitude) {
		this.dpAltitude = dpAltitude;
	}



    
    
}
