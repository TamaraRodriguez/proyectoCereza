package modelos;


public class LineaAlbaranEntrada {
	/**
	 * PROPIEDADES
	 */
	private int nAlbaran;
	private int idLinea;
	private String tipo; //No hay que poner enum
	private double peso;
	private double precioKg;
	
	/**
	 * CONSTRUCTORES
	 */
	public LineaAlbaranEntrada(){}
	/**
	 * CONSTRUCTOR PARA EL FORMULARIO
	 * @param tipo
	 * @param peso
	 * @param precioKg
	 */
	public LineaAlbaranEntrada(int nAlbaran, String tipo, double peso, double precioKg){
		
		this.idLinea = -1;
		this.tipo = tipo;
		this.peso = peso;
		this.precioKg = precioKg;
	}
	/**
	 * CONSTRUCTOR PARA EL ROWMAPPER
	 * @param tipo
	 * @param peso
	 * @param precioKg
	 * @param nAlbaran
	 * @param idLinea
	 */
	public LineaAlbaranEntrada (int nAlbaran,int idLinea, String tipo, double peso, double precioKg){
		this.nAlbaran=nAlbaran;
		this.idLinea = idLinea;
		this.tipo = tipo;
		this.peso = peso;
		this.precioKg = precioKg;
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
	public double getPrecioKg() {
		return precioKg;
	}
	public void setPrecioKg(double precioKg) {
		this.precioKg = precioKg;
	}
	public int getnAlbaran() {
		return nAlbaran;
	}
	public void setnAlbaran(int nAlbaran) {
		this.nAlbaran = nAlbaran;
	}
}
