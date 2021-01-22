package hilos;

import java.util.Calendar;

import javax.swing.JLabel;

public class HiloHora extends Thread {

	private JLabel lbHora;
	private int hora, minuto, segundo;
	private Calendar calendar;

	public void cicloHora() {

	}

	public HiloHora(JLabel lbHora) {
		super();
		this.lbHora = lbHora;
	}

	@Override
	public void run() {
		while (true) {
			try {
				sleep(1000);

				calendar = Calendar.getInstance();
				hora = calendar.get(Calendar.HOUR_OF_DAY);
				minuto = calendar.get(Calendar.MINUTE);
				segundo = calendar.get(Calendar.SECOND);
				lbHora.setText(hora + ":" + minuto + ":" + segundo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public JLabel getLbHora() {
		return lbHora;
	}

	public void setLbHora(JLabel lbHora) {
		this.lbHora = lbHora;
	}
}