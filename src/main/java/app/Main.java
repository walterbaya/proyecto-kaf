package app;

import database.DatabaseManager;
import service.ExcelService;
import ui.ConsolaUI;

public class Main {

    public static void main(String[] args)
            throws Exception {

        DatabaseManager.inicializar();

        ExcelService excel =
                new ExcelService();

        excel.importar();

        new ConsolaUI()
                .iniciar();
    }
}