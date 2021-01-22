package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import logica.Logica;
import paneles.PnlConFondo;
import paneles.PnlFooter;
import paneles.PnlHead;
import recursos.Utilidades;
import vo.AcreedorVo;
import vo.ClientesVo;
import vo.CobrosVo;
import vo.DeudaVo;

import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Date;

import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;

import dialogs.DialogAcreedor;

import javax.swing.JCheckBox;
import java.awt.ComponentOrientation;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ModuloFacturacion extends ModuloGeneral implements ActionListener {

	private JPanel contentPane;
	private Logica logica;
	private Inicio inicio;
	private ModuloFacturacion modulo;
	private JTable tableCobros;
	private JTextField txtCedulaAcreedor;

	private DefaultTableModel tableModel;
	private JTextField txtCedula;
	private JButton btnFacturar;

	protected DeudaVo deudaSelecionado;

	private JButton btnCancelar;
	private JButton btnBuscarCliente;
	private JButton btnBuscarAcreedor;

	private ClientesVo clienteEncontrado;
	private AcreedorVo acreedorEncontrado;
	protected String otroDocumento;
	private JLabel lblOtroDocumento;
	private JTextField textField;
	private JTable tableAbonos;
	private JDateChooser txtFecha;
	private JCheckBox chbxTodos;
	private JButton btnRetirar;
	private JButton btnCargarTodos;
	private JButton btnCargarUltimo;
	private JButton btnCalcular;
	private JButton btnSaldar;
	private ArrayList<CobrosVo> listaCobrosCliente;
	private ArrayList<CobrosVo> listaCobrosFacturar;
	private JLabel lblNombreCliente;
	private JLabel lblNombreAcreedor;
	private DefaultTableModel tableModelAbono;

	/**
	 * Create the frame.
	 */
	public ModuloFacturacion(Inicio inicio, Logica logica) {
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
		setBounds(100, 100, 897, 540);
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
		panelCenter.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JPanel panelHead = new PnlHead("/assets/registradora.png", this);
		panelHead.setBounds(0, 0, 897, 110);
		panelCenter.add(panelHead);

		((PnlHead) panelHead).getLblModulo().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				recargarModulo();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 286, 533, 187);
		panelCenter.add(scrollPane);

		tableCobros = new JTable();
		tableCobros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				for (int i = 0; i < tableCobros.getRowCount(); i++) {
//					boolean valueAt=(boolean) tableCobros.getValueAt(i, 7);
//					if(!valueAt) {
//						if(tableCobros.getSelectedRow()!=0) {
//							boolean valueAtAnt=(boolean) tableCobros.getValueAt(i-1, 7);
//							if (valueAtAnt) {
//								
//							}else {
//								JCheckBox chek =(JCheckBox) tableCobros.getColumnModel().getColumn(7).getCellEditor();
//								JOptionPane.showMessageDialog(null, "No");
//							}
//						}
//					}else {
//						
//					}
//				}
			}
		});
		tableCobros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "# Referencia", "Fecha corte", "Estado",
				"# D.Ven", "Int. Mora", "Abonos ", "Total", "Saldar" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Integer.class, Double.class,
					Double.class, Double.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableCobros.setModel(tableModel);
		tableCobros.getColumnModel().getColumn(0).setResizable(false);
		tableCobros.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableCobros.getColumnModel().getColumn(1).setResizable(false);
		tableCobros.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableCobros.getColumnModel().getColumn(2).setResizable(false);
		tableCobros.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableCobros.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableCobros.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableCobros.getColumnModel().getColumn(6).setResizable(false);
		tableCobros.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableCobros.getColumnModel().getColumn(7).setResizable(false);
		tableCobros.getColumnModel().getColumn(7).setPreferredWidth(90);
		scrollPane.setViewportView(tableCobros);

		btnFacturar = new JButton("Facturar");
		btnFacturar.setEnabled(false);
		btnFacturar.addActionListener(this);
		ImageIcon imgAddPlusRender = Utilidades.renderImage("/assets/aceptarIcon.png", 17, 17);
		btnFacturar.setIcon(imgAddPlusRender);
		btnFacturar.setBounds(647, 476, 110, 23);
		panelCenter.add(btnFacturar);

		JLabel lblModuloDeClientes = new JLabel("Modulo para gestión de facturación");
		lblModuloDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblModuloDeClientes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModuloDeClientes.setBounds(0, 115, 897, 20);
		panelCenter.add(lblModuloDeClientes);

		JPanel panel_1 = new PnlConFondo("/assets/imagen 70.png");
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n de pago",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 139, 533, 113);
		panelCenter.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCodigo = new JLabel("Cedula Cliente");
		lblCodigo.setBounds(10, 25, 106, 20);
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
		txtCedula.setBounds(124, 25, 119, 20);
		panel_1.add(txtCedula);
		txtCedula.setColumns(10);

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
		txtCedulaAcreedor.setBounds(124, 50, 119, 20);
		panel_1.add(txtCedulaAcreedor);
		txtCedulaAcreedor.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cedula Acreedor");
		lblNewLabel_4.setBounds(10, 50, 106, 20);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnBuscarCliente = new JButton();
		btnBuscarCliente.setEnabled(false);
		btnBuscarCliente.addActionListener(this);
		btnBuscarCliente.setIcon(Utilidades.renderImage("/assets/search.png", 18, 20));
		btnBuscarCliente.setBounds(244, 25, 20, 20);
		panel_1.add(btnBuscarCliente);

		btnBuscarAcreedor = new JButton();
		btnBuscarAcreedor.setEnabled(false);
		btnBuscarAcreedor.addActionListener(this);
		btnBuscarAcreedor.setIcon(Utilidades.renderImage("/assets/search.png", 18, 20));
		btnBuscarAcreedor.setBounds(244, 50, 20, 20);
		panel_1.add(btnBuscarAcreedor);

		lblOtroDocumento = new JLabel("");
		lblOtroDocumento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOtroDocumento.setBounds(274, 115, 70, 20);
		panel_1.add(lblOtroDocumento);

		lblNombreCliente = new JLabel("Nombre cliente");
		lblNombreCliente.setForeground(Color.BLUE);
		lblNombreCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreCliente.setBounds(269, 25, 254, 20);
		panel_1.add(lblNombreCliente);

		lblNombreAcreedor = new JLabel("Nombre acreedor");
		lblNombreAcreedor.setForeground(Color.BLUE);
		lblNombreAcreedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreAcreedor.setBounds(269, 50, 254, 20);
		panel_1.add(lblNombreAcreedor);

		btnCargarUltimo = new JButton("Cargar ultimo");
		btnCargarUltimo.setIcon(Utilidades.renderImage("/assets/facturaO.png", 17, 17));
		btnCargarUltimo.setEnabled(false);
		btnCargarUltimo.addActionListener(this);
		btnCargarUltimo.setBounds(10, 79, 174, 23);
		panel_1.add(btnCargarUltimo);

		btnCargarTodos = new JButton("Cargar todos");
		btnCargarTodos.setEnabled(false);
		btnCargarTodos.setIcon(Utilidades.renderImage("/assets/facturaO.png", 17, 17));
		btnCargarTodos.setBounds(194, 81, 174, 23);
		btnCargarTodos.addActionListener(this);
		panel_1.add(btnCargarTodos);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(this);
		btnCancelar.setIcon(Utilidades.renderImage("/assets/cancelicon.png", 17, 17));
		btnCancelar.setBounds(767, 476, 110, 23);
		panelCenter.add(btnCancelar);

		PnlConFondo panel_1_1 = new PnlConFondo("/assets/imagen 70.png");
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n de pago",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(553, 139, 324, 86);
		panelCenter.add(panel_1_1);

		JLabel lblNewLabel_8_1_1 = new JLabel("Fecha");
		lblNewLabel_8_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_8_1_1.setBounds(10, 24, 34, 20);
		panel_1_1.add(lblNewLabel_8_1_1);

		txtFecha = new JDateChooser((Date) null);
		txtFecha.setEnabled(false);
		txtFecha.setDateFormatString("dd/MM/yyyy");
		txtFecha.setBounds(54, 24, 170, 20);
		panel_1_1.add(txtFecha);

		JLabel lblOtroDocumento_1 = new JLabel("");
		lblOtroDocumento_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOtroDocumento_1.setBounds(274, 115, 70, 20);
		panel_1_1.add(lblOtroDocumento_1);

		JLabel lblNewLabel_1 = new JLabel("Valor a pagar");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 55, 81, 20);
		panel_1_1.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(101, 56, 102, 20);
		panel_1_1.add(textField);
		textField.setColumns(10);

		btnCalcular = new JButton();
		btnCalcular.setEnabled(false);
		btnCalcular.addActionListener(this);
		btnCalcular.setIcon(Utilidades.renderImage("/assets/dollar.PNG", 17, 17));
		btnCalcular.setBounds(204, 55, 20, 20);
		panel_1_1.add(btnCalcular);

		JLabel lblNewLabel_5 = new JLabel("Dif. 0");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(234, 59, 80, 14);
		panel_1_1.add(lblNewLabel_5);

		btnSaldar = new JButton("Saldar");
		btnSaldar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		btnSaldar.setEnabled(false);
		btnSaldar.addActionListener(this);
		btnSaldar.setIcon(Utilidades.renderImage("/assets/registradora.PNG", 17, 17));
		btnSaldar.setBounds(433, 476, 110, 23);
		panelCenter.add(btnSaldar);

		chbxTodos = new JCheckBox("Seleccionar todos");
		chbxTodos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		chbxTodos.setBackground(new Color(239, 241, 243));
		chbxTodos.setEnabled(false);
		chbxTodos.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		chbxTodos.setBounds(413, 256, 130, 23);
		panelCenter.add(chbxTodos);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(553, 236, 324, 205);
		panelCenter.add(scrollPane_1);

		tableAbonos = new JTable();
		tableAbonos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAbonos.setCellSelectionEnabled(true);
		tableModelAbono = new DefaultTableModel(new Object[][] {},
				new String[] { "# Refencia", "Saldo", "Abona", "Diferencia" }) {
			Class[] columnTypes = new Class[] { Object.class, Double.class, Double.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableAbonos.setModel(tableModelAbono);
		tableAbonos.getColumnModel().getColumn(0).setResizable(false);
		tableAbonos.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableAbonos.getColumnModel().getColumn(1).setResizable(false);
		tableAbonos.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableAbonos.getColumnModel().getColumn(2).setResizable(false);
		tableAbonos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableAbonos.getColumnModel().getColumn(3).setResizable(false);
		tableAbonos.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrollPane_1.setViewportView(tableAbonos);

		JLabel lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(767, 445, 110, 20);
		panelCenter.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Total a pagar:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(672, 445, 85, 20);
		panelCenter.add(lblNewLabel_3);

		btnRetirar = new JButton("Retirar");
		btnRetirar.setEnabled(false);
		btnRetirar.setIcon(Utilidades.renderImage("/assets/sacar.PNG", 17, 17));
		btnRetirar.setBounds(553, 445, 89, 23);
		panelCenter.add(btnRetirar);

	}

	private void habilitarBotones(boolean enabled) {
//		btnModificar.setEnabled(enabled);
//		btnEliminar.setEnabled(enabled);
//		btnPagos.setEnabled(enabled);
//		btnCobros.setEnabled(enabled);
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

	protected void cargarCuotas(long idCliente, long idAcreedor) {
		listaCobrosCliente = logica.getController().listaCobros(idCliente, idAcreedor);
		tableModel.setRowCount(0);
		Object[] fila = null;

		int i = 2345;
		for (CobrosVo cobro : listaCobrosCliente) {
			i++;
			fila = new Object[8];
			fila[0] = "Ref-" + i;
			fila[1] = cobro.getFechaOportuna();

			if (cobro.getEstados() == Utilidades.PAGO_PENDIENTE) {
				fila[2] = "Pendiente";
			} else if (cobro.getEstados() == Utilidades.PAGO_VENCIDO)
				fila[2] = "Vencido";

			fila[3] = cobro.getDiasVencidos();
			fila[4] = cobro.getMora();
			fila[5] = cobro.getAbonos();
			fila[6] = cobro.getDeudaActual();
			fila[7] = false;
			JCheckBox checkBox = new JCheckBox();
			chekBox.add(checkBox);
			tableModel.addRow(fila);
			agregarEventosChek();
		}
		btnSaldar.setEnabled(true);
		tableCobros.setModel(tableModel);

	}

	public String validarCobros() {

		ArrayList<Boolean> estados = new ArrayList<Boolean>();
		for (int i = 0; i < tableCobros.getRowCount(); i++) {
			boolean estado = (boolean) tableCobros.getValueAt(i, 7);
			estados.add(estado);
		}
		if (!estados.get(0)) {
			return "Los cobros seleccionados deben empezar por el primero disponible en la lista";
		}
		boolean negacion = false;
		for (int i = 0; i < estados.size(); i++) {

			if (!negacion) {
				if (!estados.get(i)) {
					if (estados.get(i + 1)) {
						return "Los cobros seleccionados deben ser concecutivos";
					} else {
						negacion = true;
					}
				}
			} else if (estados.get(i)) {
				return "Los cobros seleccionados deben ser concecutivos";
			}
		}
		return "Ok";
	}

	private ArrayList<JCheckBox> chekBox = new ArrayList<JCheckBox>();
	JCheckBox chekAnt = null;

	public void agregarEventosChek() {

//		JCheckBox chek = new JCheckBox();
//		chek.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent eF) {
//				if (chek.isSelected()) {
//					if (tableCobros.getSelectedRow() != 0) {
//						boolean ant = (boolean) tableCobros.getValueAt(tableCobros.getSelectedRow() - 1, 7);
//						if (!ant) {
//							chek.setSelected(false);
//							return;
//						}
//					}
//				} else if (!chek.isSelected()) {
//					if (tableCobros.getSelectedRow() != tableCobros.getRowCount() - 1) {
//						boolean post = (boolean) tableCobros.getValueAt(tableCobros.getSelectedRow() + 1, 7);
//						if (post) {
//							chek.setSelected(true);
//							JOptionPane.showMessageDialog(null, "No");
//							return;
//						}
//					}
//				}
//
//			}
//		});
//		DefaultCellEditor cellEditor = new DefaultCellEditor(chek);
//		tableCobros.getColumnModel().getColumn(7).setCellEditor(cellEditor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnBuscarCliente) {
			buscarCliente();
		} else if (e.getSource() == btnBuscarAcreedor) {
			buscarAcreedor();
		} else if (e.getSource() == btnCargarTodos) {
			cargarCuotas(clienteEncontrado.getId(), acreedorEncontrado.getId());
		} else if (e.getSource() == btnSaldar) {
			agregarCoutasAFacturar();
			habilitarCamposFormSeleccion(false);
		}
	}

	private void agregarCoutasAFacturar() {
		listaCobrosFacturar = new ArrayList<CobrosVo>();
		tableModelAbono.setRowCount(0);
		CobrosVo elcobro;
		Object[] fila = null;
		for (int i = 0; i < tableCobros.getRowCount(); i++) {
			if ((boolean) tableCobros.getModel().getValueAt(i, 7)) {
				elcobro = listaCobrosCliente.get(i);
				listaCobrosFacturar.add(elcobro);
				fila = new Object[4];
				fila[0] = "Ref-" + i;
				fila[1] = elcobro.getDeudaActual();
				fila[2] = elcobro.getDeudaActual();
				fila[3] = 0;
				tableModelAbono.addRow(fila);
			}
			tableAbonos.setModel(tableModelAbono);
		}
	}

	public void buscarAcreedor() {

		String cedula = txtCedulaAcreedor.getText() + "";
		if (!cedula.equals("")) {
			acreedorEncontrado = logica.getController().BuscarAcreedor(cedula);
			if (acreedorEncontrado != null) {
				lblNombreAcreedor.setText(acreedorEncontrado.getNombre() + " " + acreedorEncontrado.getApellido());
				btnCargarUltimo.setEnabled(true);
				btnCargarUltimo.requestFocus();
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
			clienteEncontrado = logica.getController().BuscarCliete(cedula);
			if (clienteEncontrado != null) {
				lblNombreCliente.setText(clienteEncontrado.getNombre() + " " + clienteEncontrado.getApellido());
				btnCargarTodos.setEnabled(true);
				txtCedulaAcreedor.setEnabled(true);
				txtCedulaAcreedor.requestFocus();
			} else
				JOptionPane.showMessageDialog(this,
						"El cliente no existe, debe crearlo para continuar o utilicé la búsqueda rápida");
		} else
			JOptionPane.showMessageDialog(this, "Debe ingresar una cedula", "GNA Software dice:",
					JOptionPane.WARNING_MESSAGE);
	}

	private void cancelarAccion() {
		habilitarCamposFormSeleccion(false);
		limpiarCampos();
		habilitarBotones(false);
		btnFacturar.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnFacturar.requestFocus();
	}

	private void habilitarCamposFormSeleccion(boolean enabled) {
		if (enabled) {
			txtCedula.setEnabled(enabled);
			btnBuscarCliente.setEnabled(enabled);
		} else {
			txtCedula.setEnabled(enabled);
			btnBuscarCliente.setEnabled(enabled);
			txtCedulaAcreedor.setEnabled(enabled);
			btnBuscarAcreedor.setEnabled(enabled);
			tableCobros.setEnabled(enabled);
			btnSaldar.setEnabled(enabled);
			chbxTodos.setEnabled(enabled);
		}
	}

	private void limpiarCampos() {

		txtFecha.setDate(new Date());
		txtCedula.setText("");
		txtCedulaAcreedor.setText("");
		lblOtroDocumento.setText("");
		clienteEncontrado = null;
		acreedorEncontrado = null;
	}

	public void recargarModulo() {

		limpiarCampos();
		habilitarBotones(false);
		habilitarCamposFormSeleccion(false);
		btnFacturar.setEnabled(true);
		btnCancelar.setEnabled(false);
		txtCedula.requestFocus();

	}

}
