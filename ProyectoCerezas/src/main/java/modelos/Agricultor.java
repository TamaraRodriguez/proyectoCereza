package modelos;

public class Agricultor extends Persona {
	/**
	 * PROPIEDADES
	 */
	private int nSocio;
	
	/**
	 * CONSTRUCTORES
	 */
	public Agricultor (){}
	/*Este constructor es para el formulario*/
	public Agricultor (String cifNif, String nombreRazonSocial, String apellidos, 
			String direccion, String telefono, String email){
		super (cifNif, nombreRazonSocial, apellidos, direccion, telefono, email);
		this.nSocio = -1;
	}
	
	/*Este constructor lo usamos para recuperar clientes en el RowMapper*/
	public Agricultor (int idPersona,String cifNif, String nombreRazonSocial, String apellidos, 
			String direccion, String telefono, String email, int nSocio){
		super (idPersona,cifNif, nombreRazonSocial, apellidos, direccion, telefono, email);
		this.nSocio = nSocio;
	}
	/**
	 * GETTERS AND SETTERS
	 */
	public int getnSocio() {
		return nSocio;
	}
	
}
