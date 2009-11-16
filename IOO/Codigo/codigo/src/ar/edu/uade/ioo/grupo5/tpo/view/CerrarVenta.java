package ar.edu.uade.ioo.grupo5.tpo.view;

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
		
		
		addField("Mesa a Cerrar: ", txtNroMesa);
		addButton(btnCerrar);
		inicializarEventos();
		
		inicializar();
		setSize(400, this.getHeight() +10);
		
		
	}
	
	private boolean esValidoCerrar(){
		
		if (txtNroMesa.getText().equals("")) return false;
		
		return true;
	}

	private void inicializarEventos() {
		btnCerrar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if (!esValidoCerrar()) {
					showMessage("Debe ingresar un numero de mesa");
					return;
				}
				try {
					hideMessage();
					int nroMesa = Integer.parseInt(txtNroMesa.getText());
					txtTotal.setText(Restaurant.getInstance().cerrarComanda(nroMesa));
					txtTotal.setVisible(true);
					dispose();
					
				} 
				catch (Exception ex) {
					handleException(ex);
				}
			}
		});
	}
}

