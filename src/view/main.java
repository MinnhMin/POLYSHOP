/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

// <editor-fold defaultstate="collapsed" desc="import 1"> 
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JOptionPane;
import panel.HoaDonView;
import panel.KhachHangView;
import panel.NhanVienView;
import panel.QuyenView;
import panel.SanPhamView;
import panel.TrangChuView;
// </editor-fold>  

// <editor-fold defaultstate="collapsed" desc="import 2"> 
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
import login.loginform.login;
import panel.PhanQuyenView;
// </editor-fold>  

/**
 *
 * @author Duc Long
 */
public class main extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc="Color"> 
    Color searchFieldColor = new Color(69, 88, 190);
    Color selection = new Color(239, 243, 246);
    Color selectionSiteBar = new Color(112, 126, 199);
    Color defaultColor = new Color(255, 255, 255);
    Color defaultTxtColor = new Color(189, 195, 228);
    Color selectionTxtColor = new Color(112, 126, 199);
    Color background = new Color(239, 243, 246);
// </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="View"> 
    HoaDonView hd = new HoaDonView();
    KhachHangView kh = new KhachHangView();
    NhanVienView nv = new NhanVienView();
    QuyenView q = new QuyenView();
    SanPhamView sp = new SanPhamView();
    TrangChuView tc = new TrangChuView();
    // </editor-fold>

    private CardLayout cardLayout;
    private boolean isSPfilter1;
    private boolean isSPfilter2;
    private boolean isSPfilter3;
    private boolean isSPfilter4;

    boolean isNVSearch, isKHSearch, isTKDateSearch, isTKIDSearch, isSPfilter = false;
    poloService PLS = new poloService();
    NhanVien nhanVien = null;

    public main() {
        initComponents();
        cardLayout = new CardLayout();
        pnlMain.setLayout(cardLayout);
        pnlMain.add(hd, "hoadon");
        pnlMain.add(sp, "sanpham");
//        nv.setSessionUser(this.nhanVien);
        pnlMain.add(nv, "nhanvien");
        pnlMain.add(kh, "khachhang");
        pnlMain.add(q, "quyen");
        pnlMain.add(tc, "trangchu");
        loadTC();

        isSPfilter = qAD.getSPCheckboxState();
        isSPfilter1 = qAD.getNhanVienCheckboxState();
        isSPfilter2 = qAD.getKhachHangCheckboxState();
        isSPfilter3 = qAD.getHoaDonCheckboxState();
        isSPfilter4 = qAD.getThongKeCheckboxState();

    }

    public void loadTC() {
        pnlTrangChu.setBackground(selection);
        pnlTCBar.setBackground(selectionSiteBar);
        pnlTrangChu.setBackground(background);
        lblTrangChu.setForeground(selectionTxtColor);
        CardLayout cardLayout = (CardLayout) pnlMain.getLayout();
        cardLayout.show(pnlMain, "trangchu");

        pnlHoaDon.setBackground(defaultColor);
        pnlKhachHang.setBackground(defaultColor);
        pnlNhanVien.setBackground(defaultColor);
        pnlQuyen.setBackground(defaultColor);
        pnlSanPham.setBackground(defaultColor);

        pnlHDBar.setBackground(defaultColor);
        pnlKHBar.setBackground(defaultColor);
        pnlNVBar.setBackground(defaultColor);
        pnlQBar.setBackground(defaultColor);
        pnlSPBar.setBackground(defaultColor);

        this.qAD = new PhanQuyenView();
        setMainAD(qAD);
        qAD.loadState();

    }

    private PhanQuyenView qAD;

    public void setMainAD(PhanQuyenView qAD) {
        this.qAD = qAD;
    }

    public void setSessionUser(NhanVien nv) {
        getSessionUser(nv);
        txtTenNhanVienHTai.setText(nv.getTenNhanVien());
        txtIDNhanVienHTai.setText(nv.getMaNV().toString());
    }

    public void getSessionUser(NhanVien nv) {
        this.nhanVien = nv;
    }

    public String tenNVHTai() {
        return txtTenNhanVienHTai.getText();
    }

    public String IDNVHTai() {
        return txtIDNhanVienHTai.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtIDNhanVienHTai = new javax.swing.JTextField();
        txtTenNhanVienHTai = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        pnlTrangChu = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        pnlTCBar = new javax.swing.JPanel();
        pnlHoaDon = new javax.swing.JPanel();
        lblHoaDon = new javax.swing.JLabel();
        pnlHDBar = new javax.swing.JPanel();
        pnlSanPham = new javax.swing.JPanel();
        lblSP = new javax.swing.JLabel();
        pnlSPBar = new javax.swing.JPanel();
        pnlKhachHang = new javax.swing.JPanel();
        lblKH = new javax.swing.JLabel();
        pnlKHBar = new javax.swing.JPanel();
        pnlNhanVien = new javax.swing.JPanel();
        lblNV = new javax.swing.JLabel();
        pnlNVBar = new javax.swing.JPanel();
        pnlQuyen = new javax.swing.JPanel();
        lblQuyen = new javax.swing.JLabel();
        pnlQBar = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(234, 234, 234));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 593));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(66, 88, 190));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jLabel1.setText("Đăng xuất");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1082, 0, 128, 57));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("POLOSHOP");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, -4, 140, 60));

        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Nhân viên:");
        jPanel2.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, 20));

        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("ID:");
        jPanel2.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, 20));

        txtIDNhanVienHTai.setEditable(false);
        txtIDNhanVienHTai.setBackground(new java.awt.Color(66, 88, 190));
        txtIDNhanVienHTai.setForeground(new java.awt.Color(255, 255, 255));
        txtIDNhanVienHTai.setText("2");
        txtIDNhanVienHTai.setBorder(null);
        txtIDNhanVienHTai.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtIDNhanVienHTai.setEnabled(false);
        jPanel2.add(txtIDNhanVienHTai, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, 20));

        txtTenNhanVienHTai.setEditable(false);
        txtTenNhanVienHTai.setBackground(new java.awt.Color(66, 88, 190));
        txtTenNhanVienHTai.setForeground(new java.awt.Color(255, 255, 255));
        txtTenNhanVienHTai.setText("Mai Linh");
        txtTenNhanVienHTai.setBorder(null);
        txtTenNhanVienHTai.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtTenNhanVienHTai.setEnabled(false);
        jPanel2.add(txtTenNhanVienHTai, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1210, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTrangChuMouseClicked(evt);
            }
        });
        pnlTrangChu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTrangChu.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTrangChu.setForeground(new java.awt.Color(112, 126, 199));
        lblTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pages_light.png"))); // NOI18N
        lblTrangChu.setText("  Trang chủ");
        pnlTrangChu.add(lblTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 0, 189, 59));

        pnlTCBar.setPreferredSize(new java.awt.Dimension(10, 59));

        javax.swing.GroupLayout pnlTCBarLayout = new javax.swing.GroupLayout(pnlTCBar);
        pnlTCBar.setLayout(pnlTCBarLayout);
        pnlTCBarLayout.setHorizontalGroup(
            pnlTCBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlTCBarLayout.setVerticalGroup(
            pnlTCBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        pnlTrangChu.add(pnlTCBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 60));

        jPanel3.add(pnlTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 26, 220, -1));

        pnlHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHoaDonMouseClicked(evt);
            }
        });
        pnlHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHoaDon.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(112, 126, 199));
        lblHoaDon.setText("Bán hàng");
        pnlHoaDon.add(lblHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 0, 191, 51));

        javax.swing.GroupLayout pnlHDBarLayout = new javax.swing.GroupLayout(pnlHDBar);
        pnlHDBar.setLayout(pnlHDBarLayout);
        pnlHDBarLayout.setHorizontalGroup(
            pnlHDBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlHDBarLayout.setVerticalGroup(
            pnlHDBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        pnlHoaDon.add(pnlHDBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jPanel3.add(pnlHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 220, -1));

        pnlSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSanPhamMouseClicked(evt);
            }
        });
        pnlSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSP.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblSP.setForeground(new java.awt.Color(112, 126, 199));
        lblSP.setText("Sản phẩm");
        pnlSanPham.add(lblSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 1, 191, 50));

        javax.swing.GroupLayout pnlSPBarLayout = new javax.swing.GroupLayout(pnlSPBar);
        pnlSPBar.setLayout(pnlSPBarLayout);
        pnlSPBarLayout.setHorizontalGroup(
            pnlSPBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlSPBarLayout.setVerticalGroup(
            pnlSPBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        pnlSanPham.add(pnlSPBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jPanel3.add(pnlSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 220, -1));

        pnlKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlKhachHangMouseClicked(evt);
            }
        });
        pnlKhachHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblKH.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblKH.setForeground(new java.awt.Color(112, 126, 199));
        lblKH.setText("Khách hàng");
        pnlKhachHang.add(lblKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 0, 191, 51));

        javax.swing.GroupLayout pnlKHBarLayout = new javax.swing.GroupLayout(pnlKHBar);
        pnlKHBar.setLayout(pnlKHBarLayout);
        pnlKHBarLayout.setHorizontalGroup(
            pnlKHBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlKHBarLayout.setVerticalGroup(
            pnlKHBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        pnlKhachHang.add(pnlKHBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jPanel3.add(pnlKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 220, -1));

        pnlNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNhanVienMouseClicked(evt);
            }
        });
        pnlNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNV.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblNV.setForeground(new java.awt.Color(112, 126, 199));
        lblNV.setText("Nhân viên");
        pnlNhanVien.add(lblNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 0, 191, 51));

        javax.swing.GroupLayout pnlNVBarLayout = new javax.swing.GroupLayout(pnlNVBar);
        pnlNVBar.setLayout(pnlNVBarLayout);
        pnlNVBarLayout.setHorizontalGroup(
            pnlNVBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlNVBarLayout.setVerticalGroup(
            pnlNVBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        pnlNhanVien.add(pnlNVBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jPanel3.add(pnlNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 220, -1));

        pnlQuyen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlQuyenMouseClicked(evt);
            }
        });
        pnlQuyen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQuyen.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblQuyen.setForeground(new java.awt.Color(112, 126, 199));
        lblQuyen.setText("Quyền");
        pnlQuyen.add(lblQuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 0, 191, 51));

        javax.swing.GroupLayout pnlQBarLayout = new javax.swing.GroupLayout(pnlQBar);
        pnlQBar.setLayout(pnlQBarLayout);
        pnlQBarLayout.setHorizontalGroup(
            pnlQBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlQBarLayout.setVerticalGroup(
            pnlQBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        pnlQuyen.add(pnlQBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        jPanel3.add(pnlQuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 220, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 53, -1, 540));

        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(pnlMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 980, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        int logOut = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (logOut == 0) {
            login lg = new login();
            lg.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void pnlHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHoaDonMouseClicked
        // TODO add your handling code here:
        if (!isSPfilter3) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập");
        } else {
            pnlHoaDon.setBackground(selection);
            pnlHDBar.setBackground(selectionSiteBar);
            pnlHoaDon.setBackground(background);
            lblHoaDon.setForeground(selectionTxtColor);
            CardLayout cardLayout = (CardLayout) pnlMain.getLayout();
            cardLayout.show(pnlMain, "hoadon");

            pnlKhachHang.setBackground(defaultColor);
            pnlNhanVien.setBackground(defaultColor);
            pnlQuyen.setBackground(defaultColor);
            pnlSanPham.setBackground(defaultColor);
            pnlTrangChu.setBackground(defaultColor);

            pnlKHBar.setBackground(defaultColor);
            pnlNVBar.setBackground(defaultColor);
            pnlQBar.setBackground(defaultColor);
            pnlSPBar.setBackground(defaultColor);
            pnlTCBar.setBackground(defaultColor);
        }
    }//GEN-LAST:event_pnlHoaDonMouseClicked

    private void pnlSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSanPhamMouseClicked
        // TODO add your handling code here:
        if (!isSPfilter) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập");
        } else {
            pnlSanPham.setBackground(selection);
            pnlSPBar.setBackground(selectionSiteBar);
            pnlSanPham.setBackground(background);
            lblSP.setForeground(selectionTxtColor);
            CardLayout cardLayout = (CardLayout) pnlMain.getLayout();
            cardLayout.show(pnlMain, "sanpham");

            pnlHoaDon.setBackground(defaultColor);
            pnlKhachHang.setBackground(defaultColor);
            pnlNhanVien.setBackground(defaultColor);
            pnlQuyen.setBackground(defaultColor);
            pnlTrangChu.setBackground(defaultColor);

            pnlHDBar.setBackground(defaultColor);
            pnlKHBar.setBackground(defaultColor);
            pnlNVBar.setBackground(defaultColor);
            pnlQBar.setBackground(defaultColor);
            pnlTCBar.setBackground(defaultColor);
        }
    }//GEN-LAST:event_pnlSanPhamMouseClicked

    private void pnlKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlKhachHangMouseClicked
        // TODO add your handling code here:
        if (!isSPfilter2) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập");
        } else {
            pnlKhachHang.setBackground(selection);
            pnlKHBar.setBackground(selectionSiteBar);
            pnlKhachHang.setBackground(background);
            lblKH.setForeground(selectionTxtColor);
            CardLayout cardLayout = (CardLayout) pnlMain.getLayout();
            cardLayout.show(pnlMain, "khachhang");

            pnlHoaDon.setBackground(defaultColor);
            pnlNhanVien.setBackground(defaultColor);
            pnlQuyen.setBackground(defaultColor);
            pnlSanPham.setBackground(defaultColor);
            pnlTrangChu.setBackground(defaultColor);

            pnlHDBar.setBackground(defaultColor);
            pnlNVBar.setBackground(defaultColor);
            pnlQBar.setBackground(defaultColor);
            pnlSPBar.setBackground(defaultColor);
            pnlTCBar.setBackground(defaultColor);
        }
    }//GEN-LAST:event_pnlKhachHangMouseClicked

    private void pnlNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNhanVienMouseClicked
        // TODO add your handling code here:
        if (!isSPfilter1) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập");
        } else {
            pnlNhanVien.setBackground(selection);
            pnlNVBar.setBackground(selectionSiteBar);
            pnlNhanVien.setBackground(background);

            lblNV.setForeground(selectionTxtColor);
            CardLayout cardLayout = (CardLayout) pnlMain.getLayout();
            cardLayout.show(pnlMain, "nhanvien");
            nv.setSessionUser(this.nhanVien);
            pnlHoaDon.setBackground(defaultColor);
            pnlKhachHang.setBackground(defaultColor);
            pnlQuyen.setBackground(defaultColor);
            pnlSanPham.setBackground(defaultColor);
            pnlTrangChu.setBackground(defaultColor);

            pnlHDBar.setBackground(defaultColor);
            pnlKHBar.setBackground(defaultColor);
            pnlQBar.setBackground(defaultColor);
            pnlSPBar.setBackground(defaultColor);
            pnlTCBar.setBackground(defaultColor);
        }
    }//GEN-LAST:event_pnlNhanVienMouseClicked

    private void pnlQuyenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlQuyenMouseClicked
        // TODO add your handling code here:
        pnlQuyen.setBackground(selection);
        pnlQBar.setBackground(selectionSiteBar);
        pnlQuyen.setBackground(background);
        lblQuyen.setForeground(selectionTxtColor);
        CardLayout cardLayout = (CardLayout) pnlMain.getLayout();
        cardLayout.show(pnlMain, "quyen");

        pnlHoaDon.setBackground(defaultColor);
        pnlKhachHang.setBackground(defaultColor);
        pnlNhanVien.setBackground(defaultColor);
        pnlSanPham.setBackground(defaultColor);
        pnlTrangChu.setBackground(defaultColor);

        pnlHDBar.setBackground(defaultColor);
        pnlKHBar.setBackground(defaultColor);
        pnlNVBar.setBackground(defaultColor);
        pnlSPBar.setBackground(defaultColor);
        pnlTCBar.setBackground(defaultColor);
    }//GEN-LAST:event_pnlQuyenMouseClicked

    private void pnlTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTrangChuMouseClicked
        // TODO add your handling code here:
        pnlTrangChu.setBackground(selection);
        pnlTCBar.setBackground(selectionSiteBar);
        pnlTrangChu.setBackground(background);
        lblTrangChu.setForeground(selectionTxtColor);
        CardLayout cardLayout = (CardLayout) pnlMain.getLayout();
        cardLayout.show(pnlMain, "trangchu");

        pnlHoaDon.setBackground(defaultColor);
        pnlKhachHang.setBackground(defaultColor);
        pnlNhanVien.setBackground(defaultColor);
        pnlQuyen.setBackground(defaultColor);
        pnlSanPham.setBackground(defaultColor);

        pnlHDBar.setBackground(defaultColor);
        pnlKHBar.setBackground(defaultColor);
        pnlNVBar.setBackground(defaultColor);
        pnlQBar.setBackground(defaultColor);
        pnlSPBar.setBackground(defaultColor);
    }//GEN-LAST:event_pnlTrangChuMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        for (double i = 0.0; i <= 1.0; i = i + 0.1) {
            String val = i + "";
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try {
                Thread.sleep(20);
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_formWindowOpened

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKH;
    private javax.swing.JLabel lblNV;
    private javax.swing.JLabel lblQuyen;
    private javax.swing.JLabel lblSP;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JPanel pnlHDBar;
    public static javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKHBar;
    public static javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNVBar;
    public static javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlQBar;
    public static javax.swing.JPanel pnlQuyen;
    private javax.swing.JPanel pnlSPBar;
    public static javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlTCBar;
    public static javax.swing.JPanel pnlTrangChu;
    private javax.swing.JTextField txtIDNhanVienHTai;
    private javax.swing.JTextField txtTenNhanVienHTai;
    // End of variables declaration//GEN-END:variables
}
