package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import ar.edu.uade.ioo.grupo5.tpo.bo.Consumible;
import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;

/**
 *
 * @author emolinero
 */
public class AgregarPedido extends LayoutBase {
	private static AgregarPedido instancia;
	private JLabel lblPrecio;
	private JLabel lblPrecioConsumible;
	private JLabel lblCantidadDescripcion;
	private JTextField txtCantidad;
	private JTextField txtNroMesa;
	private JLabel lblNroMesaDescripcion;
	private JButton btnAgregarPedido;
	private JButton btnSalir;
	private JComboBox cbxCarta;
	private String[] codigosConsumibles;
    private AgregarPedido() {
    	super("Agregar pedido a la comanda");
    	try {
	        initGUI();
	        inicializarEventos();
    	}
	    catch (Exception ex) {
			handleException(ex);
		}
    }
    
    private void initGUI() {
    	
    	String[] carta = Restaurant.getInstance().getConsumibles();
    	codigosConsumibles = Restaurant.getInstance().getCodigoConsumibles();
    	
    	
    	cbxCarta = new JComboBox(carta);
    	
    	
		lblPrecio = new JLabel();
		txtNroMesa = new JTextField(4);
		btnAgregarPedido = new JButton("Agregar pedido");
		lblNroMesaDescripcion = new JLabel("Nro. Mesa");
		lblPrecioConsumible = new JLabel("Precio");
		txtCantidad = new JTextField(4);
		lblCantidadDescripcion = new JLabel("Cantidad");
		btnSalir = new JButton("Salir");
		
		addField("Carta", cbxCarta);
		
		addField(lblPrecioConsumible, lblPrecio);
		addField(lblNroMesaDescripcion, txtNroMesa);
		addField(lblCantidadDescripcion, txtCantidad);
		addButton(btnAgregarPedido);
		addButton(btnSalir);
		
		
		setSize(400, 300);
		cargarPrecioConsumible();
	}
    
    private void setDefault(){
    	
    	txtNroMesa.setText("");
    	lblPrecio.setText("");
    	cbxCarta.setSelectedIndex(0);
    	cargarPrecioConsumible();
    	hideMessage();
    	
    }
    
    
    
    private boolean esValidoAgregarPedido(){
		String message= "";
		
		if (txtCantidad.getText().equals("")) message = "La cantidad es un campo obligatorio";
		if (txtNroMesa.getText().equals("")) message = "El Nro. de mesa es un campo obligatorio";
				
		showMessage(message);
		return message.equals("") ;
	}
    
    private void clearTextFields(){
    	txtCantidad.setText("");
    	
    	txtNroMesa.setText("");
    }
    
    private void inicializarEventos() {
	
		btnSalir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setDefault();
				dispose();
				clearTextFields();
			}
		});
		
		cbxCarta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargarPrecioConsumible();
			}

			
		});
		btnAgregarPedido.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!esValidoAgregarPedido())
					return;
				try {
					
					int nroMesa = Integer.parseInt(txtNroMesa.getText());
					int cantidad = Integer.parseInt(txtCantidad.getText());
					int indexConsumible = cbxCarta.getSelectedIndex();
					String codConsumible = codigosConsumibles[indexConsumible];
					
					Restaurant.getInstance().agregarPedido(codConsumible, cantidad, nroMesa);
					
					setDefault();
					txtCantidad.setText("");
				} catch (Exception ex) {
					handleException(ex);
				}
				
			}
		});
		
	}
    
    private void cargarPrecioConsumible() {
		try {
			int indexConsumible = cbxCarta.getSelectedIndex();
			String codConsumible = codigosConsumibles[indexConsumible];
			double precio = Restaurant.getInstance().getConsumiblePrecio(codConsumible);
			lblPrecio.setText("$ " + precio);
		} catch (Exception ex) {
			handleException(ex);
		}
	}
    
	public static AgregarPedido getInstance(){
    	if(instancia == null){
    		instancia = new AgregarPedido();
    	}
    	
    	return instancia;
    }
	
	

}
