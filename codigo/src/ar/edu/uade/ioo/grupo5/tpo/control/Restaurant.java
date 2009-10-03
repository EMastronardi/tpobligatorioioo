package ar.edu.uade.ioo.grupo5.tpo.control;

import java.util.Vector;
import java.text.*;

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
		for (int i= 0 ; i< mozos.size();i++){
			double subtotal = 0;
			Mozo unMozo = mozos.elementAt(i);
			for(int j=0; j< comandasCerradas.size();j++){
				if(unMozo.getNroMozo()==comandasCerradas.elementAt(j).getMozo().getNroMozo())
				subtotal +=comandasCerradas.elementAt(j).calcularTotal();
				
			}
			System.out.println("El mozo Nro: "+ unMozo.getNroMozo()+ "tiene una comision de: "+ (subtotal*comision)+"\n");
		}
		return null;
	}
	
	public void emitirOrdenesDeCompra() {
		for(int i=0; i<proveedores.size();i++){
			Proveedor unProveedor =proveedores.elementAt(i);
			if(unProveedor.getProductos().size()>0){
				OrdenCompra unaOC =new OrdenCompra(unProveedor.getProductos(),unProveedor);
				/*ya que estamos, meti todo adentro del constructor, si guardamos
				 *  un vector por proveedor, con los items a comprar, asi no
				 *  recorremos productos ahora. Esto seria mas bien generar OC's
				 *  */
			}
		}
	
	}
	
	public String cerrarComanda(int NroMesa) {
		double total;
		NumberFormat nm = NumberFormat.getNumberInstance();
		
		Mesa unaMesa = buscarMesa(NroMesa);
		Comanda unaComanda = unaMesa.getComanda();
		comandasCerradas.add(unaComanda);
		//setear estado a mesa, preguntar EmaT
		total = unaComanda.calcularTotal();
		
		return nm.format(total) + unaMesa.getId() + unaMesa.getMozo().getNroMozo();
	}
	
	public void agregarPedido(String codConsumible, int cantidad, int nroMesa) {
				Consumible unConsumible= buscarConsumible(codConsumible);
				ItemComanda unItemComanda = new ItemComanda(unConsumible, cantidad);
				Mesa unaMesa = buscarMesa(nroMesa);
				unaMesa.getComanda().addItem(unItemComanda);
				
	}
	
	public void nuevaComanda(int nroMesa) {
		Mesa unaMesa = buscarMesa(nroMesa);
		//setear el estado a la mesa, preguntar a emaT por lo de enumeration	
		Mozo unMozo = unaMesa.getMozo();
		Comanda unaComanda = new Comanda(unaMesa, unMozo);
		unaMesa.setComanda(unaComanda);
		
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
		/*Mepa que este metodo lo puso emaT para obtener todas las comandas de un mozo
		 * pero yo hice el liquidar comisiones a lo cabeza.. podemos cambiarlo, va a
		 * quedar mejor.. Por ahi el liquidar esta "sobrecargado"...
		 * */
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
		// no se en que parte lo usamos pero bueh..
		for(int i=0 ; i<ordenesCompra.size();i++){
			if(ordenesCompra.elementAt(i).getProveedor().getId()== idProveedor){
				return ordenesCompra.elementAt(i);
			}
		}
		
		return null;
	}
	
	private Mesa buscarMesa(int nroMesa) {
		for(int i=0;i<mesas.size();i++){
			if(mesas.elementAt(i).getId()==nroMesa){
				return mesas.elementAt(i);
			}
		}
		return null;
	}
	
	private Consumible buscarConsumible(String codConsumible) {
		for(int i=0;i<carta.size();i++){
			if(carta.elementAt(i).getCodigo()==codConsumible){
				return carta.elementAt(i);
			}
		}
		return null;
	}
}
