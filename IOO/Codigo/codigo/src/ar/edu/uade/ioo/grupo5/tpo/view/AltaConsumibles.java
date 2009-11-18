package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class AltaConsumibles extends LayoutBase {

	private static AltaConsumibles ventana;
	private JTextField txtDescripcion;
	private JButton btnAgregar;
	private JButton btnCancelar;
	private JTextField txtCodigo;
	private JTextField txtPrecio;
		
	public AltaConsumibles(String titulo) {
		super(titulo);
		initGUI();
	}

	void initGUI(){
		btnAgregar = new JButton("Agregar");
		btnCancelar = new JButton("Cancelar");
				
		txtDescripcion = new JTextField(10);
		txtCodigo = new JTextField(4);
		txtPrecio = new JTextField(4);
		
		addField("Nombre",txtDescripcion);
		addField("Código", txtCodigo);
		addField("Precio", txtPrecio);
		addButton(btnAgregar);
		addButton(btnCancelar);
		inicializarEventos();
		
		inicializar();
		setSize(400, getHeight() + 10);
	}
	
	void inicializarEventos(){
		btnAgregar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!validarDatos())
					return;
				try {
					Restaurant.getInstance().agregarConsumible(txtDescripcion.getText(),
							txtCodigo.getText(),
							Double.parseDouble(txtPrecio.getText()));
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
	
	private boolean validarDatos(){
		String message = "";
		
		if(txtPrecio.getText().equals("")) message = "El precio del consumible es un campo obligatorio";
		if(txtCodigo.getText().equals("")) message = "El código del consumible es un campo obligatorio";
		if(txtDescripcion.getText().equals("")) message = "El nombre del consumible es un campo obligatorio";
		
		showMessage(message);
		return message.equals("");
	}

	public static AltaConsumibles getInstance() {
		if (ventana == null){
			ventana = new AltaConsumibles("Alta Consumibles");
		}
		return ventana;
	}
	
	
	private void borrar(){
		txtCodigo.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
	}
}
