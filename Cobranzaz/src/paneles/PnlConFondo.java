package paneles;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author paramo
 */
public class PnlConFondo extends JPanel {

	private URL url;
	Image image;
	
	public PnlConFondo(String rutaFondo) {
		url = getClass().getResource(rutaFondo);
		image = new ImageIcon(url).getImage();
		//url = getClass().getResource("/assets/imagen 70.png");
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}
}
