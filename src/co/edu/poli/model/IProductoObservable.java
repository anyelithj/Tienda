package co.edu.poli.model;

public interface IProductoObservable {
    void registrarObservador(IProductoObserver obs);
    void eliminarObservador(IProductoObserver obs);
    void notificarObservadores(Producto producto);
}