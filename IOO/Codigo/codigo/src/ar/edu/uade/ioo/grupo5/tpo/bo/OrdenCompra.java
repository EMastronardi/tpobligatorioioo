package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;

import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;

/**
  Project : TP_IPOO_1
  File Name : OrdenCompra.java
  Date : 30/09/2009
  @author Grupo 5 

*/




public class OrdenCompra {
	private Vector<OrdenCompraItem> items;
	private String proveedor;
	
	public OrdenCompra() {
		items = new Vector<OrdenCompraItem>();
	}
	
	public void addItem(String producto, double cantidad)throws ValidationException {
		if(producto == null && producto.equals(""))
			throw new ValidationException("El código de producto agregado a la orden de compra es inválido");
		
		if(cantidad <= 0)
			throw new ValidationException("La cantidad de productos agregada a la orden de compra es inválida");
		
		items.add(new OrdenCompraItem(producto, cantidad));
	}
	
	public Vector<OrdenCompraItem> getItemsCompra() {
		return items;
	}
	
	public String getProveedor() {
		return this.proveedor;
	}
	
	
}
