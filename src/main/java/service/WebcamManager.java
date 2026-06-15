package service;

import com.github.sarxos.webcam.Webcam;

import java.awt.Dimension;

public class WebcamManager {

    private static Webcam webcam;

    private WebcamManager() {
    }

    public static synchronized Webcam getWebcam() {

        try {

            if(webcam == null) {

                webcam = Webcam.getDefault();

                if(webcam == null) {
                    throw new RuntimeException(
                            "No se detectó ninguna webcam");
                }

                webcam.setViewSize(
                        new Dimension(640, 480));

                webcam.open();
            }

            return webcam;

        } catch(Exception e) {

            throw new RuntimeException(
                    "Error iniciando webcam",
                    e);
        }
    }

    public static synchronized void close() {

        if(webcam != null) {

            try {
                webcam.close();
            }
            catch(Exception ignored) {
            }

            webcam = null;
        }
    }
}