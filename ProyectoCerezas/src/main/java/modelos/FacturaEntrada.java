package modelos;

import java.util.Date;

import Utils.DateUtils;

public class FacturaEntrada {
	/**
	 * PROPIEDADES
	 */
	private int nFactura;
	private Date fecha;
	private double precioBruto;
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
	public FacturaEntrada (int nFactura,Date fecha, double precioBruto, int iva, double precioNeto){
		this.nFactura = nFactura;
		this.fecha=fecha;
		this.precioBruto = precioBruto;
		this.iva = iva;
		this.precioNeto = precioNeto;
	}
	/**
	 * GETTERS Y SETTERS
	 */
	public double getPrecioBruto() {
		return precioBruto;
	}

	public void setPrecioBruto(double precioBruto) {
		this.precioBruto = precioBruto;
	}

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
