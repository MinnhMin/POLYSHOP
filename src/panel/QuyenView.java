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
import view.main;
/**
 *
 * @author Duc Long
 */
public class QuyenView extends javax.swing.JPanel {

    private boolean isSPfilter1;
    private boolean isSPfilter2;
    private boolean isSPfilter3;
    private boolean isSPfilter4;
    
    public QuyenView() {
        initComponents();
        chkNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkNhanVienItemStateChanged(evt);
            }
        });

        chkKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkKhachHangItemStateChanged(evt);
            }
        });

        chkSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkSPItemStateChanged(evt);
            }
        });

        chkHoaDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkHoaDonItemStateChanged(evt);
            }
        });

        chkThongKe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkThongKeItemStateChanged(evt);
            }
        });
        loadState();
    

        panelQuyen.setEnabled(false);

        chkHoaDon.setEnabled(false);
        chkKhachHang.setEnabled(false);
        chkNhanVien.setEnabled(false);
        chkSP.setEnabled(false);
        chkThongKe.setEnabled(false);

        isSPfilter = getSPCheckboxState();
        isSPfilter1 = getNhanVienCheckboxState();
        isSPfilter2 = getKhachHangCheckboxState();
        isSPfilter3 = getHoaDonCheckboxState();
        isSPfilter4 = getThongKeCheckboxState();

        chkSP.setSelected(isSPfilter);
        chkNhanVien.setSelected(isSPfilter1);
        chkKhachHang.setSelected(isSPfilter2);
        chkHoaDon.setSelected(isSPfilter3);
        chkThongKe.setSelected(isSPfilter4);
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


    boolean isNVSearch, isKHSearch, isTKDateSearch, isTKIDSearch, isSPfilter = false;
    poloService PLS = new poloService();
    NhanVien nhanVien = null;

    
    public void getSessionUser(NhanVien nv) {
        this.nhanVien = nv;
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelQuyen = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        chkSP = new javax.swing.JCheckBox();
        chkKhachHang = new javax.swing.JCheckBox();
        chkNhanVien = new javax.swing.JCheckBox();
        chkHoaDon = new javax.swing.JCheckBox();
        chkThongKe = new javax.swing.JCheckBox();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("Quyền sử dụng");

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

        javax.swing.GroupLayout panelQuyenLayout = new javax.swing.GroupLayout(panelQuyen);
        panelQuyen.setLayout(panelQuyenLayout);
        panelQuyenLayout.setHorizontalGroup(
            panelQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQuyenLayout.createSequentialGroup()
                .addGroup(panelQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelQuyenLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel2))
                    .addGroup(panelQuyenLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(panelQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkSP, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(321, Short.MAX_VALUE))
        );
        panelQuyenLayout.setVerticalGroup(
            panelQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQuyenLayout.createSequentialGroup()
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
                .addContainerGap(108, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(320, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(21, Short.MAX_VALUE)
                    .addComponent(panelQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(94, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkSPItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            enableSP();
//        } else {
//            disableSP();
//        }
    }//GEN-LAST:event_chkSPItemStateChanged

    private void chkSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSPActionPerformed

    private void chkKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkKhachHangItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            enableKH();
//        } else {
//            disableKH();
//        }
    }//GEN-LAST:event_chkKhachHangItemStateChanged

    private void chkNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkNhanVienItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            enableNhanVien();
//        } else {
//            disableNhanVien();
//        }
    }//GEN-LAST:event_chkNhanVienItemStateChanged

    private void chkHoaDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkHoaDonItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            enableHD();
//        } else {
//            disableHD();
//        }
    }//GEN-LAST:event_chkHoaDonItemStateChanged

    private void chkThongKeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkThongKeItemStateChanged
        // TODO add your handling code here:
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            enableTK();
//        } else {
//            disableTK();
//        }
    }//GEN-LAST:event_chkThongKeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkHoaDon;
    private javax.swing.JCheckBox chkKhachHang;
    private javax.swing.JCheckBox chkNhanVien;
    private javax.swing.JCheckBox chkSP;
    private javax.swing.JCheckBox chkThongKe;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelQuyen;
    // End of variables declaration//GEN-END:variables
}
