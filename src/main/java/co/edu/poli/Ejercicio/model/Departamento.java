package co.edu.poli.Ejercicio.model;

import java.util.ArrayList;
import java.util.List;

public class Departamento extends Componente {

    private List<Componente> componentes;

    public Departamento(String nombre) {
        super(nombre);
        this.componentes = new ArrayList<>();
    }

    public void agregar(Componente componente) {
        componentes.add(componente);
    }

    @Override
    public void mostrar() {
        System.out.println("Departamento: " + getNombre());
        for (Componente c : componentes) {
            c.mostrar();
        }
    }

    public void mostrarEstructura(String prefijo) {
        System.out.println(prefijo + "- " + getNombre());
        for (Componente componente : componentes) {
            if (componente instanceof Departamento) {
                ((Departamento) componente).mostrarEstructura(prefijo + "  ");
            } else if (componente instanceof EmpleadoHoja) {
                System.out.println(prefijo + "  - " + componente.getNombre());
            }
        }
    }

    @Override
    public List<Componente> getComponente() {
        return componentes;
    }
}

