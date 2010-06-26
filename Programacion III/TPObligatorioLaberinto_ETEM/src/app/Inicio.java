package app;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import mapa.Camino;
import mapa.CaminoTDA;
import mapa.Mapa;
import mapa.MapaTDA;

import vista.Ventana;

public class Inicio {
	
	private static JButton btnResolver;
	private static VentanaSolver solver  = null;
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
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
        		
        		
        		
        		CaminoTDA camino = new Camino(mapa);

        		Ventana.camino = camino;
            	Ventana.mostrar("1");

            	
            
            }
        });
    }
}
