package app.tests;
import app.CorrelationType;
import app.model.algorithms.*;
public class test {

	public static void main(String[] args) {

		TimeSeries ts=new TimeSeries("C:\\Users\\guyle\\Desktop\\gitProjects\\patam2\\src\\files\\anomaly_flight.csv");
		for (CorrelationType ct : ts.dataCoral) {
			System.out.println(ct.CoralA+" + "+ct.CoralB+" : "+ct.type);
		}
		
	}

}
