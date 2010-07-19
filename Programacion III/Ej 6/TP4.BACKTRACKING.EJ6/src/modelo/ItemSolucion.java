package modelo;

import java.util.List;

public class ItemSolucion {
	
	Tarea tarea;
	Procesador procesador;
	
	
	public ItemSolucion(Tarea tarea, Procesador procesador) {

		this.tarea = tarea;
		this.procesador = procesador;
		
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Procesador getProcesador() {
		return procesador;
	}

	public void setProcesador(Procesador procesador) {
		this.procesador = procesador;
	}
	
	public String toString(){
		return this.tarea.nombre + " - " + this.procesador.nombre;
	
	}
	
}
