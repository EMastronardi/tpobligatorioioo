package ar.edu.uade.ioo.grupo5.tpo.bo;

import java.util.Iterator;
import java.util.Vector;

/**
Project : TP_IPOO_1
File Name : Comanda.java
Date : 30/09/2009
@author Grupo 5 
*/

public class Comanda {
	private Vector<ItemComanda> items;
	//TODO estos atributos van o no van?!?!
	private Mesa mesa;
	private Mozo mozo;
	
	
	public void Comanda(Mesa mesa, Mozo mozo) {
		this.mesa = mesa;
		this.mozo = mozo;
	}
	
	public double calcularTotal() {
		double total=0;
		for(int i=0;i<items.size();i++){
			total += items.elementAt(i).calcularSubtotal();
		}
		return total;
	}
	
	public void addItem(ItemComanda item) {
		items.add(item);
	}
	
	
}
