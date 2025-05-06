package co.edu.poli.Ejercicio.model;

public class CargaFragil extends Mercancia {
    private int pesoMinimo;

    public CargaFragil(Envio envio, String descripcion, int pesoMinimo) {
        super(envio, descripcion);
        this.pesoMinimo = pesoMinimo;
    }

    @Override
    public String procesar() {
        return "Procesando carga frágil de al menos " + pesoMinimo + " kg";
    }
  
}
