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

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Ventana.mostrar("3950");
            }
        });
    }
}
