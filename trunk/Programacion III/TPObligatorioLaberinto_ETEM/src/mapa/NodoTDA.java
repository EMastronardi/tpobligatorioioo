package mapa;

import java.util.Set;

import grafico.PuntoTDA;

public interface NodoTDA extends Comparable<NodoTDA>{

	/*
	 * representa la ubicaci�n (un PuntoTDA)
	 */
	public PuntoTDA getUbicacion();
	
	/*
	 * representa el NodoTDA anterior (de donde viene)
	 */
	public NodoTDA getPadre();
	public void setPadre(NodoTDA padre);
	
	
	
	/*
	 * representa la distancia heur�stica al punto destino
	 */
	public int getH();
	public void setH(PuntoTDA p);
	
	/*
	 * representa el peso acumulado (el heredado del padre m�s el propio)
	 */
	public int getG();
	public void setG(int g);
	
	/*
	 * representa el peso total ( g + h )
	 */
	public int f();
	
	/*
	 * retorna verdadero si es igual al otro NodoTDA 
	 */
	public boolean esIgual(NodoTDA n);
	
	/*
	 * Retornar el m�todo toString() del PuntoTDA (la ubicacion)
	 */
	public String codigo();

	public int calcularDistancia(PuntoTDA punto);
	
	
	
	
}
