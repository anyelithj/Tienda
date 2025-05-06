package co.edu.poli.Ejercicio.model;
public class Puntos extends DecoratorCarrito {
    private int puntosUsados;

    public Puntos(CarritoCompra carrito, int puntosUsados) {
        super(carrito);
        this.puntosUsados = puntosUsados;
    }

    @Override
    public double obtenerTotal() {
        return carrito.obtenerTotal() - puntosUsados;
    }

    @Override
    public String obtenerDescripcion() {
        return " + Puntos usados: " + puntosUsados;
    }
}
