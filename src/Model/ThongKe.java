/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author khoad
 */
public class ThongKe {
    private Date ngayBan;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String tenSP;
    private Integer tongSP;
    private double doanhThuNgay;
    private double doanhThuNam;
    private double doanhThuThang;

    public ThongKe() {
    }

    public ThongKe(Date ngayBan, String tenSP, Integer tongSP, double doanhThuNgay) {
        this.ngayBan = ngayBan;
        this.tenSP = tenSP;
        this.tongSP = tongSP;
        this.doanhThuNgay = doanhThuNgay;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Integer getTongSP() {
        return tongSP;
    }

    public void setTongSP(Integer tongSP) {
        this.tongSP = tongSP;
    }

    public double getDoanhThuNgay() {
        return doanhThuNgay;
    }

    public void setDoanhThuNgay(double doanhThuNgay) {
        this.doanhThuNgay = doanhThuNgay;
    }

    public double getDoanhThuNam() {
        return doanhThuNam;
    }

    public void setDoanhThuNam(double doanhThuNam) {
        this.doanhThuNam = doanhThuNam;
    }

    public double getDoanhThuThang() {
        return doanhThuThang;
    }

    public void setDoanhThuThang(double doanhThuThang) {
        this.doanhThuThang = doanhThuThang;
    }

}
