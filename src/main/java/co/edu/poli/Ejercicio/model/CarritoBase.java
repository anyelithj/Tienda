package co.edu.poli.Ejercicio.model;

public class CarritoBase implements CarritoCompra {
    private double total;

    public CarritoBase(double total) {
        this.total = total;
    }

    @Override
    public double obtenerTotal() {
        return total;
    }
}
