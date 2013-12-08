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
public class PredictionModel extends DefaultTableModel {
    
    PredictionModel(ArrayList<University> universities) {
         super(grid(universities), headers());
    }
    
    private static Object[][] grid(ArrayList<University> universities) {
        final Object[][] g = new Object[universities.size()][];

        int row = 0;
        for (int i = 0; i < universities.size(); i++) {
            University u = universities.get(i);
             final Object[] columns = new Object[7];
             columns[0] = i + 1;
             columns[1] = u.getGTrendsName();
             columns[2] = u.getPredictedG();
             columns[3] = u.getTop2013();
             columns[4] = u.getPredictedTop();
             columns[5] = u.getSLP();
             columns[6] = (getDeviation(u.getTop2013(), u.getSLP()) - getDeviation(u.getTop2013(),u.getPredictedTop()));       
            g[row++] = columns;
        }

        return g;
    }

    
    private static Double getDeviation(Double a, Double b){
        Double result = Math.abs(a - b);
        if(!result.isNaN()){
        DecimalFormat df = new DecimalFormat("#.##");
        String roundedRelative = df.format(result);
        roundedRelative = roundedRelative.replace(',', '.');
        result = Double.parseDouble(roundedRelative);
        }
        return result;
    }
    
    private static String[] headers() {

        final String[] headers = new String[7];
        headers[0] = "Entropy Ranking";
        headers[1] = "Name";
        headers[2] = "Gtrends";
        headers[3] = "2013 AR";
        headers[4] = "Prediction";
        headers[5] = "S-Prediction";
        headers[6] = "Deviation";
        return headers;
    }
    
     @Override
    public Class<?> getColumnClass(final int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;
            case 4:
                return Double.class;
            case 5:
                return Double.class;
            case 6:
                return Double.class;
            default:
                return super.getColumnClass(col);
        }
    } 
     
     @Override
    public boolean isCellEditable(final int row, final int column) {

        return false;
    }
    
}
