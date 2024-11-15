/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author huuho
 */
public class XQR {

    public static BufferedImage toBuff(String content) throws WriterException {
        String base = Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.ISO_8859_1));
        Writer writer = new QRCodeWriter();
        Map<EncodeHintType, ErrorCorrectionLevel> map = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix bitMatrix = writer.encode(base, BarcodeFormat.QR_CODE, 500, 500, map);
        return MatrixToImageWriter.toBufferedImage(bitMatrix, new MatrixToImageConfig(0xff444444, 0xffffffff));
    }

    public static void writeQRToFile(String content, File file) throws WriterException, IOException {
        String base = Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.ISO_8859_1));
        Writer writer = new QRCodeWriter();
        Map<EncodeHintType, ErrorCorrectionLevel> map = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix bitMatrix = writer.encode(base, BarcodeFormat.QR_CODE, 500, 500, map);
        MatrixToImageWriter.writeToPath(bitMatrix, "png", file.toPath(), new MatrixToImageConfig(0xff444444, 0xffffffff));
    }

    public static Result readQR(File file) throws IOException, NotFoundException, ChecksumException, FormatException {
        Reader reader = new QRCodeReader();
        return reader.decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(file)))));
    }

    public static Result readQR(BufferedImage bi) throws IOException, NotFoundException, ChecksumException, FormatException {
        Reader reader = new QRCodeReader();
        return reader.decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bi))));
    }

}