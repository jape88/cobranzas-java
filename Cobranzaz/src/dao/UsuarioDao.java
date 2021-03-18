package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controladores.Conexion;
import recursos.Utilidades;
import vo.CiudadVo;
import vo.ClientesVo;
import vo.CobranzaVo;
import vo.DepartamentoVo;
import vo.UsuarioVo;

/**
 * @author Paramo
 */
public class UsuarioDao {

	private Conexion conn;

	// Metodo para obtener un usuario por nombre
	public UsuarioVo buscarUsuario(String nombre) {

		conn = new Conexion();
		UsuarioVo unUsuario = new UsuarioVo();
		try {

			String query = "SELECT a.id,a.nombre,a.apellido,a.celular,a.correo,a.password,a.tipo,a.usuario FROM usuarios a WHERE a.usuario='"
					+ nombre + "'";
			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery(query);
			while (rs.next()) {

				unUsuario.setId(rs.getLong("id"));
				unUsuario.setUsuario(rs.getString("usuario"));
				unUsuario.setPassword(rs.getString("password"));
				unUsuario.setNombre(rs.getString("nombre"));
				unUsuario.setApellido(rs.getString("apellido"));
				String tipo = rs.getString("tipo");
				if (tipo.equals("Administrador")) {
					unUsuario.setTipo(Utilidades.TIPO_ADMIN);
				} else
					unUsuario.setTipo(Utilidades.TIPO_EMPLEADO);
			}

			sentencia.close();
			conn.desconectar();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unUsuario;
	}

	public ArrayList<UsuarioVo> buscarUsuarios(String criterio) {

		ArrayList<UsuarioVo> listaUsuarios = new ArrayList<>();
		conn = new Conexion();
		UsuarioVo unUsuario;

		try {

			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			String sql = "SELECT a.id, a.codigo ,a.nombre, a.apellido, a.celular, a.correo, a.password, a.usuario, a.tipo FROM usuarios a "
					+ "WHERE a.nombre LIKE '%" + criterio + "%' OR a.apellido LIKE '%" + criterio
					+ "%' OR a.usuario LIKE '%" + criterio + "%' OR a.celular LIKE '%" + criterio
					+ "%' OR a.correo LIKE '%" + criterio + "%' OR a.tipo LIKE '%" + criterio + "%';";
			ResultSet rs = sentencia.executeQuery(sql);

			while (rs.next()) {
				unUsuario = new UsuarioVo();
				unUsuario.setId(rs.getLong("id"));
				unUsuario.setCodigo(rs.getInt("codigo"));
				unUsuario.setUsuario(rs.getString("usuario"));
				unUsuario.setPassword(rs.getString("password"));
				unUsuario.setNombre(rs.getString("nombre"));
				unUsuario.setApellido(rs.getString("apellido"));
				unUsuario.setCelular(rs.getString("celular"));
				unUsuario.setCorreo(rs.getString("correo"));

				String tipo = rs.getString("tipo");
				if (tipo.equals("Administrador")) {
					unUsuario.setTipo(Utilidades.TIPO_ADMIN);
				} else
					unUsuario.setTipo(Utilidades.TIPO_EMPLEADO);

				listaUsuarios.add(unUsuario);
			}

			sentencia.close();
			conn.desconectar();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return listaUsuarios;
	}

	public ArrayList<UsuarioVo> buscarUsuarios() {
		ArrayList<UsuarioVo> listaUsuarios = new ArrayList<>();
		conn = new Conexion();
		UsuarioVo unUsuario;

		try {

			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery(
					"SELECT a.id, a.codigo ,a.nombre, a.apellido, a.celular, a.correo, a.password, a.usuario, a.tipo FROM usuarios a");
			while (rs.next()) {
				unUsuario = new UsuarioVo();
				unUsuario.setId(rs.getLong("id"));
				unUsuario.setCodigo(rs.getInt("codigo"));
				unUsuario.setUsuario(rs.getString("usuario"));
				unUsuario.setPassword(rs.getString("password"));
				unUsuario.setNombre(rs.getString("nombre"));
				unUsuario.setApellido(rs.getString("apellido"));
				unUsuario.setCelular(rs.getString("celular"));
				unUsuario.setCorreo(rs.getString("correo"));

				String tipo = rs.getString("tipo");
				if (tipo.equals("Administrador")) {
					unUsuario.setTipo(Utilidades.TIPO_ADMIN);
				} else
					unUsuario.setTipo(Utilidades.TIPO_EMPLEADO);

				listaUsuarios.add(unUsuario);
			}

			sentencia.close();
			conn.desconectar();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return listaUsuarios;
	}

	public boolean eliminar(long idUsuario) {
		conn = new Conexion();
		try {
			Statement sentencia = conn.getConnection().createStatement();
			sentencia.executeUpdate("DELETE FROM usuarios WHERE  id='" + idUsuario + "'");

			sentencia.close();
			conn.desconectar();
			return true;

		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean agregarUsuario(UsuarioVo usuario) {

		conn = new Conexion();
		try {
			usuario.setId(Long.parseLong(Utilidades.llaveUnica()));
			Statement sentencia = conn.getConnection().createStatement();

			sentencia.executeUpdate(
					"INSERT INTO usuarios (id, nombre, apellido, celular, correo, tipo, usuario, password) VALUES ('"
							+ usuario.getId() + "','" + usuario.getNombre() + "','" + usuario.getApellido() + "','"
							+ usuario.getCelular() + "','" + usuario.getCorreo() + "','" + usuario.getTipo() + "','"
							+ usuario.getUsuario() + "','" + usuario.getPassword() + "')");

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

	public boolean modificarUsuario(UsuarioVo usuario) {
		conn = new Conexion();
		try {
			String consulta = "UPDATE usuarios SET nombre=?, apellido=?, celular=?, correo=?, usuario=?, password=?, tipo=? WHERE  codigo=?";

			PreparedStatement sentencia = conn.getConnection().prepareStatement(consulta);

			sentencia.setString(1, usuario.getNombre());
			sentencia.setString(2, usuario.getApellido());
			sentencia.setString(3, usuario.getCelular());
			sentencia.setString(4, usuario.getCorreo());
			sentencia.setString(5, usuario.getUsuario());
			sentencia.setString(6, usuario.getPassword());
			sentencia.setString(7, usuario.getTipo() + "");
			sentencia.setLong(8, usuario.getCodigo());
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
