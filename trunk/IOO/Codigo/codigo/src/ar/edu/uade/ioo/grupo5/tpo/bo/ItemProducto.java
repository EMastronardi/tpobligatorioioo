package ar.edu.uade.ioo.grupo5.tpo.bo;

import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;

/**
 Project : TP_IPOO_1
 File Name : ItemProducto.java
 Date : 30/09/2009
 @author Grupo 5 

*/




public class ItemProducto {
	private double cantidad;
	private Producto producto;
	
	public ItemProducto(Producto producto,double cantidad) throws ValidationException {
		if(producto == null)
			throw new ValidationException("El producto agregado es inválido");
		
		if(cantidad < 0)
			throw new ValidationException("La cantidad de productos del item debe ser mayor a cero");
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public void descontarStock(int cantidad) throws ValidationException {
		if(cantidad <= 0)
			throw new ValidationException("La cantidad para descontar stock debe ser mayor a cero");
			
		this.producto.descontarStock(this.cantidad * cantidad);
	}
}
