package model;

import java.time.LocalDateTime;

public class Venta {

    private Long id;
    private String qr;
    private LocalDateTime fecha;

    public Venta() {
    }

    public Venta(String qr) {
        this.qr = qr;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}