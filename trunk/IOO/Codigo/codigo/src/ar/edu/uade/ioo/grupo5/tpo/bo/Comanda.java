package ar.edu.uade.ioo.grupo5.tpo.bo;


import java.util.Vector;

import ar.edu.uade.ioo.grupo5.tpo.common.ErrorException;
import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;

/**
Project : TP_IPOO_1
File Name : Comanda.java
Date : 30/09/2009
@author Grupo 5 
*/

public class Comanda {
	private Vector<ItemComanda> items;

	public Comanda() {
		items = new Vector<ItemComanda>();
	}
	

	public double calcularTotal() {
		double total=0;

		for (ItemComanda itemComanda : items) {
			total+= itemComanda.calcularSubtotal();
		}
		return total;
	}
	
	public void addItem(Consumible consumible, int cantidad) throws ErrorException,ValidationException {
		if(consumible == null)
			throw new ErrorException("El consumible no puede ser nulo");
		if(cantidad <= 0)
			throw new ValidationException("La cantidad de items del consumible debe ser mayor a cero");
		
		items.add(new ItemComanda(consumible, cantidad));
	}
	
	
}
