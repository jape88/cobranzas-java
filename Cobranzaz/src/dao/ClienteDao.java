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
import vo.DepartamentoVo;

public class ClienteDao {

	private Conexion conn;

	public ArrayList<ClientesVo> buscarClientes(String criterio) {

		ArrayList<ClientesVo> listaClientes = new ArrayList<>();
		conn = new Conexion();
		ClientesVo unCliente;

		try {

			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT a.id, a.codigo, a.cedula, a.nombre, a.apellido, a.celular, a.celular2, a.correo, a.direccion, a.ruta_imagen, c.id id_ciudad, c.codigo_ciudad, c.nombre nombre_ciudad, d.id id_departamento, d.codigo codigo_departamento,d.nombre nombre_departamento "
					+ "FROM clientes a " + "LEFT JOIN ciudad c ON c.id=a.id_ciudad "
					+ "LEFT JOIN departamento d ON d.id=c.id_departamento " + "WHERE a.cedula LIKE '%" + criterio
					+ "%' OR a.nombre LIKE '%" + criterio + "%' OR a.apellido LIKE '%" + criterio
					+ "%' OR a.celular LIKE '%" + criterio + "%' OR a.celular2 LIKE '%" + criterio
					+ "%' OR a.correo LIKE '%" + criterio + "%';";
			ResultSet rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				unCliente = new ClientesVo();
				unCliente.setId(rs.getLong("id"));
				unCliente.setCodigo(rs.getInt("codigo"));
				unCliente.setCedula(rs.getLong("cedula"));
				unCliente.setNombre(rs.getString("nombre"));
				unCliente.setApellido(rs.getString("apellido"));
				unCliente.setCelular(rs.getString("celular"));
				unCliente.setCelular2(rs.getString("celular2"));
				unCliente.setCorreo(rs.getString("correo"));
				unCliente.setDirreccion(rs.getString("direccion"));
				unCliente.setRuta_imagne(rs.getString("ruta_imagen"));

				DepartamentoVo departamento = new DepartamentoVo();
				departamento.setId(rs.getLong("id_departamento"));
				departamento.setCodigo(rs.getInt("codigo_departamento"));
				departamento.setNombre(rs.getString("nombre_departamento"));

				CiudadVo ciudad = new CiudadVo();
				ciudad.setId(rs.getLong("id_ciudad"));
				ciudad.setCodigo(rs.getInt("codigo_ciudad"));
				ciudad.setNombre(rs.getString("nombre_ciudad"));
				ciudad.setDepartamento(departamento);

				unCliente.setCiudad(ciudad);
				listaClientes.add(unCliente);
			}

			sentencia.close();
			conn.desconectar();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return listaClientes;
	}

	public ArrayList<ClientesVo> listarCLientes() {

		ArrayList<ClientesVo> listaClientes = new ArrayList<>();
		conn = new Conexion();
		ClientesVo unCliente;

		try {

			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery(
					"SELECT a.id, a.codigo ,a.cedula, a.nombre, a.apellido, a.celular, a.celular2, a.correo, a.direccion, a.ruta_imagen, c.id id_ciudad, c.codigo_ciudad, c.nombre nombre_ciudad, d.id id_departamento, d.codigo codigo_departamento,d.nombre nombre_departamento FROM clientes a LEFT JOIN ciudad c ON c.id=a.id_ciudad LEFT JOIN departamento d ON d.id=c.id_departamento");
			while (rs.next()) {
				unCliente = new ClientesVo();
				unCliente.setId(rs.getLong("id"));
				unCliente.setCodigo(rs.getInt("codigo"));
				unCliente.setCedula(rs.getLong("cedula"));
				unCliente.setNombre(rs.getString("nombre"));
				unCliente.setApellido(rs.getString("apellido"));
				unCliente.setCelular(rs.getString("celular"));
				unCliente.setCelular2(rs.getString("celular2"));
				unCliente.setCorreo(rs.getString("correo"));
				unCliente.setDirreccion(rs.getString("direccion"));
				unCliente.setRuta_imagne(rs.getString("ruta_imagen"));

				DepartamentoVo departamento = new DepartamentoVo();
				departamento.setId(rs.getLong("id_departamento"));
				departamento.setCodigo(rs.getInt("codigo_departamento"));
				departamento.setNombre(rs.getString("nombre_departamento"));

				CiudadVo ciudad = new CiudadVo();
				ciudad.setId(rs.getLong("id_ciudad"));
				ciudad.setCodigo(rs.getInt("codigo_ciudad"));
				ciudad.setNombre(rs.getString("nombre_ciudad"));
				ciudad.setDepartamento(departamento);

				unCliente.setCiudad(ciudad);
				listaClientes.add(unCliente);
			}

			sentencia.close();
			conn.desconectar();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return listaClientes;
	}

	public boolean agregarCliente(ClientesVo cliente) {

		conn = new Conexion();
		try {
			cliente.setId(Long.parseLong(Utilidades.llaveUnica()));
			Statement sentencia = conn.getConnection().createStatement();

			sentencia.executeUpdate(
					"INSERT INTO clientes (id, cedula, nombre, apellido, celular, celular2, correo, direccion, id_ciudad, ruta_imagen) VALUES ('"
							+ cliente.getId() + "','" + cliente.getCedula() + "','" + cliente.getNombre() + "','"
							+ cliente.getApellido() + "','" + cliente.getCelular() + "','" + cliente.getCelular2()
							+ "','" + cliente.getCorreo() + "','" + cliente.getDirreccion() + "','"
							+ cliente.getCiudad().getId() + "','" + cliente.getRuta_imagne() + "')");

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

	public boolean modificarCliente(ClientesVo cliente) {

		conn = new Conexion();
		try {
			String consulta = "UPDATE clientes SET cedula=?, nombre=?, apellido=?, celular=?, celular2=?, correo=?, direccion=? WHERE  codigo=?";

			PreparedStatement sentencia = conn.getConnection().prepareStatement(consulta);

			sentencia.setLong(1, cliente.getCedula());
			sentencia.setString(2, cliente.getNombre());
			sentencia.setString(3, cliente.getApellido());
			sentencia.setString(4, cliente.getCelular());
			sentencia.setString(5, cliente.getCelular2());
			sentencia.setString(6, cliente.getCorreo());
			sentencia.setString(7, cliente.getDirreccion());
			sentencia.setLong(8, cliente.getCodigo());
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

	public boolean eliminar(long idCliente) {
		conn = new Conexion();
		try {
			Statement sentencia = conn.getConnection().createStatement();
			sentencia.executeUpdate("DELETE FROM clientes WHERE  id='" + idCliente + "'");

			sentencia.close();
			conn.desconectar();
			return true;

		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public ClientesVo buscarClinte(String cedula) {
		conn = new Conexion();
		ClientesVo unCliente = null
		;

		try {

			Statement sentencia = conn.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sentencia.executeQuery(
					"SELECT a.id, a.codigo ,a.cedula, a.nombre, a.apellido, a.celular, a.celular2, a.correo, a.direccion, a.ruta_imagen, c.id id_ciudad, c.codigo_ciudad, c.nombre nombre_ciudad, d.id id_departamento, d.codigo codigo_departamento,d.nombre nombre_departamento "
					+ "FROM clientes a "
					+ "LEFT JOIN ciudad c ON c.id=a.id_ciudad "
					+ "LEFT JOIN departamento d ON d.id=c.id_departamento "
					+ "WHERE a.cedula="+ cedula+";");
			while (rs.next()) {
				unCliente = new ClientesVo();
				unCliente.setId(rs.getLong("id"));
				unCliente.setCodigo(rs.getInt("codigo"));
				unCliente.setCedula(rs.getLong("cedula"));
				unCliente.setNombre(rs.getString("nombre"));
				unCliente.setApellido(rs.getString("apellido"));
				unCliente.setCelular(rs.getString("celular"));
				unCliente.setCelular2(rs.getString("celular2"));
				unCliente.setCorreo(rs.getString("correo"));
				unCliente.setDirreccion(rs.getString("direccion"));
				unCliente.setRuta_imagne(rs.getString("ruta_imagen"));

				DepartamentoVo departamento = new DepartamentoVo();
				departamento.setId(rs.getLong("id_departamento"));
				departamento.setCodigo(rs.getInt("codigo_departamento"));
				departamento.setNombre(rs.getString("nombre_departamento"));

				CiudadVo ciudad = new CiudadVo();
				ciudad.setId(rs.getLong("id_ciudad"));
				ciudad.setCodigo(rs.getInt("codigo_ciudad"));
				ciudad.setNombre(rs.getString("nombre_ciudad"));
				ciudad.setDepartamento(departamento);

				unCliente.setCiudad(ciudad);
			}

			sentencia.close();
			conn.desconectar();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return unCliente;
	}
}
