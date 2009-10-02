package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;

/**
  Project : TP_IPOO_1
  File Name : OrdenCompra.java
  Date : 30/09/2009
  @author Grupo 5 

*/




public class OrdenCompra {
	private Vector<Producto> productos;
	private Proveedor proveedor;
	
	public void addProducto(Producto producto) {
	//TODO Checho: este sería el add que "esta de mas"?
	}
	
	public Vector<Producto> getProductos() {
		return this.productos;
	}
	
	public Proveedor getProveedor() {
		return this.proveedor;
	}
}
