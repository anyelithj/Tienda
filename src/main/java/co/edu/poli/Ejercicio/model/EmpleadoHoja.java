package co.edu.poli.Ejercicio.model;

public class EmpleadoHoja extends Empleado {

    public EmpleadoHoja(String nombre, String id) {
        super(nombre, id);
    }

    @Override
    public void mostrar() {
        System.out.println("Empleado: " + getNombre() + " | ID: " + getId());
    }
}
