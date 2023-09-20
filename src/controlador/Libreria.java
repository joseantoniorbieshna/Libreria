package controlador;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.Libro;
import utilidades.LibroObjectMother;

public class Libreria {
	private ArrayList<Libro> arrayLibro = new ArrayList<>();
	
	
	public Libreria() {
		arrayLibro = (ArrayList<Libro>) LibroObjectMother.getList();
	}
	
	
	public void addLibro(Libro libro) {
		arrayLibro.add(libro);
	}
	
	public void removeLibro(Libro libro) {
		arrayLibro.remove(libro);
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
			String[] nombresColumnas = { "ISBN", "TITULOS", "EDITORIAL", "AUTOR", "PRECIO" };
			String[][] filasTabla = new String[getLibreria().size()][5];
			for (int i = 0; i < this.getLibreria().size(); i++) {
				filasTabla[i][0] = this.getLibreria().get(i).getISBN();
				filasTabla[i][1] = this.getLibreria().get(i).getTitulo();
				filasTabla[i][2] = this.getLibreria().get(i).getEditorial();
				filasTabla[i][3] = this.getLibreria().get(i).getAutor();
				filasTabla[i][4] = String.valueOf(getLibreria().get(i).getPrecio());
			}
			DefaultTableModel tableWithFill = new DefaultTableModel(filasTabla,nombresColumnas);
			return tableWithFill;	
	}
}
	