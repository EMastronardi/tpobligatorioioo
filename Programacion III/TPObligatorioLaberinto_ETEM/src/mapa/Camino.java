package mapa;

import grafico.Area;
import grafico.Punto;
import grafico.PuntoTDA;

import java.util.ArrayList;
import java.util.List;
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
	public List<PuntoTDA> buscarCamino() throws Exception {
		
		List<PuntoTDA> resultado = new Vector<PuntoTDA>();
		List<NodoTDA> lista =  buscarSolucion();

		if(destino.getPadre() == null){
			throw new Exception("No existe un camino solución para el laberinto especificado");
		}
		resultado.add(origen.getUbicacion());
		for (NodoTDA nodo : lista) {
			resultado.add(nodo.getUbicacion());
		}
		
		
		return resultado;
	}

	
	
	private List<NodoTDA> buscarSolucion() {
		Set<NodoTDA> listaAbierta = new TreeSet<NodoTDA>();
		List<NodoTDA> listaCerrada = new Vector<NodoTDA>();
		
		
		List<NodoTDA> solucion = new Vector<NodoTDA>();
		
	
		NodoTDA nodoActual = origen;
		nodoActual.setG(0);
		nodoActual.setH(destino.getUbicacion());
		listaAbierta.add(nodoActual);
	
		
		do  {
			List<NodoTDA> adyacentes  = getNodosValidos(mapa.getAdyacentes(nodoActual.getUbicacion()),nodoActual, listaCerrada, listaAbierta);
			
			listaAbierta.addAll(adyacentes);
			
			Vector<NodoTDA> abierta = new Vector<NodoTDA>(listaAbierta);
			abierta.remove(nodoActual);
			listaAbierta.clear();
			listaAbierta.addAll(abierta);
			listaCerrada.add(nodoActual);
		
					
			if(listaAbierta.size() > 0){
				//nodoActual = listaAbierta.iterator().next();
				Iterator<NodoTDA> it = listaAbierta.iterator();
				NodoTDA siguiente =it.next();
				
				if(nodoActual.equals(destino)){
					destino.setPadre(nodoActual);
					break;
				}
					nodoActual = siguiente;
					
					
				
			}
			
			
			
			
			
		}
		while(listaAbierta.size() > 0 );
		
		nodoActual = destino;
		
		
		while (nodoActual.getPadre() != null) {
			
			solucion.add(nodoActual);
			nodoActual = nodoActual.getPadre();
		
		}
		
		
		return solucion;
	}

	private List<NodoTDA> getNodosValidos(List<PuntoTDA> adyacentes, NodoTDA nodoActual, List<NodoTDA> listaCerrada, Set<NodoTDA> listaAbierta) {
		List<NodoTDA> nodos = new Vector<NodoTDA>();
		
		List<NodoTDA> abiertaLista = new Vector<NodoTDA>(listaAbierta);
		boolean parar = false;
		if(nodoActual.getUbicacion().getX() == 2 && nodoActual.getUbicacion().getY() == 1){
			
			parar = true;
		}
		for (PuntoTDA punto : adyacentes) {
			NodoTDA nodo = new Nodo(punto);
			
			
			
			if(!listaCerrada.contains(nodo) && !abiertaLista.contains(nodo)){
				nodos.add(nodo);
				nodo.setPadre(nodoActual);
				nodo.setH(destino.getUbicacion());
				
				int densidad = mapa.getDensidad(punto);
				int distancia = (int) (Math.round(nodoActual.calcularDistancia(punto)) * densidad);
				
				
				nodo.setG(nodoActual.f() + distancia);
				
			}
			else{
				if(nodo.equals(origen))
					continue;
				
				int index = listaCerrada.indexOf(nodo);
				
				if(index < 0){
					index = abiertaLista.indexOf(nodo);
					nodo = abiertaLista.get(index);
				}else{
					nodo = listaCerrada.get(index);
				}
				
				//nodo.setPadre(nodoActual);
			}
			
		}
		
		
		
		
		return nodos;

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
