package co.edu.poli.Ejercicio.services;

public class NequiAdapter implements Pagos {
    private Nequi nequi;
    private String mensajePago;

    public NequiAdapter() {
        this.nequi = new Nequi();
    }

    @Override
    public void realizarPago(double monto) {
        this.mensajePago = nequi.hacerPagoNequi(monto);
    }

    public String getMensajePago() {
        return mensajePago;
    }
}
