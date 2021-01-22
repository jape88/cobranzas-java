package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Logica;
import paneles.PnlConFondo;
import paneles.PnlFooter;
import paneles.PnlFormCliente;
import paneles.PnlHead;
import recursos.Utilidades;
import vo.CiudadVo;
import vo.ClientesVo;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ModuloClientes extends ModuloGeneral implements ActionListener {

	private JPanel contentPane;
	private Logica logica;
	private Inicio inicio;
	private ModuloClientes modulo;
	private JTable table;
	private JTextField txtBuscar;

	private ArrayList<ClientesVo> listaClientes;
	private DefaultTableModel tableModel;

	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;

	// 0 para empezar a agregar
	// 1 para finalizar agregar
	private int accionAgregar = 0;
	protected ClientesVo clienteSelecionado;

	// 0 para empezar a modicar
	// 1 para finalizar modicar
	private int accionModificar = 0;
	private JButton btnCancelar;
	private ModuloGeneral moduloGeneral;
	private PnlFormCliente panelForm;

	/**
	 * Create the frame.
	 */
	public ModuloClientes(Inicio inicio, Logica logica) {
		super(inicio, "Clientes");

		setUndecorated(true);
		this.inicio = inicio;
		modulo = this;
		this.logica = logica;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Utilidades.volverInicio(modulo);
			}
		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/assets/icon-cobranza.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 781, 574);
		setLocationRelativeTo(null);
		setTitle("Sistema de gestión de datos GNA");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelFooter = new PnlFooter();
		FlowLayout flowLayout = (FlowLayout) panelFooter.getLayout();
		flowLayout.setVgap(5);
		contentPane.add(panelFooter, BorderLayout.SOUTH);

		JPanel panelCenter = new PnlConFondo("/assets/imagen 70.png");
		panelCenter.setBackground(Color.WHITE);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JPanel panelHead = new PnlHead("/assets/cliente.png", this);
		panelHead.setBounds(0, 0, 781, 110);
		panelCenter.add(panelHead);

		((PnlHead) panelHead).getLblModulo().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				recargarModulo();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 365, 751, 147);
		panelCenter.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				cancelarAccion();
				int selected = table.getSelectedRow();
				clienteSelecionado = listaClientes.get(selected);
				panelForm.getTxtCodigo().setText("CL_" + clienteSelecionado.getCodigo());
				panelForm.getTxtCedula().setText(clienteSelecionado.getCedula() + "");
				panelForm.getTxtNombre().setText(clienteSelecionado.getNombre());
				panelForm.getTxtApellido().setText(clienteSelecionado.getApellido());
				panelForm.getTxtCelular1().setText(clienteSelecionado.getCelular());
				panelForm.getTxtCelular2().setText(clienteSelecionado.getCelular2());
				panelForm.getTxtCorreo().setText(clienteSelecionado.getCorreo());
				panelForm.getTxtDirrecion().setText(clienteSelecionado.getDirreccion());

				habilitarBotones(true);
//				if (!btnModificar.isEnabled()) {
//					habilitarBotones(true);
//				}
//				deshabilitarCampos();
			}
		});
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Codigo", "Cedula", "Nombre", "Telefono", "Dirrecion", "Departamento", "Ciudad" });
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		scrollPane.setViewportView(table);

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarClientes();
				}
			}
		});
		txtBuscar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtBuscar.getText().equals("Busqueda por cedula, nombre o telefono")) {
					txtBuscar.setText("");
					txtBuscar.setFont(new Font("Verdana", Font.PLAIN, 9));
					txtBuscar.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtBuscar.getText().equals("")) {
					txtBuscar.setForeground(Color.GRAY);
					txtBuscar.setFont(new Font("Verdana", Font.PLAIN, 9));
					txtBuscar.setText("Busqueda por cedula, nombre o telefono");
				}
			}
		});
		txtBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBuscar.setText("Busqueda por cedula, nombre o telefono");
		txtBuscar.setForeground(Color.GRAY);
		txtBuscar.setBounds(540, 330, 221, 20);
		panelCenter.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Busqueda de clientes");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_3.setBounds(589, 350, 172, 14);
		panelCenter.add(lblNewLabel_3);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		ImageIcon imgAddPlusRender = Utilidades.renderImage("/assets/plus.png", 17, 17);
		btnAgregar.setIcon(imgAddPlusRender);
		btnAgregar.setBounds(10, 330, 110, 23);
		panelCenter.add(btnAgregar);

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(this);

		ImageIcon imgUpdateRender = Utilidades.renderImage("/assets/update1.png", 17, 17);
		btnModificar.setIcon(imgUpdateRender);
		btnModificar.setBounds(130, 330, 110, 23);
		panelCenter.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(this);
		ImageIcon imgAddUsuerRender = Utilidades.renderImage("/assets/eliminar.png", 17, 17);
		btnEliminar.setIcon(imgAddUsuerRender);
		btnEliminar.setBounds(250, 330, 110, 23);
		panelCenter.add(btnEliminar);

		JLabel lblModuloDeClientes = new JLabel("Modulo para gesti\u00F3n de clientes");
		lblModuloDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblModuloDeClientes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModuloDeClientes.setBounds(0, 117, 781, 20);
		panelCenter.add(lblModuloDeClientes);

		ImageIcon imgFotoRender = Utilidades.renderImage("/assets/foto.png", 150, 123);
		ImageIcon imgPlusRender = Utilidades.renderImage("/assets/plus.png", 25, 25);
		ImageIcon imgMinusRender = Utilidades.renderImage("/assets/minus.png", 25, 25);

		panelForm = new PnlFormCliente("/assets/imagen 70.png");
		panelForm.setLocation(10, 139);
		panelForm.getTxtDirrecion().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (accionAgregar == 1) {
						btnAgregar.requestFocus();
					} else
						btnModificar.requestFocus();
				}
			}
		});
		panelForm.getTxtCodigo().setEnabled(false);

		panelCenter.add(panelForm);

		JButton button = new JButton(">>");
		button.setFont(new Font("SansSerif", Font.BOLD, 14));
		button.setMargin(new Insets(2, 2, 2, 2));
		button.setBounds(731, 516, 30, 23);
		panelCenter.add(button);

		JButton button_1 = new JButton(">");
		button_1.setMargin(new Insets(2, 2, 2, 2));
		button_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_1.setBounds(699, 516, 30, 23);
		panelCenter.add(button_1);

		JButton button_2 = new JButton("1");
		button_2.setMargin(new Insets(2, 2, 2, 2));
		button_2.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_2.setBounds(667, 516, 30, 23);
		panelCenter.add(button_2);

		JButton button_3 = new JButton("<");
		button_3.setMargin(new Insets(2, 2, 2, 2));
		button_3.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_3.setBounds(635, 516, 30, 23);
		panelCenter.add(button_3);

		JButton button_4 = new JButton("<<");
		button_4.setMargin(new Insets(2, 2, 2, 2));
		button_4.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_4.setBounds(603, 516, 30, 23);
		panelCenter.add(button_4);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(this);
		ImageIcon imgCancelarRender = Utilidades.renderImage("/assets/cancelicon.png", 17, 17);
		btnCancelar.setIcon(imgCancelarRender);
		btnCancelar.setBounds(370, 330, 110, 23);
		panelCenter.add(btnCancelar);

		JLabel label_1 = new JLabel();
		label_1.setBounds(589, 148, 150, 123);
		panelCenter.add(label_1);
		label_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		label_1.setIcon(imgFotoRender);

		JLabel label_2 = new JLabel();
		label_2.setBounds(634, 274, 25, 25);
		panelCenter.add(label_2);
		label_2.setIcon(imgPlusRender);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(669, 274, 25, 25);
		panelCenter.add(label_3);
		label_3.setIcon(imgMinusRender);

		habilitarCampos(false);
		cargarClientes();

	}

	private void habilitarBotones(boolean enabled) {
		btnModificar.setEnabled(enabled);
		btnEliminar.setEnabled(enabled);
	}

	protected void buscarClientes() {

		String criterio = txtBuscar.getText();
		if (criterio.equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor ingrese un criterio de busqueda");
		} else {
			listaClientes = logica.getController().BuscarClientes(criterio);
			tableModel.setRowCount(0);
			Object[] fila = null;

			int i = 1;
			for (ClientesVo clienteVo : listaClientes) {

				fila = new Object[7];
				fila[0] = "CL_" + clienteVo.getCodigo();
				fila[1] = clienteVo.getCedula();
				fila[2] = clienteVo.getNombre() + " " + clienteVo.getApellido();
				fila[3] = clienteVo.getCelular();
				fila[4] = clienteVo.getDirreccion();
				fila[5] = clienteVo.getCiudad().getNombre();
				fila[6] = clienteVo.getCiudad().getDepartamento().getNombre();

				tableModel.addRow(fila);
				i++;
			}
			table.setModel(tableModel);
		}
	}

	protected void cargarClientes() {
		listaClientes = logica.getController().listaClientes();
		tableModel.setRowCount(0);
		Object[] fila = null;

		int i = 1;
		for (ClientesVo clienteVo : listaClientes) {

			fila = new Object[7];
			fila[0] = "CL_" + clienteVo.getCodigo();
			fila[1] = clienteVo.getCedula();
			fila[2] = clienteVo.getNombre() + " " + clienteVo.getApellido();
			fila[3] = clienteVo.getCelular();
			fila[4] = clienteVo.getDirreccion();
			fila[5] = clienteVo.getCiudad().getNombre();
			fila[6] = clienteVo.getCiudad().getDepartamento().getNombre();

			tableModel.addRow(fila);
			i++;
		}
		table.setModel(tableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {

			if (accionAgregar == 0) {
				int res = JOptionPane.showConfirmDialog(this, "¿Desea agregar un nuevo cliente?", "GNA Software dice:",
						0);
				if (res == 0) {
					accionAgregar = 1;
					limpiarCampos();
					habilitarCampos(true);
					habilitarBotones(false);
					btnCancelar.setEnabled(true);
					panelForm.getTxtCedula().requestFocus();
				}
			} else if (accionAgregar == 1) {
				agregarCliente();
			}
		} else if (e.getSource() == btnModificar) {
			if (clienteSelecionado != null) {
				if (accionModificar == 0) {
					int res = JOptionPane.showConfirmDialog(this, "¿Desea modicar los datos del cliente?",
							"GNA Software dice:", 0);
					if (res == 0) {
						accionModificar = 1;
						// limpiarCampos();
						habilitarCampos(true);
						btnAgregar.setEnabled(false);
						btnEliminar.setEnabled(false);
						btnCancelar.setEnabled(true);
						panelForm.getTxtCedula().requestFocus();
					}
				} else if (accionModificar == 1) {
					modicarCliente();
				}
			} else
				JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente");

		} else if (e.getSource() == btnEliminar) {
			if (clienteSelecionado != null) {

				int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea elimnar el cliente?",
						"GNA Software dice:", 0);
				if (res == 0) {
					String codigo = JOptionPane.showInputDialog("Ingrese codigo de seguridad");
					if (codigo.equals("25057")) {
						eliminarCliente();
						clienteSelecionado = null;
						cargarClientes();
					} else
						JOptionPane.showMessageDialog(this, "Codigo incorrecto, por favor intente nuevamente");
				}

			} else
				JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente");
		} else if (e.getSource() == btnCancelar) {
			cancelarAccion();
		}
	}

	private void cancelarAccion() {
		habilitarCampos(false);
		accionAgregar = 0;
		accionModificar = 0;
		limpiarCampos();
		habilitarBotones(false);
		btnAgregar.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnAgregar.requestFocus();
	}

	private void eliminarCliente() {

		if (logica.getController().elimnarCliente(clienteSelecionado.getId())) {
			JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
			habilitarCampos(false);
			limpiarCampos();
			clienteSelecionado = null;
			limpiarBusqueda();
			habilitarBotones(false);
		} else
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");

	}

	private void modicarCliente() {
		ClientesVo cliente = new ClientesVo();
		String codigo = panelForm.getTxtCodigo().getText();
		codigo = codigo.replaceFirst("CL_", "");
		cliente.setCodigo(Integer.parseInt(codigo));
		cliente.setCedula(Long.parseLong(panelForm.getTxtCedula().getText()));
		cliente.setNombre(panelForm.getTxtNombre().getText());
		cliente.setApellido(panelForm.getTxtApellido().getText());
		cliente.setCelular(panelForm.getTxtCelular1().getText());
		cliente.setCelular2(panelForm.getTxtCelular2().getText());
		cliente.setCorreo(panelForm.getTxtCorreo().getText());
		cliente.setDirreccion(panelForm.getTxtDirrecion().getText());

		CiudadVo ciudad = new CiudadVo();
		ciudad.setId(676657776);
		cliente.setCiudad(ciudad);

		if (logica.getController().modicarCliente(cliente)) {
			JOptionPane.showMessageDialog(this, "Cliente modificado correctamente");
			habilitarCampos(false);
			limpiarCampos();
			clienteSelecionado = null;
			habilitarBotones(false);
			accionModificar = 0;
			btnAgregar.setEnabled(true);
			btnCancelar.setEnabled(false);
			limpiarBusqueda();
		} else
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");
	}

	private void agregarCliente() {
		ClientesVo cliente = new ClientesVo();
		cliente.setCedula(Long.parseLong(panelForm.getTxtCedula().getText()));
		cliente.setNombre(panelForm.getTxtNombre().getText());
		cliente.setApellido(panelForm.getTxtApellido().getText());
		cliente.setCelular(panelForm.getTxtCelular1().getText());
		cliente.setCelular2(panelForm.getTxtCelular2().getText());
		cliente.setCorreo(panelForm.getTxtCorreo().getText());
		cliente.setDirreccion(panelForm.getTxtDirrecion().getText());

		CiudadVo ciudad = new CiudadVo();
		ciudad.setId(676657776);
		cliente.setCiudad(ciudad);

		if (logica.getController().agregarCliente(cliente)) {

			int res = JOptionPane.showConfirmDialog(this, "Cliente agregado correctamente \n ¿Desea agregar otro?",
					"GNA Software dice:", 0);

			limpiarCampos();
			cargarClientes();
			if (res == 0) {
				panelForm.getTxtCedula().requestFocus();
			} else {
				accionAgregar = 0;
				btnCancelar.setEnabled(false);
				habilitarCampos(false);
			}

		} else
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");
	}

	private void habilitarCampos(boolean criterio) {
		panelForm.getTxtCedula().setEnabled(criterio);
		panelForm.getTxtNombre().setEnabled(criterio);
		panelForm.getTxtApellido().setEnabled(criterio);
		panelForm.getTxtCelular1().setEnabled(criterio);
		panelForm.getTxtCelular2().setEnabled(criterio);
		panelForm.getTxtCorreo().setEnabled(criterio);
		panelForm.getTxtDirrecion().setEnabled(criterio);
		panelForm.getCmbxCiudad().setEnabled(criterio);
		panelForm.getCmbxDepartamento().setEnabled(criterio);
	}

	private void limpiarCampos() {

		panelForm.getTxtCodigo().setText("");
		panelForm.getTxtCedula().setText("");
		panelForm.getTxtNombre().setText("");
		panelForm.getTxtApellido().setText("");
		panelForm.getTxtCelular1().setText("");
		panelForm.getTxtCelular2().setText("");
		panelForm.getTxtCorreo().setText("");
		panelForm.getTxtDirrecion().setText("");
		panelForm.getCmbxCiudad().setSelectedItem(0);
		panelForm.getCmbxDepartamento().setSelectedItem(0);
	}

	public void recargarModulo() {

		limpiarCampos();
		limpiarBusqueda();
		habilitarBotones(false);
		habilitarCampos(false);
		btnAgregar.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnAgregar.requestFocus();

	}

	public void limpiarBusqueda() {
		txtBuscar.setText("Busqueda por cedula, nombre o telefono");
		txtBuscar.setForeground(Color.GRAY);
		cargarClientes();
	}

	public void setAcciongregar(int accionAgregar) {
		this.accionAgregar = accionAgregar;
		habilitarCampos(true);
		habilitarBotones(false);
		btnCancelar.setEnabled(true);
		panelForm.getTxtCedula().requestFocus();

	}
}
