package controladores;

import vistas.Inicio;

/**
 * @author paramo
 * Controlador principal que almacena vista principl y controlador con los Dao
 */
public class Logica {

	private CobranzaControler controller;
	private Inicio ventanaPrincipal;

	public Logica(Inicio ventanaPrincipal) {
		controller = new CobranzaControler();
		controller.iniciar();
		this.setVentanaPrincipal(ventanaPrincipal);
	}

	public CobranzaControler getController() {
		return controller;
	}

	public void setPark(CobranzaControler controller) {
		this.controller = controller;
	}

	public Inicio getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(Inicio ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

}
