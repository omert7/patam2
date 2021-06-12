package app.model.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import app.model.statlib.StatLib;
import app.AnomalyReport;


public class ZScore implements TimeSeriesAnomalyDetector {


   public HashMap<String, Float> hashMap;

    //fix Zscore!

    public ZScore() {

        hashMap = new HashMap<>();
    }

    static public float findZScore(float[] colom, int size) {

        if (size <= 1)
            return 0;
        float[] arr =Arrays.copyOfRange(colom, 0, size-1);
       /* for (int j = 0; j < size-1; j++) {
            arr[j] = colom[j];
        }*/
        float temp = StatLib.var(arr);
        float stia = (float) Math.pow(temp, 0.5);
        if(stia==0) {
        	return 0;
        }
        float avg = StatLib.avg(arr);
        return Math.abs((colom[size - 1] - avg) / stia);
    }

    static public float findZmax(float[] fs) {
        if (fs.length < 0)
            return -1;
        float max = findZScore(fs, 1);
        float temp;
        for (int i = 2; i <= fs.length; i++) {
            temp = findZScore(fs, i);
            if (temp > max)
                max = temp;
        }
        return max;
    }

    @Override
    public void learnNormal(TimeSeries ts) {

        for (String col : ts.namesOfFeatures) {

        	this.hashMap.put(col, findZmax(ts.dataOfFeatureByName(col)));

        }
       

    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        // TODO Auto-generated method stub
        float tempZScore;
        List<AnomalyReport> list = new ArrayList<AnomalyReport>();
        int totalTime = ts.totalTime;
        for (String key : hashMap.keySet()) {

            for (int j = 0; j < totalTime; j++) {
                tempZScore = findZScore(ts.dataOfFeatureByName(key), j);
                if (tempZScore > hashMap.get(key))//we detect problem
                {
                    list.add(new AnomalyReport(key, j + 1));
                }

            }

        }

        return list;
    }


}
