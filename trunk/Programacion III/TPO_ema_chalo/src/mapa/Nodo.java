package mapa;

import grafico.PuntoTDA;

public class Nodo implements NodoTDA{
	private PuntoTDA ubicacion;
	private NodoTDA padre;
	private int densidad;
	private int g = 0;
	private float heuristica;
	//private float g;
	
	public Nodo(PuntoTDA ubicacion, float h, int d){
		this.ubicacion = ubicacion;
		this.densidad = d;
		this.heuristica = h;
	}
	
	public Nodo(PuntoTDA ubicacion){
		this.ubicacion = ubicacion;
		this.densidad = Integer.MAX_VALUE;
		this.heuristica = Integer.MAX_VALUE;
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
		if (this.densidad == Integer.MAX_VALUE){
			return Integer.MAX_VALUE;
		}
		// caso esperado
		return g + 1;
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
		if (this.padre != null){
			if (this.densidad != Integer.MAX_VALUE){
				this.g = this.densidad + this.padre.getG();
			}	
		}
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
	

	public int compareTo(NodoTDA nodo) {
		int valorRetorno = -1;
		if (this.f() < nodo.f()) {
			valorRetorno = -1;
		} 
		
		else if (nodo.f() == this.f()) {

			if ( this.getH()< nodo.getH()) {
			
				valorRetorno = -1;
			}else{
				
			}
		}else{
			valorRetorno = 1;
		}
	return valorRetorno;
	}
}
