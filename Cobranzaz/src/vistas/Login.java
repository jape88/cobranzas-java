package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.Logica;
import paneles.PnlConFondo;
import paneles.PnlHead;
import vo.UsuarioVo;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

/**
 * @author paramo
 * Ventana de login
 */
public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JPasswordField txtPass;
	private JButton btnIniciarSesion;

	private Inicio inicio;
	private Logica logica;
	private JLabel lbRequiere1;
	private JLabel lbRequiere2;

	/**
	 * Create the frame.
	 */
	public Login(Inicio inicio, Logica logica) {
		setUndecorated(true);

		this.inicio = inicio;
		this.logica = logica;
		// this.cargar= new Cargando();

		setTitle("Software de gestión de cobranza");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/assets/icon-cobranza.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 263);
		setLocationRelativeTo(null);

		contentPane = new PnlConFondo("/assets/imagen 70.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setBackground(new Color(30, 214, 206));
		setContentPane(contentPane);

		contentPane.setLayout(null);


		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUsuario.setAlignmentX(0.5f);
		lblUsuario.setBounds(74, 122, 106, 20);
		contentPane.add(lblUsuario);

		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A:");
		lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasea.setForeground(Color.BLACK);
		lblContrasea.setFont(new Font("Verdana", Font.BOLD, 12));
		lblContrasea.setAlignmentX(0.5f);
		lblContrasea.setBounds(74, 166, 106, 20);
		contentPane.add(lblContrasea);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPass.requestFocus();
				}
			}
		});
		txtNombre.setBounds(190, 123, 110, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtPass = new JPasswordField();
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		txtPass.setColumns(10);
		txtPass.setBounds(190, 167, 110, 20);
		contentPane.add(txtPass);

		btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesion.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnIniciarSesion.setBounds(117, 217, 150, 23);
		btnIniciarSesion.addActionListener(this);
		contentPane.add(btnIniciarSesion);

		lbRequiere1 = new JLabel("Este campo es requrido");
		lbRequiere1.setVisible(false);
		lbRequiere1.setForeground(Color.RED);
		lbRequiere1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbRequiere1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbRequiere1.setBounds(190, 142, 100, 14);
		contentPane.add(lbRequiere1);

		lbRequiere2 = new JLabel("Este campo es requrido");
		lbRequiere2.setVisible(false);
		lbRequiere2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbRequiere2.setForeground(Color.RED);
		lbRequiere2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbRequiere2.setBounds(190, 187, 100, 14);
		contentPane.add(lbRequiere2);


		JPanel panelHead = new PnlHead("", null);
		panelHead.setBounds(43, 0, 354, 110);

		((PnlHead) panelHead).setImage(null);
		contentPane.add(panelHead);

		/*JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(Utilidades.renderImage("/assets/logoParamoSoft2.png",88,88));
		lblLogo.setBounds(10, 10, 469, 88);
		contentPane.add(lblLogo);*/

	}

	private String validarCampos() {
		boolean good = true;

		// Bandera para dar focus solo al primer error de validacion
		int primerError = 0;
		String mensaje = "Por favor revise los siguientes campos: \n";

		if (txtNombre.getText().toString().equals("")) {
			mensaje += "Debe ingresar un usuario valido \n";
			if (primerError == 0) {
				txtNombre.requestFocus();
				primerError++;
			}
			lbRequiere1.setVisible(true);
			good = false;
		}
		if (txtPass.getText().toString().equals("")) {
			mensaje += "Debe ingresar una contraseña \n";
			if (primerError == 0) {
				txtPass.requestFocus();
				primerError++;
			}
			lbRequiere2.setVisible(true);
			good = false;
		}

		if (good) {
			return "ok";
		} else {
			return mensaje;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnIniciarSesion) {

			login();

		}

	}

	private void login() {
		// TODO Auto-generated method stub
		String mensaje = validarCampos();

		if (mensaje.equals("ok")) {
			String usuario = txtNombre.getText();
			String pass = txtPass.getText();
			UsuarioVo usuarioVo = logica.getController().devolverUsuario(usuario);
			if (usuarioVo.getId() != 0) {

				if (usuarioVo.getPassword().equals(pass)) {
					dispose();
					JOptionPane.showMessageDialog(this,
							"Bienvenido " + usuarioVo.getNombre() + " " + usuarioVo.getApellido());

					// cargar= new Cargando();
					// HiloCarga hiloCarga = new HiloCarga(inicio,cargar,usuarioVo);
					// cargar.setVisible(true);
					// hiloCarga.start();

					inicio.setVisible(true);
					inicio.setUsuarioActivo(usuarioVo);
//					inicio.estableceTitulo();
				} else {
					txtPass.requestFocus();
					JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
				}

			} else {

				txtNombre.requestFocus();
				JOptionPane.showMessageDialog(this, "El usuario no exite");
			}
		} else {
			JOptionPane.showMessageDialog(this, mensaje);
		}
	}
}
