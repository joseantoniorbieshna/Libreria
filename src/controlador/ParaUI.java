package controlador;



import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.data.Libro;
import modelo.repository.Libreria;
import vista.UI;

public class ParaUI extends UI {
	private Libreria libreria = new Libreria();
	
	private ControladorTextFields controlerTextField;
	private ControladorTabla controladorTabla;
	private ControladorCompraVenta controladorCompraVenta;

	private JPanel actualSelectedJPanel = getPanelLibreria();

	public ParaUI() {
		controlerTextField = new ControladorTextFields();
		controladorTabla = new ControladorTabla(getTableLibrary(), this);
		controladorCompraVenta = new ControladorCompraVenta(this);
		
		controlerTextField.annadirComportamientoTextoIsbn(getTextISBN());
		controlerTextField.annadirComportamientoTextoNumeroReal(getTextPrecio());
		controlerTextField.annadirComportamientoTextoSinNumerosYSimbolos(getTextAutor());
		controladorTabla.rellenarTabla();
		
		
		
		addComportamientoGuardar();
		addComportamientoConsultar();
		addComportamientoBorrar(getBtnDelete());
		addComportamientoBorrar(controladorTabla.getItemBorrar());
		addComportamientoSalir();
		addComportamientoRefrescarCenterLabel();
	}


	public void addComportamientoGuardar() {
		this.getBtnSave().addActionListener(e -> {
			if (estanTodosCamposLlenos() && getTextISBN().getText().length()==13) {
				Libro book = new Libro(getTextISBN().getText(), getTextTItulo().getText(), getTextAutor().getText(),
						getTextEditorial().getText(), Float.parseFloat(getTextPrecio().getText()),getPanelFormato().getTextRButtonSelected(),Libro.FORMATOS[1]);
				libreria.addLibro(book);
				vaciarCampos();
				controladorTabla.rellenarTabla();
			}
		});
	}



	public void addComportamientoSalir() {
		this.getBtnExit().addActionListener(e -> dispose());
	}

	public void addComportamientoBorrar(AbstractButton myComponent) {
		myComponent.addActionListener(e ->{
				if(actualSelectedJPanel.equals(getPanelLibreria())) {
					String isbn= controladorTabla.getSeleccionadoIsbnTabla();
					if(isbn!=null) {
						getLibreria().pedirConfirmacionBorrarPorIsbn(isbn, this);						
					}
				}else {
					vaciarCampos();					
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
	
	public void addComportamientoRefrescarCenterLabel() {
        getPanelCentral().addChangeListener(e->{
                if(getPanelLibro().isVisible()) {
                    actualSelectedJPanel = getPanelLibro();
                    
                    getBtnSave().setVisible(true);
                }else if(getPanelLibreria().isVisible()){
                    actualSelectedJPanel = getPanelLibreria();
                    
                    getBtnSave().setVisible(false);
                }
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
