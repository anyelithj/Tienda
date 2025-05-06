package co.edu.poli.Ejercicio.model;

public class VIP extends DecoratorCarrito {

    public VIP(CarritoCompra carrito) {
        super(carrito);
    }

    @Override
    public double obtenerTotal() {
        return carrito.obtenerTotal() * 0.9; // 10% de descuento
    }

    @Override
    public String obtenerDescripcion() {
        return " + Cliente VIP (10% de descuento)";
    }
}