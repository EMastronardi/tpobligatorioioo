package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;

/**
Project : TP_IPOO_1
File Name : Consumible.java
Date : 30/09/2009
@author Grupo 5 

*/

public class Consumible {
	private String descripcion;
	private String codigo;
	private double precio;
	private Vector<ItemProducto> itemsProducto;
	
	public double getPrecio() {
		return this.precio;
	}
	
	public Vector<ItemProducto> getItemsProducto() {
		return this.itemsProducto;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
}
