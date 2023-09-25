package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import modelo.Libro;
import utilidades.Validators;
import vista.UI;

public class ParaUI extends UI {
	private Libreria libreria = new Libreria();
	private JPanel actualSelectedJPanel = panelLibro;
	private static int LONGITUD_ISBN=13;
	private String seleccionadoIsbnTabla;

	public ParaUI() {
		addComportamientoGuardar();
		addComportamientoConsultar();
		addComportamientoBorrar();
		addComportamientoSalir();
		rellenarTabla();
		
		comportamientoTextoIsbn(textISBN);
		comportamientoTextoNumeroReal(textPrecio);
		comportamientoTextoSinNumerosYSimbolos(textAutor);
		
		addBehaviourActualLabel();
		addComportamientoTabla();
	}


	public void addComportamientoGuardar() {
		this.btnSave.addActionListener(e -> {
			if (estanTodosCamposLlenos()) {
				Libro book = new Libro(textISBN.getText(), textTItulo.getText(), textAutor.getText(),
						textEditorial.getText(), Float.parseFloat(textPrecio.getText()),panelFormato.getTextRButtonSelected());
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
			this.btnDelete.addActionListener(e ->{
				if(actualSelectedJPanel.equals(panelLibreria)) {
					borrarSeleccionado();
				}else {
					System.out.println("Borrar en libro");
					vaciarCampos();					
				}
				
			});						
		}
	
	public void comportamientoTextoIsbn(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				StringBuilder texto = new StringBuilder(textField.getText());
				texto.append(e.getKeyChar());
				if(!Validators.isNaturalNumber(texto.toString()) || texto.length()>LONGITUD_ISBN) {
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
	
	public void comportamientoTextoSinNumerosYSimbolos(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				StringBuilder texto = new StringBuilder(textField.getText());
				texto.append(e.getKeyChar());
				if( !Validators.isPhraseWithoutSymbolsAndNumber(texto.toString())) {
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
        panelCentral.addChangeListener(e->{
                if(panelLibro.isVisible()) {
                    actualSelectedJPanel = panelLibro;
                    
                    btnSave.setVisible(true);
                    btnConsultar.setVisible(true);
                }else if(panelLibreria.isVisible()){
                    actualSelectedJPanel = panelLibreria;
                    
                    btnSave.setVisible(false);
                    btnConsultar.setVisible(false);
                }
            }
        );
    }
	
	
	private void borrarSeleccionado(){
		if(seleccionadoIsbnTabla!=null && libreria.existe(seleccionadoIsbnTabla)){
			String textoMostrar = "Seguro que quieres borrar el libro con los datos:"+"\n"+libreria.getLibroByISBN(seleccionadoIsbnTabla);
			 int resultado = JOptionPane.showConfirmDialog(this, 
					 textoMostrar,"BORRADO",
					 JOptionPane.WARNING_MESSAGE,
					 JOptionPane.YES_NO_OPTION);
			if(resultado==JOptionPane.OK_OPTION) {
				libreria.removeLibroByIsbn(seleccionadoIsbnTabla);
				rellenarTabla();				
			}
		}
	}
	
	public void addComportamientoTabla() {
		tableLibrary.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				Integer position = tableLibrary.rowAtPoint(e.getPoint());//posicion
				tableLibrary.setRowSelectionInterval(position, position);// selección
				
				seleccionadoIsbnTabla = tableLibrary.getValueAt(tableLibrary.getSelectedRow(), 0).toString();
				if(e.getButton()==3) {
					JPopupMenu popup = new JPopupMenu("Popup");
					JMenuItem itemBorrar = new JMenuItem("Borrar");
					
					itemBorrar.addActionListener(eventoBorrar->borrarSeleccionado());
					
					popup.add(itemBorrar);
                    popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
	
	private boolean estanTodosCamposLlenos() {
		if(textISBN.getText()!=null && textTItulo.getText()!=null && textAutor.getText()!=null
				&& textEditorial.getText() != null && panelFormato.getTextRButtonSelected() != null
				&& panelEstado.getTextRButtonSelected() != null) {
			return true;
		}
		return false;
	}
	
}
