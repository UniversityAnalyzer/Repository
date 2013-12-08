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

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Evertson
 */
public class University implements Serializable {

    private int id;
    private ArrayList<Double> ar = new ArrayList<Double>();
    private ArrayList<Double> rel = new ArrayList<Double>();
    private ArrayList<Double> gt = new ArrayList<Double>();
    private String name = "";
    private Double entropy = 0.0;
    private int index;
    private String gtrendsName = "";
    private Double pearson = 0.0;
    private Double spearman = 0.0;
    private Double predicted_gtrends = 0.0;
    private Double predicted_top = 0.0;
    private Double top_2013;
    private Double standard_linear_prediction = 0.0;
    

    public University(int id, int index) {
        this.id = id;
        this.index = index;
    }
    
    public void setSLP(Double slp){
        this.standard_linear_prediction = slp;
    }

    public Double getSLP(){
        return this.standard_linear_prediction;
    }
    
    public void setTop2013(Double top_2013){
        this.top_2013 = top_2013;
    }
    
    public Double getTop2013(){
        return this.top_2013;
    }
    
    public Double getPearson() {
        return this.pearson;
    }
    
    public Double getPredictedG(){
        return this.predicted_gtrends;
    }
    
    public void setPredictedG(Double predicted_gtrends){
        this.predicted_gtrends = predicted_gtrends;
    }
    
    public Double getPredictedTop(){
        return this.predicted_top;
    }
    
    public void setPredictedTop(Double predicted_top){
        this.predicted_top = predicted_top;
    }

    
    
    public void setPearson(Double pearson) {
        if (pearson.isNaN()) {
           // this.pearson = pearson;
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            String temp = df.format(pearson);
            temp = temp.replace(',', '.');
            this.pearson = Double.parseDouble(temp);
        }
    }

    public Double getSpearman() {
        return this.spearman;
    }

    public void setSpearman(Double spearman) {
        if (spearman.isNaN()) {
            //this.spearman = spearman;
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            String temp = df.format(spearman);
            temp = temp.replace(',', '.');
            this.spearman = Double.parseDouble(temp);
        }
    }

    public String getGTrendsName() {
        return this.gtrendsName;
    }

    public void setGTrendsName(String gtrendsName) {
        this.gtrendsName = gtrendsName;
    }

    public int getIndex() {
        return this.index;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Double> getRel() {
        return this.rel;
    }

    public ArrayList<Double> getAR() {
        return this.ar;
    }

    public ArrayList<Double> getGT() {
        return this.gt;
    }

    public void setGT(ArrayList<Double> gt) {
        this.gt = gt;
    }

    public void insertReputation(Double d) {
        ar.add(d);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getEntropy() {
        Double result = 0.0;
        Double all = 0.0;
        for (int i = 0; i < ar.size(); i++) {
            all += ar.get(i);
        }

        for (int i = 0; i < ar.size(); i++) {
            result += ((ar.get(i) / all) * Math.log((ar.get(i) / all)));
        }
        result = Math.abs(result);
        this.entropy = result;
        return result;
    }

    public Double getSavedEntropy() {
        return this.entropy;
    }
}
