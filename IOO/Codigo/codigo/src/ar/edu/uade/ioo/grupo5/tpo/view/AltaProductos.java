package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.common.ErrorException;
import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class AltaProductos extends LayoutBase {

	private static AltaProductos ventana;
	private JTextField txtDescripcion;
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JTextField txtStock;
	private JTextField txtPtoPedido;
	private JTextField txtPtoReabastecimiento;
	private JComboBox cbxProveedores ;
	
	public AltaProductos(String titulo) {
		super(titulo);
		initGUI();
	}

	void initGUI(){
		
		btnAgregar = new JButton("Agregar");
		btnCancelar = new JButton("Cancelar");
		
		cargarControles();
		
		addButton(btnAgregar);
		addButton(btnCancelar);
		
		inicializarEventos();
		
		inicializar();
		setSize(500, this.getHeight() +10);
	}
	
	void inicializarEventos(){
		btnAgregar.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e) {
			if(!validarDatos())
				return;
			try {
					Restaurant.getInstance().agregarProducto(txtDescripcion.getText(),
					Double.parseDouble(txtStock.getText()),
					Double.parseDouble(txtPtoPedido.getText()),
					Double.parseDouble(txtPtoReabastecimiento.getText()),
					String.valueOf(cbxProveedores.getSelectedItem()));
					borrar();
					dispose();
			} catch (Exception ex) {
				handleException(ex);
			}
		}
		});
		
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				borrar();
				dispose();
			}
		});

	}

	public static AltaProductos getInstance() {
		if (ventana == null){
			ventana = new AltaProductos("Alta Productos");
		}
		return ventana;
	}
	private void borrar(){
		txtDescripcion.setText("");
		cbxProveedores.setSelectedIndex(0);
		txtPtoPedido.setText("");
		txtPtoReabastecimiento.setText("");
		txtStock.setText("");
	}
	public void mostrar() {
		hideMessage();
		cargarControles();
		setVisible(true);		
	}
	private boolean validarDatos(){
		String message = "";
		
		if(txtPtoReabastecimiento.getText().equals("")) message = "El punto de reabastecimiento es un campo obligatorio";
		if(txtPtoPedido.getText().equals("")) message = "El punto de pedido es un campo obligatorio";
		if(txtStock.getText().equals("")) message = "El stock es un campo obligatorio";
		if(txtDescripcion.getText().equals("")) message = "El nombre de producto es un campo obligatorio";
		
		showMessage(message);
		return message.equals("");
	}
	private void cargarControles() {
		reset();
		txtDescripcion = new JTextField(10);
		txtStock = new JTextField(4);
		txtPtoPedido = new JTextField(4);
		txtPtoReabastecimiento = new JTextField(4);
		String[] proveedores = Restaurant.getInstance().getProveedores();
		cbxProveedores = new JComboBox(proveedores);
		
		
				
		addField("Nombre",txtDescripcion);
		addField("Stock Inicial", txtStock);
		addField("Punto de pedido", txtPtoPedido);
		addField("Punto de Reabastecimiento", txtPtoReabastecimiento);
		addField("Proveedor",cbxProveedores);
		
		
		
	}
}
