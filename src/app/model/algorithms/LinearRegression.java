package app.model.algorithms;


import app.CorrelatedFeaturesLine;
import app.model.statlib.Point;
import app.model.statlib.Line;
import app.AnomalyReport;
import app.model.statlib.StatLib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LinearRegression implements TimeSeriesAnomalyDetector {

   // ArrayList<CorrelatedFeaturesLine> dataCoral;
    public HashMap<String, CorrelatedFeaturesLine> hashMap;
   
    public LinearRegression() {
        //dataCoral = new ArrayList<CorrelatedFeaturesLine>();
        hashMap=new HashMap<String, CorrelatedFeaturesLine>();
      
        
    }

    @Override
    public void learnNormal(TimeSeries ts) {
    	
        //we want to find the best pearson
        //between colom i and colom j
        float maxp, t, maxdev, threshold;
        float[] arrayX, arrayY;
        int x, y, i, j;
        int size = ts.data.get(0).length;//size of our rows
        Point[] temp;
        Line lin_reg;
        for (i = 0; i < size; i++) {
            maxp = 0;
            x = i;
            y = i;
            arrayX = ts.dataOfFeaturerByNum(i);
            for (j = i + 1; j < size; j++) {
                arrayY = ts.dataOfFeaturerByNum(j);
                t = StatLib.pearson(arrayX, arrayY);

                if (Math.abs(t) > maxp ) {
                    y = j;
                    maxp = t;
                }
            }

            threshold = -1;
            if (maxp != 0) {
                temp = StatLib.ArrayOfPoint(ts.dataOfFeaturerByNum(x), ts.dataOfFeaturerByNum(y));
                lin_reg = StatLib.linear_reg(temp);
                for (Point point : temp) {
                    maxdev = StatLib.dev(point, lin_reg);
                    if (maxdev > threshold)
                        threshold = maxdev;
                }
                threshold = Math.abs(threshold);
                //dataCoral.add(new CorrelatedFeaturesLine(ts.namesOfFeatures.get(x), ts.namesOfFeatures.get(y), maxp, lin_reg, threshold));
               hashMap.put(ts.namesOfFeatures.get(x),new CorrelatedFeaturesLine(ts.namesOfFeatures.get(x), ts.namesOfFeatures.get(y), maxp, lin_reg, threshold));
           
            }

        }
    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        //we know that in dataCoral we have connection between dataCoral.get(i)
        Point temp;
        List<AnomalyReport> list = new ArrayList<AnomalyReport>();
        //first we create the point by what we know that correlated
        for(String f: ts.namesOfFeatures) 
        {
        	if(hashMap.containsKey(f)) {
        	float[] fcorrelate1 = ts.dataOfFeatureByName(f);
            String correlate2 = new String(hashMap.get(f).feature2);
            float[] fcorrelate2 = ts.dataOfFeatureByName(correlate2);
            for (int z = 0; z < fcorrelate1.length; z++) {

                temp = new Point(fcorrelate1[z], fcorrelate2[z]);
                if (StatLib.dev(temp, hashMap.get(f).lin_reg) > hashMap.get(f).threshold + 0.015f) {
                    //we find error
                    list.add(new AnomalyReport(f + "-" + correlate2, z + 1));
                }
            	}
        	}
        }
        return list;
    }

    
    static public class time {
        public long startTime;
        public long endTime;
        public int alarm;

        public time(long timeStep, long timeStep2) {
            super();
            this.startTime = timeStep;
            this.endTime = timeStep2;
            this.alarm = 1;
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

    static public ArrayList<time> GroupByTime(List<AnomalyReport> ListAnomalyReport) {
        ArrayList<time> list = new ArrayList<time>();
        int i = 0;
        int countList = 0;
        list.add(new time(ListAnomalyReport.get(i).timeStep, ListAnomalyReport.get(i).timeStep));
        while (i < ListAnomalyReport.size() - 1) {
            if (ListAnomalyReport.get(i).timeStep + 1 == ListAnomalyReport.get(i + 1).timeStep
                    && ListAnomalyReport.get(i).description.compareTo(ListAnomalyReport.get(i + 1).description) == 0) {
                list.get(countList).setEndTime(ListAnomalyReport.get(i + 1).timeStep);
                list.get(countList).alarm++;
            } else {
                list.add(new time(ListAnomalyReport.get(i + 1).timeStep, ListAnomalyReport.get(i + 1).timeStep));
                countList++;
            }
            i++;
        }

        return list;
    }


}
