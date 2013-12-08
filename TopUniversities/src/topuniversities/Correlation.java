package topuniversities;

import java.util.ArrayList;
import java.util.Collections;


public class Correlation {
	
	public static double Pearson(ArrayList<Double> x, ArrayList<Double> y) {
		double ex = (1.0/x.size())*arrayListSum(x);		
		double ey = (1.0/y.size())*arrayListSum(y);
		double vx = (1.0/(x.size()-1))*arrayListSubtract(x, ex);
		double vy = (1.0/(y.size()-1))*arrayListSubtract(y, ey);
		return cov(x,ex,y,ey)/Math.sqrt(vx*vy);
	}
	
	public static double Spearman(ArrayList<Double> x, ArrayList<Double> y) {
		ArrayList<Double> xr = rank(x);
		ArrayList<Double> yr = rank(y);
		double ex = (1.0/xr.size())*arrayListSum(xr);		
		double ey = (1.0/yr.size())*arrayListSum(yr);
		double vx = (1.0/(xr.size()-1))*arrayListSubtract(xr, ex);
		double vy = (1.0/(yr.size()-1))*arrayListSubtract(yr, ey);
		return cov(xr,ex,yr,ey)/Math.sqrt(vx*vy);
	}			
	
	private static double arrayListSum(ArrayList<Double> arrayList) {
		double arrayListSum = 0.0;
		for(double d : arrayList) {
			arrayListSum += d;
		}
		return arrayListSum;
	}
	
	private static double arrayListSubtract(ArrayList<Double> arrayList, double value) {
		double arrayListSum = 0.0;
		for(double d : arrayList) {
			arrayListSum += Math.pow(d-value, 2);
		}		
		return arrayListSum;		
	}	
	
	private static double cov(ArrayList<Double> x, double ex, ArrayList<Double> y, double ey) {
		double temp = 0.0;
		for(int i = 0; i < x.size(); i++) {
			temp += ((x.get(i)-ex)*(y.get(i)-ey));
		}
		return (1.0/(x.size()-1))*temp;
	}
	
	private static ArrayList<Double> rank(ArrayList<Double> x) {
	    ArrayList<Double> sortedValues = new ArrayList<Double>(x);
	    Collections.sort(sortedValues);	 
	    ArrayList<Double> xr = new ArrayList<Double>();	 
	    for (int i=0; i<x.size(); i++)
	        xr.add(sortedValues.indexOf(x.get(i).doubleValue()) + 1.0);	 
	    return xr;
	}
}