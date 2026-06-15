package ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import service.QRScannerService;
import service.VentaService;
import util.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.concurrent.*;

public class VentaController {

    @FXML
    private TextField txtQR;

    @FXML
    private Label lblEstado;

    @FXML
    private ImageView webcamView;

    private QRScannerService scanner;

    private ScheduledExecutorService executor;

    private String ultimoQR = "";

    private long ultimaLectura = 0;

    @FXML
    public void initialize() {

        scanner =
                new QRScannerService();

        executor =
                Executors.newSingleThreadScheduledExecutor();

        executor.scheduleAtFixedRate(() -> {

                    try {

                        BufferedImage img =
                                scanner.capturar();

                        if(img != null) {

                            Platform.runLater(() ->
                                    webcamView.setImage(
                                            ImageUtils.convert(img)));
                        }

                        String qr =
                                scanner.leerQR();

                        if(qr != null) {

                            long ahora =
                                    System.currentTimeMillis();

                            if(qr.equals(ultimoQR)
                                    &&
                                    ahora - ultimaLectura < 2000) {

                                return;
                            }

                            ultimoQR = qr;
                            ultimaLectura = ahora;

                            Platform.runLater(() ->
                                    txtQR.setText(qr));
                        }

                    } catch(Exception ignored) {
                    }

                }, 0, 100,
                TimeUnit.MILLISECONDS);
    }

    @FXML
    public void vender() {

        try {

            String qr =
                    txtQR.getText();

            new VentaService()
                    .vender(qr);

            lblEstado.setText(
                    "Venta realizada correctamente");

        } catch(Exception e) {

            lblEstado.setText(
                    e.getMessage());
        }
    }

    public void shutdown() {

        if(executor != null) {

            executor.shutdownNow();
        }
    }
}