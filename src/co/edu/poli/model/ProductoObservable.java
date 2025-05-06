package co.edu.poli.model;

import java.util.ArrayList;
import java.util.List;

public class ProductoObservable {
    protected List<IProductoObserver> observadores = new ArrayList<>();

    public void registrarObservador(IProductoObserver obs) {
        observadores.add(obs);
    }

    public void eliminarObservador(IProductoObserver obs) {
        observadores.remove(obs);
    }

    public void notificarObservadores(Producto producto) {
        for (IProductoObserver obs : observadores) {
            obs.actualizar(producto);
        }
    }
}
