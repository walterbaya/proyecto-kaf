package app;

import database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ExcelService;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        DatabaseManager.inicializar();

        ExcelService excel =
                new ExcelService();

        excel.importar();

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/MainView.fxml"));

        Scene scene =
                new Scene(
                        loader.load());

        stage.setTitle(
                "Sistema de Calzado");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}