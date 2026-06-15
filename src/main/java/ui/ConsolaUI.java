package ui;

import model.Producto;
import dao.ProductoDAO;
import service.QRScannerService;
import service.VentaService;

import java.util.Scanner;

public class ConsolaUI {

    public void iniciar() throws Exception {

        Scanner sc =
                new Scanner(System.in);

        while(true) {

            System.out.println();
            System.out.println("1 Buscar");
            System.out.println("2 Venta");
            System.out.println("3 Salir");

            int op =
                    Integer.parseInt(
                            sc.nextLine());

            switch(op) {

                case 1 -> buscar();

                case 2 -> vender();

                case 3 -> System.exit(0);
            }
        }
    }

    private void buscar()
            throws Exception {

        QRScannerService qr =
                new QRScannerService();

        String codigo =
                qr.leerQR();

        ProductoDAO dao =
                new ProductoDAO();

        Producto p =
                dao.buscarPorQR(codigo);

        if(p == null) {

            System.out.println(
                    "No encontrado");

            return;
        }

        System.out.println(
                p.getDescripcion());

        System.out.println(
                "Stock: " +
                        p.getStock());
    }

    private void vender()
            throws Exception {

        QRScannerService qr =
                new QRScannerService();

        String codigo =
                qr.leerQR();

        new VentaService()
                .vender(codigo);
    }
}