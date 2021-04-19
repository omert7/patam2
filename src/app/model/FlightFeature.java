package app.model;

public class FlightFeature {
    private String featureName;
    private double min;
    private double max;

    public FlightFeature(String featureName, double min, double max) {
        this.featureName = featureName;
        this.min = min;
        this.max = max;
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
}
