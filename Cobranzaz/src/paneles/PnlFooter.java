package paneles;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import recursos.Utilidades;
import vistas.InfoView;
import vistas.Inicio;

public class PnlFooter extends JPanel {

	private JLabel desarrollo;
	
	
	private URL url = getClass().getResource("/assets/imagen 71.png");
	Image image = new ImageIcon(url).getImage();

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}

	public PnlFooter() {
		//setBackground(Color.BLACK);
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		//flowLayout.setVgap(5);
		desarrollo = new JLabel("Software de gestión de cobranza - © - All rights reserved - 2020 - ");
		desarrollo.setForeground(Color.BLACK);
		desarrollo.setFont(new Font("Cambria", Font.BOLD, 14));
		desarrollo.setHorizontalAlignment(SwingConstants.RIGHT);
		desarrollo.setHorizontalTextPosition(SwingConstants.LEFT);
		
		desarrollo.setIcon(Utilidades.renderImage("/assets/logoParamoSoft.png", 130, 20));
		this.add(desarrollo);
		desarrollo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		desarrollo.setToolTipText("Información del sistema y soporte técnico");
		desarrollo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new InfoView().setVisible(true);


			}
		});
	}
}
