package app.tests;

import app.CorrelationType;
import app.model.algorithms.*;

public class Tests {

    public static void main(String[] args) {

        TimeSeries ts = new TimeSeries("./files/anomaly_flight.csv");
        for (CorrelationType ct : ts.dataCoral) {
            System.out.println(ct.CoralA + " + " + ct.CoralB + " : " + ct.type);
        }

    }

}
