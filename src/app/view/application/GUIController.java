package app.view.application;

import app.view.dashboardView.Dashboard;
import app.view.featureListView.FeatureList;
import app.view.graphView.Graph;
import app.view.joystickView.Joystick;
import app.view.menuBarView.MenuBar;
import app.view.timeLineView.TimeLine;
import app.viewModel.AppViewModel;
import javafx.fxml.FXML;


public class GUIController {

    private AppViewModel vm;
    @FXML
    private FeatureList featureList;
    @FXML
    private Joystick joystick;
    @FXML
    private Dashboard dashboard;
    @FXML
    private Graph graph;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TimeLine timeLine;

    public AppViewModel getVm() {
        return vm;
    }

    public void setVm(AppViewModel vm) {
        this.vm = vm;
    }

    public GUIController() {
    }

    public void init(AppViewModel vm) {
        this.vm = vm;
        BindJoyStickProperties();;
        bindDashboardProperties();
        bindMenuBarProperties();
        bindFeatureListProperties();
        bindTimeLineProperties();
    }

    private void bindMenuBarProperties(){
        menuBar.getsAlgoFile().bindBidirectional(vm.getAlgoFile());
        menuBar.getsCsvFile().bindBidirectional(vm.getCsvFile());
        menuBar.getsSettingFile().bindBidirectional(vm.getSettingFile());
    }
    private void bindFeatureListProperties(){
        featureList.getListViewP().bind(vm.getListView());

    }
    private void bindTimeLineProperties(){
        timeLine.getTimeLineController().getPlay().setOnMouseClicked(v -> vm.play());

    }

    private void bindDashboardProperties(){
        dashboard.altitudeProperty().bind(vm.altitudeProperty());
        dashboard.yawProperty().bind(vm.yawProperty());
        dashboard.pitchProperty().bind(vm.pitchProperty());
        dashboard.rollProperty().bind(vm.rollProperty());
        dashboard.airspeedProperty().bind(vm.airspeedProperty());
        dashboard.headingProperty().bind(vm.headingProperty());
    }

    private void BindJoyStickProperties(){
        vm.minThrottleProperty().setValue(joystick.minThrottleProperty().getValue());
        vm.maxThrottleProperty().setValue(joystick.maxThrottleProperty().getValue());
        vm.minRudderProperty().setValue(joystick.minRudderProperty().getValue());
        vm.maxRudderProperty().setValue(joystick.maxRudderProperty().getValue());
        vm.minElevatorProperty().setValue(joystick.minElevatorProperty().getValue());
        vm.maxElevatorProperty().setValue(joystick.maxElevatorProperty().getValue());
        vm.minAileronProperty().setValue(joystick.minAileronProperty().getValue());
        vm.maxAileronProperty().setValue(joystick.maxAileronProperty().getValue());

        joystick.minThrottleProperty().bind(vm.minThrottleProperty());
        joystick.maxThrottleProperty().bind(vm.maxThrottleProperty());
        joystick.minRudderProperty().bind(vm.minRudderProperty());
        joystick.maxRudderProperty().bind(vm.maxRudderProperty());
        joystick.minElevatorProperty().bind(vm.minElevatorProperty());
        joystick.maxElevatorProperty().bind(vm.maxElevatorProperty());
        joystick.minAileronProperty().bind(vm.minAileronProperty());
        joystick.maxAileronProperty().bind(vm.maxAileronProperty());
        joystick.throttleProperty().bind(vm.throttleProperty());
        joystick.rudderProperty().bind(vm.rudderProperty());

        vm.aileronProperty().setValue(joystick.getAileron());
        joystick.aileronProperty().bind(vm.aileronProperty());
        vm.elevatorProperty().setValue(joystick.getElevator());
        joystick.elevatorProperty().bind(vm.elevatorProperty());
        vm.centerCircleProperty().setValue(joystick.centerCircleProperty().getValue());
        joystick.centerCircleProperty().bind(vm.centerCircleProperty());

    }

    public FeatureList getFeatureList() {
        return featureList;
    }

    public void setFeatureList(FeatureList featureList) {
        this.featureList = featureList;
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public void setJoystick(Joystick joystick) {
        this.joystick = joystick;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }


}
