package mapa;

import grafico.Area;
import grafico.PuntoTDA;

import java.util.List;

public class Mapa implements MapaTDA{

	private int[][] grilla;
	
	public Mapa(int[][] grilla) {
		this.grilla = grilla;
	}
	
	@Override
	public List<PuntoTDA> getAdyacentes(PuntoTDA ubicacion) {
		// TODO Auto-generated method stub
		return null;
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
