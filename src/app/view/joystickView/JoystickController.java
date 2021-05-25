package app.view.joystickView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

public class JoystickController {

    @FXML
    private Slider throttle;// zir y
    @FXML
    private Slider rudder;//zir x
    @FXML
    private Canvas joy;

    private DoubleProperty aileron, elevator;
    private DoubleProperty minTrot, maxTrot, minRad, maxRad;
    private DoubleProperty minele, maxele, minali, maxali;

    public JoystickController() {
        //do not write here!!!
    }

    @FXML
    private void initialize() {
      init();
    }

    public void init() {
    	  //get the setting value and initialize
        minTrot = new SimpleDoubleProperty(-1);
        maxTrot = new SimpleDoubleProperty(1);
        minRad = new SimpleDoubleProperty(-1);
        maxRad = new SimpleDoubleProperty(1);
        minele = new SimpleDoubleProperty(0);
        maxele = new SimpleDoubleProperty(joy.getHeight());
        minali = new SimpleDoubleProperty(0);
        maxali = new SimpleDoubleProperty(joy.getWidth());


        //end
        this.throttle.setMin(minTrot.getValue());
        this.throttle.setMax(maxTrot.getValue());
        this.rudder.setMin(minRad.getValue());
        this.rudder.setMax(maxRad.getValue());

        this.throttle.setValue((maxTrot.getValue()-minTrot.getValue())/2);
        this.rudder.setValue( (maxRad.getValue()-minRad.getValue())/2 );
        this.aileron = new SimpleDoubleProperty(joy.getWidth() / 2);
        this.elevator = new SimpleDoubleProperty(joy.getHeight() / 2);
        paint();
    }
    
    public void paint() {
        GraphicsContext gc = joy.getGraphicsContext2D();
        gc.clearRect(0, 0, joy.getWidth(), joy.getHeight());
        gc.strokeRect(0, 0, joy.getWidth(), joy.getHeight());
        gc.strokeOval(getAli() - 30, getEle() - 30, 60, 60);
    }

    public double getEle() {
        //get the value noramllaiz after get the canvas size
        double len = Math.abs(maxele.getValue() - minele.getValue());
        double temp = Math.abs(this.elevator.getValue() - minele.getValue());
        double result = (temp / (len)) * (joy.getHeight());
        System.out.println(result);
        return result;
    }


    public double getAli() {
        //get the value noramllaiz after get the canvas size

        double len = Math.abs(maxali.getValue() - minali.getValue());
        double temp = Math.abs(this.aileron.getValue() - minali.getValue());
        double result = (temp / (len)) * (joy.getHeight());
        System.out.println(result);
        return result;

    }

    public Slider getThrottle() {
        return throttle;
    }

    public void setThrottle(Slider throttle) {
        this.throttle = throttle;
    }

    public Slider getRudder() {
        return rudder;
    }

    public void setRudder(Slider rader) {
        this.rudder = rader;
    }

    public Canvas getJoy() {
        return joy;
    }

    public void setJoy(Canvas joy) {
        this.joy = joy;
    }

    public DoubleProperty getAileron() {
        return aileron;
    }

    public void setAileron(DoubleProperty dPali) {
        aileron = dPali;
        paint();
        
    }

    public DoubleProperty getElevator() {
        return elevator;
    }

    public void setElevator(DoubleProperty dPele) {
        elevator = dPele;
        paint();
    }

    public DoubleProperty getMinTrot() {
        return minTrot;
    }

    public void setMinTrot(DoubleProperty minTrot) {
        this.minTrot = minTrot;
    }

    public DoubleProperty getMaxTrot() {
        return maxTrot;
    }

    public void setMaxTrot(DoubleProperty maxTrot) {
        this.maxTrot = maxTrot;
    }

    public DoubleProperty getMinRad() {
        return minRad;
    }

    public void setMinRad(DoubleProperty minRad) {
        this.minRad = minRad;
    }

    public DoubleProperty getMaxRad() {
        return maxRad;
    }

    public void setMaxRad(DoubleProperty maxRad) {
        this.maxRad = maxRad;
    }

    public DoubleProperty getMinele() {
        return minele;
    }

    public void setMinele(DoubleProperty minele) {
        this.minele = minele;
    }

    public DoubleProperty getMaxele() {
        return maxele;
    }

    public void setMaxele(DoubleProperty maxele) {
        this.maxele = maxele;
    }

    public DoubleProperty getMinali() {
        return minali;
    }

    public void setMinali(DoubleProperty minali) {
        this.minali = minali;
    }

    public DoubleProperty getMaxali() {
        return maxali;
    }

    public void setMaxali(DoubleProperty maxali) {
        this.maxali = maxali;
    }


}
