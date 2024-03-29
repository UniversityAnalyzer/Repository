/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package topuniversities;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.commons.configuration.ConfigurationException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsClientException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsRequestException;
import radboud.UniversityParser;

/**
 *
 * @author Evertson
 */
public class CorrelationPanel extends javax.swing.JPanel {

    private DataExtractor de;
    private static ArrayList<Integer> faults = new ArrayList<Integer>();
    private boolean data_is_set = false;

    /**
     * Creates new form CorrelationPanel
     */
    public CorrelationPanel() {
        initComponents();
        correlationTable.setAutoCreateRowSorter(true);
    }

    public void setDataExtractor(DataExtractor de) {
        this.de = de;
        inputTable.setModel(new InputModel(de.getEntropies()));

    }

    private ArrayList<Double> convertToDoubles(int[] data) {
        ArrayList<Double> result = new ArrayList<Double>();
        for (int i = 0; i < data.length; i++) {
            Double d = Double.parseDouble(data[i] + "");
            result.add(d);
            System.out.println("Search volume " + de.getYears()[i] + " " + d);
        }

        return result;
    }

    private static class RenderCell extends DefaultTableCellRenderer {

        private final Double highestScore;
        private final Double lowestScore;

        RenderCell(final Double highestScore, final Double lowestScore) {
            if (highestScore != null) {
                this.highestScore = highestScore;
            } else {
                this.highestScore = 1.0;
            }

            if (lowestScore != null) {
                this.lowestScore = lowestScore;
            } else {
                this.lowestScore = 0.0;
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //LOG.log(Level.FINEST, "getTableCellRendererComponent({0}, {1}) called.", new Object[] {row, column});
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


            if (!isSelected) {
                ((JLabel) c).setOpaque(true);
                Float f = Float.parseFloat(table.getValueAt(row, 2).toString());
                if (f >= 0.0) {
                    c.setBackground(generateColor(f, Float.parseFloat(this.highestScore.toString()), Float.parseFloat(this.lowestScore.toString())));
                } else {
                    c.setBackground(generateColor(f, Float.parseFloat(this.highestScore.toString()), Float.parseFloat(this.lowestScore.toString())));
                }
            }
            return c;
        }

        private Color generateColor(float score, float highestScore, float lowestScore) {
            Color c = new Color(1.0F, 1.0F, 1.0F);
            if (score < 0.0F) {
                float ratio = Math.abs(score / lowestScore);
                c = new Color(1.0F, 1.0F - ratio, 1.0F - ratio);
            } else if (score > 0.0F) {
                //Color of highest scoring term should be same kind of blue as in
                //terms panel
                //float rratio = 0.75F - Math.abs(score / highestScore) * 0.75F;
                //float gratio = 0.31F - Math.abs(score / highestScore) * 0.31F;
                float ratio = Math.abs(score / highestScore);
                c = new Color(1.0F - ratio, 1.0F, 1.0F - ratio);
            }
            return c;
        }
    }

    private static class RenderCell2 extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //LOG.log(Level.FINEST, "getTableCellRendererComponent({0}, {1}) called.", new Object[] {row, column});
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


            for (int i = 0; i < faults.size(); i++) {
                if (faults.contains(row)) {
                    c.setForeground(Color.red);
                    return c;
                }
            }
            c.setForeground(Color.black);
            return c;
        }
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
        inputTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        correlationTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        calculateButton = new javax.swing.JButton();
        loadCorrelationButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        statisticsLabel = new javax.swing.JLabel();

        inputTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(inputTable);

        jLabel1.setText("Input String");

        correlationTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(correlationTable);

        jLabel2.setText("Output Correlation");

        calculateButton.setText("Calculate");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });

        loadCorrelationButton.setText("Load");
        loadCorrelationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadCorrelationButtonActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Statistics"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(statisticsLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(statisticsLabel)
                .addGap(0, 142, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 519, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadCorrelationButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(calculateButton))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(calculateButton)
                            .addComponent(loadCorrelationButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        ArrayList<University> entropies = de.getEntropies();
        UniversityParser up = new UniversityParser();
        for (int i = 0; i < inputTable.getRowCount(); i++) {
            entropies.get(i).setGTrendsName(inputTable.getValueAt(i, 1).toString());
        }
        for (int i = 0; i < entropies.size(); i++) {
            University u = entropies.get(i);
            try {
                String gname = u.getGTrendsName();
                int[] data = up.executeQuery(gname);
                if (data == null) {
                    faults.add(i);
                } else {
                    u.setGT(convertToDoubles(data));
                    u.setPearson(Correlation.Pearson(u.getAR(), u.getGT()));
                    u.setSpearman(Correlation.Spearman(u.getAR(), u.getGT()));
                    System.out.println((i + 1) + ". " + gname + " complete.");
                }

                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CorrelationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConfigurationException ex) {
                Logger.getLogger(CorrelationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(CorrelationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GoogleTrendsClientException ex) {
                Logger.getLogger(CorrelationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GoogleTrendsRequestException ex) {
                Logger.getLogger(CorrelationPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CorrelationPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       setTable(entropies);
       setStatistics(entropies);

    }//GEN-LAST:event_calculateButtonActionPerformed

    private void setStatistics(ArrayList<University> entropies){
        Integer pearsonPositive = 0;
        Integer pearsonNegative = 0;
        Integer spearmanPositive = 0;
        Integer spearmanNegative = 0;
        Integer ignored = 0;
        for(int i = 0; i < entropies.size(); i++){
            University u = entropies.get(i);
            if(u.getPearson() == 0.0){
                ignored++;
            }else{
                if(u.getPearson() > 0.0){
                    pearsonPositive++;
                }else{
                    pearsonNegative++;
                }
                if(u.getSpearman() > 0.0){
                    spearmanPositive++;
                }else{
                    spearmanNegative++;
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.#");
        Double pearsonPerc = (pearsonPositive.doubleValue() / (entropies.size() - ignored)) * 100.0;
        Double spearmanPerc = (spearmanPositive.doubleValue() / (entropies.size() - ignored)) * 100.0;
        String html = "<html><table><tr><td>Pearson positive</td><td>" + pearsonPositive + "</td></tr>";
        html += "<tr><td>Pearson negative</td><td>" + pearsonNegative + "</td></tr>";
        html += "<tr><td>Pearson percentage</td><td>" + df.format(pearsonPerc) + "%</td></tr>";
        html += "<tr><td>Spearman positive</td><td>" + spearmanPositive + "</td></tr>";
        html += "<tr><td>Spearman negative</td><td>" + spearmanNegative + "</td></tr>";
        html += "<tr><td>Spearman percentage</td><td>" + df.format(spearmanPerc) + "%</td></tr>";
        html += "<tr><td>Ignored</td><td>" + ignored + "</td></tr>";
        html += "</table></html>";
        statisticsLabel.setText(html);
    }
    
    private void setTable(ArrayList<University> entropies){
         correlationTable.setModel(new GTrendsModel(entropies));
        Double highestScore = -1.1;
        Double lowestScore = 1.1;
        for (int i = 0; i < entropies.size(); i++) {
            University u = entropies.get(i);
            if (u.getPearson() > highestScore) {
                highestScore = u.getPearson();
            }
            if (u.getPearson() < lowestScore) {
                lowestScore = u.getPearson();
            }
        }
        inputTable.setDefaultRenderer(String.class, new RenderCell2());
        correlationTable.setDefaultRenderer(String.class, new RenderCell(highestScore, lowestScore));
        correlationTable.setDefaultRenderer(Integer.class, new RenderCell(highestScore, lowestScore));
        correlationTable.setDefaultRenderer(Double.class, new RenderCell(highestScore, lowestScore));
    }
    
    private void loadCorrelationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadCorrelationButtonActionPerformed
        ArrayList<University> entropies = de.getEntropies();
        if(entropies.get(0).getPearson() == 0){
            JOptionPane.showMessageDialog(null, "No data to load", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            setTable(entropies);
            setStatistics(entropies);
        }
    }//GEN-LAST:event_loadCorrelationButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calculateButton;
    private javax.swing.JTable correlationTable;
    private javax.swing.JTable inputTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loadCorrelationButton;
    private javax.swing.JLabel statisticsLabel;
    // End of variables declaration//GEN-END:variables
}
