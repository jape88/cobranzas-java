package vo;

public class CiudadVo {
	
	private long id;
	private DepartamentoVo departamento;
	private int codigo;
	private String nombre;
	
	public CiudadVo() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public DepartamentoVo getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoVo departamento) {
		this.departamento = departamento;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
