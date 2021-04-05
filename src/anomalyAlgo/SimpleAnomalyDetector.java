package anomalyAlgo;

import java.util.ArrayList;
import java.util.List;

import test.AnomalyReport;
import test.CorrelatedFeatures;
import test.Line;
import test.Point;
import test.StatLib;
import test.TimeSeries;
import test.TimeSeriesAnomalyDetector;



public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {
	
	 ArrayList<CorrelatedFeatures> dataCoral=new  ArrayList<CorrelatedFeatures>();
	 
	
	 
	@Override
	public void learnNormal(TimeSeries ts) 
	{
		//we want to find the best pearson
		//between to col i and col j
		float maxp,t,maxdev,threshold;
		float[] arrayX,arrayY;
		int x,y,i,j;
		int size=ts.data.get(0).length;//size of our rows
		Point[] temp;
		Line lin_reg; 
		for( i=0;i<size;i++)
		{
			threshold=-1;
			maxp=0;
			x=i;
			y=i;
			arrayX=ts.dataOfFeaturerByNum(i);
			for(j=i+1;j<size;j++)
			{
				arrayY=ts.dataOfFeaturerByNum(j);
				t=StatLib.pearson(arrayX, arrayY);
				
				if (Math.abs(t)>ts.getCoral())
				{
					y=j;
					maxp=t;
				}
			}
	
			if(maxp!=0)
			{
				 temp=StatLib.ArrayOfPoint(ts.dataOfFeaturerByNum(x), ts.dataOfFeaturerByNum(y));
				 lin_reg=StatLib.linear_reg(temp);
				for (Point point : temp) 
				{
				 maxdev=StatLib.dev(point, lin_reg);
				if(maxdev>threshold)
					threshold=maxdev;	
				}
				threshold=Math.abs(threshold);
				dataCoral.add(new CorrelatedFeatures(ts.namesOfFeatures.get(x), ts.namesOfFeatures.get(y), maxp, lin_reg, threshold));
			}
			
		}
	}

	@Override
	public List<AnomalyReport> detect(TimeSeries ts) 
	{
		//we know that in dataCoral we have connection between dataCoral.get(i)
		Point  temp;
		int size=dataCoral.size();//number of correlation
		 List<AnomalyReport> list=new  ArrayList<AnomalyReport>();
		//first we create the point by what we know that correlated
		for(int i=0;i<size;i++)
		{
			
			String correlate1=new String(dataCoral.get(i).feature1);
			float[] fcorrelate1=ts.dataOfFeaturerByName(correlate1);
			String correlate2=new String(dataCoral.get(i).feature2);
			float[] fcorrelate2=ts.dataOfFeaturerByName(correlate2);
			for(int z=0;z<fcorrelate1.length;z++) 
			{
				
			 temp=new Point(fcorrelate1[z], fcorrelate2[z]);
			if(StatLib.dev(temp, dataCoral.get(i).lin_reg)>dataCoral.get(i).threshold+0.015f) 
				{
					//we find error
					list.add(new AnomalyReport( correlate1+"-" +correlate2 ,z+1) );
				}
			}
		}
			return list;
	}
	
	public List<CorrelatedFeatures> getNormalModel()
	{
		return dataCoral;
	}
	

	static public class time{
		public long startTime;
		public long endTime;
		public int alarm;
		public time(long timeStep, long timeStep2) {
			super();
			this.startTime = timeStep;
			this.endTime = timeStep2;
			this.alarm=1;
		}
		public long getStartTime() {
			return startTime;
		}
		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}
		public long getEndTime() {
			return endTime;
		}
		public void setEndTime(long timeStep) {
			this.endTime = timeStep;
		}
		
	}
	
	static public ArrayList<time> GroupByTime(List<AnomalyReport> ListAnomalyReport )
	{
		ArrayList<time> list=new ArrayList<time>();
		int i=0;
		int countList=0;
		list.add(new time(ListAnomalyReport.get(i).timeStep,ListAnomalyReport.get(i).timeStep));
			while(i<ListAnomalyReport.size()-1)
		
			{
				if( ListAnomalyReport.get(i).timeStep+1==ListAnomalyReport.get(i+1).timeStep
					&& ListAnomalyReport.get(i).description.compareTo(ListAnomalyReport.get(i+1).description)==0	)
				{
					list.get(countList).setEndTime(ListAnomalyReport.get(i+1).timeStep);
					list.get(countList).alarm++;
				}
				
				else {
					list.add(new time(ListAnomalyReport.get(i+1).timeStep,ListAnomalyReport.get(i+1).timeStep));
					countList++;
				}
					i++;
			}

		return list;
	}
	






}
