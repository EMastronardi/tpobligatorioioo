package cliente;

import java.util.List;

import modelo.*;

public class Pincipal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Reina> solucion;
		
		TableroManager tablero = new TableroManager(14);
		try {
			solucion = tablero.ubicarReinas();
			imprimirSolucion(solucion);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		
		

	}

	private static void imprimirSolucion(List<Reina> solucion) {
		int[][] tableroFinal = new int[solucion.size()][solucion.size()];
		for (Reina reina : solucion) {
			if (reina != null){
				//System.out.println("Reina: " + reina.getX() + reina.getY());
				tableroFinal[reina.getX()][reina.getY()]=1;
			}
		}
		for (int i=0;i<solucion.size();i++){
			for (int j=0;j<solucion.size();j++){
				System.out.print(" " + tableroFinal[i][j]);
			}
			System.out.println();
		}
	}

}
