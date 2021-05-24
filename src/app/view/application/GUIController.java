package app.view.application;

import app.view.dashboardView.Dashboard;
import app.view.featureListView.FeatureList;
import app.view.graphView.Graph;
import app.view.joystickView.Joystick;
import app.view.menuBarView.MenuBarButton;
import app.view.timeLineView.TimeLine;
import app.viewModel.AppViewModel;
import javafx.fxml.FXML;


public class GUIController {

    private AppViewModel vm;
    @FXML
    private FeatureList featList;
    @FXML
    private Joystick joystick;
    @FXML
    private Dashboard dashboard;
    @FXML
    private Graph graph;
    @FXML
    private MenuBarButton menuButton;
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
        joystick.throttleProperty().bind(vm.throttleProperty());
        joystick.rudderProperty().bind(vm.rudderProperty());
        joystick.aileronProperty().bind(vm.aileronProperty());
        joystick.elevatorProperty().bind(vm.elevatorProperty());


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
