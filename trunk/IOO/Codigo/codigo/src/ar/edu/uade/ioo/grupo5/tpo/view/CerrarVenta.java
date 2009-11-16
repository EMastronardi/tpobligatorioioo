package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class CerrarVenta extends LayoutBase  {
	private JTextField txtNroMesa;
	private JButton btnCerrar;
    private JTextField txtTotal;
    private JTextField txtMesa;
    private JTextField txtMozo;
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
		txtTotal = new JTextField(4);
//		txtMesa = new JTextField (4);
//		txtMozo = new JTextField (4);
		
		
		addField("Nro. Mesa", txtNroMesa);
		addButton(btnCerrar);
		inicializarEventos();
		
		inicializar();
		setSize(400, this.getHeight() +10);
		
		
	}
	
	private boolean esValidoCerrar(){
		String message="";
		
		if (txtNroMesa.getText().equals("")) 
			message="Debe ingresar un numero de mesa";
		
		showMessage(message);
		return (message.equals(""));
	}

	private void inicializarEventos() {
		btnCerrar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if (!esValidoCerrar()) return;
				try {
					hideMessage();
					int nroMesa = Integer.parseInt(txtNroMesa.getText());
					String resultado =  Restaurant.getInstance().cerrarComanda(nroMesa);
									
					PopupControl.showMessage("Cierre mesa finalizado - " + resultado);
					
					
				} 
				catch (Exception ex) {
					handleException(ex);
				}
			}
		});
	}
}

