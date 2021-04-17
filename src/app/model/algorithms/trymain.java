package app.model.algorithms;

import java.util.ArrayList;

import app.Point;
import app.TimeSeries;

public class trymain {

	public static void main(String[] args) {


		TimeSeries ts=new TimeSeries("try1.csv");
		ZScore z=new ZScore();
		z.learnNormal(ts);
		for(int i = 0; i<z.maxZScore.size(); i++)
			System.out.println("In colom: "+ ts.namesOfFeatures.get(i+1)+ " the max z score is "+z.maxZScore.get(i));
		ArrayList<Point> p=new ArrayList<Point>();
		p.add(new Point(5,-2));
		p.add(new Point(-3,-2));
		p.add(new Point(-2,5));
		p.add(new Point(1,6));
		p.add(new Point(0,2));
		Circle c=SmallestEnclosingCircle.makeCircle(p);
		System.out.println(c.r);
		System.out.println(c.c);







	}





}
