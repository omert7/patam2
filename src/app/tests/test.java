package app.tests;
import java.util.List;

import app.AnomalyReport;

import app.model.algorithms.*;
public class test {

	public static void main(String[] args) 
	{

		TimeSeries ts1=new TimeSeries("C:\\Users\\guyle\\Desktop\\gitProjects\\patam2\\src\\files\\anomalyTrain.csv");
		TimeSeries ts2=new TimeSeries("C:\\Users\\guyle\\Desktop\\gitProjects\\patam2\\src\\files\\anomalyTest.csv");
	
		ZScore z=new ZScore();
		z.learnNormal(ts1);
		 List<AnomalyReport> list=	 z.detect(ts2);
		  for (AnomalyReport anomalyReport : list) 
		  {
			System.out.println(anomalyReport.timeStep+" "+anomalyReport.description);
		  }
	/*  LinearRegression l=new LinearRegression();
			l.learnNormal(ts1);
			  List<AnomalyReport> list2=l.detect(ts2);
			  for (AnomalyReport anomalyReport : list2) {
				System.out.println(anomalyReport.timeStep+" "+anomalyReport.description);
			} 
			  HybridAlgo c=new HybridAlgo();
				c.learnNormal(ts1);
				 List<AnomalyReport> list3=	 c.detect(ts2);
				  for (AnomalyReport anomalyReport : list3) {
					System.out.println(anomalyReport.timeStep+" "+anomalyReport.description);
				}*/
		  System.out.println("done");
		
	}

}
