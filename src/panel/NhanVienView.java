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
public class NhanVienView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    public NhanVienView() {
        initComponents();
        loadDataNV(PLS.getListNV());
    }

    boolean isNVSearch, isKHSearch, isTKDateSearch, isTKIDSearch, isSPfilter = false;
    poloService PLS = new poloService();
    NhanVien nhanVien = null;

    public void setSessionUser(NhanVien nv) {
        getSessionUser(nv);
        txtTenNhanVienHTai.setText(nv.getTenNhanVien());
        txtIDNhanVienHTai.setText(nv.getMaNV().toString());
    }

    public void getSessionUser(NhanVien nv) {
        this.nhanVien = nv;
    }

    // <editor-fold defaultstate="collapsed" desc="Panel Nhan Vien">   
    private void loadDataNV(ArrayList<NhanVien> nvlist) {
        DefaultTableModel dtm = (DefaultTableModel) tblNV.getModel();
        dtm.setRowCount(0);
        for (NhanVien nv : nvlist) {
            dtm.addRow(new Object[]{
                nv.getMaNV(),
                nv.getTenNhanVien(),
                nv.getTenNguoiDung()
            });
        }
    }

    public NhanVien getModelNV() {
        NhanVien nv = new NhanVien();
        nv.setTenNhanVien(txtTenNV.getText());
        nv.setTenNguoiDung(txtTaiKhoan.getText());
        nv.setMatKhau(txtMatKhau.getText());
        return nv;
    }

    public void fillModelNV(NhanVien nv) {
        txtTenNV.setText(nv.getTenNhanVien());
        txtTaiKhoan.setText(nv.getTenNguoiDung());
        txtMatKhau.setText(nv.getMatKhau());
    }

    private void clearNV() {
        txtTenNV.setText("");
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        txtTenNV.setEnabled(true);
        txtTaiKhoan.setEnabled(true);
    }

    private void addNV() {
        String kq = PLS.addNV(getModelNV());
        JOptionPane.showMessageDialog(this, kq);
        loadDataNV(PLS.getListNV());
        clearNV();
    }

    private void updateNV() {
        String kq = PLS.updateNV(getModelNV());
        JOptionPane.showMessageDialog(this, kq);
        loadDataNV(PLS.getListNV());
        clearNV();
    }

    private void deleteNV() {
        String kq = PLS.deleteNV(getModelNV());
        JOptionPane.showMessageDialog(this, kq);
        loadDataNV(PLS.getListNV());
        clearNV();
    }

    private void searchNVbyName() {
        String name = txtTimNV.getText();
        ArrayList<NhanVien> nvlistName = PLS.getListNVbyName(name);
        loadDataNV(nvlistName);
    }
    // </editor-fold>

    public boolean ValidateNV() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isEmpty(txtTenNV, stb, "Chưa nhập tên nhân viên");
        v.isEmpty(txtTaiKhoan, stb, "Chưa nhập tài khoản");
        v.isEmpty(txtMatKhau, stb, "Chưa nhập mật khẩu");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    public boolean ValidateTimNV() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isEmpty(txtTimNV, stb, "Chưa nhập tên nhân viên");
        v.isString(txtTimNV, stb, "Vui lòng nhập tên");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNV = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        txtTimNV = new javax.swing.JTextField();
        btnTimNhanVien = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        btnThemNhanVien = new javax.swing.JButton();
        btnSuaNhanVien = new javax.swing.JButton();
        btnXoaNhanVien = new javax.swing.JButton();
        btnResetNhanVien = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        btnTaoQR = new javax.swing.JButton();
        txtTenNhanVienHTai = new javax.swing.JTextField();
        txtIDNhanVienHTai = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(980, 530));

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Tài khoản"
            }
        ));
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblNV);

        btnTimNhanVien.setText("Tìm theo tên");
        btnTimNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNhanVienActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel44.setText("Tìm nhân viên");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel45.setText("Thêm mới NV");

        jLabel46.setText("Tên nhân viên:");

        jLabel47.setText("Tài khoản:");

        btnThemNhanVien.setText("Thêm");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnSuaNhanVien.setText("Sửa");
        btnSuaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhanVienActionPerformed(evt);
            }
        });

        btnXoaNhanVien.setText("Xoá");
        btnXoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienActionPerformed(evt);
            }
        });

        btnResetNhanVien.setText("Reset");
        btnResetNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetNhanVienActionPerformed(evt);
            }
        });

        jLabel48.setText("Mật khẩu:");

        btnTaoQR.setText("Tạo QR");
        btnTaoQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoQRActionPerformed(evt);
            }
        });

        txtTenNhanVienHTai.setEditable(false);
        txtTenNhanVienHTai.setForeground(new java.awt.Color(60, 63, 65));
        txtTenNhanVienHTai.setText("Mai Linh");
        txtTenNhanVienHTai.setBorder(null);
        txtTenNhanVienHTai.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenNhanVienHTai.setEnabled(false);

        txtIDNhanVienHTai.setEditable(false);
        txtIDNhanVienHTai.setForeground(new java.awt.Color(60, 63, 65));
        txtIDNhanVienHTai.setText("2");
        txtIDNhanVienHTai.setBorder(null);
        txtIDNhanVienHTai.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtIDNhanVienHTai.setEnabled(false);

        javax.swing.GroupLayout panelNVLayout = new javax.swing.GroupLayout(panelNV);
        panelNV.setLayout(panelNVLayout);
        panelNVLayout.setHorizontalGroup(
            panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNVLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel44)
                    .addGroup(panelNVLayout.createSequentialGroup()
                        .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTimNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNVLayout.createSequentialGroup()
                        .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel45)
                            .addComponent(txtTenNV)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addGroup(panelNVLayout.createSequentialGroup()
                                .addComponent(btnXoaNhanVien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnResetNhanVien))
                            .addGroup(panelNVLayout.createSequentialGroup()
                                .addComponent(btnThemNhanVien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSuaNhanVien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTaoQR))
                            .addComponent(txtMatKhau)))
                    .addGroup(panelNVLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(txtTenNhanVienHTai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(txtIDNhanVienHTai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65))
        );
        panelNVLayout.setVerticalGroup(
            panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNVLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimNhanVien)
                    .addComponent(jLabel45))
                .addGap(18, 18, 18)
                .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelNVLayout.createSequentialGroup()
                        .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemNhanVien)
                            .addComponent(btnSuaNhanVien)
                            .addComponent(btnTaoQR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoaNhanVien)
                            .addComponent(btnResetNhanVien))
                        .addGap(38, 38, 38)
                        .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNhanVienHTai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDNhanVienHTai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(21, Short.MAX_VALUE)
                    .addComponent(panelNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
//        txtTenNV.setEnabled(false);
//        txtTaiKhoan.setEnabled(false);
        int index = tblNV.getSelectedRow();
        if (isNVSearch) {
            String name = txtTimNV.getText();
            NhanVien nv = PLS.getListNVbyName(name).get(index);
            fillModelNV(nv);
        } else {
            NhanVien nv = PLS.getListNV().get(index);
            fillModelNV(nv);
        }
    }//GEN-LAST:event_tblNVMouseClicked

    private void btnTimNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNhanVienActionPerformed
        if (ValidateTimNV()) {
            searchNVbyName();
            isNVSearch = true;
        }
    }//GEN-LAST:event_btnTimNhanVienActionPerformed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        if (ValidateNV()) {
            addNV();
            isNVSearch = false;
        }
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void btnSuaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhanVienActionPerformed
        if (ValidateNV()) {
            updateNV();
            isNVSearch = false;
        }
    }//GEN-LAST:event_btnSuaNhanVienActionPerformed

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
        deleteNV();
        isNVSearch = false;
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed

    private void btnResetNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetNhanVienActionPerformed
        clearNV();
        isNVSearch = false;
        loadDataNV(PLS.getListNV());
    }//GEN-LAST:event_btnResetNhanVienActionPerformed

    private void btnTaoQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoQRActionPerformed
        // TODO add your handling code here:
        if (ValidateNV()) {
            try {
                JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fc.setDialogTitle("Save as");
                fc.removeChoosableFileFilter(fc.getFileFilter());
                fc.addChoosableFileFilter(new FileNameExtensionFilter("PNG (.png)", "png"));
                if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    if (!file.getName().contains(".png")) {
                        file = new File(fc.getSelectedFile().getAbsolutePath() + ".png");
                    }
                    XQR.writeQRToFile(getModelNV().getTenNguoiDung() + "$" + getModelNV().getMatKhau(), file.getAbsoluteFile());
                    Runtime.getRuntime().exec("cmd /c start " + file.getAbsolutePath());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }//GEN-LAST:event_btnTaoQRActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetNhanVien;
    private javax.swing.JButton btnSuaNhanVien;
    private javax.swing.JButton btnTaoQR;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnTimNhanVien;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPanel panelNV;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtIDNhanVienHTai;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTenNhanVienHTai;
    private javax.swing.JTextField txtTimNV;
    // End of variables declaration//GEN-END:variables
}
