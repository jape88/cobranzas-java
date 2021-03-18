package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controladores.Logica;
import paneles.PnlConFondo;
import paneles.PnlFormAcreedor;
import recursos.Utilidades;
import vistas.ModuloCobranzas;
import vo.AcreedorVo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;

/**
 * @author Paramo
 * Vista para agregar acreedor rápido
 */
public class DialogAcreedor extends JDialog {

	private final JPanel contentPanel = new PnlConFondo("/assets/imagen 70.png");

	private Logica logica;
	
	private JButton btnAgregar;
	private PnlFormAcreedor formAcreedor;

	/**
	 * Create the dialog.
	 */
	public DialogAcreedor(JFrame owner, Logica logica) {
		super(owner);
		this.logica = logica;
		setModal(true);

		setUndecorated(true);
		setBounds(100, 100, 261, 281);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		formAcreedor = new PnlFormAcreedor("/assets/imagen 71.png");
		formAcreedor.getTxtCorreo().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnAgregar.requestFocus();
				}
			}
		});
		formAcreedor.setBounds(10, 46, 239, 189);
		contentPanel.add(formAcreedor);

		JLabel lblNewLabel = new JLabel("Acreedor ingreso rapido");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 239, 24);
		contentPanel.add(lblNewLabel);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(0, 0, 0)));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (validadCampos()) {
					agregarAcreedor();
				}
			}
		});
		btnAgregar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (validadCampos()) {
						agregarAcreedor();
					}
				}
			}
		});
		btnAgregar.setIcon(Utilidades.renderImage("/assets/plus.png", 18, 18));
		buttonPane.add(btnAgregar);
		getRootPane().setDefaultButton(btnAgregar);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setIcon(Utilidades.renderImage("/assets/cancelicon.png", 18, 18));
		buttonPane.add(btnNewButton);

	}

	private void agregarAcreedor() {

		AcreedorVo acreedor = new AcreedorVo();
		acreedor.setCedula(Long.parseLong(formAcreedor.getTxtCedula().getText()));
		acreedor.setNombre(formAcreedor.getTxtNombre().getText());
		acreedor.setApellido(formAcreedor.getTxtApellido().getText());
		acreedor.setCelular(formAcreedor.getTxtCelular().getText());
		acreedor.setCorreo(formAcreedor.getTxtCorreo().getText());

		if (logica.getController().agregarAcreedor(acreedor)) {
			JOptionPane.showMessageDialog(this, "Acreedor agregado correctamente");
			((ModuloCobranzas) getOwner()).buscarAcreedor();
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"Algo ha ido mal, por favor intente nuevamente. Si persiste el error por favor comunicarse con soporte técnico.");

		}

	}

	public JTextField getTxtCedula() {
		return formAcreedor.getTxtCedula();
	}

	public void setTxtCedula(JTextField txtCedula) {
		formAcreedor.setTxtCedula(txtCedula);
	}

	private boolean validadCampos() {
		// TODO Auto-generated method stub
		return true;
	}
}
