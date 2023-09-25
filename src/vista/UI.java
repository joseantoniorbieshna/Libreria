package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Libro;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;

import controlador.PanelRadioButton;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextField textISBN;
	protected JTextField textTItulo;
	protected JTextField textAutor;
	protected JTextField textEditorial;
	protected JTextField textPrecio;
	protected JButton btnSave;
	protected JButton btnExit;
	protected JTable tableLibrary;
	protected JPanel panelLibreria;
	protected JButton btnDelete;
	protected JButton btnConsultar;
	protected JTextField textFormato;
	protected PanelRadioButton panelFormato;
	protected PanelRadioButton panelEstado;
	protected JPanel panelLibro;
	protected JTabbedPane panelCentral;
	private JPanel panelComprarVender;


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
		panelSuperior.setBackground(new Color(0, 255, 0));
		panelSuperior.setForeground(new Color(0, 0, 0));
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		
		JLabel LabelTItulo = new JLabel("LIBRERIA");
		LabelTItulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSuperior.add(LabelTItulo);
		
		panelCentral = new JTabbedPane(JTabbedPane.TOP);
		panelCentral.setBackground(new Color(241, 228, 158));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		panelLibro = new JPanel();
		panelLibro.setBackground(new Color(241, 228, 158));
		panelCentral.addTab("Libro", null, panelLibro, null);
		panelLibro.setLayout(new MigLayout("insets 1%,gap rel 0.5%", "[20%,grow][40%,grow][40%,grow]", "[28px][28px][28px][28px][28px][28px][28px][28px][28px]"));
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setHorizontalAlignment(SwingConstants.CENTER);
		panelLibro.add(lblISBN, "cell 0 0,grow");
		
		textISBN = new JTextField();
		panelLibro.add(textISBN, "cell 1 0,grow");
		textISBN.setColumns(10);
		
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
		panelCentral.setBackgroundAt(1, new Color(241, 228, 158));
		
		panelComprarVender = new JPanel();
		panelCentral.addTab("Compra y venta", null, panelComprarVender, null);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(new Color(255, 91, 91));
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
	
	
	public void vaciarCampos() {
		textISBN.setText("");
		textAutor.setText("");
		textTItulo.setText("");
		textPrecio.setText("");
		textEditorial.setText("");
		panelFormato.deselectedAll();
		panelEstado.deselectedAll();
	}
}
