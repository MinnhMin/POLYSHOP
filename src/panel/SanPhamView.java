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
public class SanPhamView extends javax.swing.JPanel {

    private boolean isSPfilter1;
    private boolean isSPfilter2;
    private boolean isSPfilter3;
    private boolean isSPfilter4;

    boolean isNVSearch, isKHSearch, isTKDateSearch, isTKIDSearch, isSPfilter = false;
    poloService PLS = new poloService();
    NhanVien nhanVien = null;

    public SanPhamView() {
        initComponents();
        loadDataSP(PLS.getListSP());
        loadcboCL(PLS.getListChatLieu());
        loadcboDM(PLS.getListDM());
        loadcboKC(PLS.getListKichCo());
        loadcboMS(PLS.getListMau());
//        loadDataDSSP(PLS.getListSP());
    }
    
    // <editor-fold defaultstate="collapsed" desc="Validate">
    public boolean ValidateSP() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isEmpty(txtTenSP, stb, "Chưa nhập tên sản phẩm");
        v.isEmpty(txtDonGia, stb, "Chưa nhập đơn giá");
        v.isEmpty(txtSoLuong, stb, "Chưa nhập số lượng");
        v.isEmpty(txtareaDesc, stb, "Chưa nhập mô tả");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    public boolean ValidateTTSP() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isEmpty(txtTenTT, stb, "Chưa nhập tên thuộc tính");
        v.isString(txtTenTT, stb, "Không nhập số, ký tự đặc biệt");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    public boolean ValidateLocSP1() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isString(txtTenSP, stb, "Vui lòng nhập tên sản phẩm!");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    //</editor-fold>

    private void loadcboMS(ArrayList<MauSac> mslist) {
        DefaultComboBoxModel dcbo = (DefaultComboBoxModel) cboMauSac.getModel();
        dcbo.removeAllElements();
        for (MauSac ms : mslist) {
            dcbo.addElement(ms.getTenMau());
        }
    }

    private void loadcboKC(ArrayList<KichCo> kclist) {
        DefaultComboBoxModel dcbo = (DefaultComboBoxModel) cboKichCo.getModel();
        dcbo.removeAllElements();
        for (KichCo kc : kclist) {
            dcbo.addElement(kc.getTenKichCo());
        }
    }

    private void loadcboCL(ArrayList<ChatLieu> cllist) {
        DefaultComboBoxModel dcbo = (DefaultComboBoxModel) cboChatLieu.getModel();
        dcbo.removeAllElements();
        for (ChatLieu cl : cllist) {
            dcbo.addElement(cl.getTenChatLieu());
        }
    }

    private void loadcboDM(ArrayList<DanhMuc> dmlist) {
        DefaultComboBoxModel dcbo = (DefaultComboBoxModel) cboDanhMuc.getModel();
        dcbo.removeAllElements();
        for (DanhMuc dm : dmlist) {
            dcbo.addElement(dm.getTenDanhMuc());
        }
    }

    public void loadDataSP(ArrayList<SanPham> splist) {
        DefaultTableModel dtm = (DefaultTableModel) tblSP.getModel();
        dtm.setRowCount(0);
        for (SanPham sp : splist) {
            dtm.addRow(new Object[]{
                sp.getId(),
                sp.getTenSP(),
                sp.getMoTa(),
                sp.getDanhMuc(),
                sp.getMauSac(),
                sp.getKichCo(),
                sp.getChatLieu(),
                sp.getSoLuong(),
                sp.getDonGia()
            });
        }
    }

    public SanPham getModelSP() {
        SanPham sp = new SanPham();
        sp.setId(Integer.valueOf(txtMaSP.getText()));
        sp.setTenSP(txtTenSP.getText());
        sp.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        sp.setDonGia(Integer.valueOf(txtDonGia.getText()));
        sp.setMoTa(txtareaDesc.getText());
        sp.setMaMS(PLS.getMSid(cboMauSac.getSelectedItem().toString()).getId());
        sp.setMaKC(PLS.getKCid(cboKichCo.getSelectedItem().toString()).getId());
        sp.setMaCL(PLS.getCLid(cboChatLieu.getSelectedItem().toString()).getId());
        sp.setMaDM(PLS.getDMid(cboDanhMuc.getSelectedItem().toString()).getId());
        return sp;
    }

    public void fillModelSP(SanPham sp) {
        txtMaSP.setText(sp.getId().toString());
        txtTenSP.setText(sp.getTenSP());
        cboMauSac.setSelectedItem(sp.getMauSac());
        cboKichCo.setSelectedItem(sp.getKichCo());
        cboChatLieu.setSelectedItem(sp.getChatLieu());
        cboDanhMuc.setSelectedItem(sp.getDanhMuc());
        txtDonGia.setText(sp.getDonGia().toString());
        txtSoLuong.setText(sp.getSoLuong().toString());
        txtareaDesc.setText(sp.getMoTa());
        txtTenSP.setEnabled(false);
        txtareaDesc.setEnabled(false);
    }

    private void clearSP() {
        txtMaSP.setText("0");
        txtTenSP.setText("");
        cboMauSac.setSelectedIndex(0);
        cboKichCo.setSelectedIndex(0);
        cboChatLieu.setSelectedIndex(0);
        cboDanhMuc.setSelectedIndex(0);
        txtDonGia.setText("");
        txtSoLuong.setText("");
        txtareaDesc.setText("");
        txtTenSP.setEnabled(true);
        txtareaDesc.setEnabled(true);
        loadDataSP(PLS.getListSP());
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

    private void hideSP() {
        PLS.hideSP(getModelSP());
        loadDataSP(PLS.getListSP());
        clearSP();
    }

    private void unhideSP() {
        PLS.unhideSP();
        loadDataSP(PLS.getListSP());
    }

    private void addSP() {
        String kq = PLS.addSP(getModelSP());
        JOptionPane.showMessageDialog(this, kq);
        loadDataSP(PLS.getListSP());
        clearSP();
    }

    private void updateSP() {
        String kq = PLS.updateTTSP(getModelSP());
        JOptionPane.showMessageDialog(this, kq);
        loadDataSP(PLS.getListSP());
        clearSP();
    }

    private void importExcel() {
        ExcelPreview epv = new ExcelPreview();
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJtableImport = null;
        //Set default path
        String defaultDirectoryPath = "E:\\OneDrive - Đại học FPT- FPT University\\FPT\\SOF2041";
        JFileChooser excelFileChooser = new JFileChooser(defaultDirectoryPath);
        //Filter file ext
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        //Set dialog title
        excelFileChooser.setDialogTitle("Chọn file excel");
        int excelChooser = excelFileChooser.showOpenDialog(panelSP);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJtableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJtableImport.getSheetAt(0);
                ArrayList<SanPham> splist = new ArrayList<>();

                //Looping through excel columns and rows
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow sheetRow = excelSheet.getRow(row);

                    XSSFCell tenSP = sheetRow.getCell(0);
                    XSSFCell moTa = sheetRow.getCell(1);
                    XSSFCell danhMuc = sheetRow.getCell(2);
                    XSSFCell mauSac = sheetRow.getCell(3);
                    XSSFCell kichCo = sheetRow.getCell(4);
                    XSSFCell chatLieu = sheetRow.getCell(5);
                    XSSFCell soLuong = sheetRow.getCell(6);
                    XSSFCell donGia = sheetRow.getCell(7);

                    SanPham sp = new SanPham();
                    sp.setTenSP(tenSP.getStringCellValue());
                    sp.setMoTa(moTa.getStringCellValue());
                    sp.setDanhMuc(danhMuc.getStringCellValue());
                    sp.setMauSac(mauSac.getStringCellValue());
                    sp.setKichCo(kichCo.getStringCellValue());
                    sp.setChatLieu(chatLieu.getStringCellValue());
                    sp.setSoLuong((int) (soLuong.getNumericCellValue()));
                    sp.setDonGia((int) (donGia.getNumericCellValue()));
                    splist.add(sp);

                }
                epv.loadDataSP(splist);
                epv.setVisible(true);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelJtableImport != null) {
                        excelJtableImport.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void exportExcel() {
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = (Sheet) wb.createSheet("customer");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblSP.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblSP.getColumnName(i));
                }

                for (int j = 0; j < tblSP.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblSP.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tblSP.getValueAt(j, k) != null) {
                            cell.setCellValue(tblSP.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Panel Thuoc Tinh">
    private void loadDataMS(ArrayList<MauSac> mslist) {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) tblThuocTinh.getModel();
        dtm.setRowCount(0);
        for (MauSac ms : mslist) {
            dtm.addRow(new Object[]{
                i++,
                ms.getId(),
                ms.getTenMau()
            });
        }
    }

    private void loadDataKC(ArrayList<KichCo> kclist) {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) tblThuocTinh.getModel();
        dtm.setRowCount(0);
        for (KichCo kc : kclist) {
            dtm.addRow(new Object[]{
                i++,
                kc.getId(),
                kc.getTenKichCo()
            });
        }
    }

    private void loadDataCL(ArrayList<ChatLieu> cllist) {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) tblThuocTinh.getModel();
        dtm.setRowCount(0);
        for (ChatLieu cl : cllist) {
            dtm.addRow(new Object[]{
                i++,
                cl.getId(),
                cl.getTenChatLieu()
            });
        }
    }

    private void loadDataDM(ArrayList<DanhMuc> dmlist) {
        int i = 1;
        DefaultTableModel dtm = (DefaultTableModel) tblThuocTinh.getModel();
        dtm.setRowCount(0);
        for (DanhMuc dm : dmlist) {
            dtm.addRow(new Object[]{
                i++,
                dm.getId(),
                dm.getTenDanhMuc()
            });
        }
    }

    public MauSac getModelMS() {
        MauSac ms = new MauSac();
        ms.setId(Integer.valueOf(txtIDTT.getText()));
        ms.setTenMau(txtTenTT.getText());
        return ms;
    }

    public KichCo getModelKC() {
        KichCo kc = new KichCo();
        kc.setId(Integer.valueOf(txtIDTT.getText()));
        kc.setTenKichCo(txtTenTT.getText());
        return kc;
    }

    public ChatLieu getModelCL() {
        ChatLieu cl = new ChatLieu();
        cl.setId(Integer.valueOf(txtIDTT.getText()));
        cl.setTenChatLieu(txtTenTT.getText());
        return cl;
    }

    public DanhMuc getModelDM() {
        DanhMuc dm = new DanhMuc();
        dm.setId(Integer.valueOf(txtIDTT.getText()));
        dm.setTenDanhMuc(txtTenTT.getText());
        return dm;
    }

    public void fillModelMS(MauSac ms, int index) {
        txtIDTT.setText(ms.getId().toString());
        txtTenTT.setText(ms.getTenMau());
    }

    public void fillModelKC(KichCo kc, int index) {
        txtIDTT.setText(kc.getId().toString());
        txtTenTT.setText(kc.getTenKichCo());
    }

    public void fillModelCL(ChatLieu cl, int index) {
        txtIDTT.setText(cl.getId().toString());
        txtTenTT.setText(cl.getTenChatLieu());
    }

    public void fillModelDM(DanhMuc dm, int index) {
        txtIDTT.setText(dm.getId().toString());
        txtTenTT.setText(dm.getTenDanhMuc());
    }

    private void clearTT() {
        txtIDTT.setText("0");
        txtTenTT.setText("");
    }

    private void addMS() {
        String kq = PLS.addMS(getModelMS());
        JOptionPane.showMessageDialog(this, kq);
        loadDataMS(PLS.getListMau());
    }

    private void addKC() {
        String kq = PLS.addKC(getModelKC());
        JOptionPane.showMessageDialog(this, kq);
        loadDataKC(PLS.getListKichCo());
    }

    private void addCL() {
        String kq = PLS.addCL(getModelCL());
        JOptionPane.showMessageDialog(this, kq);
        loadDataCL(PLS.getListChatLieu());
    }

    private void addDM() {
        String kq = PLS.addDM(getModelDM());
        JOptionPane.showMessageDialog(this, kq);
        loadDataDM(PLS.getListDM());
    }

    private void updateMS() {
        String kq = PLS.updateMS(getModelMS());
        JOptionPane.showMessageDialog(this, kq);
        loadDataMS(PLS.getListMau());
    }

    private void updateKC() {
        String kq = PLS.updateKC(getModelKC());
        JOptionPane.showMessageDialog(this, kq);
        loadDataKC(PLS.getListKichCo());
    }

    private void updateCL() {
        String kq = PLS.updateCL(getModelCL());
        JOptionPane.showMessageDialog(this, kq);
        loadDataCL(PLS.getListChatLieu());
    }

    private void updateDM() {
        String kq = PLS.updateDM(getModelDM());
        JOptionPane.showMessageDialog(this, kq);
        loadDataDM(PLS.getListDM());
    }

    private void deleteMS() {
        String kq = PLS.deleteMS(getModelMS());
        JOptionPane.showMessageDialog(this, kq);
        loadDataMS(PLS.getListMau());
    }

    private void deleteKC() {
        String kq = PLS.deleteKC(getModelKC());
        JOptionPane.showMessageDialog(this, kq);
        loadDataKC(PLS.getListKichCo());
    }

    private void deleteCL() {
        String kq = PLS.deleteCL(getModelCL());
        JOptionPane.showMessageDialog(this, kq);
        loadDataCL(PLS.getListChatLieu());
    }

    private void deleteDM() {
        String kq = PLS.deleteDM(getModelDM());
        JOptionPane.showMessageDialog(this, kq);
        loadDataDM(PLS.getListDM());
    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelSP = new javax.swing.JPanel();
        panelTTSP = new javax.swing.JTabbedPane();
        panelThongTin = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cboKichCo = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        btnThemThongTin = new javax.swing.JButton();
        btnSuaThongTin = new javax.swing.JButton();
        btnAnThongTin = new javax.swing.JButton();
        btnHienThiThongTin = new javax.swing.JButton();
        btnResetThongTin = new javax.swing.JButton();
        cboDanhMuc = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtareaDesc = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        btnImportEX = new javax.swing.JButton();
        btnExportEX = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        panelThuocTinh = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        txtTenTT = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        rdoDanhMuc = new javax.swing.JRadioButton();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoChatLieu = new javax.swing.JRadioButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        btnThemThuocTinh = new javax.swing.JButton();
        btnSuaThuocTinh = new javax.swing.JButton();
        btnXoaThuocTinh = new javax.swing.JButton();
        btnResetThuocTinh = new javax.swing.JButton();
        rdoKichCo = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        txtIDTT = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(980, 530));

        panelTTSP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                panelTTSPStateChanged(evt);
            }
        });

        txtMaSP.setEditable(false);
        txtMaSP.setText("0");
        txtMaSP.setEnabled(false);

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel31.setText("Mã sản phẩm:");

        jLabel32.setText("Tên sản phẩm:");

        jLabel33.setText("Chất Liệu:");

        jLabel34.setText("Màu sắc:");

        jLabel35.setText("Kích cỡ:");

        cboKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel36.setText("Đơn giá:");

        jLabel37.setText("Số lượng:");

        btnThemThongTin.setText("Thêm");
        btnThemThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThongTinActionPerformed(evt);
            }
        });

        btnSuaThongTin.setText("Sửa");
        btnSuaThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThongTinActionPerformed(evt);
            }
        });

        btnAnThongTin.setText("Ẩn");
        btnAnThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnThongTinActionPerformed(evt);
            }
        });

        btnHienThiThongTin.setText("Hiển thị các sản phẩm đã ẩn");
        btnHienThiThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThiThongTinActionPerformed(evt);
            }
        });

        btnResetThongTin.setText("Reset");
        btnResetThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetThongTinActionPerformed(evt);
            }
        });

        cboDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel49.setText("Danh mục:");

        txtareaDesc.setColumns(20);
        txtareaDesc.setRows(5);
        jScrollPane9.setViewportView(txtareaDesc);

        jLabel27.setText("Mô tả:");

        btnLoc.setText("Lọc sản phẩm");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnImportEX.setText("Nhập Excel");
        btnImportEX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnImportEXMousePressed(evt);
            }
        });
        btnImportEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportEXActionPerformed(evt);
            }
        });

        btnExportEX.setText("Xuất Excel");
        btnExportEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportEXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                    .addComponent(cboChatLieu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboKichCo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtSoLuong))
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(jLabel49)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cboDanhMuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(43, 43, 43)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnResetThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnThemThongTin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaThongTin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnThongTin))
                    .addComponent(btnHienThiThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnImportEX, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportEX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(287, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel36)
                    .addComponent(btnThemThongTin)
                    .addComponent(btnSuaThongTin)
                    .addComponent(btnAnThongTin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel37)
                    .addComponent(btnHienThiThongTin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(btnResetThongTin)
                    .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(btnLoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImportEX)
                            .addComponent(btnExportEX))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Mô tả", "Danh mục", "Màu sắc", "Kích cỡ", "Chất liệu", "Số lượng", "Đơn giá"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSPMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tblSP);

        javax.swing.GroupLayout panelThongTinLayout = new javax.swing.GroupLayout(panelThongTin);
        panelThongTin.setLayout(panelThongTinLayout);
        panelThongTinLayout.setHorizontalGroup(
            panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelThongTinLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        panelThongTinLayout.setVerticalGroup(
            panelThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        panelTTSP.addTab("Thông tin sản phẩm", panelThongTin);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel38.setText("Thêm thuộc tính");

        jLabel39.setText("Tên thuộc tính:");

        buttonGroup1.add(rdoDanhMuc);
        rdoDanhMuc.setText("Danh mục");
        rdoDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDanhMucActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMauSac);
        rdoMauSac.setSelected(true);
        rdoMauSac.setText("Màu sắc");
        rdoMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMauSacActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoChatLieu);
        rdoChatLieu.setText("Chất Liệu");
        rdoChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChatLieuActionPerformed(evt);
            }
        });

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Thuộc Tính", "Tên Thuộc Tính"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblThuocTinhMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblThuocTinh);

        btnThemThuocTinh.setText("Thêm");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnSuaThuocTinh.setText("Sửa");
        btnSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThuocTinhActionPerformed(evt);
            }
        });

        btnXoaThuocTinh.setText("Xoá");
        btnXoaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThuocTinhActionPerformed(evt);
            }
        });

        btnResetThuocTinh.setText("Reset");
        btnResetThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetThuocTinhActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoKichCo);
        rdoKichCo.setText("Kích cỡ");
        rdoKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKichCoActionPerformed(evt);
            }
        });

        jLabel26.setText("ID:");

        txtIDTT.setEditable(false);
        txtIDTT.setText("0");
        txtIDTT.setEnabled(false);

        javax.swing.GroupLayout panelThuocTinhLayout = new javax.swing.GroupLayout(panelThuocTinh);
        panelThuocTinh.setLayout(panelThuocTinhLayout);
        panelThuocTinhLayout.setHorizontalGroup(
            panelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThuocTinhLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(panelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelThuocTinhLayout.createSequentialGroup()
                        .addGroup(panelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThuocTinhLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelThuocTinhLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIDTT, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoDanhMuc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(panelThuocTinhLayout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(rdoKichCo)
                                .addGap(27, 27, 27)))
                        .addGroup(panelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoChatLieu)
                            .addComponent(rdoMauSac))
                        .addGap(26, 26, 26)
                        .addGroup(panelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThuocTinhLayout.createSequentialGroup()
                                .addComponent(btnThemThuocTinh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaThuocTinh))
                            .addGroup(panelThuocTinhLayout.createSequentialGroup()
                                .addComponent(btnSuaThuocTinh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnResetThuocTinh)))))
                .addContainerGap(352, Short.MAX_VALUE))
        );
        panelThuocTinhLayout.setVerticalGroup(
            panelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThuocTinhLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(rdoChatLieu)
                    .addComponent(rdoKichCo)
                    .addComponent(btnXoaThuocTinh)
                    .addComponent(btnThemThuocTinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(rdoDanhMuc)
                    .addComponent(rdoMauSac)
                    .addComponent(btnSuaThuocTinh)
                    .addComponent(btnResetThuocTinh)
                    .addComponent(jLabel26)
                    .addComponent(txtIDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTTSP.addTab("Thuộc tính sản phẩm", panelThuocTinh);

        javax.swing.GroupLayout panelSPLayout = new javax.swing.GroupLayout(panelSP);
        panelSP.setLayout(panelSPLayout);
        panelSPLayout.setHorizontalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelTTSP)
                .addContainerGap())
        );
        panelSPLayout.setVerticalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTTSP)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1194, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThongTinActionPerformed
        if (ValidateSP()) {
            addSP();
        }
    }//GEN-LAST:event_btnThemThongTinActionPerformed

    private void btnSuaThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinActionPerformed
        updateSP();
    }//GEN-LAST:event_btnSuaThongTinActionPerformed

    private void btnAnThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnThongTinActionPerformed
        hideSP();
    }//GEN-LAST:event_btnAnThongTinActionPerformed

    private void btnHienThiThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThiThongTinActionPerformed
        unhideSP();
    }//GEN-LAST:event_btnHienThiThongTinActionPerformed

    private void btnResetThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetThongTinActionPerformed
        clearSP();
    }//GEN-LAST:event_btnResetThongTinActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        isSPfilter = true;
        String name = txtTenSP.getText();
        loadDataSP(PLS.getListSP(name));
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnImportEXMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportEXMousePressed
        importExcel();
    }//GEN-LAST:event_btnImportEXMousePressed

    private void btnImportEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportEXActionPerformed

    }//GEN-LAST:event_btnImportEXActionPerformed

    private void btnExportEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportEXActionPerformed
        exportExcel();
    }//GEN-LAST:event_btnExportEXActionPerformed

    private void tblSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMousePressed
        int index = tblSP.getSelectedRow();
        SanPham sp = PLS.getListSP().get(index);
        fillModelSP(sp);
    }//GEN-LAST:event_tblSPMousePressed

    private void rdoDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDanhMucActionPerformed
        loadDataDM(PLS.getListDM());
    }//GEN-LAST:event_rdoDanhMucActionPerformed

    private void rdoMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMauSacActionPerformed
        loadDataMS(PLS.getListMau());
    }//GEN-LAST:event_rdoMauSacActionPerformed

    private void rdoChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChatLieuActionPerformed
        loadDataCL(PLS.getListChatLieu());
    }//GEN-LAST:event_rdoChatLieuActionPerformed

    private void tblThuocTinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMousePressed
        int index = tblThuocTinh.getSelectedRow();
        if (rdoMauSac.isSelected()) {
            MauSac ms = PLS.getListMau().get(index);
            fillModelMS(ms, index);
        } else if (rdoKichCo.isSelected()) {
            KichCo ks = PLS.getListKichCo().get(index);
            fillModelKC(ks, index);
        } else if (rdoChatLieu.isSelected()) {
            ChatLieu cl = PLS.getListChatLieu().get(index);
            fillModelCL(cl, index);
        } else if (rdoDanhMuc.isSelected()) {
            DanhMuc dm = PLS.getListDM().get(index);
            fillModelDM(dm, index);
        }
    }//GEN-LAST:event_tblThuocTinhMousePressed

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        if (ValidateTTSP()) {
            if (rdoMauSac.isSelected()) {
                addMS();
            } else if (rdoKichCo.isSelected()) {
                addKC();
            } else if (rdoChatLieu.isSelected()) {
                addCL();
            } else if (rdoDanhMuc.isSelected()) {
                addDM();
            }
        }
    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void btnSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThuocTinhActionPerformed
        if (ValidateTTSP()) {
            if (rdoMauSac.isSelected()) {
                updateMS();
            } else if (rdoKichCo.isSelected()) {
                updateKC();
            } else if (rdoChatLieu.isSelected()) {
                updateCL();
            } else if (rdoDanhMuc.isSelected()) {
                updateDM();
            }
        }
    }//GEN-LAST:event_btnSuaThuocTinhActionPerformed

    private void btnXoaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThuocTinhActionPerformed
        if (rdoMauSac.isSelected()) {
            deleteMS();
        } else if (rdoKichCo.isSelected()) {
            deleteKC();
        } else if (rdoChatLieu.isSelected()) {
            deleteCL();
        } else if (rdoDanhMuc.isSelected()) {
            deleteDM();
        }
    }//GEN-LAST:event_btnXoaThuocTinhActionPerformed

    private void btnResetThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetThuocTinhActionPerformed
        clearTT();
    }//GEN-LAST:event_btnResetThuocTinhActionPerformed

    private void rdoKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKichCoActionPerformed
        loadDataKC(PLS.getListKichCo());
    }//GEN-LAST:event_rdoKichCoActionPerformed

    private void panelTTSPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_panelTTSPStateChanged
        loadDataSP(PLS.getListSP());
        loadcboCL(PLS.getListChatLieu());
        loadcboDM(PLS.getListDM());
        loadcboKC(PLS.getListKichCo());
        loadcboMS(PLS.getListMau());
    }//GEN-LAST:event_panelTTSPStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnThongTin;
    private javax.swing.JButton btnExportEX;
    private javax.swing.JButton btnHienThiThongTin;
    private javax.swing.JButton btnImportEX;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnResetThongTin;
    private javax.swing.JButton btnResetThuocTinh;
    private javax.swing.JButton btnSuaThongTin;
    private javax.swing.JButton btnSuaThuocTinh;
    private javax.swing.JButton btnThemThongTin;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnXoaThuocTinh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel panelSP;
    private javax.swing.JTabbedPane panelTTSP;
    private javax.swing.JPanel panelThongTin;
    private javax.swing.JPanel panelThuocTinh;
    private javax.swing.JRadioButton rdoChatLieu;
    private javax.swing.JRadioButton rdoDanhMuc;
    private javax.swing.JRadioButton rdoKichCo;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtIDTT;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenTT;
    private javax.swing.JTextArea txtareaDesc;
    // End of variables declaration//GEN-END:variables
}
