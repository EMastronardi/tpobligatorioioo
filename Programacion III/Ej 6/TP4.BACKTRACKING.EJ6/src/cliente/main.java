package cliente;

import java.util.ArrayList;
import java.util.List;

import modelo.Procesador;
import modelo.ProcessManager;
import modelo.Tarea;

public class main {

	public static void main(String[] args) {
		List<Procesador> procesadores = new ArrayList<Procesador>();
		List<Tarea> tareas = new ArrayList<Tarea>();
		tareas.add(new Tarea("tarea 1", 1));
		tareas.add(new Tarea("tarea 2", 100));
		tareas.add(new Tarea("tarea 3", 3));
		tareas.add(new Tarea("tarea 4", 2));
		tareas.add(new Tarea("tarea 5", 5));
		
		procesadores.add(new Procesador("Procesador 1"));
		procesadores.add(new Procesador("Procesador 2"));
		ProcessManager pm = new ProcessManager(procesadores, tareas);
		
		pm.distribuirTareas();
		
		
		System.out.println("Lista:");
		for (Procesador item : procesadores) {
			System.out.println(" - Procesador " + item.getNombre());
			for (Tarea tarea : item.getTareas()) {
				System.out.println("     - " + tarea.getNombre());
			}
		}
		System.out.println("Fin");
		
	}

}
