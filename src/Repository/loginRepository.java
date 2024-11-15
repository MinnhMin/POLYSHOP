/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author khoad
 */
public class loginRepository {

    DbConnection dbConnection;

    public NhanVien getNVLogin(String username, String pass) {
        String sql = "SELECT * FROM Staff WHERE username = ? AND password = ? AND deleted != 1";
        NhanVien nv = new NhanVien();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getInt(1), rs.getNString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

    public boolean checkLogin(String username, String pass) {
        if (getNVLogin(username, pass).getMaNV() == null) {
            return false;
        }
        return true;
    }
    
    public String getUserRole(String username, String password) {
        String sql = "SELECT role FROM Staff WHERE username = ? AND password = ?";
        String role = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, username);
            ps.setObject(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return role;
    }
}
