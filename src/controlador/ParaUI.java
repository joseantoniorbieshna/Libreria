package controlador;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Libro;
import utilidades.Validators;
import vista.UI;

public class ParaUI extends UI {
	private Libreria libreria = new Libreria();

	public ParaUI() {
		addComportamientoGuardar();
		addComportamientoConsultar();
		addComportamientoBorrar();
		addComportamientoSalir();
		rellenarTabla();
		
		comportamientoTextoNumeroNatural(textISBN);
		comportamientoTextoNumeroReal(textPrecio);
	}

	public void addComportamientoGuardar() {
		this.btnSave.addActionListener(e -> {
			if (!textISBN.getText().isEmpty()) {
				Libro book = new Libro(textISBN.getText(), textTItulo.getText(), textAutor.getText(),
						textEditorial.getText(), Float.parseFloat(textPrecio.getText()));
				libreria.addLibro(book);
				vaciarCampos();
				rellenarTabla();
			}
		});
	}

	public void rellenarTabla() {
		tableLibrary.setModel(libreria.getFillTableModel());
	}

	public void addComportamientoSalir() {
		this.btnExit.addActionListener(e -> dispose());
	}

	public void addComportamientoBorrar() {
		this.btnDelete.addActionListener(e -> vaciarCampos());
	}
	
	public void comportamientoTextoNumeroNatural(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				StringBuilder texto = new StringBuilder(textField.getText());
				texto.append(e.getKeyChar());
				if(!Validators.isNaturalNumber(texto.toString())) {
					e.consume();
				}
			}
		});
		
	}
	public void comportamientoTextoNumeroReal(JTextField textField) {
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

	public void addComportamientoConsultar() {
		this.btnConsultar.addActionListener(e -> {
			String isbn = JOptionPane.showInputDialog("introduce un ISBN:");
			boolean libroExiste = libreria.existe(isbn);
			if (libroExiste && isbn !=null) {
				Libro libroEncontrado = libreria.getLibroByISBN(isbn);
				String mensajePanel = "Libro";
				String mensajeMostrar= libroEncontrado.getMessage();
				JOptionPane.showMessageDialog(null, mensajeMostrar, mensajePanel, JOptionPane.INFORMATION_MESSAGE);
			}
			else if(!libroExiste && isbn !=null) {
				JOptionPane.showMessageDialog(null, "No se ha encontrado el libro", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});
	}

}
