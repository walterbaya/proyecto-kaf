package app;

import database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ExcelService;
import service.WebcamManager;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setWidth(1200);
        stage.setHeight(800);

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

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles.css")
                        .toExternalForm());

        stage.setTitle(
                "Sistema de Calzado");

        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {

            WebcamManager.close();
        });

        stage.setMaximized(true);


        stage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }
}