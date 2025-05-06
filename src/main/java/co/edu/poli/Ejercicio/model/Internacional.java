package co.edu.poli.Ejercicio.model;

public class Internacional implements Envio {
    private Mercancia mercancia;

    @Override
    public String enviar() {
        String mensaje = "Enviando mercancía a nivel internacional";
        System.out.println(mensaje);
        return mensaje;
    }

    @Override
    public void setMercancia(Mercancia mercancia) {
        this.mercancia = mercancia;
    }

    @Override
    public Mercancia getMercancia() {
        return mercancia;
    }
}

