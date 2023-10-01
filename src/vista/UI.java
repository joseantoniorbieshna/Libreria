package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;

import controlador.PanelRadioButton;
import modelo.data.Libro;

import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textISBN;
	private JTextField textTItulo;
	private JTextField textAutor;
	private JTextField textEditorial;
	private JTextField textPrecio;
	private JButton btnSave;
	private JButton btnExit;
	private JTable tableLibrary;
	private JPanel panelLibreria;
	private JButton btnDelete;
	private JButton btnConsultar;
	private PanelRadioButton panelFormato;
	private PanelRadioButton panelEstado;
	private JPanel panelLibro;
	private JTabbedPane panelCentral;
	private JPanel panelComprarVender;
	private JLabel lblPhoto;
	private PanelRadioButton jPanelButtonCompraVenta;
	private JTextField textIsbnCompraVenta;
	private JTextField textTituloCompraVenta;
	private JLabel textPrecioCompraVenta;
	private JLabel lblTotalCompraVenta;
	private JLabel lblNewLabel;
	private JSpinner spinnerNumeroArticulos;
	private JLabel textCantidadCompraVenta;
	private JButton btnConfirmar;
	private JSpinner spinnerCantidadLibro;


	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(248, 203, 166));
		panelSuperior.setForeground(new Color(0, 0, 0));
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		
		JLabel LabelTItulo = new JLabel("LIBRERIA");
		LabelTItulo.setForeground(new Color(0, 0, 0));
		LabelTItulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSuperior.add(LabelTItulo);
		
		panelCentral = new JTabbedPane(JTabbedPane.TOP);
		panelCentral.setBackground(new Color(255, 251, 235));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		panelLibro = new JPanel();
		panelLibro.setBackground(new Color(255, 251, 235));
		panelCentral.addTab("Libro", null, panelLibro, null);
		panelLibro.setLayout(new MigLayout("", "[20%,grow][40%,grow][40%,grow]", "[12.5%][12.5%][12.5%][12.5%][12.5%][12.5%][12.5%][12.5%]"));
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setHorizontalAlignment(SwingConstants.CENTER);
		panelLibro.add(lblISBN, "cell 0 0,grow");
		
		textISBN = new JTextField();
		panelLibro.add(textISBN, "cell 1 0,grow");
		textISBN.setColumns(10);
		
		lblPhoto = new JLabel("");
		ImageIcon imagen = resizeIcon(new ImageIcon(UI.class.getResource("/recursos/estanteLibros.png")), 100, 100);
		lblPhoto.setIcon(imagen);
		panelLibro.add(lblPhoto, "cell 2 0 1 5,alignx center,growy");
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelLibro.add(lblTitulo, "cell 0 1,grow");
		
		textTItulo = new JTextField();
		panelLibro.add(textTItulo, "cell 1 1,grow");
		textTItulo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		panelLibro.add(lblAutor, "cell 0 2,grow");
		
		textAutor = new JTextField();
		panelLibro.add(textAutor, "cell 1 2,grow");
		textAutor.setColumns(10);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		panelLibro.add(lblEditorial, "cell 0 3,grow");
		
		textEditorial = new JTextField();
		panelLibro.add(textEditorial, "cell 1 3,grow");
		textEditorial.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		panelLibro.add(lblPrecio, "cell 0 4,grow");
		
		textPrecio = new JTextField();
		panelLibro.add(textPrecio, "cell 1 4,grow");
		textPrecio.setColumns(10);
		
		JLabel formato = new JLabel("Formato");
		formato.setHorizontalAlignment(SwingConstants.CENTER);
		panelLibro.add(formato, "cell 0 5,grow");
		
		
		panelFormato = new PanelRadioButton(Libro.FORMATOS);
		panelFormato.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLibro.add(panelFormato, "cell 1 5 2 1,grow");
		
		JLabel lblEstado = new JLabel("Estado:");
		panelLibro.add(lblEstado, "cell 0 6,alignx center,growy");
		
		panelEstado = new PanelRadioButton(Libro.ESTADOS);
		panelLibro.add(panelEstado, "cell 1 6 2 1,grow");
		
		lblNewLabel = new JLabel("Cantidad");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelLibro.add(lblNewLabel, "cell 0 7,grow");
		
		spinnerCantidadLibro = new JSpinner();
		spinnerCantidadLibro.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
		spinnerCantidadLibro.setPreferredSize(new Dimension(50,25));
		panelLibro.add(spinnerCantidadLibro, "cell 1 7");
		
		
		panelLibreria = new JPanel();
		panelCentral.addTab("Libreria", null, panelLibreria, null);
		panelLibreria.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelLibreria.add(scrollPane);
		
		tableLibrary = new JTable(ALLBITS, 5);
		scrollPane.setViewportView(tableLibrary);
		
		panelComprarVender = new JPanel();
		panelCentral.addTab("Compra y venta", null, panelComprarVender, null);
		panelComprarVender.setLayout(new MigLayout("", "[50%,grow][50%,grow][]", "[28px][28px][28px][28px][28px][28px][28px]"));
		
		JLabel lblIsbnCompraVenta = new JLabel("ISBN");
		lblIsbnCompraVenta.setHorizontalAlignment(SwingConstants.CENTER);
		panelComprarVender.add(lblIsbnCompraVenta, "cell 0 0,grow");
		
		textIsbnCompraVenta = new JTextField();
		textIsbnCompraVenta.setHorizontalAlignment(SwingConstants.CENTER);
		textIsbnCompraVenta.setEditable(false);
		textIsbnCompraVenta.setText("-");
		panelComprarVender.add(textIsbnCompraVenta, "cell 1 0,grow");
		
		JLabel lblTituloCompraVenta = new JLabel("Titulo");
		lblTituloCompraVenta.setHorizontalAlignment(SwingConstants.CENTER);
		panelComprarVender.add(lblTituloCompraVenta, "cell 0 1,grow");
		
		textTituloCompraVenta = new JTextField();
		textTituloCompraVenta.setHorizontalAlignment(SwingConstants.CENTER);
		textTituloCompraVenta.setText("-");
		textTituloCompraVenta.setEditable(false);
		panelComprarVender.add(textTituloCompraVenta, "cell 1 1,grow");
		textTituloCompraVenta.setColumns(10);
		
		JLabel lblPrecioCompraYVenta = new JLabel("Precio");
		lblPrecioCompraYVenta.setHorizontalAlignment(SwingConstants.CENTER);
		panelComprarVender.add(lblPrecioCompraYVenta, "cell 0 2,grow");
		
		textPrecioCompraVenta = new JLabel("-");
		textPrecioCompraVenta.setHorizontalAlignment(SwingConstants.CENTER);
		panelComprarVender.add(textPrecioCompraVenta, "cell 1 2,grow");
		
		JLabel lblCantidadCompraVenta = new JLabel("Cantidad:");
		lblCantidadCompraVenta.setHorizontalAlignment(SwingConstants.CENTER);
		panelComprarVender.add(lblCantidadCompraVenta, "cell 0 3,grow");
		
		textCantidadCompraVenta = new JLabel("-");
		textCantidadCompraVenta.setHorizontalAlignment(SwingConstants.CENTER);
		panelComprarVender.add(textCantidadCompraVenta, "cell 1 3,grow");
		
		jPanelButtonCompraVenta = new PanelRadioButton(new String[]{"Vender","Comprar"});
		jPanelButtonCompraVenta.setSelectByText("Vender");
		
		panelComprarVender.add(jPanelButtonCompraVenta, "cell 0 5 2 1,grow");
		
		JLabel lblNumeroCompraVenta = new JLabel("N\u00FAmero de articulos");
		panelComprarVender.add(lblNumeroCompraVenta, "cell 0 4,alignx center");
		
		spinnerNumeroArticulos =  new JSpinner();
		spinnerNumeroArticulos.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
		spinnerNumeroArticulos.setPreferredSize(new Dimension(50,25));
		panelComprarVender.add(spinnerNumeroArticulos, "cell 1 4,alignx left");
		
		JLabel textTotal = new JLabel("TOTAL:");
		textTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panelComprarVender.add(textTotal, "cell 0 6,grow");
		
		lblTotalCompraVenta = new JLabel("-");
		lblTotalCompraVenta.setHorizontalAlignment(SwingConstants.CENTER);
		panelComprarVender.add(lblTotalCompraVenta, "cell 1 6,grow");
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(255, 231, 166));
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		btnSave = new JButton("Guardar");
		panelInferior.add(btnSave);
		
		btnConsultar = new JButton("Consultar");
		panelInferior.add(btnConsultar);
		
		btnConfirmar = new JButton("Confirmar");
		panelInferior.add(btnConfirmar);
		
		btnDelete = new JButton("Borrar");
		panelInferior.add(btnDelete);
		
		btnExit = new JButton("Salir");
		panelInferior.add(btnExit);
		
		
	}
	
	private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
	
	public void vaciarCamposLibro() {
		textISBN.setText("");
		textAutor.setText("");
		textTItulo.setText("");
		textPrecio.setText("");
		textEditorial.setText("");
		panelFormato.deselectedAll();
		panelEstado.deselectedAll();
		spinnerCantidadLibro.setValue(0);
	}

	
	public void vaciarCamposCompraVenta() {
		getTextIsbnCompraVenta().setText("-");
		getTextPrecioCompraVenta().setText("-");
		getLblTotalCompraVenta().setText("-");
		getTextCantidadCompraVenta().setText("-");
		getTextTituloCompraVenta().setText("-");
		getSpinnerNumeroArticulos().setValue(0);
	}

	protected JTextField getTextISBN() {
		return textISBN;
	}


	protected JTextField getTextTItulo() {
		return textTItulo;
	}


	protected JTextField getTextAutor() {
		return textAutor;
	}


	protected JTextField getTextEditorial() {
		return textEditorial;
	}


	protected JTextField getTextPrecio() {
		return textPrecio;
	}


	protected JButton getBtnSave() {
		return btnSave;
	}


	protected JButton getBtnExit() {
		return btnExit;
	}


	protected JTable getTableLibrary() {
		return tableLibrary;
	}


	protected JPanel getPanelLibreria() {
		return panelLibreria;
	}


	protected JButton getBtnDelete() {
		return btnDelete;
	}


	protected JButton getBtnConsultar() {
		return btnConsultar;
	}


	protected PanelRadioButton getPanelFormato() {
		return panelFormato;
	}


	protected PanelRadioButton getPanelEstado() {
		return panelEstado;
	}


	protected JPanel getPanelLibro() {
		return panelLibro;
	}


	protected JTabbedPane getPanelCentral() {
		return panelCentral;
	}

	protected PanelRadioButton getJpanelButtonCompraVenta() {
		return jPanelButtonCompraVenta;
	}

	protected JTextField getTextIsbnCompraVenta() {
		return textIsbnCompraVenta;
	}

	protected JLabel getTextPrecioCompraVenta() {
		return textPrecioCompraVenta;
	}

	protected JLabel getLblTotalCompraVenta() {
		return lblTotalCompraVenta;
	}

	protected JPanel getPanelComprarVender() {
		return panelComprarVender;
	}

	protected JTextField getTextTituloCompraVenta() {
		return textTituloCompraVenta;
	}

	protected JSpinner getSpinnerNumeroArticulos() {
		return spinnerNumeroArticulos;
	}

	protected JLabel getTextCantidadCompraVenta() {
		return textCantidadCompraVenta;
	}

	public JButton getBtnConfirmar() {
		return btnConfirmar;
	}

	public JSpinner getSpinnerCantidadLibro() {
		return spinnerCantidadLibro;
	}
	
	
	
	
	
}
