package paneles;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * @author Paramo
 * Panel con el formulario para agregar un cliente
 */
public class PnlFormCliente extends JPanel implements KeyListener {

	private URL url;
	Image image;
	private JTextField txtCodigo;
	private JLabel lblNewLabel_6;
	private JTextField txtCelular1;
	private JTextField txtCedula;
	private JTextField txtCelular2;
	private JComboBox<?> cmbxDepartamento;
	private JComboBox<?> cmbxCiudad;
	private JTextField txtDirrecion;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}

	public PnlFormCliente(String rutaFondo) {
		url = getClass().getResource(rutaFondo);
		image = new ImageIcon(url).getImage();
		setBorder(new TitledBorder(null, "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBounds(10, 128, 560, 181);

		setLayout(null);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 25, 80, 20);
		add(lblCodigo);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCodigo = new JTextField();
		txtCodigo.setBounds(100, 25, 140, 20);
		add(txtCodigo);
		txtCodigo.setColumns(10);

		lblNewLabel_6 = new JLabel("Teléfono 1");
		lblNewLabel_6.setBounds(286, 25, 80, 20);
		add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCelular1 = new JTextField();
		txtCelular1.setBounds(396, 25, 140, 20);
		add(txtCelular1);
		txtCelular1.setColumns(10);

		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setBounds(10, 56, 80, 20);
		add(lblCedula);
		lblCedula.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCedula = new JTextField();
		txtCedula.setBounds(100, 56, 140, 20);
		add(txtCedula);
		txtCedula.setColumns(10);

		txtCelular2 = new JTextField();
		txtCelular2.setBounds(396, 56, 140, 20);
		add(txtCelular2);
		txtCelular2.setColumns(10);

		cmbxDepartamento = new JComboBox();
		cmbxDepartamento.setBounds(396, 87, 140, 20);
		add(cmbxDepartamento);
		cmbxDepartamento.setModel(new DefaultComboBoxModel(new String[] { "Seleccione" }));

		cmbxCiudad = new JComboBox();
		cmbxCiudad.setBounds(396, 118, 140, 20);
		add(cmbxCiudad);
		cmbxCiudad.setModel(new DefaultComboBoxModel(new String[] { "Seleccione" }));

		txtDirrecion = new JTextField();
		txtDirrecion.setBounds(396, 148, 140, 20);
		add(txtDirrecion);
		txtDirrecion.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Direcci\u00F3n");
		lblNewLabel_8.setBounds(286, 149, 80, 20);
		add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(286, 118, 80, 20);
		add(lblCiudad);
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(286, 87, 90, 20);
		add(lblDepartamento);
		lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblCelular = new JLabel("Teléfono 2");
		lblCelular.setBounds(286, 56, 80, 20);
		add(lblCelular);
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtNombre = new JTextField();
		txtNombre.setBounds(100, 87, 140, 20);
		add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Nombre");
		lblNewLabel_4.setBounds(10, 87, 80, 20);
		add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_5 = new JLabel("Apellido");
		lblNewLabel_5.setBounds(10, 118, 80, 20);
		add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtApellido = new JTextField();
		txtApellido.setBounds(100, 118, 140, 20);
		add(txtApellido);
		txtApellido.setColumns(10);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(100, 150, 140, 20);
		add(txtCorreo);
		txtCorreo.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("E-mail");
		lblNewLabel_7.setBounds(10, 149, 80, 20);
		add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCedula.addKeyListener(this);
		txtNombre.addKeyListener(this);
		txtApellido.addKeyListener(this);
		txtCorreo.addKeyListener(this);
		txtCelular1.addKeyListener(this);
		txtCelular2.addKeyListener(this);

	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtCelular1() {
		return txtCelular1;
	}

	public void setTxtCelular1(JTextField txtCelular1) {
		this.txtCelular1 = txtCelular1;
	}

	public JTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(JTextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	public JTextField getTxtCelular2() {
		return txtCelular2;
	}

	public void setTxtCelular2(JTextField txtCelular2) {
		this.txtCelular2 = txtCelular2;
	}

	public JComboBox<?> getCmbxDepartamento() {
		return cmbxDepartamento;
	}

	public void setCmbxDepartamento(JComboBox<?> cmbxDepartamento) {
		this.cmbxDepartamento = cmbxDepartamento;
	}

	public JComboBox<?> getCmbxCiudad() {
		return cmbxCiudad;
	}

	public void setCmbxCiudad(JComboBox<?> cmbxCiudad) {
		this.cmbxCiudad = cmbxCiudad;
	}

	public JTextField getTxtDirrecion() {
		return txtDirrecion;
	}

	public void setTxtDirrecion(JTextField txtDirrecion) {
		this.txtDirrecion = txtDirrecion;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	public void setTxtCorreo(JTextField txtCorreo) {
		this.txtCorreo = txtCorreo;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == getTxtCedula()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				getTxtNombre().requestFocus();
			}
		} else if (e.getSource() == getTxtNombre()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				getTxtApellido().requestFocus();
			}
		} else if (e.getSource() == getTxtApellido()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				getTxtCorreo().requestFocus();
			}
		} else if (e.getSource() == getTxtCorreo()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				getTxtCelular1().setFocusable(true);
				getTxtCelular1().requestFocus();
			}
		} else if (e.getSource() == getTxtCelular1()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				getTxtCelular2().requestFocus();
			}
		} else if (e.getSource() == getTxtCelular2()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				getTxtDirrecion().requestFocus();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
