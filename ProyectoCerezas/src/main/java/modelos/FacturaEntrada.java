package modelos;

import java.util.Date;

import Utils.DateUtils;

public class FacturaEntrada {
	/**
	 * PROPIEDADES
	 */
	private int nFactura;
	private Date fecha;
	private int iva;
	private double precioNeto;
	
	/**
	 * CONSTRUCTOR VACÍO
	 */
	public FacturaEntrada(){}
	/**
	 * 	CONSTRUCTOR CON PARÁMETROS
	 * @param nFactura
	 * @param precioBruto
	 * @param iva
	 * @param precioNeto
	 * @param fecha
	 */
	public FacturaEntrada (int nFactura,Date fecha, int iva, double precioNeto){
		this.nFactura = nFactura;
		this.fecha=fecha;
		this.iva = iva;
		this.precioNeto = precioNeto;
	}
	/**
	 * GETTERS Y SETTERS
	 */

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public double getPrecioNeto() {
		return precioNeto;
	}

	public void setPrecioNeto(double precioNeto) {
		this.precioNeto = precioNeto;
	}

	public int getnFactura() {
		return nFactura;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getStringFecha(){
		return DateUtils.formatearFecha(fecha);
	}
	public void setnFactura(int nFactura) {
		this.nFactura = nFactura;
	}
		
}
