package ui;

import dao.ProductoDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Producto;
import service.QRScannerService;
import util.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.concurrent.*;

public class BuscarController {

    @FXML
    private TextField txtQR;

    @FXML
    private TextArea resultado;

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

                            Platform.runLater(() -> {

                                txtQR.setText(qr);

                                try {

                                    buscarAutomatico(qr);

                                } catch(Exception e) {

                                    resultado.setText(
                                            e.getMessage());
                                }
                            });
                        }

                    } catch(Exception ignored) {
                    }

                }, 0, 100,
                TimeUnit.MILLISECONDS);
    }

    private void buscarAutomatico(
            String qr)
            throws Exception {

        ProductoDAO dao =
                new ProductoDAO();

        Producto p =
                dao.buscarPorQR(qr);

        if(p == null) {

            resultado.setText(
                    "Producto no encontrado");

            return;
        }

        mostrarProducto(p);
    }

    @FXML
    public void buscar()
            throws Exception {

        ProductoDAO dao =
                new ProductoDAO();

        Producto p =
                dao.buscarPorQR(
                        txtQR.getText());

        if(p == null) {

            resultado.setText(
                    "Producto no encontrado");

            return;
        }

        mostrarProducto(p);
    }

    private void mostrarProducto(
            Producto p) {

        resultado.setText(

                "QR: " + p.getQr()

                        + "\n\nCódigo: "
                        + p.getCodigo()

                        + "\nDescripción: "
                        + p.getDescripcion()

                        + "\nMarca: "
                        + p.getMarca()

                        + "\nTalle: "
                        + p.getTalle()

                        + "\nColor: "
                        + p.getColor()

                        + "\nStock: "
                        + p.getStock()
        );
    }

    public void shutdown() {

        try {

            if (executor != null) {
                executor.shutdownNow();
            }

        } catch (Exception ignored) {
        }
    }
}