/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import Model.ChatLieu;
import Model.DanhMuc;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.KichCo;
import Model.MauSac;
import Model.NhanVien;
import Model.PhuongThucTT;
import Model.SanPham;
import Model.ThongKe;
import Service.poloService;
import Validate.Validate;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.TableCellListener;
import Repository.MsgBox;
import Repository.XQR;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.ProcessBuilder.Redirect.to;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JOptionPane;
//import org.apache.logging.log4j.message.Message;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Duc Long
 */
public class KhachHangView extends javax.swing.JPanel {

    private boolean isSPfilter1;
    private boolean isSPfilter2;
    private boolean isSPfilter3;
    private boolean isSPfilter4;
    
    public KhachHangView() {
        initComponents();
        loadDataKH(PLS.getListKH());

    }

    // <editor-fold defaultstate="collapsed" desc="Panel Khach Hang">
    private void loadDataKH(ArrayList<KhachHang> khlist) {
        DefaultTableModel dtm = (DefaultTableModel) tblKH.getModel();
        dtm.setRowCount(0);
        for (KhachHang kh : khlist) {
            dtm.addRow(new Object[]{
                kh.getId(),
                kh.getTenKhachHang(),
                kh.getSoDienThoai()
            });
        }
    }

    public KhachHang getModelKH() {
        KhachHang kh = new KhachHang();
        kh.setId(Integer.valueOf(txtIDKH.getText()));
        kh.setTenKhachHang(txtTenKH.getText());
        kh.setSoDienThoai(txtSDT.getText());
        return kh;
    }

    public void fillModelKH(KhachHang kh) {
        txtTenKH.setText(kh.getTenKhachHang());
        txtSDT.setText(kh.getSoDienThoai());
        txtIDKH.setText(kh.getId().toString());
    }

    private void clearKH() {
        txtTenKH.setText("");
        txtSDT.setText("");
        txtIDKH.setText("0");
    }

    private void addKH() {
        String kq = PLS.addKH(getModelKH());
        JOptionPane.showMessageDialog(this, kq);
        loadDataKH(PLS.getListKH());
        clearKH();
    }

    private void updateKH() {
        String kq = PLS.updateKH(getModelKH());
        JOptionPane.showMessageDialog(this, kq);
        loadDataKH(PLS.getListKH());
        clearKH();
    }

    private void deleteKH() {
        String kq = PLS.deleteKH(getModelKH());
        JOptionPane.showMessageDialog(this, kq);
        loadDataKH(PLS.getListKH());
        clearKH();
    }

    private void searchKHbyName() {
        String name = txtTimKH.getText();
        ArrayList<KhachHang> khlistName = PLS.getListKHbyName(name);
        loadDataKH(khlistName);
    }// </editor-fold>
    

    public boolean ValidateKH() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isEmpty(txtTenKH, stb, "Chưa nhập tên khách hàng");
        v.isEmpty(txtSDT, stb, "Chưa nhập SĐT khách hàng");
        v.isPhone(txtSDT, stb, "Không đúng SĐT");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    public boolean ValidateTimKH() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isEmpty(txtTimKH, stb, "Chưa nhập tên khách hàng");
        v.isString(txtTimKH, stb, "Vui lòng nhập tên");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    boolean isNVSearch, isKHSearch, isTKDateSearch, isTKIDSearch, isSPfilter = false;
    poloService PLS = new poloService();
    NhanVien nhanVien = null;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelKH = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();
        txtTimKH = new javax.swing.JTextField();
        btnTimKH = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        btnThemKhachHang = new javax.swing.JButton();
        btnSuaKhachHang = new javax.swing.JButton();
        btnXoaKhachHang = new javax.swing.JButton();
        btnResetKhachHang = new javax.swing.JButton();
        txtIDKH = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "SĐT"
            }
        ));
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblKH);

        btnTimKH.setText("Tìm theo tên");
        btnTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKHActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel40.setText("Tìm khách hàng");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel41.setText("Thêm mới KH");

        jLabel42.setText("Tên khách hàng:");

        jLabel43.setText("SĐT:");

        btnThemKhachHang.setText("Thêm");
        btnThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhachHangActionPerformed(evt);
            }
        });

        btnSuaKhachHang.setText("Sửa");
        btnSuaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKhachHangActionPerformed(evt);
            }
        });

        btnXoaKhachHang.setText("Xoá");
        btnXoaKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhachHangActionPerformed(evt);
            }
        });

        btnResetKhachHang.setText("Reset");
        btnResetKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetKhachHangActionPerformed(evt);
            }
        });

        txtIDKH.setEditable(false);
        txtIDKH.setText("0");
        txtIDKH.setEnabled(false);

        jLabel19.setText("ID:");

        javax.swing.GroupLayout panelKHLayout = new javax.swing.GroupLayout(panelKH);
        panelKH.setLayout(panelKHLayout);
        panelKHLayout.setHorizontalGroup(
            panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKHLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelKHLayout.createSequentialGroup()
                            .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(btnTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel41)
                        .addComponent(txtTenKH)
                        .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                        .addComponent(txtIDKH))
                    .addGroup(panelKHLayout.createSequentialGroup()
                        .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThemKhachHang)
                            .addComponent(btnXoaKhachHang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResetKhachHang)
                            .addComponent(btnSuaKhachHang))))
                .addGap(65, 65, 65))
        );
        panelKHLayout.setVerticalGroup(
            panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKHLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKH)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelKHLayout.createSequentialGroup()
                        .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(12, 12, 12)
                        .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemKhachHang)
                            .addComponent(btnSuaKhachHang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetKhachHang)
                            .addComponent(btnXoaKhachHang))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        int index = tblKH.getSelectedRow();
        if (isKHSearch) {
            String name = txtTimKH.getText();
            KhachHang kh = PLS.getListKHbyName(name).get(index);
            fillModelKH(kh);
        } else {
            KhachHang kh = PLS.getListKH().get(index);
            fillModelKH(kh);
        }
    }//GEN-LAST:event_tblKHMouseClicked

    private void btnTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKHActionPerformed
        if (ValidateTimKH()) {
            searchKHbyName();
            isKHSearch = true;
        }
    }//GEN-LAST:event_btnTimKHActionPerformed

    private void btnThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhachHangActionPerformed
        if (ValidateKH()) {
            addKH();
            isKHSearch = false;
        }
    }//GEN-LAST:event_btnThemKhachHangActionPerformed

    private void btnSuaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKhachHangActionPerformed
        if (ValidateKH()) {
            updateKH();
            isKHSearch = false;
        }
    }//GEN-LAST:event_btnSuaKhachHangActionPerformed

    private void btnXoaKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhachHangActionPerformed
        deleteKH();
        isKHSearch = false;
    }//GEN-LAST:event_btnXoaKhachHangActionPerformed

    private void btnResetKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetKhachHangActionPerformed
        clearKH();
        isKHSearch = false;
        loadDataKH(PLS.getListKH());
    }//GEN-LAST:event_btnResetKhachHangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetKhachHang;
    private javax.swing.JButton btnSuaKhachHang;
    private javax.swing.JButton btnThemKhachHang;
    private javax.swing.JButton btnTimKH;
    private javax.swing.JButton btnXoaKhachHang;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel panelKH;
    private javax.swing.JTable tblKH;
    private javax.swing.JTextField txtIDKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKH;
    // End of variables declaration//GEN-END:variables
}
