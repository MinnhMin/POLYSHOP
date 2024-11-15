/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khoad
 */
public class SanPham {
    private Integer id;
    private String tenSP;
    private String moTa;
    private String chatLieu;
    private String mauSac;
    private String kichCo;
    private String danhMuc;
    private Integer donGia;
    private Integer soLuong;
    private Integer maMS, maKC, maCL, maDM;

    public SanPham() {
    }

    public SanPham(Integer id, String tenSP, String moTa, String chatLieu, String mauSac, String kichCo, String danhMuc, Integer donGia, Integer soLuong) {
        this.id = id;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.kichCo = kichCo;
        this.danhMuc = danhMuc;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getMaMS() {
        return maMS;
    }

    public void setMaMS(Integer maMS) {
        this.maMS = maMS;
    }

    public Integer getMaKC() {
        return maKC;
    }

    public void setMaKC(Integer maKC) {
        this.maKC = maKC;
    }

    public Integer getMaCL() {
        return maCL;
    }

    public void setMaCL(Integer maCL) {
        this.maCL = maCL;
    }

    public Integer getMaDM() {
        return maDM;
    }

    public void setMaDM(Integer maDM) {
        this.maDM = maDM;
    }
    
    
}
