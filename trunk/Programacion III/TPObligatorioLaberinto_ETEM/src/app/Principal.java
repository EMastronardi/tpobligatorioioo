package app;

import grafico.Punto;
import grafico.PuntoTDA;

import java.io.Console;
import java.util.List;

import mapa.Camino;
import mapa.Mapa;
import mapa.MapaTDA;

import vista.Ventana;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*int[][] matriz = {
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,5,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1}
				};*/
		
		int[][] matriz = {
				{1,1,1,1},
				{1,1,1,1},
				{1,1,1,1},
				{1,1,1,1}
				};
		
		Mapa mapa = new Mapa(matriz);
		
		Camino camino = new Camino(mapa);
		
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
			
				System.out.print(" " + matriz[i][j] +  " ");
				
			}
			
			System.out.println("");
		}
		
		System.out.println("");
		camino.setOrigen(new Punto(0,0));
		camino.setDestino(new Punto(3,3));
		
		List<PuntoTDA> resultado = camino.buscarCamino();
		
		for (PuntoTDA puntoTDA : resultado) {
			matriz[puntoTDA.getX()][puntoTDA.getY()] = 1000;
		}
		
		

		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if(matriz[i][j] == 1000){
					System.out.print(" * ");
				}
				else{
					System.out.print(" " + matriz[i][j] +  " ");
				}
				
			}
			
			System.out.println("");
		}
	}

}
