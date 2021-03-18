package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controladores.Conexion;
import recursos.Utilidades;
import vo.CobranzaVo;

/**
 * @author Paramo
 */
public class CobranzaDao {

	private Conexion conn;

	public boolean agregarParqueadero(CobranzaVo parqueadero) {

		conn = new Conexion();
		try {
			parqueadero.setId(Long.parseLong(Utilidades.llaveUnica()));
			Statement sentencia = conn.getConnection().createStatement();

			sentencia.executeUpdate(
					"INSERT INTO parqueadero (id, nombre, nit, dirrecion, telefono, correo, regimen, horario) "
							+ "VALUES ('" + parqueadero.getId() + "', '" + parqueadero.getNombre() + "', '"
							+ parqueadero.getNit() + "', '" + parqueadero.getDireccion() + "', '"
							+ parqueadero.getTelefono() + "', '" + parqueadero.getCorreo() + "', '"
							+ parqueadero.getRegimen() + "','" + parqueadero.getHorario() + "');");

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

	public CobranzaVo retornarParqueadero(long id) {
		conn = new Conexion();
		CobranzaVo unParqueadero = null;
		try {

			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery(
					"SELECT a.id,a.nombre,a.nit,a.dirrecion,a.telefono,a.correo,a.regimen,a.horario FROM parqueadero a "
							+ "WHERE a.id=" + id);
			while (rs.next()) {
				unParqueadero = new CobranzaVo();
				unParqueadero.setId(rs.getLong("id"));
				unParqueadero.setNombre(rs.getString("nombre"));
				unParqueadero.setNit(rs.getString("nit"));
				unParqueadero.setDireccion(rs.getString("dirrecion"));
				unParqueadero.setTelefono(rs.getString("telefono"));
				unParqueadero.setCorreo(rs.getString("correo"));
				unParqueadero.setRegimen(rs.getInt("regimen"));
				unParqueadero.setHorario(rs.getString("horario"));
			}

			sentencia.close();
			conn.desconectar();
			return unParqueadero;

		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean modificarPark(CobranzaVo parqueaderoVo) {
		conn = new Conexion();
		try {
			String consulta = "UPDATE parqueadero SET nombre=?, nit=?, dirrecion=?, telefono=?, correo=?, regimen=?, horario=? WHERE  id=?;";

			PreparedStatement sentencia = conn.getConnection().prepareStatement(consulta);

			sentencia.setString(1, parqueaderoVo.getNombre());
			sentencia.setString(2, parqueaderoVo.getNit());
			sentencia.setString(3, parqueaderoVo.getDireccion());
			sentencia.setString(4, parqueaderoVo.getTelefono());
			sentencia.setString(5, parqueaderoVo.getCorreo());
			sentencia.setInt(6, parqueaderoVo.getRegimen());
			sentencia.setString(7, parqueaderoVo.getHorario());
			sentencia.setLong(8, parqueaderoVo.getId());
			sentencia.executeUpdate();
			sentencia.close();
			conn.desconectar();
			return true;

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			conn.desconectar();
		}
		return false;
	}
}
