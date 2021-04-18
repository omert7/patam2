package app.model.algorithms;
import  app.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import app.CorrelationType;
import app.StatLib;
import app.CorrelationType.typeAlgo;

public class HybridAlgo implements TimeSeriesAnomalyDetector {

	
	HashMap<CorrelationType,Circle> hashMap; 
	public HybridAlgo() {
		hashMap=new HashMap<CorrelationType,Circle>();
	}
	@Override
	public void learnNormal(TimeSeries ts) {
		// TODO Auto-generated method stub
			
		for (CorrelationType temp : ts.dataCoral) {
			if(temp.type==typeAlgo.Circle)
			{
				Point[] t=StatLib.ArrayOfPoint(ts.dataOfFeaturerByName(temp.CoralA), ts.dataOfFeaturerByName(temp.CoralB));
				Circle c=SmallestEnclosingCircle.makeCircle(new ArrayList<>(Arrays.asList(t)));
				hashMap.put(temp, c);
			}
		}
		
		
		
	}

	@Override
	public List<AnomalyReport> detect(TimeSeries ts)
	{
		
		 List<AnomalyReport> list=new  ArrayList<AnomalyReport>();
		for (CorrelationType key : hashMap.keySet()) {
		  
			Point[] t=StatLib.ArrayOfPoint(ts.dataOfFeaturerByName(key.CoralA), ts.dataOfFeaturerByName(key.CoralB));
			for(int i=0;i<t.length;i++)
			{
				if(!hashMap.get(key).contains(t[i]))
				{
					//we detect problem!
					list.add(new AnomalyReport(key.CoralA+"-"+key.CoralB, i+1));
				}
			}
			
			
		}
		return list;
	}

}
