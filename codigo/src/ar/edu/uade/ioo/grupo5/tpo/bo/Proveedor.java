package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;


/**
 Project : TP_IPOO_1
 File Name : Proveedor.java
 Date : 30/09/2009
 Author : 

*/




public class Proveedor {
	//TODO Checho: con el nombre no alcanza para identificarlo?
	private int id;
	private Vector<Producto> productos;
	private String nombre;
	
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Vector<Producto> getProductos() {
		return this.productos;
	}
}
