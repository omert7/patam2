package app.tests;

import app.model.AppModel;
import app.viewModel.FlightSettings;
import app.viewModel.AppViewModel;

public class Tests {

    public static void main(String[] args) {

        AppModel am = new AppModel();
        AppViewModel avm = new AppViewModel(am);
//        avm.loadSettings("C:\\\\Users\\\\tatio\\\\patam2\\\\src\\\\files\\\\settings.json");
        FlightSettings fs = avm.getAppModel().getFlightSettings();
        System.out.println(fs.getSimulatorPort());
    }

}
