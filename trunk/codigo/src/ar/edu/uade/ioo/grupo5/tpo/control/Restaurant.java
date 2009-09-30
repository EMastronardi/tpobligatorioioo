package ar.edu.uade.ioo.grupo5.tpo.control;

import java.util.Vector;

import ar.edu.uade.ioo.grupo5.tpo.bo.*;
/**
 Project : TP_IPOO_1
 File Name : Restaurant.java
 Date : 30/09/2009
 Author : 

**/




public class Restaurant {
	private double comision;
	private Vector<Consumible> carta;
	private Vector<Producto> productos;
	private Vector<Mozo> mozos;
	private Vector<Mesa> mesas;
	private Vector<Comanda> comandasCerradas;
	private Vector<Proveedor> proveedores;
	private Vector<OrdenCompra> ordenesCompra;
	
	
	public String emitirLiquidaciones() {
		return null;
	}
	
	public void emitirOrdenesDeCompra() {
	
	}
	
	public String cerrarComanda(int NroMesa) {
		//No me acuerdo que devolviamos aca...
		return null;
	}
	
	public void agregarPedido(String codConsumible, int cantidad) {
	
	}
	
	public void nuevaComanda(int nroMesa) {
		
	}
	
	public void inicializar(int cantidadMozos, int cantidadMesas, double porcentajeComision) {
		for (int i=0;i<cantidadMozos;i++){
			Mozo unMozo = new Mozo();
			mozos.add(unMozo);
		}
		for (int i=0;i<cantidadMesas;i++){
			Mesa unaMesa = new Mesa();
			mesas.add(unaMesa);
		}
		this.comision = porcentajeComision;
	}
	
	private Vector<Comanda> buscarComandas(int nroMozo) {
		//Aca le pasamos el numero de Mozo o el mozo en si mismo?
		return null;
	}
	
	private double getPorcentajeComision() {
		return comision;
	}
	
	private Vector<Producto> buscarProductos(int idProveedor) {
		//Tenemos un buscarproductos que busca y devuelve varios?!
		
		//TODO Ojo que esto es solo para que devuelva algo
		
		return null;
	}
	
	private OrdenCompra buscarOrdenCompra(int idProveedor) {
		//TODO Ojo que esto es solo para que devuelva algo
		return null;
	}
	
	private Mesa buscarMesa(int nroMesa) {
		//TODO Ojo que esto es solo para que devuelva algo
		return null;
	}
	
	private Consumible buscarConsumible(String codConsumible) {
		//TODO Ojo que esto es solo para que devuelva algo
		return null;
	}
}
