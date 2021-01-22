package hilos;

import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import vistas.Cargando;
import vistas.Inicio;
import vo.UsuarioVo;

public class HiloCarga extends Thread {

	private Inicio inicio;
	private Cargando cargar;
	private UsuarioVo usuarioVo;

	public void cicloHora() {

	}

	public HiloCarga(Inicio inicio, Cargando cargar, UsuarioVo usuarioVo) {
		super();
		this.inicio = inicio;
		this.cargar = cargar;
		this.usuarioVo = usuarioVo;
	}

	@Override
	public void run() {
		try {

			for (int i = 0; i < 4; i++) {
				sleep(1000);
			}
			cargar.dispose();
			inicio.setVisible(true);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
