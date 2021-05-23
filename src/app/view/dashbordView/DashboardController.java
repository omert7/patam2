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
	private StringProperty Sproll, Spyaw , Sppitch ,Sphigh  ;
	private DoubleProperty dir,speed;
	// Add a public no-args constructor
    public DashboardController() 
    {
    }
     
    @FXML
    private void initialize() 
    {
    	Sproll=new SimpleStringProperty("0");
    	Spyaw=new SimpleStringProperty("0");
    	Sppitch=new SimpleStringProperty("0");
    	Sphigh =new SimpleStringProperty("0");   	
    	dir=new SimpleDoubleProperty(0);
    	speed=new SimpleDoubleProperty(0);
    	updateText();
    	updateImages();
    }
    
    public void updateText() {
    	roll.setText(Sproll.getValue());
    	yaw.setText(Spyaw.getValue());
    	pitch.setText(Sppitch.getValue());
    	high.setText(Sphigh.getValue());
	}
    public void updateImages() {
    	airplane.setRotate(dir.getValue());
    	speedRange.setRotate(speed.getValue()*1.35);
  	}

	
    
   
    
}
