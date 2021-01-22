package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controladores.Conexion;
import recursos.Utilidades;
import vo.AcreedorVo;
import vo.CiudadVo;
import vo.ClientesVo;
import vo.DepartamentoVo;

public class AcreedorDao {

	private Conexion conn;

	public AcreedorVo buscarAcreedor(String cedula) {
		AcreedorVo acreedor = null;
		conn = new Conexion();
		;

		try {

			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery("SELECT a.id, a.cedula, a.nombre, a.apellido, a.celular, a.correo "
					+ "FROM acreedores a " + "WHERE a.cedula=" + cedula + ";");
			while (rs.next()) {
				acreedor = new AcreedorVo();
				acreedor.setId(rs.getLong("id"));
				acreedor.setCedula(rs.getLong("cedula"));
				acreedor.setNombre(rs.getString("nombre"));
				acreedor.setApellido(rs.getString("apellido"));
				acreedor.setCelular(rs.getString("celular"));
				acreedor.setCorreo(rs.getString("correo"));
			}

			sentencia.close();
			conn.desconectar();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return acreedor;
	}

	public boolean agregarACreedor(AcreedorVo acreedor) {
		
		conn = new Conexion();
		try {
			acreedor.setId(Long.parseLong(Utilidades.llaveUnica()));
			Statement sentencia = conn.getConnection().createStatement();

			sentencia.executeUpdate(
					"INSERT INTO acreedores (id, cedula, nombre, apellido, celular,  correo) VALUES ('"
							+ acreedor.getId() + "','" + acreedor.getCedula() + "','" + acreedor.getNombre() + "','"
							+ acreedor.getApellido() + "','" + acreedor.getCelular() + "','" + acreedor.getCorreo() + "')");

			sentencia.close();
			conn.desconectar();
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

}
