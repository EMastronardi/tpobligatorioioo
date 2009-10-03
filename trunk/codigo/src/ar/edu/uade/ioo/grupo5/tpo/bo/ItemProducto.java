package ar.edu.uade.ioo.grupo5.tpo.bo;

/**
 Project : TP_IPOO_1
 File Name : ItemProducto.java
 Date : 30/09/2009
 @author Grupo 5 

*/




public class ItemProducto {
	private double cantidad;
	private Producto producto;
	
	
	public ItemProducto(Producto producto,double cantidad) {
		
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public double getCantidad() {
		return this.cantidad;
	}
	
	public Producto getProducto() {
		return this.producto;
	}
}
