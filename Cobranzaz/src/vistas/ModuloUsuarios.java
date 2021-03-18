package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.Logica;
import paneles.PnlConFondo;
import paneles.PnlFooter;
import paneles.PnlHead;
import recursos.Utilidades;
import vo.UsuarioVo;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;

/**
 * @author paramo
 * Módulo para gestión de usuarios
 */
public class ModuloUsuarios extends ModuloGeneral implements ActionListener,IModulosTemplate {

	private JPanel contentPane;
	private Logica logica;
	private JTextField txtCodigo;
	private JTextField txtCorreo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCelular;

	// Botones
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JTextField txtBuscar;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPass;

	private JTable table;
	private ArrayList<UsuarioVo> listaUsuarios;
	private DefaultTableModel tableModel;

	private JComboBox<String> cbmxTipo;

	// 0 para empezar a agregar
	// 1 para finalizar agregar
	private int accionAgregar = 0;
	protected UsuarioVo usuarioSelecionado;

	// 0 para empezar a modicar
	// 1 para finalizar modicar
	private int accionModificar = 0;

	/**
	 * Create the frame.
	 */
	public ModuloUsuarios(Inicio inicio, Logica logica) {
		super(inicio,"Usuarios");
		setUndecorated(true);
		this.logica = logica;

		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/assets/icon-cobranza.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 588);
		setLocationRelativeTo(null);
		setTitle("Sistema de gestión de datos usuarios");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelFooter = new PnlFooter();
		FlowLayout flowLayout = (FlowLayout) panelFooter.getLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		contentPane.add(panelFooter, BorderLayout.SOUTH);

		JPanel panelCenter = new PnlConFondo("/assets/imagen 70.png");
		panelCenter.setBackground(Color.WHITE);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JPanel panelHead = new PnlHead("/assets/gestionUsuarios.png", this);
		panelHead.setBounds(0, 0, 781, 110);
		panelCenter.add(panelHead);

		((PnlHead) panelHead).getLblModulo().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reiniciar();
			}
		});

		JLabel lblModuloDeClientes = new JLabel("Gesti\u00F3n de usuarios empleados");
		lblModuloDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblModuloDeClientes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModuloDeClientes.setBounds(0, 117, getWidth(), 18);
		panelCenter.add(lblModuloDeClientes);

		PnlConFondo pnlConFondo = new PnlConFondo("/assets/imagen 70.png");
		pnlConFondo.setLayout(null);
		pnlConFondo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del usuario",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlConFondo.setBounds(10, 141, 751, 185);
		panelCenter.add(pnlConFondo);

		JLabel lblNombre = new JLabel("Código");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(10, 25, 80, 20);
		pnlConFondo.add(lblNombre);

		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(100, 25, 140, 20);
		pnlConFondo.add(txtCodigo);

		JLabel lblApellido = new JLabel("E-mail");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellido.setBounds(10, 150, 80, 20);
		pnlConFondo.add(lblApellido);

		txtCorreo = new JTextField();
		txtCorreo.setEnabled(false);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(100, 150, 140, 20);
		pnlConFondo.add(txtCorreo);

		JLabel lblCorreo = new JLabel("Nombre");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setBounds(10, 56, 80, 20);
		pnlConFondo.add(lblCorreo);

		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(100, 56, 140, 20);
		pnlConFondo.add(txtNombre);

		cbmxTipo = new JComboBox<String>();
		cbmxTipo.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Seleccione", "Administrador", "Agente Call Center" }));
		cbmxTipo.setEnabled(false);
		cbmxTipo.setBounds(100, 119, 140, 20);
		pnlConFondo.add(cbmxTipo);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasea.setBounds(250, 86, 70, 20);
		pnlConFondo.add(lblContrasea);

		txtApellido = new JTextField();
		txtApellido.setEnabled(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(100, 87, 140, 20);
		pnlConFondo.add(txtApellido);

		JLabel lblUsuario = new JLabel("Apellido");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(10, 87, 80, 20);
		pnlConFondo.add(lblUsuario);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipo.setBounds(10, 118, 80, 20);
		pnlConFondo.add(lblTipo);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCelular.setBounds(250, 25, 80, 20);
		pnlConFondo.add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setEnabled(false);
		txtCelular.setColumns(10);
		txtCelular.setBounds(340, 25, 140, 20);
		pnlConFondo.add(txtCelular);

		JLabel lblRepetir = new JLabel("Repetir");
		lblRepetir.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRepetir.setBounds(250, 118, 70, 20);
		pnlConFondo.add(lblRepetir);

		JButton button = new JButton("");
		button.setIcon(Utilidades.renderImage("/assets/asignarCartera.png", 45, 45));
		button.setBounds(537, 25, 50, 50);
		pnlConFondo.add(button);

		JButton button_1 = new JButton("");
		button_1.setBounds(597, 25, 50, 50);
		pnlConFondo.add(button_1);

		JButton button_2 = new JButton("");
		button_2.setBounds(657, 25, 50, 50);
		pnlConFondo.add(button_2);

		JButton button_3 = new JButton("");
		button_3.setBounds(537, 86, 50, 50);
		pnlConFondo.add(button_3);

		JButton button_4 = new JButton("");
		button_4.setBounds(597, 86, 50, 50);
		pnlConFondo.add(button_4);

		JButton button_5 = new JButton("");
		button_5.setBounds(657, 86, 50, 50);
		pnlConFondo.add(button_5);

		JLabel lblUsuario_1 = new JLabel("Usuario");
		lblUsuario_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario_1.setBounds(250, 56, 80, 20);
		pnlConFondo.add(lblUsuario_1);

		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(340, 56, 140, 20);
		pnlConFondo.add(txtUsuario);

		txtPassword = new JPasswordField();
		txtPassword.setEnabled(false);
		txtPassword.setBounds(340, 87, 140, 20);
		pnlConFondo.add(txtPassword);

		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setEnabled(false);
		txtConfirmPass.setBounds(340, 119, 140, 20);
		pnlConFondo.add(txtConfirmPass);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		ImageIcon imgAddPlusRender = Utilidades.renderImage("/assets/plus.png", 17, 17);
		btnAgregar.setIcon(imgAddPlusRender);
		btnAgregar.setBounds(10, 342, 110, 23);
		panelCenter.add(btnAgregar);

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(this);

		ImageIcon imgUpdateRender = Utilidades.renderImage("/assets/update1.png", 17, 17);
		btnModificar.setIcon(imgUpdateRender);
		btnModificar.setBounds(130, 342, 110, 23);
		panelCenter.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(this);
		ImageIcon imgAddUsuerRender = Utilidades.renderImage("/assets/eliminar.png", 17, 17);
		btnEliminar.setIcon(imgAddUsuerRender);
		btnEliminar.setBounds(250, 342, 110, 23);
		panelCenter.add(btnEliminar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(this);
		ImageIcon imgCancelarRender = Utilidades.renderImage("/assets/cancelicon.png", 17, 17);
		btnCancelar.setIcon(imgCancelarRender);
		btnCancelar.setBounds(370, 342, 110, 23);
		panelCenter.add(btnCancelar);

		txtBuscar = new JTextField();
		txtBuscar.setText("Busqueda por cedula, nombre o teléfono");
		txtBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBuscar.setForeground(Color.GRAY);
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(540, 345, 221, 20);
		panelCenter.add(txtBuscar);

		JLabel label_1 = new JLabel("Busqueda de clientes");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_1.setBounds(589, 365, 172, 14);
		panelCenter.add(label_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 379, 751, 141);
		panelCenter.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				cancelarAccion();
				int selected = table.getSelectedRow();
				usuarioSelecionado = listaUsuarios.get(selected);
				txtCodigo.setText("CU_" + usuarioSelecionado.getCodigo());
				txtNombre.setText(usuarioSelecionado.getNombre());
				txtApellido.setText(usuarioSelecionado.getApellido());
				txtCelular.setText(usuarioSelecionado.getCelular());
				txtUsuario.setText(usuarioSelecionado.getUsuario());
				txtCorreo.setText(usuarioSelecionado.getCorreo());
				txtPassword.setText(usuarioSelecionado.getPassword());
				txtConfirmPass.setText(usuarioSelecionado.getPassword());
				cbmxTipo.setSelectedIndex(usuarioSelecionado.getTipo());
				habilitarBotones(true);
//				if (!btnModificar.isEnabled()) {
//					habilitarBotones(true);
//				}
//				deshabilitarCampos();
			}
		});
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Código", "Nombre", "Apellido", "Usuario", "Celular", "Correo", "Tipo", });
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);
		scrollPane.setViewportView(table);

		JButton button_6 = new JButton("<<");
		button_6.setMargin(new Insets(2, 2, 2, 2));
		button_6.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_6.setBounds(603, 524, 30, 23);
		panelCenter.add(button_6);

		JButton button_7 = new JButton("<");
		button_7.setMargin(new Insets(2, 2, 2, 2));
		button_7.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_7.setBounds(635, 524, 30, 23);
		panelCenter.add(button_7);

		JButton button_8 = new JButton("1");
		button_8.setMargin(new Insets(2, 2, 2, 2));
		button_8.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_8.setBounds(667, 524, 30, 23);
		panelCenter.add(button_8);

		JButton button_9 = new JButton(">");
		button_9.setMargin(new Insets(2, 2, 2, 2));
		button_9.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_9.setBounds(699, 524, 30, 23);
		panelCenter.add(button_9);

		JButton button_10 = new JButton(">>");
		button_10.setMargin(new Insets(2, 2, 2, 2));
		button_10.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_10.setBounds(731, 524, 30, 23);
		panelCenter.add(button_10);

		cargarUsuarios();
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ModuloUsuarios frame = new ModuloUsuarios(null, null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	private void habilitarBotones(boolean enabled) {
		btnModificar.setEnabled(enabled);
		btnEliminar.setEnabled(enabled);
	}

	protected void buscarUsuario() {

		String criterio = txtBuscar.getText();
		if (criterio.equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor ingrese un criterio de busqueda");
		} else {
			listaUsuarios = logica.getController().BuscarUsuarios(criterio);
			tableModel.setRowCount(0);
			Object[] fila = null;

			for (UsuarioVo usuarioVo : listaUsuarios) {

				fila = new Object[7];
				fila[0] = "CU_" + usuarioVo.getCodigo();
				fila[1] = usuarioVo.getNombre();
				fila[2] = usuarioVo.getApellido();
				fila[3] = usuarioVo.getUsuario();
				fila[4] = usuarioVo.getCelular();
				fila[5] = usuarioVo.getCorreo();
				int tipo = usuarioVo.getTipo();
				if (tipo == Utilidades.TIPO_ADMIN) {
					fila[6] = "Administrativo";
				} else
					fila[6] = "Agente Call Center";

				tableModel.addRow(fila);

			}
			table.setModel(tableModel);
		}
	}

	protected void cargarUsuarios() {
		listaUsuarios = logica.getController().BuscarUsuarios();
		tableModel.setRowCount(0);
		Object[] fila = null;

		for (UsuarioVo usuarioVo : listaUsuarios) {

			fila = new Object[7];
			fila[0] = "CU_" + usuarioVo.getCodigo();
			fila[1] = usuarioVo.getNombre();
			fila[2] = usuarioVo.getApellido();
			fila[3] = usuarioVo.getUsuario();
			fila[4] = usuarioVo.getCelular();
			fila[5] = usuarioVo.getCorreo();
			int tipo = usuarioVo.getTipo();
			if (tipo == Utilidades.TIPO_ADMIN) {
				fila[6] = "Administrativo";
			} else
				fila[6] = "Agente Call Center";

			tableModel.addRow(fila);

		}
		table.setModel(tableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {

			if (accionAgregar == 0) {
				int res = JOptionPane.showConfirmDialog(this, "¿Desea agregar un nuevo usuario?", "Software de gestión de cobranza dice:",
						0);
				if (res == 0) {
					accionAgregar = 1;
					limpiar();
					habilitarCampos();
					habilitarBotones(false);
					btnCancelar.setEnabled(true);
					txtNombre.requestFocus();
				}
			} else if (accionAgregar == 1) {
				agregarUsuario();
			}
		} else if (e.getSource() == btnModificar) {
			if (usuarioSelecionado != null) {
				if (accionModificar == 0) {
					int res = JOptionPane.showConfirmDialog(this, "¿Desea modicar los datos del usuario?",
							"Software de gestión de cobranza dice:", 0);
					if (res == 0) {
						accionModificar = 1;
						// limpiarCampos();
						habilitarCampos();
						btnAgregar.setEnabled(false);
						btnEliminar.setEnabled(false);
						btnCancelar.setEnabled(true);
						txtNombre.requestFocus();
					}
				} else if (accionModificar == 1) {
					modicarUsuario();
				}
			} else
				JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario");

		} else if (e.getSource() == btnEliminar) {
			if (usuarioSelecionado != null) {

				int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea elimnar el usuario?",
						"Software de gestión de cobranza dice:", 0);
				if (res == 0) {
					String codigo = JOptionPane.showInputDialog("Ingrese código de seguridad");
					if (codigo.equals("25057")) {
						eliminarUsuario();
						usuarioSelecionado = null;
						cargarUsuarios();
					} else
						JOptionPane.showMessageDialog(this, "Código incorrecto, por favor intente nuevamente");
				}

			} else
				JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario");
		} else if (e.getSource() == btnCancelar) {
			cancelarAccion();
		}
	}

	private void cancelarAccion() {
		deshabilitarCampos();
		accionAgregar = 0;
		accionModificar = 0;
		limpiar();
		habilitarBotones(false);
		btnAgregar.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnAgregar.requestFocus();
	}

	private void eliminarUsuario() {

		if (logica.getController().elimnarUsuario(usuarioSelecionado.getId())) {
			JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente");
			deshabilitarCampos();
			limpiar();
			usuarioSelecionado = null;
			limpiarBusqueda();
			habilitarBotones(false);
		} else
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");

	}

	private void modicarUsuario() {
		UsuarioVo usuario = new UsuarioVo();
		String codigo = txtCodigo.getText();
		codigo = codigo.replaceFirst("CU_", "");
		usuario.setCodigo(Integer.parseInt(codigo));
		usuario.setNombre(txtNombre.getText());
		usuario.setApellido(txtApellido.getText());
		usuario.setUsuario(txtUsuario.getText());
		usuario.setCelular(txtCelular.getText());
		usuario.setCorreo(txtCorreo.getText());
		usuario.setPassword(txtPassword.getText());
		usuario.setTipo(cbmxTipo.getSelectedIndex());

		if (logica.getController().modicarUsuario(usuario)) {
			JOptionPane.showMessageDialog(this, "Usuario modificado correctamente");
			deshabilitarCampos();
			limpiar();
			usuarioSelecionado = null;
			habilitarBotones(false);
			accionModificar = 0;
			btnAgregar.setEnabled(true);
			btnCancelar.setEnabled(false);
			limpiarBusqueda();
		} else
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");
	}

	private void agregarUsuario() {
		UsuarioVo usuario = new UsuarioVo();
		usuario.setNombre(txtNombre.getText());
		usuario.setApellido(txtApellido.getText());
		usuario.setUsuario(txtUsuario.getText());
		usuario.setCelular(txtCelular.getText());
		usuario.setCorreo(txtCorreo.getText());
		usuario.setPassword(txtPassword.getText());
		usuario.setTipo(cbmxTipo.getSelectedIndex());

		if (logica.getController().agregarUsuario(usuario)) {
			int res = JOptionPane.showConfirmDialog(this, "Usuario agregado correctamente \n ¿Desea continuar?",
					"Software de gestión de cobranza dice:", 0);

			limpiar();
			cargarUsuarios();
			if (res == 0) {
				txtNombre.requestFocus();
			} else {
				accionAgregar = 0;
				btnCancelar.setEnabled(false);
				deshabilitarCampos();
			}
		} else
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");
	}

	private void deshabilitarCampos() {
		txtNombre.setEnabled(false);
		txtApellido.setEnabled(false);
		txtCelular.setEnabled(false);
		txtCorreo.setEnabled(false);
		txtUsuario.setEnabled(false);
		txtPassword.setEnabled(false);
		txtConfirmPass.setEnabled(false);
		cbmxTipo.setEnabled(false);

	}

	private void habilitarCampos() {
		txtNombre.setEnabled(true);
		txtApellido.setEnabled(true);
		txtCelular.setEnabled(true);
		txtCorreo.setEnabled(true);
		txtUsuario.setEnabled(true);
		txtPassword.setEnabled(true);
		txtConfirmPass.setEnabled(true);
		cbmxTipo.setEnabled(true);
	}

	public void limpiarBusqueda() {
		txtBuscar.setText("Busqueda por cédula, nombre o teléfono");
		txtBuscar.setForeground(Color.GRAY);
		cargarUsuarios();
	}

	@Override
	public void reiniciar() {
		limpiar();
		limpiarBusqueda();
		habilitarBotones(false);
		deshabilitarCampos();
		btnAgregar.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnAgregar.requestFocus();
	}

	@Override
	public void limpiar() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtCelular.setText("");
		txtCorreo.setText("");
		txtUsuario.setText("");
		txtPassword.setText("");
		txtConfirmPass.setText("");
		cbmxTipo.setSelectedItem(0);
	}
}
