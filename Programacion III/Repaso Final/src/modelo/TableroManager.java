package modelo;

import java.util.ArrayList;
import java.util.List;

public class TableroManager {
	int[][] tablero;
	List<Reina> reinas;
	int dimension;
	
	public TableroManager(int dimension){
		tablero = new int[dimension][dimension];
		reinas = new ArrayList<Reina>();
		this.dimension = dimension;  
	}
	
	public List<Reina> ubicarReinas() throws Exception{
		List<Reina> solucion = new ArrayList<Reina>();
		int i=0;
		
		if (!ubicarReina(solucion)){
			throw new Exception("No se encontró solucion");
		}
		
		return solucion;
	}

	private boolean ubicarReina(List <Reina> solucion) {
		
		Reina currentQueen = null; 
		
		if (solucion.size() == dimension){
			return true;
		}
		
		for (int i=0;i<dimension;i++){
			for (int j=0;j<dimension;j++){
				if (tablero[i][j] == 0){
					 
					//ubico la reina actual y pruebo la siguiente
					currentQueen = new Reina(i,j,(solucion.size() + 1) * 2);
					solucion.add(currentQueen);
					ejecutarReglas(currentQueen);

					if (ubicarReina(solucion)){
						return true;
					} else 					//si no encontre una posicion valida
						{
						solucion.remove(currentQueen);
						currentQueen.setValor(currentQueen.getValor() * -1);
						ejecutarReglas(currentQueen);
					}


				} 
				
			}
		}
		
		if (currentQueen == null){
			return false;
		} else {
			
		}
		
		return false;
		
	}

	private void ejecutarReglas(Reina currentQueen) {
		for (int i=0;i<dimension;i++){
			for (int j=0;j<dimension;j++){
				if (currentQueen.getX() == i || currentQueen.getY() == j || (Math.abs(currentQueen.getX()-i) == Math.abs(currentQueen.getY()-j))){
					tablero[i][j]+=currentQueen.getValor();
				}
			}
		}
		
	}
	
}
