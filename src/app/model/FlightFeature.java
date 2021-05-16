package app.model;

public class FlightFeature {
    private String featureName;
    private double min;
    private double max;
    private int featureIndex;

    public FlightFeature(String featureName, double min, double max, int featureIndex) {
        this.featureName = featureName;
        this.min = min;
        this.max = max;
        this.featureIndex = featureIndex;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public int getFeatureIndex() {
        return featureIndex;
    }

    public void setFeatureIndex(int featureIndex) {
        this.featureIndex = featureIndex;
    }

}
