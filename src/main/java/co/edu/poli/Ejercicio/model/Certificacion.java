package co.edu.poli.Ejercicio.model;

import java.util.Date;


public class Certificacion {
    private String id;
    private String nombre;
    private Date fechaEmision;
    private Date fechaExpiracion;

    public Certificacion(String id, String nombre, Date fechaEmision, Date fechaExpiracion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaEmision = fechaEmision;
        this.fechaExpiracion = fechaExpiracion;
    }

    // Getters para los atributos
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public Date getFechaEmision() { return fechaEmision; }
    public Date getFechaExpiracion() { return fechaExpiracion; }
}


/*
public class Certificacion {
    private String nombre;
    private String entidadEmisora;
    // Constructor, getters, setters
    public Certificacion(String nombre, String entidadEmisora) {
        this.nombre = nombre;
        this.entidadEmisora = entidadEmisora;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEntidadEmisora() {
        return entidadEmisora;
    }
    public void setEntidadEmisora(String entidadEmisora) {
        this.entidadEmisora = entidadEmisora;
    }
    @Override
    public String toString() {
        return "Certificacion{" +
                "nombre='" + nombre + '\'' +
                ", entidadEmisora='" + entidadEmisora + '\'' +
                '}';
    }
}*/
// package co.edu.poli.Ejercicio.model;

// import java.util.Date;
