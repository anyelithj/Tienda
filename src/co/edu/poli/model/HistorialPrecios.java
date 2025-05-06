package co.edu.poli.model;

import java.util.ArrayList;
import java.util.List;

public class HistorialPrecios {
    private int productId;
    private List<ProductoMemento> historial = new ArrayList<>();

    public void agregarMemento(ProductoMemento memento) {
        historial.add(memento);
    }

    public List<ProductoMemento> obtenerHistorialProducto() {
        return historial;
    }
}
