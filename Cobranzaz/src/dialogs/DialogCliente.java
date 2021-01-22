package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logica.Logica;
import paneles.PnlConFondo;
import paneles.PnlFormCliente;
import recursos.Utilidades;
import vistas.ModuloCobranzas;
import vo.AcreedorVo;
import vo.CiudadVo;
import vo.ClientesVo;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.xml.transform.Source;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

public class DialogCliente extends JDialog implements KeyListener {

	private final JPanel contentPanel = new PnlConFondo("/assets/imagen 70.png");

	private Logica logica;

	// Formulario
	private PnlFormCliente panelForm;

	// Botones
	private JButton btnAgregar;
	private JButton btnCancelar;

	/**
	 * Create the dialog.
	 */
	public DialogCliente(JFrame owner, Logica logica) {
		super(owner);
		this.logica = logica;
		setModal(true);

		setUndecorated(true);
		setBounds(100, 100, 588, 292);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Clientes ingreso rapido");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 567, 24);
		contentPanel.add(lblNewLabel);

		panelForm = new PnlFormCliente("/assets/imagen 71.png");
		panelForm.getTxtCelular1().setFocusable(false);
		panelForm.getTxtCodigo().setEnabled(false);
		panelForm.setBounds(10, 46, 567, 196);
		contentPanel.add(panelForm);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(0, 2, 2, 2, (Color) Color.BLACK));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (validadCampos()) {
					agregarCliente();
				}
			}
		});
		btnAgregar.addKeyListener(this);
		btnAgregar.setIcon(Utilidades.renderImage("/assets/plus.png", 18, 18));
		buttonPane.add(btnAgregar);
		getRootPane().setDefaultButton(btnAgregar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(Utilidades.renderImage("/assets/cancelicon.png", 18, 18));
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnCancelar);
		panelForm.getTxtDirrecion().addKeyListener(this);

	}

	private void agregarCliente() {

		ClientesVo cliente = new ClientesVo();
		cliente.setCedula(Long.parseLong(panelForm.getTxtCedula().getText()));
		cliente.setNombre(panelForm.getTxtNombre().getText());
		cliente.setApellido(panelForm.getTxtApellido().getText());
		cliente.setCelular(panelForm.getTxtCelular1().getText());
		cliente.setCorreo(panelForm.getTxtCorreo().getText());
		cliente.setCelular2(panelForm.getTxtCelular2().getText());
		cliente.setDirreccion(panelForm.getTxtDirrecion().getText());

		CiudadVo ciudad = new CiudadVo();
		ciudad.setId(676657776);
		cliente.setCiudad(ciudad);

		if (logica.getController().agregarCliente(cliente)) {
			JOptionPane.showMessageDialog(this, "Cliente agregado correctamente");
			((ModuloCobranzas) getOwner()).buscarCliente();
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");

		}

	}

	public JTextField getTxtCedula() {
		return panelForm.getTxtCedula();
	}

	public void setTxtCedula(JTextField txtCedula) {
		panelForm.setTxtCedula(txtCedula);
		panelForm.getTxtCedula().requestFocus();
	}

	private boolean validadCampos() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == panelForm.getTxtDirrecion()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				btnAgregar.requestFocus();
			}
		} else if (e.getSource() == btnAgregar) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (validadCampos()) {
					agregarCliente();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
