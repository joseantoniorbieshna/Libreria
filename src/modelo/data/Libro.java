package modelo.data;

public class Libro {
	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private Float precio;
	private String formato;
	private String estado;
	private Integer cantidad;
	
	public final static String[] CAMPOS = { "ISBN", "TITULOS", "EDITORIAL", "AUTOR", "PRECIO", "FORMATO","ESTADO","CANTIDAD" };
	public static final String[] FORMATOS = {"Cartoné","Rústico","Grapada","Espiral"};
	public static final String[] ESTADOS = {"Reedición", "Novedad"};
	
	
	public Libro() {}

	public Libro(String iSBN, String titulo, String autor, String editorial, Float precio, String formato, String estado, Integer cantidad) {
		super();
		this.ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
		this.formato = formato;
		this.estado = estado;
		this.cantidad = cantidad;
	}
	public Libro(Libro libro) {
		super();
		this.ISBN = libro.getISBN();
		this.titulo = libro.getTitulo();
		this.autor = libro.getAutor();
		this.editorial = libro.getEditorial();
		this.precio = libro.getPrecio();
		this.formato = libro.getFormato();
		this.estado = libro.getEstado();
		this.cantidad=libro.getCantidad();
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
	
	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public void cambiarDatos(Libro libro) {
		this.setTitulo(libro.getTitulo());
		this.setAutor(libro.getAutor());
		this.setEditorial(libro.getEditorial());
		this.setPrecio(libro.getPrecio());
		this.setFormato(libro.getFormato());
		this.setEstado(libro.getEstado());
		this.setCantidad(libro.getCantidad());
	}


	public String toString() {
		return "ISBN: " + getISBN() + "\n" + "TITULO: " + getTitulo() + "\n" + "AUTOR: " + getAutor() + "\n"
				+ "EDITORIAL: " + getEditorial() + "\n"+"PRECIO: " + String.valueOf(getPrecio())+ "\n" + 
				"FORMATO: "+ getFormato();
	}

}