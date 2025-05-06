package co.edu.poli.Ejercicio.model;
public abstract class DecoratorCarrito implements CarritoCompra {
    protected CarritoCompra carrito;

    public DecoratorCarrito(CarritoCompra carrito) {
        this.carrito = carrito;
    }

    public CarritoCompra getCarrito() {
        return carrito;
    }

    // también puede tener:
    public abstract String obtenerDescripcion();
}