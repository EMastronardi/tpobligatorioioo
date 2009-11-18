package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class AgregarProductosAConsumibles extends LayoutBase {

	private static AgregarProductosAConsumibles ventana;
	private JTextField txtConsumible; //TODO este habria que cambiarlo por un combo de consumibles
	private JTextField txtCantidad; //TODO este habria que cambiarlo por un combo de productos
	private JTextField txtProducto;
	private JButton btnAgregar;
	private JButton btnCancelar;
	
	
		
	public AgregarProductosAConsumibles(String titulo) {
		super(titulo);
		initGUI();
	}

	void initGUI(){
		btnAgregar = new JButton("Agregar");
		btnCancelar = new JButton("Cancelar");
				
		txtConsumible = new JTextField(4);
		txtProducto = new JTextField(4);
		txtCantidad = new JTextField(4);
		
		addField("Consumible",txtConsumible);
		addField("Producto",txtProducto);
		addField("Cantidad", txtCantidad);
		
		addButton(btnAgregar);
		addButton(btnCancelar);
		
		inicializarEventos();
		
		inicializar();
		setSize(400, getHeight() + 10);
	}
	
	void inicializarEventos(){
		btnAgregar.addActionListener(new ActionListener(){
			
	
			public void actionPerformed(ActionEvent e) {
				Restaurant.getInstance().agregarProductoAConsumible(txtConsumible.getText(),
						txtProducto.getText(),
						Double.parseDouble(txtCantidad.getText())
						);
				borrar();
				dispose();
			}
		});
		
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				borrar();
				dispose();
			}
		});

	}

	public static AgregarProductosAConsumibles getInstance() {
		if (ventana == null){
			ventana = new AgregarProductosAConsumibles("Alta Consumibles");
		}
		return ventana;
	}
	
	private void borrar(){
		txtCantidad.setText("");
		txtConsumible.setText("");
		txtProducto.setText("");
	}
	
}
