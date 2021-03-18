package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import controladores.Conexion;
import recursos.Utilidades;
import vo.AcreedorVo;
import vo.ClientesVo;
import vo.CobrosVo;
import vo.CreditoVo;

/**
 * @author Paramo
 */
public class CreditoDao {

	private Conexion conn;

	public boolean agregarDeuda(CreditoVo deuda) {
		conn = new Conexion();
		try {
			deuda.setId(Long.parseLong(Utilidades.llaveUnica()));
			Statement sentencia = conn.getConnection().createStatement();

			String modalidad = "";
			if (deuda.getModalidad() == Utilidades.TIPO_MENSUAL) {
				modalidad = "Mensual";
			} else if (deuda.getModalidad() == Utilidades.TIPO_QUINCENAL) {
				modalidad = "Quincenal";
			}
			sentencia.executeUpdate(
					"INSERT INTO creditos (id, id_cliente, id_acreedor, documento, modelo, estado, deuda, interes, honorarios, mora, cuotas, total, fecha, valor_cuota, valor_extraordinario) "
							+ "VALUES ('" + deuda.getId() + "', '" + deuda.getClientes().getId() + "', '"
							+ deuda.getAcreedor().getId() + "', '" + deuda.getDocumento() + "', '" + modalidad
							+ "', 'Pendiente', '" + deuda.getDeudaCapital() + "', '" + deuda.getInteres() + "', " + "'"
							+ deuda.getHonorarios() + "', '" + deuda.getMora() + "', '" + deuda.getCuotas() + "', '"
							+ deuda.getTotalDeuda() + "', '" + deuda.getFecha() + "', '" + deuda.getValorCouta()
							+ "', '" + deuda.getValorExtraordinario() + "');");

			sentencia.close();
			conn.desconectar();
			agregarCobrosADeuda(deuda);
			return true;

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return false;
	}

	public void agregarCobrosADeuda(CreditoVo deudaVo) {

		// Preparamos valores iniciales
		CobrosVo unCobro;
		double coutaCapital = deudaVo.getDeudaCapital() / deudaVo.getCuotas();
		double interesesCouta = ((deudaVo.getDeudaCapital() * deudaVo.getInteres()) / 100) / deudaVo.getCuotas();
		double honorariosCouta = ((deudaVo.getDeudaCapital() * deudaVo.getHonorarios()) / 100) / deudaVo.getCuotas();

		Date fechaActual = deudaVo.getFechaCalculos();
		int modalidadCobro = deudaVo.getModalidad();
		Calendar calendar = Calendar.getInstance();
		String sql = "INSERT INTO cobros (id, id_credito, fecha_de_pago_oportuno, cuota_capital, intereses, honorarios, mora ,cuota_total) VALUES";

		try {
			for (int i = 0; i < deudaVo.getCuotas(); i++) {

				// Calculamos fecha
				calendar.setTime(fechaActual);
				if (modalidadCobro == Utilidades.TIPO_MENSUAL) {
					calendar.add(Calendar.MONTH, 1);
				} else {
					calendar.add(Calendar.WEEK_OF_MONTH, 2);
				}
				fechaActual = calendar.getTime();

				// Creamos cobro
				unCobro = new CobrosVo();
				unCobro.setId(Long.parseLong(Utilidades.llaveUnica()));
				unCobro.setFechaOportuna(Utilidades.convertDateToSql(fechaActual));

				// Agregamos al sql
				sql += "('" + unCobro.getId() + "','" + deudaVo.getId() + "','" + unCobro.getFechaOportuna() + "','"
						+ coutaCapital + "','" + interesesCouta + "','" + honorariosCouta + "','0','"
						+ deudaVo.getValorCouta() + "')";
				if (i == deudaVo.getCuotas() - 1) {
					sql += ";";

				} else {
					sql += ",";
				}
			}

			// Ejecutamos sql
			conn = new Conexion();
			Statement sentencia = conn.getConnection().createStatement();
			sentencia.executeUpdate(sql);
			sentencia.close();
			conn.desconectar();

		} catch (NumberFormatException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<CreditoVo> listaDeudas() {
		ArrayList<CreditoVo> listaDeudas = new ArrayList<CreditoVo>();
		conn = new Conexion();
		CreditoVo deuda;

		String sql = "SELECT a.id,a.documento,a.modelo,a.estado,a.deuda,a.interes,"
				+ "a.honorarios,a.mora,a.cuotas,a.total,a.fecha,a.valor_cuota," + "a.valor_extraordinario,"
				+ "b.id id_cliente,b.cedula cedula_cliente,b.nombre nombre_cliente,b.apellido apellido_cliente,"
				+ "c.id id_acreedor,c.cedula cedula_acreedor,c.nombre nombre_acreedor,c.apellido apellido_acreedor "
				+ "FROM creditos a " + "LEFT JOIN clientes b ON a.id_cliente=b.id "
				+ "LEFT JOIN acreedores c ON a.id_acreedor=c.id";

		try {

			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				deuda = new CreditoVo();
				deuda.setId(rs.getLong("id"));
				deuda.setDocumento(rs.getString("documento"));

				String modalidad = rs.getString("modelo");
				if (modalidad.equals("Mensual")) {
					deuda.setModalidad(Utilidades.TIPO_MENSUAL);
				} else {
					deuda.setModalidad(Utilidades.TIPO_QUINCENAL);
				}

				String estado = rs.getString("estado");
				if (estado.equals("Pendiente")) {
					deuda.setEstado(Utilidades.ESTADO_DEUDA_PENDIENTE);
				} else {
					deuda.setEstado(Utilidades.ESTADO_DEUDA_PAGA);
				}

				deuda.setDeudaCapital(rs.getDouble("deuda"));
				deuda.setInteres(rs.getDouble("interes"));
				deuda.setHonorarios(rs.getDouble("honorarios"));
				deuda.setMora(rs.getDouble("mora"));
				deuda.setCuotas(rs.getInt("cuotas"));

				deuda.setTotalDeuda(rs.getDouble("total"));
				deuda.setValorCouta(rs.getDouble("valor_cuota"));
				deuda.setFecha(rs.getDate("fecha") + "");
				deuda.setValorExtraordinario(rs.getDouble("valor_extraordinario"));

				ClientesVo cliente = new ClientesVo();
				cliente.setId(rs.getLong("id_cliente"));
				cliente.setCedula(rs.getLong("cedula_cliente"));
				cliente.setNombre(rs.getString("nombre_cliente"));
				cliente.setApellido(rs.getString("apellido_cliente"));
				deuda.setClientes(cliente);

				AcreedorVo acreedor = new AcreedorVo();
				acreedor.setId(rs.getLong("id_acreedor"));
				acreedor.setCedula(rs.getLong("cedula_acreedor"));
				acreedor.setNombre(rs.getString("nombre_acreedor"));
				acreedor.setApellido(rs.getString("apellido_acreedor"));
				deuda.setAcreedor(acreedor);

				listaDeudas.add(deuda);
			}

			sentencia.close();
			conn.desconectar();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return listaDeudas;
	}

	public boolean modificarDeuda(CreditoVo deudaSelecionado) {
		conn = new Conexion();
		try {
			String modalidad = "";
			if (deudaSelecionado.getModalidad() == Utilidades.TIPO_MENSUAL) {
				modalidad = "Mensual";
			} else if (deudaSelecionado.getModalidad() == Utilidades.TIPO_QUINCENAL) {
				modalidad = "Quincenal";
			}
			String sql = "UPDATE creditos SET id_cliente=?, id_acreedor=?, documento=?, modelo=?, deuda=?, interes=?, honorarios=?, mora=?, cuotas=?, total=? WHERE  id=?";
			PreparedStatement sentencia = conn.getConnection().prepareStatement(sql);

			sentencia.setLong(1, deudaSelecionado.getClientes().getId());
			sentencia.setLong(2, deudaSelecionado.getAcreedor().getId());
			sentencia.setString(3, deudaSelecionado.getDocumento());
			sentencia.setString(4, modalidad);
			sentencia.setDouble(5, deudaSelecionado.getDeudaCapital());
			sentencia.setDouble(6, deudaSelecionado.getInteres());
			sentencia.setDouble(7, deudaSelecionado.getHonorarios());
			sentencia.setDouble(8, deudaSelecionado.getMora());
			sentencia.setInt(9, deudaSelecionado.getCuotas());
			sentencia.setDouble(10, deudaSelecionado.getDeudaCapital());
			sentencia.setLong(11, deudaSelecionado.getId());
			sentencia.executeUpdate();

			sentencia.close();
			conn.desconectar();
			return true;

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return false;
	}

}
