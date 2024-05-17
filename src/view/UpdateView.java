/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import business.UavService;
import core.helper.InputHelper;
import entity.Uav;
import java.awt.Color;
import java.awt.Toolkit;

/**
 *
 * @author onurkuran
 */
public class UpdateView extends javax.swing.JFrame {

    private final UavService uavService;
    private final int id;
    /**
     * Creates new form SaveView
     * @param id
     */
    public UpdateView(int id) {
        initComponents();
        this.uavService = new UavService();
        
        this.id = id;
        
        Uav uav = this.uavService.getById(id);
                
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        
        this.txtCode.setText(uav.getCode());
        this.txtSpeed.setText(String.valueOf(uav.getSpeed()));
        this.txtBattery.setText(String.valueOf(uav.getBatteryPercentage()));
        this.txtAltitude.setText(String.valueOf(uav.getGeoPosition().getAltitude()));
        this.txtLatitude.setText(String.valueOf(uav.getGeoPosition().getLatitude()));
        this.txtLongitude.setText(String.valueOf(uav.getGeoPosition().getLongitude()));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblCode = new javax.swing.JLabel();
        lblGeoLoc = new javax.swing.JLabel();
        lblSpeed = new javax.swing.JLabel();
        lblBattery = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        txtLatitude = new javax.swing.JTextField();
        txtSpeed = new javax.swing.JTextField();
        txtBattery = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblLatitude = new javax.swing.JLabel();
        lblLongitude = new javax.swing.JLabel();
        lblAltitude = new javax.swing.JLabel();
        txtLongitude = new javax.swing.JTextField();
        txtAltitude = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblTitle.setText("Yeni Kayıt Oluştur");

        lblCode.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblCode.setText("Kod");

        lblGeoLoc.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblGeoLoc.setText("Coğrafi Lokasyon");

        lblSpeed.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblSpeed.setText("Hız");

        lblBattery.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblBattery.setText("Batarya");

        lblDescription.setText("Alanları eksiksiz biçimde doldurup kaydet butonuna basınız.");

        txtCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeActionPerformed(evt);
            }
        });

        txtLatitude.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLatitudeKeyTyped(evt);
            }
        });

        txtSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpeedActionPerformed(evt);
            }
        });
        txtSpeed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSpeedKeyTyped(evt);
            }
        });

        txtBattery.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBatteryKeyTyped(evt);
            }
        });

        btnCancel.setText("İptal");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });

        btnSave.setText("Kaydet");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });

        lblLatitude.setText("Enlem");

        lblLongitude.setText("Boylam");

        lblAltitude.setText("Yükseklik");

        txtLongitude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLongitudeActionPerformed(evt);
            }
        });
        txtLongitude.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLongitudeKeyTyped(evt);
            }
        });

        txtAltitude.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAltitudeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblGeoLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblBattery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblAltitude)
                                        .addComponent(lblLongitude)
                                        .addComponent(lblLatitude))
                                    .addGap(29, 29, 29)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtBattery, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLatitude, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLongitude, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAltitude, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(lblCode, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addGap(135, 135, 135))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(120, 120, 120))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCode)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSpeed)
                    .addComponent(txtSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBattery)
                    .addComponent(txtBattery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblGeoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLatitude))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLongitude)
                    .addComponent(txtLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAltitude)
                    .addComponent(txtAltitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSave))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodeActionPerformed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        String code = this.txtCode.getText();
        String latitude = this.txtLatitude.getText();
        String longitude = this.txtLongitude.getText();
        String altitude = this.txtAltitude.getText();
        String speed = this.txtSpeed.getText();
        String battery = this.txtBattery.getText();

        try {
            uavService.update(this.id, code, latitude, longitude, altitude, speed, battery);
            this.dispose();
        } catch (Exception ex) {
            lblDescription.setForeground(Color.red);
            lblDescription.setText(ex.getMessage());
        }
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCancelMouseClicked

    private void txtLongitudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLongitudeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLongitudeActionPerformed

    private void txtSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpeedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpeedActionPerformed

    private void txtSpeedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSpeedKeyTyped
        InputHelper.isDouble(evt);
    }//GEN-LAST:event_txtSpeedKeyTyped

    private void txtBatteryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBatteryKeyTyped
        InputHelper.isDouble(evt);
    }//GEN-LAST:event_txtBatteryKeyTyped

    private void txtLatitudeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLatitudeKeyTyped
        InputHelper.isDouble(evt);
    }//GEN-LAST:event_txtLatitudeKeyTyped

    private void txtLongitudeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLongitudeKeyTyped
        InputHelper.isDouble(evt);
    }//GEN-LAST:event_txtLongitudeKeyTyped

    private void txtAltitudeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAltitudeKeyTyped
        InputHelper.isDouble(evt);
    }//GEN-LAST:event_txtAltitudeKeyTyped

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
            java.util.logging.Logger.getLogger(SaveView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaveView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaveView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaveView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SaveView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblAltitude;
    private javax.swing.JLabel lblBattery;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblGeoLoc;
    private javax.swing.JLabel lblLatitude;
    private javax.swing.JLabel lblLongitude;
    private javax.swing.JLabel lblSpeed;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtAltitude;
    private javax.swing.JTextField txtBattery;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtLatitude;
    private javax.swing.JTextField txtLongitude;
    private javax.swing.JTextField txtSpeed;
    // End of variables declaration//GEN-END:variables
}
