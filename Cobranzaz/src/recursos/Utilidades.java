package recursos;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import vistas.Inicio;
import vistas.ModuloGeneral;

/**
 * @author paramo
 * Clase para almacenar constantes y metodos reutilizables en el sistema.
 */
public class Utilidades {

	public final static int ESTADO_DEUDA_PENDIENTE = 1;
	public final static int ESTADO_DEUDA_PAGA = 2;

	public final static int TIPO_QUINCENAL = 2;
	public final static int TIPO_MENSUAL = 1;

	public static final int TIPO_ADMIN = 1;
	public static final int TIPO_EMPLEADO = 2;

	public static final int PAGO_PENDIENTE = 0;
	public static final int PAGO_SALDADO = 0;
	public static final int PAGO_VENCIDO = 0;

	public static java.sql.Date convertDateToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	public static Date convertSqlToDate(java.sql.Date date) {
		java.util.Date sDate = new java.util.Date(date.getTime());
		return sDate;
	}

	public static String formatoNumero(double numero) {
		Locale locale = new Locale("es", "ES");
		NumberFormat objNF2 = NumberFormat.getInstance(locale);
		return objNF2.format(numero);
	}

	public static String formatoFecha(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(fecha);
	}

	public static void soloNumeros(KeyEvent e) {
		char caracter = e.getKeyChar();
		// Verificar si la tecla pulsada no es un digito
		if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)
				&& (caracter != ',')) {
			e.consume(); // ignorar el evento de teclado
		}
	}

	public static void soloNumerosEnteros(KeyEvent e) {
		char caracter = e.getKeyChar();
		// Verificar si la tecla pulsada no es un digito
		if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
			e.consume(); // ignorar el evento de teclado
		}
	}

	public static boolean validarCorreo(String correo) {

		Pattern pat = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
		Matcher mather = pat.matcher(correo);
		if (mather.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static String llaveUnica() throws InterruptedException {

		String sAnio, sMes, sDia, sHora, sMinuto, sSeg, sMilisegundo;

		Calendar calendar = Calendar.getInstance();

		int anio = calendar.get(Calendar.YEAR) - 2000;
		int mes = calendar.get(Calendar.MONTH) + 1;
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		int minutos = calendar.get(Calendar.MINUTE);
		int segundo = calendar.get(Calendar.SECOND);
		int milesegudo = calendar.get(Calendar.MILLISECOND);

		// El sistema se detiene por un milisegundo para asegurar la uniquidad de las
		// llaves
		Thread.sleep(1);

		sAnio = String.valueOf(anio);

		if (mes <= 9)
			sMes = String.valueOf("0" + mes);
		else
			sMes = String.valueOf(mes);

		if (dia <= 9)
			sDia = String.valueOf("0" + dia);
		else
			sDia = String.valueOf(dia);

		if (hora <= 9)
			sHora = String.valueOf("0" + hora);
		else
			sHora = String.valueOf(hora);

		if (minutos <= 9)
			sMinuto = String.valueOf("0" + minutos);
		else
			sMinuto = String.valueOf(minutos);

		if (segundo <= 9)
			sSeg = String.valueOf("0" + segundo);
		else
			sSeg = String.valueOf(segundo);

		if (milesegudo == 1000) {
			milesegudo -= 9;
		}

		if (milesegudo <= 9)
			sMilisegundo = String.valueOf("00" + milesegudo);
		else if (milesegudo <= 99)
			sMilisegundo = String.valueOf("0" + milesegudo);
		else
			sMilisegundo = String.valueOf(milesegudo);

		return sAnio + sMes + sDia + sHora + sMinuto + sSeg + sMilisegundo;
	}

	// Para que el metodo funcine correctamente la imagen debe estar en el classpath
	// del proyecto
	public static ImageIcon renderImage(String rutaImage, int ancho, int alto) {
		Image img = new ImageIcon(Inicio.class.getResource(rutaImage)).getImage();
		ImageIcon imgRender = new ImageIcon(img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
		return imgRender;
	}

	public static void volverInicio(ModuloGeneral moduloActual) {
		int res = JOptionPane.showConfirmDialog(null, "¿Volver a la página principal?", "Software de gestión de cobranza dice", JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			moduloActual.dispose();
			moduloActual.getInicio().setVisible(true);
		}
	}
}
