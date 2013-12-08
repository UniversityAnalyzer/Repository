/**
 * Copyright (C) 2013 Marco Tizzoni <marco.tizzoni@gmail.com>
 *
 * This file is part of j-google-trends-api
 *
 *     j-google-trends-api is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     j-google-trends-api is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with j-google-trends-api.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package topuniversities;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Evertson
 */
public class DataExtractorModel extends DefaultTableModel {
    
    
    
    DataExtractorModel(ArrayList<University> universities, int index) {
        super(grid(universities, index), headers(index));
    }
    
    
    
    
    private static Object[][] grid(ArrayList<University> universities, int index) {
        final Object[][] g = new Object[universities.size()][];

        int row = 0;
        for (int i = 0; i < universities.size(); i++) {
             final Object[] columns = new Object[3];
             columns[0] = i + 1;
             columns[1] = universities.get(i).getName();
             if(index == -1){
             columns[2] = universities.get(i).getSavedEntropy();  
             }else{
             columns[2] = universities.get(i).getAR().get(index);
             }
            g[row++] = columns;
        }

        return g;
    }
    
    
    private static String[] headers(int i) {

        final String[] headers = new String[3];
        headers[0] = "Ranking";
        headers[1] = "Name";
        if(i == -1){
           headers[2] = "Entropy"; 
        }else{
        headers[2] = "Academic Reputation";
        }
      
        return headers;
    }
    
    @Override
    public boolean isCellEditable(final int row, final int column) {
        return false;
    }
}
