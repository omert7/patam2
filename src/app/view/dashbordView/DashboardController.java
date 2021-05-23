package app.view.dashbordView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DashboardController {
	
	@FXML private Text roll;
	@FXML private Text yaw;
	@FXML private Text pitch;
	@FXML private Text high;
	@FXML private ImageView airplane;
	@FXML private ImageView speedRange;
	private DoubleProperty dir,speed;
	private StringProperty SpRoll,SpYaw,SpPitch,SpHigh;
	// Add a public no-args constructor
    public DashboardController() 
    {
    }
     
    @FXML
    private void initialize() 
    {
    	dir=new SimpleDoubleProperty(0);
    	speed=new SimpleDoubleProperty(0);
    	SpRoll=new SimpleStringProperty("0");
    	SpYaw=new SimpleStringProperty("0");
    	SpPitch=new SimpleStringProperty("0");
    	SpHigh=new SimpleStringProperty("0");
    	update();
    	
    }
    
    private void airplaneDir() 
    {
    	//change flight direction
    	airplane.setRotate(dir.getValue());
    }
    
    private void airplaneSpeed() 
    {
    	speedRange.setRotate(speed.getValue());
    }
    private void update() 
    {
    	roll.setText(SpRoll.getValue());
	   	 yaw.setText(SpYaw.getValue());
	   	pitch.setText(SpPitch.getValue());
	   	 high.setText(SpHigh.getValue());
    }

	public ImageView getSpeedRange() {
		return speedRange;
	}

	public void setSpeedRange(ImageView speedRange) {
		this.speedRange = speedRange;
	}

	public DoubleProperty getSpeed() {
		return speed;
	}

	public void setSpeed(DoubleProperty speed) {
		this.speed = speed;
	}

	public StringProperty getSpRoll() {
		return SpRoll;
	}

	public void setSpRoll(StringProperty spRoll) {
		SpRoll = spRoll;
	}

	public StringProperty getSpYaw() {
		return SpYaw;
	}

	public void setSpYaw(StringProperty spYaw) {
		SpYaw = spYaw;
	}

	public StringProperty getSpPitch() {
		return SpPitch;
	}

	public void setSpPitch(StringProperty spPitch) {
		SpPitch = spPitch;
	}

	public StringProperty getSpHigh() {
		return SpHigh;
	}

	public void setSpHigh(StringProperty spHigh) {
		SpHigh = spHigh;
	}
    
    
    
    
}
