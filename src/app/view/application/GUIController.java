package app.view.application;

import app.model.algorithms.LinearRegression;
import app.model.algorithms.ZScore;
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

import java.util.HashMap;


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

    private HashMap<String,XYChart.Series<Number, Number> > seriesHashMap;
    private HashMap<String,XYChart.Series<Number, Number> > seriesHashMapB;
    private HashMap<String,XYChart.Series<Number, Number> > seriesHashMapAnomaly;
    private HashMap<String,XYChart.Series<Number, Number> > seriesHashMapTimeAnomaly;

    private XYChart.Series seriesPointA;
    private XYChart.Series seriesPointB;
    private XYChart.Series seriesPointAnomaly;
    private XYChart.Series seriesTimeAnomaly;
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
        //map of many serries
        seriesHashMap=new HashMap<>();
        seriesHashMapB=new HashMap<>();
        seriesHashMapTimeAnomaly=new HashMap<>();
        seriesHashMapAnomaly=new HashMap<>();
        //series
        seriesPointA=new XYChart.Series<>();
        seriesPointB=new XYChart.Series<>();
        seriesPointAnomaly=new XYChart.Series<>();
        seriesTimeAnomaly=new XYChart.Series<>();
        vm.getNameofFeatureA().set(graph.getNameOfFeatureA().getValue());
        vm.getNameofFeatureB().set(graph.getNameOfFeatureB().getValue());
        graph.getNameOfFeatureA().bindBidirectional(vm.getNameofFeatureA());
        graph.getNameOfFeatureB().bindBidirectional(vm.getNameofFeatureB());
        graph.getGraphController().getFeatureA().getData().add(seriesPointA);
        graph.getGraphController().getFeatureB().getData().add(seriesPointB);
        graph.getGraphController().getAnomalyDetec().getData().add(seriesPointAnomaly);
        graph.getGraphController().getAnomalyDetec().getData().add(seriesTimeAnomaly);
        graph.getGraphController().getSpLabelCoralFeatureA().bindBidirectional(vm.getSpLabelCoralFeatureA());
        graph.getGraphController().getSpLabelCoralFeatureB().bindBidirectional(vm.getSpLabelCoralFeatureB());
        graph.getGraphController().getSpAnomalyClassProperty().bindBidirectional(vm.getSpAnomalyClassProperty());

    }

    private void bindFeatureListProperties() {
        featureList.getListViewP().bind(vm.getListView());
        featureList.getNameOfFeature().bindBidirectional(vm.getNameFromList());

    }





private void addLis(){
       timeLine.timeStampProperty().addListener(
            v -> {
                if(!vm.getNameofFeatureA().getValue().equals(""))
                {
                        vm.getAppModel().addValueAtTime(vm.getNameofFeatureA().getValue(), seriesPointA);

                   if(  vm.isOnflight()&&
                            this.vm.getAppModel().getAnomalDetect()!=null &&
                            this.vm.getAppModel().getAnomalDetect().getClass()== LinearRegression.class)
                    {
                        vm.getAppModel().addValueAtTime(vm.getNameofFeatureB().getValue(), seriesPointB);

                    }
                    if(vm.isOnflight()&&this.vm.getAppModel().getAnomalDetect()!=null )
                        vm.getAppModel().addAnomalyValueAtTime(vm.getNameofFeatureA().getValue(), seriesTimeAnomaly);
                }



            });
       vm.getNameofFeatureA().addListener(v->{
           boolean lock=false;
      if(vm.isOnflight()) {
          vm.pause();
          lock=true;
      }
          // change feature B
                 if(    this.vm.getAppModel().getAnomalDetect()!=null &&
                      this.vm.getAppModel().getAnomalDetect().getClass()== LinearRegression.class)
                 {

                          XYChart.Series z=new XYChart.Series();
                          if(seriesHashMapB.get( vm.getNameofFeatureA().getValue())==null)
                          {

                              vm.getAppModel().addValueTilTime(vm.getNameofFeatureB().getValue(),z);
                              seriesHashMapB.put(vm.getNameofFeatureB().getValue(),z);

                          }

                        graph.getGraphController().getFeatureB().getData().clear();
                          graph.getGraphController().getFeatureB().getData().add(z);
                          seriesPointB=z;
                  // adding the anomaly graph
                     XYChart.Series anomal=new XYChart.Series();
                     XYChart.Series anomalTimes=new XYChart.Series();
                     if(seriesHashMapAnomaly.get( vm.getNameofFeatureA().getValue())==null)
                     {

                         vm.getAppModel().addLine(vm.getNameofFeatureA().getValue(),anomal);
                         seriesHashMapAnomaly.put(vm.getNameofFeatureA().getValue(),anomal);


                     }

                    else{
                         anomal=seriesHashMapAnomaly.get( vm.getNameofFeatureA().getValue());

                     }
                    if(seriesHashMapTimeAnomaly.get( vm.getNameofFeatureA().getValue())==null){
                        vm.getAppModel().addAnomalyValueAtTime(vm.getNameofFeatureA().getValue(),anomalTimes);
                        seriesHashMapTimeAnomaly.replace(vm.getNameofFeatureA().getValue(),anomalTimes);
                    }else{

                        anomalTimes=seriesHashMapTimeAnomaly.get( vm.getNameofFeatureA().getValue());
                    }
                     graph.getGraphController().getAnomalyDetec().getData().clear();
                     graph.getGraphController().getAnomalyDetec().getData().add(anomal);
                     graph.getGraphController().getAnomalyDetec().getData().add(anomalTimes);
                     seriesPointAnomaly=anomal;
                     seriesTimeAnomaly=anomalTimes;
              }
              else if(    this.vm.getAppModel().getAnomalDetect()!=null &&
                             this.vm.getAppModel().getAnomalDetect().getClass()== ZScore.class){
                     XYChart.Series anomal=new XYChart.Series();
                     XYChart.Series anomalTimes=new XYChart.Series();
                     if(seriesHashMapAnomaly.get( vm.getNameofFeatureA().getValue())==null)
                     {

                         vm.getAppModel().addZScoreLine(vm.getNameofFeatureA().getValue(),anomal);
                         seriesHashMapAnomaly.put(vm.getNameofFeatureA().getValue(),anomal);


                     }
                     else{
                         anomal=seriesHashMapAnomaly.get( vm.getNameofFeatureA().getValue());

                     }
                     if(seriesHashMapTimeAnomaly.get( vm.getNameofFeatureA().getValue())==null){
                         vm.getAppModel().addAnomalyValueAtTime(vm.getNameofFeatureA().getValue(),anomalTimes);
                         seriesHashMapTimeAnomaly.replace(vm.getNameofFeatureA().getValue(),anomalTimes);
                     }else{

                         anomalTimes=seriesHashMapTimeAnomaly.get( vm.getNameofFeatureA().getValue());
                     }
                     graph.getGraphController().getAnomalyDetec().getData().clear();
                     graph.getGraphController().getAnomalyDetec().getData().add(anomal);
                     graph.getGraphController().getAnomalyDetec().getData().add(anomalTimes);
                     seriesPointAnomaly=anomal;
                     seriesTimeAnomaly=anomalTimes;
                     }


          //graph A leave it alone!
          XYChart.Series s=new XYChart.Series();
          if(seriesHashMap.get( vm.getNameofFeatureA().getValue())==null)
          {

               vm.getAppModel().addValueTilTime(vm.getNameofFeatureA().getValue(),s);
              seriesHashMap.put(vm.getNameofFeatureA().getValue(),s);

          }

          graph.getGraphController().getFeatureA().getData().clear();
         graph.getGraphController().getFeatureA().getData().add(s);
          seriesPointA=s;
          if(lock){
         vm.play();
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
