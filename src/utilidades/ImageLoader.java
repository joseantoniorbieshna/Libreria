package utilidades;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javafx.scene.image.Image;

public class ImageLoader {
	public static ImageIcon getimageByPath(String path) {
		ImageIcon  image=null;
		image = new ImageIcon(path);
		return image;
	}
}
