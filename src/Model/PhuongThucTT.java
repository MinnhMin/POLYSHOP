/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khoad
 */
public class PhuongThucTT {
    private Integer id;
    private String hinhThuc;

    public PhuongThucTT() {
    }

    public PhuongThucTT(Integer id, String hinhThuc) {
        this.id = id;
        this.hinhThuc = hinhThuc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }
    
    
}
