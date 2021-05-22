package app.view.menuBarView;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MenuBarController {
	@FXML private MenuItem setttingXml;
	@FXML private MenuItem csvFile;
	@FXML private MenuItem algoChoose;
	
	// Add a public no-args constructor
    public MenuBarController() 
    {
    }
     
    @FXML
    private void initialize() 
    {
    	//
    }
    @FXML
     void xmlFileChooser(ActionEvent event) 
    {
    	FileChooser fc=new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("xml files", "*.xml"));
    	File f=fc.showOpenDialog(null);
    	if (f!=null)
    	{
    		System.out.println(f.getName());
    	}
    }
    @FXML
    void csvFileChooser(ActionEvent event) 
   {
    	FileChooser fc=new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter(" csv files", "*.csv"));
    	File f=fc.showOpenDialog(null);
    	if (f!=null)
    	{
    		System.out.println(f.getName());
    	}
   }
    @FXML
    void algoFileChooser(ActionEvent event) 
   {
    	FileChooser fc=new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Anomaly detector files", "*.java"));
    	File f=fc.showOpenDialog(null);
    	if (f!=null)
    	{
    		System.out.println(f.getName());
    	}
   }
}
