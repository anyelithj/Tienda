package co.edu.poli.Ejercicio.model;

public abstract class Mercancia {
    private Envio envio;
    private String descripcion;

    public Mercancia(Envio envio, String descripcion) {
        this.envio = envio;
        this.descripcion = descripcion;
    }

    public abstract String procesar();

    public Envio getEnvio() {
        return envio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

