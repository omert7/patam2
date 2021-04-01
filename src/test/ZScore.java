package test;

import java.util.ArrayList;
import java.util.List;

public class ZScore implements TimeSeriesAnomalyDetector {

	ArrayList<Float> maxZScorre;

	public float findZScore (float[] colom,int size){

		if(size==1)
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
		maxZScorre=new ArrayList<>();
		int size=ts.namesOfFeatures.size();//size of our coloms

		for (int i=1;i<size;i++)//for each colom we calac the stia and avg
		{
			float[] temp=ts.dataOfFeaturerByNum(i);
			maxZScorre.add(findZmax(temp));
		}




	}

	@Override
	public List<AnomalyReport> detect(TimeSeries ts) {
		// TODO Auto-generated method stub
		float tempZScore;
	 List<AnomalyReport> list=new  ArrayList<AnomalyReport>();
		int size=ts.namesOfFeatures.size();//size of our coloms
		int totalTime=ts.totalTime;
		for (int i=1;i<size;i++)//for each colom we calac the stia and avg
		{

			for(int j=0;j<totalTime;j++)
			{
				tempZScore=findZScore(ts.dataOfFeaturerByNum(i),j);
				if(tempZScore>maxZScorre.get(i))//we detect problem
				{
					list.add(new AnomalyReport(ts.namesOfFeatures.get(i), j+1));
				}
			}
		 }

		return list;
	}


}
