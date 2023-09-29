package servicios;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import controlador.PanelRadioButton;
import controlador.ParaUI;
import modelo.data.Libro;
import utilidades.Validators;

public class ServiceCompraVenta {

	public final String[] VALORES = { "Vender", "Comprar" };
	private final String OPT_VENTA = VALORES[0];
	private final String OPT_COMPRA = VALORES[1];

	private JTextField textFieldIsbn;
	private JTextField textFieldTitulo;
	private JLabel lblPrecio;
	private JSpinner spinnerNumeroArticulos;
	private PanelRadioButton panelEleccionCompraVenta;
	private JLabel lblTotal;
	private JLabel lblCantidad;

	private Libro libro;
	private JButton btnEditar;

	public ServiceCompraVenta(JTextField textFieldIsbn, JTextField textFieldTitulo, JLabel lblPrecio,
			JSpinner spinnerNumeroArticulos, PanelRadioButton panelCompraVenta, JLabel lblTotal,JLabel lblCantidad) {
		super();
		this.textFieldIsbn = textFieldIsbn;
		this.textFieldTitulo = textFieldTitulo;
		this.lblPrecio = lblPrecio;
		this.spinnerNumeroArticulos = spinnerNumeroArticulos;
		this.panelEleccionCompraVenta = panelCompraVenta;
		this.lblTotal = lblTotal;
		this.lblCantidad = lblCantidad;
		annadirComportamientoSpinner();
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
		setValoresConLibro();
	}

	public void setValoresConLibro() {
		getTextFieldIsbn().setText(libro.getISBN());
		getTextFieldTitulo().setText(libro.getTitulo());
		getLblPrecio().setText(libro.getPrecio().toString());
		getLblCantidad().setText(libro.getCantidad().toString());
		getSpinnerNumeroArticulos().setValue(0);

	}

	public void annadirComportamientoSpinner() {
		getSpinnerNumeroArticulos().addChangeListener(e -> {
			Integer valorInput = Integer.parseInt(this.spinnerNumeroArticulos.getValue().toString());
			
			if(hayLibroSeleccionado()) {
				Integer cantidadLibro = libro.getCantidad();
				
				
				if(valorInput<=cantidadLibro && panelEleccionCompraVenta.esEste(OPT_VENTA)) {
					String cantidadTexto = String.valueOf(cantidadLibro-valorInput);
					
					getSpinnerNumeroArticulos().setValue(valorInput);
					lblCantidad.setText(cantidadTexto);
				}else if (!panelEleccionCompraVenta.esEste(OPT_COMPRA)) {
					getSpinnerNumeroArticulos().setValue(valorInput-1);
					
				}
				
			}else {
				getSpinnerNumeroArticulos().setValue(0);
			}
			
		});
	}
	
	public boolean hayLibroSeleccionado() {
		return libro!=null;
	}
	
	public void quitarLibro() {
		this.libro=null;
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

	public JSpinner getSpinnerNumeroArticulos() {
		return spinnerNumeroArticulos;
	}

	public PanelRadioButton getPanelCompraVenta() {
		return panelEleccionCompraVenta;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public Libro getLibro() {
		return libro;
	}
	
	
	

}
