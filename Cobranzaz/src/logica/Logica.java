package logica;

import vistas.Inicio;

public class Logica {

	private CobranzaControler controller;
	private Inicio ventanaPrincipal;

	public Logica(Inicio ventanaPrincipal) {
		controller = new CobranzaControler();
		controller.iniciarParqueadero();
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
