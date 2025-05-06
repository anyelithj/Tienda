package co.edu.poli.Ejercicio.model;

public class CargaPesada extends Mercancia {
    private double pesoMaximo;

    public CargaPesada(Envio envio, String descripcion, double pesoMaximo) {
        super(envio, descripcion);
        this.pesoMaximo = pesoMaximo;
    }

    @Override
    public String procesar() {
        return "Procesando carga pesada de al menos " + pesoMaximo + " kg";
    }
}
