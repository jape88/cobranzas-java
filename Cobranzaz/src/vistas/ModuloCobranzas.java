package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import paneles.PnlHead;
import recursos.Utilidades;
import vo.AcreedorVo;
import vo.CiudadVo;
import vo.ClientesVo;
import vo.DeudaVo;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.util.Date;
import java.util.Locale;

import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;

import dialogs.DialogAcreedor;
import dialogs.DialogCliente;
import dialogs.DialogViewCobros;

import java.awt.Cursor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.components.JSpinField;

public class ModuloCobranzas extends ModuloGeneral implements ActionListener {

	private JPanel contentPane;
	private Logica logica;
	private Inicio inicio;
	private ModuloCobranzas modulo;
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtCedulaAcreedor;

	private DefaultTableModel tableModel;
	private JTextField txtCedula;
	private JComboBox<Object> cmbxDocumento;
	private JComboBox<Object> cmbxModalidad;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;

	// 0 para empezar a agregar
	// 1 para finalizar agregar
	private int accionAgregar = 0;
	protected DeudaVo deudaSelecionado;

	// 0 para empezar a modicar
	// 1 para finalizar modicar
	private int accionModificar = 0;
	private JButton btnCancelar;
	private JDateChooser txtFecha;
	private JTextField txtValorDeuda;
	private JTextField txtInteres;
	private JTextField txtHonorarios;
	private JTextField txtMora;
	private JTextField txtCuotas;
	private JButton btnBuscarCliente;
	private JButton btnBuscarAcreedor;
	private JLabel lblTotalApagar;
	private JLabel lblValorCouta;
	private JButton btnCobros;
	private JButton btnPagos;
	private JLabel lblCoutaExtraordinaria;
	private JLabel lblValorInteresMora;
	private JLabel lblValorAbonos;
	private JLabel lblTotalDeuda;

	private ClientesVo clienteDeuda;
	private AcreedorVo AcreedorDeuda;
	protected String otroDocumento;
	private JLabel lblOtroDocumento;
	private ArrayList<DeudaVo> listaDeudad;
	private double deudaCapital;
	private double interes;
	private double interesMora;
	private double honorarios;
	private int coutas;
	private double deudaInflada;
	private double ValorinteresMora;
	private double valorCuota;
	private double valorCoutaExtra;

	/**
	 * Create the frame.
	 */
	public ModuloCobranzas(Inicio inicio, Logica logica) {
		super(inicio, "Cobranzas");

		otroDocumento = "";

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


		setTitle("Sistema de gestión de datos GNA");

		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(100, 100, 810, 576);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setLocationRelativeTo(null);
		JPanel panelFooter = new PnlFooter();
		FlowLayout flowLayout = (FlowLayout) panelFooter.getLayout();
		flowLayout.setVgap(5);
		contentPane.add(panelFooter, BorderLayout.SOUTH);

		JPanel panelCenter = new PnlConFondo("/assets/imagen 70.png");
		panelCenter.setBackground(Color.WHITE);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JPanel panelHead = new PnlHead("/assets/cobranza.png", this);
		panelHead.setBounds(0, 0, 810, 110);
		panelCenter.add(panelHead);

		((PnlHead) panelHead).getLblModulo().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				recargarModulo();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 373, 780, 141);
		panelCenter.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				cancelarAccion();
				int selected = table.getSelectedRow();
				deudaSelecionado = listaDeudad.get(selected);
				txtCedula.setText(
						deudaSelecionado.getClientes().getNombre() + " " + deudaSelecionado.getClientes().getApellido()
								+ " " + deudaSelecionado.getClientes().getCedula());
				txtCedulaAcreedor.setText(
						deudaSelecionado.getAcreedor().getNombre() + " " + deudaSelecionado.getAcreedor().getApellido()
								+ " " + deudaSelecionado.getAcreedor().getCedula());
				cmbxDocumento.setSelectedItem(deudaSelecionado.getDocumento());
				cmbxModalidad.setSelectedIndex(deudaSelecionado.getModalidad());
				txtValorDeuda.setText(deudaSelecionado.getDeudaCapital() + "");
				txtInteres.setText(deudaSelecionado.getInteres() + "");
				txtHonorarios.setText(deudaSelecionado.getHonorarios() + "");
				txtMora.setText(deudaSelecionado.getMora() + "");
				txtCuotas.setText(deudaSelecionado.getCuotas() + "");
				lblTotalApagar.setText(Utilidades.formatoNumero(deudaSelecionado.getDeudaCapital()));
				lblValorCouta.setText(Utilidades.formatoNumero(deudaSelecionado.getValorCouta()));
				lblCoutaExtraordinaria.setText(Utilidades.formatoNumero(deudaSelecionado.getValorExtraordinario()));
				lblTotalDeuda.setText(Utilidades.formatoNumero(deudaSelecionado.getDeudaCapital()));
				
				habilitarBotones(true);
			}
		});
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Cedula", "Nombre", "Tipo documento",
				"Modalidad de cobro", "Total a pagar", "Abonos generados", "Deuda actual" }) {

			Class<?>[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Double.class,
					Double.class, Double.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		table.setModel(tableModel);

		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		scrollPane.setViewportView(table);

		txtBuscar = new JTextField();
		txtBuscar.setToolTipText("Busqueda avanzada");
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarDeudas();
				}
			}
		});
		txtBuscar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtBuscar.getText().equals("Busqueda avanzada")) {
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
					txtBuscar.setText("Busqueda avanzada");
				}
			}
		});
		txtBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBuscar.setText("Busqueda avanzada");
		txtBuscar.setForeground(Color.GRAY);
		txtBuscar.setBounds(553, 339, 237, 20);
		panelCenter.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Busqueda de clientes");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_3.setBounds(618, 359, 172, 14);
		panelCenter.add(lblNewLabel_3);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		ImageIcon imgAddPlusRender = Utilidades.renderImage("/assets/plus.png", 17, 17);
		btnAgregar.setIcon(imgAddPlusRender);
		btnAgregar.setBounds(10, 339, 110, 23);
		panelCenter.add(btnAgregar);

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(this);

		ImageIcon imgUpdateRender = Utilidades.renderImage("/assets/update1.png", 17, 17);
		btnModificar.setIcon(imgUpdateRender);
		btnModificar.setBounds(130, 339, 110, 23);
		panelCenter.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(this);
		ImageIcon imgAddUsuerRender = Utilidades.renderImage("/assets/eliminar.png", 17, 17);
		btnEliminar.setIcon(imgAddUsuerRender);
		btnEliminar.setBounds(250, 339, 110, 23);
		panelCenter.add(btnEliminar);

		JLabel lblModuloDeClientes = new JLabel("Modulo para gesti\u00F3n de cartera");
		lblModuloDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblModuloDeClientes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModuloDeClientes.setBounds(0, 116, 810, 20);
		panelCenter.add(lblModuloDeClientes);

		JPanel panel_1 = new PnlConFondo("/assets/imagen 70.png");
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de la deuda",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 139, 533, 189);
		panelCenter.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCodigo = new JLabel("Cedula Cliente");
		lblCodigo.setBounds(10, 54, 106, 20);
		panel_1.add(lblCodigo);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCedula = new JTextField();
		txtCedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarCliente();
				}
			}
		});
		txtCedula.setEnabled(false);
		txtCedula.setBounds(124, 54, 119, 20);
		panel_1.add(txtCedula);
		txtCedula.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Valor de la deuda $");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(306, 24, 127, 20);
		panel_1.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblCedula = new JLabel("Tipo documento");
		lblCedula.setBounds(10, 115, 106, 20);
		panel_1.add(lblCedula);
		lblCedula.setFont(new Font("Tahoma", Font.BOLD, 12));

		cmbxDocumento = new JComboBox<Object>();
		cmbxDocumento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					cmbxModalidad.requestFocus();
				}
			}
		});

		cmbxDocumento.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				{
					if (cmbxDocumento.getSelectedIndex() == 6 && otroDocumento.equals("")) {
						otroDocumento = JOptionPane.showInputDialog(null, "Especifique", "GNA Software dice:",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (cmbxDocumento.getSelectedIndex() != 6) {
						otroDocumento = "";
					}
					lblOtroDocumento.setText(otroDocumento);
				}
			}
		});
		cmbxDocumento.setEnabled(false);
		cmbxDocumento.setBounds(124, 115, 140, 20);
		panel_1.add(cmbxDocumento);
		cmbxDocumento.setModel(new DefaultComboBoxModel<Object>(new String[] { "Seleccione", "Letra ", "Cheque",
				"Pagare", "Pacatura", "Contrato", "Otro (Especifique)" }));

		cmbxModalidad = new JComboBox<Object>();
		cmbxModalidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtValorDeuda.requestFocus();
				}
			}
		});
		cmbxModalidad.setEnabled(false);
		cmbxModalidad.setBounds(124, 146, 140, 20);
		panel_1.add(cmbxModalidad);
		cmbxModalidad.setModel(new DefaultComboBoxModel<Object>(new String[] { "Seleccione", "Mensual", "Quincenal" }));

		JLabel lblDepartamento = new JLabel("Honorarios");
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartamento.setBounds(316, 84, 117, 20);
		panel_1.add(lblDepartamento);
		lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblCelular = new JLabel("Tasa de interes");
		lblCelular.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCelular.setBounds(316, 54, 117, 20);
		panel_1.add(lblCelular);
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCedulaAcreedor = new JTextField();
		txtCedulaAcreedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarAcreedor();
				}
			}
		});
		txtCedulaAcreedor.setEnabled(false);
		txtCedulaAcreedor.setBounds(124, 84, 119, 20);
		panel_1.add(txtCedulaAcreedor);
		txtCedulaAcreedor.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cedula Acreedor");
		lblNewLabel_4.setBounds(10, 84, 106, 20);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_5 = new JLabel("Modalidad");
		lblNewLabel_5.setBounds(10, 147, 106, 20);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_8_1 = new JLabel("Fecha de igreso");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8_1.setBounds(10, 24, 106, 20);
		panel_1.add(lblNewLabel_8_1);

		txtInteres = new JTextField();
		txtInteres.setText("10");
		txtInteres.setHorizontalAlignment(SwingConstants.RIGHT);
		txtInteres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtHonorarios.requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				Utilidades.soloNumeros(e);
			}
		});
		txtInteres.setEnabled(false);
		txtInteres.setBounds(443, 54, 56, 20);
		panel_1.add(txtInteres);

		txtHonorarios = new JTextField();
		txtHonorarios.setText("20");
		txtHonorarios.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHonorarios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtMora.requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				Utilidades.soloNumeros(e);
			}
		});
		txtHonorarios.setEnabled(false);
		txtHonorarios.setBounds(443, 84, 56, 20);
		panel_1.add(txtHonorarios);

		txtValorDeuda = new JTextField();
		txtValorDeuda.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValorDeuda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtInteres.requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				Utilidades.soloNumeros(e);
			}
		});
		txtValorDeuda.setEnabled(false);
		txtValorDeuda.setBounds(443, 24, 80, 20);
		panel_1.add(txtValorDeuda);

		btnBuscarCliente = new JButton();
		btnBuscarCliente.setEnabled(false);
		btnBuscarCliente.addActionListener(this);
		btnBuscarCliente.setIcon(Utilidades.renderImage("/assets/search.png", 18, 20));
		btnBuscarCliente.setBounds(244, 54, 20, 20);
		panel_1.add(btnBuscarCliente);

		btnBuscarAcreedor = new JButton();
		btnBuscarAcreedor.setEnabled(false);
		btnBuscarAcreedor.addActionListener(this);
		btnBuscarAcreedor.setIcon(Utilidades.renderImage("/assets/search.png", 18, 20));
		btnBuscarAcreedor.setBounds(244, 84, 20, 20);
		panel_1.add(btnBuscarAcreedor);

		txtFecha = new JDateChooser(new Date());
		txtFecha.setDateFormatString("dd/MM/yyyy");
		txtFecha.setEnabled(false);
		txtFecha.getCalendarButton().setIcon(Utilidades.renderImage("/assets/calendario.png", 17, 17));
		txtFecha.setBounds(124, 24, 140, 20);
		panel_1.add(txtFecha);

		JLabel lblCelular_1 = new JLabel("%");
		lblCelular_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCelular_1.setBounds(509, 54, 14, 20);
		panel_1.add(lblCelular_1);

		JLabel lblCelular_1_1 = new JLabel("%");
		lblCelular_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCelular_1_1.setBounds(509, 84, 14, 20);
		panel_1.add(lblCelular_1_1);

		txtMora = new JTextField();
		txtMora.setText("5");
		txtMora.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtCuotas.requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				Utilidades.soloNumeros(e);
			}
		});
		txtMora.setBounds(443, 115, 56, 20);
		panel_1.add(txtMora);
		txtMora.setEnabled(false);

		JLabel lblCelular_1_2 = new JLabel("%");
		lblCelular_1_2.setBounds(509, 115, 14, 20);
		panel_1.add(lblCelular_1_2);
		lblCelular_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblCiudad = new JLabel("Tasa por mora");
		lblCiudad.setBounds(316, 115, 117, 20);
		panel_1.add(lblCiudad);
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblOtroDocumento = new JLabel("");
		lblOtroDocumento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOtroDocumento.setBounds(274, 115, 70, 20);
		panel_1.add(lblOtroDocumento);

		JLabel lblNewLabel_8 = new JLabel("Coutas");
		lblNewLabel_8.setBounds(363, 148, 70, 20);
		panel_1.add(lblNewLabel_8);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCuotas = new JTextField();
		txtCuotas.setText("12");
		txtCuotas.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCuotas.setBounds(443, 148, 56, 20);
		panel_1.add(txtCuotas);
		txtCuotas.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					int res = JOptionPane.showConfirmDialog(null, "¿Desea calcular ahora?", "GNA Software dice:",
							JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION) {
						calcularAhora();
						btnAgregar.setEnabled(true);
						btnAgregar.requestFocus();
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				Utilidades.soloNumerosEnteros(e);
			}
		});
		txtCuotas.setEnabled(false);

		JLabel lblCelular_1_1_1 = new JLabel("#");
		lblCelular_1_1_1.setBounds(509, 146, 14, 20);
		panel_1.add(lblCelular_1_1_1);
		lblCelular_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		JButton button = new JButton(">>");
		button.setFont(new Font("SansSerif", Font.BOLD, 14));
		button.setMargin(new Insets(2, 2, 2, 2));
		button.setBounds(760, 517, 30, 23);
		panelCenter.add(button);

		JButton button_1 = new JButton(">");
		button_1.setMargin(new Insets(2, 2, 2, 2));
		button_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_1.setBounds(728, 517, 30, 23);
		panelCenter.add(button_1);

		JButton button_2 = new JButton("1");
		button_2.setMargin(new Insets(2, 2, 2, 2));
		button_2.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_2.setBounds(696, 517, 30, 23);
		panelCenter.add(button_2);

		JButton button_3 = new JButton("<");
		button_3.setMargin(new Insets(2, 2, 2, 2));
		button_3.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_3.setBounds(664, 517, 30, 23);
		panelCenter.add(button_3);

		JButton button_4 = new JButton("<<");
		button_4.setMargin(new Insets(2, 2, 2, 2));
		button_4.setFont(new Font("SansSerif", Font.BOLD, 14));
		button_4.setBounds(632, 517, 30, 23);
		panelCenter.add(button_4);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(this);
		ImageIcon imgCancelarRender = Utilidades.renderImage("/assets/cancelicon.png", 17, 17);
		btnCancelar.setIcon(imgCancelarRender);
		btnCancelar.setBounds(370, 339, 110, 23);
		panelCenter.add(btnCancelar);

		JPanel panel = new PnlConFondo("/assets/imagen 70.png");
		panel.setBounds(553, 139, 237, 141);
		panelCenter.add(panel);
		panel.setBorder(new TitledBorder(null, "Estado de deuda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);

		JLabel lblNewLabel_6_1 = new JLabel("Total a pagar $");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1.setBounds(10, 21, 115, 14);
		panel.add(lblNewLabel_6_1);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lblNewLabel_6_1_1 = new JLabel("Valor cuota $");
		lblNewLabel_6_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1_1.setBounds(10, 40, 115, 14);
		panel.add(lblNewLabel_6_1_1);
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lblNewLabel_6_1_2 = new JLabel("Cuota extraordinaria $");
		lblNewLabel_6_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1_2.setBounds(10, 59, 115, 14);
		panel.add(lblNewLabel_6_1_2);
		lblNewLabel_6_1_2.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lblNewLabel_6_1_2_1_1 = new JLabel("Intereses por mora $");
		lblNewLabel_6_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1_2_1_1.setBounds(10, 78, 115, 14);
		panel.add(lblNewLabel_6_1_2_1_1);
		lblNewLabel_6_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lblNewLabel_6_1_2_1 = new JLabel("Abonos $");
		lblNewLabel_6_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1_2_1.setBounds(10, 97, 115, 14);
		panel.add(lblNewLabel_6_1_2_1);
		lblNewLabel_6_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lblNewLabel_6_1_2_1_2 = new JLabel("Total deuda $");
		lblNewLabel_6_1_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_6_1_2_1_2.setBounds(10, 117, 115, 14);
		panel.add(lblNewLabel_6_1_2_1_2);

		lblTotalApagar = new JLabel("0,0");
		lblTotalApagar.setForeground(Color.RED);
		lblTotalApagar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalApagar.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTotalApagar.setBounds(135, 21, 80, 14);
		panel.add(lblTotalApagar);

		lblValorCouta = new JLabel("0,0");
		lblValorCouta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorCouta.setForeground(Color.RED);
		lblValorCouta.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblValorCouta.setBounds(135, 40, 80, 14);
		panel.add(lblValorCouta);

		lblValorInteresMora = new JLabel("0,0");
		lblValorInteresMora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorInteresMora.setForeground(Color.RED);
		lblValorInteresMora.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblValorInteresMora.setBounds(135, 78, 80, 14);
		panel.add(lblValorInteresMora);

		lblCoutaExtraordinaria = new JLabel("0,0");
		lblCoutaExtraordinaria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCoutaExtraordinaria.setForeground(Color.RED);
		lblCoutaExtraordinaria.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCoutaExtraordinaria.setBounds(135, 59, 80, 14);
		panel.add(lblCoutaExtraordinaria);

		lblTotalDeuda = new JLabel("0,0");
		lblTotalDeuda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalDeuda.setForeground(Color.RED);
		lblTotalDeuda.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTotalDeuda.setBounds(135, 116, 80, 14);
		panel.add(lblTotalDeuda);

		lblValorAbonos = new JLabel("0,0");
		lblValorAbonos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorAbonos.setForeground(Color.RED);
		lblValorAbonos.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblValorAbonos.setBounds(135, 97, 80, 14);
		panel.add(lblValorAbonos);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(553, 282, 237, 46);
		panelCenter.add(panel_2);
		panel_2.setLayout(null);

		btnPagos = new JButton("");
		btnPagos.setEnabled(false);
		btnPagos.setBounds(123, 11, 22, 22);
		panel_2.add(btnPagos);
		btnPagos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPagos.setToolTipText("Abonos realizados");
		btnPagos.setIcon(Utilidades.renderImage("/assets/pagosO.PNG", 20, 20));

		btnCobros = new JButton();
		btnCobros.setEnabled(false);
		btnCobros.addActionListener(this);
		btnCobros.setBounds(98, 11, 22, 22);
		panel_2.add(btnCobros);
		btnCobros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCobros.setToolTipText("Facturas de cobro");
		btnCobros.setIcon(Utilidades.renderImage("/assets/facturaO.PNG", 20, 20));

		cargarDeudas();

	}

	protected void calcularAhora() {
		// Capital neto de la deuda
		deudaCapital = Double.parseDouble(txtValorDeuda.getText().replace(",", "."));
		// % Interes
		interes = Double.parseDouble(txtInteres.getText().replace(",", "."));
		// % Honorarios
		honorarios = Double.parseDouble(txtHonorarios.getText().replace(",", "."));
		// % Interes por mora
		interesMora = Double.parseDouble(txtMora.getText().replace(",", "."));
		// Cuotas en las que paga
		coutas = Integer.parseInt(txtCuotas.getText());
		// Capital + intereses y honorarios
		deudaInflada = deudaCapital + ((deudaCapital * interes) / 100) + ((deudaCapital * honorarios) / 100);
		// Valor en que queda la cuota
		valorCuota = deudaInflada / coutas;
		// Valor de intereses extras por mora
		ValorinteresMora = (valorCuota * interesMora) / 100;
		// Valor de cuota morosa
		valorCoutaExtra = valorCuota + ValorinteresMora;

		lblTotalApagar.setText(Utilidades.formatoNumero(deudaInflada));
		lblValorCouta.setText(Utilidades.formatoNumero(valorCuota));
		lblCoutaExtraordinaria.setText(Utilidades.formatoNumero(valorCoutaExtra));
		lblTotalDeuda.setText(Utilidades.formatoNumero(deudaInflada));

	}

	private void habilitarBotones(boolean enabled) {
		btnModificar.setEnabled(enabled);
		btnEliminar.setEnabled(enabled);
		btnPagos.setEnabled(enabled);
		btnCobros.setEnabled(enabled);
	}

	protected void buscarDeudas() {

//		String criterio = txtBuscar.getText();
//		if (criterio.equals("")) {
//			JOptionPane.showMessageDialog(this, "Por favor ingrese un criterio de busqueda");
//		} else {
//			listaClientes = logica.getController().BuscarClientes(criterio);
//			tableModel.setRowCount(0);
//			Object[] fila = null;
//
//			int i = 1;
//			for (ClientesVo clienteVo : listaClientes) {
//
//				fila = new Object[7];
//				fila[0] = "CL_" + clienteVo.getCodigo();
//				fila[1] = clienteVo.getCedula();
//				fila[2] = clienteVo.getNombre() + " " + clienteVo.getApellido();
//				fila[3] = clienteVo.getCelular();
//				fila[4] = clienteVo.getDirreccion();
//				fila[5] = clienteVo.getCiudad().getNombre();
//				fila[6] = clienteVo.getCiudad().getDepartamento().getNombre();
//
//				tableModel.addRow(fila);
//				i++;
//			}
//			table.setModel(tableModel);
//		}
	}

	protected void cargarDeudas() {
		listaDeudad = logica.getController().listaDeudas();
		tableModel.setRowCount(0);
		Object[] fila = null;

		for (DeudaVo deuda : listaDeudad) {
			fila = new Object[7];
			fila[0] = deuda.getClientes().getCedula();
			fila[1] = deuda.getClientes().getNombre() + " " + deuda.getClientes().getApellido();
			fila[2] = deuda.getDocumento();
			if (deuda.getModalidad() == Utilidades.TIPO_MENSUAL) {
				fila[3] = "Mensual";
			} else
				fila[3] = "Quincenal";

			fila[4] = deuda.getTotalDeuda();
			fila[5] = 0;
			fila[6] = deuda.getTotalDeuda();

			tableModel.addRow(fila);

		}
		table.setModel(tableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnBuscarCliente) {
			buscarCliente();
		} else if (e.getSource() == btnBuscarAcreedor) {
			buscarAcreedor();
		} else if (e.getSource() == btnAgregar) {

			if (accionAgregar == 0) {
				int res = JOptionPane.showConfirmDialog(this, "¿Desea agregar una nueva deuda?", "GNA Software dice:",
						0);
				if (res == 0) {
					accionAgregar = 1;
					limpiarCampos();
					habilitarCamposForm(true);
					habilitarBotones(false);
					btnAgregar.setText("Guardar");
					btnCancelar.setEnabled(true);
					txtCedula.requestFocus();
				}
			} else if (accionAgregar == 1) {
				int res = JOptionPane.showConfirmDialog(this, "¿Todo listo para guardar y generar cobros?",
						"GNA Software dice", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					aregarDeuda();
				}

			}
		} else if (e.getSource() == btnModificar) {
			if (deudaSelecionado != null) {
				if (accionModificar == 0) {
					int res = JOptionPane.showConfirmDialog(this, "¿Desea modicar los datos de la deuda?",
							"GNA Software dice:", 0);
					if (res == 0) {
						btnModificar.setText("Guardar");
						accionModificar = 1;
						// limpiarCampos();
						habilitarCamposForm(true);
						btnAgregar.setEnabled(false);
						btnEliminar.setEnabled(false);
						btnCancelar.setEnabled(true);
						txtCedula.requestFocus();
					}
				} else if (accionModificar == 1) {
					modicarDeuda();
				}
			} else
				JOptionPane.showMessageDialog(this, "Debe seleccionar una deuda");

		} else if (e.getSource() == btnEliminar) {
			if (deudaSelecionado != null) {

				int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea elimnar la deuda?",
						"GNA Software dice:", 0);
				if (res == 0) {
					String codigo = JOptionPane.showInputDialog("Ingrese codigo de seguridad");
					if (codigo.equals("25057")) {
						eliminarDeuda();
						deudaSelecionado = null;
						cargarDeudas();
					} else
						JOptionPane.showMessageDialog(this, "Codigo incorrecto, por favor intente nuevamente");
				}

			} else
				JOptionPane.showMessageDialog(this, "Debe seleccionar una deuda");
		} else if (e.getSource() == btnCancelar) {
			cancelarAccion();
		} else if (e.getSource() == btnCobros) {
			int res = JOptionPane.showConfirmDialog(this, "¿Desea ver estado de cobors?", "GNA Software dice:",
					JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {
				abrirDialogCobros();
			}
		}
	}

	private void abrirDialogCobros() {
		DialogViewCobros dialogViewCobros = new DialogViewCobros(this, deudaSelecionado, logica);
		dialogViewCobros.setVisible(true);
	}

	public void buscarAcreedor() {

		String cedula = txtCedulaAcreedor.getText() + "";
		if (!cedula.equals("")) {
			AcreedorDeuda = logica.getController().BuscarAcreedor(cedula);
			if (AcreedorDeuda != null) {
				txtCedulaAcreedor.setText(AcreedorDeuda.getNombre() + " " + AcreedorDeuda.getApellido() + " - "
						+ AcreedorDeuda.getCedula());
				cmbxDocumento.requestFocus();
			} else {
				int res = JOptionPane.showConfirmDialog(this, "El acreedor no existe ¿Desea crearlo?",
						"GNA Software dice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.YES_OPTION) {
					DialogAcreedor dialogAcreedor = new DialogAcreedor(this, logica);
					dialogAcreedor.getTxtCedula().setText(cedula);
					dialogAcreedor.setVisible(true);

				}
			}
		} else
			JOptionPane.showMessageDialog(this, "Debe ingresar una cedula", "GNA Software dice:",
					JOptionPane.WARNING_MESSAGE);
	}

	public void buscarCliente() {
		String cedula = txtCedula.getText() + "";
		if (!cedula.equals("")) {
			clienteDeuda = logica.getController().BuscarCliete(cedula);
			if (clienteDeuda != null) {
				txtCedula.setText(
						clienteDeuda.getNombre() + " " + clienteDeuda.getApellido() + " - " + clienteDeuda.getCedula());
				txtCedulaAcreedor.requestFocus();
			} else {
				int res = JOptionPane.showConfirmDialog(this, "El cliente no existe ¿Desea crearlo?",
						"GNA Software dice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.YES_OPTION) {
					DialogCliente dialogCliente = new DialogCliente(this, logica);
					dialogCliente.getTxtCedula().setText(cedula);
					// dialogCliente.getTxtCedula().requestFocus();
					dialogCliente.setVisible(true);

				}
			}
		} else
			JOptionPane.showMessageDialog(this, "Debe ingresar una cedula", "GNA Software dice:",
					JOptionPane.WARNING_MESSAGE);
	}

	private void cancelarAccion() {
		habilitarCamposForm(false);
		accionAgregar = 0;
		accionModificar = 0;
		limpiarCampos();
		habilitarBotones(false);
		btnAgregar.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnAgregar.requestFocus();
	}

	private void eliminarDeuda() {

		if (logica.getController().elimnarCliente(deudaSelecionado.getId())) {
			JOptionPane.showMessageDialog(this, "Deuda eliminada correctamente");
			habilitarCamposForm(false);
			limpiarCampos();
			deudaSelecionado = null;
			limpiarBusqueda();
			habilitarBotones(false);
		} else
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");

	}

	private void modicarDeuda() {

		// DeudaVo deuda = new DeudaVo();

		deudaSelecionado.setFecha(Utilidades.formatoFecha(txtFecha.getDate()));
		deudaSelecionado.setFechaCalculos(txtFecha.getDate());

		deudaSelecionado.setAcreedor(AcreedorDeuda);
		deudaSelecionado.setClientes(clienteDeuda);

		String doc;
		int docSelect = cmbxDocumento.getSelectedIndex();
		if (docSelect == 6) {
			doc = otroDocumento;
		} else {
			doc = cmbxDocumento.getSelectedItem() + "";
		}
		deudaSelecionado.setDocumento(doc);
		deudaSelecionado.setModalidad(cmbxModalidad.getSelectedIndex());

		// Vuelve y calcula para tomar las ultimas modificaciones de ser el caso
		calcularAhora();
		deudaSelecionado.setDeudaCapital(deudaCapital);
		deudaSelecionado.setInteres(interes);
		deudaSelecionado.setHonorarios(honorarios);
		deudaSelecionado.setMora(interesMora);
		deudaSelecionado.setCuotas(coutas);
		deudaSelecionado.setEstado(Utilidades.ESTADO_DEUDA_PENDIENTE);

		deudaSelecionado.setTotalDeuda(deudaInflada);
		deudaSelecionado.setValorCouta(valorCuota);
		deudaSelecionado.setValorExtraordinario(valorCoutaExtra);

		if (logica.getController().modificarDeuda(deudaSelecionado)) {
			JOptionPane.showMessageDialog(this, "Deuda modificada correctamente");

			limpiarCampos();
			cargarDeudas();
			accionModificar = 0;
			btnModificar.setText("Modificar");
			btnCancelar.setEnabled(false);
			habilitarCamposForm(false);

		} else
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");
	}

	private void aregarDeuda() {
		DeudaVo deuda = new DeudaVo();

		deuda.setFecha(Utilidades.formatoFecha(txtFecha.getDate()));
		deuda.setFechaCalculos(txtFecha.getDate());

		deuda.setAcreedor(AcreedorDeuda);
		deuda.setClientes(clienteDeuda);

		String doc;
		int docSelect = cmbxDocumento.getSelectedIndex();
		if (docSelect == 6) {
			doc = otroDocumento;
		} else {
			doc = cmbxDocumento.getSelectedItem() + "";
		}
		deuda.setDocumento(doc);
		deuda.setModalidad(cmbxModalidad.getSelectedIndex());

		// Vuelve y calcula para tomar las ultimas modificaciones de ser el caso
		calcularAhora();
		deuda.setDeudaCapital(deudaCapital);
		deuda.setInteres(interes);
		deuda.setHonorarios(honorarios);
		deuda.setMora(interesMora);
		deuda.setCuotas(coutas);
		deuda.setEstado(Utilidades.ESTADO_DEUDA_PENDIENTE);

		deuda.setTotalDeuda(deudaInflada);
		deuda.setValorCouta(valorCuota);
		deuda.setValorExtraordinario(valorCoutaExtra);

		if (logica.getController().agregarDeuda(deuda)) {
			int res = JOptionPane.showConfirmDialog(this,
					"Deuda agregada correctamente \n ¿Desea agregar otra deuda de un cliente?", "GNA Software dice:",
					0);

			limpiarCampos();
			cargarDeudas();
			if (res == 0) {
				txtCedula.requestFocus();
			} else {
				accionAgregar = 0;
				btnCancelar.setEnabled(false);
				habilitarCamposForm(false);
			}
		} else
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");
	}

	private void habilitarCamposForm(boolean enabled) {
		txtFecha.setEnabled(enabled);
		txtCedula.setEnabled(enabled);
		btnBuscarCliente.setEnabled(enabled);
		txtCedulaAcreedor.setEnabled(enabled);
		btnBuscarAcreedor.setEnabled(enabled);
		cmbxDocumento.setEnabled(enabled);
		cmbxModalidad.setEnabled(enabled);
		txtValorDeuda.setEnabled(enabled);
		txtInteres.setEnabled(enabled);
		txtHonorarios.setEnabled(enabled);
		txtMora.setEnabled(enabled);
		txtCuotas.setEnabled(enabled);
	}

	private void limpiarCampos() {

		txtFecha.setDate(new Date());
		txtCedula.setText("");
		txtCedulaAcreedor.setText("");
		cmbxDocumento.setSelectedIndex(0);
		lblOtroDocumento.setText("");
		cmbxModalidad.setSelectedIndex(0);
		txtValorDeuda.setText("");
		txtInteres.setText("10");
		txtHonorarios.setText("20");
		txtMora.setText("5");
		txtCuotas.setText("12");
		lblTotalApagar.setText("0,0");
		lblCoutaExtraordinaria.setText("0,0");
		lblValorCouta.setText("0,0");
		lblValorInteresMora.setText("0,0");
		lblTotalDeuda.setText("0,0");
		lblValorAbonos.setText("0,0");
		btnAgregar.setText("Agregar");
		btnModificar.setText("Modificar");
		clienteDeuda = null;
		AcreedorDeuda = null;
	}

	public void recargarModulo() {

		limpiarCampos();
		limpiarBusqueda();
		habilitarBotones(false);
		habilitarCamposForm(false);
		btnAgregar.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnAgregar.requestFocus();

	}

	public void limpiarBusqueda() {
		txtBuscar.setText("Busqueda avanzada");
		txtBuscar.setForeground(Color.GRAY);
		cargarDeudas();
	}
}
