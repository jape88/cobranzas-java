package vo;

/**
 * @author paramo
 */
import java.util.Date;

public class CreditoVo {

	private long id;
	private ClientesVo clientes;
	private AcreedorVo acreedor;
	private String documento;
	private String fecha;
	private Date fechaCalculos;
	private int modalidad, cuotas, estado;
	private double deudaCapital, interes, honorarios, mora, totalDeuda, valorCouta, valorExtraordinario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ClientesVo getClientes() {
		return clientes;
	}

	public void setClientes(ClientesVo clientes) {
		this.clientes = clientes;
	}

	public AcreedorVo getAcreedor() {
		return acreedor;
	}

	public void setAcreedor(AcreedorVo acreedor) {
		this.acreedor = acreedor;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getModalidad() {
		return modalidad;
	}

	public void setModalidad(int modalidad) {
		this.modalidad = modalidad;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public double getDeudaCapital() {
		return deudaCapital;
	}

	public void setDeudaCapital(double deudaCapital) {
		this.deudaCapital = deudaCapital;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public double getHonorarios() {
		return honorarios;
	}

	public void setHonorarios(double honorarios) {
		this.honorarios = honorarios;
	}

	public double getMora() {
		return mora;
	}

	public void setMora(double mora) {
		this.mora = mora;
	}

	public double getTotalDeuda() {
		return totalDeuda;
	}

	public void setTotalDeuda(double totalDeuda) {
		this.totalDeuda = totalDeuda;
	}

	public double getValorCouta() {
		return valorCouta;
	}

	public void setValorCouta(double valorCouta) {
		this.valorCouta = valorCouta;
	}

	public double getValorExtraordinario() {
		return valorExtraordinario;
	}

	public void setValorExtraordinario(double valorExtraordinario) {
		this.valorExtraordinario = valorExtraordinario;
	}

	public Date getFechaCalculos() {
		return fechaCalculos;
	}

	public void setFechaCalculos(Date fechaCalculos) {
		this.fechaCalculos = fechaCalculos;
	}

}
