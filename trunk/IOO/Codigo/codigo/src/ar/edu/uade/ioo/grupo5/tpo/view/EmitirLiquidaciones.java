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
import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class EmitirLiquidaciones extends LayoutBase {

	private JTable tabLiquidaciones;
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnMostrar;
	private static EmitirLiquidaciones ventana;
	
	public EmitirLiquidaciones(){
		super("Emitir Liquidaciones");
		initGUI();
	}

	public static EmitirLiquidaciones getInstance(){
		if (ventana == null){
			ventana = new EmitirLiquidaciones();
		}
		return ventana;
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
		
		try {
			String[] columnNames = {"Mozo",
			        "Comision",};
			
			Vector<LiquidacionViewData> liqs = Restaurant.getInstance().emitirLiquidaciones();
			Object[][] data = new Object[liqs.size()][2];
			for(int i=0;i<liqs.size();i++){
				data[i][0] = liqs.elementAt(i).getNroMozo();
				data[i][1] = liqs.elementAt(i).getComision();
			}
			tabLiquidaciones = new JTable(data, columnNames); 
			scrollPane = new JScrollPane(tabLiquidaciones);
			tabLiquidaciones.setFillsViewportHeight(true);
		} catch (Exception e) {
			handleException(e);
		}
	}
}
