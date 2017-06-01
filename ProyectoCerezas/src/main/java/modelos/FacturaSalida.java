package modelos;

import java.util.Date;

public class FacturaSalida {
	/**
	 * PROPIEDADES
	 */
	private String nFactura;
	private Date fecha;
	private double precioBruto;
	private int iva;
	/*En la base de datos se ha introducido retenciones, pero quedamos en que no se iba a incluir*/
	private double precioNeto;
	
	/**
	 * CONSTRUCTOR VACÍO
	 */
	public FacturaSalida(){}
	/**
	 * 	CONSTRUCTOR CON PARÁMETROS
	 * @param nFactura
	 * @param precioBruto
	 * @param iva
	 * @param precioNeto
	 */
	public FacturaSalida(String nFactura, Date fecha, double precioBruto, int iva, double precioNeto){
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

	public String getnFactura() {
		return nFactura;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setnFactura(String nFactura) {
		this.nFactura = nFactura;
	}
	
	
}
