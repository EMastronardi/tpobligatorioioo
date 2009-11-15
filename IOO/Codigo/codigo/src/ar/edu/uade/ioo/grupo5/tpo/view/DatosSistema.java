package ar.edu.uade.ioo.grupo5.tpo.view;



import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DatosSistema extends LayoutBase {
	private JTextField txtCantidadMozos;
	private JTextField txtCantidadMesas;
	private JTextField txtPorcentajeComision;
	private JButton btnCargarDatos;
    
	public DatosSistema() {
		
			super("Datos del sistema");
			
			txtCantidadMesas = new JTextField(4);
			txtCantidadMozos = new JTextField(4);
			txtPorcentajeComision = new JTextField(4);
			btnCargarDatos = new JButton("Cargar datos");
			
			addField("Cantidad de mozos", txtCantidadMozos);
			addField("Cantidad de mesas", txtCantidadMesas);
			addField("Porcentaje de comisión", txtPorcentajeComision);
			addButton(btnCargarDatos);
			
			inicializarEventos();
			inicializar();
			setSize(300, this.getHeight() +10);

			
			
			
	}

	private void inicializarEventos() {
		btnCargarDatos.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showMessage("Prueba mensaje de formulario");
				PopupControl.showMessage("PRUEBA MENSAJE POPUP");
				PopupControl.showError("PRUEBA MENSAJE POPUP ERROR");
				
			}
		});
		
	}
	
}
