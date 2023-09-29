package servicios;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import utilidades.Validators;
import vista.UI;

public class ServiceTextFields {
	
	private static int LONGITUD_ISBN=13;
	
	public ServiceTextFields() {
	}

	public void annadirComportamientoTextoIsbn(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				StringBuilder texto = new StringBuilder(textField.getText());
				texto.append(e.getKeyChar());
				if(!Validators.isNaturalNumber(texto.toString()) || texto.length()>LONGITUD_ISBN) {
					e.consume();
				}
			}
		});
		
	}
	public void annadirComportamientoTextoNumeroReal(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				StringBuilder texto = new StringBuilder(textField.getText());
				texto.append(e.getKeyChar());
				if( !Validators.isNaturalNumber(texto.toString()) && !Validators.isRealNumber(texto.toString())) {
					e.consume();
				}
			}
		});
	}
	
	public void annadirComportamientoTextoSinNumerosYSimbolos(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				StringBuilder texto = new StringBuilder(textField.getText());
				texto.append(e.getKeyChar());
				if( !Validators.isPhraseWithoutSymbolsAndNumber(texto.toString())) {
					e.consume();
				}
			}
		});
	}
}
