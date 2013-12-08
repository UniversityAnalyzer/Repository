/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package topuniversities;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Maarten
 */
public class LinearRegression {    
    
    public static double linearRegression(ArrayList<Double> x, ArrayList<Double> y, double z) {
        // Step 1: Count number of values
        int n = 0;
        if(x.size() == y.size()) {
            n = x.size();
        }
        else {
            // Houston we have a problem
        }        
        
        // Step 2: Find XY,X^2
        ArrayList<Double> xy = new ArrayList<Double>();
        ArrayList<Double> xx = new ArrayList<Double>();
        
        for(int i=0; i< n; i++) {
            xy.add(x.get(i) * y.get(i));
            xx.add(x.get(i) * x.get(i));
        }
        
        // Step 3: Find ΣX, ΣY, ΣXY, ΣX^2.
        double Σx = 0;
        for (int i = 0; i < x.size(); i++) {
            Σx = Σx + x.get(i);
        }
        double Σy = 0;
        for (int i = 0; i < y.size(); i++) {
            Σy = Σy + y.get(i);
        }
        double Σxy = 0;
        for (int i = 0; i < xy.size(); i++) {
            Σxy = Σxy + xy.get(i);
        }
        double Σxx = 0;
        for (int i = 0; i < xx.size(); i++) {
            Σxx = Σxx + xx.get(i);
        }
        
        // Step 4: Substitute in the above slope formula given. Slope(b) = (NΣXY - (ΣX)(ΣY)) / (NΣX^2 - (ΣX)^2)                 
        double b = ((n*Σxy) - (Σx*Σy))/(n*Σxx - (Σx*Σx));      
        
        // Step 5: Now, again substitute in the above intercept formula given. Intercept(a) = (ΣY - b(ΣX)) / N 
        double a = (Σy - (b*Σx))/n;
        
        // Step 6: Then substitute these values in regression equation formula. Regression Equation(y) = a + bx 
        Double regEqY = a + (b*z);
        
        if(regEqY.isNaN()){
            return regEqY;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String roundedRelative = df.format(regEqY);
        roundedRelative = roundedRelative.replace(',', '.');
        double result = Double.parseDouble(roundedRelative);
        
        return result;
    }
    
}
