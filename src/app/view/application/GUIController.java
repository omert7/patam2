package app.view.application;

import app.view.dashbordView.Dashboard;
import app.view.featureListView.FeatureList;
import app.view.graphView.Graph;
import app.view.joystickView.Joystick;
import app.view.menuBarView.MenuBarButton;
import app.view.timeLineView.TimeLine;
import app.viewModel.AppViewModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class GUIController  {
	
   // private SimpleDoubleProperty aileron, elevator, rudder, throttle, altitude, airspeed, heading, yaw, roll, pitch;
    private AppViewModel vm;
    @FXML private FeatureList featList;
    @FXML private Joystick joystick;
    @FXML private Dashboard dashbord;
    @FXML private  Graph graph;
    @FXML private MenuBarButton menuButton;
    @FXML private TimeLine timeLine;
    
    public AppViewModel getVm() {
        return vm;
    }

    public void setVm(AppViewModel vm) {
        this.vm = vm;
    }

    public GUIController(){
     /*   this.aileron = new SimpleDoubleProperty();
        this.elevator = new SimpleDoubleProperty();
        this.throttle = new SimpleDoubleProperty();
        this.rudder = new SimpleDoubleProperty();
        this.airspeed = new SimpleDoubleProperty();
        this.heading = new SimpleDoubleProperty();
        this.yaw = new SimpleDoubleProperty();
        this.pitch = new SimpleDoubleProperty();
        this.roll = new SimpleDoubleProperty();
        this.altitude = new SimpleDoubleProperty();*/
      

    }

    public void init(AppViewModel vm){
        this.vm = vm;
        this.vm.throttleProperty().bind(joystick.getJoystickController().getTrot().valueProperty());
        this.vm.rudderProperty().bind(joystick.getJoystickController().getRudder().valueProperty());
        this.vm.aileronProperty().bind(joystick.getJoystickController().getDPali());
        this.vm.elevatorProperty().bind(joystick.getJoystickController().getDPele());
        
        this.vm.airspeedProperty().bind(dashbord.getDashboardController().getSpeed());
        this.vm.headingProperty().bind(dashbord.getDashboardController().getDir());
        
        this.vm.altitudeProperty().bind(dashbord.getDashboardController().getSpAlt());
        this.vm.rollProperty().bind(dashbord.getDashboardController().getSpRoll());
        this.vm.pitchProperty().bind(dashbord.getDashboardController().getSpPitch());
        this.vm.yawProperty().bind(dashbord.getDashboardController().getSpYaw());
    }

	public FeatureList getFeatList() {
		return featList;
	}

	public void setFeatList(FeatureList featList) {
		this.featList = featList;
	}

	public Joystick getJoystick() {
		return joystick;
	}

	public void setJoystick(Joystick joystick) {
		this.joystick = joystick;
	}

	public Dashboard getDashbord() {
		return dashbord;
	}

	public void setDashbord(Dashboard dashbord) {
		this.dashbord = dashbord;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public MenuBarButton getMenuButton() {
		return menuButton;
	}

	public void setMenuButton(MenuBarButton menuButton) {
		this.menuButton = menuButton;
	}

	public TimeLine getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(TimeLine timeLine) {
		this.timeLine = timeLine;
	}

   
}
