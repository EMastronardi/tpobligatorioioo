package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Formulario {
	 private GridBagConstraints constraints;
	    private Container container;
	    private JLabel lblMessage;
	    public Formulario(Container container) {
	    	lblMessage = new JLabel();
	    	lblMessage.setSize(500, 50);
	    	lblMessage.setForeground(new Color(203,12,12));
	    	
	        this.container = container;
	        container.setLayout(new GridBagLayout());
	        constraints = new GridBagConstraints();
	        constraints.insets = new Insets(3, 3, 3, 3);
	        constraints.gridx = 0;
	        constraints.gridy = 0;
	        constraints.anchor = GridBagConstraints.LINE_START;
	        this.container.add(lblMessage, constraints);
	        constraints.gridy++;
	       
	    }
	    public void showMessage(String message){
	    	lblMessage.setText(message);
		}
		
	    public void hideMessage(){
			lblMessage.setText("");
		}
		
	    public void addField(String label, JComponent component) {
	        addField(new JLabel(label), component);
	    }

	    public void addField(JLabel label, JComponent component) {
	        constraints.gridx = 0;
	        constraints.weightx = 0;
	        constraints.weighty = 0;
	        Class<?> clazz = component.getClass();

	        constraints.fill = GridBagConstraints.NONE;
	        if(JScrollPane.class == clazz)
	            constraints.anchor = GridBagConstraints.FIRST_LINE_END;
	        else
	            constraints.anchor = GridBagConstraints.LINE_END;
	        container.add(label, constraints);

	        constraints.gridx = 1;

	        if(JScrollPane.class == clazz) {
	            constraints.weightx = 1;
	            constraints.weighty = 1;
	            constraints.fill = GridBagConstraints.BOTH;
	            container.add(component, constraints);
	        } else if(JTextField.class == clazz) {
	            JTextField textField = (JTextField) component;
	            if(textField.getColumns() == 0) {
	                constraints.fill = GridBagConstraints.BOTH;
	            } else {

	                textField.setMinimumSize(textField.getPreferredSize());
	                constraints.anchor = GridBagConstraints.LINE_START;
	            }
	            container.add(component, constraints);
	        } else {
	            constraints.anchor = GridBagConstraints.LINE_START;
	            container.add(component, constraints);
	        }
	        label.setLabelFor(component); 

	        constraints.gridy++;
	    }

}
