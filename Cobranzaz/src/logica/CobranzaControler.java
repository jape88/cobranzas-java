package logica;

import java.util.ArrayList;

import dao.AcreedorDao;
import dao.ClienteDao;
import dao.CobranzaDao;
import dao.CobrosDao;
import dao.UsuarioDao;
import dao.DeudaDao;
import vo.AcreedorVo;
import vo.ClientesVo;
import vo.CobranzaVo;
import vo.CobrosVo;
import vo.DeudaVo;
import vo.UsuarioVo;

public class CobranzaControler {

	private CobranzaVo myParqueadero;

	private UsuarioDao usuarioDao;
	private ClienteDao clienteDao;
	private CobranzaDao unParqueaderoDao;
	private DeudaDao deudaDao;
	private CobrosDao cobroDao;

	private AcreedorDao acreedorDao;

	public void iniciarParqueadero() {
		myParqueadero = new CobranzaVo();
		clienteDao = new ClienteDao();
		usuarioDao = new UsuarioDao();
		acreedorDao = new AcreedorDao();
		unParqueaderoDao = new CobranzaDao();
		deudaDao = new DeudaDao();
		cobroDao = new CobrosDao();
	}

	@Override
	public String toString() {
		return "ParqueaderoControler [myParqueadero=" + myParqueadero + ", usuarioDao=" + usuarioDao
				+ ", unParqueaderoDao=" + unParqueaderoDao + "]";
	}

	public UsuarioVo devolverUsuario(String nombre) {
		return usuarioDao.buscarUsuario(nombre);
	}

	public boolean agregarParqueadero(CobranzaVo parqueaderoVo) {
		return unParqueaderoDao.agregarParqueadero(parqueaderoVo);
	}

	public boolean modificarParqueadero(CobranzaVo parqueaderoVo) {
		return unParqueaderoDao.modificarPark(parqueaderoVo);
	}

	public ArrayList<ClientesVo> listaClientes() {
		return clienteDao.listarCLientes();
	}

	public ArrayList<ClientesVo> BuscarClientes(String criterio) {
		return clienteDao.buscarClientes(criterio);
	}

	public boolean agregarCliente(ClientesVo cliente) {
		return clienteDao.agregarCliente(cliente);
	}

	public boolean modicarCliente(ClientesVo cliente) {
		return clienteDao.modificarCliente(cliente);
	}

	public boolean elimnarCliente(long id) {

		return clienteDao.eliminar(id);
	}

	public ArrayList<UsuarioVo> BuscarUsuarios(String criterio) {
		return usuarioDao.buscarUsuarios(criterio);
	}

	public ArrayList<UsuarioVo> BuscarUsuarios() {
		return usuarioDao.buscarUsuarios();
	}

	public boolean elimnarUsuario(long id) {
		return usuarioDao.eliminar(id);
	}

	public boolean agregarUsuario(UsuarioVo usuario) {
		return usuarioDao.agregarUsuario(usuario);
	}

	public boolean modicarUsuario(UsuarioVo usuario) {
		return usuarioDao.modificarUsuario(usuario);
	}

	public ClientesVo BuscarCliete(String cedula) {

		return clienteDao.buscarClinte(cedula);
	}

	public AcreedorVo BuscarAcreedor(String cedula) {
		return acreedorDao.buscarAcreedor(cedula);
	}

	public boolean agregarAcreedor(AcreedorVo acreedor) {
		return acreedorDao.agregarACreedor(acreedor);
	}

	public boolean agregarDeuda(DeudaVo deuda) {
		return deudaDao.agregarDeuda(deuda);
	}

	public ArrayList<DeudaVo> listaDeudas() {
		return deudaDao.listaDeudas();
	}

	public ArrayList<CobrosVo> listaCobros(DeudaVo deuda) {
		return cobroDao.listaCobrosPorDeuda(deuda);
	}

	public boolean modificarDeuda(DeudaVo deudaSelecionado) {
		
		return deudaDao.modificarDeuda(deudaSelecionado);
	}

	public ArrayList<CobrosVo> listaCobros(long idCliente, long idAcreedor) {
		return cobroDao.listaCobrosPorClienteYAcreedor(idCliente, idAcreedor);
	}

}
