package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;


/**
 Project : TP_IPOO_1
 File Name : Proveedor.java
 Date : 30/09/2009
 Author : 

*/




public class Proveedor {
	private Vector<Producto> productosAReponer;
	private String nombre;
	
	public Proveedor() {
		productosAReponer = new Vector<Producto>();
	}
			
	public String getNombre() {
		return this.nombre;
	}
	
	public Vector<Producto> getProductos() {
		return this.productosAReponer;
	}
	
	public void addProductoAReponer(Producto producto){
		productosAReponer.add(producto);
	}
	
}
