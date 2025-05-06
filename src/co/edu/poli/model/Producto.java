package co.edu.poli.model;

import java.util.Date;

public class Producto extends ProductoObservable {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private Date fechaActualizacion;
    private HistorialPrecios historial = new HistorialPrecios();

    public Producto(int id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaActualizacion = new Date();
    }

    public double getPrecio() { return precio; }
    public String getNombre() { return nombre; }

    public void setPrecio(double nuevoPrecio) {
        historial.agregarMemento(crearMemento());
        this.precio = nuevoPrecio;
        this.fechaActualizacion = new Date();
        notificarObservadores(this);
    }

    public ProductoMemento crearMemento() {
        return new ProductoMemento(id, precio, fechaActualizacion);
    }

    public void restaurarMemento(ProductoMemento memento) {
        this.precio = memento.getPrecio();
        this.fechaActualizacion = memento.getFecha();
        notificarObservadores(this);
    }

    public HistorialPrecios getHistorial() {
        return historial;
    }
}
