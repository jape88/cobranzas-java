package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import vistas.Configuracion;
import vistas.InfoView;
import vistas.Inicio;

public class MenuBar extends JMenuBar implements ActionListener {

	private JFrame frameActual;
	private Inicio inicio;

	// Menu inicio
	private JMenu mnInicio;
	private JMenuItem mntmInicio;

	// Menu ventas
	private JMenu mnClientes;
	private JMenuItem mntmSubscripciones;

	// Menu reportes
	private JMenu mnHelp;
	private JMenuItem mntmConfiguracion;
	private JMenuItem mntmGestionTarifas;
	private JMenuItem mntmInfoSoftware;
	private JMenuItem mntmSuportSoftware;

	public MenuBar(Inicio inicio, JFrame frameActual) {
		this.inicio = inicio;
		this.frameActual = frameActual;

		// Menu Inicio
		mnInicio = new JMenu("Inicio");
		add(mnInicio);

		// Menu Item Inicio
		mntmInicio = new JMenuItem("Inicio");
		mntmInicio.addActionListener(this);
		mnInicio.add(mntmInicio);

		// Menu ventas
		mnClientes = new JMenu("Clientes");
		add(mnClientes);

		// Menu Item VentasRestaurante
		mntmSubscripciones = new JMenuItem("Suscripciones");
		mntmSubscripciones.addActionListener(this);
		mnClientes.add(mntmSubscripciones);

		// Menu reportes
		mnHelp = new JMenu("Herramientas");
		add(mnHelp);

		// Menu Item VentasRestaurante
		mntmConfiguracion = new JMenuItem("Configuración");
		mntmConfiguracion.addActionListener(this);
		mnHelp.add(mntmConfiguracion);
		mntmGestionTarifas = new JMenuItem("Gestión de tarifas");
		mntmGestionTarifas.addActionListener(this);
		mnHelp.add(mntmGestionTarifas);
		mntmInfoSoftware=new JMenuItem("Información del software");
		mntmInfoSoftware.addActionListener(this);
		mnHelp.add(mntmInfoSoftware);
		
		mntmSuportSoftware=new JMenuItem("Soporte técnico");
		mntmSuportSoftware.addActionListener(this);
		mnHelp.add(mntmSuportSoftware);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mntmInicio) {
			inicio.setVisible(true);
			frameActual.dispose();
		} else if (e.getSource() == mntmInfoSoftware || e.getSource() == mntmSuportSoftware) {

			InfoView frame = new InfoView();
			frame.setVisible(true);
			frameActual.dispose();

		} else if (e.getSource() == mntmConfiguracion) {
			Configuracion frame = new Configuracion(inicio, inicio.getlogica());
			//frame.estableceTitulo();
			frame.setVisible(true);
			frameActual.dispose();
		} else if (e.getSource() == mntmGestionTarifas) {
//			GestionTarifas frame = new GestionTarifas(inicio, inicio.getlogica());
//			frame.estableceTitulo();
//			frame.setVisible(true);
//			frameActual.dispose();
			JOptionPane.showMessageDialog(null, "No disponible");
		}

	}

	public JMenuItem getMntmSubscripciones() {
		return mntmSubscripciones;
	}

	public void setMntmSubscripciones(JMenuItem mntmSubscripciones) {
		this.mntmSubscripciones = mntmSubscripciones;
	}

	public JMenuItem getMntmInicio() {
		return mntmInicio;
	}

	public void setMntmInicio(JMenuItem mntmInicio) {
		this.mntmInicio = mntmInicio;
	}

	public JMenuItem getMntmConfiguracion() {
		return mntmConfiguracion;
	}

	public void setMntmConfiguracion(JMenuItem mntmConfiguracion) {
		this.mntmConfiguracion = mntmConfiguracion;
	}

	public JMenuItem getMntmGestionTarifas() {
		return mntmGestionTarifas;
	}

	public void setMntmGestionTarifas(JMenuItem mntmGestionTarifas) {
		this.mntmGestionTarifas = mntmGestionTarifas;
	}

}
