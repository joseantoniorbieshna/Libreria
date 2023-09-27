package controlador;


import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import modelo.data.Libro;

public class ControladorCompraVenta {
	
	public final String [] VALORES = {"Vender","Comprar"};
	
	private JTextField textFieldIsbn;
	private JTextField textFieldTitulo;
	private JLabel lblPrecio;
	private JSpinner numeroArticulosJSpinner;
	private PanelRadioButton panelCompraVenta;
	private JLabel lblTotal;
	
	public ControladorCompraVenta(ParaUI gui) {
		textFieldIsbn = gui.getTextIsbnCompraVenta();
		textFieldTitulo = gui.getTextIsbnCompraVenta();
		lblPrecio = gui.getTextPrecioCompraVenta();
		panelCompraVenta = gui.getJpanelButtonCompraVenta();
		lblTotal = gui.getLblTotalCompraVenta();
	}
	
	public void setValoresConLibro(Libro libro) {
		getTextFieldIsbn().setText(libro.getISBN());
		getTextFieldTitulo().setText(libro.getTitulo());
		getLblPrecio().setText(libro.getPrecio().toString());
		
		getNumeroArticulosJSpinner().addChangeListener(e->{
			System.out.println(e.getSource());
		});
	}

	public JTextField getTextFieldIsbn() {
		return textFieldIsbn;
	}

	public JTextField getTextFieldTitulo() {
		return textFieldTitulo;
	}

	public JLabel getLblPrecio() {
		return lblPrecio;
	}

	public JSpinner getNumeroArticulosJSpinner() {
		return numeroArticulosJSpinner;
	}

	public PanelRadioButton getPanelCompraVenta() {
		return panelCompraVenta;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}
	
	
}
