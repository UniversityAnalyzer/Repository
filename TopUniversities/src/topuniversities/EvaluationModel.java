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

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Evertson
 */
public class EvaluationModel extends DefaultTableModel {

    EvaluationModel(University university, Integer[] years) {
        super(grid(university, years), headers());
    }

    private static Object[][] grid(University university, Integer[] years) {
        final Object[][] g = new Object[years.length][];

        int row = 0;
        ArrayList<Double> rel = new ArrayList<Double>();
        if (university.getRel().isEmpty()) {
            rel = getRel(university);
        } else {
            rel = university.getRel();
        }
        for (int i = 0; i < years.length; i++) {
            final Object[] columns = new Object[3];
            columns[0] = years[i];
            columns[1] = university.getAR().get(i);
            columns[2] = rel.get(i);
            g[row++] = columns;
        }

        return g;
    }

    private static ArrayList<Double> getRel(University university) {
        ArrayList<Double> result = new ArrayList<Double>();
        ArrayList<Double> ar = university.getAR();
        Double highest = 0.0;
        Double lowest = 101.0;
        for (int i = 0; i < ar.size(); i++) {
            Double rep = ar.get(i);
            if (rep > highest) {
                highest = rep;
            }
            if (rep < lowest) {
                lowest = rep;
            }
        }
        for (int i = 0; i < ar.size(); i++) {
            Double rep = ar.get(i);
            DecimalFormat df = new DecimalFormat("#.##");
            Double relative = 0.0;
            if (highest - lowest == 0.0) {
                relative = 100.0;
            } else {
                String roundedRelative = df.format(((rep - lowest) / (highest - lowest)) * 100.0);
                roundedRelative = roundedRelative.replace(',', '.');
                relative = Double.parseDouble(roundedRelative);
                university.getRel().add(relative);
            }
            result.add(relative);
        }
        return result;
    }

    private static String[] headers() {

        final String[] headers = new String[3];
        headers[0] = "Years";
        headers[1] = "Rep";
        headers[2] = "Rel%";
        return headers;
    }

    @Override
    public boolean isCellEditable(final int row, final int column) {
        return false;
    }
}
