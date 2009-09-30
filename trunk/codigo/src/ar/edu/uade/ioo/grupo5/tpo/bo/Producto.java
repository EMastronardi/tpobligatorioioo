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
	public void descontarStock(double cantidad) {
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public boolean estaBajoPuntoPedido() {
		return this.puntoPedido > this.stock;
	}
	
	public double getPuntoReabastecimiento() {
		return this.puntoReabastecimiento;
	}
}
