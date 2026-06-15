package service;

import dao.ProductoDAO;
import dao.VentaDAO;
import model.Producto;

public class VentaService {

    private final ProductoDAO productoDAO =
            new ProductoDAO();

    private final VentaDAO ventaDAO =
            new VentaDAO();

    private final ExcelService excelService =
            new ExcelService();

    public void vender(String qr)
            throws Exception {

        Producto p =
                productoDAO.buscarPorQR(qr);

        if(p == null) {

            System.out.println(
                    "Producto inexistente");

            return;
        }

        if(p.getStock() <= 0) {

            System.out.println(
                    "Sin stock");

            return;
        }

        int nuevoStock =
                p.getStock() - 1;

        productoDAO.actualizarStock(
                qr,
                nuevoStock);

        excelService.actualizarStock(
                qr,
                nuevoStock);

        ventaDAO.registrar(qr);

        System.out.println(
                "Venta OK");
    }
}