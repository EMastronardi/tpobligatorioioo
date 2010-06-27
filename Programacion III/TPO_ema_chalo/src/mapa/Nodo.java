package mapa;

import grafico.PuntoTDA;

public class Nodo implements NodoTDA{
	private PuntoTDA ubicacion;
	private NodoTDA padre;
	private CaminoTDA camino;
	//private float g;
	
	public Nodo(PuntoTDA ubicacion, CaminoTDA camino){
		this.ubicacion = ubicacion;
		this.camino = camino;
	}
	
	@Override
	public String codigo() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return this.getG() + this.getH();
	}

	@Override
	public int getG() {
		// TODO Auto-generated method stub
		int valorRetorno = this.camino.getDensidadNodo(this);
		
		if (this.padre != null){
			valorRetorno = valorRetorno + this.padre.getG();
		}
		return valorRetorno;
	}

	@Override
	public float getH() {
		// TODO Auto-generated method stub
		return this.camino.getHeuristicaNodo(this);
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
