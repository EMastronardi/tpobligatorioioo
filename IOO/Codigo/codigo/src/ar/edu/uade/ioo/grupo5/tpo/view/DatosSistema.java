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
			initGUI();
			inicializarEventos();
	}

	private void initGUI() {
		
		
//		try {
//			
//			int cantidadMozos = 10;
//			int cantidadMesas = 20;
//			double porcentajeComision = 50.0;
//			
//			Restaurant.getInstance().inicializar(cantidadMozos, cantidadMesas, porcentajeComision);
//			
//			Principal.getInstance().setVisible(true);
//			setVisible(false);
//			dispose();
//			
//		} 
//		catch (Exception ex) {
//			handleException(ex);
//		}
		
		txtCantidadMesas = new JTextField(4);
		txtCantidadMozos = new JTextField(4);
		txtPorcentajeComision = new JTextField(4);
		btnCargarDatos = new JButton("Cargar datos");
		
		addField("Cantidad de mozos", txtCantidadMozos);
		addField("Cantidad de mesas", txtCantidadMesas);
		addField("Porcentaje de comisión", txtPorcentajeComision);
		addButton(btnCargarDatos);
		
		inicializar();
		setSize(400, this.getHeight() +10);
	}

	private void inicializarEventos() {
		btnCargarDatos.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
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
