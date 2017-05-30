package modelos;

public class Cliente extends Persona {
	/**
	 * PROPIEDADES
	 */
	private int nCliente;
	
	/**
	 * CONSTRUCTORES
	 */
	public Cliente (){}
	/*Este constructor lo tenemos para crear cliente*/
	public Cliente (String cifNif, String nombreRazonSocial, String apellidos, 
			String direccion, String telefono, String email){
		super (cifNif, nombreRazonSocial, apellidos, direccion, telefono, email);
		this.nCliente = -1;
	}
	/*Este constructor lo usamos para recuperar clientes en el RowMapper*/
	public Cliente (int idPersona,String cifNif, String nombreRazonSocial, String apellidos, 
			String direccion, String telefono, String email, int nCliente){
		super (idPersona,cifNif, nombreRazonSocial, apellidos, direccion, telefono, email);
		this.nCliente = nCliente;
	}
	/**
	 * GETTERS AND SETTERS
	 */
	public int getnSocio() {
		return nCliente;
	}
}
