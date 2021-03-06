package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String formatearFecha(Date f){
		SimpleDateFormat parser = new SimpleDateFormat(getFormatoFecha());
		return parser.format(f);
	}
	
	public static Date parse(String f){
		Date d =null;
		SimpleDateFormat parser = new SimpleDateFormat(getFormatoFecha());
		try {
			d = parser.parse(f);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return d;
	}
	
	public static String getFormatoFecha(){
		return "dd/MM/yyyy";
	}
	
	
	public static String getFormatoFechaVista(){
		return "dd/mm/yy";
	}
	
	/**************************************************************************/
	/***********************Formato para introducir datos SQL*******/
	/**
	 * Funci�n para gestionar el formato de la fecha -- FUNCIONA
	 * @param fecha
	 * @return la fecha formateada
	 */
	public static Date parseFormato (String fecha){
		Date d=null;
		String[] formatosAceptados={
				"d-M-yyyy",
				"d/M/yyyy",
				"M-d-yyyy",
				"M/d/yyyy",
				"yyyy-M-d",
				"yyyy/M/d"
		};
		SimpleDateFormat parser;
		for(String formato: formatosAceptados){
			parser = new SimpleDateFormat(formato);
			try{
				
				d = parser.parse(fecha);
				break;
			}
			catch(ParseException pe){
				System.out.println("Error al dar formato a la fecha");
			}
		}
		
		return d;
	}
	
	public static String getFormatoFechaSql(){
		return "yyyy/MM/dd";
	}
	
	public static Date parseSql(String f){
		Date d =null;
		SimpleDateFormat parser = new SimpleDateFormat(getFormatoFecha());
		try {
			d = parser.parse(f);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return d;
	}
	
}
