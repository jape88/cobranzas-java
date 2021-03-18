package paneles;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * @author paramo
 */
public class PnlFormAcreedor extends JPanel implements KeyListener {

	private URL url;
	Image image;

	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCelular;
	private JTextField txtCorreo;

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}

	public PnlFormAcreedor(String rutaFondo) {
		url = getClass().getResource(rutaFondo);
		image = new ImageIcon(url).getImage();
		setBorder(new TitledBorder(null, "Datos del acreedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setBounds(10, 29, 60, 20);
		add(lblCedula);
		lblCedula.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCedula = new JTextField();
		txtCedula.setBounds(80, 29, 140, 20);
		add(txtCedula);
		txtCedula.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(80, 60, 140, 20);
		add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Nombre");
		lblNewLabel_4.setBounds(10, 60, 60, 20);
		add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNewLabel_5 = new JLabel("Apellido");
		lblNewLabel_5.setBounds(10, 91, 60, 20);
		add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtApellido = new JTextField();
		txtApellido.setBounds(80, 91, 140, 20);
		add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Teléfono");
		lblNewLabel_6.setBounds(10, 124, 60, 20);
		add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCelular = new JTextField();
		txtCelular.setBounds(80, 124, 140, 20);
		add(txtCelular);
		txtCelular.setColumns(10);

		JLabel lblNewLabel_6_1 = new JLabel("E-Mail");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_1.setBounds(10, 155, 60, 20);
		add(lblNewLabel_6_1);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(80, 155, 140, 20);
		add(txtCorreo);

		txtCedula.addKeyListener(this);
		txtNombre.addKeyListener(this);
		txtApellido.addKeyListener(this);
		txtCelular.addKeyListener(this);

	}

	public JTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(JTextField txtCedula) {
		this.txtCedula = txtCedula;
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

	public JTextField getTxtCelular() {
		return txtCelular;
	}

	public void setTxtCelular(JTextField txtCelular) {
		this.txtCelular = txtCelular;
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
				getTxtCelular().requestFocus();
			}
		} else if (e.getSource() == getTxtCelular()) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				getTxtCorreo().requestFocus();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
