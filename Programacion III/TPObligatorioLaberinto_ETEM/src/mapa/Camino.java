package mapa;

import grafico.Area;
import grafico.Punto;
import grafico.PuntoTDA;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Iterator;

import sun.security.krb5.internal.PAData;
import vista.Ventana;

public class Camino implements CaminoTDA{
	private MapaTDA mapa;
	private NodoTDA origen;
	private NodoTDA destino;
	
	public Camino(MapaTDA mapa){
		this.mapa = mapa;
	}
	
	@Override
	public List<PuntoTDA> buscarCamino() {
		
		List<PuntoTDA> resultado = buscarSolucion();

		if(destino.getPadre() == null){
			//throw new Exception("No existe un camino solución para el laberinto especificado");
			System.err.println("No existe un camino solución para el laberinto especificado");
		}

		return resultado;
	}

	private List<PuntoTDA> buscarSolucion() {
		PriorityQueue<NodoTDA> listaAbierta = new PriorityQueue<NodoTDA>();
		int[][] nodosEstados = new int[mapa.getGrilla().length][mapa.getGrilla().length];
		List<PuntoTDA> solucion = new Vector<PuntoTDA>();
			
		NodoTDA nodoActual = origen;
		nodoActual.setG(0);
		nodoActual.setH(destino.getUbicacion());
		listaAbierta.add(nodoActual);
	
		nodosEstados[nodoActual.getUbicacion().getX()][nodoActual.getUbicacion().getY()] = 1;
		do  {
			getNodosValidos(mapa.getAdyacentes(nodoActual.getUbicacion()),nodoActual, nodosEstados, listaAbierta);
			
			listaAbierta.remove(nodoActual);
			
			nodosEstados[nodoActual.getUbicacion().getX()][nodoActual.getUbicacion().getY()] = 2;
			
			if(!listaAbierta.isEmpty()){
				NodoTDA siguiente =listaAbierta.peek();
				
				if(nodoActual.equals(destino)){
					destino.setPadre(nodoActual);
				}
				nodoActual = siguiente;
			}
			
		}
		while(listaAbierta.size() > 0 && destino.getPadre() == null );
		
		nodoActual = destino;
		
		while (nodoActual.getPadre() != null) {
			solucion.add(nodoActual.getUbicacion());
			nodoActual = nodoActual.getPadre();
		}
		
		return solucion;
	}

	private void getNodosValidos(List<PuntoTDA> adyacentes, NodoTDA nodoActual, int[][] nodosEstados, PriorityQueue<NodoTDA> listaAbierta) {
		for (PuntoTDA punto : adyacentes) {
			
			if(nodosEstados[punto.getX()][punto.getY()] == 0){
				NodoTDA nodo = new Nodo(punto);
				
				nodo.setPadre(nodoActual);
				nodo.setH(destino.getUbicacion());
				
				int densidad = mapa.getDensidad(punto);
				int distancia = nodoActual.calcularDistancia(punto) * densidad;
				
				nodo.setG(nodoActual.getG() + distancia);
				
				listaAbierta.add(nodo);
				nodosEstados[nodo.getUbicacion().getX()][nodo.getUbicacion().getY()] = 1;
			}
		}
	}

	@Override
	public NodoTDA getDestino() {
		return destino;
	}

	@Override
	public NodoTDA getOrigen() {
		return origen;
	}

	@Override
	public void setDestino(NodoTDA destino) {
		this.destino = destino;
		
	}

	@Override
	public void setDestino(PuntoTDA punto) {
		this.destino = new Nodo(punto);
	}

	@Override
	public void setOrigen(NodoTDA origen) {
		this.origen = origen;
		
	}

	@Override
	public void setOrigen(PuntoTDA punto) {
		this.origen = new Nodo(punto);
	}

}
