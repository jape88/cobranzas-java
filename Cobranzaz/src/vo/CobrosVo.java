package vo;

import java.util.Date;

public class CobrosVo {

	private long id;
	private Date fechaOportuna, fechaDePago;
	private double capital, abonos, deudaActual, intereses, honorarios, mora;
	private int diasVencidos, estados;
	private DeudaVo deuda;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaOportuna() {
		return fechaOportuna;
	}

	public void setFechaOportuna(Date date) {
		this.fechaOportuna = date;
	}

	public java.util.Date getFechaDePago() {
		return fechaDePago;
	}

	public void setFechaDePago(java.util.Date fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public double getAbonos() {
		return abonos;
	}

	public void setAbonos(double abonos) {
		this.abonos = abonos;
	}

	public double getDeudaActual() {
		return deudaActual;
	}

	public void setDeudaActual(double deudaActual) {
		this.deudaActual = deudaActual;
	}

	public int getDiasVencidos() {
		return diasVencidos;
	}

	public void setDiasVencidos(int diasVencidos) {
		this.diasVencidos = diasVencidos;
	}

	public int getEstados() {
		return estados;
	}

	public void setEstados(int estados) {
		this.estados = estados;
	}

	public DeudaVo getDeuda() {
		return deuda;
	}

	public void setDeuda(DeudaVo deuda) {
		this.deuda = deuda;
	}

	public double getIntereses() {
		return intereses;
	}

	public void setIntereses(double intereses) {
		this.intereses = intereses;
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

}
