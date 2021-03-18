package vistas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paneles.PnlConFondo;
import paneles.PnlFooter;
import paneles.PnlHead;
import recursos.Utilidades;
import vo.UsuarioVo;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import controladores.Logica;
import menu.MenuBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

/**
 * @author paramo
 * Ventana principal home
 */
public class Inicio extends JFrame implements ActionListener {

	private Logica logica;
	private UsuarioVo usuarioActivo;

	private JPanel contentPane;
	private JLabel lbHora;
	private JMenuBar menuBar;
	private JButton btnModuloClientes;
	private JButton btnModuloUsuarios;
	private JButton btnModuloCobranza;
	private JButton btnModuloFacturacion;
	private  JButton btnModuloAcreedores;

	/**
	 * Create the frame.
	 */
	public Inicio(Logica logica) {
		setUndecorated(true);
		this.logica = logica;

		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/assets/icon-cobranza.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 572);
		setLocationRelativeTo(null);
		setTitle("Sistema de gestión de cobranza");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		menuBar = new MenuBar(this, this);
		//setJMenuBar(menuBar);
		// Enabled false siempre para el item actual
		((MenuBar) menuBar).getMntmInicio().setEnabled(false);

		JPanel panelFooter = new PnlFooter();
		contentPane.add(panelFooter, BorderLayout.SOUTH);

		JPanel panelCenter = new PnlConFondo("/assets/imagen 70.png");
		panelCenter.setBackground(Color.WHITE);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JLabel lblInformacinDelSistema = new JLabel("Bienvenido al Software de gestión de cobranza");
		lblInformacinDelSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDelSistema.setFont(new Font("Verdana", Font.BOLD, 18));
		lblInformacinDelSistema.setBounds(0, 120, 775, 30);
		panelCenter.add(lblInformacinDelSistema);

		JPanel panel = new PnlHead("", null);
		panel.setBounds(0, 0, 781, 110);
		panelCenter.add(panel);


		//((PnlHead) panel).getLblModulo().setBounds(0,0,0,0);

		btnModuloClientes = new JButton();
		btnModuloClientes.setToolTipText("Gestión de clientes");
		btnModuloClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModuloClientes.addActionListener(this);
		ImageIcon imgAddUserRender = Utilidades.renderImage("/assets/gestionClientes.png", 140, 140);
		btnModuloClientes.setIcon(imgAddUserRender);

		panelCenter.add(btnModuloClientes);
		btnModuloClientes.requestFocus();

		btnModuloUsuarios = new JButton();
		btnModuloUsuarios.setToolTipText("Gestión de usuarios");
		btnModuloUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModuloUsuarios.addActionListener(this);
		ImageIcon imgUusariosRender = Utilidades.renderImage("/assets/gestionUsuarios.png", 140, 140);
		btnModuloUsuarios.setIcon(imgUusariosRender);

		panelCenter.add(btnModuloUsuarios);

		btnModuloAcreedores = new JButton();
		btnModuloAcreedores.setToolTipText("Gestión de acreedores");
		btnModuloAcreedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModuloAcreedores.addActionListener(this);
		ImageIcon imgAcreedorRender = Utilidades.renderImage("/assets/acreedoresicon.png", 140, 140);
		btnModuloAcreedores.setIcon(imgAcreedorRender);

		panelCenter.add(btnModuloAcreedores);

		btnModuloCobranza = new JButton();
		btnModuloCobranza.setToolTipText("Gestión de cobranza");
		btnModuloCobranza.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModuloCobranza.addActionListener(this);
		ImageIcon imgCobranzaRender = Utilidades.renderImage("/assets/cobranza.png", 140, 140);
		btnModuloCobranza.setIcon(imgCobranzaRender);

		panelCenter.add(btnModuloCobranza);
		
				JLabel lblCerrar = new JLabel();
				lblCerrar.setBorder(new EmptyBorder(0,0,0,0));
				lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
						lblCerrar.setBounds(761, 0, 20, 20);
						panel.add(lblCerrar);
						lblCerrar.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								exit();
							}
						});
						lblCerrar.setIcon(Utilidades.renderImage("/assets/cerrar.png", 20, 20));

		lbHora = new JLabel("--:--:--");
		lbHora.setBounds(679, 19, 102, 18);
		panel.add(lbHora);
		lbHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lbHora.setForeground(Color.BLUE);
		lbHora.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		btnModuloFacturacion = new JButton();
		btnModuloFacturacion.setIcon(Utilidades.renderImage("/assets/registradora.png", 140, 140));
		btnModuloFacturacion.setToolTipText("Gestión de facturación");
		btnModuloFacturacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnModuloFacturacion.addActionListener(this);
		panelCenter.add(btnModuloFacturacion);
		btnModuloClientes.requestFocus();

		//los bounds
		{
			btnModuloUsuarios.setBounds(136, 171, 150, 150);
			btnModuloClientes.setBounds(316, 171, 150, 150);
			btnModuloAcreedores.setBounds(496, 171, 150, 150);

			btnModuloFacturacion.setBounds(225, 352, 150, 150);
			btnModuloCobranza.setBounds(405, 352, 150, 150);
		}



	}

	protected void exit() {
		int x = JOptionPane.showConfirmDialog(this, "¿Seguro que desea salir?", "Software de gestión de cobranza dice:", 0);
		if (x == 0) {
			System.exit(0);
		}

	}

	public JLabel getLbHora() {
		return lbHora;
	}

	public void setLbHora(JLabel lbHora) {
		this.lbHora = lbHora;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModuloClientes) {
			ModuloClientes frame = new ModuloClientes(this, getlogica());
			// frame.estableceTitulo();
			frame.setVisible(true);
			this.dispose();
		} else if (e.getSource() == btnModuloUsuarios) {
			ModuloUsuarios frame = new ModuloUsuarios(this, getlogica());
			// frame.estableceTitulo();
			frame.setVisible(true);
			this.dispose();
		} else if (e.getSource() == btnModuloCobranza) {
			ModuloCobranzas frame = new ModuloCobranzas(this, getlogica());
			// frame.estableceTitulo();
			frame.setVisible(true);
			this.dispose();
		}else if (e.getSource() == btnModuloFacturacion) {
			ModuloFacturacion frame = new ModuloFacturacion(this, getlogica());
			// frame.estableceTitulo();
			frame.setVisible(true);
			this.dispose();
		}

	}

	private String validarCampos() {
		boolean good = true;

		// Bandera para dar focus solo al primer error de validacion
		int primerError = 0;
		String mensaje = "Por favor revise los siguientes campos: \n";

//		if (txtPlaca.getText().toString().equals("Ingresar placa")) {
//			mensaje += "Debe ingresar una placa \n";
//			if (primerError == 0) {
//				txtPlaca.requestFocus();
//				primerError++;
//			}
//			lblErrorPlaca.setText("Este campo es obligatorio");
//			good = false;
//		}
//		if (txtNombreCliente.getText().toString().equals("Ingresar cliente")) {
//			mensaje += "Debe ingresar un cliente \n";
//			if (primerError == 0) {
//				txtNombreCliente.requestFocus();
//				primerError++;
//			}
//			lblErrorCliente.setText("Este campo es obligatorio");
//			good = false;
//		}

		if (good) {
			return "ok";
		} else {
			return mensaje;
		}
	}

	private void limpiarCampos() {

	}

	public Logica getlogica() {
		return logica;
	}

	public UsuarioVo getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(UsuarioVo usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

//	public static void main(String[] args) {
////		try {
////			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
////		} catch (Exception e1) {
////			e1.printStackTrace();
////		}
////		EventQueue.invokeLater(new Runnable() {
////			public void run() {
////				try {
////					Inicio frame = new Inicio(null);
////					frame.setVisible(true);
////				} catch (Exception e) {
////					e.printStackTrace();
////				}
////			}
////		});
//	}
}
