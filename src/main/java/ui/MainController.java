package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    public void abrirBusqueda()
            throws Exception {

        Stage stage =
                new Stage();

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/BuscarView.fxml"));

        stage.setScene(
                new Scene(loader.load()));

        stage.show();
    }

    public void abrirVenta()
            throws Exception {

        Stage stage =
                new Stage();

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/VentaView.fxml"));

        stage.setScene(
                new Scene(loader.load()));

        stage.show();
    }

    public void salir() {

        System.exit(0);
    }
}