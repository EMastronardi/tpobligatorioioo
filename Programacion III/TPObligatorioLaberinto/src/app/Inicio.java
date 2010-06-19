package app;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import mapa.Camino;
import mapa.MapaTDA;

import vista.Ventana;

public class Inicio {
	
	private static JButton btnResolver;
	private static VentanaSolver solver  = null;
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
            	Ventana.mostrar("1");
            	
            	btnResolver = new JButton("Resolver");
            	Ventana.panelCentral.getRootPane().add(btnResolver);
            	solver = new VentanaSolver();
            	solver.setSize(400, 100);
            	
            	solver.show();
            	solver.add(btnResolver);
            	
            	Camino camino = new Camino(Ventana.mapa);
            
            	btnResolver.addActionListener(new ActionListener(){
            		@Override
            		public void actionPerformed(ActionEvent e) {
            			MapaTDA mapa = Ventana.mapa;
            			
            			int[][] grilla =  mapa.getGrilla();
            			
            			int a = 34;
            			a  = a+2;
            		}
            	});
            	
            	camino.buscarCamino();
            }
        });
    }
}
