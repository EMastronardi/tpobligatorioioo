package ar.edu.uade.ioo.grupo5.tpo.control;

import java.util.Iterator;
import java.util.Vector;
import java.text.*;

import ar.edu.uade.ioo.grupo5.tpo.bo.*;

/**
 * Project : TP_IPOO_1 File Name : Restaurant.java Date : 30/09/2009 Author :
 **/

public class Restaurant {
	private double comision;
	private Vector<Consumible> carta;
	private Vector<Producto> productos;
	private Vector<Mozo> mozos;
	private Vector<Mesa> mesas;
	private Vector<Proveedor> proveedores;
	
	public Restaurant() {
		carta = new Vector<Consumible>();
		productos = new Vector<Producto>();
		mozos = new Vector<Mozo>();
		mesas = new Vector<Mesa>();
		proveedores = new Vector<Proveedor>();
	}
	public void inicializar(int cantidadMozos, int cantidadMesas,
			double porcentajeComision) {
		
		setComision(porcentajeComision);
		
		if(cantidadMesas < cantidadMozos)
			return;
		
		agregarMozos(cantidadMozos);

		agregarMesas(cantidadMesas);	
		
		reasignarMesas();
	}
	
	
	private void reasignarMesas(){
		int i=0;
		
		for (Mesa mesa : mesas) {
			
			if(i > mozos.size()){
				i = 0;
			}
			
			mesa.asignarMozo(mozos.elementAt(i));
			i++;
		}
	}
	
	
	
	
	public Vector<LiquidacionViewData> emitirLiquidaciones() {
		double total;
		Vector<LiquidacionViewData> liquidaciones = new Vector<LiquidacionViewData>();
		
		for (Mozo mozo : mozos) {
			total = 0;
			for (Comanda comanda : mozo.getComandasCerradas()) {
				total +=comanda.calcularTotal();
			}
			
			liquidaciones.add(new LiquidacionViewData(total * this.comision, mozo.getNro()));
		}

		return liquidaciones;
	}

	public Vector<OrdenCompra> emitirOrdenesDeCompra() {
		Vector<OrdenCompra> ordenesDeCompra = new Vector<OrdenCompra>();
		
		for (Proveedor proveedor : proveedores) {
			OrdenCompra orden = new OrdenCompra();
			
			for (Producto producto : proveedor.getProductos()) {
				orden.addItem(producto.getNombre(), producto.getPuntoReabastecimiento());
			}
			
			ordenesDeCompra.add(orden);
		}

		return ordenesDeCompra;
	}

	public String cerrarComanda(int NroMesa) {
		double total;
		NumberFormat nm = NumberFormat.getNumberInstance();

		Mesa unaMesa = buscarMesa(NroMesa);
		Comanda unaComanda = unaMesa.getComanda();
				
		unaMesa.setEstado(ESTADO_MESA.LIBRE);
		
		total = unaComanda.calcularTotal();
		
		Mozo unMozo = unaMesa.getMozo();
		
		unMozo.addComandaCerrada(unaComanda);
		
		return nm.format(total) + unaMesa.GetNro()
				+ unaMesa.getMozo().getNro();
	}

	public void agregarPedido(String codConsumible, int cantidad, int nroMesa) {
		Consumible unConsumible = buscarConsumible(codConsumible);

		if (unConsumible != null) {
			ItemComanda unItemComanda = new ItemComanda(unConsumible, cantidad);
			
			
			Mesa unaMesa = buscarMesa(nroMesa);

			if (unaMesa != null) {
				Comanda comanda = unaMesa.getComanda();
				comanda.addItem(unItemComanda);

			}
		}
	}
	
	

	public void nuevaComanda(int nroMesa) {
		Mesa unaMesa = buscarMesa(nroMesa);

		if (unaMesa != null) {
			unaMesa.setEstado(ESTADO_MESA.OCUPADA);

			Comanda unaComanda = new Comanda();
			unaMesa.setComanda(unaComanda);

		}

	}

	private Mesa buscarMesa(int nroMesa) {
		for (Mesa mesa : mesas) {
			if (mesa.GetNro() == nroMesa)
				return mesa;
		}
		
		return null;
	}

	private Consumible buscarConsumible(String codConsumible) {
		for (Consumible consumible : carta) {
			if (consumible.getCodigo().equalsIgnoreCase(codConsumible))
				return consumible;
		}
		return null;
	}
	
	private Vector<Mesa> getMesasLibres(){
		Vector<Mesa> mesasLibres =new Vector<Mesa>();
		
		for (Mesa mesa : mesas) {
			if(mesa.getEstado().equals(ESTADO_MESA.LIBRE)){
				mesasLibres.add(mesa);
			}
		}
		
		return mesasLibres;
	
	}
	
	
	private void agregarMesas(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			mesas.add(new Mesa());
		}
	}
	
	private void quitarMesas(int cantidad) {
		Vector<Mesa> mesasLibres = getMesasLibres();
		
		for (Mesa mesaLibre : mesasLibres) {
			if(cantidad == 0)
				break;
			
			mesas.remove(mesaLibre);
			cantidad--;
		}
		
	}
	
	private void quitarMozos(int cantidad) {
		
		if(cantidad < mozos.size())
			return;
		
		for (int i = 0; i < cantidad; i++) {
			mozos.remove(mozos.elementAt(i));
		}
		
	}
	
	private void agregarMozos(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			mozos.add(new Mozo());
		}
		
	}
	
	public void modificarCantidadMesas(int cantidadMesas){
		
		if(mozos.size() > cantidadMesas)
			return;
	
		int diferenciaMesas = cantidadMesas - mesas.size();
		
		if(diferenciaMesas < 0){
			quitarMesas(diferenciaMesas * (-1));
		}
		else if(diferenciaMesas >0){
			agregarMesas(diferenciaMesas);
		}
		
		reasignarMesas();
		
	}
	
	public void modificarCantidadMozos(int cantidadMozos){
		
		if(cantidadMozos > mesas.size())
			return;
	
		
		int diferenciaMozos = cantidadMozos - mozos.size();
		
		
		if(diferenciaMozos < 0){
			quitarMozos(diferenciaMozos * (-1));
		}
		else if(diferenciaMozos >0){
			agregarMozos(diferenciaMozos);
		}
		
		reasignarMesas();
		
	}
	
	
	public void setComision(double comision){
		this.comision = comision;
	}
}
