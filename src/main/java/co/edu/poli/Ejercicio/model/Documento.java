package co.edu.poli.Ejercicio.model;

public class Documento extends Mercancia {
    private String tipoDocumento;

    public Documento(Envio envio, String descripcion, String tipoDocumento) {
        super(envio, descripcion);
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public boolean procesar() {
        System.out.println("Procesando documento");
        return true;
    }
}