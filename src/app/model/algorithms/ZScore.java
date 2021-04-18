package app.model.algorithms;
import  app.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import app.CorrelationType;
import app.StatLib;
import app.CorrelationType.typeAlgo;

public class ZScore implements TimeSeriesAnomalyDetector {

	
	HashMap<String,Float> hashMap; 
	
	//fix Zscore!
	
	public ZScore() {
		
		hashMap=new HashMap<>();
	}
	
	public float findZScore (float[] colom,int size){

		if(size<=1)
			return 0;
			float[] arr = new float[size];
	        for(int j=0;j<size;j++){
	        	arr[j]=colom[j];
	        }
	        float temp=StatLib.var(arr);
	        float stia=(float) Math.pow(temp,0.5);
	        float avg=StatLib.avg(arr);
		return Math.abs((colom[size-1]-avg)/stia);
	}

	public float findZmax (float[] fs ){
		if(fs.length<0)
			return -1;
		float max=findZScore(fs,1);
		float temp;
		for(int i=2;i<=fs.length;i++)
		{
			temp=findZScore(fs,i);
			if(temp>max)
				max=temp;
		}
		return max;
	}

	@Override
	public void learnNormal(TimeSeries ts) {
		
		for (CorrelationType temp : ts.dataCoral) {
			if(temp.type==typeAlgo.zScore)
			{
				float[] t1=ts.dataOfFeaturerByName(temp.CoralA);
				float[] t2=ts.dataOfFeaturerByName(temp.CoralB);
				float z1=findZmax(t1);
				float z2=findZmax(t2);
				hashMap.put(temp.CoralA, z1);
				hashMap.put(temp.CoralB, z2);
			}
		}

	}

	@Override
	public List<AnomalyReport> detect(TimeSeries ts) {
		// TODO Auto-generated method stub
		float tempZScore;
	 List<AnomalyReport> list=new  ArrayList<AnomalyReport>();
		int totalTime=ts.totalTime;		
		for (String key : hashMap.keySet()) {
			  
				for(int j=0;j<totalTime;j++)
				{
					tempZScore=findZScore(ts.dataOfFeaturerByName(key),j);
					if(tempZScore>hashMap.get(key))//we detect problem
					{
						list.add(new AnomalyReport(key, j+1));
					}
					
				}
			}
			
	
		
	

		return list;
	}


}
