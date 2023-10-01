package modelo.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.data.Libro;
import objectMother.LibroObjectMother;

public class Libreria {
	private List<Libro> arrayLibro = new ArrayList<>();
	
	
	
	public Libreria() {
		arrayLibro = (ArrayList<Libro>) LibroObjectMother.getList();
	}
	public Libreria(List<Libro> arrayLibro ) {
		this.arrayLibro = arrayLibro;
	}
	
	
	public void addLibro(Libro libro) {
		arrayLibro.add(libro);
	}
	
	public void removeLibro(Libro libro) {
		arrayLibro.remove(libro);
	}
	public void removeLibroByIsbn(String isbn) {
		Iterator<Libro> iterator = arrayLibro.iterator();
		while (iterator.hasNext()) {
			Libro libro = (Libro) iterator.next();
			if(libro.getISBN().equals(isbn)) {
				iterator.remove();
				break;
			}
		}
	}
	
	public List<Libro> getLibreria() {
		return arrayLibro;
	}
	
	public Libro getLibroByISBN(String ISBN) {
		for(int i=0; i<arrayLibro.size();i++) {
			if(arrayLibro.get(i).getISBN().equals(ISBN)) {
				return arrayLibro.get(i);
			}
		}
		return null;
	}
	
	public boolean existe(String isbn) {
		boolean existe= false;
		if(isbn==null) 
			return false;
		for(int i=0;  i<arrayLibro.size();i++) {
			if(arrayLibro.get(i).getISBN().equals(isbn)) existe=true;
		}
		return existe;
	}
	
	
	public DefaultTableModel getFillTableModel() {
			String[][] filasTabla = new String[getLibreria().size()][Libro.CAMPOS.length];
			for (int i = 0; i < this.getLibreria().size(); i++) {
				Libro libroActual = this.getLibreria().get(i);
				
				filasTabla[i][0] = libroActual.getISBN();
				filasTabla[i][1] = libroActual.getTitulo();
				filasTabla[i][2] = libroActual.getEditorial();
				filasTabla[i][3] = libroActual.getAutor();
				filasTabla[i][4] = String.valueOf(libroActual.getPrecio());
				filasTabla[i][5] = libroActual.getFormato();
				filasTabla[i][6] = libroActual.getEstado();
				filasTabla[i][7] = libroActual.getCantidad().toString();
			}
			DefaultTableModel tableWithFill = new DefaultTableModel(filasTabla,Libro.CAMPOS);
			return tableWithFill;	
	}
	
	private int crearMensajeConfirmaciónBorrar(String isbn, JFrame jframe) {
		String textoMostrar = "SEGURO QUE QUIERES BORRAR EL LIBRO:" + "\n" + "\n"
				+ getLibroByISBN(isbn) + "\n" + "\n";
		int resultado = JOptionPane.showConfirmDialog(jframe, textoMostrar, "BORRAR", JOptionPane.WARNING_MESSAGE,
				JOptionPane.YES_NO_OPTION);
		return resultado;
	}
	
	public boolean pedirConfirmacionBorrarPorIsbn(String isbn, JFrame jframe) {
		if (existe(isbn)) {
			int resultado = crearMensajeConfirmaciónBorrar(isbn,jframe);
			if (resultado == JOptionPane.OK_OPTION) {
				removeLibroByIsbn(isbn);
				return true;
			}
		}
		return false;
	}
	
}
	