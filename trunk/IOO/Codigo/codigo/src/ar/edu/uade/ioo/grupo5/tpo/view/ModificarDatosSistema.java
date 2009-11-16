package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;
import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class ModificarDatosSistema extends LayoutBase {
	private JTextField txtCantidadMozos;
	private JTextField txtCantidadMesas;
	private JTextField txtPorcentajeComision;
	private JButton btnModificarDatos;
	private static ModificarDatosSistema instancia;
    
	public ModificarDatosSistema(){
			super("Modificar Datos del sistema");
			initGUI();
			inicializarEventos();
	}
	
	public static ModificarDatosSistema getInstance(){
		if (instancia == null){
			instancia = new ModificarDatosSistema();
		}
		return instancia;
	}
	
	
	private void clearTextFields(){
		txtCantidadMesas.setText("");
		txtCantidadMozos.setText("");
		txtPorcentajeComision.setText("");
	}
	
	
	public void mostrarDatos(){
		clearTextFields();
		txtCantidadMesas.setText(String.valueOf(Restaurant.getInstance().getCantidadMesas()));
		txtCantidadMozos.setText(String.valueOf(Restaurant.getInstance().getCantidadMozos()));
		txtPorcentajeComision.setText(String.valueOf(Restaurant.getInstance().getComision()));
		this.setVisible(true);
	}

	private void initGUI() {
		
		txtCantidadMesas = new JTextField(4);
		txtCantidadMozos = new JTextField(4);
		txtPorcentajeComision = new JTextField(4);
		btnModificarDatos = new JButton("Modificar datos");
		
		addField("Cantidad de mozos", txtCantidadMozos);
		addField("Cantidad de mesas", txtCantidadMesas);
		addField("Porcentaje de comisión", txtPorcentajeComision);
		addButton(btnModificarDatos);
		
		inicializar();
		setSize(400, this.getHeight() +10);
	}
	
	private boolean esValidoModificarDatos(){
		String message = "";
		
		if (txtCantidadMesas.getText().equals("")) 
			message="Debe ingresar una cantidad de mesas";
		else if (txtCantidadMozos.getText().equals("")) 
			message="Debe ingresar una cantidad de mozos";
		else if (txtPorcentajeComision.getText().equals(""))
			message="Debe ingresar un porcentaje de comision";
		
		showMessage(message);
		return (message.equals(""));
	}

	private void inicializarEventos(){
		btnModificarDatos.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!esValidoModificarDatos()) return;
				try {
					hideMessage();
					int cantidadMozos = Integer.parseInt(txtCantidadMozos.getText());
					int cantidadMesas = Integer.parseInt(txtCantidadMesas.getText());
					double porcentajeComision = Double.parseDouble(txtPorcentajeComision.getText());			
					
					Restaurant.getInstance().modificarCantidadMesas(cantidadMesas);
					Restaurant.getInstance().modificarCantidadMozos(cantidadMozos);
					Restaurant.getInstance().setComision(porcentajeComision);
					
					dispose();	
				} 
				catch (Exception ex) {
					handleException(ex);
				}
			}
		});
		
	}

}
