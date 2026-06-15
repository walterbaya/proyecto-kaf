package service;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;

public class QRScannerService {

    private final Webcam webcam;

    public QRScannerService() {

        webcam =
                WebcamManager.getWebcam();
    }

    public BufferedImage capturar() {

        try {

            if(webcam == null)
                return null;

            return webcam.getImage();

        } catch(Exception e) {

            return null;
        }
    }

    public String leerQR() {

        try {

            BufferedImage image =
                    webcam.getImage();

            if(image == null)
                return null;

            LuminanceSource source =
                    new BufferedImageLuminanceSource(
                            image);

            BinaryBitmap bitmap =
                    new BinaryBitmap(
                            new HybridBinarizer(
                                    source));

            Result result =
                    new MultiFormatReader()
                            .decode(bitmap);

            return result.getText();

        } catch(Exception e) {

            return null;
        }
    }
}