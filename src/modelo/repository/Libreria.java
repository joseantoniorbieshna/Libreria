package modelo.repository;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import modelo.data.Libro;
import utilidades.LibroObjectMother;

public class Libreria {
	private ArrayList<Libro> arrayLibro = new ArrayList<>();
	private final static String[] nombresColumnas = { "ISBN", "TITULOS", "EDITORIAL", "AUTOR", "PRECIO", "FORMATO","ESTADO" };
	
	
	public Libreria() {
		arrayLibro = (ArrayList<Libro>) LibroObjectMother.getList();
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
	
	public ArrayList<Libro> getLibreria() {
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
		for(int i=0; i<arrayLibro.size();i++) {
			if(arrayLibro.get(i).getISBN().equals(isbn)) existe=true;
		}
		return existe;
	}
	
	public DefaultTableModel getFillTableModel() {
			String[][] filasTabla = new String[getLibreria().size()][6];
			for (int i = 0; i < this.getLibreria().size(); i++) {
				Libro libroActual = this.getLibreria().get(i);
				
				filasTabla[i][0] = libroActual.getISBN();
				filasTabla[i][1] = libroActual.getTitulo();
				filasTabla[i][2] = libroActual.getEditorial();
				filasTabla[i][3] = libroActual.getAutor();
				filasTabla[i][4] = String.valueOf(libroActual.getPrecio());
				filasTabla[i][5] = libroActual.getFormato();
			}
			DefaultTableModel tableWithFill = new DefaultTableModel(filasTabla,nombresColumnas);
			return tableWithFill;	
	}
}
	