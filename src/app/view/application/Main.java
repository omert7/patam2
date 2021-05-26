package app.view.application;

import app.model.AppModel;
import app.view.dashboardView.Dashboard;
import app.view.joystickView.Joystick;
import app.viewModel.AppViewModel;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxl = new FXMLLoader();
            BorderPane root = fxl.load(getClass().getResource("./Sample.fxml").openStream());
            AppModel m = new AppModel();
            AppViewModel vm = new AppViewModel(m);
            GUIController view = fxl.getController();
            view.init(vm);
           
          //  vm.setAltitude(12.2);
            

	      
            Scene scene = new Scene(root, 1400, 680);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            new Thread(()->{
            	try {
					Thread.sleep(3000);
					System.out.println("hi guy");
					vm.setRoll(10.2);
					vm.setElevator(65);
					vm.setAileron(105);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }).start();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
