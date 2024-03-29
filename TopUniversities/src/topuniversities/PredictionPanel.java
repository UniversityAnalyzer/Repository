/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package topuniversities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.configuration.ConfigurationException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsClientException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsRequestException;
import radboud.UniversityParser;

/**
 *
 * @author Evertson
 */
public class PredictionPanel extends javax.swing.JPanel {

    private DataExtractor de;

    /**
     * Creates new form PredictionPanel
     */
    public PredictionPanel() {
        initComponents();
        predictionTable.setAutoCreateRowSorter(true);
    }

    public void setDataExtractor(DataExtractor de) {
        this.de = de;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        predictionTable = new javax.swing.JTable();
        predictButton = new javax.swing.JButton();
        loadPredictionButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        statisticsLabel = new javax.swing.JLabel();

        predictionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(predictionTable);

        predictButton.setText("Predict");
        predictButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predictButtonActionPerformed(evt);
            }
        });

        loadPredictionButton.setText("Load");
        loadPredictionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPredictionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(statisticsLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(statisticsLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 176, Short.MAX_VALUE)
                        .addComponent(loadPredictionButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(predictButton))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(predictButton)
                    .addComponent(loadPredictionButton))
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public ArrayList<Double> convertYears(Integer[] years) {
        ArrayList<Double> result = new ArrayList<Double>();
        for (int i = 0; i < years.length; i++) {
            Double temp = years[i].doubleValue();
            result.add(temp);
        }
        return result;
    }

    private void predictButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predictButtonActionPerformed
        ArrayList<University> entropies = de.getEntropies();
        UniversityParser up = new UniversityParser();
        for (int i = 0; i < entropies.size(); i++) {
            try {
                University u = entropies.get(i);
                u.setPredictedG(up.execute2013Query(u.getGTrendsName()));
                u.setPredictedTop(LinearRegression.linearRegression(u.getGT(), u.getAR(), u.getPredictedG()));
                u.setSLP(LinearRegression.linearRegression(convertYears(de.getYears()), u.getAR(), 2013.00));
                System.out.println((i + 1) + ". " + u.getName() + " complete");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PredictionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConfigurationException ex) {
                Logger.getLogger(PredictionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(PredictionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GoogleTrendsClientException ex) {
                Logger.getLogger(PredictionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GoogleTrendsRequestException ex) {
                Logger.getLogger(PredictionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PredictionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        predictionTable.setModel(new PredictionModel(entropies));
        setStatistics();
    }//GEN-LAST:event_predictButtonActionPerformed

    private void setStatistics() {
        int positive = 0;
        int negative = 0;
        int ignored = 0;
        for (int i = 0; i < predictionTable.getRowCount(); i++) {
            String deviation = predictionTable.getModel().getValueAt(i, 6).toString();
            Double test = Double.parseDouble(deviation);
            if (test == 0.0) {
                ignored++;
            } else {
                if (test > 0) {
                    positive++;
                } else {
                    negative++;
                }
            }
        }

        String html = "<html><table><tr><td>Better</td><td>" + positive + "</td></tr>";
        html += "<tr><td>Worse</td><td>" + negative + "</td></tr>";
        html += "<tr><td>Ignored</td><td>" + ignored + "</td></tr>";
        html += "</table></html>";
        statisticsLabel.setText(html);
    }

    private void loadPredictionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadPredictionButtonActionPerformed
        ArrayList<University> entropies = de.getEntropies();

        if (entropies.get(0).getPredictedG() != 0.0) {
            predictionTable.setModel(new PredictionModel(entropies));
            setStatistics();
        } else {
            JOptionPane.showMessageDialog(null, "No data to load", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loadPredictionButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadPredictionButton;
    private javax.swing.JButton predictButton;
    private javax.swing.JTable predictionTable;
    private javax.swing.JLabel statisticsLabel;
    // End of variables declaration//GEN-END:variables
}
