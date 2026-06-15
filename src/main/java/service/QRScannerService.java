package service;

import java.util.Scanner;

public class QRScannerService {

    public String leerQR() {

        Scanner sc =
                new Scanner(System.in);

        System.out.print(
                "Escanee QR: ");

        return sc.nextLine();
    }
}