package controladores;

import java.awt.EventQueue;

import javax.swing.UIManager;

import hilos.HiloHora;
import vistas.Inicio;
import vistas.Login;

/**
 * @author Paramo
 * Controlador con el metodo main que inicia la ventana principal y la logica del negocio
 */
public class Principal {

	private static Logica miLogica;
	private static Inicio inicio;
	private static Login login;

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		miLogica = new Logica(inicio);
		inicio = new Inicio(miLogica);
		login = new Login(inicio,miLogica);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				login.setVisible(true);
				HiloHora hiloHora = new HiloHora(inicio.getLbHora());
				hiloHora.start();

			}
		});
	}

}
