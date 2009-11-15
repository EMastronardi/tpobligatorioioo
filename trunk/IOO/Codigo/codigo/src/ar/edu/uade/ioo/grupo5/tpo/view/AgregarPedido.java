package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	private JTextField txtCodigoConsumible; 
	private JButton btnBuscarConsumible;
	
	private JLabel lblConsumible;
	private JLabel lblConsumibleDescripcion;
	private JLabel lblCantidadDescripcion;
	private JTextField txtCantidad;
	private JTextField txtNroMesa;
	private JLabel lblNroMesaDescripcion;
	private JButton btnAgregarPedido;
	private JButton btnSalir;
	
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
    	// Buscador de consumible
		txtCodigoConsumible = new JTextField(10);
		btnBuscarConsumible = new JButton("Buscar consumible");
		lblConsumible = new JLabel();
		txtNroMesa = new JTextField(3);
		btnAgregarPedido = new JButton("Agregar pedido");
		lblNroMesaDescripcion = new JLabel("Nro. Mesa");
		lblConsumibleDescripcion = new JLabel("Descripción");
		txtCantidad = new JTextField();
		lblCantidadDescripcion = new JLabel("Cantidad");
		btnSalir = new JButton("Salir");
		
		
		addField("Cod. consumible", txtCodigoConsumible);
		addButton(btnBuscarConsumible);
		addField(lblConsumibleDescripcion, lblConsumible);
		addField(lblNroMesaDescripcion, txtNroMesa);
		addField(lblCantidadDescripcion, txtCantidad);
		addButton(btnAgregarPedido);
		addButton(btnSalir);
		mostrarResultado(false);
		
		setSize(400, 300);
	}
    
    private void setDefault(){
    	txtCodigoConsumible.setText("");
    	txtNroMesa.setText("");
    	lblConsumible.setText("");
    	mostrarResultado(false);
    	
    }
    private void inicializarEventos() {
	
		btnBuscarConsumible.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String codigo = txtCodigoConsumible.getText().trim();
					
					Consumible consumible =  Restaurant.getInstance().buscarConsumible(codigo);
				
					lblConsumible.setText(String.format("%s (%s) | Precio:%f", consumible.getDescripcion(), consumible.getCodigo(), consumible.getPrecio()));
					mostrarResultado(true);
				} catch (Exception ex) {
					handleException(ex);
				}
				
			}
		});
		
		btnSalir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setDefault();
				dispose();
				
			}
		});
		
		btnAgregarPedido.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int nroMesa = Integer.parseInt(txtNroMesa.getText());
					int cantidad = Integer.parseInt(txtCantidad.getText());
					String codConsumible = txtCodigoConsumible.getText().trim();
					
					Restaurant.getInstance().agregarPedido(codConsumible, cantidad, nroMesa);
					
					setDefault();
				} catch (Exception ex) {
					handleException(ex);
				}
				
			}
		});
		
	}
    
    private void mostrarResultado(boolean mostrar){
    	btnAgregarPedido.setVisible(mostrar);
    	txtNroMesa.setVisible(mostrar);
    	lblConsumible.setVisible(mostrar);
    	lblCantidadDescripcion.setVisible(mostrar);
    	txtCantidad.setVisible(mostrar);
    	lblConsumibleDescripcion.setVisible(mostrar);
    	lblNroMesaDescripcion.setVisible(mostrar);
    }
    
	public static AgregarPedido getInstance(){
    	if(instancia == null){
    		instancia = new AgregarPedido();
    	}
    	
    	return instancia;
    }
	
	

}
