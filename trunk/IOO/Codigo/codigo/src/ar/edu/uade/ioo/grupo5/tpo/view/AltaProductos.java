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
	//private JTextField txtProveedor; //TODO este habria que cambiarlo por un combo de proveedores
	private JComboBox cbxProveedores ;
	
	public AltaProductos(String titulo) {
		super(titulo);
		initGUI();
	}

	void initGUI(){
		
		txtDescripcion = new JTextField(10);
		txtStock = new JTextField(4);
		txtPtoPedido = new JTextField(4);
		txtPtoReabastecimiento = new JTextField(4);
		String[] proveedores = Restaurant.getInstance().getProveedores();
		cbxProveedores = new JComboBox(proveedores);
		
		btnAgregar = new JButton("Agregar");
		btnCancelar = new JButton("Cancelar");
				
		addField("Nombre",txtDescripcion);
		addField("Stock Inicial", txtStock);
		addField("Punto de pedido", txtPtoPedido);
		addField("Punto de Reabastecimiento", txtPtoReabastecimiento);
		addField("Proveedor",cbxProveedores);
		
		addButton(btnAgregar);
		addButton(btnCancelar);
		
		inicializarEventos();
		
		inicializar();
		setSize(400, this.getHeight() +10);
	}
	
	void inicializarEventos(){
		btnAgregar.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent e) {
				
				try {
					Restaurant.getInstance().agregarProducto(txtDescripcion.getText(),
							Double.parseDouble(txtStock.getText()),
							Double.parseDouble(txtPtoPedido.getText()),
							Double.parseDouble(txtPtoReabastecimiento.getText()),
							String.valueOf(cbxProveedores.getSelectedItem()));
							borrar();
							dispose();
				} catch (Exception e1) {
					handleException(e1);
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
		txtProveedor.setText("");
		txtPtoPedido.setText("");
		txtPtoReabastecimiento.setText("");
		txtStock.setText("");
	}
}
