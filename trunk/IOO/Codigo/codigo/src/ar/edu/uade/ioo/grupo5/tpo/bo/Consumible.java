package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;

import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;

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
	
	
	
	public Consumible(String descripcion, String codigo, double precio) throws ValidationException {
		if(descripcion.equals("") || descripcion == null)
			throw new ValidationException("La descripci�n ingresada del consumible es inv�lida");
		
		if(codigo.equals("") || codigo == null)
			throw new ValidationException("El c�digo ingresado del consumible es inv�lido");
		
		if(precio < 0)
			throw new ValidationException("El precio ingresado del consumible es inv�lido");
		
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.precio = precio;
		this.itemsProducto = new Vector<ItemProducto>();
	}
	
	public void addItemProducto(Producto producto, double cantidad) throws ValidationException{
		if(producto == null)
			throw new ValidationException("El producto agregado al consumible es inv�lido");
		
		if(cantidad < 0)
			throw new ValidationException("La cantidad de productos del consumible ingresada para el item es inv�lida");
		
		itemsProducto.add(new ItemProducto(producto, cantidad));
	}

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
