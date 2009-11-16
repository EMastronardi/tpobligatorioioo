package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.bo.LiquidacionViewData;
import ar.edu.uade.ioo.grupo5.tpo.bo.OrdenCompra;
import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class EmitirOrdenesDeCompra extends LayoutBase {

	private JTable tabOrdenesCompra;
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnMostrar;
	private static EmitirOrdenesDeCompra instancia;
	
	public EmitirOrdenesDeCompra(){
		super("Emitir Ordenes de Compra");
		initGUI();
	}

	public static EmitirOrdenesDeCompra getInstance(){
		if (instancia == null){
			instancia = new EmitirOrdenesDeCompra();
		}
		return instancia;
	}

	private void initGUI() {


		cargarDatos();
		
				
		btnAceptar = new JButton("Aceptar");
		
		addField("", scrollPane);
		
		addButton(btnAceptar);
		
		
		inicializarEventos();
		
		inicializar();
		setSize(400, this.getHeight() +10);
	}
	
	private void inicializarEventos() {
		
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();		
			}
		});
	}

	public void mostar() {

		cargarDatos();
		setVisible(true);		
	}
	
	private void cargarDatos(){
		
		String[] columnNames = {"Proveedor",
                "Producto","Cantidad",};
		
		Vector<OrdenCompra> OCs = Restaurant.getInstance().emitirOrdenesDeCompra();
		Object[][] data = new Object[OCs.size()][3];
		for(int i=0;i<OCs.size();i++){
			
			for(int j=0;i<OCs.elementAt(i).getItemsCompra().size();i++){
				data[i][0] = OCs.elementAt(i).getProveedor();
				data[i][1] = OCs.elementAt(i).getItemsCompra().elementAt(j).getProducto();
				data[i][2] = OCs.elementAt(i).getItemsCompra().elementAt(j).getCantidad();
			}
			
		}
		tabOrdenesCompra = new JTable(data, columnNames); 
		scrollPane = new JScrollPane(tabOrdenesCompra);
		tabOrdenesCompra.setFillsViewportHeight(true);
	}
}
