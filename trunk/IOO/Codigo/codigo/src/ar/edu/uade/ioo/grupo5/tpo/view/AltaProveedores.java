package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class AltaProveedores extends LayoutBase {

	private static AltaProveedores ventana;
	private JTextField txtNombre;
	private JButton btnAgregar;
	private JButton btnCancelar;
	
	public AltaProveedores(String titulo) {
		super(titulo);
		initGUI();
	}

	void initGUI(){
		btnAgregar = new JButton("Agregar");
		btnCancelar = new JButton("Cancelar");
		
		JTextField txtNombre = new JTextField(10);
		
		addField("Nombre",txtNombre);
		
		addButton(btnAgregar);
		addButton(btnCancelar);
		inicializarEventos();
		
		inicializar();
		setSize(400, getHeight() + 10);
	}
	
	void inicializarEventos(){
		btnAgregar.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent e) {
				try{
					Restaurant.getInstance().agregarProveedor(txtNombre.getText());
				}
				catch (Exception ex){
					handleException(ex);
				}
			}
		});
	}

	public static AltaProveedores getInstance() {
		if (ventana == null){
			ventana = new AltaProveedores("Alta Proveedores");
		}
		return ventana;
	}
	
}
