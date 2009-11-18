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
	private JTextField txtPrecio;
	private JButton btnAgregar;
	private JButton btnCancelar;
	
		
	public AgregarProductosAConsumibles(String titulo) {
		super(titulo);
		initGUI();
	}

	void initGUI(){
		btnAgregar = new JButton();
		btnCancelar = new JButton();
				
		txtConsumible = new JTextField(4);
		txtCantidad = new JTextField(4);
		txtPrecio = new JTextField(4);
		
		addField("Consumible",txtConsumible);
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
						txtCantidad.getText(),
						Double.parseDouble(txtPrecio.getText()));
				
			}
		});
	}

	public static AgregarProductosAConsumibles getInstance() {
		if (ventana == null){
			ventana = new AgregarProductosAConsumibles("Alta Consumibles");
		}
		return ventana;
	}
	
}
