package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class Principal extends LayoutBase {
	private JMenuBar barraMenu;
	private JMenu menuMesas;
	private JMenuItem menuAgregarPedido;
	private JMenuItem menuCerrarComanda;
	private JMenuItem menuInicializarComanda;
	private static Principal instancia = null;
	
	private JMenu menuSistema;
	private JMenuItem menuEmitirLiquidaciones;
	private JMenuItem menuEmitirOrdenesCompra;
	private JMenuItem menuModificarDatosSistema;

	private JMenu menuAltas;
	private JMenuItem menuAltaProductos;
	private JMenuItem menuAltaProveedores;
	private JMenuItem menuAltaConsumibles;
	private JMenuItem menuAltaProductosAConsumibles;

	private Principal() {
		super("Sistema RESTAURANT");
		initGUI();
		inicializarEventos();
	}

	public static Principal getInstance(){
		if(instancia == null){
			instancia = new Principal();
		}
		
		return instancia;
	}

	private void initGUI() {
		barraMenu = new JMenuBar();
		menuMesas = new JMenu("Mesas");
		menuAgregarPedido = new JMenuItem("Agregar pedido");
		menuCerrarComanda = new JMenuItem("Cerrar comanda");
		menuInicializarComanda = new JMenuItem("Inicializar comanda");
		
		menuMesas.add(menuAgregarPedido);
		menuMesas.add(menuCerrarComanda);
		menuMesas.add(menuInicializarComanda);
		
		menuSistema = new JMenu("Sistema");
		menuEmitirLiquidaciones = new JMenuItem("Emitir liquidaciones");
		menuModificarDatosSistema  = new JMenuItem("Modificar datos del sistema");
		menuEmitirOrdenesCompra = new JMenuItem("Emitir órdenes de compra");
		
		menuSistema.add(menuEmitirLiquidaciones);
		menuSistema.add(menuModificarDatosSistema);
		menuSistema.add(menuEmitirOrdenesCompra);

		menuAltas = new JMenu("Altas");
		menuAltaConsumibles = new JMenuItem("Alta Consumibles");
		menuAltaProductos = new JMenuItem("Alta Productos");
		menuAltaProveedores = new JMenuItem("Alta Proveedores");
		menuAltaProductosAConsumibles = new JMenuItem("Alta Productos a un Consumible");

		menuAltas.add(menuAltaConsumibles);
		menuAltas.add(menuAltaProductos);
		menuAltas.add(menuAltaProveedores);
		menuAltas.add(menuAltaProductosAConsumibles);

		barraMenu.add(menuMesas);
		barraMenu.add(menuSistema);
		barraMenu.add(menuAltas);

		setJMenuBar(barraMenu);
		inicializar();
		
		setSize(400, 300);
		
		
		
	}
	
	private void inicializarEventos() {
		menuInicializarComanda.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						AbrirMesa.getInstance().setVisible(true);
					}
					catch (Exception ex){
						handleException(ex);
					}
			    }
			}
        );	
		
		menuAgregarPedido.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
					AgregarPedido.getInstance().setVisible(true);
					
				} catch (Exception ex) {
					handleException(ex);
				}
				
			}
		});
		menuCerrarComanda.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
			try {
				hideMessage();
				CerrarVenta.getInstance().setVisible(true);
				
				
			} catch (Exception ex) {
				handleException(ex);
			}
				
			}
		});
		menuEmitirLiquidaciones.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
					EmitirLiquidaciones.getInstance().mostrar();
					
					
				} catch (Exception ex) {
					handleException(ex);
				}
					
			}
		});
		menuEmitirOrdenesCompra.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
					EmitirOrdenesDeCompra.getInstance().mostrar();

				} catch (Exception ex) {
					handleException(ex);
				}

			}
		});


		menuAltaConsumibles.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
					AltaConsumibles.getInstance().setVisible(true);

				} catch (Exception ex) {
					handleException(ex);
				}

			}
		});

		menuAltaProveedores.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
					AltaProveedores.getInstance().setVisible(true);

				} catch (Exception ex) {
					handleException(ex);
				}

			}
		});

		menuAltaProductos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
					AltaProductos.getInstance().mostrar();

				} catch (Exception ex) {
					handleException(ex);
				}

			}
		});
		
		menuAltaProductosAConsumibles.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
					AgregarProductosAConsumibles.getInstance().mostrar();

				} catch (Exception ex) {
					handleException(ex);
				}
					
			}
		});
		
		menuModificarDatosSistema.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
					ModificarDatosSistema.getInstance().mostrarDatos();
				
				} catch (Exception ex) {
					handleException(ex);
				}
					
			}
		});
	}
	
}
