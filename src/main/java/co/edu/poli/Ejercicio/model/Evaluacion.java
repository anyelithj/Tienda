package co.edu.poli.Ejercicio.model;

import java.util.Date;

public class Evaluacion {
    private String id;
    private Date fecha;
    private double puntuacion;
    private String comentarios;

    public Evaluacion(String id, Date fecha, double puntuacion, String comentarios) {
        this.id = id;
        this.fecha = fecha;
        this.puntuacion = puntuacion;
        this.comentarios = comentarios;
    }

    public String getId() { return id; }
    public Date getFecha() { return fecha; }
    public double getPuntuacion() { return puntuacion; }
    public String getComentarios() { return comentarios; }
}