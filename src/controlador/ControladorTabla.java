package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

public class ControladorTabla {
	private JTable tabla;
	private String seleccionadoIsbnTabla;
	private ParaUI gui;
	private Libreria libreria;
	
	public ControladorTabla(JTable tabla, ParaUI gui) {
		this.tabla=tabla;
		this.gui=gui;
		this.libreria = gui.getLibreria();
		addComportamientoTabla();
	}
	
	public void rellenarTabla() {
		tabla.setModel(libreria.getFillTableModel());
	}
	
	
	public void borrarSeleccionado(){
		if(seleccionadoIsbnTabla!=null && libreria.existe(seleccionadoIsbnTabla)){
			String textoMostrar = "SEGURO QUE QUIERES BORRAR EL LIBRO:"+"\n"+"\n"+libreria.getLibroByISBN(seleccionadoIsbnTabla)+"\n"+"\n";
			 int resultado = JOptionPane.showConfirmDialog(gui, 
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
		this.tabla.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				Integer position = tabla.rowAtPoint(e.getPoint());//posicion
				tabla.setRowSelectionInterval(position, position);// selección
				
				seleccionadoIsbnTabla = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
				if(e.getButton()==3) {
					JPopupMenu popup = new JPopupMenu("Popup");
					JMenuItem itemBorrar = new JMenuItem("Borrar");
					JMenuItem itemEditar = new JMenuItem("Editar");
					JMenuItem itemCompraVenta = new JMenuItem("Comprar o vender");
					
					itemBorrar.addActionListener(eventoBorrar->borrarSeleccionado());
					
					popup.add(itemCompraVenta);
					popup.add(itemEditar);
					popup.add(itemBorrar);
                    popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
}
