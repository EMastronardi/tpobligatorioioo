package modelo;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
	
	List<Tarea> tareas;
	String nombre;
	private int tiempoTotal;
	
	public Procesador(String nombre) {
		this.nombre = nombre;
		this.tareas = new ArrayList<Tarea>();
	}

	public void addTarea(Tarea tarea) {
		tareas.add(tarea);
		tiempoTotal+=tarea.getTiempo();
	}
	
	public int getTiempoTotal(){
		return this.tiempoTotal;
		
	}
	
	public void removeTarea(Tarea tarea){
		this.tareas.remove(tarea);
		tiempoTotal-=tarea.getTiempo();
	}

	public void clear() {
		this.tareas.clear();
		
	}

	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public String getNombre() {
		return this.nombre;
	}
	
}
