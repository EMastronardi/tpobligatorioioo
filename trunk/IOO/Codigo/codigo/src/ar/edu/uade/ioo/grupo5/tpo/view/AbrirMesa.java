package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.control.Restaurant;


/**
 *
 * @author emolinero
 */

public class AbrirMesa extends LayoutBase {
	private JTextField txtNumeroMesa;
	private JButton btnAbrirMesa;
	private static AbrirMesa instancia;
	
	public AbrirMesa(){
		super("Abrir Mesa");
		initGUI();
		inicializarEventos();
	}
	

	private void initGUI(){
		txtNumeroMesa = new JTextField(3);
		btnAbrirMesa = new JButton("Abrir Mesa");
		
		addField("Nro Mesa:", txtNumeroMesa);
		addButton(btnAbrirMesa);
		
		inicializar();
		setSize(400, this.getHeight() +10);
	}
	
	public static AbrirMesa getInstance(){
		if (instancia == null){
			instancia = new AbrirMesa();
		}
		return instancia;
	}
	
	private void inicializarEventos() {
		btnAbrirMesa.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					hideMessage();
					int numeroMesa = Integer.parseInt(txtNumeroMesa.getText());
					Restaurant.getInstance().nuevaComanda(numeroMesa);
					dispose();
				} 
				catch (Exception ex) {
					handleException(ex);
				}
			}
		});
		
	}


}

