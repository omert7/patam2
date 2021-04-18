package app.model.algorithms;

import java.util.List;

import app.AnomalyReport;

public interface TimeSeriesAnomalyDetector 
{
	void learnNormal(TimeSeries ts);
	List<AnomalyReport> detect(TimeSeries ts);
}
