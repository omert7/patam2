package app.view.joystickView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class JoystickController   
{
	
	@FXML private Slider trot;// zir y
	@FXML private Slider rader;//zir x
	@FXML private Canvas joy;
	private DoubleProperty DPali,DPele;
	private double minTrot,maxTrot,minRad,maxRad;
	private double minele,maxele,minali,maxali;
	 public JoystickController() 
	    {//do not write here!!!		
	    }
	     
	    @FXML
	    private void initialize() 
	    {
	    	//get the setting value and initialize
	    	 minTrot=-1;maxTrot=1 ;minRad=-1;maxRad=1;
			 minele=0;maxele=joy.getHeight();minali=0;maxali=joy.getWidth();
			 //end
	    	this.trot.setMin(minTrot);
	    	this.trot.setMax(maxTrot);
	    	this.rader.setMin(minRad);
	    	this.rader.setMax(maxRad);
	    	
	    	this.trot.setValue(0);
	    	this.rader.setValue(0);
	    	DPali=new SimpleDoubleProperty(joy.getWidth()/2);
	    	DPele=new SimpleDoubleProperty(joy.getHeight()/2);
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
			double len=Math.abs(maxele-minele);
			double temp=Math.abs(DPele.getValue()-minele);
			double	result=(temp/( len ))*(joy.getHeight());
			System.out.println(result);
			return result;
		}

		/*public void setEle(String ele) {
			this.ele.setValue(double); 
		}*/

		public double getAli() {
			//get the value noramllaiz after get the canvas size
		
			double len=Math.abs(maxali-minali);
			double temp=Math.abs(DPali.getValue()-minali);
			double	result=(temp/( len ))*(joy.getHeight());
			System.out.println(result);
			return result;

		}

		public Slider getTrot() {
			return trot;
		}

		public void setTrot(Slider trot) {
			this.trot = trot;
		}

		public Slider getRader() {
			return rader;
		}

		public void setRader(Slider rader) {
			this.rader = rader;
		}

		public Canvas getJoy() {
			return joy;
		}

		public void setJoy(Canvas joy) {
			this.joy = joy;
		}

		public DoubleProperty getDPali() {
			return DPali;
		}

		public void setDPali(DoubleProperty dPali) {
			DPali = dPali;
		}

		public DoubleProperty getDPele() {
			return DPele;
		}

		public void setDPele(DoubleProperty dPele) {
			DPele = dPele;
		}

		public double getMinTrot() {
			return minTrot;
		}

		public void setMinTrot(double minTrot) {
			this.minTrot = minTrot;
		}

		public double getMaxTrot() {
			return maxTrot;
		}

		public void setMaxTrot(double maxTrot) {
			this.maxTrot = maxTrot;
		}

		public double getMinRad() {
			return minRad;
		}

		public void setMinRad(double minRad) {
			this.minRad = minRad;
		}

		public double getMaxRad() {
			return maxRad;
		}

		public void setMaxRad(double maxRad) {
			this.maxRad = maxRad;
		}

		public double getMinele() {
			return minele;
		}

		public void setMinele(double minele) {
			this.minele = minele;
		}

		public double getMaxele() {
			return maxele;
		}

		public void setMaxele(double maxele) {
			this.maxele = maxele;
		}

		public double getMinali() {
			return minali;
		}

		public void setMinali(double minali) {
			this.minali = minali;
		}

		public double getMaxali() {
			return maxali;
		}

		public void setMaxali(double maxali) {
			this.maxali = maxali;
		}

		

		
	/*	public void setAli(String ali) {
			this.ali = ali;
		} */

}
