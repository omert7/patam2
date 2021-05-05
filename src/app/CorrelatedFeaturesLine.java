package app;

import app.model.statlib.Line;

public class CorrelatedFeaturesLine extends CorrelatedFeature{
   
    public final Line lin_reg;
    public final float threshold;

    public CorrelatedFeaturesLine(String feature1, String feature2, float corrlation, Line lin_reg, float threshold) {
       super( feature1, feature2, corrlation);
        this.lin_reg = lin_reg;
        this.threshold = threshold;
    }

}
