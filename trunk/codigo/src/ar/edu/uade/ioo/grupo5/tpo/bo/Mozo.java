package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Vector;

/**
Project : TP_IPOO_1
File Name : Mozo.java
Date : 30/09/2009
@author Grupo 5 
*/


public class Mozo {
	private static int cantMozos=0;
	private int nro;
	private Vector<Comanda> comandasCerradas;
	
	
	public Mozo() {
		this.nro = getNewId();
	}
	
	private static int getNewId() {
		return ++Mozo.cantMozos;
	}
	
	public int getNro() {
		return nro;
	}
	
	public Vector<Comanda> getComandasCerradas(){
		return comandasCerradas;
	}


	public void addComandaCerrada(Comanda unaComanda) {
		comandasCerradas.add(unaComanda);
		
	}
}

