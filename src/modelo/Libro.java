package modelo;

public class Libro {
	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private Float precio;
	private String formato;
	
	public static final String[] FORMATOS = {"Carton�","R�stico","Grapada","Espiral"};
	public static final String[] ESTADOS = {"Reedici�n", "Novedad"};
	
	
	public Libro() {}

	public Libro(String iSBN, String titulo, String autor, String editorial, Float precio, String formato) {
		super();
		this.ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
		this.formato = formato;
	}
	public Libro(Libro libro) {
		super();
		this.ISBN = libro.getISBN();
		this.titulo = libro.getTitulo();
		this.autor = libro.getAutor();
		this.editorial = libro.getEditorial();
		this.precio = libro.getPrecio();
		this.formato = libro.getFormato();
		
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	

	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	@Override
	public String toString() {
		return "ISBN: " + getISBN() + "\n" + "TITULO: " + getTitulo() + "\n" + "AUTOR: " + getAutor() + "\n"
				+ "EDITORIAL: " + getEditorial() + "\n"+"PRECIO: " + String.valueOf(getPrecio())+ "\n" + 
				"FORMATO: "+ getFormato();
	}

}