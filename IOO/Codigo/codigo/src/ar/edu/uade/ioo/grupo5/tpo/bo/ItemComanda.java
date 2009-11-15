package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;

/**
Project : TP_IPOO_1
File Name : ItemComanda.java
Date : 30/09/2009
@author Grupo 5 

*/





public class ItemComanda {
	private Consumible consumible;
	private int cantidad;
	
	public ItemComanda(Consumible consumible, int cantidad) {
		this.consumible = consumible;
		this.cantidad = cantidad;
		
		Vector<ItemProducto> itemsProductos = this.consumible.getItemsProducto();
		
		for (ItemProducto itemProducto: itemsProductos) {
			itemProducto.descontarStock(cantidad);
		}
 
	}
	
	public Consumible getConsumible() {
		return this.consumible;
	}
	
	public double calcularSubtotal() {
		return consumible.getPrecio() * cantidad;
	}
	
	
}
