package co.edu.poli.model;

public class ObservadorPrecio implements IProductoObserver {
    @Override
    public void actualizar(Producto producto) {
        System.out.println("El precio del producto ' " + producto.getNombre() + "' ha cambiado a $" + producto.getPrecio());
    }
}
