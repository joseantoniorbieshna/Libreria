package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelRadioButton extends JPanel{
	private List<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
	private Boolean oneIsOBligatory=false;
	
	public PanelRadioButton(String[] textoBotones) {
		super();
		generarBotonesPorNombre(textoBotones);
	}
	
	

	private void generarBotonesPorNombre(String[] textoBotones) {
		for(String textoBoton: textoBotones) {
			JRadioButton boton = new JRadioButton(textoBoton);
			boton.addActionListener(getComportamiento());
			radioButtons.add(boton);
			this.add(boton);
		}
	}
	public String getTextRButtonSelected() {
		Iterator<JRadioButton> iterator = radioButtons.iterator();
		while(iterator.hasNext()) {
			JRadioButton boton = iterator.next();
			if(boton.isSelected())
				return boton.getText();
		}
		return null;
	}
	
	public JRadioButton getRButtonSelected() {
		Iterator<JRadioButton> iterator = radioButtons.iterator();
		while(iterator.hasNext()) {
			JRadioButton boton = iterator.next();
			if(boton.isSelected())
				return boton;
		}
		return null;
	}
	
	public void addListener(ActionListener actionListener){
		radioButtons.forEach(e->{
			e.addActionListener(actionListener);
		});
	}
	
	
	public boolean esEste(String input) {
		return getTextRButtonSelected().equals(input);
	}
	
	
	public void setSelectByText(String text) {
		Iterator<JRadioButton> iterator = radioButtons.iterator();
		while(iterator.hasNext()) {
			JRadioButton boton = iterator.next();
			if(boton.getText().equals(text))
				boton.setSelected(true);
		}
	}
	
	public void deselectedAll() {
		Iterator<JRadioButton> iterator = radioButtons.iterator();
		while(iterator.hasNext()) {
			JRadioButton boton = iterator.next();
			boton.setSelected(false);
		}
	}
	
	
	private ActionListener getComportamiento() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				radioButtons.forEach((boton)->{
					if(boton!=e.getSource()) {
						((JRadioButton)boton).setSelected(false);//borrar demas seleccionado						
					}						
					else {
						((JRadioButton)boton).setSelected(true);
					}
				});
			}
		};
	}
	
	
	
}
