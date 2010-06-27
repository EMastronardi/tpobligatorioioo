package mapa;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import com.sun.corba.se.spi.oa.OADefault;

import grafico.PuntoTDA;

public class Nodo implements NodoTDA{
	private PuntoTDA ubicacion;
	private PuntoTDA destino;
	private NodoTDA padre;
	private int g = 0; 
	public Nodo(PuntoTDA ubicacion){
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String codigo() {
		return String.format("{%d - %d}", ubicacion.getX(), ubicacion.getY());
	}

	@Override
	public boolean esIgual(NodoTDA n) {
		return hashCode() == n.hashCode();
	}

	@Override
	public boolean equals(Object arg0) {
		
		return hashCode() == arg0.hashCode();
	}
	
	@Override
	public String toString() {
		
		return ubicacion.toString();
	}
	@Override
	public int hashCode() {
		return codigo().hashCode();
	}
	
	@Override
	public int f() {
		return this.g + this.h;
	}

	@Override
	public int getG() {
		return this.g;
	}

	private int h; 
	@Override
	public int getH() {
		return h;
	}

	@Override
	public NodoTDA getPadre() {
		if(padres.size() == 0)
			return null;
		NodoTDA mejor = padres.get(0);
		double total = mejor.calcularDistancia(this.ubicacion);
		
		for (NodoTDA nodo : padres) {
			if(nodo.calcularDistancia(this.ubicacion) < total){
				mejor = nodo;
				total = nodo.calcularDistancia(this.ubicacion);
			}
		}
		
		return mejor;
		
	}

	@Override
	public PuntoTDA getUbicacion() {
		return ubicacion;
	}

	@Override
	public void setG(int g) {
		this.g = g;
		
	}

	@Override
	public void setH(PuntoTDA p) {
		this.destino = p;
		
		int dX = Math.abs(ubicacion.getX() - destino.getX());
		int dY = Math.abs(ubicacion.getY() - destino.getY());
		
		this.h =  dX + dY;
		
	}

	@Override
	public void setPadre(NodoTDA padre) {
		this.padre = padre;
		this.padres.add(padre);
	}

	@Override
	public int compareTo(NodoTDA arg0) {
		if(equals(arg0))
			return 0;
		else if(this.f() < arg0.f()){
			return -1;
		}
		else{
			return 1;
		}
	}
	
	@Override
	public double calcularDistancia(PuntoTDA punto) {
		int x, y;
		
		
		x = Math.abs(this.getUbicacion().getX() -punto.getX());
		y = Math.abs(this.getUbicacion().getY() -punto.getY());
		
		double resultado = Math.sqrt((x * x) + (y *y));
		
		return resultado;
		
	}

	private List<NodoTDA> padres =  new Vector<NodoTDA>();
	
	

}
