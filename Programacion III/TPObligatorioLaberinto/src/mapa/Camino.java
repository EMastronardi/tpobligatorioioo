package mapa;

import grafico.Area;
import grafico.Punto;
import grafico.PuntoTDA;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
		// TODO Auto-generated method stub
		
		List<PuntoTDA> cmc = new ArrayList<PuntoTDA>();
		Set listaAbierta = new TreeSet<NodoTDA>();
		Set listaCerrada = new TreeSet<NodoTDA>();
		
		
		
		cmc.add(origen.getUbicacion());
		cmc.add(destino.getUbicacion());
		
		//Ventana.camino = new Camino(mapa);
	
		List<PuntoTDA> lista =  mapa.getAdyacentes(new Punto(5,5));
	
		
		cmc.addAll(lista);
		
		
		return cmc;
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
