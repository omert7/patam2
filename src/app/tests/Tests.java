package app.tests;

import app.model.AppModel;
import app.model.FlightSettings;
import app.viewModel.AppViewModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Tests {

    public static void main(String[] args) {

//        TimeSeries ts = new TimeSeries("./files/anomaly_flight.csv");
//        for (CorrelationType ct : ts.dataCoral) {
//            System.out.println(ct.CoralA + " + " + ct.CoralB + " : " + ct.type);
//        }
//        CsvFlightHandler cfh = new CsvFlightHandler("C:\\Users\\tatio\\patam2\\src\\files\\anomaly_flight.csv");
//        cfh.readCsvFeatures();
//        cfh.CreateListOfFeaturesFromCsvColumns();
//        cfh.serializeToXML();
//        System.out.println(cfh.getCsvColumns());
//        String settingsFile = "C:\\Users\\tatio\\patam2\\src\\files\\settings.json";
//        FlightSettings fs = new FlightSettings(settingsFile);
//        fs.loadSettings();

        AppModel am = new AppModel();
        AppViewModel avm = new AppViewModel(am);
        avm.loadSettings("C:\\\\Users\\\\tatio\\\\patam2\\\\src\\\\files\\\\settings.json");
        FlightSettings fs = avm.getAppModel().getFlightSettings();
        System.out.println(fs.getSimulatorPort());
    }

}
