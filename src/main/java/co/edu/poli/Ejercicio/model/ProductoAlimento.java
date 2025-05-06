package co.edu.poli.Ejercicio.model;

public class ProductoAlimento extends Producto implements ProductoF {
    private int aporteCalorico;

    public ProductoAlimento(String id, String descripcion, double precio, String tipo, int aporteCalorico) {
        super(id, descripcion, precio, tipo);
        this.aporteCalorico = aporteCalorico;
    }

    public int getAporteCalorico() {
        return aporteCalorico;
    }

    @Override
    public String obtenerCaracteristica() {
        return "Aporte Calórico: " + aporteCalorico + " kcal";
    }

    @Override
    public Producto crearProducto(String id, String descripcion, double precio, String tipo, String voltajeEntrada, Integer aporteCalorico) {
        return new ProductoAlimento(id, descripcion, precio, tipo, aporteCalorico);
    }

    @Override
    public Producto clone() {
        String nuevoId = java.util.UUID.randomUUID().toString(); // Generar nuevo ID
        return new ProductoAlimento(nuevoId, this.descripcion, this.precio, this.tipo, this.aporteCalorico);
    }

}