/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import java.util.Random;
/**
 *
 * @author Admin
 */
public class MaCapchaService {
    public static String sinhMaXacThuc(int doDai) {
        String kyTu = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(doDai);
        Random random = new Random();

        for (int i = 0; i < doDai; i++) {
            int index = random.nextInt(kyTu.length());
            char randomChar = kyTu.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
    
    public static String emailCheckMaXacThuc(int doDai) {
        String kyTu = "0123456789";
        StringBuilder sb = new StringBuilder(doDai);
        Random random = new Random();

        for (int i = 0; i < doDai; i++) {
            int index = random.nextInt(kyTu.length());
            char randomChar = kyTu.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
