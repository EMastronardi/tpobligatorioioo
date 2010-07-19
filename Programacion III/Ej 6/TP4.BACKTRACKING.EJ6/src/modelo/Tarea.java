package modelo;

public class Tarea {
	String nombre;
	int tiempo;
	Procesador procesador;
	
	public Tarea(String nombre, int tiempo) {
	
		this.nombre = nombre;
		this.tiempo = tiempo;
	}

	public int getTiempo() {
		
		return tiempo;
	}

	public boolean isAsignada() {
		return procesador != null;
	}

	public void setProcesador(Procesador procesador) {
		this.procesador = procesador;
		this.procesador.addTarea(this);
	}

	public Procesador getProcesador() {
		return this.procesador;
	}
	
	public void removerProcesador(){
		if (this.procesador != null){
			this.procesador.removeTarea(this);
		}
		this.procesador = null;
	}

	public String getNombre() {
		return this.nombre;
	}
	
}
