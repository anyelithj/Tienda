package co.edu.poli.Ejercicio.model;

public class EnvioGratis extends DecoratorCarrito {

    public EnvioGratis(CarritoCompra carrito) {
        super(carrito);
    }

    @Override
    public double obtenerTotal() {
        // Simulamos que el envío cuesta normalmente 10
        return carrito.obtenerTotal(); // Se descuenta automáticamente
    }

    @Override
    public String obtenerDescripcion() {
        return " + Envío Gratis";
    }
}