/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Duc Long
 */
public class PhanQuyenView extends javax.swing.JPanel {

    /**
     * Creates new form PhanQuyenView
     */
    public PhanQuyenView() {
        initComponents();
    }
    
    private void saveState() {
        String filePath = "checkboxAdmin.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(chkNhanVien.isSelected());
            writer.println(chkHoaDon.isSelected());
            writer.println(chkKhachHang.isSelected());
            writer.println(chkSP.isSelected());
            writer.println(chkThongKe.isSelected());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadState() {
        String filePath = "checkboxAdmin.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            chkNhanVien.setSelected(Boolean.parseBoolean(reader.readLine()));
            chkHoaDon.setSelected(Boolean.parseBoolean(reader.readLine()));
            chkKhachHang.setSelected(Boolean.parseBoolean(reader.readLine()));
            chkSP.setSelected(Boolean.parseBoolean(reader.readLine()));
            chkThongKe.setSelected(Boolean.parseBoolean(reader.readLine()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean getSPCheckboxState() {
        return chkSP.isSelected();
    }

    public boolean getNhanVienCheckboxState() {
        return chkNhanVien.isSelected();
    }

    public boolean getKhachHangCheckboxState() {
        return chkKhachHang.isSelected();
    }

    public boolean getHoaDonCheckboxState() {
        return chkHoaDon.isSelected();
    }

    public boolean getThongKeCheckboxState() {
        return chkThongKe.isSelected();
    }
    
    private void enableSP(){
        SanPhamView sp = new SanPhamView();
        sp.setEnabled(true);
    }
    
    private void enableKH(){
        KhachHangView kh = new KhachHangView();
        kh.setEnabled(true);
    }
    
    private void enableNV(){
        SanPhamView sp = new SanPhamView();
        sp.setEnabled(true);
    }
    
    private void enableQ(){
        QuyenView q = new QuyenView();
        q.setEnabled(true);
    }
    
    private void enableHD(){
        HoaDonView hd = new HoaDonView();
        hd.setEnabled(true);
    }
    
    private void enableTK(){
        TrangChuView tc = new TrangChuView();
        tc.setEnabled(true);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        chkSP = new javax.swing.JCheckBox();
        chkKhachHang = new javax.swing.JCheckBox();
        chkNhanVien = new javax.swing.JCheckBox();
        chkHoaDon = new javax.swing.JCheckBox();
        chkThongKe = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(980, 530));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("Phân Quyền Nhân Viên");

        chkSP.setSelected(true);
        chkSP.setText("Quản lý sản phẩm");
        chkSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkSPItemStateChanged(evt);
            }
        });
        chkSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSPActionPerformed(evt);
            }
        });

        chkKhachHang.setSelected(true);
        chkKhachHang.setText("Khách hàng");
        chkKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkKhachHangItemStateChanged(evt);
            }
        });

        chkNhanVien.setSelected(true);
        chkNhanVien.setText("Nhân viên");
        chkNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkNhanVienItemStateChanged(evt);
            }
        });

        chkHoaDon.setSelected(true);
        chkHoaDon.setText("Hóa đơn");
        chkHoaDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkHoaDonItemStateChanged(evt);
            }
        });

        chkThongKe.setSelected(true);
        chkThongKe.setText("Thống kê");
        chkThongKe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkThongKeItemStateChanged(evt);
            }
        });

        jButton2.setText("Lưu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hủy");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkSP, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(jButton2)
                        .addGap(49, 49, 49)
                        .addComponent(jButton3)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addGap(50, 50, 50)
                .addComponent(chkSP)
                .addGap(18, 18, 18)
                .addComponent(chkKhachHang)
                .addGap(18, 18, 18)
                .addComponent(chkNhanVien)
                .addGap(18, 18, 18)
                .addComponent(chkHoaDon)
                .addGap(18, 18, 18)
                .addComponent(chkThongKe)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1124, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(468, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(21, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkSPItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            enableSP();
        }
    }//GEN-LAST:event_chkSPItemStateChanged

    private void chkSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSPActionPerformed

    private void chkKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkKhachHangItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            enableKH();
        }
    }//GEN-LAST:event_chkKhachHangItemStateChanged

    private void chkNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkNhanVienItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            enableNV();
        }
    }//GEN-LAST:event_chkNhanVienItemStateChanged

    private void chkHoaDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkHoaDonItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            enableHD();
        }
    }//GEN-LAST:event_chkHoaDonItemStateChanged

    private void chkThongKeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkThongKeItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            enableTK();
        }
    }//GEN-LAST:event_chkThongKeItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        saveState();
        loadState();
        JOptionPane.showMessageDialog(this, "Đã lưu");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        loadState();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkHoaDon;
    private javax.swing.JCheckBox chkKhachHang;
    private javax.swing.JCheckBox chkNhanVien;
    private javax.swing.JCheckBox chkSP;
    private javax.swing.JCheckBox chkThongKe;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
