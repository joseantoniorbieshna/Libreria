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

	public ParaUI() {
		serviceTextFields = new ServiceTextFields();
		serviceTabla = new ServiceTabla(getTableLibrary(), this);
		serviceCompraVenta = new ServiceCompraVenta(this);
		
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
	}


	public void addComportamientoGuardar() {
		this.getBtnSave().addActionListener(e -> {
			if (estanTodosCamposLlenos() && getTextISBN().getText().length()==13) {
				Libro book = new Libro(getTextISBN().getText(), getTextTItulo().getText(), getTextAutor().getText(),
						getTextEditorial().getText(), Float.parseFloat(getTextPrecio().getText()),getPanelFormato().getTextRButtonSelected(),Libro.FORMATOS[1]);
				libreria.addLibro(book);
				vaciarCampos();
				serviceTabla.rellenarTabla();
			}
		});
	}



	public void addComportamientoSalir() {
		this.getBtnExit().addActionListener(e -> dispose());
	}

	public void addComportamientoBorrar(AbstractButton myComponent) {
		myComponent.addActionListener(e ->{
				if(actualSelectedJPanel.equals(getPanelLibreria())) {
					String isbn= serviceTabla.getSeleccionadoIsbnTabla();
					if(isbn!=null) {
						getLibreria().pedirConfirmacionBorrarPorIsbn(isbn, this);						
					}
				}else {
					vaciarCampos();					
				}
				
			});						
		}
	
	public void addComportamientoLlevarAPanelCompraVenta(AbstractButton myComponent) {
		myComponent.addActionListener(e ->{
			String isbnSelececcionado = serviceTabla.getSeleccionadoIsbnTabla();
			
			if(isbnSelececcionado!=null && libreria.existe(isbnSelececcionado)) {
				getPanelCentral().setSelectedComponent(getPanelComprarVender());
				Libro libro = libreria.getLibroByISBN(isbnSelececcionado);
				getTextIsbnCompraVenta().setText(libro.getISBN());
				getTextTituloCompraVenta().setText(libro.getTitulo());
				getTextPrecioCompraVenta().setText(libro.getPrecio().toString());
				
			}
				
			});						
		}

	public void addComportamientoConsultar() {
		this.getBtnConsultar().addActionListener(e -> {
			String isbn = JOptionPane.showInputDialog("introduce un ISBN:");
			boolean libroExiste = libreria.existe(isbn);
			if (libroExiste && isbn !=null) {
				Libro libroEncontrado = libreria.getLibroByISBN(isbn);
				String mensajePanel = "Libro";
				String mensajeMostrar= libroEncontrado.toString();
				JOptionPane.showMessageDialog(null, mensajeMostrar, mensajePanel, JOptionPane.INFORMATION_MESSAGE);
			}
			else if(!libroExiste && isbn !=null) {
				JOptionPane.showMessageDialog(null, "No se ha encontrado el libro", "ERROR",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});
	}
	
	public void addComportamientoGuardarPanelCentralActual() {
        getPanelCentral().addChangeListener(e->{
        	
                if(getPanelLibro().isVisible()) {
                    
                    getBtnSave().setVisible(true);
                    getBtnConsultar().setVisible(true);
                }else if(getPanelLibreria().isVisible()){
                    
                    getBtnSave().setVisible(false);
                    getBtnConsultar().setVisible(false);
                }else if(getPanelComprarVender().isVisible()){
                	
                    getBtnSave().setVisible(false);
                    getBtnConsultar().setVisible(false);
                }
                actualSelectedJPanel = getPanelLibreria();
            }
        );
	}
    
	

	private boolean estanTodosCamposLlenos() {
		if(getTextISBN().getText()!=null && getTextTItulo().getText()!=null && getTextAutor().getText()!=null
				&& getTextEditorial().getText() != null && getPanelFormato().getTextRButtonSelected() != null
				&& getPanelEstado().getTextRButtonSelected() != null) {
			return true;
		}
		return false;
	}

	public Libreria getLibreria() {
		return libreria;
	}
	
	
}
