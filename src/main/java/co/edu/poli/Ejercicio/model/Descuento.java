package co.edu.poli.Ejercicio.model;

public class Descuento extends DecoratorCarrito {
    private double porcentaje;

    public Descuento(CarritoCompra carrito, double porcentaje) {
        super(carrito);
        this.porcentaje = porcentaje;
    }

    @Override
    public double obtenerTotal() {
        return carrito.obtenerTotal() * (1 - porcentaje / 100);
    }

    @Override
    public String obtenerDescripcion() {
        return " + Descuento del " + porcentaje + "%";
    }
}