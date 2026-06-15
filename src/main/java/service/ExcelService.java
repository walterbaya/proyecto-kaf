package service;

import dao.ProductoDAO;
import model.Producto;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelService {

    private static final String FILE =
            "Productos.xlsx";

    public void importar() throws Exception {

        ProductoDAO dao =
                new ProductoDAO();

        FileInputStream fis =
                new FileInputStream(FILE);

        Workbook wb =
                new XSSFWorkbook(fis);

        Sheet sheet =
                wb.getSheetAt(0);

        for(Row row : sheet) {

            if(row.getRowNum()==0)
                continue;

            Producto p =
                    new Producto();

            p.setQr(
                    row.getCell(0)
                            .getStringCellValue());

            p.setCodigo(
                    row.getCell(1)
                            .getStringCellValue());

            p.setDescripcion(
                    row.getCell(2)
                            .getStringCellValue());

            p.setMarca(
                    row.getCell(3)
                            .getStringCellValue());

            p.setTalle(
                    row.getCell(4)
                            .getStringCellValue());

            p.setColor(
                    row.getCell(5)
                            .getStringCellValue());

            p.setStock(
                    (int) row.getCell(6)
                            .getNumericCellValue());

            dao.guardar(p);
        }

        wb.close();
        fis.close();
    }

    public void actualizarStock(
            String qr,
            int nuevoStock) throws Exception {

        FileInputStream fis =
                new FileInputStream(FILE);

        Workbook wb =
                new XSSFWorkbook(fis);

        Sheet sheet =
                wb.getSheetAt(0);

        for(Row row : sheet) {

            if(row.getRowNum()==0)
                continue;

            String qrExcel =
                    row.getCell(0)
                            .getStringCellValue();

            if(qrExcel.equals(qr)) {

                row.getCell(6)
                        .setCellValue(nuevoStock);

                break;
            }
        }

        fis.close();

        FileOutputStream fos =
                new FileOutputStream(FILE);

        wb.write(fos);

        fos.close();
        wb.close();
    }
}