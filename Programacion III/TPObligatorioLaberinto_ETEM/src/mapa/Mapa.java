package mapa;

import grafico.Area;
import grafico.Punto;
import grafico.PuntoTDA;

import java.util.List;
import java.util.Vector;

public class Mapa implements MapaTDA{

	private int[][] grilla;
	
	public Mapa(int[][] grilla) {
		this.grilla = grilla;
	}
	
	@Override
	public List<PuntoTDA> getAdyacentes(PuntoTDA ubicacion) {
		List<PuntoTDA> adyacentes = new Vector<PuntoTDA>();
		
		for (int i = ubicacion.getX()-1; i <= ubicacion.getX()+1; i++) {
			for (int j =  ubicacion.getY()-1; j <= ubicacion.getY()+1; j++) {
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
		return puntoValido(punto) && grilla[punto.getX()][punto.getY()] < 3;
	}
	@Override
	public int getDensidad(int x, int y) {
		return this.grilla[x][y];
	}

	@Override
	public int getDensidad(PuntoTDA p) {
		return getDensidad(p.getX(), p.getY());
	}

	@Override
	public int[][] getGrilla() {
		return this.grilla;
	}

	@Override
	public boolean puntoValido(PuntoTDA p) {
		return ((p.getX() >= 0 && p.getX() < this.grilla[0].length) &&
					(p.getY() >= 0 && p.getY() < this.grilla[0].length));
	}

	@Override
	public void setArea(Area area) {
		// No nos importa uachin pelado boton
	}

	

}
