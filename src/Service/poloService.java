/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.ChatLieu;
import Model.DanhMuc;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import java.util.ArrayList;
import Model.KhachHang;
import Model.KichCo;
import Model.MauSac;
import Model.NhanVien;
import Model.PhuongThucTT;
import Model.SanPham;
import Model.ThongKe;
import Repository.loginRepository;
import Repository.poloRepository;
import java.sql.*;

/**
 *
 * @author khoad
 */
public class poloService {
    poloRepository PLR = new poloRepository();
    loginRepository LGR = new loginRepository();
    
    // <editor-fold defaultstate="collapsed" desc="Panel Nhan Vien">
    public ArrayList<NhanVien> getListNV(){
        return PLR.getListNV();
    }
    
    public ArrayList<NhanVien> getListNVbyName(String name){
        return PLR.getListNVbyName(name);
    }
    
    public ArrayList<NhanVien> getListNVbyUsername(String name){
        return PLR.getListNVbyUsername(name);
    }
    
    public String addNV(NhanVien nv){
        boolean chk = PLR.addNV(nv);
        if (chk) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    public String updateNV(NhanVien nv){
        boolean chk = PLR.updateNV(nv);
        if (chk) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }
    
    public String updateDMK(NhanVien nv){
        boolean chk = PLR.updateNV(nv);
        if (chk) {
            return "Đổi mật khẩu thành công";
        } else {
            return "Đổi mật khẩu thất bại";
        }
    }
    
    public String deleteNV(NhanVien nv){
        boolean chk = PLR.deleteNV(nv);
        if (chk) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Panel Khach Hang">
    public ArrayList<KhachHang> getListKH(){
        return PLR.getListKH();
    }
    
    public ArrayList<KhachHang> getListKHbyName(String name){
        return PLR.getListKHbyName(name);
    }
    
    public String addKH(KhachHang kh){
        boolean chk = PLR.addKH(kh);
        if (chk) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    public String updateKH(KhachHang kh){
        boolean chk = PLR.updateKH(kh);
        if (chk) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }
    
    public String deleteKH(KhachHang kh){
        boolean chk = PLR.deleteKH(kh);
        if (chk) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Panel Thuoc Tinh">
    public ArrayList<MauSac> getListMau(){
        return PLR.getListMS();
    }
    
    public ArrayList<KichCo> getListKichCo(){
        return PLR.getListKC();
    }
    
    public ArrayList<ChatLieu> getListChatLieu(){
        return PLR.getListCL();
    }
    
    public ArrayList<DanhMuc> getListDM(){
        return PLR.getListDM();
    }
    
    public String addMS(MauSac ms){
        boolean chk = PLR.addMS(ms);
        if (chk) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    public String addKC(KichCo kc){
        boolean chk = PLR.addKC(kc);
        if (chk) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    public String addCL(ChatLieu cl){
        boolean chk = PLR.addCL(cl);
        if (chk) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    public String addDM(DanhMuc dm){
        boolean chk = PLR.addDM(dm);
        if (chk) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    public String updateMS(MauSac ms){
        boolean chk = PLR.updateMS(ms);
        if (chk) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật thất bại";
        }
    }
    
    public String updateKC(KichCo kc){
        boolean chk = PLR.updateKC(kc);
        if (chk) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật thất bại";
        }
    }
    
    public String updateCL(ChatLieu cl){
        boolean chk = PLR.updateCL(cl);
        if (chk) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật thất bại";
        }
    }
    
    public String updateDM(DanhMuc dm){
        boolean chk = PLR.updateDM(dm);
        if (chk) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật thất bại";
        }
    }
    
    public String deleteMS(MauSac ms){
        boolean chk = PLR.deleteMS(ms);
        if (chk) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }
    
    public String deleteKC(KichCo kc){
        boolean chk = PLR.deleteKC(kc);
        if (chk) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }
    
    public String deleteCL(ChatLieu cl){
        boolean chk = PLR.deleteCL(cl);
        if (chk) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }
    
    public String deleteDM(DanhMuc dm){
        boolean chk = PLR.deleteDM(dm);
        if (chk) {
            return "Xoá thành công";
        } else {
            return "Xoá thất bại";
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Panel San Pham">
    public ArrayList<SanPham> getListSP(){
        return PLR.getListSP();
    }
    
    public ArrayList<SanPham> getListSP(String name){
        return PLR.getListSP(name);
    }
    
    public ArrayList<SanPham> getListSP(int id){
        return PLR.getListSP(id);
    }
    
    public void hideSP(SanPham sp){
        PLR.hideSP(sp);
    }
    
    public void unhideSP(){
        PLR.unhideSP();
    }
    
    public MauSac getMSid(String ten){
        return PLR.getMSid(ten);
    }
    
    public KichCo getKCid(String ten){
        return PLR.getKCid(ten);
    }
    
    public ChatLieu getCLid(String ten){
        return PLR.getCLid(ten);
    }
    
    public DanhMuc getDMid(String ten){
        return PLR.getDMid(ten);
    }
    
    public String addSP(SanPham sp){
        boolean chk = PLR.addSP(sp);
        if (chk) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    public String updateTTSP(SanPham sp){
        boolean chk = PLR.updateTTSP(sp);
        if (chk) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật thất bại";
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Panel Hoa Don">
    public ArrayList<HoaDon> getListHDHT(){
        return PLR.getListHDHT();
    }
    
    public ArrayList<HoaDon> getListHDCHT(){
        return PLR.getListHDCHT();
    }
    
    public ArrayList<HoaDonChiTiet> getHDCT(Integer MaHD){
        return PLR.getHDCT(MaHD);
    }
    
    public ArrayList<PhuongThucTT> getListPT(){
        return PLR.getListPT();
    }
    
    public PhuongThucTT getPTid(String phuongThuc){
        return PLR.getPTid(phuongThuc);
    }
    
    public KhachHang getIDKH(String phoneNum){
        return PLR.getKHinfo(phoneNum);
    }
    
    public String addHD(HoaDon hd){
        boolean chk = PLR.addHD(hd);
        if (chk) {
            return "Tạo thành công";
        } else {
            return "Tạo thất bại";
        }
    }
    
    public String updateHD(double tong, int id){
        boolean chk = PLR.updateHD(tong, id);
        if (chk) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật không thành công";
        }
    }
    
    public String thanhtoanHD(int id){
        boolean chk = PLR.thanhtoanHD(id);
        if (chk) {
            return "Thanh toán thành công";
        } else {
            return "Thanh toán không thành công";
        }
    }
    
    public String huyHD(int id){
        boolean chk = PLR.huyHD(id);
        if (chk) {
            return "Huỷ thành công";
        } else {
            return "Huỷ không thành công";
        }
    }
    
    public void xoaHDCT(int id){
        PLR.xoaHDCT(id);
    }
    
    public void xoaHDCT(int MaHD, int MaSP){
        PLR.xoaHDCT(MaHD, MaSP);
    }
    
    public void addGH(int maHD, int maSP, int soLuongMua){
        PLR.addGH(maHD, maSP, soLuongMua);
    }
    
    public void updateGH(int maHD, int maSP, int soLuongMua){
        PLR.updateGH(maHD, maSP, soLuongMua);
    }
    
    public void updateSP(int maSP, int soLuong){
        PLR.updateSP(maSP, soLuong);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Panel Login">
    public NhanVien getNVLogin(String username, String pass){
        return LGR.getNVLogin(username, pass);
    }
    
    public boolean checkLogin(String username, String pass){
        return LGR.checkLogin(username, pass);
    }
    
    public String getUserRole(String username, String password) {
        return LGR.getUserRole(username, password);
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Panel Thong ke">
    public ArrayList<ThongKe> getListTK(){
        return PLR.getListTK();
    }
    
    public ArrayList<ThongKe> getListTK(Date ngayBatdau, Date ngayKetThuc){
        return PLR.getListTK(ngayBatdau, ngayKetThuc);
    }
    
    public ThongKe getDTngay(){
        return PLR.getDTngay();
    }
    
    public ThongKe getDTthang(){
        return PLR.getDTthang();
    }
    
    public ThongKe getDTnam(){
        return PLR.getDTnam();
    }
    
    public Integer getTongDon(){
        return PLR.getTongDon();
    }
    
    public ArrayList<HoaDon> getListHDHTDate(Date ngayBatdau, Date ngayKetThuc){
        return PLR.getListHDHTDate(ngayBatdau, ngayKetThuc);
    }
    
    public ArrayList<HoaDon> getListHDHT(int maHD){
        return PLR.getListHDHT(maHD);
    }
    //</editor-fold>
}
