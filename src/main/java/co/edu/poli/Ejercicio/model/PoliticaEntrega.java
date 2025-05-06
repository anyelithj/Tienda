package co.edu.poli.Ejercicio.model;

import java.util.List;

public class PoliticaEntrega {
    private int tiempoMaximo;
    private double costoEnvio;
    private boolean requiereSeguro;
    private List<String> zonasCobertura;

    public PoliticaEntrega(int tiempoMaximo, double costoEnvio, boolean requiereSeguro, List<String> zonasCobertura) {
        this.tiempoMaximo = tiempoMaximo;
        this.costoEnvio = costoEnvio;
        this.requiereSeguro = requiereSeguro;
        this.zonasCobertura = zonasCobertura;
    }

    public int getTiempoMaximo() { return tiempoMaximo; }
    public double getCostoEnvio() { return costoEnvio; }
    public boolean isRequiereSeguro() { return requiereSeguro; }
    public List<String> getZonasCobertura() { return zonasCobertura; }
}