package mapa;

import grafico.PuntoTDA;

public class Nodo implements NodoTDA{
	private PuntoTDA ubicacion;
	private NodoTDA padre;
	private float g;
	
	
	public Nodo(PuntoTDA ubicacion){
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String codigo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esIgual(NodoTDA n) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int f() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getG() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getH() {
		// TODO Auto-generated method stub
		return 0;
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
		
	}

	@Override
	public void setH(PuntoTDA p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPadre(NodoTDA padre) {
		this.padre = padre;
		
	}
	

	@Override
	public int compareTo(NodoTDA arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
