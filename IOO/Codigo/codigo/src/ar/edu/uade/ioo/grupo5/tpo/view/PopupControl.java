package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopupControl extends JFrame {
	private JLabel lblMessage;
	private JButton btnContinuar;
	private static PopupControl instancia = null;
	
	private PopupControl() {
		inicializar();
	}
	private void inicializar() {
		lblMessage = new JLabel();
		lblMessage.setBounds(30,3, 200, 40);
		btnContinuar = new JButton("Continuar");
		
		getContentPane().setLayout(null);
		getContentPane().add(lblMessage);
		btnContinuar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		setSize(400, 100);
	
	}
	
	private static PopupControl getInstancia(){
		if(instancia == null){
			instancia = new PopupControl();
		}
		
		return instancia;
	}
	private void setMessage(String mensaje){
		lblMessage.setText(mensaje);
	}
	
	private void setColorMessage(Color color){
		lblMessage.setForeground(color);
	}
	public static void showMessage(String mensaje){
		PopupControl.getInstancia().setMessage(mensaje);
		PopupControl.getInstancia().setColorMessage(Color.black);
		PopupControl.getInstancia().setVisible(true);
		Toolkit.getDefaultToolkit().beep();
	}
	
	public static void hidePopup(){
		PopupControl.getInstancia().setVisible(false);
	}
	public static void showError(String mensaje) {
		PopupControl.getInstancia().setMessage(mensaje);
		PopupControl.getInstancia().setColorMessage(new Color(203,12,12));
		PopupControl.getInstancia().setVisible(true);
		Toolkit.getDefaultToolkit().beep();
		
	}
	
	
	
}
