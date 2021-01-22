package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paneles.PnlFooter;
import paneles.PnlHead;
import recursos.Utilidades;
import vo.CobranzaVo;

import javax.swing.border.TitledBorder;

import logica.Logica;
import menu.MenuBar;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Configuracion extends ModuloGeneral implements ActionListener {

	private Inicio inicio;
	private Configuracion configuracion;
	private JMenuBar menuBar;

	private JPanel contentPane;
	private JTextField txtHorario;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtNombre;
	private JTextField txtNit;
	private Logica logica;
	private CobranzaVo parqueadero;
	private JComboBox<String> cmbRegimen;
	private PnlHead panelHeader;
	private JButton btnCargar;
	private JButton btnAccion;
	private JButton btnDescartar;

	/**
	 * Create the frame.
	 */
	public Configuracion(Inicio inicio, Logica logica) {
		super(inicio,"Confiuracion");
		this.inicio = inicio;
		configuracion = this;
		this.logica=logica;

		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/assets/iconPark.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 461, 506);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		menuBar = new MenuBar(this.inicio, this);
		// setJMenuBar(menuBar);
		// Enabled false siempre para el item actual
		((MenuBar) menuBar).getMntmConfiguracion().setEnabled(false);

		panelHeader = new PnlHead("",this);
		contentPane.add(panelHeader, BorderLayout.NORTH);

		JPanel panelFooter = new PnlFooter();
		contentPane.add(panelFooter, BorderLayout.SOUTH);

		Image imgPark = new ImageIcon(Inicio.class.getResource("/assets/parqueadero.png")).getImage();

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Informaci\u00F3n del negocio", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.setBounds(10, 45, 437, 261);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblLogoDelNegocio = new JLabel("Nombre del negocio");
		lblLogoDelNegocio.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblLogoDelNegocio.setBounds(11, 30, 125, 14);
		panel_1.add(lblLogoDelNegocio);

		JLabel lblNit = new JLabel("Nit");
		lblNit.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNit.setBounds(11, 79, 126, 14);
		panel_1.add(lblNit);

		JLabel lblRegimen = new JLabel("R\u00E9gimen");
		lblRegimen.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblRegimen.setBounds(11, 128, 125, 14);
		panel_1.add(lblRegimen);

		JLabel lblNewLabel = new JLabel("Direcci\u00F3n");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNewLabel.setBounds(158, 30, 125, 14);
		panel_1.add(lblNewLabel);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTelefono.setBounds(157, 79, 125, 14);
		panel_1.add(lblTelefono);

		JLabel lblCorreoElectronico = new JLabel("Correo Electronico");
		lblCorreoElectronico.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCorreoElectronico.setBounds(157, 128, 125, 14);
		panel_1.add(lblCorreoElectronico);

		JLabel lblHorariosDeAtencin = new JLabel("Horario de atenci\u00F3n");
		lblHorariosDeAtencin.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblHorariosDeAtencin.setBounds(11, 178, 125, 14);
		panel_1.add(lblHorariosDeAtencin);
		ImageIcon imgParkRender2 = new ImageIcon(imgPark.getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		txtHorario = new JTextField();
		txtHorario.setEditable(false);
		txtHorario.setBounds(11, 197, 272, 20);
		panel_1.add(txtHorario);
		txtHorario.setColumns(10);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setBounds(158, 49, 125, 20);
		panel_1.add(txtDireccion);
		txtDireccion.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setBounds(157, 98, 125, 20);
		panel_1.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.setBounds(157, 147, 125, 20);
		panel_1.add(txtCorreo);
		txtCorreo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(11, 49, 125, 20);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);

		txtNit = new JTextField();
		txtNit.setEditable(false);
		txtNit.setBounds(11, 98, 125, 20);
		panel_1.add(txtNit);
		txtNit.setColumns(10);

		cmbRegimen = new JComboBox<String>();
		cmbRegimen.setEnabled(false);
		cmbRegimen.setModel(new DefaultComboBoxModel(new String[] { "Seleccione", "Comun", "Simplificado" }));
		cmbRegimen.setBounds(11, 147, 125, 20);
		panel_1.add(cmbRegimen);

		JLabel lblLogoDelNegocio_1 = new JLabel("Logo del negocio");
		lblLogoDelNegocio_1.setBounds(305, 30, 107, 14);
		panel_1.add(lblLogoDelNegocio_1);
		lblLogoDelNegocio_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoDelNegocio_1.setFont(new Font("Verdana", Font.PLAIN, 12));

		JLabel label = new JLabel();
		label.setBounds(333, 78, 50, 50);
		panel_1.add(label);

		label.setIcon(imgParkRender2);

		btnCargar = new JButton("Cargar Imagen");
		btnCargar.setEnabled(false);
		btnCargar.setBounds(305, 49, 106, 20);
		btnCargar.addActionListener(this);
		panel_1.add(btnCargar);

		btnAccion = new JButton("Modificar");
		btnAccion.setBounds(11, 228, 100, 23);
		btnAccion.addActionListener(this);
		panel_1.add(btnAccion);

		btnDescartar = new JButton("Descartar");
		btnDescartar.setEnabled(false);
		btnDescartar.setBounds(121, 228, 100, 23);
		btnDescartar.addActionListener(this);
		panel_1.add(btnDescartar);

		JLabel lblOpcionesDeConfiguracion = new JLabel("Opciones de configuracion ");
		lblOpcionesDeConfiguracion.setBounds(10, 11, 437, 23);
		panel.add(lblOpcionesDeConfiguracion);
		lblOpcionesDeConfiguracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpcionesDeConfiguracion.setFont(new Font("Verdana", Font.BOLD, 18));

		//cargarInformacionParqueadero();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				configuracion.dispose();
				configuracion.inicio.setVisible(true);

			}
		});
	}

//	public void estableceTitulo() {
//		String title = "The parkingSoft " + inicio.getUsuarioActivo().getMiParqueadero().getNombre();
//		setTitle(title);
//		panelHeader.lblTheParkin.setText(title);
//	}

//	public void cargarInformacionParqueadero() {
//
//		parqueadero = inicio.getUsuarioActivo().getMiParqueadero();
//		txtNombre.setText(parqueadero.getNombre());
//		txtNit.setText(parqueadero.getNit());
//		txtDireccion.setText(parqueadero.getDireccion());
//		txtTelefono.setText(parqueadero.getTelefono());
//		txtCorreo.setText(parqueadero.getCorreo());
//		cmbRegimen.setSelectedIndex(parqueadero.getRegimen());
//		txtHorario.setText(parqueadero.getHorario());
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == btnAccion) {
//			if (btnAccion.getText().endsWith("Modificar")) {
//				habilitarCampos();
//				btnAccion.setText("Guardar");
//			} else if (btnAccion.getText().equals("Guardar")) {
//				String mensaje = validarCampos();
//				if (mensaje.equals("ok")) {
//					if (modificarParqueadero()) {
//						JOptionPane.showMessageDialog(this, "Información actualizada con éxito");
//					}
//				} else
//					JOptionPane.showMessageDialog(this, mensaje);
//			}
//		}
	}

//	private boolean modificarParqueadero() {
//
//		CobranzaVo miParqueaderoVo = inicio.getUsuarioActivo().getMiParqueadero();
//
//		miParqueaderoVo.setNombre(txtNombre.getText());
//		miParqueaderoVo.setDireccion(txtDireccion.getText());
//		miParqueaderoVo.setTelefono(txtTelefono.getText());
//		miParqueaderoVo.setNit(txtNit.getText());
//		miParqueaderoVo.setHorario(txtHorario.getText());
//		miParqueaderoVo.setCorreo(txtCorreo.getText());
//		miParqueaderoVo.setRegimen(cmbRegimen.getSelectedIndex());
//
//		return logica.getController().modificarParqueadero(miParqueaderoVo);
//
//	}

	private String validarCampos() {
		boolean good = true;

		// Bandera para dar focus solo al primer error de validacion
		int primerError = 0;
		String mensaje = "Por favor revise los siguientes campos: \n";

		if (txtNombre.getText().toString().equals("")) {
			mensaje += "Nombre del parqueadero. \n";
			if (primerError == 0) {
				txtNombre.requestFocus();
				primerError++;
			}
			// lblErrorNombre.setText("Este campo es obligatorio");
			good = false;
		}
		if (txtNit.getText().toString().equals("")) {
			mensaje += "Nit del parqueadero. \n";
			if (primerError == 0) {
				txtNit.requestFocus();
				primerError++;
			}
			// lblErrorCedula.setText("Este campo es obligatorio");
			good = false;
		}
		if (cmbRegimen.getSelectedIndex() == 0) {
			mensaje += "Regimen del parqueadero. \n";
			if (primerError == 0) {
				cmbRegimen.requestFocus();
				primerError++;
			}
			// lblErrorNumero.setText("Este campo es obligatorio");
			good = false;
		}
		if (txtDireccion.getText().toString().equals("")) {
			mensaje += "Dirección del parqueadero. \n";
			if (primerError == 0) {
				txtDireccion.requestFocus();
				primerError++;
			}
			// lblErrorMarca.setText("Este campo es obligatorio");
			good = false;
		}
		if (txtTelefono.getText().toString().equals("")) {
			mensaje += "Telefono del parqueadero. \n";
			if (primerError == 0) {
				txtTelefono.requestFocus();
				primerError++;
			}
			// lblErrorLinea.setText("Este campo es obligatorio");
			good = false;
		}
		if (txtCorreo.getText().toString().equals("")) {
			mensaje += "Correo del parqueadero. \n";
			if (primerError == 0) {
				txtCorreo.requestFocus();
				primerError++;
			}
			// lblErrorCorreo.setText("Este campo es obligatorio");
			good = false;
		} else if (!Utilidades.validarCorreo(txtCorreo.getText())) {
			mensaje += "Correo no valido. \n";
			if (primerError == 0) {
				txtCorreo.requestFocus();
				primerError++;
			}
			// lblErrorCorreo.setText("Este campo es obligatorio");
			good = false;
		}

		if (txtHorario.getText().toString().equals("")) {
			mensaje += "Horario del parqueadero. \n";
			if (primerError == 0) {
				txtHorario.requestFocus();
				primerError++;
			}
			// lblErrorModelo.setText("Este campo es obligatorio");
			good = false;
		}
		if (good) {
			return "ok";
		} else {
			return mensaje;
		}
	}

	private void habilitarCampos() {
		txtNombre.setEditable(true);
		txtNit.setEditable(true);
		txtDireccion.setEditable(true);
		txtTelefono.setEditable(true);
		txtCorreo.setEditable(true);
		txtHorario.setEditable(true);
		btnCargar.setEnabled(true);
		cmbRegimen.setEnabled(true);

	}
}
