package modelos;

public class LineaAlbaranSalida{
	/**
	 * PROPIEDADES
	 */
	private int nAlbaran;
	private int idLinea;
	private String tipo; //No hay que poner enum
	private int nCajas;
	private double peso; /*El peso tendría que ser double, en la Base de datos se ha puesto int*/
	private double precioCaja;
	
	/**
	 * CONSTRUCTORES
	 */
	public LineaAlbaranSalida(){}

	/**
	 * CONSTRUCTOR PARA EL FORMULARIO
	 * @param tipo
	 * @param nCajas
	 * @param peso
	 * @param precioCaja
	 */
	public LineaAlbaranSalida(int nAlbaran,String tipo,int nCajas, double peso, double precioCaja){
		this.nAlbaran=nAlbaran;
		this.idLinea = -1;
		this.tipo = tipo;
		this.nCajas = nCajas;
		this.peso = peso;
		this.precioCaja = precioCaja;
	}
	
	/**
	 * CONSTRUCTOR PARA EL ROWMAPPER
	 * @param idLinea
	 * @param tipo
	 * @param nCajas
	 * @param peso
	 * @param precioCaja
	 */
	public LineaAlbaranSalida(int nAlbaran,int idLinea, String tipo, int nCajas, double peso, double precioCaja){
		this.nAlbaran = nAlbaran;
		this.idLinea = idLinea;
		this.tipo = tipo;
		this.nCajas = nCajas;
		this.peso = peso;
		this.precioCaja = precioCaja;
	}
	
	/**
	 * GETTER AND SETTER
	 */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getIdLinea() {
		return idLinea;
	}
	public int getnCajas() {
		return nCajas;
	}
	public void setnCajas(int nCajas) {
		this.nCajas = nCajas;
	}
	public double getPrecioCaja() {
		return precioCaja;
	}
	public void setPrecioCaja(double precioCaja) {
		this.precioCaja = precioCaja;
	}
	public int getnAlbaran() {
		return nAlbaran;
	}

	public void setnAlbaran(int nAlbaran) {
		this.nAlbaran = nAlbaran;
	}

}
