/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.ChatLieu;
import Model.DanhMuc;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import java.sql.*;
import java.util.ArrayList;
import Model.KhachHang;
import Model.KichCo;
import Model.MauSac;
import Model.NhanVien;
import Model.PhuongThucTT;
import Model.SanPham;
import Model.ThongKe;

/**
 *
 * @author khoad
 */
public class poloRepository {

    DbConnection dbConnection;

    // <editor-fold defaultstate="collapsed" desc="Panel Nhan Vien">
    public ArrayList<NhanVien> getListNV() {
        String sql = "SELECT * FROM Staff WHERE deleted != 1";
        ArrayList<NhanVien> nvlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt(1),
                        rs.getNString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                nvlist.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nvlist;
    }

    public ArrayList<NhanVien> getListNVbyName(String name) {
        String sql = "SELECT * FROM Staff WHERE deleted != 1 AND fullname LIKE ?";
        ArrayList<NhanVien> nvlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt(1),
                        rs.getNString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                nvlist.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nvlist;
    }
    
    public ArrayList<NhanVien> getListNVbyUsername(String name) {
        String sql = "SELECT * FROM Staff WHERE deleted != 1 AND username LIKE ?";
        ArrayList<NhanVien> nvlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt(1),
                        rs.getNString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                nvlist.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nvlist;
    }

    public boolean addNV(NhanVien nv) {
        String sql = "INSERT INTO Staff (fullname, username, password) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getTenNhanVien());
            ps.setString(2, nv.getTenNguoiDung());
            ps.setString(3, nv.getMatKhau());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateNV(NhanVien nv) {
        String sql = "UPDATE Staff\n"
                + "SET password = ?\n"
                + "WHERE username = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getMatKhau());
            ps.setString(2, nv.getTenNguoiDung());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteNV(NhanVien nv) {
        String sql = "UPDATE Staff\n"
                + "SET deleted = 1\n"
                + "WHERE username = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getTenNguoiDung());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Panel Khach Hang">
    public ArrayList<KhachHang> getListKH() {
        String sql = "SELECT * FROM Customer WHERE deleted != 1";
        ArrayList<KhachHang> khlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getInt(1),
                        rs.getNString(2),
                        rs.getString(3)
                );
                khlist.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khlist;
    }

    public ArrayList<KhachHang> getListKHbyName(String name) {
        String sql = "SELECT * FROM Customer WHERE deleted != 1 AND fullname LIKE ?";
        ArrayList<KhachHang> khlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getInt(1),
                        rs.getNString(2),
                        rs.getString(3)
                );
                khlist.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khlist;
    }

    public boolean addKH(KhachHang kh) {
        String sql = "INSERT INTO Customer (fullname, phone) VALUES (?, ?)";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getTenKhachHang());
            ps.setString(2, kh.getSoDienThoai());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateKH(KhachHang kh) {
        String sql = "UPDATE Customer\n"
                + "SET fullname = ?, phone = ?\n"
                + "WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getTenKhachHang());
            ps.setString(2, kh.getSoDienThoai());
            ps.setInt(3, kh.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteKH(KhachHang kh) {
        String sql = "UPDATE Customer\n"
                + "SET deleted = 1\n"
                + "WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, kh.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Panel Thuoc Tinh">
    public ArrayList<MauSac> getListMS() {
        String sql = "SELECT * FROM Color WHERE deleted != 1";
        ArrayList<MauSac> mlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac(
                        rs.getInt(1),
                        rs.getNString(2)
                );
                mlist.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mlist;
    }

    public ArrayList<KichCo> getListKC() {
        String sql = "SELECT * FROM Size WHERE deleted != 1";
        ArrayList<KichCo> kclist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichCo kc = new KichCo(
                        rs.getInt(1),
                        rs.getNString(2)
                );
                kclist.add(kc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kclist;
    }

    public ArrayList<ChatLieu> getListCL() {
        String sql = "SELECT * FROM Material WHERE deleted != 1";
        ArrayList<ChatLieu> cllist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(
                        rs.getInt(1),
                        rs.getNString(2)
                );
                cllist.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cllist;
    }

    public ArrayList<DanhMuc> getListDM() {
        String sql = "SELECT * FROM Category WHERE deleted != 1";
        ArrayList<DanhMuc> dmlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMuc dm = new DanhMuc(
                        rs.getInt(1),
                        rs.getNString(2)
                );
                dmlist.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dmlist;
    }

    public boolean addMS(MauSac ms) {
        String sql = "INSERT INTO Color (color) VALUES (?)";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, ms.getTenMau());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addKC(KichCo kc) {
        String sql = "INSERT INTO Size (size) VALUES (?)";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, kc.getTenKichCo());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addCL(ChatLieu cl) {
        String sql = "INSERT INTO Material (material) VALUES (?)";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, cl.getTenChatLieu());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addDM(DanhMuc dm) {
        String sql = "INSERT INTO Category (category_name) VALUES (?)";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, dm.getTenDanhMuc());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMS(MauSac ms) {
        String sql = "UPDATE Color SET color = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, ms.getTenMau());
            ps.setInt(2, ms.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateKC(KichCo kc) {
        String sql = "UPDATE Size SET size = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, kc.getTenKichCo());
            ps.setInt(2, kc.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCL(ChatLieu cl) {
        String sql = "UPDATE Material SET material = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, cl.getTenChatLieu());
            ps.setInt(2, cl.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDM(DanhMuc dm) {
        String sql = "UPDATE Category SET category_name = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, dm.getTenDanhMuc());
            ps.setInt(2, dm.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMS(MauSac ms) {
        String sql = "UPDATE Color SET deleted = 1 WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ms.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteKC(KichCo kc) {
        String sql = "UPDATE Size SET deleted = 1 WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, kc.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCL(ChatLieu cl) {
        String sql = "UPDATE Material SET deleted = 1 WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cl.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDM(DanhMuc dm) {
        String sql = "UPDATE Category SET deleted = 1 WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, dm.getId());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Panel San Pham">
    public ArrayList<SanPham> getListSP() {
        String sql = """
                     SELECT 
                         ProdItem.id,
                         P.product_name,
                         P.product_desc,
                         C.category_name,
                         Co.color,
                         S.size,
                         M.material,
                         ProdItem.qty_in_stock,
                         ProdItem.price
                     FROM Product_Item ProdItem
                     JOIN Product P ON ProdItem.product_id = P.id
                     JOIN Category C ON P.category_id = C.id
                     JOIN Variation_Option VO ON ProdItem.id = VO.product_item_id
                     JOIN Color Co ON VO.color_id = Co.id
                     JOIN Size S ON VO.size_id = S.id
                     JOIN Material M ON VO.material_id = M.id
                     WHERE ProdItem.deleted != 1;""";
        ArrayList<SanPham> splist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setTenSP(rs.getNString(2));
                sp.setMoTa(rs.getNString(3));
                sp.setDanhMuc(rs.getNString(4));
                sp.setMauSac(rs.getNString(5));
                sp.setKichCo(rs.getNString(6));
                sp.setChatLieu(rs.getNString(7));
                sp.setSoLuong(rs.getInt(8));
                sp.setDonGia(rs.getInt(9));
                splist.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return splist;
    }

    public ArrayList<SanPham> getListSP(String name) {
        String sql = """
                     SELECT 
                         ProdItem.id,
                         P.product_name,
                         P.product_desc,
                         C.category_name,
                         Co.color,
                         S.size,
                         M.material,
                         ProdItem.qty_in_stock,
                         ProdItem.price
                     FROM Product_Item ProdItem
                     JOIN Product P ON ProdItem.product_id = P.id
                     JOIN Category C ON P.category_id = C.id
                     JOIN Variation_Option VO ON ProdItem.id = VO.product_item_id
                     JOIN Color Co ON VO.color_id = Co.id
                     JOIN Size S ON VO.size_id = S.id
                     JOIN Material M ON VO.material_id = M.id
                     WHERE ProdItem.deleted != 1 AND P.product_name LIKE ?;""";
        ArrayList<SanPham> splist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setTenSP(rs.getNString(2));
                sp.setMoTa(rs.getNString(3));
                sp.setDanhMuc(rs.getNString(4));
                sp.setMauSac(rs.getNString(5));
                sp.setKichCo(rs.getNString(6));
                sp.setChatLieu(rs.getNString(7));
                sp.setSoLuong(rs.getInt(8));
                sp.setDonGia(rs.getInt(9));
                splist.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return splist;
    }

    public ArrayList<SanPham> getListSP(int id) {
        String sql = """
                     SELECT 
                         ProdItem.id,
                         P.product_name,
                         P.product_desc,
                         C.category_name,
                         Co.color,
                         S.size,
                         M.material,
                         ProdItem.qty_in_stock,
                         ProdItem.price
                     FROM Product_Item ProdItem
                     JOIN Product P ON ProdItem.product_id = P.id
                     JOIN Category C ON P.category_id = C.id
                     JOIN Variation_Option VO ON ProdItem.id = VO.product_item_id
                     JOIN Color Co ON VO.color_id = Co.id
                     JOIN Size S ON VO.size_id = S.id
                     JOIN Material M ON VO.material_id = M.id
                     WHERE ProdItem.id = ? AND ProdItem.deleted != 1;""";
        ArrayList<SanPham> splist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setTenSP(rs.getNString(2));
                sp.setMoTa(rs.getNString(3));
                sp.setDanhMuc(rs.getNString(4));
                sp.setMauSac(rs.getNString(5));
                sp.setKichCo(rs.getNString(6));
                sp.setChatLieu(rs.getNString(7));
                sp.setSoLuong(rs.getInt(8));
                sp.setDonGia(rs.getInt(9));
                splist.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return splist;
    }

    public void hideSP(SanPham sp) {
        String sql = "UPDATE Product_Item SET deleted = 1 WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sp.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unhideSP() {
        String sql = "UPDATE Product_Item SET deleted = 0";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MauSac getMSid(String ten) {
        String sql = "SELECT id, color FROM Color WHERE color = ?";
        MauSac mauSac = new MauSac();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac(rs.getInt(1), rs.getNString(2));
                mauSac = ms;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSac;
    }

    public KichCo getKCid(String ten) {
        String sql = "SELECT id, size FROM Size WHERE size = ?";
        KichCo kichCo = new KichCo();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichCo kc = new KichCo(rs.getInt(1), rs.getNString(2));
                kichCo = kc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kichCo;
    }

    public ChatLieu getCLid(String ten) {
        String sql = "SELECT id, material FROM Material WHERE material = ?";
        ChatLieu chatLieu = new ChatLieu();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu(rs.getInt(1), rs.getNString(2));
                chatLieu = cl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatLieu;
    }

    public DanhMuc getDMid(String ten) {
        String sql = "SELECT id, category_name FROM Category WHERE category_name = ?";
        DanhMuc danhMuc = new DanhMuc();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMuc dm = new DanhMuc(rs.getInt(1), rs.getNString(2));
                danhMuc = dm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhMuc;
    }

//    public boolean addSP(SanPham sp) {
//        String sql = "SELECT id FROM Product WHERE product_name = ?";
//        String sql1 = "INSERT INTO Product (category_id, product_name, product_desc) VALUES (?, ?, ?);";
//        String sql2 = "INSERT INTO Product_Item (product_id, qty_in_stock, price) VALUES (?, ?, ?);";
//        String sql3 = "INSERT INTO Variation_Option (product_item_id, color_id, size_id, material_id) VALUES (?, ?, ?, ?);";
//
//        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); PreparedStatement ps1 = conn.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement ps2 = conn.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement ps3 = conn.prepareStatement(sql3)) {
//
//            ps.setNString(1, sp.getTenSP());
//            ResultSet rs = ps.executeQuery();
//            int productId = 0;
//            if (rs.next()) {
//                productId = rs.getInt(1);
//            } else {
//                ps1.setInt(1, sp.getMaDM());
//                ps1.setNString(2, sp.getTenSP());
//                ps1.setNString(3, sp.getMoTa());
//                ps1.executeUpdate();
//
//                ResultSet rs1 = ps1.getGeneratedKeys();
//                if (rs1.next()) {
//                    productId = rs1.getInt(1);
//                }
//            }
//
//            ps2.setInt(1, productId);
//            ps2.setInt(2, sp.getSoLuong());
//            ps2.setInt(3, sp.getDonGia());
//            ps2.executeUpdate();
//
//            ResultSet rs2 = ps2.getGeneratedKeys();
//            int productItemId = 0;
//            if (rs2.next()) {
//                productItemId = rs2.getInt(1);
//            }
//
//            ps3.setInt(1, productItemId);
//            ps3.setInt(2, sp.getMaMS());
//            ps3.setInt(3, sp.getMaKC());
//            ps3.setInt(4, sp.getMaCL());
//            ps3.executeUpdate();
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    public boolean addSP(SanPham sp) {
        String sql = """
                     BEGIN TRANSACTION;
                     
                     DECLARE @product_id INT;
                     DECLARE @product_item_id INT;
                     
                     SELECT @product_id = id FROM Product WHERE product_name = ?;
                     
                     IF @product_id IS NULL
                     BEGIN
                         INSERT INTO Product (category_id, product_name, product_desc) VALUES (?, ?, ?);
                         SET @product_id = SCOPE_IDENTITY();
                     END
                     
                     SELECT @product_item_id = id FROM Product_Item WHERE product_id = @product_id;
                     
                     IF @product_item_id IS NULL
                     BEGIN
                         INSERT INTO Product_Item (product_id, qty_in_stock, price) VALUES (@product_id, ?, ?);
                         SET @product_item_id = SCOPE_IDENTITY();
                     END
                     
                     IF NOT EXISTS (
                         SELECT 1 FROM Variation_Option 
                         WHERE product_item_id = @product_item_id 
                         AND color_id = ?
                         AND size_id = ?
                         AND material_id = ?
                     )
                     BEGIN
                         INSERT INTO Product_Item (product_id, qty_in_stock, price)
                         SELECT product_id, qty_in_stock, ?
                         FROM Product_Item
                         WHERE id = @product_item_id;
                     
                         SET @product_item_id = SCOPE_IDENTITY();
                         INSERT INTO Variation_Option (product_item_id, color_id, size_id, material_id) VALUES (@product_item_id, ?, ?, ?);
                     END
                     ELSE
                     BEGIN
                         UPDATE Product_Item
                         SET qty_in_stock = qty_in_stock + ?,
                             price = ?
                         WHERE id = @product_item_id;
                     END
                     
                     COMMIT TRANSACTION;""";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setNString(1, sp.getTenSP());
            ps.setInt(2, sp.getMaDM());
            ps.setNString(3, sp.getTenSP());
            ps.setNString(4, sp.getMoTa());
            ps.setInt(5, sp.getSoLuong());
            ps.setInt(6, sp.getDonGia());
            ps.setInt(7, sp.getMaMS());
            ps.setInt(8, sp.getMaKC());
            ps.setInt(9, sp.getMaCL());
            ps.setInt(10, sp.getDonGia());
            ps.setInt(11, sp.getMaMS());
            ps.setInt(12, sp.getMaKC());
            ps.setInt(13, sp.getMaCL());
            ps.setInt(14, sp.getSoLuong());
            ps.setInt(15, sp.getDonGia());
            
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTTSP(SanPham sp) {
        String sql = "SELECT id FROM Product WHERE product_name = ?";
        String sql1 = "UPDATE Product SET category_id = ?, product_name = ?, product_desc = ? WHERE id = ?";
        String sql2 = "UPDATE Product_Item SET qty_in_stock = ?, price = ? WHERE id = ?";
        String sql3 = "UPDATE Variation_Option SET color_id = ?, size_id = ?, material_id = ? WHERE product_item_id = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); PreparedStatement ps1 = conn.prepareStatement(sql1); PreparedStatement ps2 = conn.prepareStatement(sql2); PreparedStatement ps3 = conn.prepareStatement(sql3)) {

            // Get the product ID
            ps.setNString(1, sp.getTenSP());
            ResultSet rs = ps.executeQuery();
            int productId;
            if (rs.next()) {
                productId = rs.getInt(1);
            } else {
                return false;
            }

            // Update Product
            ps1.setInt(1, sp.getMaDM());
            ps1.setNString(2, sp.getTenSP());
            ps1.setNString(3, sp.getMoTa());
            ps1.setInt(4, productId);
            ps1.executeUpdate();

            // Update Product Item
            ps2.setInt(1, sp.getSoLuong());
            ps2.setInt(2, sp.getDonGia());
            ps2.setInt(3, sp.getId());
            ps2.executeUpdate();

            // Update Variation Option
            ps3.setInt(1, sp.getMaMS());
            ps3.setInt(2, sp.getMaKC());
            ps3.setInt(3, sp.getMaCL());
            ps3.setInt(4, sp.getId());
            ps3.executeUpdate();

            return true; // All updates succeeded
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Panel Hoa Don">
    public ArrayList<HoaDon> getListHDHT() {
        String sql = """
                     SELECT 
                        i.id,
                        st.fullname,
                        cu.fullname,
                        pm.method,
                        i.created_date,
                        i.total,
                        cu.phone
                     FROM Invoice i
                     JOIN Staff st ON i.staff_id = st.id
                     JOIN Customer cu ON i.customer_id = cu.id
                     JOIN Payment_Method pm ON i.payment_method_id = pm.id
                     WHERE i.completed = 1 AND i.deleted = 0
                     ORDER BY i.created_date DESC;""";
        ArrayList<HoaDon> hdlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setTenNhanVien(rs.getNString(2));
                hd.setTenKhachHang(rs.getNString(3));
                hd.setPttt(rs.getNString(4));
                hd.setNgayTao(rs.getDate(5).toLocalDate());
                hd.setTong(rs.getDouble(6));
                hd.setSoDienThoai(rs.getString(7));
                hdlist.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hdlist;
    }

    public ArrayList<HoaDon> getListHDCHT() {
        String sql = """
                     SELECT 
                        i.id,
                        st.fullname,
                        cu.fullname,
                        pm.method,
                        i.created_date,
                        i.total,
                        cu.phone
                     FROM Invoice i
                     JOIN Staff st ON i.staff_id = st.id
                     JOIN Customer cu ON i.customer_id = cu.id
                     JOIN Payment_Method pm ON i.payment_method_id = pm.id
                     WHERE i.completed = 0 AND i.deleted = 0""";
        ArrayList<HoaDon> hdlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setTenNhanVien(rs.getNString(2));
                hd.setTenKhachHang(rs.getNString(3));
                hd.setPttt(rs.getNString(4));
                hd.setNgayTao(rs.getDate(5).toLocalDate());
                hd.setTong(rs.getDouble(6));
                hd.setSoDienThoai(rs.getString(7));
                hdlist.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hdlist;
    }

    public ArrayList<HoaDonChiTiet> getHDCT(Integer MaHD) {
        String sql = """
                     SELECT
                       I.id AS MaHD,
                       PItem.id AS MaSP,
                       P.product_name,
                       C.color,
                       S.size,
                       M.material,
                       PItem.price,
                       ID.qty,
                       ID.qty * PItem.price AS product_total
                     FROM Invoice I
                     JOIN Invoice_Details ID ON I.id = ID.invoice_id
                     JOIN Product_Item PItem ON ID.product_item_id = PItem.id
                     JOIN Product P ON PItem.product_id = P.id
                     JOIN Variation_Option VO ON PItem.id = VO.product_item_id
                     JOIN Color C ON VO.color_id = C.id
                     JOIN Size S ON VO.size_id = S.id
                     JOIN Material M ON VO.material_id = M.id
                     WHERE I.id = ?""";
        ArrayList<HoaDonChiTiet> hdctlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, MaHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHD(rs.getInt(1));
                hdct.setMaSP(rs.getInt(2));
                hdct.setTenSP(rs.getNString(3));
                hdct.setMauSac(rs.getNString(4));
                hdct.setKichCo(rs.getNString(5));
                hdct.setChatLieu(rs.getNString(6));
                hdct.setDonGia(rs.getInt(7));
                hdct.setSoLuong(rs.getInt(8));
                hdct.setTong(rs.getInt(9));
                hdctlist.add(hdct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hdctlist;
    }

    public ArrayList<PhuongThucTT> getListPT() {
        String sql = "SELECT * FROM Payment_Method";
        ArrayList<PhuongThucTT> ptlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhuongThucTT pt = new PhuongThucTT();
                pt.setId(rs.getInt(1));
                pt.setHinhThuc(rs.getNString(2));
                ptlist.add(pt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ptlist;
    }

    public PhuongThucTT getPTid(String phuongThuc) {
        String sql = "SELECT * FROM Payment_Method WHERE method = ?";
        PhuongThucTT pttt = new PhuongThucTT();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phuongThuc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhuongThucTT pt = new PhuongThucTT(rs.getInt(1), rs.getNString(2));
                pttt = pt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pttt;
    }

    public boolean addHD(HoaDon hd) {
        String sql = """
                     INSERT INTO Invoice (staff_id, customer_id, payment_method_id, created_date, total)
                     VALUES (?, ?, ?, ?, ?);""";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, hd.getIdNV());
            ps.setInt(2, hd.getIdKH());
            ps.setInt(3, hd.getIdPTTT());
            ps.setDate(4, Date.valueOf(hd.getNgayTao()));
            ps.setDouble(5, hd.getTong());

            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateHD(double tong, int id) {
        String sql = "UPDATE Invoice SET total = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, tong);
            ps.setInt(2, id);

            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean huyHD(int id) {
        String sql = "UPDATE Invoice SET deleted = 1 WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaHDCT(int id) {
        String sql = "DELETE FROM Invoice_Details WHERE invoice_id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaHDCT(int MaHD, int MaSP) {
        String sql = "DELETE FROM Invoice_Details WHERE invoice_id = ? AND product_item_id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, MaHD);
            ps.setInt(2, MaSP);

            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public KhachHang getKHinfo(String phoneNum) {
        String sql = "SELECT * FROM Customer WHERE phone = ? AND deleted != 1";
        KhachHang kh = new KhachHang();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phoneNum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang(rs.getInt(1), rs.getNString(2), rs.getString(3));
                kh = khachHang;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }

    public void addGH(int maHD, int maSP, int soLuongMua) {
        String sql = "SELECT * FROM Invoice_Details WHERE invoice_id = ? AND product_item_id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maHD);
            ps.setInt(2, maSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int soLuongCu = rs.getInt(4);
                int soLuongMoi = soLuongCu + soLuongMua;
                String sql1 = "UPDATE Invoice_Details SET qty = ? WHERE invoice_id = ? AND product_item_id = ?";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, soLuongMoi);
                ps1.setInt(2, maHD);
                ps1.setInt(3, maSP);
                ps1.executeUpdate();
            } else {
                String sql2 = "INSERT INTO Invoice_Details VALUES (?, ?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, maHD);
                ps2.setInt(2, maSP);
                ps2.setInt(3, soLuongMua);
                ps2.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGH(int maHD, int maSP, int soLuongMua) {
        String sql = "UPDATE Invoice_Details SET qty = ? WHERE invoice_id = ? AND product_item_id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongMua);
            ps.setInt(2, maHD);
            ps.setInt(3, maSP);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSP(int maSP, int soLuong) {
        String sql = "UPDATE Product_Item SET qty_in_stock = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuong);
            ps.setInt(2, maSP);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean thanhtoanHD(int id) {
        String sql = "UPDATE Invoice SET completed = 1 WHERE id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Panel Thong Ke">
    public ArrayList<ThongKe> getListTK() {
        String sql = """
                     SELECT
                         i.created_date AS Invoice_Created_Date,
                         p.product_name AS Product_Name,
                         COUNT(id.product_item_id) AS Total_Product_Items,
                         SUM(pi.price * id.qty) AS Total_Price
                     FROM Invoice i
                     JOIN Invoice_Details id ON i.id = id.invoice_id
                     JOIN Product_Item pi ON id.product_item_id = pi.id
                     JOIN Product p ON pi.product_id = p.id
                     GROUP BY i.created_date, p.product_name
                     ORDER BY i.created_date DESC;""";
        ArrayList<ThongKe> tklist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setNgayBan(rs.getDate(1));
                tk.setTenSP(rs.getNString(2));
                tk.setTongSP(rs.getInt(3));
                tk.setDoanhThuNgay(rs.getDouble(4));
                tklist.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tklist;
    }

    public ArrayList<ThongKe> getListTK(Date ngayBatdau, Date ngayKetThuc) {
        String sql = """
                     SELECT
                         i.created_date AS Invoice_Created_Date,
                         p.product_name AS Product_Name,
                         COUNT(id.product_item_id) AS Total_Product_Items,
                         SUM(pi.price * id.qty) AS Total_Price
                     FROM Invoice i
                     JOIN Invoice_Details id ON i.id = id.invoice_id
                     JOIN Product_Item pi ON id.product_item_id = pi.id
                     JOIN Product p ON pi.product_id = p.id
                     WHERE i.created_date BETWEEN ? AND ?
                     GROUP BY i.created_date, p.product_name;""";
        ArrayList<ThongKe> tklist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, ngayBatdau);
            ps.setDate(2, ngayKetThuc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setNgayBan(rs.getDate(1));
                tk.setTenSP(rs.getNString(2));
                tk.setTongSP(rs.getInt(3));
                tk.setDoanhThuNgay(rs.getDouble(4));
                tklist.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tklist;
    }

    public ThongKe getDTngay() {
        String sql = """
                     SELECT 
                     CAST(created_date AS DATE) AS Day,
                     SUM(price * qty) AS Total_Price_By_Day
                     FROM Invoice i
                     JOIN Invoice_Details id ON i.id = id.invoice_id
                     JOIN Product_Item pi ON id.product_item_id = pi.id
                     WHERE CAST(created_date AS DATE) = CAST(GETDATE() AS DATE)
                     GROUP BY CAST(created_date AS DATE);""";
        ThongKe tk = new ThongKe();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tk = new ThongKe();
                tk.setDoanhThuNgay(rs.getDouble(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }

    public ThongKe getDTthang() {
        String sql = """
                     SELECT 
                         DATEPART(MONTH, created_date) AS Month,
                         SUM(price * qty) AS Total_Price_By_Month
                     FROM Invoice i
                     JOIN Invoice_Details id ON i.id = id.invoice_id
                     JOIN Product_Item pi ON id.product_item_id = pi.id
                     WHERE 
                         DATEPART(YEAR, created_date) = DATEPART(YEAR, GETDATE()) AND
                         DATEPART(MONTH, created_date) = DATEPART(MONTH, GETDATE())
                     GROUP BY 
                         DATEPART(YEAR, created_date),
                         DATEPART(MONTH, created_date);""";
        ThongKe tk = new ThongKe();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tk = new ThongKe();
                tk.setDoanhThuThang(rs.getDouble(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }

    public ThongKe getDTnam() {
        String sql = """
                     SELECT 
                         DATEPART(YEAR, created_date) AS Year,
                         SUM(price * qty) AS Total_Price_By_Year
                     FROM Invoice i
                     JOIN Invoice_Details id ON i.id = id.invoice_id
                     JOIN Product_Item pi ON id.product_item_id = pi.id
                     WHERE DATEPART(YEAR, created_date) = DATEPART(YEAR, GETDATE())
                     GROUP BY DATEPART(YEAR, created_date);""";
        ThongKe tk = new ThongKe();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tk = new ThongKe();
                tk.setDoanhThuNam(rs.getDouble(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }

    public Integer getTongDon() {
        String sql = "SELECT COUNT(*) FROM Invoice";
        Integer tongDon = 0;
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tongDon = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDon;
    }

    public ArrayList<HoaDon> getListHDHTDate(Date ngayBatdau, Date ngayKetThuc) {
        String sql = """
                     SELECT 
                        i.id,
                        st.fullname,
                        cu.fullname,
                        pm.method,
                        i.created_date,
                        i.total,
                        cu.phone
                     FROM Invoice i
                     JOIN Staff st ON i.staff_id = st.id
                     JOIN Customer cu ON i.customer_id = cu.id
                     JOIN Payment_Method pm ON i.payment_method_id = pm.id
                     WHERE i.completed = 1 AND i.deleted = 0 AND i.created_date BETWEEN ? AND ?""";
        ArrayList<HoaDon> hdlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, ngayBatdau);
            ps.setDate(2, ngayKetThuc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setTenNhanVien(rs.getNString(2));
                hd.setTenKhachHang(rs.getNString(3));
                hd.setPttt(rs.getNString(4));
                hd.setNgayTao(rs.getDate(5).toLocalDate());
                hd.setTong(rs.getDouble(6));
                hd.setSoDienThoai(rs.getString(7));
                hdlist.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hdlist;
    }

    public ArrayList<HoaDon> getListHDHT(int maHD) {
        String sql = """
                     SELECT 
                        i.id,
                        st.fullname,
                        cu.fullname,
                        pm.method,
                        i.created_date,
                        i.total,
                        cu.phone
                     FROM Invoice i
                     JOIN Staff st ON i.staff_id = st.id
                     JOIN Customer cu ON i.customer_id = cu.id
                     JOIN Payment_Method pm ON i.payment_method_id = pm.id
                     WHERE i.completed = 1 AND i.deleted = 0 AND i.id = ?""";
        ArrayList<HoaDon> hdlist = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setTenNhanVien(rs.getNString(2));
                hd.setTenKhachHang(rs.getNString(3));
                hd.setPttt(rs.getNString(4));
                hd.setNgayTao(rs.getDate(5).toLocalDate());
                hd.setTong(rs.getDouble(6));
                hd.setSoDienThoai(rs.getString(7));
                hdlist.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hdlist;
    }
    //</editor-fold>

}
