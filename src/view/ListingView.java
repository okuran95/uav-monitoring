/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import business.UavService;
import core.helper.ViewHelper;
import entity.Uav;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author onurkuran
 */
public class ListingView extends javax.swing.JFrame {

    DefaultTableModel model;
    private UavService uavService;
    String columnName;
    SortOrder sortOrder;

    /**
     * Creates new form ListingView
     */
    public ListingView() {
        initComponents();
        this.uavService = new UavService();

        this.uavService.createMockData();
        populate();

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        tblUav.setRowSorter(sorter);

        sorter.addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent e) {
                if (e.getType() == RowSorterEvent.Type.SORT_ORDER_CHANGED) {
                    List<? extends RowSorter.SortKey> sortKeys = sorter.getSortKeys();
                    if (!sortKeys.isEmpty()) {
                        RowSorter.SortKey sortKey = sortKeys.get(0);

                        int columnIndex = sortKey.getColumn();
                        columnName = tblUav.getColumnName(columnIndex);
                        if (columnName != null) {
                            changeSorting();
                            populate();
                        }
                        sorter.setSortKeys(null);
                    }

                }
            }
        });

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Object cellValue = table.getValueAt(row, 3);
                Double battery = Double.valueOf(cellValue.toString());

                if (battery < 25) {
                    setBackground(Color.ORANGE);
                } else {
                    setBackground(Color.WHITE);
                }

                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        for (int i = 0; i < tblUav.getColumnCount(); i++) {
            tblUav.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        Timer timer = new Timer(1000, (ActionEvent e) -> {
            for (int row = 0; row < model.getRowCount(); row++) {
                LocalTime value = (LocalTime) model.getValueAt(row, 5);
                Boolean status = (Boolean) model.getValueAt(row, 6);
                if (status && value.getHour() < 5) {
                    model.setValueAt(value.plusSeconds(1L), row, 5);
                }
            }
        });

        Timer batteryTimer = new Timer(60000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uavService.reduceBatteryPercantage();
                populate();

            }
        });

        timer.start();
        batteryTimer.start();

    }

    private void changeSorting() {
        sortOrder = sortOrder == SortOrder.ASCENDING ? SortOrder.DESCENDING : SortOrder.ASCENDING;
    }

    public void populate() {
        model = (DefaultTableModel) tblUav.getModel();
        model.setRowCount(0);
        List<Uav> uavs = this.uavService.getAll();

        List<Uav> firstList = new ArrayList<>();
        List<Uav> secondList = new ArrayList<>();

        for (Uav uav : uavs) {
            if (uav.getWarningStatus()) {
                firstList.add(uav);
            } else {
                secondList.add(uav);
            }
        }

        if (columnName != null) {
            Comparator<Uav> comparator = null;
            switch (columnName) {
                case "Kod" -> comparator = Comparator.comparing(Uav::getCode);
                case "Id" -> comparator = Comparator.comparingInt(Uav::getId);
                case "Lokasyon" -> comparator = Comparator.comparing(Uav -> Uav.getGeoPosition().getAsString());
                case "Batarya Durumu" -> comparator = Comparator.comparingDouble(Uav::getBatteryPercentage);
                case "Hız" -> comparator = Comparator.comparingDouble(Uav::getSpeed);
                case "Uçuş Süresi" -> comparator = Comparator.comparing(Uav::getDuration);
                case "Uçuş Durumu" -> comparator = Comparator.comparing(Uav::getFlightStatus);
                default -> {
                }
            }

            if (sortOrder == SortOrder.DESCENDING) {
                comparator = comparator.reversed();
            }

            Collections.sort(firstList, comparator);
            Collections.sort(secondList, comparator);
        }
        firstList.addAll(secondList);

        for (Uav uav : firstList) {

            Object[] row = {
                uav.getId(),
                uav.getCode(),
                uav.getGeoPosition().getAsString(),
                uav.getBatteryPercentage(),
                uav.getSpeed(),
                uav.getDuration(),
                uav.getFlightStatus()};

            model.addRow(row);
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
        tblUav = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblUav.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Kod", "Lokasyon", "Batarya Durumu", "Hız", "Uçuş Süresi", "Uçuş Durumu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblUav);
        if (tblUav.getColumnModel().getColumnCount() > 0) {
            tblUav.getColumnModel().getColumn(0).setResizable(false);
            tblUav.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblUav.getColumnModel().getColumn(1).setResizable(false);
            tblUav.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblUav.getColumnModel().getColumn(2).setResizable(false);
            tblUav.getColumnModel().getColumn(2).setPreferredWidth(130);
            tblUav.getColumnModel().getColumn(3).setResizable(false);
            tblUav.getColumnModel().getColumn(3).setPreferredWidth(5);
            tblUav.getColumnModel().getColumn(4).setResizable(false);
            tblUav.getColumnModel().getColumn(4).setPreferredWidth(5);
            tblUav.getColumnModel().getColumn(5).setResizable(false);
            tblUav.getColumnModel().getColumn(5).setPreferredWidth(20);
            tblUav.getColumnModel().getColumn(6).setResizable(false);
        }

        btnCreate.setText("Ekle");
        btnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreateMouseClicked(evt);
            }
        });

        btnUpdate.setText("Güncelle");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Sil");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnStop.setText("Durdur");
        btnStop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStopMouseClicked(evt);
            }
        });
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(btnStop, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseClicked
        SaveView saveView = new SaveView();
        saveView.setVisible(true);
        saveView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                populate();
            }
        });
    }//GEN-LAST:event_btnCreateMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        int id;
        if (tblUav.getSelectedRow() != -1) {
            id = (int) tblUav.getValueAt(tblUav.getSelectedRow(), 0);

            if (ViewHelper.confirm("Silmek istediğinize emin misiniz?")) {
                id = (int) tblUav.getValueAt(tblUav.getSelectedRow(), 0);
                uavService.delete(id);
                populate();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seçim yapmanız gerekmekte.", "HATA!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked

        int id;
        if (tblUav.getSelectedRow() != -1) {
            id = (int) tblUav.getValueAt(tblUav.getSelectedRow(), 0);
            UpdateView updateView = new UpdateView(id);
            updateView.setVisible(true);
            updateView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    populate();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Seçim yapmanız gerekmekte.", "HATA!", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnStopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStopMouseClicked
        int id;
        if (tblUav.getSelectedRow() != -1) {
            id = (int) tblUav.getValueAt(tblUav.getSelectedRow(), 0);

            if (ViewHelper.confirm("Durdurmak istediğinize emin misiniz?")) {
                id = (int) tblUav.getValueAt(tblUav.getSelectedRow(), 0);
                uavService.stop(id);
                populate();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seçim yapmanız gerekmekte.", "HATA!", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnStopMouseClicked

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStopActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListingView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUav;
    // End of variables declaration//GEN-END:variables
}
