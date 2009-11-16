package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;

/**
  Project : TP_IPOO_1
  File Name : OrdenCompra.java
  Date : 30/09/2009
  @author Grupo 5 

*/




public class OrdenCompra {
	private Vector<OrdenCompraItem> items;
	private String proveedor;
	
	public OrdenCompra(String proveedor) {
		this.proveedor = proveedor;
		items = new Vector<OrdenCompraItem>();
	}
	
	public void addItem(String producto, double cantidad) {
		items.add(new OrdenCompraItem(producto, cantidad));
	}
	
	public Vector<OrdenCompraItem> getItemsCompra() {
		return items;
	}
	
	public String getProveedor() {
		return this.proveedor;
	}
	
	
}
