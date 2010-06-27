package mapa;

import grafico.PuntoTDA;

import java.util.ArrayList;
import java.util.List;

import vista.Ventana;

public class Camino implements CaminoTDA{
	private MapaTDA mapa;
	private NodoTDA origen;
	private NodoTDA destino;
	private float raizDeDos = (float) Math.sqrt(2);
	
	public Camino(MapaTDA mapa){
		this.mapa = mapa;
	}
	
	public List<PuntoTDA> buscarCamino() {
        List<PuntoTDA> cmc;
        List<NodoTDA> listaAbierta = new ArrayList<NodoTDA>();
        List<NodoTDA> listaCerrada = new ArrayList<NodoTDA>();
        
        listaCerrada.add(this.origen);
        cmc = buscarCaminoRecursivo(listaAbierta,listaCerrada,this.origen);
        Ventana.camino = new Camino(mapa);
        
        return cmc;
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
		this.destino = destino;
	}

	@Override
	public void setDestino(PuntoTDA punto) {
		this.destino = new Nodo(punto,this);
	}

	@Override
	public void setOrigen(NodoTDA origen) {
		// TODO Auto-generated method stub
		this.origen = origen;
	}

	@Override
	public void setOrigen(PuntoTDA punto) {
		this.origen = new Nodo(punto,this);
		// por las dudas...
		this.origen.setPadre(null);
	}
	
	
	public int getDensidadNodo (NodoTDA n){
		return mapa.getDensidad(n.getUbicacion());
	}
	
	public float getHeuristicaNodo (NodoTDA n){
		float heuristica = 0;
		int cordX = n.getUbicacion().getX();
		int cordY = n.getUbicacion().getY();
		int destX = destino.getUbicacion().getX();
		int destY = destino.getUbicacion().getY();
		while (!(cordX == destX && cordY == destY)){

			if(cordX > destX){
				if(cordY > destY){
					heuristica += raizDeDos;
					cordX--;
					cordY--;
				}else if(cordY == destY){
					heuristica +=1;
					cordX--;
				}else if(cordY < destY){
					heuristica += raizDeDos;
					cordX--;
					cordY++;
				}
			}else if(cordX == destX){
				if(cordY > destY){
					heuristica +=1;
					cordY--;
				}else if(cordY < destY){
					heuristica +=1;
					cordY++;
				}
			}else if (cordX < destX){
				if(cordY >destY){
					heuristica += raizDeDos;
					cordX++;
					cordY--;
				}else if(cordY == destY){
					heuristica +=1;
					cordX++;
				}else if(cordY < destY){
					heuristica += raizDeDos;
					cordX++;
					cordY++;
				}
				
			}
		}
		return heuristica;
		
	}
	
	public List<PuntoTDA> buscarCaminoRecursivo(List<NodoTDA> listaAbierta,List<NodoTDA> listaCerrada,NodoTDA nodoActual){
		List<NodoTDA> adyacentes;
		NodoTDA nodoSiguiente;
		int indiceNodoSiguiente;	
	// caso base
		if (nodoActual.esIgual(this.destino)){
			return generarCaminoConListaDePuntos(listaCerrada);
		}
	// proceso principal
		// agregar adyacentes a lista abierta
		adyacentes = convertirAListaDeNodos(this.mapa.getAdyacentes(nodoActual.getUbicacion()));
		asiganarPadreANodos(adyacentes,nodoActual);
		concatenarListasNodos(listaAbierta,adyacentes);
		// evaluo cual nodo es el siguiente en el camino
		indiceNodoSiguiente = obtenerMininoListaNodos(listaAbierta);
		nodoSiguiente = listaAbierta.get(indiceNodoSiguiente);
		listaCerrada.add(nodoSiguiente);
		nodoActual = nodoSiguiente;
		listaAbierta.remove(indiceNodoSiguiente);
		// sigo con la recursion
		return buscarCaminoRecursivo(listaAbierta,listaCerrada,nodoActual);
	}

	private List<PuntoTDA> generarCaminoConListaDePuntos(List<NodoTDA> listaCerrada){
		List<PuntoTDA> aux = new ArrayList<PuntoTDA>();
		List<PuntoTDA> valorRetorno = new ArrayList<PuntoTDA>();
		NodoTDA nodoActual;
		
		// agrego a la lista el punto destino
		nodoActual = listaCerrada.get(listaCerrada.size()-1);
		aux.add(nodoActual.getUbicacion());
		// armo la lista de puntos que establecen el camino minimo
		while (nodoActual.getPadre() != null) {
			nodoActual = nodoActual.getPadre();
			aux.add(nodoActual.getUbicacion());
		}
		// invierto la lista por prolijidad
		for (int i = aux.size() -1; i >= 0 ; i--){
			valorRetorno.add(aux.get(i));
		}
		
		return valorRetorno;
	}

	/*private List<PuntoTDA> convertirAListaDePuntos(List<NodoTDA> lista){
		List<PuntoTDA> listaPuntos = new ArrayList<PuntoTDA>();
		
		for (NodoTDA n: lista){
				PuntoTDA punto = new Punto(n.getUbicacion().getX(),n.getUbicacion().getY());
				listaPuntos.add(punto);
		}
		
		return listaPuntos;
	}*/

	private List<NodoTDA> convertirAListaDeNodos(List<PuntoTDA> lista){
		List<NodoTDA> listaNodos = new ArrayList<NodoTDA>();
		
		for (PuntoTDA p: lista){
				NodoTDA nodo = new Nodo(p,this);
				listaNodos.add(nodo);
		}
		
		return listaNodos;
	}

	private void concatenarListasNodos(List<NodoTDA> listaDestino,List<NodoTDA> listaFuente){
		for (NodoTDA nodo: listaFuente){
			listaDestino.add(nodo);
		}
	}

	private void asiganarPadreANodos(List<NodoTDA> listaNodos,NodoTDA padre){
		for (NodoTDA nodo: listaNodos){
			nodo.setPadre(padre);
		}
	}

	private int obtenerMininoListaNodos(List<NodoTDA> listaNodos){
		int valorRetorno = -1;
		float fMinima = Float.MAX_VALUE;
		int tamanioLista = listaNodos.size();
		NodoTDA nodo;
		NodoTDA nodoMinimo = new Nodo(null,this);
		
		for ( int i = 0; i < tamanioLista; i++){
			nodo = listaNodos.get(i);
			if (nodo.f() < fMinima){
				fMinima = nodo.f();
				nodoMinimo = nodo;
				valorRetorno = i;
			}
			else if (nodo.f() == fMinima) {
				if (nodo.getH() < nodoMinimo.getH()){
					fMinima = nodo.f();
					valorRetorno = i;
					nodoMinimo = nodo;
				}
			}
		}
		
		return valorRetorno;
	}


}
