package ar.edu.uade.ioo.grupo5.tpo.view;

import java.util.Dictionary;
import java.util.Vector;

import javax.annotation.processing.SupportedOptions;
import javax.swing.JFrame;




/**
 * 
 * @author Emmanuel
 *
 */
public class Principal extends LayoutBase {
	Vector<FormType> formularios;
    /**
	    * @param args the command line arguments
	    */
	    public static void main(String args[]) {
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new Principal().setVisible(true);
	            }
	        });
	    }
	
	public Principal() {
		formularios = new Vector<FormType>();
		initGUI();
	}

	private void initGUI() {
		try 
		{
			addForm(FORMS.INICIALIZACION_SISTEMA, new InicializacionSistema());
			
			
			showForm(FORMS.INICIALIZACION_SISTEMA);
			pack();
			
			
		} catch (Exception e) {
			
		}
		
	}

	private void showForm(FORMS formKey) {
		
		if(getForm(formKey) != null)
			getForm(formKey).setVisible(true); 
	}
	
private void hideForm(FORMS formKey) {
		
		if(getForm(formKey) != null)
			getForm(formKey).setVisible(false); 
	}
	
	
	
	private JFrame getForm(FORMS formKey){
		for (FormType item : formularios) {
			if(item.getType() == formKey)
				return item.getForm();
		}
		
		return null;
	}
	
	private void addForm(FORMS formKey, JFrame form){
		if(getForm(formKey) == null)
			formularios.add(new FormType(formKey, form));
		
	}
	
	
	
	

}
