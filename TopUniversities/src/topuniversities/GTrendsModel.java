/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package topuniversities;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Evertson
 */
public class GTrendsModel extends DefaultTableModel {
    
     GTrendsModel(ArrayList<University> universities) {
         super(grid(universities), headers());
    }
    
     private static Object[][] grid(ArrayList<University> universities) {
        final Object[][] g = new Object[universities.size()][];

        int row = 0;
        for (int i = 0; i < universities.size(); i++) {
            University u = universities.get(i);
             final Object[] columns = new Object[4];
             columns[0] = i + 1;
             columns[1] = u.getGTrendsName();
             columns[2] = u.getPearson();
             columns[3] = u.getSpearman();
            g[row++] = columns;
        }

        return g;
    }
     
     private static String[] headers() {

        final String[] headers = new String[4];
        headers[0] = "Entropy Ranking";
        headers[1] = "Name";
        headers[2] = "Pearson";
        headers[3] = "Spearman";
        return headers;
    }
     
     @Override
    public boolean isCellEditable(final int row, final int column) {
        return false;
    }
     
    @Override
    public Class<?> getColumnClass(final int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;
            default:
                return super.getColumnClass(col);
        }
    } 
     
}
