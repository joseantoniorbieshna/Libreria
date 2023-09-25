package controlador;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.Libro;
import vista.UI;

public class ParaUI extends UI {
	private Libreria libreria = new Libreria();
	
	private ControlerTextFields controlerTextField;
	private ControladorTabla controladorTabla;

	private JPanel actualSelectedJPanel = getPanelLibreria();

	public ParaUI() {
		controlerTextField = new ControlerTextFields();
		controladorTabla = new ControladorTabla(getTableLibrary(), this);
		
		controlerTextField.annadirComportamientoTextoIsbn(getTextISBN());
		controlerTextField.annadirComportamientoTextoNumeroReal(getTextPrecio());
		controlerTextField.annadirComportamientoTextoSinNumerosYSimbolos(getTextAutor());
		controladorTabla.rellenarTabla();
		
		
		
		addComportamientoGuardar();
		addComportamientoConsultar();
		addComportamientoBorrar();
		addComportamientoSalir();
		addBehaviourActualLabel();
	}


	public void addComportamientoGuardar() {
		this.getBtnSave().addActionListener(e -> {
			if (estanTodosCamposLlenos()) {
				Libro book = new Libro(getTextISBN().getText(), getTextTItulo().getText(), getTextAutor().getText(),
						getTextEditorial().getText(), Float.parseFloat(getTextPrecio().getText()),getPanelFormato().getTextRButtonSelected());
				libreria.addLibro(book);
				vaciarCampos();
				controladorTabla.rellenarTabla();
			}
		});
	}



	public void addComportamientoSalir() {
		this.getBtnExit().addActionListener(e -> dispose());
	}

	public void addComportamientoBorrar() {
			this.getBtnDelete().addActionListener(e ->{
				if(actualSelectedJPanel.equals(getPanelLibreria())) {
					controladorTabla.borrarSeleccionado();
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
	
	public void addBehaviourActualLabel() {
        getPanelCentral().addChangeListener(e->{
                if(getPanelLibro().isVisible()) {
                    actualSelectedJPanel = getPanelLibro();
                    
                    getBtnSave().setVisible(true);
                    getBtnConsultar().setVisible(true);
                }else if(getPanelLibreria().isVisible()){
                    actualSelectedJPanel = getPanelLibreria();
                    
                    getBtnSave().setVisible(false);
                    getBtnConsultar().setVisible(false);
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
