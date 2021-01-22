package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controladores.Conexion;
import recursos.Utilidades;
import vo.CobrosVo;
import vo.DeudaVo;

public class CobrosDao {

	private Conexion conn;

	public ArrayList<CobrosVo> listaCobrosPorDeuda(DeudaVo deuda) {
		ArrayList<CobrosVo> listaCobros = new ArrayList<CobrosVo>();
		conn = new Conexion();
		CobrosVo cobro;
		String sql = "SELECT a.id,a.estado,a.fecha_de_pago_oportuno,a.fecha_de_pago,a.dias_vencido,a.cuota_capital,a.intereses,a.honorarios,a.mora,a.abonos,a.cuota_total "
				+ "FROM cobros a WHERE a.id_deuda='" + deuda.getId() + "'";
		try {
			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				cobro = new CobrosVo();
				cobro.setId(rs.getLong("id"));

				String estado = rs.getString("estado");
				if (estado.equals("Pendiente")) {
					cobro.setEstados(Utilidades.PAGO_PENDIENTE);
				} else if (estado.equals("Vencido")) {
					cobro.setEstados(Utilidades.PAGO_VENCIDO);
				} else if (estado.equals("Pago")) {
					cobro.setEstados(Utilidades.PAGO_SALDADO);
				}
				cobro.setFechaOportuna(Utilidades.convertSqlToDate(rs.getDate("fecha_de_pago_oportuno")));
				java.sql.Date fechaPago = rs.getDate("fecha_de_pago");
				if (fechaPago != null) {
					cobro.setFechaDePago(Utilidades.convertSqlToDate(fechaPago));
				} else
					cobro.setFechaDePago(null);

				cobro.setCapital(rs.getDouble("cuota_capital"));
				cobro.setDiasVencidos(rs.getInt("dias_vencido"));
				cobro.setIntereses(rs.getDouble("intereses"));
				cobro.setHonorarios(rs.getDouble("honorarios"));
				cobro.setMora(rs.getDouble("mora"));

				cobro.setAbonos(rs.getDouble("abonos"));
				cobro.setDeudaActual(rs.getDouble("cuota_total"));

				listaCobros.add(cobro);
			}

			sentencia.close();
			conn.desconectar();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return listaCobros;
	}

	public ArrayList<CobrosVo> listaCobrosPorClienteYAcreedor(long idCliente, long idAcreedor) {
		ArrayList<CobrosVo> listaCobros = new ArrayList<CobrosVo>();
		conn = new Conexion();
		CobrosVo cobro;
		String sql = "SELECT a.id,a.estado,a.fecha_de_pago_oportuno,a.fecha_de_pago,a.dias_vencido,a.cuota_capital,a.intereses,a.honorarios,a.mora,a.abonos,a.cuota_total "
				+ "FROM cobros a LEFT JOIN deuda_cliente b ON a.id_deuda=b.id WHERE b.id_cliente='" + idCliente
				+ "' AND b.id_acreedor='" + idAcreedor + "'";

		try {
			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				cobro = new CobrosVo();
				cobro.setId(rs.getLong("id"));

				String estado = rs.getString("estado");
				if (estado.equals("Pendiente")) {
					cobro.setEstados(Utilidades.PAGO_PENDIENTE);
				} else if (estado.equals("Vencido")) {
					cobro.setEstados(Utilidades.PAGO_VENCIDO);
				} else if (estado.equals("Pago")) {
					cobro.setEstados(Utilidades.PAGO_SALDADO);
				}
				cobro.setFechaOportuna(Utilidades.convertSqlToDate(rs.getDate("fecha_de_pago_oportuno")));
				java.sql.Date fechaPago = rs.getDate("fecha_de_pago");
				if (fechaPago != null) {
					cobro.setFechaDePago(Utilidades.convertSqlToDate(fechaPago));
				} else
					cobro.setFechaDePago(null);

				cobro.setCapital(rs.getDouble("cuota_capital"));
				cobro.setDiasVencidos(rs.getInt("dias_vencido"));
				cobro.setIntereses(rs.getDouble("intereses"));
				cobro.setHonorarios(rs.getDouble("honorarios"));
				cobro.setMora(rs.getDouble("mora"));

				cobro.setAbonos(rs.getDouble("abonos"));
				cobro.setDeudaActual(rs.getDouble("cuota_total"));

				listaCobros.add(cobro);
			}

			sentencia.close();
			conn.desconectar();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return listaCobros;
	}
}
