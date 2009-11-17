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
	
	public boolean tieneStockDisponible(int cantidad){
		return this.producto.tieneStockDisponible(cantidad * this.cantidad);
	}
	
	public Producto getProducto(){
		return producto;
	}

	public void descontarStock(int cantidad) {
		this.producto.descontarStock(this.cantidad * cantidad);
	}
}
