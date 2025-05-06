package co.edu.poli.Ejercicio.model;

import java.util.List;

public abstract class Componente {
    protected String nombre;

    public Componente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Componente> getComponente() {
        return null;
    }

    public abstract void mostrar();
    
    @Override
    public String toString() {
        return "Componente [nombre=" + nombre + "]";
    }
}
