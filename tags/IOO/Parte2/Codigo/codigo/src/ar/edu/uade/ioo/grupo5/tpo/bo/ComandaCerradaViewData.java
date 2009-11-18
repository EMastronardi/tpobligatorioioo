package ar.edu.uade.ioo.grupo5.tpo.bo;

public class ComandaCerradaViewData {
	private double total;
	public double getTotal() {
		return total;
	}

	public int getNroMesa() {
		return nroMesa;
	}

	public int getNroMozo() {
		return nroMozo;
	}

	private int nroMesa;
	private int nroMozo;
	
	public ComandaCerradaViewData(double total, int nroMesa, int nroMozo) {
		super();
		this.total = total;
		this.nroMesa = nroMesa;
		this.nroMozo = nroMozo;
	}
	
	
}
