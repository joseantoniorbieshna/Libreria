package servicios;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controlador.ParaUI;
import modelo.repository.Libreria;

public class ServiceTabla {
	private JTable tabla;
	private String seleccionadoIsbnTabla;
	private ParaUI gui;
	JMenuItem itemBorrar;
	JMenuItem itemCopiarIsbn;
	JMenuItem itemEditar;
	JMenuItem itemCompraVenta;

	public ServiceTabla(JTable tabla, ParaUI gui) {
		this.tabla = tabla;
		this.gui = gui;
		addComportamientoTabla();
		itemCopiarIsbn = new JMenuItem("Copiar isbn");
		itemBorrar = new JMenuItem("Borrar");
		itemEditar = new JMenuItem("Editar");
		itemCompraVenta = new JMenuItem("Comprar o vender");
		
		addComportamientoCopiarISBN();
	}

	public void rellenarTabla() {
		tabla.setModel(getLibreria().getFillTableModel());
	}

	public void borrarSeleccionsado() {
			getLibreria().removeLibroByIsbn(seleccionadoIsbnTabla);
	}

	public void addComportamientoTabla() {
		this.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		MouseListener[] allMouseListeners = this.tabla.getMouseListeners();
		this.tabla.removeMouseListener(allMouseListeners[allMouseListeners.length - 1]);// remover el listener por
		
		
		this.tabla.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				Integer position = tabla.rowAtPoint(e.getPoint());// posicion
				
				if (position != -1) {
					tabla.setRowSelectionInterval(position, position);// selección
					tabla.setColumnSelectionInterval(0, tabla.getColumnCount() - 1);
				}
				
				if (tabla.getSelectedColumn() != -1 && tabla.getSelectedRow() != -1 && e.getButton() == 3) {

					seleccionadoIsbnTabla = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
					
					JPopupMenu popup = new JPopupMenu("Popup");

					
					popup.add(itemCopiarIsbn);
					popup.add(itemCompraVenta);
					popup.add(itemEditar);
					popup.add(itemBorrar);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

	}
	
	private void addComportamientoCopiarISBN() {
		itemCopiarIsbn.addActionListener(elemento->{
			StringSelection strSel = new StringSelection(seleccionadoIsbnTabla);
	        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	        cb.setContents(strSel, null);
		});
	}

	public JMenuItem getItemBorrar() {
		return itemBorrar;
	}

	public JMenuItem getItemEditar() {
		return itemEditar;
	}

	public JMenuItem getItemCompraVenta() {
		return itemCompraVenta;
	}

	public String getSeleccionadoIsbnTabla() {
		return seleccionadoIsbnTabla;
	}

	private Libreria getLibreria() {
		return gui.getLibreria();
	}
}
