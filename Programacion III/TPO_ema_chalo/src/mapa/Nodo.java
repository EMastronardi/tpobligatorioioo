package mapa;

import grafico.PuntoTDA;

public class Nodo implements NodoTDA{
	private PuntoTDA ubicacion;
	private NodoTDA padre;
	private int densidad;
	private int g;
	private float heuristica;
	//private float g;
	
	public Nodo(PuntoTDA ubicacion, float h, int d){
		this.ubicacion = ubicacion;
		this.densidad = d;
		this.heuristica = h;
	}
	
	public Nodo(PuntoTDA ubicacion){
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String codigo() {
		// TODO Auto-generated method stub
		return "NodoActual = ("+this.getUbicacion().getX()+","+this.getUbicacion().getY()+")";
	}

	@Override
	public boolean esIgual(NodoTDA n) {
		// TODO Auto-generated method stub
		boolean valorRetorno;
		
		if (this.ubicacion.getX() == n.getUbicacion().getX()){
			if (this.ubicacion.getY() == n.getUbicacion().getY()){
				valorRetorno = true;
			}
			else {
				valorRetorno = false;
			}
		}
		else {
			valorRetorno = false;
		}
		return valorRetorno;
	}

	@Override
	public float f() {
		
		return this.getG() + this.getH();
	}

	@Override
	public int getG() {
		return g;
	}

	@Override
	public float getH() {
		
		return this.heuristica;
	}

	@Override
	public NodoTDA getPadre() {
		
		return this.padre;
	}

	@Override
	public PuntoTDA getUbicacion() {
		return ubicacion;
	}

	@Override
	public void setG(int g) {
		// TODO Auto-generated method stub
		this.densidad = g;
	}

	@Override
	public void setH(float h) {
		// TODO Auto-generated method stub
		this.heuristica = h;
	}

	@Override
	public void setPadre(NodoTDA padre) {
		this.padre = padre;
		//this.g = this.densidad + 1 + this.padre.getG();
		this.g = this.densidad + this.padre.getG();
	}
	
	public void setDensidad(int d){
		this.densidad = d;
	}
	
	public int getDensidad(){
		return this.densidad;
	}
	
	public void setHeuristica(float h){
		this.heuristica = h;
	}
	

	@Override
	public int compareTo(NodoTDA arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
