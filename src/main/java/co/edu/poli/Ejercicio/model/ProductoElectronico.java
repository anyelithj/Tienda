package co.edu.poli.Ejercicio.model;

public class ProductoElectronico extends Producto implements ProductoF {
    private String voltajeEntrada;

    public ProductoElectronico(String id, String descripcion, double precio, String tipo, String voltajeEntrada) {
        super(id, descripcion, precio, tipo);
        this.voltajeEntrada = voltajeEntrada;
    }

    public String getVoltajeEntrada() {
        return voltajeEntrada;
    }

    @Override
    public String obtenerCaracteristica() {
        return "Voltaje de entrada: " + voltajeEntrada;
    }

    @Override
    public Producto crearProducto(String id, String descripcion, double precio, String tipo, String voltajeEntrada, Integer aporteCalorico) {
        return new ProductoElectronico(id, descripcion, precio, tipo, voltajeEntrada);
    }

    @Override
    public Producto clone() {
        String nuevoId = java.util.UUID.randomUUID().toString(); // Generar nuevo ID
        return new ProductoElectronico(nuevoId, this.descripcion, this.precio, this.tipo, this.voltajeEntrada);
    }

}