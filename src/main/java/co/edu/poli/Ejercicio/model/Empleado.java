package co.edu.poli.Ejercicio.model;

public abstract class Empleado extends Componente {
	
	    

	private String Id;

	public Empleado(String nombre, String Id) {
        super(nombre);
    }

	    public void Empelado(String Id, String nombre) {
	        this.Id= Id;
	        this.nombre = nombre;
	    }

	    public String getId() {
	        return this.Id;
	    }

	    public void setId(String Id) {
	        this.Id = Id;
	    }

	    public String getNombre() {
	        return this.nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    @Override
	    public String toString() {
	        return "Cliente [id=" + this.Id + ", nombre=" + this.nombre + "]";
	    }
	}

