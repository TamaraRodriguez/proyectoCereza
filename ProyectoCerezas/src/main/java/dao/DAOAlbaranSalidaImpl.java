package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import modelos.AlbaranSalida;

public class DAOAlbaranSalidaImpl {
class AlbaranSalidaRowMapper implements RowMapper<AlbaranSalida>{
		
		public AlbaranSalida mapRow(ResultSet rs,int numRow) throws SQLException{
			AlbaranSalida as=new AlbaranSalida(
					rs.getInt("n_albaran"),
					rs.getInt("n_cliente"),
					new java.util.Date(rs.getDate("fecha").getTime()),
					rs.getInt("n_factura"));
			
			return as;
		}
		
	}
	/**
	 * Establecemos la conexión con la base de datos.
	 */
	private DataSource dataSource;
		
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	/***************************************************************************************/
	/*Hay que elegir entre un método de creación y otro*/
	/**
	 * Función para crear un objeto AlbaranEntrada, que devuelve el nAlbaran del objeto creado.
	 * @param a
	 * @return nAlbaran
	 */
	public int create(final AlbaranSalida a){
		int nAlbaran = -1;
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		final String sql="insert into albaranes_salida (n_albaran, n_cliente, fecha, n_factura) values (?,?,?,?)";
		
		GeneratedKeyHolder kh=new GeneratedKeyHolder();
		final java.sql.Date d = new java.sql.Date(a.getFecha().getTime());
		
		jdbc.update(new PreparedStatementCreator(){

			public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement =con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, a.getnAlbaran());
				statement.setInt(2,a.getnCliente());
				statement.setDate(3, d);
				statement.setInt(4,a.getnFactura());

				return statement;
			}
				
		},kh
		);
		nAlbaran = kh.getKey().intValue();
		a.setnAlbaran(nAlbaran);
		return nAlbaran;		
	}
	/*public int create(AlbaranSalida as){
		int nAlbaran=-1;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="insert into albaranes_salida (n_socio,fecha,n_factura)"
				+ "values (?,?,?)";
		java.sql.Date d=new java.sql.Date(as.getFecha().getTime());
		try{
			jdbc.update(
				sql,
				new Object[]{as.getnCliente(),d,as.getnFactura()});
			
			//Recupero el n_albaran del ultimo albaran añadido para luego poder incluirlo en las lineas de albaran creadas

			nAlbaran=jdbc.queryForObject("SELECT MAX(n_albaran) FROM albaranes_salida", Integer.class );
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		return nAlbaran;		
	}*/
	
	/**
	 * Función que devuelve un objeto AlbaranSalida 
	 * @param nAlbaran
	 * @return as
	 */
	public AlbaranSalida read(int nAlbaran){
		AlbaranSalida as=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select * from albaranes_salida where n_albaran=?";
		try{
			as=jdbc.queryForObject(sql,new Object[]{nAlbaran},new AlbaranSalidaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			System.out.println("Read AlbaranSalida -- Data access exception thrown when a result was not of the expected size, for example when expecting a single row but getting 0 or more than 1 rows.");
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("Read AlbaranSalida -- Error acceso de datos");
		}
		
		return as;
	}
	
	public boolean update(AlbaranSalida as){ //Modifica un albaran
		boolean r=false;
		
		String sql="update albaranes_salida set "
					+ "n_cliente=?, "
					+ "fecha=?,"
					+ "n_factura=? "
				+ "where n_albaran=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{
							as.getnCliente(),
							new java.sql.Date(as.getFecha().getTime()),
							as.getnFactura(),
							as.getnAlbaran()});
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("Update AlbaranEntrada -- Error acceso de datos");
		}
		
		return r;
	}
	
	/**
	 * Modifica el nFactura para indicar así que el albarán ha sido facturado
		si queremos quitar un albaran de una factura pasar nFactura=0 y el albarán 
		volvería a quedar sin estar facturado
	 * @param nAlbaran
	 * @param nFactura
	 * @return boolean -- Determina si la función se ha ejecutado correctamente o no.
	 */
	public boolean facturar(int nAlbaran, int nFactura){ 

		boolean r=false;
		
		String sql="update albaranes_salida set n_factura=? where n_albaran=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{nFactura, nAlbaran});
			r=n>0;
		}
		catch(DataAccessException dae){
			System.out.println("facturar AlbaranSalida - Error acceso de datos");
			dae.printStackTrace();
		}
		
		return r;
	}
	/**
	 * Creamos una función que duelve una lista con todos los albaranes de salida.
	 * @return lista
	 */
	public List<AlbaranSalida> listar(){
		List<AlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_salida";
		lista=jdbc.query(sql,new AlbaranSalidaRowMapper());
		return lista;
	}
	/********************************************************************************************/
	/*Hay que preguntar si hacemos un list <AlbaranSalida> listar sólo para los facturados. Tenemos uno para los no facturados.*/
	/*Dentro del select hemos puesto los campos de AlbaranSalida habría que discutir que campos vamos a mostrar*/
	/**
	 * Creamos una función que duelve una lista con todos los albaranes de entrada filtrados por cif_nif.
	 * @return lista
	 */
	public List<AlbaranSalida> listar(int cifNif){ 
		List<AlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="SELECT albaranes_salida.n_albaran,albaranes_salida.n_cliente,"
				+ "albaranes_salida.fecha,albaranes_salida.n_factura from albaranes_salida"
				+ " join clientes on (albaranes_salida.n_cliente = clientes.n_cliente) join"
				+ " personas on (personas.id_persona = clientes.id_persona)"
				+ " where personas.cif_nif = ?; ";
		lista=jdbc.query(sql,new Object[]{cifNif},new AlbaranSalidaRowMapper());
		return lista;
	}

	/**
	 * Función sobrecargada para buscar por fecha.
	 * @param fecha
	 * @return  lista -- Devuelve una lista con todos los objetos AlbaranEntrada
	 */
	public List<AlbaranSalida> listar(Date fecha){
		List<AlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_salida where fecha=?";
		java.sql.Date d=new java.sql.Date(fecha.getTime());
		lista=jdbc.query(sql,new Object[]{d},new AlbaranSalidaRowMapper());
		return lista;
	}
	/************************************************************************************************************/
	/*Dentro del select hemos puesto los campos de AlbaranSalida habría que discutir que campos vamos a mostrar*/
	/**
	 * Función sobrecargada que devuelve una lista con todos los albaranes no facturados.
	 * @param cifNif
	 * @return lista
	 */
	public List<AlbaranSalida> listarPendientes(int cifNif){ 
		
		List<AlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="SELECT albaranes_salida.n_albaran,albaranes_salida.n_cliente,"
				+ "albaranes_salida.fecha,albaranes_salida.n_factura from albaranes_salida"
				+ " join clientes on (albaranes_salida.n_cliente = clientes.n_cliente) "
				+ "join personas on (personas.id_persona = clientes.id_persona) "
				+ "where personas.cif_nif = ?;  and albaranes_salida.n_factura is NULL;";
		lista=jdbc.query(sql,new Object[]{cifNif},new AlbaranSalidaRowMapper());
		return lista;
	}
	/**
	 * Borra un albaran, hay que controlar que sea un albarán que nFactura sea nulo, es decir, que no esté facturado.
	 * @param nAlbaran
	 * @return boolean -- Devuelve un boolean que comprueba si se ha ejecutado correctamente o no.
	 */
	public boolean delete(int nAlbaran){ 
		String sql="delete from albaranes_salida where n_albaran=? and n_factura is NULL";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		int n=jdbc.update(sql,new Object[]{nAlbaran});
		
		return n>0;
	}
	
}
