/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package topuniversities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Evertson
 */
public class TrendsModel extends DefaultTableModel {
    
    TrendsModel(Double[] data, Integer[] years) {
        super(grid(data, years), headers());
    }
    
    private static Object[][] grid(Double[] data, Integer[] years) {
        final Object[][] g = new Object[years.length][];

        int row = 0;
        ArrayList<Double> rel = new ArrayList<Double>();
        rel = getRel(data);

        for (int i = 0; i < years.length; i++) {
            final Object[] columns = new Object[3];
            columns[0] = years[i];
            columns[1] = data[i];
            columns[2] = rel.get(i);
            g[row++] = columns;
        }

        return g;
    }
    
     private static String[] headers() {

        final String[] headers = new String[3];
        headers[0] = "Years";
        headers[1] = "vol";
        headers[2] = "Rel%";
        return headers;
    }
     
     private static ArrayList<Double> getRel(Double[] data) {
        ArrayList<Double> result = new ArrayList<Double>();
        Double highest = 0.0;
        Double lowest = 101.0;
        for (int i = 0; i < data.length; i++) {
            Double rep = data[i];
            if (rep > highest) {
                highest = rep;
            }
            if (rep < lowest) {
                lowest = rep;
            }
        }
        for (int i = 0; i < data.length; i++) {
            Double rep = data[i];
            DecimalFormat df = new DecimalFormat("#.##");
            Double relative = 0.0;
            if (highest - lowest == 0.0) {
                relative = 100.0;
            } else {
                String roundedRelative = df.format(((rep - lowest) / (highest - lowest)) * 100.0);
                roundedRelative = roundedRelative.replace(',', '.');
                relative = Double.parseDouble(roundedRelative);
            }
            result.add(relative);
        }
        return result;
    } 
     
     @Override
    public boolean isCellEditable(final int row, final int column) {
        return false;
    }
    
}
