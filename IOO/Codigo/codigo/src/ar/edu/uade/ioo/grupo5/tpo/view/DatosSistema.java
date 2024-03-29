package ar.edu.uade.ioo.grupo5.tpo.view;



import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ar.edu.uade.ioo.grupo5.tpo.common.ErrorException;
import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;
import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class DatosSistema extends LayoutBase {
	private JTextField txtCantidadMozos;
	private JTextField txtCantidadMesas;
	private JTextField txtPorcentajeComision;
	private JButton btnCargarDatos;
    
	public DatosSistema() {
			super("Datos del sistema");
			try {
				initGUI();
				inicializarEventos();
			} catch (Exception ex) {
				handleException(ex);
			}
			
	}

	private boolean esValidoModificarDatos(){
		String message = "";
		
		if (txtCantidadMesas.getText().equals("")) message = "La cantidad de mesas es un campo obligatorio";
		if (txtPorcentajeComision.getText().equals("")) message = "La comisi�n es un campo obligatorio";
		if (txtCantidadMozos.getText().equals("")) message = "La cantidad de mozos es un campo obligatorio";
		
		
		
		showMessage(message);
		
		return message.equals("");
	}

	private void initGUI() {

		txtCantidadMesas = new JTextField(4);
		txtCantidadMozos = new JTextField(4);
		txtPorcentajeComision = new JTextField(4);
		btnCargarDatos = new JButton("Cargar datos");
		
		addField("Cantidad de mozos", txtCantidadMozos);
		addField("Cantidad de mesas", txtCantidadMesas);
		addField("Porcentaje de comisi�n", txtPorcentajeComision);
		addButton(btnCargarDatos);
		
		inicializar();
		setSize(400, this.getHeight() +10);
	}

	private void inicializarEventos() {
		btnCargarDatos.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				hideMessage();
				
				if (!esValidoModificarDatos())
					return;
				
				try {
					
					int cantidadMozos = Integer.parseInt(txtCantidadMozos.getText());
					int cantidadMesas = Integer.parseInt(txtCantidadMesas.getText());
					double porcentajeComision = Double.parseDouble(txtPorcentajeComision.getText());
					
					Restaurant.getInstance().inicializar(cantidadMozos, cantidadMesas, porcentajeComision);
					
					Principal.getInstance().setVisible(true);
					dispose();
					
				} 
				catch (Exception ex) {
					handleException(ex);
				}
			}
		});
		
	}
	
}
