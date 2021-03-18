package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import paneles.PnlConFondo;
import recursos.Utilidades;
import vo.CobrosVo;
import vo.CreditoVo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controladores.Logica;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Paramo
 * Vista para los cobros
 */
public class DialogViewCobros extends JDialog {

	private final JPanel contentPanel = new PnlConFondo("/assets/imagen 70.png");

	// Botones
	private JButton btnAceptar;
	private JTable table;
	private CreditoVo deuda;
	private Logica logica;

	private ArrayList<CobrosVo> listaCobros;

	private DefaultTableModel tableModel;
	private JLabel lblFechaInicial;
	private JLabel lblSaldoInicial;
	private JLabel lblIntereses;
	private JLabel lblAbonos;
	private JLabel lblSaldoActual;
	private JLabel lblEdado;
	private JLabel lblCliente;
	private JLabel lblAcreedor;
	private JButton btnFacturar;

	private JButton btnFacturar_1;

	/**
	 * Create the dialog.
	 */
	public DialogViewCobros(JFrame owner, CreditoVo deuda, Logica logica) {
		super(owner);
		setModal(true);
		this.deuda = deuda;
		this.logica = logica;

		setUndecorated(true);
		setBounds(100, 100, 700, 440);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Lista de cobros ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 680, 24);
		contentPanel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 680, 166);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnFacturar.setEnabled(true);
				btnFacturar_1.setEnabled(true);
				
			}
		});
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Cuota #", "Fecha oportuna",
				"Fecha de pago", "Estado", "Valor Cuota", "Dias vencida", "Abonos", "Saldo Actual" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, Double.class,
					Integer.class, Double.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		scrollPane.setViewportView(table);

		JPanel panel = new PnlConFondo("/assets/imagen 70.png");
		panel.setBorder(
				new TitledBorder(null, "Estado de la deuda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 46, 680, 166);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblFechaInicial = new JLabel("22/04/2020");
		lblFechaInicial.setBounds(122, 22, 159, 14);
		panel.add(lblFechaInicial);
		lblFechaInicial.setForeground(Color.RED);
		lblFechaInicial.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_1_2 = new JLabel("Fecha de ingreso");
		lblNewLabel_1_2.setBounds(10, 22, 102, 14);
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_1_2_1 = new JLabel("Estado de deuda");
		lblNewLabel_1_2_1.setBounds(215, 71, 108, 14);
		panel.add(lblNewLabel_1_2_1);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblEdado = new JLabel("El dia");
		lblEdado.setBounds(333, 71, 202, 14);
		panel.add(lblEdado);
		lblEdado.setForeground(Color.BLUE);
		lblEdado.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblCliente = new JLabel("Cliente de prueba - Cc: 12345678");
		lblCliente.setBounds(284, 22, 386, 14);
		panel.add(lblCliente);
		lblCliente.setForeground(Color.BLUE);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblAcreedor = new JLabel("Acreedor de prueba - Cc: 12345678");
		lblAcreedor.setBounds(284, 46, 386, 14);
		panel.add(lblAcreedor);
		lblAcreedor.setForeground(Color.BLUE);
		lblAcreedor.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_1_1 = new JLabel("Acreedor:");
		lblNewLabel_1_1.setBounds(215, 46, 59, 14);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_1 = new JLabel("Cliente:");
		lblNewLabel_1.setBounds(215, 22, 46, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		JPanel panel_1 = new PnlConFondo("/assets/fondoTransp100.png");
		panel_1.setBorder(
				new TitledBorder(null, "Abonos o intereses", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 47, 195, 108);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1_1 = new JLabel("Saldo Inicial");
		lblNewLabel_1_1_1.setBounds(10, 22, 80, 14);
		panel_1.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblSaldoInicial = new JLabel("0,0");
		lblSaldoInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldoInicial.setBounds(100, 22, 85, 14);
		panel_1.add(lblSaldoInicial);
		lblSaldoInicial.setForeground(Color.BLUE);
		lblSaldoInicial.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Interés mora");
		lblNewLabel_1_1_1_1.setBounds(10, 41, 80, 14);
		panel_1.add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblIntereses = new JLabel("0,0");
		lblIntereses.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIntereses.setBounds(100, 41, 85, 14);
		panel_1.add(lblIntereses);
		lblIntereses.setForeground(Color.RED);
		lblIntereses.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Abonos");
		lblNewLabel_1_1_1_1_1.setBounds(10, 60, 80, 14);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblAbonos = new JLabel("0,0");
		lblAbonos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAbonos.setBounds(100, 60, 85, 14);
		panel_1.add(lblAbonos);
		lblAbonos.setForeground(Color.BLUE);
		lblAbonos.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Saldo Actual");
		lblNewLabel_1_1_1_2.setBounds(10, 79, 80, 14);
		panel_1.add(lblNewLabel_1_1_1_2);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblSaldoActual = new JLabel("0,0");
		lblSaldoActual.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldoActual.setBounds(100, 79, 85, 14);
		panel_1.add(lblSaldoActual);
		lblSaldoActual.setForeground(Color.BLUE);
		lblSaldoActual.setFont(new Font("Tahoma", Font.BOLD, 12));
		
				btnFacturar = new JButton();
				btnFacturar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						JOptionPane.showMessageDialog(null, "Funcion en desarollo");
					}
				});
				btnFacturar.setEnabled(false);
				btnFacturar.setBounds(634, 119, 36, 36);
				panel.add(btnFacturar);
				btnFacturar.setToolTipText("Abonar Saldar");
				btnFacturar.setIcon(Utilidades.renderImage("/assets/registradora.png", 30, 30));
				
				btnFacturar_1 = new JButton();
				
				btnFacturar_1.setToolTipText("Imprimir");
				btnFacturar_1.setEnabled(false);
				btnFacturar_1.setBounds(588, 119, 36, 36);
				btnFacturar_1.setIcon(Utilidades.renderImage("/assets/impresora.png", 30, 30));
				panel.add(btnFacturar_1);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(0, 2, 2, 2, (Color) Color.BLACK));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnAceptar.setIcon(Utilidades.renderImage("/assets/aceptarIcon.png", 18, 18));
		buttonPane.add(btnAceptar);
		getRootPane().setDefaultButton(btnAceptar);

		cargarInfoDeuda();

	}

	private void cargarInfoDeuda() {

		lblFechaInicial.setText(deuda.getFecha());
		lblAcreedor.setText(deuda.getAcreedor().getNombre() + " " + deuda.getAcreedor().getApellido() + " - Cc "
				+ deuda.getAcreedor().getCedula());
		lblCliente.setText(deuda.getClientes().getNombre() + " " + deuda.getClientes().getApellido() + " - Cc "
				+ deuda.getClientes().getCedula());
		if (deuda.getEstado() == Utilidades.ESTADO_DEUDA_PAGA) {
			lblEdado.setForeground(Color.BLUE);
			lblEdado.setText("Deuda saldada");
		} else {
			if (!cobrosVencidos()) {
				lblEdado.setForeground(Color.RED);
				lblEdado.setText("Con cuotas atrasadas");

			} else {
				lblEdado.setForeground(Color.BLUE);
				lblEdado.setText("Cuotas al dia");
			}
		}
		lblSaldoInicial.setText(Utilidades.formatoNumero(deuda.getTotalDeuda()));
		lblSaldoActual.setText(Utilidades.formatoNumero(deuda.getTotalDeuda()));
		lblAbonos.setText("0");
		lblIntereses.setText("0");
		listaCobros = logica.getController().listaCobros(deuda);
		tableModel.setRowCount(0);
		Object[] fila = null;
		int i = 1;
		for (CobrosVo cobrosVo : listaCobros) {
			fila = new Object[8];
			fila[0] = "C#-" + i;

			fila[1] = Utilidades.formatoFecha(cobrosVo.getFechaOportuna());
			if (cobrosVo.getFechaDePago() != null) {
				fila[2] = Utilidades.formatoFecha(cobrosVo.getFechaDePago());
			} else
				fila[2] = "Pago pendiente";

			if (cobrosVo.getEstados() == Utilidades.PAGO_PENDIENTE) {
				fila[3] = "Pendiente";
			} else if (cobrosVo.getEstados() == Utilidades.PAGO_SALDADO) {
				fila[3] = "Saldada";
			} else
				fila[3] = "Vencida";

			fila[4] = cobrosVo.getDeudaActual();
			fila[5] = cobrosVo.getDiasVencidos();
			fila[6] = cobrosVo.getAbonos();
			fila[7] = cobrosVo.getDeudaActual();

			tableModel.addRow(fila);
			i++;

		}
		table.setModel(tableModel);
	}

	private boolean cobrosVencidos() {
		// TODO Auto-generated method stub
		return true;
	}
}
