package app;

import app.model.algorithms.Circle;

public class CorrelatedFeatureCircle extends CorrelatedFeature {
public final Circle c;

	public CorrelatedFeatureCircle(String feature1, String feature2, float corrlation, Circle c) {
		super(feature1, feature2, corrlation);
		this.c = c;
	}

}
