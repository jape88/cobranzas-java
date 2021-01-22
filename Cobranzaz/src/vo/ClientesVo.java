package vo;

public class ClientesVo {

	private long id, cedula;
	private int codigo;
	private String nombre, apellido, celular, celular2, correo, dirreccion, ruta_imagne;
	private CiudadVo ciudad;

	public ClientesVo() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDirreccion() {
		return dirreccion;
	}

	public void setDirreccion(String dirreccion) {
		this.dirreccion = dirreccion;
	}

	public String getRuta_imagne() {
		return ruta_imagne;
	}

	public void setRuta_imagne(String ruta_imagne) {
		this.ruta_imagne = ruta_imagne;
	}

	public CiudadVo getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadVo ciudad) {
		this.ciudad = ciudad;
	}

	public String getCelular2() {
		return celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
