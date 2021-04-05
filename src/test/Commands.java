package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import anomalyAlgo.SimpleAnomalyDetector;
import anomalyAlgo.SimpleAnomalyDetector.time;

public class Commands {
	
	// Default IO interface
	public interface DefaultIO
	{
		public String readText();
		public void write(String text);
		public float readVal();
		public void write(float val);
		

		// you may add default methods here
		
	}
	
	// the default IO to be used in all commands
	DefaultIO dio;
	
	public Commands(DefaultIO dio) {
		this.dio=dio;
	}
	
	// you may add other helper classes here
	
	public String checkDot(String s)
	{
		String str=s;
		if(str.length()>4)
		{
		
		if(s.charAt(2)=='0'&&s.charAt(3)=='0'&&s.charAt(4)=='0')
			str=s.charAt(0)+".0";
		else if(s.charAt(3)=='0'&&s.charAt(4)=='0')
			str=s.charAt(0)+"."+s.charAt(2);
		else if(s.charAt(4)=='0')
			str=s.charAt(0)+"."+s.charAt(2)+s.charAt(3);
		else {
			str=s.charAt(0)+"."+s.charAt(2)+s.charAt(3)+s.charAt(4);
		}
		}
		return str;
	}
	
	// the shared state of all commands
	private class SharedState{
		// implement here whatever you need
		File train ;
		File test ;
		TimeSeries timeSeries;
		SimpleAnomalyDetector detect;
		List<AnomalyReport> listReport;
	}
	
	private  SharedState sharedState=new SharedState();

	
	// Command abstract class
	public abstract class Command{
		protected String description;
		
		public Command(String description) {
			this.description=description;
		}
		
		public abstract void execute();
	}
	

	// implement here all other commands
	public class uploadFile extends Command{

		public uploadFile() {
			super("");
			
		}

		@Override
		public void execute() {
			
	
			dio.write("Please upload your local train CSV file.\n");
			String s;
			
			try {
				 sharedState.train= new File("anomalyTrain.csv");

				 FileWriter fw1 = new FileWriter(sharedState.train.getAbsolutePath(),true);
				s = new String(dio.readText());
				while(s.compareTo("done")!=0)
				{	
					fw1.write(s+"\n");
					s = new String(dio.readText());
				}
				fw1.close();
				dio.write("Upload complete.\n");
				dio.write("Please upload your local test CSV file.\n");
				 sharedState.test= new File("anomalyTest.csv");
				
				 FileWriter fw = new FileWriter(sharedState.test.getAbsolutePath(),true);
				s = new String(dio.readText());
				while(s.compareTo("done")!=0)
				{	
					fw.write(s+"\n");
					s = new String(dio.readText());
				}
				fw.close();
				sharedState.timeSeries=new TimeSeries(sharedState.train.getAbsolutePath());
				dio.write("Upload complete.\n");
			}
			
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}

			}

		
	}
	public class changeCoral extends Command{

	public changeCoral() {
		super("");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void execute() {
		float f;
	 f=sharedState.timeSeries.getCoral();
	dio.write("The current correlation threshold is "+f+ "\n");
	dio.write("Type a new threshold\n");
	f=dio.readVal();
	while(f<0||f>1)
		{
		dio.write("please choose a value between 0 and 1.\n");
		f=dio.readVal();
		}
	sharedState.timeSeries.setCoral(f);
	}
}
	public class anomalyDetection extends Command{


	public anomalyDetection() {
		super("");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void execute() {
		
	
		sharedState.detect=new SimpleAnomalyDetector();
		sharedState.detect.learnNormal(sharedState.timeSeries);
		dio.write("anomaly detection complete.\n");
	}
	
}
	
	
	public class anomalyReport extends Command{

	public anomalyReport() {
		super("");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void execute() {
		
		sharedState.listReport=sharedState.detect.detect(new TimeSeries(sharedState.test.getAbsolutePath())) ;
		for (AnomalyReport anomalyReport : sharedState.listReport) {
			dio.write(anomalyReport.timeStep+"\t"+anomalyReport.description+"\n");
		}
		dio.write("Done.\n");
	}
	
}
	
	public class detectionAlgo extends Command{
	public detectionAlgo() {
		super("");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void execute() 
	{
		
		ArrayList<time> t=SimpleAnomalyDetector.GroupByTime(sharedState.listReport);
		dio.write("Please upload your local anomalies file.\n");
		String s;
		int FP=t.size();
		int indexOfDetect=0;
		int N=sharedState.timeSeries.totalTime;
		int TP=0;
		File f=new File("temp.txt");
		try {

			 FileWriter fw1 = new FileWriter(f.getAbsolutePath(),true);
			s = new String(dio.readText());
			int p=0;
			while(s.compareTo("done")!=0)
			{	
				fw1.write(s+"\n");
				p++;
				s = new String(dio.readText());
			}
			fw1.close();
			dio.write("Upload complete.\n");
		//	dio.write("Analyzing...\n");
			BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
			String line="";
			
			for(int j=0;j<p;j++)
			{
		
				line = br.readLine();
				String[] s1 = line.split(",");
				long startT=Long.parseLong(s1[0]),endT=Long.parseLong(s1[1]);
				N-=endT-startT+1;
				for(int i=indexOfDetect;i<t.size();i++)
				{
					if((startT<=t.get(i).startTime && t.get(i).startTime<=endT)
						|| 	(startT<=t.get(i).endTime && t.get(i).endTime<=endT)
						||t.get(i).startTime<=startT&& endT<=t.get(i).endTime						
						)//we found detect in rang
						
					{
					
						indexOfDetect=i+1;
						TP++;
						FP--;
						break;
					}
					
				}
				
				
				
			}
			
			float TPrate=(float)TP/p;
	
			String strfloat1= String.valueOf(TPrate);
			strfloat1=checkDot(strfloat1);
			dio.write("True Positive Rate: "+strfloat1 +"\n");
			float FalseAlarm=(float)FP/N;
			String strfloat2= String.valueOf(FalseAlarm);
			strfloat2=checkDot(strfloat2);
			dio.write("False Positive Rate: "+strfloat2 +"\n");
			br.close();
			Files.deleteIfExists(Paths.get(f.getAbsolutePath()));
		}
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		
	}
	

}
	
	public class MenuCommmand extends Command{

		public MenuCommmand() {
			super("Welcome to the Anomaly Detection Server.\n"
					+ "Please choose an option:\n"+
					"1. upload a time series csv file\n"+
					"2. algorithm settings\n"+
					"3. detect anomalies\n"+
					"4. display results\n"+
					"5. upload anomalies and analyze results\n"+
					"6. exit\n");
			
		}

		@Override
		public void execute() {
			
			dio.write(this.description);
		}
		
	}
	

	
}


