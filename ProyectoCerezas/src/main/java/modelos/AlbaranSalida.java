package modelos;

import java.util.Date;

import Utils.DateUtils;

public class AlbaranSalida {
	/**
	 * PROPIEDADES
	 */
	private int nAlbaran;
	private int nCliente;
	private Date fecha; /*Hacer un Utils para gestionar la fecha*/
	private String nFactura;
	/**
	 * CONSTRUCTORES
	 */
	public AlbaranSalida(){}
	
	/**
	 * CONSTRUCTOR PARA EL FORMULARIO
	 * @param nCliente
	 * @param fecha
	 * @param nFactura
	 */
	public AlbaranSalida(int nCliente, Date fecha, String nFactura){
		this.nAlbaran = -1;
		this.nCliente = nCliente;
		this.fecha = fecha;
		this.nFactura = nFactura;	
	}
	
	/**
	 * CONSTRUCTOR PARA EL ROWMAPPER
	 * @param nCliente
	 * @param fecha
	 * @param nFactura
	 */
	public AlbaranSalida(int nAlbaran,int nCliente, Date fecha, String nFactura){
		this.nAlbaran = nAlbaran;
		this.nCliente = nCliente;
		this.fecha = fecha;
		this.nFactura = nFactura;	
	}
	
	
	/**
	 * GETTER AND SETTER
	 */
	public int getnCliente() {
		return nCliente;
	}
	public void setnCliente(int nCliente) {
		this.nCliente = nCliente;
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
