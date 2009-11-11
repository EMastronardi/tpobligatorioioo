package ar.edu.uade.ioo.grupo5.tpo.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InicializacionSistema extends LayoutBase {
	private JLabel lblCantidadMozos;
	private JLabel lblCantidadMesas;
	private JLabel lblPorcentajeComision;
	private JTextField txtCantidadMozos;
	private JTextField txtCantidadMesas;
	private JTextField txtPorcentajeComision;
	private JButton btnInicializar;
	
	public InicializacionSistema() {
		initGUI();
	}

	private void initGUI() {
		try 
		{
			getContentPane().setLayout(null);
			
			lblCantidadMozos = new JLabel("Cantidad de mozos:");
			lblCantidadMesas= new JLabel("Cantidad de mesas:");
			lblPorcentajeComision = new JLabel("Porcentaje de comisión:");
			txtCantidadMozos= new JTextField();
			txtCantidadMesas = new JTextField();
			txtPorcentajeComision = new JTextField();
			btnInicializar = new JButton("Iniciar sistema");
			getContentPane().add(lblCantidadMozos);
			getContentPane().add(lblCantidadMesas);
			getContentPane().add(lblPorcentajeComision);
			getContentPane().add(txtCantidadMozos);
			getContentPane().add(txtCantidadMesas);
			getContentPane().add(txtPorcentajeComision);
		
			getContentPane().add(btnInicializar);
						
			formatLayout();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

	
	
	
}
