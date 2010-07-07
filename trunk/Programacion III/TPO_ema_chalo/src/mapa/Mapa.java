package mapa;

import grafico.Punto;
import grafico.PuntoTDA;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Mapa extends MapaBase{

	public List<PuntoTDA> getAdyacentes(PuntoTDA ubicacion) {
List<PuntoTDA> adyacentes = new Vector<PuntoTDA>();
		
		for (int i = ubicacion.getX()-1; i <= ubicacion.getX()+1; i++) {
			for (int j =  ubicacion.getY()-1; j <= ubicacion.getY()+1; j++) {
				if(i< 0 || j < 0)
					continue;
				
				Punto punto = new Punto(i,j);
				
				if(!punto.esIgual(ubicacion) && transitable(punto)){
					
					if(punto.getX() != ubicacion.getX()
							&& punto.getY() != ubicacion.getY()){
					
						Punto diagA = new Punto(punto.getX(), ubicacion.getY());
						Punto diagB = new Punto(ubicacion.getX(), punto.getY());
						
						if(transitable(diagA) && transitable(diagB)){
							adyacentes.add(punto);
						}
						
					}
					else
						adyacentes.add(punto);
					
				}
			}
		}
		
		return adyacentes;
	

	}

	private boolean transitable(PuntoTDA punto){
		return  grilla[punto.getX()][punto.getY()] < 3;
	
	}

}
