package pl.skiresort.Logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

@Service
public class ZxingQRGenerator implements QRGenerator {

    /*
        Data written in QR code
    */
    private String data;

    /*
        Where image will be saved - on disc
     */
    private String path;

    /*
        Encoding charset - default UTF-8
     */
    private String charset;

    /*
        Hashed map with data
        Default - EncodedHintType - ERROR CORRECTION
        ErrorCorrectionLevel - L (Low)
     */
    private Map<EncodeHintType, ErrorCorrectionLevel> hashMap;

    /*
        Height of jpg file in pixels
     */
    private Integer height;

    /*
        Width of jpg file in pixels
     */
    private Integer width;

    private ZxingQRGenerator() {}

    public static QRServiceBuilder builder() {
        return new QRServiceBuilder();
    }

    public static final class QRServiceBuilder {
        private String data;
        private String path;
        private String charset = "UTF-8";
        private Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        private Integer height;
        private Integer width;

        public QRServiceBuilder() {
            this.hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        }

        public QRServiceBuilder setData(final String data) {
            this.data = data;
            return this;
        }

        public QRServiceBuilder setPath(final String path) {
            this.path = path;
            return this;
        }

        public QRServiceBuilder setCharset(final String charset) {
            this.charset = charset;
            return this;
        }

        public QRServiceBuilder setHashMap(final Map<EncodeHintType, ErrorCorrectionLevel> hashMap) {

            this.hashMap = hashMap;
            return this;
        }

        public QRServiceBuilder setHeight(final Integer height) {
            this.height = height;
            return this;
        }

        public QRServiceBuilder setWidth(final Integer width) {
            this.width = width;
            return this;
        }

        public ZxingQRGenerator build() {
            ZxingQRGenerator zxingQrGenerator = new ZxingQRGenerator();
            zxingQrGenerator.charset = this.charset;
            zxingQrGenerator.data = this.data;
            zxingQrGenerator.hashMap = this.hashMap;
            zxingQrGenerator.height = this.height;
            zxingQrGenerator.width = this.width;
            zxingQrGenerator.path = this.path;
            return zxingQrGenerator;
        }
    }

    public String getData() {
        return data;
    }

    public String getPath() {
        return path;
    }

    public String getCharset() {
        return charset;
    }

    public Map<EncodeHintType, ErrorCorrectionLevel> getHashMap() {
        return hashMap;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    @Override
    public BufferedImage createQrImage() throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(this.data.getBytes(this.charset), this.charset),
                BarcodeFormat.QR_CODE, this.width, this.height
        );
        MatrixToImageWriter.writeToFile(
                matrix,
                this.path.substring(this.path.lastIndexOf('.') + 1),
                new File(this.path)
        );
        return MatrixToImageWriter.toBufferedImage(matrix);
    }
}
