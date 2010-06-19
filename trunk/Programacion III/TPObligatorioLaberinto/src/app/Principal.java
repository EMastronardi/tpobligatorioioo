package app;

import grafico.Punto;

import java.io.Console;

import mapa.Camino;
import mapa.Mapa;
import mapa.MapaTDA;

import vista.Ventana;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] matriz = {
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1}
				};
		
		Mapa mapa = new Mapa(matriz);
		
		
		
		Camino camino = new Camino(mapa);
		System.out.println("sdsd");

	}

}
