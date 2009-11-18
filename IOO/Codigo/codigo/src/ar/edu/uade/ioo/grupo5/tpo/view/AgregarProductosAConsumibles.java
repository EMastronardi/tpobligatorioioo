package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.common.ErrorException;
import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;
import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

public class AgregarProductosAConsumibles extends LayoutBase {

	private static AgregarProductosAConsumibles ventana;

	private JTextField txtCantidad; 
	private JComboBox cbxConsumible; 
	private JComboBox cbxProducto;

	private JButton btnAgregar;
	private JButton btnCancelar;
	private String[] codigosConsumibles;
		
	public AgregarProductosAConsumibles(String titulo) {
		super(titulo);
		initGUI();
	}

	void initGUI(){
		btnAgregar = new JButton("Agregar");
		btnCancelar = new JButton("Cancelar");

		cargarControles();
		
		
		
		addButton(btnAgregar);
		addButton(btnCancelar);
		
		inicializarEventos();
		
		inicializar();
		setSize(400, getHeight() + 10);
	}
	
	private void cargarControles() {
		
		reset();

		String consumibles[] = Restaurant.getInstance().getConsumibles();
		codigosConsumibles = Restaurant.getInstance().getCodigoConsumibles();
		cbxConsumible = new JComboBox(consumibles);
		String productos[] = Restaurant.getInstance().getProductos();
		cbxProducto = new JComboBox(productos);
		
		txtCantidad = new JTextField(4);

		addField("Consumible",cbxConsumible);
		addField("Producto",cbxProducto);
		addField("Cantidad", txtCantidad);
		
	}
	
	public void mostrar() {
		hideMessage();
		cargarControles();
		setVisible(true);		
	}

	void inicializarEventos(){
		btnAgregar.addActionListener(new ActionListener(){
			
	
			public void actionPerformed(ActionEvent e) {
				if (!validarDatos()) return;
				try {
					Restaurant.getInstance().agregarProductoAConsumible(codigosConsumibles[cbxConsumible.getSelectedIndex()],
							String.valueOf(cbxProducto.getSelectedItem()),
							Double.parseDouble(txtCantidad.getText())
							);

				
				} catch (Exception e1) {
					
					handleException(e1);
				}
				borrar();
				dispose();
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
		if(txtCantidad.getText().equals("")) message = "La cantidad es un campo obligatorio";
		
		showMessage(message);
		return message.equals("");
			
	}

	public static AgregarProductosAConsumibles getInstance() {
		if (ventana == null){
			ventana = new AgregarProductosAConsumibles("Alta Productos al Consumibles");
		}
		return ventana;
	}
	
	private void borrar(){
		txtCantidad.setText("");
//		txtConsumible.setText("");
//		txtProducto.setText("");
		cbxConsumible.setSelectedIndex(0);
		cbxProducto.setSelectedIndex(0);
	}
	
}
