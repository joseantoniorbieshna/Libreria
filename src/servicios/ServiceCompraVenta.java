package servicios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;

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

	private Integer cantidadOperacion = 0;

	public ServiceCompraVenta(JTextField textFieldIsbn, JTextField textFieldTitulo, JLabel lblPrecio,
			JSpinner spinnerNumeroArticulos, PanelRadioButton panelCompraVenta, JLabel lblTotal, JLabel lblCantidad) {
		super();
		this.textFieldIsbn = textFieldIsbn;
		this.textFieldTitulo = textFieldTitulo;
		this.lblPrecio = lblPrecio;
		this.spinnerNumeroArticulos = spinnerNumeroArticulos;
		this.panelEleccionCompraVenta = panelCompraVenta;
		this.lblTotal = lblTotal;
		this.lblCantidad = lblCantidad;
		annadirComportamientoSpinner();

		panelEleccionCompraVenta.addListener(getComportamientoPanelEleccion());
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
		cantidadOperacion = 0;

	}

	public ActionListener getComportamientoPanelEleccion() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerNumeroArticulos.setValue(0);
			}
		};

	}

	public void annadirComportamientoSpinner() {
		getSpinnerNumeroArticulos().addChangeListener(e -> {
			Integer valorInput = Integer.parseInt(this.spinnerNumeroArticulos.getValue().toString());
			
			if (hayLibroSeleccionado() && panelEleccionCompraVenta.getRButtonSelected() != null) {
				Integer cantidadLibro = libro.getCantidad();

				
				if (valorInput <= cantidadLibro && panelEleccionCompraVenta.esEste(OPT_VENTA)) {
					cambiarValoresPanelPorCantidad(valorInput,true);
					this.cantidadOperacion = -valorInput;
				} else if (panelEleccionCompraVenta.esEste(OPT_VENTA)) {
					getSpinnerNumeroArticulos().setValue(cantidadLibro);
				}else if(panelEleccionCompraVenta.esEste(OPT_COMPRA)) {
					this.cantidadOperacion = valorInput;
					cambiarValoresPanelPorCantidad(valorInput,false);
				}
				

			} else {
				getSpinnerNumeroArticulos().setValue(0);
				this.cantidadOperacion = 0;
			}

		});
	}

	private void cambiarValoresPanelPorCantidad(Integer valorInput, boolean isVenta) {
		Integer valor = isVenta?-valorInput:valorInput;
		String cantidadTexto = String.valueOf(libro.getCantidad() +(valor));

		getSpinnerNumeroArticulos().setValue(valorInput);
		lblCantidad.setText(cantidadTexto);
		String textoPrecioTotal = String.format("%.2f", libro.getPrecio() * valorInput);

		lblTotal.setText(textoPrecioTotal);
	}
	public void cambiarCantidadLibro() {
		if (hayLibroSeleccionado()) {
			Libro libro = getLibro();
			Integer cantidad = libro.getCantidad() + getCantidadOperacion();
			libro.setCantidad(cantidad);
		}
	}

	public boolean hayLibroSeleccionado() {
		return libro != null;
	}

	public void quitarLibro() {
		this.libro = null;
	}

	private JTextField getTextFieldIsbn() {
		return textFieldIsbn;
	}

	private JTextField getTextFieldTitulo() {
		return textFieldTitulo;
	}

	private JLabel getLblPrecio() {
		return lblPrecio;
	}

	private JSpinner getSpinnerNumeroArticulos() {
		return spinnerNumeroArticulos;
	}

	private PanelRadioButton getPanelCompraVenta() {
		return panelEleccionCompraVenta;
	}

	private JLabel getLblTotal() {
		return lblTotal;
	}

	private JLabel getLblCantidad() {
		return lblCantidad;
	}

	public Libro getLibro() {
		return libro;
	}

	public Integer getCantidadOperacion() {
		return cantidadOperacion;
	}

}
