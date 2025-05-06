package co.edu.poli.model;

import java.util.Date;

public class ProductoMemento {
    private int productId;
    private double precio;
    private Date fecha;

    public ProductoMemento(int productId, double precio, Date fecha) {
        this.productId = productId;
        this.precio = precio;
        this.fecha = fecha;
    }

    public double getPrecio() { return precio; }
    public Date getFecha() { return fecha; }
    public int getProductId() { return productId; }
}
