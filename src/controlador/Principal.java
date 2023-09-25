package controlador;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Principal {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaUI frame = new ParaUI();
					frame.setVisible(true);
					
					frame.setTitle("Libreria J.A Ramos");
					
					/*Crear imagen*/
					BufferedImage image= ImageIO.read(new File("src/recursos/librosico.png"));
					frame.setIconImage(image);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
