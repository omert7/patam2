package app.tests;

import app.model.CsvFlightHandler;

public class Tests {

    public static void main(String[] args) {

//        TimeSeries ts = new TimeSeries("./files/anomaly_flight.csv");
//        for (CorrelationType ct : ts.dataCoral) {
//            System.out.println(ct.CoralA + " + " + ct.CoralB + " : " + ct.type);
//        }
        CsvFlightHandler cfh = new CsvFlightHandler("C:\\Users\\tatio\\patam2\\src\\files\\anomaly_flight.csv");
        cfh.readCsvFeatures();
        cfh.CreateListOfFeaturesFromCsvColumns();
        cfh.serializeToXML();
        System.out.println(cfh.getCsvColumns());
    }

}
