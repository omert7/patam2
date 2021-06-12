package app.view.application;

import app.view.dashboardView.Dashboard;
import app.view.featureListView.FeatureList;
import app.view.graphView.Graph;
import app.view.joystickView.Joystick;
import app.view.menuBarView.MenuBar;
import app.view.timeLineView.TimeLine;
import app.viewModel.AppViewModel;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;


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


   public  XYChart.Series<Number, Number> attributeA ;
    public  XYChart.Series<Number, Number> attributeB;// = new XYChart.Series<Number, Number>();

    public GUIController() {
    }

    public void init(AppViewModel vm) {
        this.vm = vm;
        BindJoyStickProperties();
        bindDashboardProperties();
        bindMenuBarProperties();
        bindFeatureListProperties();
        bindTimeLineProperties();
        bindGraphProperties();
        addLis();
    }

    private void bindMenuBarProperties() {
        menuBar.getsAlgoFile().bindBidirectional(vm.getAlgoFile());
        menuBar.getsCsvFile().bindBidirectional(vm.getCsvFile());
        menuBar.getsSettingFile().bindBidirectional(vm.getSettingFile());
    }

    private void bindGraphProperties() {
        attributeA= new XYChart.Series<Number, Number>();
        attributeA.setName("feature A values");
        vm.getNameofFeatureA().set(graph.getNameOfFeatureA().getValue());
        vm.getNameofFeatureB().set(graph.getNameOfFeatureB().getValue());
        graph.getNameOfFeatureA().bindBidirectional(vm.getNameofFeatureA());
        graph.getNameOfFeatureB().bindBidirectional(vm.getNameofFeatureB());
        graph.getGraphController().getFeatureA().getData().add(attributeA);
        graph.getGraphController().getAnomalyDetec().setAnimated(false);


    }

    private void bindFeatureListProperties() {
        featureList.getListViewP().bind(vm.getListView());
        featureList.getNameOfFeature().bindBidirectional(vm.getNameFromList());

    }
private void addLis(){
        timeLine.timeStampProperty().addListener(
            v -> {
                    vm.getAppModel().addValueAtTime( graph.getNameOfFeatureA().getValue(),attributeA);
            });


    vm.getNameofFeatureA().addListener(v->{
      if(vm.isOnflight()){
          vm.pause();

          graph.getGraphController().getFeatureA().getData().clear();
         /* attributeA.getData().removeAll();
          attributeA.getData().clear();*/
          attributeA=  vm.getAppModel().addValueTilTime( graph.getNameOfFeatureA().getValue());
          graph.getGraphController().getFeatureA().getData().add(attributeA);
          //vm.play();
        }
      else{
          System.out.println("you are not running");
      }
    });


}


    private void bindTimeLineProperties() {
        timeLine.getTimeLineController().getPlay().setOnMouseClicked(v -> vm.play());
        timeLine.getTimeLineController().getPause().setOnMouseClicked(v -> vm.pause());
        timeLine.getTimeLineController().getNext().setOnMouseClicked(v->vm.runNext(timeLine.getTimeLineController().getNextValue()));
        timeLine.getTimeLineController().getBack().setOnMouseClicked(v->vm.runBack(timeLine.getTimeLineController().getBackValue()));
        timeLine.getTimeLineController().getStop().setOnMouseClicked(v->vm.stop());
        timeLine.getTimeLineController().getTime().setMin(0);
        timeLine.maxTimeLineProperty().bind(vm.maxTimeLineProperty());

        timeLine.timeStampProperty().bindBidirectional(vm.timeStampProperty());
        timeLine.speedProperty().bindBidirectional(vm.speedProperty());
        timeLine.getTimeLineController().getTime().setOnMousePressed(v->{vm.setTimeStamp(timeLine.getTimeLineController().getTime().getValue());
        });
        timeLine.getTimeLineController().getTime().setOnMouseDragged(v->{
            vm.setTimeStamp(timeLine.getTimeLineController().getTime().getValue());
        });

    }

        private void bindDashboardProperties() {
        dashboard.altitudeProperty().bind(vm.altitudeProperty());
        dashboard.yawProperty().bind(vm.yawProperty());
        dashboard.pitchProperty().bind(vm.pitchProperty());
        dashboard.rollProperty().bind(vm.rollProperty());
        dashboard.airspeedProperty().bind(vm.airspeedProperty());
        dashboard.headingProperty().bind(vm.headingProperty());
    }

    private void BindJoyStickProperties() {
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

    public AppViewModel getVm() {
        return vm;
    }

    public void setVm(AppViewModel vm) {
        this.vm = vm;
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
