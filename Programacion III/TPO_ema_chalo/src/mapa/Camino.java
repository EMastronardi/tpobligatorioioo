package mapa;

import grafico.PuntoTDA;

import java.util.ArrayList;
import java.util.List;

import vista.Ventana;

public class Camino implements CaminoTDA {
	private MapaTDA mapa;
	private NodoTDA origen;
	private NodoTDA destino;
	private float raizDeDos = (float) Math.sqrt(2);

	public Camino(MapaTDA mapa) {
		this.mapa = mapa;
	}

	public List<PuntoTDA> buscarCamino() {
		List<PuntoTDA> cmc;
		List<NodoTDA> listaAbierta = new ArrayList<NodoTDA>();
		List<NodoTDA> listaCerrada = new ArrayList<NodoTDA>();

		System.out.println("Voy a buscar el camino mas corto");
		listaCerrada.add(this.origen);
		cmc = buscarCaminoIterativo(listaAbierta, listaCerrada, this.origen);
		// implementacion recursiva
		// fin de la implementacion recusrsiva
		Ventana.camino = new Camino(mapa);
		System.out.println("Encontré el camino mas corto");
		return cmc;
	}
	
	public List<PuntoTDA> buscarCaminoIterativo(List<NodoTDA> listaAbierta,
		List<NodoTDA> listaCerrada, NodoTDA nodoActual) {
		List<NodoTDA> adyacentes;
		NodoTDA nodoSiguiente;
		int indiceNodoSiguiente;
		boolean OK = true;
		
		while (OK) {
			if (nodoActual.esIgual(this.destino)) {
				OK = false;
			}
			// agregar adyacentes a lista abierta
			adyacentes = convertirAListaDeNodos(this.mapa.getAdyacentes(nodoActual.getUbicacion()));
			asiganarPadreANodos(adyacentes, nodoActual);
			concatenarListasNodos(listaAbierta, listaCerrada, adyacentes);
			// evaluo cual nodo es el siguiente en el camino
			indiceNodoSiguiente = obtenerMininoListaNodos(listaAbierta, nodoActual);
			nodoSiguiente = listaAbierta.get(indiceNodoSiguiente);
			listaCerrada.add(nodoSiguiente);
			nodoActual = nodoSiguiente;
			listaAbierta.remove(indiceNodoSiguiente);
		}
		return generarCaminoConListaDePuntos(listaCerrada);
	}

	public NodoTDA getDestino() {
		return destino;
	}

	@Override
	public NodoTDA getOrigen() {
		return origen;
	}

	@Override
	public void setDestino(NodoTDA destino) {
		// TODO Auto-generated method stub
		//System.out.println("Creo destino con un nodo");
		this.destino = destino;
		PuntoTDA punto = this.destino.getUbicacion();
		this.destino.setDensidad(getDensidadPunto(punto));
		this.destino.setHeuristica(getHeuristicaPunto(punto));
	}

	@Override
	public void setDestino(PuntoTDA punto) {
		//System.out.println("Creo destino con un punto");
		//this.destino = new Nodo(punto, this.getHeuristicaPunto(punto), this.getDensidadPunto(punto));
		this.destino = new Nodo(punto);
		this.destino.setDensidad(getDensidadPunto(punto));
		this.destino.setHeuristica(getHeuristicaPunto(punto));
	}

	@Override
	public void setOrigen(NodoTDA origen) {
		// TODO Auto-generated method stub
		//System.out.println("Creo origen con un nodo");
		this.origen = origen;
		PuntoTDA punto = this.origen.getUbicacion();
		this.origen.setDensidad(getDensidadPunto(punto));
		this.origen.setHeuristica(getHeuristicaPunto(punto));
		this.origen.setG(getDensidadPunto(punto));
	}

	
	public void setOrigen(PuntoTDA punto) {
		//System.out.println("Creo origen con un punto");
		//this.origen = new Nodo(punto, this.getHeuristicaPunto(punto),this.getDensidadPunto(punto));
		this.origen = new Nodo(punto);
		this.origen.setDensidad(getDensidadPunto(punto));
		this.origen.setHeuristica(getHeuristicaPunto(punto));
		this.origen.setG(getDensidadPunto(punto));
	}

	public int getDensidadPunto(PuntoTDA p) {
		return mapa.getDensidad(p);
	}

	public float getHeuristicaPunto(PuntoTDA p) {
		float heuristica = 0;
		int cordX = p.getX();
		int cordY = p.getY();
		int destX = destino.getUbicacion().getX();
		int destY = destino.getUbicacion().getY();
		while (!(cordX == destX && cordY == destY)) {

			if (cordX > destX) {
				if (cordY > destY) {
					heuristica += raizDeDos;
					cordX--;
					cordY--;
				} else if (cordY == destY) {
					heuristica += 1;
					cordX--;
				} else if (cordY < destY) {
					heuristica += raizDeDos;
					cordX--;
					cordY++;
				}
			} else if (cordX == destX) {
				if (cordY > destY) {
					heuristica += 1;
					cordY--;
				} else if (cordY < destY) {
					heuristica += 1;
					cordY++;
				}
			} else if (cordX < destX) {
				if (cordY > destY) {
					heuristica += raizDeDos;
					cordX++;
					cordY--;
				} else if (cordY == destY) {
					heuristica += 1;
					cordX++;
				} else if (cordY < destY) {
					heuristica += raizDeDos;
					cordX++;
					cordY++;
				}

			}
		}
		// System.out.println("Heuristica parcial: "+heuristica);
		return heuristica;

	}

	private List<PuntoTDA> generarCaminoConListaDePuntos(
			List<NodoTDA> listaCerrada) {
		List<PuntoTDA> aux = new ArrayList<PuntoTDA>();
		List<PuntoTDA> valorRetorno = new ArrayList<PuntoTDA>();
		NodoTDA nodoActual;

		// agrego a la lista el punto destino
		nodoActual = listaCerrada.get(listaCerrada.size() - 1);
		aux.add(nodoActual.getUbicacion());
		// armo la lista de puntos que establecen el camino minimo
		while (nodoActual.getPadre() != null) {
			nodoActual = nodoActual.getPadre();
			aux.add(nodoActual.getUbicacion());
		}
		// invierto la lista por prolijidad
		for (int i = aux.size() - 1; i >= 0; i--) {
			valorRetorno.add(aux.get(i));
		}

		return valorRetorno;
	}

	/*
	 * private List<PuntoTDA> convertirAListaDePuntos(List<NodoTDA> lista){
	 * List<PuntoTDA> listaPuntos = new ArrayList<PuntoTDA>();
	 * 
	 * for (NodoTDA n: lista){ PuntoTDA punto = new
	 * Punto(n.getUbicacion().getX(),n.getUbicacion().getY());
	 * listaPuntos.add(punto); }
	 * 
	 * return listaPuntos; }
	 */

	private List<NodoTDA> convertirAListaDeNodos(List<PuntoTDA> lista) {
		List<NodoTDA> listaNodos = new ArrayList<NodoTDA>();

		for (PuntoTDA p : lista) {
			NodoTDA nodo = new Nodo(p,this.getHeuristicaPunto(p),getDensidadPunto(p));
			listaNodos.add(nodo);
		}

		return listaNodos;
	}

	private void concatenarListasNodos(List<NodoTDA> listaDestino,
			List<NodoTDA> listaVisitados, List<NodoTDA> listaFuente) {
		for (NodoTDA nodo : listaFuente) {
			if (!estaEnLista(listaDestino, nodo)
					&& (!estaEnLista(listaVisitados, nodo))) {
				listaDestino.add(nodo);
				//System.out.println("Agrego uno!");
			}
		}
	}

	private boolean estaEnLista(List<NodoTDA> lista, NodoTDA nodoActual) {
		for (NodoTDA nodo : lista) {
			if (nodo.esIgual(nodoActual)) {
				//if (nodo.getPadre() == nodoActual.getPadre()) {
					return true;
				//}
			}
		}
		return false;
	}

	private void asiganarPadreANodos(List<NodoTDA> listaNodos, NodoTDA padre) {
		for (NodoTDA nodo : listaNodos) {
			nodo.setPadre(padre);
		}
	}

	private int obtenerMininoListaNodos(List<NodoTDA> listaNodos,
			NodoTDA nodoActual) {
		int valorRetorno = -1;
		float fMinima = Float.MAX_VALUE;
		int tamanioLista = listaNodos.size();
		NodoTDA nodo;
		NodoTDA nodoMinimo = null;

		for (int i = 0; i < tamanioLista; i++) {
			nodo = listaNodos.get(i);
			if (nodo.f() < fMinima) {
				fMinima = nodo.f();
				nodoMinimo = nodo;
				valorRetorno = i;
			//	System.out.println("F es menor");
			} 
			
			else if (nodo.f() == fMinima) {
//				System.out.println("la f es igual");
//				System.out.print("Densidad nodo :"+nodo.getDensidad());
//				System.out.print(" Heurisitca nodo :"+nodo.getH());
//				System.out.println(" G nodo :"+nodo.getG());
//				System.out.print("Densidad nodo minimo:"+nodoMinimo.getDensidad());
//				System.out.print(" Heuristica nodo minomo:"+nodoMinimo.getH());
//				System.out.println(" G nodo minimo :"+nodoMinimo.getG());
				if (nodo.getH() < nodoMinimo.getH()) {
					fMinima = nodo.f();
					nodoMinimo = nodo;
					//System.out.println("me movi por densidad");
					valorRetorno = i;
				}
			}	
//					// seguro las G son iguales
//					if (this.getDensidadNodo(nodo) < this
//							.getDensidadNodo(nodoMinimo)) {
//						fMinima = nodo.f();
//						nodoMinimo = nodo;
//						valorRetorno = i;
//					} 
//					else if (this.getDensidadNodo(nodo) == this
//							.getDensidadNodo(nodoMinimo)) {
//						if (nodo.getPadre() == nodoActual) {
//							fMinima = nodo.f();
//							nodoMinimo = nodo;
//							valorRetorno = i;
//						}
//					}
//				}
//				// entonces eligo el de menor densidad
//				else {
//					if (this.getDensidadNodo(nodo) < this
//							.getDensidadNodo(nodoMinimo)) {
//						fMinima = nodo.f();
//						valorRetorno = i;
//						nodoMinimo = nodo;
//					}
//				}
//			}
		}
		if( valorRetorno == -1 ){
			System.out.println("algo paso");
		}
		return valorRetorno;
	}

}
