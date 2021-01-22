package vistas;

import javax.swing.JFrame;

public class ModuloGeneral extends JFrame {

	private Inicio inicio;
	private String nombre;

	public ModuloGeneral(Inicio inicio, String nombre) {
		this.inicio = inicio;
		this.nombre = nombre;
	}

	public Inicio getInicio() {
		return inicio;
	}

	public void setInicio(Inicio inicio) {
		this.inicio = inicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
