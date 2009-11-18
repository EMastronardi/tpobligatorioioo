package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ar.edu.uade.ioo.grupo5.tpo.common.ErrorException;
import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;


public class LayoutBase extends JFrame {
	private JPanel content; // Panel contenedor principal
	private JPanel footer;  // Panel inferior 
    private JPanel header;  // Panel de cabecera
    private JPanel panel;
    private Formulario form; // Formulario donde vamos a ubicar los pares de label con un campo
    private String titulo;
    private JLabel lblMessage;
    private JPanel pnlMessage;
    private JPanel container;
    
	public LayoutBase(String titulo) {
		this.titulo = titulo;
		setTitle(titulo);
		lblMessage = new JLabel();
		content = new JPanel();
        footer = new JPanel();
        header = new JPanel();
        panel = new JPanel();
        pnlMessage = new JPanel();
        lblMessage.setForeground(new Color(203,12,12));
        container = new JPanel();
     // el formulario usa GridBagLayout
        form = new Formulario(content);
        
        footer.setLayout(new BoxLayout(footer, BoxLayout.LINE_AXIS));
        pnlMessage.setLayout(new BoxLayout(pnlMessage, BoxLayout.LINE_AXIS));
        
        footer.add(Box.createHorizontalGlue()); // botones alineados a la derecha
        footer.add(Box.createRigidArea(new Dimension(5, 0))); // separaci�n entre botones
        
        pnlMessage.add(Box.createHorizontalGlue()); // botones alineados a la derecha
        pnlMessage.add(Box.createRigidArea(new Dimension(5, 0))); // separaci�n entre botones
        
        
        footer.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5));
        

        // un JPanel por defecto tiene un FlowLayout
        // as� que la etiqueta estar� centrada
        JLabel lblTitulo = new JLabel(titulo);
        
        header.add(lblTitulo);
    
  
        header.setBackground(Color.GRAY);
        header.setBorder(BorderFactory.createEtchedBorder());

        // el "contentPane" tiene BorderLayout por defecto
        this.getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        pnlMessage.add(lblMessage);
        panel.add(lblMessage);
        panel.add(content);
        
        
        //this.getContentPane().add(content, BorderLayout.WEST);
        
        this.getContentPane().add(footer, BorderLayout.SOUTH);
        
        this.getContentPane().add(header, BorderLayout.NORTH);
        
	}
	
	protected void reset()
	{
		form.reset();
	}
	protected void showMessage(String message){
		this.lblMessage.setVisible(true);
		this.lblMessage.setText(message);
	}
	
	protected void hideMessage(){
		this.lblMessage.setVisible(false);
	}
	protected void inicializar(){
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
         
	}
	
	protected void addField(String titulo, JComponent component){
		form.addField(titulo,component);
	}
	
	protected void addField(JLabel titulo, JComponent component){
		form.addField(titulo,component);
	}
	
	protected void addButton(JButton boton){
		   footer.add(Box.createRigidArea(new Dimension(5, 0))); // separaci�n entre botones
		   footer.add(boton);
	       
		}
	
	protected void handleException(Exception ex){
		
		if(ex instanceof ValidationException){
			showMessage(ex.getMessage());
		}else
		if(ex instanceof ErrorException){
			PopupControl.showError(ex.getMessage());
		}
		else{
			
			PopupControl.showError("Se produjo un error descocido");
		}
		
		ex.printStackTrace();

		
	}
}
