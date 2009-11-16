package ar.edu.uade.ioo.grupo5.tpo.bo;

import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;

/**
 Project : TP_IPOO_1
 File Name : Mesa.java
 Date : 30/09/2009
 Author : 

*/

public class Mesa {
	private static int cantMesas = 0;
	private int nro;
	private Comanda comanda;
	public ESTADO_MESA estado;
	private Mozo mozo;
	
	private static int getNewId() {
		return ++Mesa.cantMesas;
	}
	
	public int GetNro() {
		return this.nro;
	}
	
	
	public Comanda getComanda() {
		return this.comanda;
	}
	
	public Mozo getMozo() {
		return this.mozo;
	}
	
	public void setEstado(ESTADO_MESA estado) {
		this.estado = estado;
	}
	
	public void setComanda(Comanda comanda) throws ValidationException{
		if(comanda == null)
			throw new ValidationException("La comanda asignada a la mesa es inválida");
		this.comanda = comanda;
	}
	
	
	public void asignarMozo(Mozo mozo)throws ValidationException {
		if(mozo == null)
			throw new ValidationException("El mozo asignado a la mesa es inválido");
		
		this.mozo = mozo;
	}
	
	public ESTADO_MESA getEstado() {
		return estado;
	}
	
	public Mesa() {
		this.nro = getNewId();
	}
}
