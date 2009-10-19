package ar.edu.uade.ioo.grupo5.tpo.bo;

/**
  Project : TP_IPOO_1<br>
  File Name : Producto.java<br>
  Date 30/09/2009
  @author Grupo 5

*/




public class Producto {
	private String nombre;
	private double stock;
	private double puntoPedido;
	private double puntoReabastecimiento;
	private Proveedor proveedor;
	
	
	public Producto(String nombre, double stock, double puntoPedido,
			double puntoReabastecimiento, Proveedor proveedor) {
		
		this.nombre = nombre;
		this.stock = stock;
		this.puntoPedido = puntoPedido;
		this.puntoReabastecimiento = puntoReabastecimiento;
		this.proveedor = proveedor;
	}

	public void descontarStock(double cantidad) {
		stock-= cantidad;
		
		if(estaBajoPuntoPedido()){
			proveedor.addProductoAReponer(this);
		}
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	private boolean estaBajoPuntoPedido() {
		return this.puntoPedido > this.stock;
	}
	
	public double getPuntoReabastecimiento() {
		return this.puntoReabastecimiento;
	}

	

	public double getPuntoPedido() {
		return puntoPedido;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}
	
	
}
