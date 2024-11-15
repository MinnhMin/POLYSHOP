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
public class TrangChuView extends javax.swing.JPanel {

    poloService PLS = new poloService();

    /**
     * Creates new form SanPhamView
     */
    public TrangChuView() {
        initComponents();
        loadDataTK(PLS.getListTK());
        loadDataDSHD1(PLS.getListHDHT());
        
        lbDTngay.setText(new DecimalFormat("#,###").format(PLS.getDTngay().getDoanhThuNgay()) + " VND");
        lbDTthang.setText(new DecimalFormat("#,###").format(PLS.getDTthang().getDoanhThuThang()) + " VND");
        lbDTnam.setText(new DecimalFormat("#,###").format(PLS.getDTnam().getDoanhThuNam()) + " VND");
        lbTongDon.setText(String.valueOf(PLS.getTongDon()) + " Đơn");
        isTKDateSearch = false;
        isTKIDSearch = false;
        loadDataDSHD1(PLS.getListHDHT());
//        loadDataDSSP(PLS.getListSP());

    }
    
//    private void loadDataDSSP(ArrayList<SanPham> splist) {
//        DefaultTableModel dtm = (DefaultTableModel) tblDSSP.getModel();
//        dtm.setRowCount(0);
//        for (SanPham sp : splist) {
//            dtm.addRow(new Object[]{
//                sp.getId(),
//                sp.getTenSP(),
//                sp.getMauSac(),
//                sp.getKichCo(),
//                sp.getChatLieu(),
//                sp.getSoLuong(),});
//        }
//    }

    private void loadDataTK(ArrayList<ThongKe> tklist) {
        DefaultTableModel dtm = (DefaultTableModel) tblThongKe.getModel();
        dtm.setRowCount(0);
        for (ThongKe tk : tklist) {
            dtm.addRow(new Object[]{
                tk.getNgayBan(),
                tk.getTenSP(),
                tk.getTongSP(),
                new DecimalFormat("#,###").format(tk.getDoanhThuNgay()) + " VND"
            });
        }
    }

    private void loadDataDSHD1(ArrayList<HoaDon> hdlist) {
        DefaultTableModel dtm = (DefaultTableModel) tblDSHD1.getModel();
        dtm.setRowCount(0);
        for (HoaDon hd : hdlist) {
            dtm.addRow(new Object[]{
                hd.getId(),
                hd.getTenNhanVien(),
                hd.getTenKhachHang(),
                hd.getPttt(),
                hd.getNgayTao(),
                new DecimalFormat("#,###").format(hd.getTong()) + " VND"
            });
        }
    }

    private void loadDataHDCT1(ArrayList<HoaDonChiTiet> hdctlist) {
        DefaultTableModel dtm = (DefaultTableModel) tblGioHang1.getModel();
        dtm.setRowCount(0);
        for (HoaDonChiTiet hdct : hdctlist) {
            dtm.addRow(new Object[]{
                hdct.getMaSP(),
                hdct.getTenSP(),
                hdct.getMauSac(),
                hdct.getKichCo(),
                hdct.getChatLieu(),
                hdct.getDonGia(),
                hdct.getSoLuong(),
                hdct.getTong()
            });
        }
    }

    public boolean ValidateDate() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isEmptyDate(ngayBatDau, stb, "Chưa nhập ngày bắt đầu!");
        v.isEmptyDate(ngayKetThuc, stb, "Chưa nhập ngày kết thúc!");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    public boolean ValidateTKHD() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isNumber(txtTKHD, stb, "Vui lòng nhập đúng định dạng mã HD!", 0);
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }
    boolean isNVSearch, isKHSearch, isTKDateSearch, isTKIDSearch, isSPfilter = false;
    NhanVien nhanVien = null;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTK = new javax.swing.JPanel();
        panelTongDon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbTongDon = new javax.swing.JLabel();
        panelDoanhThuNam = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lbDTnam = new javax.swing.JLabel();
        panelDoanhThuThang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbDTthang = new javax.swing.JLabel();
        panelDoanhThuNgay = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lbDTngay = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnSearchTKDate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDSHD1 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblGioHang1 = new javax.swing.JTable();
        btnSearchTKID = new javax.swing.JButton();
        txtTKHD = new javax.swing.JTextField();
        btnResetTK = new javax.swing.JButton();
        ngayBatDau = new com.toedter.calendar.JDateChooser();
        ngayKetThuc = new com.toedter.calendar.JDateChooser();

        panelTongDon.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Tổng đơn");

        lbTongDon.setText("123 Đơn");

        javax.swing.GroupLayout panelTongDonLayout = new javax.swing.GroupLayout(panelTongDon);
        panelTongDon.setLayout(panelTongDonLayout);
        panelTongDonLayout.setHorizontalGroup(
            panelTongDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongDonLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelTongDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTongDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        panelTongDonLayout.setVerticalGroup(
            panelTongDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongDonLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongDon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDoanhThuNam.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Doanh thu năm");

        lbDTnam.setText("123 Đơn");

        javax.swing.GroupLayout panelDoanhThuNamLayout = new javax.swing.GroupLayout(panelDoanhThuNam);
        panelDoanhThuNam.setLayout(panelDoanhThuNamLayout);
        panelDoanhThuNamLayout.setHorizontalGroup(
            panelDoanhThuNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuNamLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelDoanhThuNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDTnam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelDoanhThuNamLayout.setVerticalGroup(
            panelDoanhThuNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuNamLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDTnam)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDoanhThuThang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Doanh thu tháng");

        lbDTthang.setText("123 Đơn");

        javax.swing.GroupLayout panelDoanhThuThangLayout = new javax.swing.GroupLayout(panelDoanhThuThang);
        panelDoanhThuThang.setLayout(panelDoanhThuThangLayout);
        panelDoanhThuThangLayout.setHorizontalGroup(
            panelDoanhThuThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuThangLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelDoanhThuThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDTthang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        panelDoanhThuThangLayout.setVerticalGroup(
            panelDoanhThuThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuThangLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDTthang)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        panelDoanhThuNgay.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("Doanh thu ngày");

        lbDTngay.setText("123 Đơn");

        javax.swing.GroupLayout panelDoanhThuNgayLayout = new javax.swing.GroupLayout(panelDoanhThuNgay);
        panelDoanhThuNgay.setLayout(panelDoanhThuNgayLayout);
        panelDoanhThuNgayLayout.setHorizontalGroup(
            panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDTngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDoanhThuNgayLayout.setVerticalGroup(
            panelDoanhThuNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoanhThuNgayLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDTngay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setText("Ngày bắt đầu:");

        jLabel18.setText("Ngày kết thúc:");

        btnSearchTKDate.setText("Tìm kiếm");
        btnSearchTKDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchTKDateActionPerformed(evt);
            }
        });

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ngày bán", "Sản phẩm", "Số lượng", "Doanh thu"
            }
        ));
        tblThongKe.setPreferredSize(new java.awt.Dimension(980, 530));
        tblThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongKeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThongKe);

        tblDSHD1.setAutoCreateRowSorter(true);
        tblDSHD1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nhân Viên", "Khách Hàng", "PTTT", "Ngày Tạo", "Giá trị"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSHD1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSHD1MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblDSHD1);

        tblGioHang1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDSP", "Sản Phẩm", "Màu Sắc", "Kích Cỡ", "Chất Liệu", "Đơn Giá", "Số Lượng", "Tổng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tblGioHang1);

        btnSearchTKID.setText("Tìm HD");
        btnSearchTKID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchTKIDActionPerformed(evt);
            }
        });

        btnResetTK.setText("Reset");
        btnResetTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTKLayout = new javax.swing.GroupLayout(panelTK);
        panelTK.setLayout(panelTKLayout);
        panelTKLayout.setHorizontalGroup(
            panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTKLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTKLayout.createSequentialGroup()
                        .addGroup(panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTKLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTKLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(btnSearchTKDate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelTKLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane11)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTKLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTKHD, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchTKID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetTK))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTKLayout.createSequentialGroup()
                        .addComponent(panelTongDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelDoanhThuNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelDoanhThuThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelDoanhThuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(190, 190, 190))
        );
        panelTKLayout.setVerticalGroup(
            panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelDoanhThuThang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDoanhThuNam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTongDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDoanhThuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addGroup(panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearchTKDate)
                        .addComponent(btnSearchTKID)
                        .addComponent(txtTKHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnResetTK))
                    .addComponent(ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTKLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelTKLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1148, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelTK, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchTKDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTKDateActionPerformed
        if (ValidateDate()) {
            isTKDateSearch = true;
            isTKIDSearch = false;
            Date ngayBatDau = new Date(this.ngayBatDau.getDate().getTime());
            Date ngayKetThuc = new Date(this.ngayKetThuc.getDate().getTime());
            loadDataTK(PLS.getListTK(ngayBatDau, ngayKetThuc));
            loadDataDSHD1(PLS.getListHDHTDate(ngayBatDau, ngayKetThuc));
        }
    }//GEN-LAST:event_btnSearchTKDateActionPerformed

    private void tblThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongKeMouseClicked

    }//GEN-LAST:event_tblThongKeMouseClicked

    private void tblDSHD1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSHD1MouseClicked
        int index = tblDSHD1.getSelectedRow();
        if (isTKDateSearch) {
            Date ngayBatDau = new Date(this.ngayBatDau.getDate().getTime());
            Date ngayKetThuc = new Date(this.ngayKetThuc.getDate().getTime());
            HoaDon hd = PLS.getListHDHTDate(ngayBatDau, ngayKetThuc).get(index);
            Integer MaHD = hd.getId();
            loadDataHDCT1(PLS.getHDCT(MaHD));
        } else if (isTKIDSearch) {
            int maHD = Integer.valueOf(txtTKHD.getText());
            HoaDon hd = PLS.getListHDHT(maHD).get(index);
            Integer MaHD = hd.getId();
            loadDataHDCT1(PLS.getHDCT(MaHD));
        } else {
            HoaDon hd = PLS.getListHDHT().get(index);
            Integer MaHD = hd.getId();
            loadDataHDCT1(PLS.getHDCT(MaHD));
        }
    }//GEN-LAST:event_tblDSHD1MouseClicked

    private void btnSearchTKIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTKIDActionPerformed
        if (ValidateTKHD()) {
            isTKIDSearch = true;
            isTKDateSearch = false;
            int maHD = Integer.valueOf(txtTKHD.getText());
            loadDataDSHD1(PLS.getListHDHT(maHD));
        }
    }//GEN-LAST:event_btnSearchTKIDActionPerformed

    private void btnResetTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTKActionPerformed
        isTKDateSearch = false;
        isTKIDSearch = false;
        loadDataDSHD1(PLS.getListHDHT());
        loadDataTK(PLS.getListTK());
        txtTKHD.setText("");
        txtTKHD.setBackground(WHITE);
    }//GEN-LAST:event_btnResetTKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetTK;
    private javax.swing.JButton btnSearchTKDate;
    private javax.swing.JButton btnSearchTKID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel lbDTnam;
    private javax.swing.JLabel lbDTngay;
    private javax.swing.JLabel lbDTthang;
    private javax.swing.JLabel lbTongDon;
    private com.toedter.calendar.JDateChooser ngayBatDau;
    private com.toedter.calendar.JDateChooser ngayKetThuc;
    private javax.swing.JPanel panelDoanhThuNam;
    private javax.swing.JPanel panelDoanhThuNgay;
    private javax.swing.JPanel panelDoanhThuThang;
    private javax.swing.JPanel panelTK;
    private javax.swing.JPanel panelTongDon;
    private javax.swing.JTable tblDSHD1;
    private javax.swing.JTable tblGioHang1;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JTextField txtTKHD;
    // End of variables declaration//GEN-END:variables
}
