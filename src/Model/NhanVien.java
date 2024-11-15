/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khoad
 */
public class NhanVien {
    private Integer maNV;
    private String tenNhanVien;
    private String tenNguoiDung;
    private String matKhau;
    private String role;

    public NhanVien() {
    }

    public NhanVien(Integer maNV, String tenNhanVien, String tenNguoiDung, String matKhau, String role) {
        this.maNV = maNV;
        this.tenNhanVien = tenNhanVien;
        this.tenNguoiDung = tenNguoiDung;
        this.matKhau = matKhau;
        this.role = role;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
