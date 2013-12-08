/**
 * Copyright (C) 2013 Marco Tizzoni <marco.tizzoni@gmail.com>
 *
 * This file is part of j-google-trends-api
 *
 * j-google-trends-api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * j-google-trends-api is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * j-google-trends-api. If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package topuniversities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Evertson
 */
public class DataExtractor {

    private Integer[] years = {2007, 2008, 2009, 2010, 2011, 2012};
    private int[] ids = {
        4179, 2547, 2249, 2820, 2803, 2106, 9069, 9201, 2274, 2248,
        4546, 2382, 4465, 2291, 2296, 2641, 2332, 9109, 2329, 2805,
        4165, 4172, 2320, 4234, 2238, 2563, 2194, 2328, 4265, 2223,
        4168, 9105, 4158, 2564, 2721, 5052, 9195, 9606, 2540, 2241,
        2330, 4208, 4576, 4246, 2294, 2204, 2226, 9116, 2224, 2553,
        2495, 4244, 9089, 4193, 4405, //3969, 
        2255, 2173, 9183, 2708,
        2319, 2213, 2243, 9180, 4189, 9103, 2838, 2817, 4181, 2554,
        4565, 2453, 9094, 2762, 4274, 9101, 2259, 9267, 2217, 9139,
        9150, 4228, 9066, 9190, 2239, 9063, 9184, 2448, 4412, 2322,
        2161, 2357, 4186, 2191, 2308, 2170, 2525, 2752, 2467, 4197,
      4550, 2439, 4177, 2283, 2165, 4474, 4464, 4535, 2433, 2200,
      2748, 4495, 4291, 9264, 9265, 2811, 2661, 4162, 4552, 2305,
      4187, 2850, 2487, 5318, 9065, 2568, 4833, 2506, 4251, 2245,
      2788, 9166, 2730, 9119, 9087, 2253, 4620, 2260, 2318, 4166,
      2343, 2550, 4255, 2560, 2711, 2252, 2159, 2162, 2240, 9173        
    };
    private Double[] d_2010 = {100.0, 100.0, 100.0,  99.0, 100.0, 100.0, 100.0, 100.0, 100.0, 99.0,
                               100.0,  99.0,  98.0, 100.0, 100.0,  96.0,  99.0, 100.0,  74.0, 92.0,
                                99.0, 100.0, 95.00, 100.0, 100.0,  96.0, 100.0,  95.0,  90.0, 90.0,
                               100.0, 100.0,  98.0,  86.0, 100.0,  96.0,  94.0,  99.0,  88.0, 100.0,
                                75.0,  82.0,  90.0,  94.0,  87.0, 100.0,  87.0,  98.0, 100.0,  93.0,
                                77.0,  95.0,  78.0,  78.0,  84.0,  //96.0,  
                                89.0,  94.0,  91.0,  81.0,
                                86.0,  81.0,  98.0,  92.0,  94.0,  75.0,  85.0,  88.0,  95.0,  83.0,
                                72.0,  63.0,  96.0,  83.0,  76.0,  59.0,  84.0,  75.0,  76.0,  85.0,
                                82.0,  94.0,  60.0,  71.0,  71.0,  71.0,  54.0,  85.0,  72.0,  66.0,
                                70.0,  52.0,  66.0,  94.0,  72.0,  79.0,  61.0,  80.0,  64.0,  66.0,
                                78.0,  73.0,  81.0,  62.0,  69.0,  47.0,  74.0,  49.0,  89.0,  60.0,
                                41.0,  71.0,  65.0,  70.0,  66.0,  59.0,  49.0,  67.0,  53.0,  45.0,
                                83.0,  41.0,  54.0,  69.0,  57.0,  91.0,  58.0,  58.0,  41.0,  82.0,
                                52.0,  52.0,  44.0,  64.0,  34.0,  61.0,  48.0,  70.0,  67.0,  59.0,
                                42.0,  72.0,  35.0,  41.0,  67.0,  54.0,  51.0,  58.0,  64.0,  37.0};

    private ArrayList<University> universities = new ArrayList<University>();
    private ArrayList<University> entropy;

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        new DataExtractor();
//    }
    public Integer[] getYears() {
        return this.years;
    }

    public ArrayList<University> getEntropies() {
        return this.entropy;
    }

    public ArrayList<University> getUniversities() {
        return this.universities;
    }

    private void createUniversities() {
        for (int i = 0; i < ids.length; i++) {
            universities.add(new University(ids[i], i));
        }
    }

    public DataExtractor(ArrayList<University>[] data) {
        this.universities = data[0];
        this.entropy = data[1];
//        System.out.println("--Entropy List--");
//        entropy = getEntropyList();
//        printUniversityList(entropy);
    }

    public DataExtractor() {
        createUniversities();
        for (int i = 0; i < years.length; i++) {
            System.out.println("--" + years[i] + "--");
            for (int j = 0; j < ids.length; j++) {
                try {
                    System.out.println((j + 1) + ". " + extractData(ids[j], years[i], universities.get(j)));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        System.out.println("--" + 2013 + "--");
        for (int i = 0; i < ids.length; i++) {
            try {              
              System.out.println((i + 1) + ". " + extractData(ids[i], 2013, universities.get(i)));
            } catch (IOException ex) {
                Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("--Entropy List--");
        entropy = getEntropyList();
        printUniversityList(entropy);
        //printLowestEntropy();
    }

    private String extractData(int id, int year, University u) throws IOException {
        String result = "";
        if (year == 2010) {
            result = get2010Data(id, u);
        } else {

            String url = "http://www.topuniversities.com/node/" + id + "/ranking-details/world-university-rankings/" + year;
            Document doc = Jsoup.connect(url).timeout(0).get();
            String title = doc.getElementById("page-title").text();
            title = title.substring(0, (title.length() - 8)).trim();
            String ar = doc.getElementsByClass("field-items").get(0).text();
            if (year == 2013) {
                u.setTop2013(Double.parseDouble(ar));
            } else {
                u.insertReputation(Double.parseDouble(ar));
            }
            u.setName(title);
            result = title + " academic reputation: " + ar;
        }
        return result;
    }

    private String get2010Data(int id, University u) {
        String result = "";
        u.insertReputation(d_2010[u.getIndex()]);
        result = u.getName() + " academic reputation: " + d_2010[u.getIndex()];
        return result;
    }

    private ArrayList<University> getEntropyList() {
        ArrayList<University> result = new ArrayList<University>();
        ArrayList<University> copy = new ArrayList<University>(universities);
        while (!copy.isEmpty()) {
            result.add(getLowestEntropy(copy));
        }
        return result;
    }

    private University getLowestEntropy(ArrayList<University> list) {
        Double lowest = 100.0;
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            Double entropy = list.get(i).getEntropy();
            if (entropy < lowest) {
                lowest = entropy;
                index = i;
            }
        }
        University low = list.get(index);
        list.remove(low);
        return low;
    }

    private void printUniversityList(ArrayList<University> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getName() + " " + list.get(i).getID());
        }
    }
}
