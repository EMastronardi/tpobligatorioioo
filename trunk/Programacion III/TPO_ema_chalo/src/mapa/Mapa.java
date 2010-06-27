package mapa;

import grafico.Punto;
import grafico.PuntoTDA;

import java.util.ArrayList;
import java.util.List;

public class Mapa extends MapaBase{

	@Override
	public List<PuntoTDA> getAdyacentes(PuntoTDA ubicacion) {
		List<PuntoTDA> lista = new ArrayList<PuntoTDA>();
		int x = ubicacion.getX();
		int y = ubicacion.getY();
		if (getDensidad(x-1, y-1)<3)lista.add(new Punto(x-1, y-1));
		if (getDensidad(x-1, y)<3)lista.add(new Punto(x-1, y));
		if (getDensidad(x-1, y+1)<3)lista.add(new Punto(x-1, y+1));
		if (getDensidad(x, y-1)<3)lista.add(new Punto(x, y-1));
		if (getDensidad(x, y+1)<3)lista.add(new Punto(x, y+1));
		if (getDensidad(x+1, y-1)<3)lista.add(new Punto(x+1, y-1));
		if (getDensidad(x+1, y)<3)lista.add(new Punto(x+1, y));
		if (getDensidad(x+1, y+1)<3)lista.add(new Punto(x+1, y+1));
			
		return lista;
	}

}
