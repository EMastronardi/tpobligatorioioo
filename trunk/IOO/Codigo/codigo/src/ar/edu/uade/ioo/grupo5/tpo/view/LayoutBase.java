package ar.edu.uade.ioo.grupo5.tpo.view;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.sun.org.apache.bcel.internal.generic.Type;

public abstract class LayoutBase extends JFrame {
	private JPanel pnlButton;
	
	protected void formatLayout(){
		int iLabel = 0;
		int iTextField = 0;
		int iButton = 0;
		int height = 0;
		
		for (Component component : getContentPane().getComponents()) {
			if (component instanceof JLabel) {
				JLabel label = (JLabel) component;
				label.setBounds(50, 40 + iLabel * 50, 150, 30);
				iLabel++;
			}
			
			if (component instanceof JTextField) {
				JTextField field = (JTextField) component;
				field.setBounds(250, 40 + iTextField * 50, 300, 30);
				iTextField++;
			}
		}
		
		if(iLabel > iTextField)
			height = iLabel* 60;
		else
			height = iTextField* 60;
		
		pnlButton = new JPanel();
		for (Component component : getContentPane().getComponents()) {
			if (component instanceof JButton) {
				JButton button = (JButton) component;
				
				
				pnlButton.add(button);
			}
		}
		
		getContentPane().add(pnlButton);
		
		pack();
		
		setSize(600, height  + 100);
		
		pnlButton.setBounds(0, height + 10, getWidth(), 40);
	}
	

}
