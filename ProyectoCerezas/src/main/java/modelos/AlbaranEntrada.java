package modelos;

import java.util.Date;

import Utils.DateUtils;



public class AlbaranEntrada {
	/**
	 * PROPIEDADES
	 */
	private int nAlbaran;
	private int nSocio;
	private Date fecha; /*Hacer un Utils para gestionar la fecha*/
	private int nFactura;
	
	/**
	 * CONSTRUCTORES
	 */
	public AlbaranEntrada (){}
	/**
	 * CONSTRUCTOR PARA EL FORMULARIO
	 * @param nSocio
	 * @param fecha
	 * @param nFactura
	 */
	public AlbaranEntrada (int nSocio, Date fecha, int nFactura){
		this.nAlbaran = -1;
		this.nSocio = nSocio;
		this.fecha = fecha;
		this.nFactura = nFactura;	
	}
	/**
	 * CONSTRUCTOR PARA EL ROWMAPPER
	 * @param nAlbaran
	 * @param nSocio
	 * @param fecha
	 * @param nFactura
	 */
	public AlbaranEntrada (int nAlbaran,int nSocio, Date fecha, int nFactura){
		this.nAlbaran = nAlbaran;
		this.nSocio = nSocio;
		this.fecha = fecha;
		this.nFactura = nFactura;	
	}
	/**
	 * GETTER AND SETTER
	 */
	public int getnSocio() {
		return nSocio;
	}
	public void setnSocio(int nSocio) {
		this.nSocio = nSocio;
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
	public int getnFactura() {
		return nFactura;
	}
	public void setnFactura(int nFactura) {
		this.nFactura = nFactura;
	}
	public int getnAlbaran() {
		return nAlbaran;
	}
	public void setnAlbaran(int nAlbaran) {
		this.nAlbaran = nAlbaran;
	}
	
	
}
