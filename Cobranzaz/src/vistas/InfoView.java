package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paneles.PnlConFondo;
import recursos.Utilidades;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author paramo
 * Vista con información del sistema y soporte
 */
public class InfoView extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public InfoView() {

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 542);
		setLocationRelativeTo(null);
		contentPane = new PnlConFondo("/assets/negro.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setToolTipText("www.paramosoft.com");
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.setIcon(Utilidades.renderImage("/assets/logoParamoSoft2.png", 235, 196));
		label.setBounds(58, 11, 235, 196);
		contentPane.add(label);

		JLabel buttonHome = new JLabel();
		
		buttonHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		buttonHome.setToolTipText("Volver al inicio");
		buttonHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ImageIcon img = Utilidades.renderImage("/assets/buton2.png", 80, 80);
		buttonHome.setIcon(img);
		buttonHome.setBounds(136, 447, 80, 80);
		contentPane.add(buttonHome);

		JPanel panel = new PnlConFondo("/assets/negro.jpg");
		panel.setForeground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sobre este software",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(10, 216, 330, 72);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSoftwareDeCobranza = new JLabel("Sofware de cobranza Versi\u00F3n 1.0.1");
		ImageIcon img2 = Utilidades.renderImage("/assets/engranes.png", 14, 14);
		lblSoftwareDeCobranza.setBounds(10, 21, 183, 14);
		lblSoftwareDeCobranza.setIcon(img2);
		lblSoftwareDeCobranza.setForeground(Color.WHITE);
		lblSoftwareDeCobranza.setFont(new Font("Cambria", Font.PLAIN, 11));

		panel.add(lblSoftwareDeCobranza);

		JLabel lblLicencia = new JLabel("Licencia:");
		lblLicencia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLicencia.setToolTipText("Detalles de licencia");
		lblLicencia.setIcon(Utilidades.renderImage("/assets/licenceicon.png", 14, 14));
		lblLicencia.setFont(new Font("Cambria", Font.PLAIN, 11));
		lblLicencia.setForeground(Color.WHITE);
		lblLicencia.setBounds(215, 21, 59, 14);
		panel.add(lblLicencia);

		JLabel lblEstadoLicencia = new JLabel("Activa");
		lblEstadoLicencia.setFont(new Font("Cambria", Font.PLAIN, 11));
		lblEstadoLicencia.setForeground(Color.RED);
		lblEstadoLicencia.setBounds(278, 21, 42, 14);
		panel.add(lblEstadoLicencia);

		JLabel lblIdDeProducto = new JLabel("Id de producto:");
		lblIdDeProducto.setForeground(Color.WHITE);
		lblIdDeProducto.setFont(new Font("Cambria", Font.PLAIN, 11));
		lblIdDeProducto.setBounds(10, 46, 96, 14);
		lblIdDeProducto.setIcon(Utilidades.renderImage("/assets/keys.png", 14, 14));
		panel.add(lblIdDeProducto);

		JLabel label_2 = new JLabel("2.020.041.817.542.912");
		label_2.setToolTipText("Copiar Id");
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Cambria", Font.PLAIN, 13));
		label_2.setBounds(105, 46, 215, 14);
		panel.add(label_2);

		JPanel panel_1 = new PnlConFondo("/assets/negro.jpg");
		panel_1.setBorder(new TitledBorder(null, "Soporte t\u00E9cnico", TitledBorder.LEADING, TitledBorder.TOP, null,
				Color.WHITE));
		panel_1.setBounds(10, 299, 330, 54);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblWhatsapp = new JLabel("3024094404");
		lblWhatsapp.setToolTipText("Copiar n\u00FAmero");
		lblWhatsapp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWhatsapp.setHorizontalAlignment(SwingConstants.LEFT);
		lblWhatsapp.setIcon(Utilidades.renderImage("/assets/logoWhatsapp.png", 14, 14));
		lblWhatsapp.setFont(new Font("Cambria", Font.PLAIN, 11));
		lblWhatsapp.setForeground(Color.WHITE);
		lblWhatsapp.setBounds(216, 22, 104, 14);
		panel_1.add(lblWhatsapp);

		JLabel lblCorreoSoportetecnicoparamosoftcom = new JLabel("soportetecnico@paramosoft.com");
		lblCorreoSoportetecnicoparamosoftcom.setToolTipText("Copiar correo");
		lblCorreoSoportetecnicoparamosoftcom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCorreoSoportetecnicoparamosoftcom.setIcon(Utilidades.renderImage("/assets/gmailIcon.png", 14, 14));
		lblCorreoSoportetecnicoparamosoftcom.setForeground(Color.WHITE);
		lblCorreoSoportetecnicoparamosoftcom.setFont(new Font("Cambria", Font.PLAIN, 11));
		lblCorreoSoportetecnicoparamosoftcom.setBounds(10, 22, 175, 14);
		panel_1.add(lblCorreoSoportetecnicoparamosoftcom);

		JPanel panel_2 = new PnlConFondo("/assets/negro.jpg");
		panel_2.setBorder(new TitledBorder(null, "An\u00E1lisis de proyectos", TitledBorder.LEADING, TitledBorder.TOP,
				null, Color.WHITE));
		panel_2.setBounds(10, 364, 330, 72);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblWwwparamosoftcomanalisamostuproyecto = new JLabel("www.paramosoft.com/analisamostuproyecto");
		lblWwwparamosoftcomanalisamostuproyecto.setToolTipText("Ir al sitio");
		lblWwwparamosoftcomanalisamostuproyecto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblWwwparamosoftcomanalisamostuproyecto.setIcon(Utilidades.renderImage("/assets/www.png", 14, 14));
		lblWwwparamosoftcomanalisamostuproyecto.setForeground(Color.WHITE);
		lblWwwparamosoftcomanalisamostuproyecto.setFont(new Font("Cambria", Font.PLAIN, 11));
		lblWwwparamosoftcomanalisamostuproyecto.setBounds(10, 47, 233, 14);
		panel_2.add(lblWwwparamosoftcomanalisamostuproyecto);

		JLabel lblAnalisisdeproyectosparamosoftcom = new JLabel("analisisdeproyectos@paramosoft.com");
		lblAnalisisdeproyectosparamosoftcom.setToolTipText("Copiar correo");
		lblAnalisisdeproyectosparamosoftcom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAnalisisdeproyectosparamosoftcom.setIcon(Utilidades.renderImage("/assets/gmailIcon.png", 14, 14));
		lblAnalisisdeproyectosparamosoftcom.setForeground(Color.WHITE);
		lblAnalisisdeproyectosparamosoftcom.setFont(new Font("Cambria", Font.PLAIN, 11));
		lblAnalisisdeproyectosparamosoftcom.setBounds(10, 22, 215, 14);
		panel_2.add(lblAnalisisdeproyectosparamosoftcom);
	}

}
