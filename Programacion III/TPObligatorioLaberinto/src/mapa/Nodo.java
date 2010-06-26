package mapa;

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
	public int hashCode() {
		return codigo().hashCode();
	}
	
	@Override
	public int f() {
		return this.padre.getG() + this.g;
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
		return padre;
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
		
	}

	@Override
	public int compareTo(NodoTDA arg0) {
		if(this.g < arg0.getG()){
			return -1;
		}
		else{
			return 1;
		}
	}

}
