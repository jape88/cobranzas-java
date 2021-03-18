package controladores;

import java.util.ArrayList;

import dao.AcreedorDao;
import dao.ClienteDao;
import dao.CobranzaDao;
import dao.CobrosDao;
import dao.UsuarioDao;
import dao.CreditoDao;
import vo.AcreedorVo;
import vo.ClientesVo;
import vo.CobranzaVo;
import vo.CobrosVo;
import vo.CreditoVo;
import vo.UsuarioVo;

/**
 * @author Paramo
 * Controlador donde se alamacenan los Dao
 */
public class CobranzaControler {

	private CobranzaVo myCobranza;

	private UsuarioDao usuarioDao;
	private ClienteDao clienteDao;
	private CobranzaDao unCobranzaDao;
	private CreditoDao deudaDao;
	private CobrosDao cobroDao;

	private AcreedorDao acreedorDao;

	public void iniciar() {
		myCobranza = new CobranzaVo();
		clienteDao = new ClienteDao();
		usuarioDao = new UsuarioDao();
		acreedorDao = new AcreedorDao();
		unCobranzaDao = new CobranzaDao();
		deudaDao = new CreditoDao();
		cobroDao = new CobrosDao();
	}


	public UsuarioVo devolverUsuario(String nombre) {
		return usuarioDao.buscarUsuario(nombre);
	}

	public boolean agregarParqueadero(CobranzaVo parqueaderoVo) {
		return unCobranzaDao.agregarParqueadero(parqueaderoVo);
	}

	public boolean modificarParqueadero(CobranzaVo parqueaderoVo) {
		return unCobranzaDao.modificarPark(parqueaderoVo);
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

	public boolean agregarDeuda(CreditoVo deuda) {
		return deudaDao.agregarDeuda(deuda);
	}

	public ArrayList<CreditoVo> listaDeudas() {
		return deudaDao.listaDeudas();
	}

	public ArrayList<CobrosVo> listaCobros(CreditoVo deuda) {
		return cobroDao.listaCobrosPorDeuda(deuda);
	}

	public boolean modificarDeuda(CreditoVo deudaSelecionado) {
		
		return deudaDao.modificarDeuda(deudaSelecionado);
	}

	public ArrayList<CobrosVo> listaCobros(long idCliente, long idAcreedor) {
		return cobroDao.listaCobrosPorClienteYAcreedor(idCliente, idAcreedor);
	}

}
