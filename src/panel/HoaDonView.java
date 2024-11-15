/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panel;

import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.NhanVien;
import Model.PhuongThucTT;
import Model.SanPham;
import Service.poloService;
import Validate.Validate;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import Model.TableCellListener;
import static java.awt.Color.WHITE;
import java.awt.event.ActionEvent;
import java.text.MessageFormat;
import javax.swing.JOptionPane;
import view.main;

/**
 *
 * @author Duc Long
 */
public class HoaDonView extends javax.swing.JPanel {

    private boolean isSPfilter1;
    private boolean isSPfilter2;
    private boolean isSPfilter3;
    private boolean isSPfilter4;

    boolean isNVSearch, isKHSearch, isTKDateSearch, isTKIDSearch, isSPfilter = false;
    poloService PLS = new poloService();
    NhanVien nhanVien = null;

    public HoaDonView() {
        initComponents();
//        loadDataKH(PLS.getListKH());
        ArrayList<KhachHang> khList = PLS.getListKH();
        loadDataSP(PLS.getListSP());
        loadDataDSSP(PLS.getListSP());
        loadDataDSHD(PLS.getListHDCHT());
        loadcboPTTT(PLS.getListPT());
        addTableGioHangCellListener();

    }

    private void XoaSPGH() throws NumberFormatException {
        int index = tblGioHang.getSelectedRow();
        Integer id = Integer.valueOf(txtMaHD.getText());
        HoaDonChiTiet hdct = PLS.getHDCT(id).get(index);
        xoaHDCT(hdct);
        PLS.xoaHDCT(hdct.getMaHD(), hdct.getMaSP());
        loadDataHDCT(PLS.getHDCT(id));
        loadDataDSSP(PLS.getListSP());
        loadDataSP(PLS.getListSP());
        sumHD(id);
    }

    private void sumHD(int maHD) throws NumberFormatException {
        if (isSPfilter) {
            String name = txtLocSP.getText();
            double sum = 0;
            for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                double value = Double.parseDouble(tblGioHang.getValueAt(i, 7).toString().replace(".", "").replace(" VND", ""));
                sum += value;
            }
            PLS.updateHD(sum, maHD);
            loadDataDSSP(PLS.getListSP(name));
            if (rdoDaTT.isSelected()) {
                loadDataDSHD(PLS.getListHDHT());
            } else {
                loadDataDSHD(PLS.getListHDCHT());
            }
            txtTong.setText(new DecimalFormat("#,###").format(sum) + " VND");
            txtCanTra.setText(txtTong.getText());
        } else {
            double sum = 0;
            for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                double value = Double.parseDouble(tblGioHang.getValueAt(i, 7).toString().replace(".", "").replace(" VND", ""));
                sum += value;
            }
            PLS.updateHD(sum, maHD);
            loadDataDSSP(PLS.getListSP());
            if (rdoDaTT.isSelected()) {
                loadDataDSHD(PLS.getListHDHT());
            } else {
                loadDataDSHD(PLS.getListHDCHT());
            }
            txtTong.setText(new DecimalFormat("#,###").format(sum) + " VND");
            txtCanTra.setText(txtTong.getText());
        }

    }

//    private void loadDataKH(ArrayList<KhachHang> khlist) {
//        DefaultTableModel dtm = (DefaultTableModel) tblKH.getModel();
//        dtm.setRowCount(0);
//        for (KhachHang kh : khlist) {
//            dtm.addRow(new Object[]{
//                kh.getId(),
//                kh.getTenKhachHang(),
//                kh.getSoDienThoai()
//            });
//        }
//    }
    public void loadDataSP(ArrayList<SanPham> splist) {
        DefaultTableModel dtm = (DefaultTableModel) tblDSSP.getModel();
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

    private void loadDataDSSP(ArrayList<SanPham> splist) {
        DefaultTableModel dtm = (DefaultTableModel) tblDSSP.getModel();
        dtm.setRowCount(0);
        for (SanPham sp : splist) {
            dtm.addRow(new Object[]{
                sp.getId(),
                sp.getTenSP(),
                sp.getMauSac(),
                sp.getKichCo(),
                sp.getChatLieu(),
                sp.getSoLuong(),});
        }
    }

    private void loadDataDSHD(ArrayList<HoaDon> hdlist) {
        DefaultTableModel dtm = (DefaultTableModel) tblDSHD.getModel();
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

    private void loadDataHDCT(ArrayList<HoaDonChiTiet> hdctlist) {
        DefaultTableModel dtm = (DefaultTableModel) tblGioHang.getModel();
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
                new DecimalFormat("#,###").format(hdct.getTong()) + " VND"
            });
            System.out.println(hdct.getTong());
        }
    }

    private void addTableGioHangCellListener() {
        TableCellListener tcl = new TableCellListener(tblGioHang, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableCellListener tcl = (TableCellListener) e.getSource();
                int row = tcl.getRow();
                int oldValue = Integer.parseInt(tcl.getOldValue().toString());
                int newValue = Integer.parseInt(tcl.getNewValue().toString());
                int soLuongDieuChinh = 0;
                soLuongDieuChinh = newValue - oldValue;

                int maHD = Integer.parseInt(txtMaHD.getText());
                int maSP = (int) tblGioHang.getValueAt(row, 0);
                int soLuongTon = PLS.getListSP(maSP).get(0).getSoLuong();
                if (newValue > soLuongTon + oldValue) {
                    JOptionPane.showMessageDialog(panelHD, "Không đủ hàng tồn!");
                    tblGioHang.setValueAt(oldValue, row, tcl.getColumn());
                } else if (newValue < 0) {
                    JOptionPane.showMessageDialog(panelHD, "Không nhập số âm!");
                    tblGioHang.setValueAt(oldValue, row, tcl.getColumn());
                } else if (newValue == 0) {
                    XoaSPGH();
                } else {
                    soLuongTon -= soLuongDieuChinh;
                    PLS.updateSP(maSP, soLuongTon);
                    PLS.updateGH(maHD, maSP, newValue);
                    loadDataDSSP(PLS.getListSP());
                    loadDataHDCT(PLS.getHDCT(maHD));
                    sumHD(maHD);
                }
            }
        });
    }

    private HoaDon getModelHD() {
        HoaDon hd = new HoaDon();
        main main1 = new main();
        hd.setId(0);
        hd.setIdNV(Integer.valueOf(main1.IDNVHTai()));
        hd.setIdKH(PLS.getIDKH(txtSDTHD.getText()).getId());
        hd.setIdPTTT(PLS.getPTid(cboHTTT.getSelectedItem().toString()).getId());
        hd.setNgayTao(LocalDate.now());
        hd.setTong(0);
        return hd;
    }

    private void loadcboPTTT(ArrayList<PhuongThucTT> ptlist) {
        DefaultComboBoxModel dcbo = (DefaultComboBoxModel) cboHTTT.getModel();
        dcbo.removeAllElements();
        for (PhuongThucTT pt : ptlist) {
            dcbo.addElement(pt.getHinhThuc());
        }
    }

    private void addHD() {
        String kq = PLS.addHD(getModelHD());
        JOptionPane.showMessageDialog(this, kq);
        loadDataDSHD(PLS.getListHDCHT());
        txtMaHD.setText(PLS.getListHDCHT().get(PLS.getListHDCHT().size() - 1).getId().toString());
    }

    private void huyHD(int id) {
        String kq = PLS.huyHD(id);
        JOptionPane.showMessageDialog(this, kq);
        loadDataDSHD(PLS.getListHDCHT());
    }

    private void fillModelHD(HoaDon hd) {
        txtMaHD.setText(hd.getId().toString());
        txtTenKHHD.setText(hd.getTenKhachHang());
        txtSDTHD.setText(hd.getSoDienThoai());
        cboHTTT.setSelectedItem(hd.getPttt());
        txtTong.setText(new DecimalFormat("#,###").format(hd.getTong()) + " VND");
        txtCanTra.setText(txtTong.getText());
    }

    private void clearHD() {
        txtMaHD.setText("");
        txtTenKHHD.setText("");
        txtSDTHD.setText("");
        cboHTTT.setSelectedIndex(0);
        txtTong.setText("");
        txtCanTra.setText("");
        txtTienKhachDua.setText("");
        txtTienThua.setText("");
        isSPfilter = false;
        txtSDTHD.setBackground(WHITE);
        loadDataDSSP(PLS.getListSP());
    }

    private void thanhToan(int id) {
        String kq = PLS.thanhtoanHD(id);
        JOptionPane.showMessageDialog(this, kq);
        loadDataDSHD(PLS.getListHDCHT());
        DefaultTableModel dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
        hoaDon();
        clearHD();
    }

    private void huyHDCT(ArrayList<HoaDonChiTiet> hdctlist) {
        for (HoaDonChiTiet hdct : hdctlist) {
            Integer soLuongMua = hdct.getSoLuong();
            Integer maSP = hdct.getMaSP();
            Integer soLuongTon = PLS.getListSP(maSP).get(0).getSoLuong() + soLuongMua;
            PLS.updateSP(maSP, soLuongTon);
        }
    }

    private void xoaHDCT(HoaDonChiTiet hdct) {
        Integer soLuongMua = hdct.getSoLuong();
        Integer maSP = hdct.getMaSP();
        Integer soLuongTon = PLS.getListSP(maSP).get(0).getSoLuong() + soLuongMua;
        PLS.updateSP(maSP, soLuongTon);
    }

    public boolean ValidateHD() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isEmpty(txtSDTHD, stb, "Chưa nhập SĐT khách hàng");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    public boolean ValidateSDTHD() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isPhone(txtSDTHD, stb, "Không đúng định dạng SĐT");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    public boolean ValidateTienKhachDua() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isNumber(txtTienKhachDua, stb, "Không đúng định dạng tiền", 1);
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }

    public boolean ValidateLocSP() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();
        v.isString(txtLocSP, stb, "Vui lòng nhập tên sản phẩm!");
        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelHD = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSSP = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDSHD = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        panelTaoHoaDon = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTenKHHD = new javax.swing.JTextField();
        txtSDTHD = new javax.swing.JTextField();
        lbTong = new javax.swing.JLabel();
        lbCanTra = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cboHTTT = new javax.swing.JComboBox<>();
        txtTienKhachDua = new javax.swing.JTextField();
        txtTienThua = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnTaoHD = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtTong = new javax.swing.JTextField();
        txtCanTra = new javax.swing.JTextField();
        btnResetHD = new javax.swing.JButton();
        btnBill = new javax.swing.JButton();
        rdoChuaTT = new javax.swing.JRadioButton();
        rdoDaTT = new javax.swing.JRadioButton();
        btnLocSP = new javax.swing.JButton();
        txtLocSP = new javax.swing.JTextField();
        btnXoaSPGH = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(980, 530));

        tblDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDSP", "Sản Phẩm", "Màu Sắc", "Kích Cỡ", "Chất Liệu", "Tồn Kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDSSP);

        tblDSHD.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDSHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSHDMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDSHD);

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblGioHang);

        jLabel20.setText("Danh sách hoá đơn");

        jLabel21.setText("Danh sách sản phẩm");

        jLabel22.setText("Giỏ hàng");

        panelTaoHoaDon.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Tạo hoá đơn");

        jLabel24.setText("Tên KH:");

        jLabel25.setText("SĐT:");

        txtTenKHHD.setEnabled(false);

        txtSDTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTHDActionPerformed(evt);
            }
        });

        lbTong.setText("Tổng tiền hàng: ");

        lbCanTra.setText("Khách cần trả: ");

        jLabel28.setText("HT Thanh toán:");

        jLabel29.setText("Tiền khách đưa:");

        jLabel30.setText("Tiền thừa:");

        cboHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtTienKhachDua.setEnabled(false);
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });

        txtTienThua.setEditable(false);
        txtTienThua.setEnabled(false);
        txtTienThua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThuaActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnTaoHD.setText("Tạo hoá đơn");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel50.setText("Mã HĐ:");

        txtMaHD.setEditable(false);
        txtMaHD.setEnabled(false);

        txtTong.setEditable(false);
        txtTong.setEnabled(false);

        txtCanTra.setEditable(false);
        txtCanTra.setEnabled(false);

        btnResetHD.setText("Reset");
        btnResetHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetHDActionPerformed(evt);
            }
        });

        btnBill.setText("In hóa đơn");
        btnBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTaoHoaDonLayout = new javax.swing.GroupLayout(panelTaoHoaDon);
        panelTaoHoaDon.setLayout(panelTaoHoaDonLayout);
        panelTaoHoaDonLayout.setHorizontalGroup(
            panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTaoHoaDonLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBill, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelTaoHoaDonLayout.createSequentialGroup()
                            .addComponent(jLabel50)
                            .addGap(18, 18, 18)
                            .addComponent(txtMaHD))
                        .addGroup(panelTaoHoaDonLayout.createSequentialGroup()
                            .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24)
                                .addComponent(jLabel25))
                            .addGap(18, 18, 18)
                            .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTenKHHD)
                                .addComponent(txtSDTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelTaoHoaDonLayout.createSequentialGroup()
                            .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel29)
                                .addComponent(jLabel30)
                                .addComponent(jLabel28))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboHTTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTienKhachDua)
                                .addComponent(txtTienThua)))
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTaoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTaoHoaDonLayout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addGap(72, 72, 72))
                        .addGroup(panelTaoHoaDonLayout.createSequentialGroup()
                            .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbTong)
                                .addComponent(lbCanTra))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCanTra)
                                .addComponent(txtTong)))
                        .addComponent(btnResetHD, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelTaoHoaDonLayout.setVerticalGroup(
            panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTaoHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(13, 13, 13)
                .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtTenKHHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtSDTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTong)
                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCanTra)
                    .addComponent(txtCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTaoHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaoHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetHD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBill)
                .addContainerGap())
        );

        buttonGroup1.add(rdoChuaTT);
        rdoChuaTT.setSelected(true);
        rdoChuaTT.setText("Chưa thanh toán");
        rdoChuaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChuaTTActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaTT);
        rdoDaTT.setText("Đã thanh toán");
        rdoDaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaTTActionPerformed(evt);
            }
        });

        btnLocSP.setText("Lọc SP");
        btnLocSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocSPActionPerformed(evt);
            }
        });

        btnXoaSPGH.setText("Xoá");
        btnXoaSPGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPGHActionPerformed(evt);
            }
        });

        jScrollPane1.setMinimumSize(new java.awt.Dimension(0, 0));

        area.setColumns(20);
        area.setRows(5);
        jScrollPane1.setViewportView(area);

        javax.swing.GroupLayout panelHDLayout = new javax.swing.GroupLayout(panelHD);
        panelHD.setLayout(panelHDLayout);
        panelHDLayout.setHorizontalGroup(
            panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHDLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelHDLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(rdoChuaTT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoDaTT)
                        .addGap(21, 21, 21))
                    .addGroup(panelHDLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 429, Short.MAX_VALUE)
                        .addComponent(btnXoaSPGH))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                    .addGroup(panelHDLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtLocSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLocSP))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(panelTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        panelHDLayout.setVerticalGroup(
            panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHDLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(rdoChuaTT)
                            .addComponent(rdoDaTT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnXoaSPGH, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelHDLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelHDLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLocSP)
                                .addComponent(txtLocSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(panelHDLayout.createSequentialGroup()
                        .addComponent(panelTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDSSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSPMouseClicked
        if (isSPfilter) {
            int index = tblDSSP.getSelectedRow();
            String name = txtLocSP.getText();
            if (PLS.getListSP(name).get(index).getSoLuong() == 0) {
                JOptionPane.showMessageDialog(this, "Không còn hàng tồn!");
            } else {
                Integer soLuongMua = 1;
                int maHD = Integer.parseInt(txtMaHD.getText());
                int maSP = PLS.getListSP(name).get(index).getId();
                int soLuongTon = PLS.getListSP(name).get(index).getSoLuong() - soLuongMua;
                PLS.addGH(maHD, maSP, soLuongMua);
                PLS.updateSP(maSP, soLuongTon);
                loadDataHDCT(PLS.getHDCT(maHD));
                loadDataSP(PLS.getListSP());
                sumHD(maHD);
            }
        } else {
            int index = tblDSSP.getSelectedRow();
            if (PLS.getListSP().get(index).getSoLuong() == 0) {
                JOptionPane.showMessageDialog(this, "Không còn hàng tồn!");
            } else {
                Integer soLuongMua = 1;
                int maHD = Integer.parseInt(txtMaHD.getText());
                int maSP = PLS.getListSP().get(index).getId();
                int soLuongTon = PLS.getListSP().get(index).getSoLuong() - soLuongMua;
                PLS.addGH(maHD, maSP, soLuongMua);
                PLS.updateSP(maSP, soLuongTon);
                loadDataHDCT(PLS.getHDCT(maHD));
                loadDataSP(PLS.getListSP());
                sumHD(maHD);
            }
        }
        if (!txtTienKhachDua.getText().isBlank()) {
            if (ValidateTienKhachDua()) {
                String tienCanTra = txtCanTra.getText().replace(".", "").replace(" VND", "");
                double value = Double.parseDouble(txtTienKhachDua.getText()) - Double.parseDouble(tienCanTra);
                txtTienThua.setText(new DecimalFormat("#,###").format(value) + " VND");
            }
        }
    }//GEN-LAST:event_tblDSSPMouseClicked

    private void tblDSHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSHDMouseClicked
        if (rdoDaTT.isSelected()) {
            int index = tblDSHD.getSelectedRow();
            HoaDon hd = PLS.getListHDHT().get(index);
            Integer MaHD = hd.getId();
            loadDataHDCT(PLS.getHDCT(MaHD));
            fillModelHD(hd);
        }
        if (rdoChuaTT.isSelected()) {
            int index = tblDSHD.getSelectedRow();
            HoaDon hd = PLS.getListHDCHT().get(index);
            Integer MaHD = hd.getId();
            loadDataHDCT(PLS.getHDCT(MaHD));
            fillModelHD(hd);
            txtTienKhachDua.setEnabled(true);
        }
    }//GEN-LAST:event_tblDSHDMouseClicked

    private void txtSDTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTHDActionPerformed
        if (ValidateSDTHD()) {
            String phoneNum = txtSDTHD.getText();
            KhachHang kh = PLS.getIDKH(phoneNum);
            if (kh.getSoDienThoai() != null) {
                txtTenKHHD.setText(kh.getTenKhachHang());
                txtSDTHD.setText(kh.getSoDienThoai());
            } else {
                int option = JOptionPane.showConfirmDialog(this, "Khách hàng không tồn tại! Bạn có muốn thêm mới?", "Thêm mới KH", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    String tenKh = JOptionPane.showInputDialog(this, "Nhập tên KH:", "Thêm mới KH", JOptionPane.INFORMATION_MESSAGE);

                    if (tenKh == null) {
                        return;
                    }

                    KhachHang kh1 = new KhachHang();
                    kh1.setTenKhachHang(tenKh);
                    kh1.setSoDienThoai(phoneNum);
                    PLS.addKH(kh1);
                    txtTenKHHD.setText(kh1.getTenKhachHang());
                }
//                loadDataKH(PLS.getListKH());
                ArrayList<KhachHang> khList = PLS.getListKH();
            }
        }
    }//GEN-LAST:event_txtSDTHDActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        if (ValidateTienKhachDua()) {
            String tienCanTra = txtCanTra.getText().replace(".", "").replace(" VND", "");
            double value = Double.parseDouble(txtTienKhachDua.getText()) - Double.parseDouble(tienCanTra);
            txtTienThua.setText(new DecimalFormat("#,###").format(value) + " VND");
        }
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (txtMaHD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelHD, "Chưa chọn Hoá Đơn!");
        } else {
            int id = Integer.valueOf(txtMaHD.getText());
            thanhToan(id);
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        if (ValidateHD()) {
            addHD();
            txtTienKhachDua.setEnabled(true);
        }
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed

        if (txtMaHD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panelHD, "Chưa chọn Hoá Đơn!");
        } else {
            Integer id = Integer.valueOf(txtMaHD.getText());
            huyHDCT(PLS.getHDCT(id));
            PLS.xoaHDCT(id);
            loadDataDSSP(PLS.getListSP());
            huyHD(id);
            DefaultTableModel dtm = (DefaultTableModel) tblGioHang.getModel();
            dtm.setRowCount(0);
            clearHD();
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnResetHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetHDActionPerformed
        clearHD();
    }//GEN-LAST:event_btnResetHDActionPerformed

    private void rdoChuaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChuaTTActionPerformed
        loadDataDSHD(PLS.getListHDCHT());
        DefaultTableModel dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
        txtTenKHHD.setEnabled(false);
        txtSDTHD.setEnabled(true);
        txtTienKhachDua.setEnabled(false);
        cboHTTT.setEnabled(true);
        btnThanhToan.setEnabled(true);
        btnHuy.setEnabled(true);
        btnTaoHD.setEnabled(true);
        clearHD();
    }//GEN-LAST:event_rdoChuaTTActionPerformed

    private void rdoDaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaTTActionPerformed
        loadDataDSHD(PLS.getListHDHT());
        DefaultTableModel dtm = (DefaultTableModel) tblGioHang.getModel();
        dtm.setRowCount(0);
        txtTenKHHD.setEnabled(false);
        txtSDTHD.setEnabled(false);
        txtTienKhachDua.setEnabled(false);
        cboHTTT.setEnabled(false);
        btnThanhToan.setEnabled(false);
        btnHuy.setEnabled(false);
        btnTaoHD.setEnabled(false);
        clearHD();
    }//GEN-LAST:event_rdoDaTTActionPerformed

    private void btnLocSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocSPActionPerformed
        if (ValidateLocSP()) {
            isSPfilter = true;
            String name = txtLocSP.getText();
            loadDataDSSP(PLS.getListSP(name));
        }
    }//GEN-LAST:event_btnLocSPActionPerformed

    private void btnXoaSPGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPGHActionPerformed
        XoaSPGH();
        if (!txtTienKhachDua.getText().isBlank()) {
            txtTienKhachDuaActionPerformed(evt);
        }
    }//GEN-LAST:event_btnXoaSPGHActionPerformed

    private void btnBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBillActionPerformed
        // TODO add your handling code here:
        hoaDon();
    }//GEN-LAST:event_btnBillActionPerformed

    private void txtTienThuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThuaActionPerformed
    public void hoaDon() {
        StringBuilder contentBuilder = new StringBuilder();

        // Header
        contentBuilder.append("===========================================================\n");
        contentBuilder.append("*                        JACKET STORE                    |*\n");
        contentBuilder.append("*                80 Xuân Thủy, Cầu Giấy, Hà Nội          |*\n");
        contentBuilder.append("*                       SDT: 0336381788                  |*\n");
        contentBuilder.append("===========================================================\n");

        // Thông tin khách hàng và hóa đơn
        contentBuilder.append(String.format("Ngày: %s\n", "15/11/2024"));
        contentBuilder.append(String.format("Mã HĐ : %s\n", txtMaHD.getText()));
        contentBuilder.append(String.format("Tên KH : %s\n", txtTenKHHD.getText()));
        contentBuilder.append(String.format("SĐT : %s\n", txtSDTHD.getText()));
        contentBuilder.append(String.format("PTTT : %s\n", cboHTTT.getSelectedItem()));

        // Danh sách sản phẩm đã mua
        contentBuilder.append("                           Thông tin hóa đơn\n");
        contentBuilder.append("============================================================\n");
        contentBuilder.append(String.format("%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n", "Sản Phẩm", "Màu Sắc", "Kích Cỡ", "Chất Liệu", "Đơn Giá", "Số Lượng", "Tổng"));

        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            contentBuilder.append(String.format("%-15s%-15s%-15s%-15s%-10s%-10s%-10s\n",
                    tblGioHang.getValueAt(i, 1), // Sản Phẩm
                    tblGioHang.getValueAt(i, 2), // Màu Sắc
                    tblGioHang.getValueAt(i, 3), // Kích Cỡ
                    tblGioHang.getValueAt(i, 4), // Chất Liệu
                    tblGioHang.getValueAt(i, 5), // Đơn Giá
                    tblGioHang.getValueAt(i, 6), // Số Lượng
                    tblGioHang.getValueAt(i, 7))); // Tổng
        }

        // Tổng cộng và thanh toán
        contentBuilder.append("=========================================================================================================================\n");
        contentBuilder.append(String.format("Tổng tiền : %s\n", txtTong.getText()));
        contentBuilder.append(String.format("Tiền khách đưa : %s\n", txtTienKhachDua.getText()));
        contentBuilder.append(String.format("Tiền thừa : %s\n", txtTienThua.getText()));
        contentBuilder.append("*                              Giá đã bao gồm thuế GTGT                                                                  \n");
        contentBuilder.append("*************************************************************************************************************************\n");

        // Footer
        contentBuilder.append("*                             Cảm ơn quý khách đã mua hàng!                                                             *\n");
        contentBuilder.append("*                                      Hẹn gặp lại!                                                                     *\n");
        contentBuilder.append("*************************************************************************************************************************\n");
        contentBuilder.append("*                                Website : www.poloshop.vn                                                              *\n");

        // Gán nội dung cho JTextArea
        String content = contentBuilder.toString();
        System.out.println(content); // Để gỡ lỗi
        area.setText(content);

        MessageFormat header = new MessageFormat("HÓA ĐƠN POLOSHOP");
        MessageFormat footer = new MessageFormat("");
        try {
            area.print(header, footer);

            JOptionPane.showMessageDialog(this, "\n" + "In thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "\n" + "Thất bại" + "\n" + e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JButton btnBill;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLocSP;
    private javax.swing.JButton btnResetHD;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaSPGH;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboHTTT;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbCanTra;
    private javax.swing.JLabel lbTong;
    private javax.swing.JPanel panelHD;
    private javax.swing.JPanel panelTaoHoaDon;
    private javax.swing.JRadioButton rdoChuaTT;
    private javax.swing.JRadioButton rdoDaTT;
    private javax.swing.JTable tblDSHD;
    private javax.swing.JTable tblDSSP;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTextField txtCanTra;
    private javax.swing.JTextField txtLocSP;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtSDTHD;
    private javax.swing.JTextField txtTenKHHD;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
