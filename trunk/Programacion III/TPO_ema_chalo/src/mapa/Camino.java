package mapa;

import grafico.Punto;
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
        List<PuntoTDA> cmc = new ArrayList<PuntoTDA>();
        cmc.add(origen.getUbicacion());
        cmc.add(destino.getUbicacion());
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
		
	}

	@Override
	public void setDestino(PuntoTDA punto) {
		this.destino = new Nodo(punto);
	}

	@Override
	public void setOrigen(NodoTDA origen) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setOrigen(PuntoTDA punto) {
		this.origen = new Nodo(punto);
	}
	
	private int getDensidadNodo (PuntoTDA p){
		return mapa.getDensidad(p);
	}
	private float getHeuristicaNodo (PuntoTDA p){
		float heuristica = 0;
		int cordX = p.getX();
		int cordY = p.getY();
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

}
