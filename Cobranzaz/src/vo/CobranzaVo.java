package vo;

import java.io.Serializable;

public class CobranzaVo  implements Serializable {

	private long id;
	private String nombre;
	private int cantidadPuestosCarros;
	private int cantidadPuestosMotos;
	private boolean isLlenoCarros;
	private boolean isLlenoMotos;
	private int noExiste;
	private int yaExiste;
	private int horaApertura;
	private int horaCierre;
	private int tarifaCarro;
	private int tarifaMoto;
	private int horaReal;
	private boolean isAbierto;
	private String codigoLicencia;
	private String nit, direccion, telefono, correo, horario;
	private int regimen;

	public CobranzaVo(String nombre, int cantidadPuestosCarros, int cantidadPuestosMotos, boolean isLlenoCarros,
			boolean isLlenoMotos, int noExiste, int yaExiste, int horaApertura, int horaCierre, int tarifaCarro,
			int tarifaMoto, int horaReal, boolean isAbierto) {
		super();
		this.nombre = nombre;
		this.cantidadPuestosCarros = cantidadPuestosCarros;
		this.cantidadPuestosMotos = cantidadPuestosMotos;
		this.isLlenoCarros = isLlenoCarros;
		this.isLlenoMotos = isLlenoMotos;
		this.noExiste = noExiste;
		this.yaExiste = yaExiste;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.tarifaCarro = tarifaCarro;
		this.tarifaMoto = tarifaMoto;
		this.horaReal = horaReal;
		this.isAbierto = isAbierto;
	}

	public CobranzaVo() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadPuestosCarros() {
		return cantidadPuestosCarros;
	}

	public void setCantidadPuestosCarros(int cantidadPuestosCarros) {
		this.cantidadPuestosCarros = cantidadPuestosCarros;
	}

	public int getCantidadPuestosMotos() {
		return cantidadPuestosMotos;
	}

	public void setCantidadPuestosMotos(int cantidadPuestosMotos) {
		this.cantidadPuestosMotos = cantidadPuestosMotos;
	}

	public boolean isLlenoCarros() {
		return isLlenoCarros;
	}

	public void setLlenoCarros(boolean isLlenoCarros) {
		this.isLlenoCarros = isLlenoCarros;
	}

	public boolean isLlenoMotos() {
		return isLlenoMotos;
	}

	public void setLlenoMotos(boolean isLlenoMotos) {
		this.isLlenoMotos = isLlenoMotos;
	}

	public int getNoExiste() {
		return noExiste;
	}

	public void setNoExiste(int noExiste) {
		this.noExiste = noExiste;
	}

	public int getYaExiste() {
		return yaExiste;
	}

	public void setYaExiste(int yaExiste) {
		this.yaExiste = yaExiste;
	}

	public int getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(int horaApertura) {
		this.horaApertura = horaApertura;
	}

	public int getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(int horaCierre) {
		this.horaCierre = horaCierre;
	}

	public int getTarifaCarro() {
		return tarifaCarro;
	}

	public void setTarifaCarro(int tarifaCarro) {
		this.tarifaCarro = tarifaCarro;
	}

	public int getTarifaMoto() {
		return tarifaMoto;
	}

	public void setTarifaMoto(int tarifaMoto) {
		this.tarifaMoto = tarifaMoto;
	}

	public int getHoraReal() {
		return horaReal;
	}

	public void setHoraReal(int horaReal) {
		this.horaReal = horaReal;
	}

	public boolean isAbierto() {
		return isAbierto;
	}

	public void setAbierto(boolean isAbierto) {
		this.isAbierto = isAbierto;
	}

	public void iniciarParqueadero() {

	}

	public String getCodigoLicencia() {
		return codigoLicencia;
	}

	public void setCodigoLicencia(String codigoLicencia) {
		this.codigoLicencia = codigoLicencia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getRegimen() {
		return regimen;
	}

	public void setRegimen(int regimen) {
		this.regimen = regimen;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

}
