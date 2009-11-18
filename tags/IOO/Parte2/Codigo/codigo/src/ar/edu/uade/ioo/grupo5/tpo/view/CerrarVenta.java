package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.bo.ComandaCerradaViewData;
import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class CerrarVenta extends LayoutBase  {
	private JTextField txtNroMesa;
	private JButton btnCerrar;

  
    private static CerrarVenta instancia = null;
	private  CerrarVenta() {
			super("Cerrar Comanda");
			initGUI();
	}
	
	public static CerrarVenta getInstance(){
		if(instancia == null){
			instancia = new CerrarVenta();
		}
		return instancia;
	}

	private void initGUI() {
		txtNroMesa = new JTextField(4);
		btnCerrar = new JButton ("Cerrar Mesa");
	
		addField("Mesa a Cerrar", txtNroMesa);
		addButton(btnCerrar);
		inicializarEventos();
		
		inicializar();
		setSize(400, this.getHeight() +10);
	}

	private void inicializarEventos() {
		
		btnCerrar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
					int nroMesa = Integer.parseInt(txtNroMesa.getText());
					ComandaCerradaViewData resultado =  Restaurant.getInstance().cerrarComanda(nroMesa);
									
					PopupControl.showMessage(String.format("Cierre mesa Nro. %d para el mozo Nro. %d por $%f", resultado.getNroMesa(), resultado.getNroMozo(), resultado.getTotal()) );
					setDefault();
					dispose();
				} 
				catch (Exception ex) {
					handleException(ex);
				}
			}
		});
	}
	
	private void setDefault() {
		txtNroMesa.setText("");
		
	}
}

