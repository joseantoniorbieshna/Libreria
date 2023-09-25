package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Libro;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.ImageIcon;

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
		panelLibro.setLayout(new MigLayout("insets 1%,gap rel 0.5%", "[20%,grow][40%,grow][40%,grow]", "[28px][28px][28px][28px][28px][28px][28px][28px][28px]"));
		
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
		panelLibro.add(formato, "cell 0 5 1 2,grow");
		
		
		panelFormato = new PanelRadioButton(Libro.FORMATOS);
		panelFormato.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLibro.add(panelFormato, "cell 1 5 2 2,growx,aligny center");
		
		JLabel lblEstado = new JLabel("Estado:");
		panelLibro.add(lblEstado, "cell 0 7 1 2,alignx center,growy");
		
		panelEstado = new PanelRadioButton(Libro.ESTADOS);
		panelLibro.add(panelEstado, "cell 1 7 2 2,growx,aligny center");
		
		
		panelLibreria = new JPanel();
		panelCentral.addTab("Libreria", null, panelLibreria, null);
		panelLibreria.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelLibreria.add(scrollPane);
		
		tableLibrary = new JTable(ALLBITS, 5);
		scrollPane.setViewportView(tableLibrary);
		
		panelComprarVender = new JPanel();
		panelCentral.addTab("Compra y venta", null, panelComprarVender, null);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(255, 231, 166));
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		btnSave = new JButton("Guardar");
		panelInferior.add(btnSave);
		
		btnConsultar = new JButton("Consulatar");
		panelInferior.add(btnConsultar);
		
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
	
	public void vaciarCampos() {
		textISBN.setText("");
		textAutor.setText("");
		textTItulo.setText("");
		textPrecio.setText("");
		textEditorial.setText("");
		panelFormato.deselectedAll();
		panelEstado.deselectedAll();
	}


	public JTextField getTextISBN() {
		return textISBN;
	}


	public JTextField getTextTItulo() {
		return textTItulo;
	}


	public JTextField getTextAutor() {
		return textAutor;
	}


	public JTextField getTextEditorial() {
		return textEditorial;
	}


	public JTextField getTextPrecio() {
		return textPrecio;
	}


	public JButton getBtnSave() {
		return btnSave;
	}


	public JButton getBtnExit() {
		return btnExit;
	}


	public JTable getTableLibrary() {
		return tableLibrary;
	}


	public JPanel getPanelLibreria() {
		return panelLibreria;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}


	public JButton getBtnConsultar() {
		return btnConsultar;
	}


	public PanelRadioButton getPanelFormato() {
		return panelFormato;
	}


	public PanelRadioButton getPanelEstado() {
		return panelEstado;
	}


	public JPanel getPanelLibro() {
		return panelLibro;
	}


	public JTabbedPane getPanelCentral() {
		return panelCentral;
	}
	
	
	
	
}
