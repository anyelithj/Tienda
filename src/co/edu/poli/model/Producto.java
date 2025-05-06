package co.edu.poli.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producto implements IProductoObservable {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private Date fechaActualizacion;
    private HistorialPrecios historial = new HistorialPrecios();
    protected List<IProductoObserver> observadores = new ArrayList<>();

    public Producto(int id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaActualizacion = new Date();
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public HistorialPrecios getHistorial() {
        return historial;
    }

    public boolean setPrecio(double nuevoPrecio) {
        if (this.precio != nuevoPrecio) {
            historial.agregarMemento(crearMemento());
            this.precio = nuevoPrecio;
            this.fechaActualizacion = new Date();
            notificarObservadores(this);
            return true;
        }
        return false;
    }

    public ProductoMemento crearMemento() {
        return new ProductoMemento(id, precio, fechaActualizacion);
    }

    public void restaurarMemento(ProductoMemento memento) {
        this.precio = memento.getPrecio();
        this.fechaActualizacion = memento.getFecha();
        notificarObservadores(this);
    }

    @Override
    public void registrarObservador(IProductoObserver obs) {
        observadores.add(obs);
    }

    @Override
    public void eliminarObservador(IProductoObserver obs) {
        observadores.remove(obs);
    }

    @Override
    public void notificarObservadores(Producto producto) {
        for (IProductoObserver obs : observadores) {
            obs.actualizar(producto);
        }
    }
}