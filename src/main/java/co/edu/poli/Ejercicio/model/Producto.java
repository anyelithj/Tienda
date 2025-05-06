package co.edu.poli.Ejercicio.model;

public abstract class Producto implements Cloneable {
    protected String id;
    protected String descripcion;
    protected double precio;
    protected String tipo; 
    public Producto(String id, String descripcion, double precio, String tipo) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo = tipo;
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public String getTipo() { return tipo; }

    public abstract String obtenerCaracteristica();
    
    @Override
    public abstract  Producto clone();
    
    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", tipo=" + tipo +
                '}';
    }
}
