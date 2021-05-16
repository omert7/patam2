package app.view.dashbordView;

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
	
	// Add a public no-args constructor
    public DashboardController() 
    {
    }
     
    @FXML
    private void initialize() 
    {
    	roll.setText("0");
    	 yaw.setText("0");
    	pitch.setText("0");
    	 high.setText("0");
    }
    
    private void airplaneDir() 
    {
    	//change flight direction
    }
    
    private void airplaneSpeed() 
    {
    	//change speed clock
    }
    
}
