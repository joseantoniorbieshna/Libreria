package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	protected JPanel panelDerecha;
	protected JButton btnDelete;
	protected JButton btnConsultar;
	protected JLabel Formato;


	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		JTabbedPane panelCentral = new JTabbedPane(JTabbedPane.TOP);
		panelCentral.setBackground(new Color(241, 228, 158));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBackground(new Color(241, 228, 158));
		panelCentral.addTab("Libro", null, panelIzquierda, null);
		panelIzquierda.setLayout(new GridLayout(0, 2, 6, 0));
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setHorizontalAlignment(SwingConstants.CENTER);
		panelIzquierda.add(lblISBN);
		
		textISBN = new JTextField();
		panelIzquierda.add(textISBN);
		textISBN.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelIzquierda.add(lblTitulo);
		
		textTItulo = new JTextField();
		panelIzquierda.add(textTItulo);
		textTItulo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		panelIzquierda.add(lblAutor);
		
		textAutor = new JTextField();
		panelIzquierda.add(textAutor);
		textAutor.setColumns(10);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		panelIzquierda.add(lblEditorial);
		
		textEditorial = new JTextField();
		panelIzquierda.add(textEditorial);
		textEditorial.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		panelIzquierda.add(lblPrecio);
		
		textPrecio = new JTextField();
		panelIzquierda.add(textPrecio);
		textPrecio.setColumns(10);
		
		Formato = new JLabel("Formato");
		Formato.setHorizontalAlignment(SwingConstants.CENTER);
		panelIzquierda.add(Formato);
		
		JTextField textFormato = new JTextField();
		panelIzquierda.add(textFormato);
		textFormato.setColumns(10);
		
		panelDerecha = new JPanel();
		panelCentral.addTab("Libreria", null, panelDerecha, null);
		panelDerecha.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelDerecha.add(scrollPane);
		
		tableLibrary = new JTable(ALLBITS, 5);
		scrollPane.setViewportView(tableLibrary);
		panelCentral.setBackgroundAt(1, new Color(241, 228, 158));
		
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
	}
}
