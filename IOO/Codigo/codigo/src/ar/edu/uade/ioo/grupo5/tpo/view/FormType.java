package ar.edu.uade.ioo.grupo5.tpo.view;

import javax.swing.JFrame;



public class FormType {
	private FORMS type;
	private JFrame form;
	
	public FORMS getType() {
		return type;
	}
	public void setType(FORMS type) {
		this.type = type;
	}
	public JFrame getForm() {
		return form;
	}
	public void setForm(JFrame form) {
		this.form = form;
	}
	
	public FormType(FORMS type, JFrame form) {
		super();
		this.type = type;
		this.form = form;
	}
	
	
}
