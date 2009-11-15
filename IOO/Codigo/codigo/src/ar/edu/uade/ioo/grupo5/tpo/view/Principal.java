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
		
		barraMenu.add(menuMesas);
		barraMenu.add(menuSistema);
		
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
;					}
			    }
			}
        );	
	}
	
}
