package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import modelo.repository.Libreria;

public class ControladorTabla {
	private JTable tabla;
	private String seleccionadoIsbnTabla;
	private ParaUI gui;

	public ControladorTabla(JTable tabla, ParaUI gui) {
		this.tabla = tabla;
		this.gui = gui;
		addComportamientoTabla();
	}

	public void rellenarTabla() {
		tabla.setModel(getLibreria().getFillTableModel());
	}

	public void borrarSeleccionado() {
		if (seleccionadoIsbnTabla != null && getLibreria().existe(seleccionadoIsbnTabla)) {
			String textoMostrar = "SEGURO QUE QUIERES BORRAR EL LIBRO:" + "\n" + "\n"
					+ getLibreria().getLibroByISBN(seleccionadoIsbnTabla) + "\n" + "\n";
			int resultado = JOptionPane.showConfirmDialog(gui, textoMostrar, "BORRADO", JOptionPane.WARNING_MESSAGE,
					JOptionPane.YES_NO_OPTION);
			if (resultado == JOptionPane.OK_OPTION) {
				getLibreria().removeLibroByIsbn(seleccionadoIsbnTabla);
				rellenarTabla();
			}
		}
	}

	public void addComportamientoTabla() {
		this.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		MouseListener[] allMouseListeners = this.tabla.getMouseListeners();
		this.tabla.removeMouseListener(allMouseListeners[allMouseListeners.length - 1]);// remover el listener por
																						// default

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
					JMenuItem itemBorrar = new JMenuItem("Borrar");
					JMenuItem itemEditar = new JMenuItem("Editar");
					JMenuItem itemCompraVenta = new JMenuItem("Comprar o vender");

					itemBorrar.addActionListener(eventoBorrar -> borrarSeleccionado());

					popup.add(itemCompraVenta);
					popup.add(itemEditar);
					popup.add(itemBorrar);
					popup.show(e.getComponent(), e.getX(), e.getY());

				}

			}
		});

	}

	private Libreria getLibreria() {
		return gui.getLibreria();
	}
}
