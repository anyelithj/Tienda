package co.edu.poli.Ejercicio.services;

public class PayPalAdapter implements Pagos {
    private PayPal paypal;
    private String mensajePago;

    public PayPalAdapter() {
        this.paypal = new PayPal();
    }

    @Override
    public void realizarPago(double monto) {
        this.mensajePago = paypal.hacerPagoPayPal(monto);
    }

    public String getMensajePago() {
        return mensajePago;
    }
}
