package test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TimeSeries 
{

	 ArrayList<String> namesOfFeatures=new ArrayList<String>();
	 ArrayList<float[]> data=new  ArrayList<float[]>();
	 float coral;
	 int totalTime;
	public TimeSeries(String csvFileName) 
	{
		
		this.coral=(float)0.9;
		String line = "";
		try {
			
			Path p = Paths.get(csvFileName);
			BufferedReader br = new BufferedReader(new FileReader(p.toString()));
			if ((line = br.readLine()) != null) 
			{
				//Reading the file for the first time
				//Creates new Feature for each column
				
				String[] s1 = line.split(",");
				int len=s1.length;	
				for (int c=0;c<len;c++) 
				{
					namesOfFeatures.add(s1[c]);//what will be the feature
					
				 }
				int counter=0;
			    while ((line = br.readLine()) != null) 
			    {
			    	
			    	data.add(new float[len]);//add new line
			    	totalTime++;
			    	s1 = line.split(",");
			    	for(int i=0;i<len;i++)
			    	{
			    		data.get(counter)[i]=(Float.parseFloat(s1[i]));//fill the line with data

			    	}	
			    	counter++;
			    } 
			    this.totalTime=counter;

			}
				} catch(FileNotFoundException e){
					e.printStackTrace();
				} catch(IOException e){
					e.printStackTrace();
				}
	
      }

	public void addNewFeatureAndData(String fName, float[] values) 
	{
	
		if(fName!=null&&values.length>0)
		{
			namesOfFeatures.add(fName);
			data.add(values);
		}
	}
	
	

	public  int featurePlace(String s) 
	{
		
		return namesOfFeatures.indexOf(s);
	}
	
	public  float[] dataOfFeaturerByName(String s)
	{
		int n=featurePlace(s);
		float[] f=new float[data.size()];
		int i=0;
		for (float[] element : data) {
			f[i]=element[n];
			i++;			
		}
		
		return f;
	
	}
	
	public float[] dataOfFeaturerByNum(int s)
	{
		float[] f=new float[data.size()];
		int i=0;
		for (float[] element : data) 
		{
			f[i]=element[s];
			i++;			
		}
		
		return f;
	
	}
	
	public float getValAtSepcifiTime(int time, String request)
	//we get key and feature name 
	//we return the feature at that time
	{
		
		int  sPlace = featurePlace(request);
		if(sPlace == -1){
			return  -1;
		}
		else return data.get(time)[sPlace];
	
	}

	public float getCoral() {
		return coral;
	}

	public void setCoral(float coral) {
		this.coral = coral;
	}







}
