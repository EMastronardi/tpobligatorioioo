package ar.edu.uade.ioo.grupo5.tpo.bo;

import ar.edu.uade.ioo.grupo5.tpo.common.ValidationException;


/**
  Project : TP_IPOO_1
  File Name : Plato.java
  Date : 30/09/2009
  @author Grupo 5 

*/




public class Plato extends Consumible {
	public Plato(String descripcion, String codigo, double precio) throws ValidationException {
		super(descripcion, codigo, precio);
	}
}
