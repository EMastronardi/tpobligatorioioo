package modelo;

public class Reina {
	int x;
	int y;
	int valor;
	public Reina(int x, int y, int valorReina){
		this.x = x;
		this.y = y;
		this.valor = valorReina;
	}
	
	public int getValor(){
		return this.valor;
	}

	public int getX() {
		return x;
	}
	
	

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setValor(int i) {
		this.valor = i;
 	}
	
	
	
}
