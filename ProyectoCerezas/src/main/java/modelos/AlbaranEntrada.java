package modelos;

import java.util.Date;

public class AlbaranEntrada {
	/**
	 * PROPIEDADES
	 */
	private int nAlbaran;
	private int nSocio;
	private Date fecha; /*Hacer un Utils para gestionar la fecha*/
	private String lugarRecogida;
	private String nFactura;
	
	/**
	 * CONSTRUCTORES
	 */
	public AlbaranEntrada (){}
	/**
	 * CONSTRUCTOR PARA EL FORMULARIO
	 * @param nSocio
	 * @param fecha
	 * @param lugarRecogida
	 * @param nFactura
	 */
	public AlbaranEntrada (int nSocio, Date fecha, String lugarRecogida, String nFactura){
		this.nAlbaran = -1;
		this.nSocio = nSocio;
		this.fecha = fecha;
		this.lugarRecogida = lugarRecogida;
		this.nFactura = nFactura;	
	}
	/**
	 * CONSTRUCTOR PARA EL ROWMAPPER
	 * @param nSocio
	 * @param fecha
	 * @param lugarRecogida
	 * @param nFactura
	 */
	public AlbaranEntrada (int nAlbaran,int nSocio, Date fecha, String lugarRecogida, String nFactura){
		this.nAlbaran = nAlbaran;
		this.nSocio = nSocio;
		this.fecha = fecha;
		this.lugarRecogida = lugarRecogida;
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
	public String getLugarRecogida() {
		return lugarRecogida;
	}
	public void setLugarRecogida(String lugarRecogida) {
		this.lugarRecogida = lugarRecogida;
	}
	public String getnFactura() {
		return nFactura;
	}
	public void setnFactura(String nFactura) {
		this.nFactura = nFactura;
	}
	public int getnAlbaran() {
		return nAlbaran;
	}
	
}
