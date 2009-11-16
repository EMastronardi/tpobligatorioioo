package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;

import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;


/**
 Project : TP_IPOO_1
 File Name : Proveedor.java
 Date : 30/09/2009
 Author : 

*/




public class Proveedor {
	private Vector<Producto> productosAReponer;
	private String nombre;
	
	public Proveedor(String nombre) throws ValidationException {
		if(nombre == null || nombre.equals(""))
			throw new ValidationException("El nombre del proveedor ingresado es inválido");
		
		this.nombre = nombre;
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
