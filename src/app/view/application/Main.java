package app.view.application;

import app.model.AppModel;
import app.viewModel.AppViewModel;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxl = new FXMLLoader();
            BorderPane root = fxl.load(getClass().getResource("./Sample.fxml").openStream());
            AppModel m = new AppModel();
            GUIController view = fxl.getController();
            AppViewModel vm = new AppViewModel(m);
            view.init(vm);
            vm.setRoll(10.2);
            vm.setAltitude(12.2);
            //  vm.setElevator(100);
           // vm.setAileron(30);
          view.getJoystick().getJoystickController().init();
          view.getJoystick().getJoystickController().paint();
          view.getDashboard().getDashboardController().init();
          view.getDashboard().getDashboardController().updateText();
            Scene scene = new Scene(root, 1400, 680);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
