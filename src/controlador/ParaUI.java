package controlador;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.data.Libro;
import modelo.repository.Libreria;
import servicios.ServiceCompraVenta;
import servicios.ServiceTabla;
import servicios.ServiceTextFields;
import vista.UI;

public class ParaUI extends UI {
	private Libreria libreria = new Libreria();

	private ServiceTextFields serviceTextFields;
	private ServiceTabla serviceTabla;
	private ServiceCompraVenta serviceCompraVenta;

	private JPanel actualSelectedJPanel = getPanelLibreria();

	private final String OPT_GUARDAR = "Guardar";
	private final String OPT_EDITAR = "Editar";

	public ParaUI() {
		serviceTextFields = new ServiceTextFields();
		serviceTabla = new ServiceTabla(getTableLibrary(), getLibreria());

		serviceCompraVenta = new ServiceCompraVenta(getTextIsbnCompraVenta(), getTextTituloCompraVenta(),
				getTextPrecioCompraVenta(), getSpinnerNumeroArticulos(), getJpanelButtonCompraVenta(),
				getLblTotalCompraVenta(), getTextCantidadCompraVenta());

		serviceTextFields.annadirComportamientoTextoIsbn(getTextISBN());
		serviceTextFields.annadirComportamientoTextoNumeroReal(getTextPrecio());
		serviceTextFields.annadirComportamientoTextoSinNumerosYSimbolos(getTextAutor());
		serviceTabla.rellenarTabla();

		addComportamientoGuardar();
		addComportamientoConsultar();
		addComportamientoBorrar(getBtnDelete());
		addComportamientoBorrar(serviceTabla.getItemBorrar());
		addComportamientoLlevarAPanelCompraVenta(serviceTabla.getItemCompraVenta());
		addComportamientoSalir();
		addComportamientoGuardarPanelCentralActual();
		addComportamientoConfrimar();
		addComportamientoEditar(serviceTabla.getItemEditar());
	}	

	public void addComportamientoGuardar() {
		this.getBtnSave().addActionListener(e -> {
			
			if (estanTodosCamposLlenos() && getTextISBN().getText().length() == ServiceTextFields.LONGITUD_ISBN) {
				Libro libroPanel = new Libro(getTextISBN().getText(), getTextTItulo().getText(), getTextAutor().getText(),
						getTextEditorial().getText(), Float.parseFloat(getTextPrecio().getText()),
						getPanelFormato().getTextRButtonSelected(), getPanelEstado().getTextRButtonSelected(),
						(Integer) getSpinnerCantidadLibro().getValue());
				
				//EDITAR
				if(getBtnSave().getText().equals(OPT_EDITAR)) {
					String isbnPanel = libroPanel.getISBN();
					Libro libroDeLibreria = libreria.getLibroByISBN(isbnPanel);
					if(libreria.existe(isbnPanel)) {
						libroDeLibreria.cambiarDatos(libroPanel);
					}
					getBtnSave().setText(OPT_GUARDAR);
					editarPanelLibroParaGuardar();
					
				}
				//YA EXISTE UN ISBN
				else if(libreria.existe(libroPanel.getISBN())) {
					 JOptionPane.showMessageDialog(this, "El ISBN ya existe.", "ERROR",
						        JOptionPane.WARNING_MESSAGE);
				}
				
				//GUARDAR
				else if(getBtnSave().getText().equals(OPT_GUARDAR)) {
					libreria.addLibro(libroPanel);
				}
				vaciarCamposLibro();
				serviceTabla.rellenarTabla();
			}
		});
	}

	public void addComportamientoConfrimar() {
		this.getBtnConfirmar().addActionListener(e -> {
			serviceCompraVenta.cambiarCantidadLibro();
			serviceCompraVenta.quitarLibro();
			vaciarCamposCompraVenta();
			serviceTabla.rellenarTabla();
		});
	}

	public void addComportamientoSalir() {
		this.getBtnExit().addActionListener(e -> dispose());
	}

	public void addComportamientoBorrar(AbstractButton myComponent) {
		myComponent.addActionListener(e -> {
			// PANEL LIBRERIA
			if (actualSelectedJPanel.equals(getPanelLibreria())) {
				String isbn = serviceTabla.getSeleccionadoIsbnTabla();
				if (isbn != null) {
					boolean estaBorrado = getLibreria().pedirConfirmacionBorrarPorIsbn(isbn, this);

					if (estaBorrado) {
						serviceCompraVenta.quitarLibro();
						vaciarCamposCompraVenta();
						vaciarCamposLibro();
						serviceTabla.rellenarTabla();
						editarPanelLibroParaGuardar();
					}

				}
				// PANEL LIBRO
			} else if (actualSelectedJPanel.equals(getPanelLibro())) {
				vaciarCamposLibro();
				getBtnSave().setText(OPT_GUARDAR);
				editarPanelLibroParaGuardar();
				// PANEL COCMPRA VENTA
			} else if (actualSelectedJPanel.equals(getPanelComprarVender())) {
				serviceCompraVenta.quitarLibro();
				vaciarCamposCompraVenta();
			}

		});
	}

	public void addComportamientoLlevarAPanelCompraVenta(AbstractButton myComponent) {
		myComponent.addActionListener(e -> {
			String isbnSelececcionado = serviceTabla.getSeleccionadoIsbnTabla();

			if (isbnSelececcionado != null && libreria.existe(isbnSelececcionado)) {
				getPanelCentral().setSelectedComponent(getPanelComprarVender());
				Libro libro = libreria.getLibroByISBN(isbnSelececcionado);
				serviceCompraVenta.setLibro(libro);

			}

		});
	}

	public void addComportamientoConsultar() {
		this.getBtnConsultar().addActionListener(e -> {
			String isbn = JOptionPane.showInputDialog("introduce un ISBN:");
			boolean libroExiste = libreria.existe(isbn);
			if (libroExiste && isbn != null) {
				Libro libroEncontrado = libreria.getLibroByISBN(isbn);
				String mensajePanel = "Libro";
				String mensajeMostrar = libroEncontrado.toString();
				JOptionPane.showMessageDialog(null, mensajeMostrar, mensajePanel, JOptionPane.INFORMATION_MESSAGE);
			} else if (!libroExiste && isbn != null) {
				JOptionPane.showMessageDialog(null, "No se ha encontrado el libro", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});
	}

	public void addComportamientoGuardarPanelCentralActual() {
		getPanelCentral().addChangeListener(e -> {

			if (getPanelLibro().isVisible()) {
				getBtnSave().setVisible(true);
				getBtnConsultar().setVisible(true);
				getBtnConfirmar().setVisible(false);
			} else if (getPanelLibreria().isVisible()) {

				getBtnSave().setVisible(false);
				getBtnConsultar().setVisible(false);
				getBtnConfirmar().setVisible(false);
			} else if (getPanelComprarVender().isVisible()) {

				getBtnSave().setVisible(false);
				getBtnConsultar().setVisible(false);
				getBtnConfirmar().setVisible(true);
			}
			actualSelectedJPanel = (JPanel) getPanelCentral().getSelectedComponent();
		});
	}

	public void editarPanelLibroParaGuardar() {
		getBtnSave().setText(OPT_GUARDAR);
		getTextISBN().setEditable(true);
	}

	public void editarPanelLibroParaEditar() {
		getBtnSave().setText(OPT_EDITAR);
		getTextISBN().setEditable(false);
	}

	public void addComportamientoEditar(AbstractButton myComponent) {

		myComponent.addActionListener(e -> {
			String isbn = serviceTabla.getSeleccionadoIsbnTabla();
			if (libreria.existe(isbn)) {
				Libro libro = libreria.getLibroByISBN(isbn);
				getPanelCentral().setSelectedComponent(getPanelLibro());
				getTextISBN().setText(libro.getISBN());
				getTextTItulo().setText(libro.getTitulo());
				getTextAutor().setText(libro.getAutor());
				getTextEditorial().setText(libro.getEditorial());
				getTextPrecio().setText(libro.getPrecio().toString());
				getPanelFormato().setSelectByText(libro.getFormato());
				getPanelEstado().setSelectByText(libro.getEstado());
				getSpinnerCantidadLibro().setValue(libro.getCantidad());
				
				editarPanelLibroParaEditar();
			}
		});
	}

	private boolean estanTodosCamposLlenos() {
		if (getTextISBN().getText() != null && getTextTItulo().getText() != null && getTextAutor().getText() != null
				&& getTextEditorial().getText() != null && getTextPrecio().getText()!=null && getPanelFormato().getTextRButtonSelected() != null
				&& getPanelEstado().getTextRButtonSelected() != null) {
			return true;
		}
		return false;
	}

	public Libreria getLibreria() {
		return libreria;
	}

}
