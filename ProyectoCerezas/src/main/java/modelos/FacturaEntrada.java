package modelos;

public class FacturaEntrada {
	/**
	 * PROPIEDADES
	 */
	private String nFactura;
	private double precioBruto;
	private int iva;
	/*En la base de datos se ha introducido retenciones, pero quedamos en que no se iba a incluir*/
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
	 */
	public FacturaEntrada (String nFactura, double precioBruto, int iva, double precioNeto){
		this.nFactura = nFactura;
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
	

	
}
