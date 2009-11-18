package ar.edu.uade.ioo.grupo5.tpo.control;

import java.util.Iterator;
import java.util.Vector;
import java.text.*;

import ar.edu.uade.ioo.grupo5.tpo.bo.*;
import ar.edu.uade.ioo.grupo5.tpo.common.ErrorException;
import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;

/**
 * Project : TP_IPOO_1 File Name : Restaurant.java Date : 30/09/2009 Author :
 **/

public class Restaurant {
	public static Restaurant getInstance(){
		if(instancia == null){
			instancia= new Restaurant();
		}
		return instancia;
	}
	private double comision;
	private Vector<Consumible> carta;
	private Vector<Producto> productos;
	private Vector<Mozo> mozos;
	private Vector<Mesa> mesas;
	private Vector<Proveedor> proveedores;
	
	private static Restaurant instancia = null;
	
	private Restaurant() {
		carta = new Vector<Consumible>();
		productos = new Vector<Producto>();
		mozos = new Vector<Mozo>();
		mesas = new Vector<Mesa>();
		proveedores = new Vector<Proveedor>();
	}
	
	public void agregarConsumible(String descripcion, String codigo, double precio){
		Consumible cons = new Consumible(descripcion, codigo, precio);
		carta.add(cons);
	}

	private void agregarMesas(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			mesas.add(new Mesa());
		}
	}

	private void agregarMozos(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			mozos.add(new Mozo());
		}
		
	}
	
	public void agregarPedido(String codConsumible, int cantidad, int nroMesa) throws ErrorException, ValidationException{
		
		Mesa unaMesa = buscarMesa(nroMesa);
		Consumible unConsumible = buscarConsumible(codConsumible);
		
		Comanda comanda = unaMesa.getComanda();
		
		if(comanda != null){
			comanda.addItem(unConsumible, cantidad);
		}
		else{
			throw new ValidationException("La mesa no tiene asociada una comanda");
		}
	}

	public void agregarProducto(String nombre, double stock, double puntoPedido,
			double puntoReabastecimiento, String proveedor) throws ErrorException {
		Proveedor prov = buscarProveedor(proveedor);
		Producto prod = new Producto(nombre, stock, puntoPedido, puntoReabastecimiento, prov);
		productos.add(prod);
	}

	public void agregarProveedor(String nombre) throws ValidationException{
		Proveedor prov = new Proveedor(nombre);
		proveedores.add(prov);
	}

	public Consumible buscarConsumible(String codConsumible) throws ErrorException {
		for (Consumible consumible : carta) {
			if (consumible.getCodigo().equalsIgnoreCase(codConsumible))
				return consumible;
		}
		
		throw new ErrorException("El consumible solicitado no existe");
	}
	
	private Mesa buscarMesa(int nroMesa) {
		for (Mesa mesa : mesas) {
			if (mesa.GetNro() == nroMesa)
				return mesa;
		}
		
		return null;
	}

	private Proveedor buscarProveedor(String nombre) throws ErrorException {
		for (Proveedor proveedor : proveedores) {
			if (proveedor.getNombre().equalsIgnoreCase(nombre))
				return proveedor;
		}
		
		throw new ErrorException("El proveedor solicitado no existe");
	}

	private void cargarDatosPrueba() throws ValidationException {
		Proveedor verduleria = new Proveedor("Verduleria");
		Proveedor carniceria = new Proveedor("Carniceria");
		Proveedor cocacola = new Proveedor("Coca-Cola");
		
		proveedores.add(verduleria);
		proveedores.add(carniceria);
		proveedores.add(cocacola);
		
		Producto papa=new Producto("Papa", 10, 5, 5, verduleria);
		Producto batata=new Producto("Batata", 10, 5, 5, verduleria);
		Producto lomo=new Producto("Lomo", 20, 5, 5, verduleria);
		Producto cuadril=new Producto("Cuadril", 30, 10, 5, verduleria);
		Producto coca = new Producto("Coca-Cola", 100, 70, 50, cocacola);
		Producto sprite = new Producto("Sprite", 100, 70, 50, cocacola);
		
		productos.add(papa);
		productos.add(batata);
		productos.add(lomo);
		productos.add(cuadril);
		productos.add(coca);
		productos.add(sprite);
		
		Consumible lopa = new Consumible("Lomo con papas","lopa",100.00);
		lopa.addItemProducto(papa, 0.5);
		lopa.addItemProducto(lomo, .2);
		Consumible loba = new Consumible("Lomo con papas","loba",100.00);
		loba.addItemProducto(batata, 0.5);
		loba.addItemProducto(lomo, .2);
		Consumible cuaba = new Consumible("Cuadril con batatas","cuaba",80.00);
		cuaba.addItemProducto(batata, 0.5);
		cuaba.addItemProducto(cuadril, 0.2);
		Consumible cocabot = new Consumible("Coca-Cola","coca",10.00);
		cocabot.addItemProducto(coca, 1);
		Consumible spritebot = new Consumible("Sprite","sprite",10.00);
		spritebot.addItemProducto(sprite, 1);
		
		carta.add(lopa);
		carta.add(loba);
		carta.add(cuaba);
		carta.add(cocabot);
		carta.add(spritebot);
	}
	
	public ComandaCerradaViewData cerrarComanda(int NroMesa) throws ErrorException, ValidationException {
		double total;
		NumberFormat nm = NumberFormat.getNumberInstance();

		Mesa unaMesa = buscarMesa(NroMesa);
		Comanda unaComanda = unaMesa.getComanda();
				
		if(unaComanda == null)
			throw new ErrorException("La mesa no tiene asociada una comanda");
		
		unaMesa.setEstado(ESTADO_MESA.LIBRE);
		
		total = unaComanda.calcularTotal();
		
		unaMesa.setComanda(null);
		Mozo unMozo = unaMesa.getMozo();
		
		unMozo.addComandaCerrada(unaComanda);
	
		return new ComandaCerradaViewData(total, unaMesa.GetNro(), unaMesa.getMozo().getNro());
	}
	
	
	public Vector<LiquidacionViewData> emitirLiquidaciones() {
		double total;
		Vector<LiquidacionViewData> liquidaciones = new Vector<LiquidacionViewData>();
		
		for (Mozo mozo : mozos) {
			total = 0;
			for (Comanda comanda : mozo.getComandasCerradas()) {
				total +=comanda.calcularTotal();
			}
			
			liquidaciones.add(new LiquidacionViewData(total * this.comision / 100, mozo.getNro()));
		}

		return liquidaciones;
	}
	
	public Vector<OrdenCompra> emitirOrdenesDeCompra() {
		Vector<OrdenCompra> ordenesDeCompra = new Vector<OrdenCompra>();
		
		for (Proveedor proveedor : proveedores) {
			OrdenCompra orden = new OrdenCompra(proveedor.getNombre());
			
			for (Producto producto : proveedor.getProductos()) {
				orden.addItem(producto.getNombre(), producto.getPuntoReabastecimiento());
			}
			
			if(orden.getItemsCompra().size() > 0)
				ordenesDeCompra.add(orden);
		}

		return ordenesDeCompra;
	}
	
	public int getCantidadMesas() {
		return mesas.size();
	}
	
	public int getCantidadMozos() {
		return mozos.size();
	}
	
	public double getComision() {
		return comision;
	}
	
	private Vector<Mesa> getMesasLibres(){
		Vector<Mesa> mesasLibres = new Vector<Mesa>();
		
		for (Mesa mesa : mesas) {
			if(mesa.getEstado().equals(ESTADO_MESA.LIBRE)){
				mesasLibres.add(mesa);
			}
		}
		
		return mesasLibres;
	}
	
	public void inicializar(int cantidadMozos, int cantidadMesas,
			double porcentajeComision) throws ValidationException {
		
		setComision(porcentajeComision);
		
		if(cantidadMesas <= 0)
			throw new ValidationException("La cantidad de mesas debe ser mayor a cero");
		
		if(cantidadMozos <= 0)
			throw new ValidationException("La cantidad de mozos debe ser mayor a cero");
		
		setComision(porcentajeComision);
		
		if(cantidadMesas < cantidadMozos)
			throw new ValidationException("La cantidad de mesas no puede ser menor a la cantidad de mozos");
		
			
		agregarMozos(cantidadMozos);

		agregarMesas(cantidadMesas);	
		
		reasignarMesas();
		
		cargarDatosPrueba();
	}

	public void modificarCantidadMesas(int cantidadMesas) throws ValidationException{
		
		if(mozos.size() > cantidadMesas)
			throw new ValidationException("La cantidad de mesas no puede ser menor a la cantidad de mozos");
	
		int diferenciaMesas = cantidadMesas - mesas.size();
		
		if(diferenciaMesas < 0){
			quitarMesas(diferenciaMesas * (-1));
		}
		else if(diferenciaMesas >0){
			agregarMesas(diferenciaMesas);
		}
		
		reasignarMesas();
		
	}

	public void modificarCantidadMozos(int cantidadMozos) throws ValidationException{
		
		if(cantidadMozos > this.mesas.size())
			throw new ValidationException("La cantidad de mesas no puede ser menor a la cantidad de mozos");
	
		int diferenciaMozos = cantidadMozos - mozos.size();
		
		if(diferenciaMozos < 0){
			quitarMozos(diferenciaMozos * (-1));
		}
		else if(diferenciaMozos >0){
			agregarMozos(diferenciaMozos);
		}
		
		reasignarMesas();
	}

	public void nuevaComanda(int nroMesa) throws ErrorException{
		Mesa unaMesa = buscarMesa(nroMesa);

		if (unaMesa != null) {
			unaMesa.setEstado(ESTADO_MESA.OCUPADA);

			Comanda unaComanda = new Comanda();
			unaMesa.setComanda(unaComanda);

		}
		else {
			throw new ErrorException("La mesa ingresada no existe");
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

	private void reasignarMesas() throws ValidationException{
		int i=0;
		
		for (Mesa mesa : mesas) {
			
			if(i >= mozos.size()){
				i = 0;
			}
			
			mesa.asignarMozo(mozos.elementAt(i));
			i++;
		}
	}

	public void setComision(double comision)throws ValidationException{
		if(comision <= 100 && comision >= 0){
			this.comision = comision;
		}
		else{
			throw new ValidationException("El porcentaje está fuera de rango (Debe ser de 0-100)");
		}
	}

	public void agregarProductoAConsumible(String codigoConsumible, String producto,
			double cantidad) throws ValidationException, ErrorException {
		Consumible cons = buscarConsumible(codigoConsumible);
		Producto prod = buscarProducto(producto);

		cons.addItemProducto(prod, cantidad);
		
	}
	
	private Producto buscarProducto(String prod) throws ErrorException {
		
		for (Producto producto : productos) {
			if (producto.getNombre().equalsIgnoreCase(prod))
				return producto;
		}
		
		throw new ErrorException("El consumible solicitado no existe");
		
	}

	public String[] getProveedores(){
		String[] lista = new String[this.proveedores.size()];
		
		for (int i = 0; i < this.proveedores.size(); i++) {
			String string = this.proveedores.elementAt(i).getNombre();
			lista[i] = string;
		}
		
		return lista;
	}

	public String[] getConsumibles() {
		String lista[] = new String[this.carta.size()];
		
		for (int i=0; i<this.carta.size();i++){
			String consu = this.carta.elementAt(i).getDescripcion();
			lista[i] = consu;
		}
		return lista;
	}
	
	public String[] getCodigoConsumibles() {
		String lista[] = new String[this.carta.size()];
		
		for (int i=0; i<this.carta.size();i++){
			String codigo = this.carta.elementAt(i).getCodigo();
			lista[i] = codigo;
		}
		return lista;
	}
	
	public String[] getProductos() {
		String lista[] = new String[this.productos.size()];
		
		for (int i=0; i<this.productos.size();i++){
			String prod = this.productos.elementAt(i).getNombre();
			lista[i] = prod;
		}
		return lista;
	}
}
