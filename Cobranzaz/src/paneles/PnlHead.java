package paneles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.*;

import recursos.Utilidades;
import vistas.ModuloGeneral;

public class PnlHead extends JPanel {

	private ModuloGeneral moduloActual;
	private JLabel lblLogoImg;


	private URL url = getClass().getResource("/assets/imagen 71.png");
	private Image image = new ImageIcon(url).getImage();
	private JLabel lblModulo;

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		int myWith=this.getWidth()-90-10;
		lblModulo.setBounds(myWith, 9, 133, 98);
		setOpaque(false);
		super.paint(g);
	}

	public PnlHead(String rutaIcon, ModuloGeneral moduloActual) {
		this.moduloActual = moduloActual;
		this.setLayout(null);

		lblLogoImg = new JLabel();

		lblLogoImg.setBounds(10, 9, 115, 90);
		lblLogoImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLogoImg.setIcon(Utilidades.renderImage("/assets/logoParamoSoft2.png", 115, 100));
		this.add(lblLogoImg);

		JLabel lblNewLabel = new JLabel("SOFTWARE");
		lblNewLabel.setForeground(new Color(214, 30, 38));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(135, 20, 225, 14);
		this.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("DE GESTÓN DE");
		lblNewLabel_1.setForeground(new Color(214, 30, 38));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(135, 45, 225, 14);
		this.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("COBRANZA PARAMOSOFT");
		lblNewLabel_2.setForeground(new Color(214, 30, 38));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(135, 70, 268, 14);
		this.add(lblNewLabel_2);

		lblModulo = new JLabel();



		ImageIcon imgUusariosRender = Utilidades.renderImage(rutaIcon, 90, 90);
		lblModulo.setIcon(imgUusariosRender);


		lblLogoImg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (moduloActual != null) {
			this.add(lblModulo);
			lblModulo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblLogoImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					volverInicio();
				}
			});
		}else {
			lblLogoImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JOptionPane.showMessageDialog(null,"Software de gestión de cobranza paramoSoft","Software de gestión de cobranza dice", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}

	}

	public JLabel getLblModulo() {
		return lblModulo;
	}

	public void setLblMoulo(JLabel lblModulo) {
		this.lblModulo = lblModulo;
	}

	protected void volverInicio() {
		Utilidades.volverInicio(moduloActual);
	}

	public JLabel getLblLogoImg() {
		return lblLogoImg;
	}

	public void setLblLogoImg(JLabel lblLogoImg) {
		this.lblLogoImg = lblLogoImg;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
