package mapa;

import grafico.PuntoTDA;

import java.util.ArrayList;
import java.util.Calendar;
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
		// 0 : nodo no visitado
		// 1 : nodo visitado
		int tamanioMatriz = 400;
		boolean [][]visitadosListaAbierta = new boolean[tamanioMatriz][tamanioMatriz];;
		boolean [][]visitadosListaCerrada = new boolean[tamanioMatriz][tamanioMatriz];;
		
		// resetear la matriz con cada camino
		for(int i = 0; i < tamanioMatriz; i++){
			for(int j = 0; j < tamanioMatriz; j++){
				visitadosListaAbierta[i][j] = false;
				visitadosListaCerrada[i][j] = false;
			}
		}
		// proceso principal
		System.out.println("Voy a buscar el camino mas corto");
		listaCerrada.add(this.origen);
		visitadosListaCerrada[this.origen.getUbicacion().getX()][this.origen.getUbicacion().getY()] = true;
		long startTime = Calendar.getInstance().getTimeInMillis();
		// implementacion recursiva
		cmc = buscarCaminoIterativo(listaAbierta, listaCerrada, visitadosListaAbierta, visitadosListaCerrada, this.origen);
		long endTime = Calendar.getInstance().getTimeInMillis();
		System.out.println("Encontré el camino mas corto en "+Long.toString((endTime - startTime)/1000)+" segundos.");
		System.out.println("Cant. de segundos transcurridos: "+Long.toString((endTime - startTime)/1000));
		System.out.println("---------------------------------------------------------");
		Ventana.camino = new Camino(mapa);
		return cmc;
	}
	
	public List<PuntoTDA> buscarCaminoIterativo(List<NodoTDA> listaAbierta,
		List<NodoTDA> listaCerrada, boolean [][]visitadosAbierta, boolean [][]visitadosCerrada, NodoTDA nodoActual) {
		List<NodoTDA> adyacentes;
		NodoTDA nodoSiguiente;
		int indiceNodoSiguiente;
		boolean OK = true;
		int contador = 0;
		while (OK) {
			System.out.println("contador: "+ contador);
			contador++;
			// agregar adyacentes a lista abierta
			adyacentes = convertirAListaDeNodos(this.mapa.getAdyacentes(nodoActual.getUbicacion()));
			asiganarPadreANodos(adyacentes, nodoActual);
			agregarAdyacentesAListaAbierta(listaAbierta, adyacentes, visitadosAbierta, visitadosCerrada);
			// evaluo cual nodo es el siguiente en el camino
			indiceNodoSiguiente = obtenerMininoListaNodos(listaAbierta, nodoActual);
			nodoSiguiente = listaAbierta.get(indiceNodoSiguiente);
			listaCerrada.add(nodoSiguiente);
			visitadosCerrada[nodoSiguiente.getUbicacion().getX()][nodoSiguiente.getUbicacion().getY()] = true;
			nodoActual = nodoSiguiente;
			listaAbierta.remove(indiceNodoSiguiente);
			System.out.println("tamaño lista abierta"+listaAbierta.size());
			visitadosAbierta[nodoSiguiente.getUbicacion().getX()][nodoSiguiente.getUbicacion().getY()] = false;
			if (nodoActual.esIgual(this.destino)) {
				OK = false;
			}
		}
		System.out.println("contador final!: "+ contador);
		return generarCaminoConListaDePuntos(listaCerrada);
	}

	public NodoTDA getDestino() {
		return destino;
	}

	public NodoTDA getOrigen() {
		return origen;
	}

	public void setDestino(NodoTDA destino) {
		//System.out.println("Creo destino con un nodo");
		this.destino = destino;
		PuntoTDA punto = this.destino.getUbicacion();
		this.destino.setDensidad(getDensidadPunto(punto));
		this.destino.setHeuristica(getHeuristicaPunto(punto));
	}

	public void setDestino(PuntoTDA punto) {
		//System.out.println("Creo destino con un punto");
		//this.destino = new Nodo(punto, this.getHeuristicaPunto(punto), this.getDensidadPunto(punto));
		this.destino = new Nodo(punto);
		this.destino.setDensidad(getDensidadPunto(punto));
		this.destino.setHeuristica(getHeuristicaPunto(punto));
	}

	public void setOrigen(NodoTDA origen) {
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

	private List<PuntoTDA> generarCaminoConListaDePuntos(List<NodoTDA> listaCerrada) {
		List<PuntoTDA> aux = new ArrayList<PuntoTDA>();
		List<PuntoTDA> valorRetorno = new ArrayList<PuntoTDA>();
		NodoTDA nodoActual;

		//System.out.println(" DEBUG: voy a generar el camino con la lista cerrada");
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
		//System.out.println(" DEBUG: fin del armado de la lista");
		return valorRetorno;
	}

	private List<NodoTDA> convertirAListaDeNodos(List<PuntoTDA> lista) {
		List<NodoTDA> listaNodos = new ArrayList<NodoTDA>();

		for (PuntoTDA p : lista) {
			NodoTDA nodo = new Nodo(p,this.getHeuristicaPunto(p),getDensidadPunto(p));
			listaNodos.add(nodo);
		}

		return listaNodos;
	}

	private void agregarAdyacentesAListaAbierta(List<NodoTDA> listaAbierta, 
										List<NodoTDA> listaAdyacentes, 
										boolean [][]visiAbierta,
										boolean [][]visiCerrada) {
		// lista destino es la lista abierta
		for (NodoTDA nodo : listaAdyacentes) {
			if (!estaEnLista(visiAbierta, nodo) && (!estaEnLista(visiCerrada, nodo))) {
				listaAbierta.add(nodo);
				visiAbierta[nodo.getUbicacion().getX()][nodo.getUbicacion().getY()] = true;
				//System.out.println("Agrego uno!");
			}
		}
	}

	private boolean estaEnLista(boolean [][]m,NodoTDA nodo) {
		return (m[nodo.getUbicacion().getX()][nodo.getUbicacion().getY()]);
	}

	private void asiganarPadreANodos(List<NodoTDA> listaNodos, NodoTDA padre) {
		for (NodoTDA nodo : listaNodos) {
			nodo.setPadre(padre);
		}
	}

	private int obtenerMininoListaNodos(List<NodoTDA> listaNodos, NodoTDA nodoActual) {
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
