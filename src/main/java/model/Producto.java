package model;

public class Producto {

 private String qr;
 private String codigo;
 private String descripcion;
 private String marca;
 private String talle;
 private String color;
 private int stock;

 public Producto() {}

 public Producto(String qr,
                 String codigo,
                 String descripcion,
                 String marca,
                 String talle,
                 String color,
                 int stock) {

  this.qr = qr;
  this.codigo = codigo;
  this.descripcion = descripcion;
  this.marca = marca;
  this.talle = talle;
  this.color = color;
  this.stock = stock;
 }

 public String getQr() { return qr; }
 public void setQr(String qr) { this.qr = qr; }

 public String getCodigo() { return codigo; }
 public void setCodigo(String codigo) { this.codigo = codigo; }

 public String getDescripcion() { return descripcion; }
 public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

 public String getMarca() { return marca; }
 public void setMarca(String marca) { this.marca = marca; }

 public String getTalle() { return talle; }
 public void setTalle(String talle) { this.talle = talle; }

 public String getColor() { return color; }
 public void setColor(String color) { this.color = color; }

 public int getStock() { return stock; }
 public void setStock(int stock) { this.stock = stock; }
}