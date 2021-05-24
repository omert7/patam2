package app.view.dashbordView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DashboardController {
	
	@FXML private Text roll;
	@FXML private Text yaw;
	@FXML private Text pitch;
	@FXML private Text high;
	@FXML private ImageView airplane;
	@FXML private ImageView speedRange;
	private DoubleProperty SpRoll, SpYaw , SpPitch ,SpAlt ;
	private DoubleProperty dir,speed;
	// Add a public no-args constructor
    public DashboardController() 
    {
    	SpRoll=new SimpleDoubleProperty(0);
    	SpYaw=new SimpleDoubleProperty(0);
    	SpPitch=new SimpleDoubleProperty(0);
    	SpAlt =new SimpleDoubleProperty(0);
    	dir=new SimpleDoubleProperty(0);
    	speed=new SimpleDoubleProperty(0);
    	updateText();
    	updateImages();
    }
     
   @FXML
   private void initialize() 
    {
    	SpRoll=new SimpleDoubleProperty(0);
    	SpYaw=new SimpleDoubleProperty(0);
    	SpPitch=new SimpleDoubleProperty(0);
    	SpAlt =new SimpleDoubleProperty(0);
    	dir=new SimpleDoubleProperty(0);
    	speed=new SimpleDoubleProperty(0);
    	updateText();
    	updateImages();
    }
    
    public void updateText() {
    	roll.setText(SpRoll.getValue().toString());
    	yaw.setText(SpYaw.getValue().toString());
    	pitch.setText(SpPitch.getValue().toString());
    	high.setText(SpAlt.getValue().toString());
	}
    public void updateImages() {
    	airplane.setRotate(dir.getValue());
    	speedRange.setRotate(speed.getValue()*1.35);
  	}

	

	public DoubleProperty getDir() {
		return dir;
	}

	public void setDir(DoubleProperty dir) {
		this.dir = dir;
	}

	public DoubleProperty getSpeed() {
		return speed;
	}

	public void setSpeed(DoubleProperty speed) {
		this.speed = speed;
	}

	public DoubleProperty getSpRoll() {
		return SpRoll;
	}

	public void setSpRoll(double spRoll) {
		this.SpRoll.set(spRoll);
	}

	public DoubleProperty getSpYaw() {
		return SpYaw;
	}

	public void setSpYaw(double spYaw) {
		
		this.SpYaw.set(spYaw);
	}

	public DoubleProperty getSpPitch() {
		return SpPitch;
		
	}

	public void setSpPitch(double spPitch) {
		this.SpPitch.set(spPitch);
	}

	public DoubleProperty getSpAlt() {
		return SpAlt;
	}

	public void setSpAlt(double spAlt) {
		this.SpAlt.set(spAlt);
	}

	
    
   
    
}
