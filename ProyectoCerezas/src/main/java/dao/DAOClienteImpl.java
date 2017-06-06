package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import modelos.Cliente;

public class DAOClienteImpl implements DAOCliente {

	class ClienteRowMapper implements RowMapper<Cliente> {

		public Cliente mapRow(ResultSet rs, int numRow) throws SQLException {

			Cliente c = new Cliente(rs.getInt("id_persona"), rs.getString("cif_nif"), rs.getString("nombre_razon_social"),
					rs.getString("apellidos"), rs.getString("direccion"), rs.getString("telefono"),
					rs.getString("email"), rs.getInt("n_cliente"), rs.getBoolean("baja"));
			return c;
		}
	}

	/**
	 * ESTABLECEMOS LA CONEXIÓN CON LA BASE DE DATOS
	 */
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * MÉTODO CREACIÓN DE UN OBJETO DE TIPO CLIENTE
	 * 
	 * @param c
	 * @return boolean que señala si se ha ejecutado bien el método o no.
	 */
	public boolean create(final Cliente c) {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		final String sql = "insert into personas (cif_nif, nombre_razon_social, apellidos, direccion, telefono, email) values (?,?,?,?,?,?)";

		GeneratedKeyHolder kh = new GeneratedKeyHolder();
		int n = jdbc.update(new PreparedStatementCreator() {

			public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, c.getCifNif());
				statement.setString(2, c.getNombreRazonSocial());
				statement.setString(3, c.getApellidos());
				statement.setString(4, c.getDireccion());
				statement.setString(5, c.getTelefono());
				statement.setString(6, c.getEmail());
				return statement;
			}

		}, kh);
		c.setIdPersona(kh.getKey().intValue());

		GeneratedKeyHolder kh2 = new GeneratedKeyHolder();
		final String sql2 = "insert into clientes (id_persona, baja) values (?,?)";
		int m = jdbc.update(new PreparedStatementCreator() {

			public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, c.getIdPersona());
				statement.setBoolean(2, c.isBaja());
				return statement;
			}

		}, kh2);
		c.setnCliente(kh2.getKey().intValue());
		return n > 0 && m > 0;

	}

	/**
	 * Función para recuperar un objeto cliente
	 * 
	 * @param nCliente
	 * @return c
	 */
	public Cliente read(int nCliente) {
		Cliente c = null;

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		String sql = "select personas.*, clientes.n_cliente from personas"
				+ " join clientes ON (clientes.id_persona=personas.id_persona)"
				+ " where clientes.n_cliente=?";
		try {
			c = jdbc.queryForObject(sql, new Object[] { nCliente }, new ClienteRowMapper());
		} catch (IncorrectResultSizeDataAccessException ics) {
			System.out.println(
					"Read Cliente - Data access exception thrown when a result was not of the expected size, for example when expecting a single row but getting 0 or more than 1 rows.");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			System.out.println("Read Cliente - Error acceso de datos");
		}

		return c;
	}

	/**
	 * Función que devuelve un List con todos los clientes.
	 * 
	 * @param busqueda
	 *            (por cif/nif, razón social, apellido y por teléfono)
	 * @return lista
	 */
	public List<Cliente> read(String busqueda) {
		List<Cliente> lista = null;

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		String sql = "select personas.*, clientes.n_cliente "
				+ "from personas join clientes on (personas.id_persona=clientes.id_persona) "
				+ "where cif_nif like ? or nombre_razon_social like ? or apellidos like ? or telefono like ?";
		try {
			String b="%"+busqueda+"%";
			lista = jdbc.query(sql, new Object[] {b,b,b,b}, new ClienteRowMapper());
		} catch (IncorrectResultSizeDataAccessException ics) {
			System.out.println(
					"ArrayList <Agricultor> read - Data access exception thrown when a result was not of the expected size, for example when expecting a single row but getting 0 or more than 1 rows.");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			System.out.println("ArrayList <Cliente> read - Error acceso de datos");
		}

		return lista;
	}
	/**
	 * Función que modifica el objeto cliente. El cliente se busca por id_persona.
	 * @param c
	 * @return boolean -- Que determina si se ha llevado a cabo correctamente la función o no.
	 */
	public boolean update(Cliente c) {
		boolean r = false;

		String sql = "update personas set " 
				+ "cif_nif=?," 
				+ "nombre_razon_social=?," 
				+ "apellidos=?," 
				+ "direccion=?, "
				+ "telefono=?," 
				+ "email=? " 
				+ "where id_persona=?";

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		try {
			int n = jdbc.update(sql, new Object[] { 
					c.getCifNif(), 
					c.getNombreRazonSocial(), 
					c.getApellidos(),
					c.getDireccion(), 
					c.getTelefono(), 
					c.getEmail(), 
					c.getIdPersona()});
			r = n > 0;
			
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			System.out.println("Update - Error acceso de datos");
		}

		return r;
	}
	/**
	 * Función que devuelve un List con todos los clientes
	 * @return lista
	 */
	public List<Cliente> listar() {

		List<Cliente> lista;

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		String sql = "select personas.*, clientes.n_cliente "
				+ "from personas join clientes on (personas.id_persona=clientes.id_persona) "
				+ "order by personas.nombre_razon_social";
		lista = jdbc.query(sql, new ClienteRowMapper());

		return lista;
	}

	/**
	 * Creamos un método que asigna false al campo baja de la tabla clientes.
	 * @param n_cliente
	 * @return r 
	 */
	public boolean delete (int nCliente){
		boolean r=false;
		
		String sql="update clientes set "
				+ "baja=?, "
				+ "where n_cliente=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{true,nCliente});
							
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("Delete - Error acceso de datos");
		}
		
		return r;
	}

}
