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

import modelos.Agricultor;

public class DAOAgricultorImpl implements DAOAgricultor {
	
	
class AgricultorRowMapper implements RowMapper<Agricultor>{
		
		public Agricultor mapRow(ResultSet rs,int numRow) throws SQLException{
			
			Agricultor c=new Agricultor(
				rs.getInt("id_persona"),
				rs.getString("cif_nif"),
				rs.getString("nombre_razon_social"),
				rs.getString("apellidos"),
				rs.getString("direccion"),
				rs.getString("telefono"),
				rs.getString("email"),
				rs.getInt("n_socio"),
				rs.getBoolean("baja"));
				
			return c;
		}
	}
	
	/**
	 * Conectar con la base de datos.
	 */
	private DataSource dataSource;
	
	public DataSource getDataSource(){
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Crear objeto agricultor
	 * @param idPersona
	 * @return
	 */
	public boolean create(final Agricultor a){
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		final String sql="insert into personas (cif_nif, nombre_razon_social, apellidos, direccion, telefono, email) values (?,?,?,?,?,?)";
		
		GeneratedKeyHolder kh=new GeneratedKeyHolder();
		int n = jdbc.update(new PreparedStatementCreator(){

			public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement =con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, a.getCifNif());
				statement.setString(2,a.getNombreRazonSocial());
				statement.setString(3, a.getApellidos());
				statement.setString(4,a.getDireccion());
				statement.setString(5,a.getTelefono());
				statement.setString(6,a.getEmail());
				
				return statement;
			}
				
		},kh
		);
		a.setIdPersona(kh.getKey().intValue());
		
		
		GeneratedKeyHolder kh2=new GeneratedKeyHolder();
		final String sql2 = "insert into agricultores (id_persona, baja) values (?,?)";
		int m = jdbc.update(new PreparedStatementCreator(){

			public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement =con.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, a.getIdPersona());
				statement.setBoolean(2, a.isBaja());
				return statement;
			}
				
		},kh2
		);
		a.setnSocio(kh2.getKey().intValue());
		return n>0 && m>0;		
	}
	/*******************************************************************************************************************/
	/**
	 * Tenemos que preguntar si al final vamos a buscar por número de socio o por dni.
	 */
	public Agricultor read(int n_socio){ //Busca agricultores por su número de socio, que es su propiedad diferenciadora de clientes.
		Agricultor c=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select personas.*, agricultores.n_socio from personas join agricultores ON (agricultores.id_persona=personas.id_persona) where agricultores.n_socio=?";
		try{
			c=jdbc.queryForObject(sql,new Object[]{n_socio},new AgricultorRowMapper()); /*Tenemos que tratar esto con AOP*/
		}
		catch(IncorrectResultSizeDataAccessException ics){
			System.out.println("Read Agricultor - Data access exception thrown when a result was not of the expected size, for example when expecting a single row but getting 0 or more than 1 rows.");
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("Read Agricultor - Error acceso de datos");
		}
		
		return c;
	}
	
	public List<Agricultor> read(String busqueda){  //Busca agricultores por nombre, apellido NIE o nº telefono

		List<Agricultor> lista=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select personas.*, agricultores.n_socio "
				+ "from personas join agricultores on (personas.id_persona=agricultores.id_persona) "
				+ "where cif_nif like ? or nombre_razon_social like ? or apellido like ? or telefono like ?";
		try{
			lista=jdbc.query(sql,new Object[]{"%"+busqueda+"%"},new AgricultorRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			System.out.println("ArrayList <Agricultor> read - Data access exception thrown when a result was not of the expected size, for example when expecting a single row but getting 0 or more than 1 rows.");
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("ArrayList <Agricultor> read - Error acceso de datos");
		}
				
		return lista;
	}
	/*REVISAR A PARTIR DE AQUÍ*/
	/**
	 * Función que modifica el objeto agricultor. El agricultor se busca por id_persona.
	 * @param c
	 * @return boolean -- Que determina si se ha llevado a cabo correctamente la función o no.
	 */
	public boolean update(Agricultor c){
		boolean r=false;
		
		String sql="update personas set "
					+ "cif_nif=?, "
					+ "nombre_razon_social=?, "
					+ "apellidos=?, "
					+ "direccion=?, "
					+ "telefono=?, "
					+ "email=? "
				+ "where id_persona=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{
							c.getCifNif(),
							c.getNombreRazonSocial(),
							c.getApellidos(),
							c.getDireccion(),
							c.getTelefono(),
							c.getEmail(),
							c.getIdPersona()});
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("Update - Error acceso de datos");
		}
		
		return r;
	}
	
	/**
	 * Función que devuelve un List con todos los agricultores
	 * @return lista
	 */
	public List<Agricultor> listar(){ 
		
		List<Agricultor> lista;
				
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select personas.*, agricultores.n_socio "
				+ "from personas join agricultores on (personas.id_persona=agricultores.id_persona) "
				+ "order by personas.nombre_razon_social";
		lista=jdbc.query(sql,new AgricultorRowMapper());

		return lista;
	}
	
	/**
	 * Creamos un método que asigna false al campo baja de la tabla agricultores.
	 * @param n_socio
	 * @return r 
	 */
	public boolean delete (int nSocio){
		boolean r=false;
		
		String sql="update agricultores set "
				+ "baja=?, "
				+ "where n_socio=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{true,nSocio});
							
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("Delete - Error acceso de datos");
		}
		
		return r;
	}

	
	
}
