package app.view.joystickView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class JoystickController   
{
	
	@FXML private Slider ele;// zir y
	@FXML private Slider ali;//zir x
	@FXML private Canvas joy;
	public DoubleProperty aileron,elevators;
	
	 public JoystickController() 
	    {
	    }
	     
	    @FXML
	    private void initialize() 
	    {
	    	
	    	this.ele.setValue(0);
	    	this.ali.setValue(0);
	    	aileron=new SimpleDoubleProperty();
	    	elevators=new SimpleDoubleProperty();
	    	paint();
	    }
	    
	    
	    public void paint()
	    {
	    	GraphicsContext gc=joy.getGraphicsContext2D();
	    	gc.clearRect(0, 0, joy.getWidth(), joy.getHeight());
	    	gc.strokeRect(0, 0, joy.getWidth(),  joy.getHeight());
	    	gc.strokeOval(getAli()-30, getEle()-30, 60, 60);
	    } 
	    
		public double getEle() {
			//get the value noramllaiz after get the canvas size
			double len=Math.abs(ele.getMax()-ele.getMin());
			double temp=Math.abs(ele.getValue()-ele.getMin());
			double	result=(temp/( len ))*(joy.getHeight());
			System.out.println(result);
			return result;
		}

		/*public void setEle(String ele) {
			this.ele.setValue(double); 
		}*/

		public double getAli() {
			//get the value noramllaiz after get the canvas size
		
			double len=Math.abs(ali.getMax()-ali.getMin());
			double temp=Math.abs(ali.getValue()-ali.getMin());
			double	result=(temp/( len ))*(joy.getWidth());
			System.out.println(result);
			return result;

		}

		

		
	/*	public void setAli(String ali) {
			this.ali = ali;
		} */

}
