package ar.edu.uade.ioo.grupo5.tpo.control;

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
	
	public void inicializar(int cantidadMozos, int cantidadMesas,
			double porcentajeComision) {
		for (int i = 0; i < cantidadMozos; i++) {
			Mozo unMozo = new Mozo();
			mozos.add(unMozo);
		}

		while (cantidadMesas != 0) {
			for (Mozo mozo : mozos) {
				Mesa unaMesa = new Mesa();
				unaMesa.asignarMozo(mozo);

				mesas.add(unaMesa);

				cantidadMesas--;

				if (cantidadMesas == 0)
					break;
			}
		}

		this.comision = porcentajeComision;
		
		this.productos = new Vector<Producto>();
		this.mesas = new Vector<Mesa>();
		this.mozos = new Vector<Mozo>();
		
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
}
