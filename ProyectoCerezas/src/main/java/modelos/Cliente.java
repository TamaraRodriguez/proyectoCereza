package modelos;

public class Cliente extends Persona {
	/**
	 * PROPIEDADES
	 */
	private int nCliente;
	private boolean baja;

	/**
	 * CONSTRUCTORES
	 */
	public Cliente (){}
	/*Este constructor lo tenemos para crear cliente*/
	public Cliente (String cifNif, String nombreRazonSocial, String apellidos, 
			String direccion, String telefono, String email){
		super (cifNif, nombreRazonSocial, apellidos, direccion, telefono, email);
		this.nCliente = -1;
		this.baja = false;
	}
	/*Este constructor lo usamos para recuperar clientes en el RowMapper*/
	public Cliente (int idPersona,String cifNif, String nombreRazonSocial, String apellidos, 
			String direccion, String telefono, String email, int nCliente, boolean baja){
		super (idPersona,cifNif, nombreRazonSocial, apellidos, direccion, telefono, email);
		this.nCliente = nCliente;
		this.baja = baja;
	}
	/**
	 * GETTERS AND SETTERS
	 */
	public int getnSocio() {
		return nCliente;
	}
	public int getnCliente() {
		return nCliente;
	}
	public void setnCliente(int nCliente) {
		this.nCliente = nCliente;
	}
	public boolean isBaja() {
		return baja;
	}
	public void setBaja(boolean baja) {
		this.baja = baja;
	}
	
}
