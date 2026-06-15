package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.WebcamManager;

public class MainController {

    private Stage buscarStage;

    private Stage ventaStage;

    @FXML
    public void abrirBusqueda() throws Exception {

        if (buscarStage != null &&
                buscarStage.isShowing()) {

            buscarStage.requestFocus();
            return;
        }

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/BuscarView.fxml"));

        Scene scene =
                new Scene(loader.load());

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles.css")
                        .toExternalForm());

        BuscarController controller =
                loader.getController();

        buscarStage = new Stage();
        buscarStage.setMaximized(true);

        buscarStage.setTitle(
                "Buscar Producto");

        buscarStage.setScene(scene);

        buscarStage.setOnCloseRequest(e -> {

            controller.shutdown();

            buscarStage = null;
        });

        buscarStage.show();
    }

    @FXML
    public void abrirVenta() throws Exception {

        if (ventaStage != null &&
                ventaStage.isShowing()) {

            ventaStage.requestFocus();
            return;
        }

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/VentaView.fxml"));

        Scene scene =
                new Scene(loader.load());

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles.css")
                        .toExternalForm());

        VentaController controller =
                loader.getController();

        ventaStage = new Stage();
        ventaStage.setMaximized(true);

        ventaStage.setTitle(
                "Registrar Venta");

        ventaStage.setScene(scene);

        ventaStage.setOnCloseRequest(e -> {

            controller.shutdown();

            ventaStage = null;
        });

        ventaStage.show();
    }

    @FXML
    public void salir() {

        WebcamManager.close();

        System.exit(0);
    }
}