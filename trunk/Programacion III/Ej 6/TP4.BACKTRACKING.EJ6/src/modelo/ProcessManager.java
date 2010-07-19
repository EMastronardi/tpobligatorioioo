package modelo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ProcessManager {
	List<Procesador> procesadores;
	List<Tarea> tareas;
	private int pasadas;
	
	public ProcessManager(List<Procesador> procesadores, List<Tarea> tareas) {
		
		this.procesadores = procesadores;
		this.tareas = tareas;
	}
	
	public void distribuirTareas() {
		List<ItemSolucion> soluciones = new ArrayList<ItemSolucion>();
		
		//tareas.get(0).setProcesador(procesadores.get(0));
		asignarTareas(soluciones);
		
		for (Procesador procesador : procesadores) {
			procesador.clear();
		}
		
		for (ItemSolucion itemSolucion : soluciones) {
			int index = procesadores.indexOf(itemSolucion.getProcesador());
			procesadores.get(index).addTarea(itemSolucion.getTarea());
		}
		System.out.println(pasadas);
	}

	private void asignarTareas(List<ItemSolucion> solucionActual ) {
		
		if(!existeTareaSinAsignar()){

			
			
			if(solucionActual.size() == 0){
				solucionActual.addAll(generarSolucionParcial());
			}
			else{
				int totalActual = calcularMaximo(solucionActual);
				
				List<ItemSolucion> solucionParcial = generarSolucionParcial();
				int totalParcial = calcularMaximoParcial();
				
				if(totalParcial < totalActual){
					solucionActual.clear();
					solucionActual.addAll(solucionParcial);
				}
			}
			
			
			
			return;
		}

		for (Tarea tarea : tareas) {
			if(!tarea.isAsignada()){
				
				for (Procesador procesador : procesadores) {
					
//					System.out.println("Asigna " + tarea.getNombre() + " a " + procesador.getNombre());
					tarea.setProcesador(procesador);
					asignarTareas(solucionActual);
					tarea.removerProcesador();
				}
					
			}
		
		}
		
						
	}


	

	private int calcularMaximoParcial() {
		int total = 0;
		
		for (Procesador procesador : procesadores) {
			if(procesador.getTiempoTotal() > total){
				total = procesador.getTiempoTotal();
			}
		}
		
		
		return total;
	}

	private Tarea getTareaSinProcesar() {
		for (Tarea tarea : tareas) {
			if(!tarea.isAsignada())
				return tarea;
		}
		
		return null;
	}


	private List<ItemSolucion> generarSolucionParcial() {
		List<ItemSolucion> solucion = new ArrayList<ItemSolucion>();
		
		for (Tarea tarea : tareas) {
			solucion.add(new ItemSolucion(tarea, tarea.getProcesador()));
		}
		
		
		
		return solucion;
		
	}


	private int calcularMaximo(List<ItemSolucion> soluciones) {
		int total = 0;
		
		Hashtable<Procesador, Integer> totales= new Hashtable<Procesador,Integer>();
		for (ItemSolucion item : soluciones) {
			if(!totales.containsKey(item.procesador)){
				totales.put(item.getProcesador(), 0);
			}
			
//			System.out.println("Asigna " + item.getTarea().getNombre() + " a " + item.getProcesador().getNombre());
			int totalParcial = totales.get(item.getProcesador()) + item.getTarea().getTiempo();
			totales.put(item.getProcesador(), totalParcial);
			
		}
		
		for (Procesador item : totales.keySet()) {
			if(totales.get(item) > total){
				total= totales.get(item);
			}
		}
		
		return total;
	}


	private boolean existeTareaSinAsignar() {
		for (Tarea tarea : tareas) {
			if(!tarea.isAsignada())
				return true;
		}
		
		return false;
	} 

	
}
