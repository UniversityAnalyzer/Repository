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
public class InputModel extends DefaultTableModel{
    
    InputModel(ArrayList<University> universities) {
         super(grid(universities), headers());
    }
    
    private static Object[][] grid(ArrayList<University> universities) {
        final Object[][] g = new Object[universities.size()][];

        int row = 0;
        for (int i = 0; i < universities.size(); i++) {
            String name = universities.get(i).getName();
             final Object[] columns = new Object[2];
             columns[0] = i + 1;
             columns[1] = convertString(name);
            g[row++] = columns;
        }

        return g;
    }
    
     @Override
    public Class<?> getColumnClass(final int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            default:
                return super.getColumnClass(col);
        }
    } 
    
    private static String convertString(String s){
        if(s.contains("(")){
            int begin = s.indexOf("(");
            int end = s.indexOf(")");
            if(s.substring(begin, end).length() > s.substring(0, begin).length()){
                return s.substring(begin + 1, end);
            }
            return s.substring(0, begin);           
        }
        return s;    
    }
    
    private static String[] headers() {

        final String[] headers = new String[2];
        headers[0] = "Entropy Ranking";
        headers[1] = "Name";      
        return headers;
    }
    
    
    @Override
    public boolean isCellEditable(final int row, final int column) {
        if(column == 1){
            return true;
        }
        return false;
    }
    
}
