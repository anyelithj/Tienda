package co.edu.poli.Ejercicio.model;


/*public class Proveedor {
    private String nombre;
    private String direccion;
    private Certificacion certificacion;
    private Evaluacion evaluacion;
    private PoliticaEntrega politicaEntrega;

    private Proveedor(Builder builder) {
        this.nombre = builder.nombre;
        this.direccion = builder.direccion;
        this.certificacion = builder.certificacion;
        this.evaluacion = builder.evaluacion;
        this.politicaEntrega = builder.politicaEntrega;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Certificacion getCertificacion() {
        return certificacion;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public PoliticaEntrega getPoliticaEntrega() {
        return politicaEntrega;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", certificacion=" + certificacion +
                ", evaluacion=" + evaluacion +
                ", politicaEntrega=" + politicaEntrega +
                '}';
    }

    public static class Builder {
        private String nombre;
        private String direccion;
        private Certificacion certificacion;
        private Evaluacion evaluacion;
        private PoliticaEntrega politicaEntrega;

        public Builder(String nombre, String direccion) {
            this.nombre = nombre;
            this.direccion = direccion;
        }

        public Builder certificacion(Certificacion certificacion) {
            this.certificacion = certificacion;
            return this;
        }

        public Builder evaluacion(Evaluacion evaluacion) {
            this.evaluacion = evaluacion;
            return this;
        }

        public Builder politicaEntrega(PoliticaEntrega politicaEntrega) {
            this.politicaEntrega = politicaEntrega;
            return this;
        }

        public Proveedor build() {
            return new Proveedor(this);
        }
    }
}*/
import java.util.List;

public class Proveedor {
    private String id;
    private String nombre;
    private String contacto;
    private List<Certificacion> certificaciones;
    private List<Evaluacion> evaluaciones;
    private PoliticaEntrega politicaEntrega;

    // Constructor privado para ser usado por el Builder
    private Proveedor(String id, String nombre, String contacto, List<Certificacion> certificaciones,
                      List<Evaluacion> evaluaciones, PoliticaEntrega politicaEntrega) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.certificaciones = certificaciones;
        this.evaluaciones = evaluaciones;
        this.politicaEntrega = politicaEntrega;
    }

    // Getters para los atributos
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
    public List<Certificacion> getCertificaciones() { return certificaciones; }
    public List<Evaluacion> getEvaluaciones() { return evaluaciones; }
    public PoliticaEntrega getPoliticaEntrega() { return politicaEntrega; }

    // Clase interna Builder
    public static class ProveedorBuilder {
        private String id;
        private String nombre;
        private String contacto;
        private List<Certificacion> certificaciones;
        private List<Evaluacion> evaluaciones;
        private PoliticaEntrega politicaEntrega;

        public ProveedorBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ProveedorBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public ProveedorBuilder setContacto(String contacto) {
            this.contacto = contacto;
            return this;
        }

        public ProveedorBuilder setCertificaciones(List<Certificacion> certificaciones) {
            this.certificaciones = certificaciones;
            return this;
        }

        public ProveedorBuilder setEvaluaciones(List<Evaluacion> evaluaciones) {
            this.evaluaciones = evaluaciones;
            return this;
        }

        public ProveedorBuilder setPoliticaEntrega(PoliticaEntrega politicaEntrega) {
            this.politicaEntrega = politicaEntrega;
            return this;
        }

        public Proveedor build() {
            return new Proveedor(id, nombre, contacto, certificaciones, evaluaciones, politicaEntrega);
        }
    }
}



