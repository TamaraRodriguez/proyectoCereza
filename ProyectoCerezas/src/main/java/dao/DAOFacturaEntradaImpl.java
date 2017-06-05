package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.mysql.jdbc.Statement;
import org.springframework.jdbc.core.PreparedStatementCreator;
import modelos.FacturaEntrada;

public class DAOFacturaEntradaImpl implements DAOFacturaEntrada {

	class FacturaEntradaRowMapper implements RowMapper<FacturaEntrada> {

		public FacturaEntrada mapRow(ResultSet rs, int numRow) throws SQLException {
			FacturaEntrada fe = new FacturaEntrada(rs.getInt("n_factura"),
					new java.util.Date(rs.getDate("fecha").getTime()), rs.getDouble("precio_bruto"), rs.getInt("iva"),
					rs.getDouble("precio_neto"));

			return fe;
		}

	}

	/**
	 * Recupero la conexión con la base de datos.
	 */
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * Función para crear un objeto FacturaEntrada, que devuelve el nFactura del objeto creado.
	 * @param fe -- Objeto FacturaEntrada
	 * @return nFactura
	 */
	public int create(final FacturaEntrada fe) {
		int nFactura = -1;
		
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		/*****************************************************************************************************************************/
		/* Si tenemos precio_bruto e iva, no haría falta guardar precio_neto
		 * Tenemos que comprobar si tenemos que devolver un boolean  */
		final String sql = "insert into factura_e (fecha, precio_bruto, iva, precio_neto) values (?,?,?,?)";

		GeneratedKeyHolder kh = new GeneratedKeyHolder();
		final java.sql.Date d = new java.sql.Date(fe.getFecha().getTime());
		jdbc.update(new PreparedStatementCreator() {

			public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setDate(1, d);
				statement.setDouble(2, fe.getPrecioBruto());
				statement.setInt(3, fe.getIva());
				statement.setDouble(4, fe.getPrecioNeto());
				return statement;
			}
		}, kh);
		
		nFactura = kh.getKey().intValue();
		fe.setnFactura(nFactura);
		return nFactura;
	}

	public FacturaEntrada read(int nFactura) {
		FacturaEntrada fe = null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from factura_e where n_factura=?";
		
		try{
			fe=jdbc.queryForObject(sql,new Object[]{nFactura},new FacturaEntradaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			System.out.println("Read FacturaEntrada -- Data access exception thrown when a result was not of the expected size, for example when expecting a single row but getting 0 or more than 1 rows.");
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("Read FacturaEntrada -- Error acceso de datos");
		} 
		
		return fe;
	}
	/*****************************************************************************************************************************/
	/*Como comentamos había problemas con la modificación de una factura.
	 * Al final que hacemos ¿Modificamos la factura? Y si la modicamos, ¿Qué campos tocamos?*/
	/**
	 * Función que modifica el objeto FacturaEntrada. 
	 * @param fe -- Se introduce un FacturaEntrada
	 * @return r -- Devuelve un boolean que determina si la función se ha ejecutado correctamente o no.
	 */
	public boolean update(FacturaEntrada fe) {
		boolean r=false;
		
		String sql="update factura_e set "
					+ "fecha=?, "	
					+ "precio_bruto=?, "
					+ "iva=?, "
					+ "precio_neto=? "
				+ "where n_factura=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{
							new java.sql.Date(fe.getFecha().getTime()),
							fe.getPrecioBruto(),
							fe.getIva(),
							fe.getPrecioNeto(),
							fe.getnFactura()});
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("Update FacturaEntrada -- Error acceso de datos");
			
		}
		
		return r;
	}
	
	/**
	 * Creamos una función que duelve una lista con todos las facturas de entrada.
	 * @return lista
	 */
	public List<FacturaEntrada> listar() {
		List<FacturaEntrada> lista = null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from factura_e";
		lista=jdbc.query(sql,new FacturaEntradaRowMapper());
		return lista;
	}
	
	/**
	 * Función sobrecargada que devuelve una lista con todos las facturas de entrada.
	 * Sql by laura y marco
	 * @param cifNif
	 * @return lista
	 */
	public List<FacturaEntrada> listar(String cifNif) {
		List<FacturaEntrada> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="SELECT factura_e.precio_bruto,factura_e.iva, "
				+ "factura_e.precio_bruto, factura_e.n_factura,"
				+ "factura_e.fecha "
				+ "from factura_e "
					+ "join albaranes_entrada on(factura_e.n_factura=albaranes_entrada.n_factura)"
					+ "join agricultores on (agricultores.n_socio=albaranes_entrada.n_socio)"
					+ " join personas on (agricultores.id_persona=personas.id_persona)"
				+ " WHERE personas.cif_nif =?;";
		lista=jdbc.query(sql,new Object[]{cifNif}, new FacturaEntradaRowMapper());
		return lista;
	}
/*
	public List<FacturaEntrada> listar(Date fecha) {
		
		List<FacturaEntrada> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from factura_e where fecha=?";
		java.sql.Date d=new java.sql.Date(fecha.getTime());
		lista=jdbc.query(sql,new Object[]{d},new FacturaEntradaRowMapper());
		return lista;
	}
	*/
	/*****************************************************************************************************************************/
	/*Creo que hablamos que no se podía eliminar una factura.
	 Hariamos un método que nos anulara la factura.*/
	/*public boolean delete(int nFactura) {
		String sql="delete from albaranes_entrada where n_albaran=? and n_factura is NULL";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		int n=jdbc.update(sql,new Object[]{nFactura});

		return n>0;
	}*/
	/**
	 * funcion para acotar la fecha de busqueda
	 * @param fechaInicio
	 * @param fechaFinal
	 * @return lista
	 */	
	
	public List<FacturaEntrada> buscarFecha (Date fechaInicio, Date fechaFinal){
		
		List<FacturaEntrada> lista;
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		/*Comprobar la insercion de las sentencias de lectura (las ? entre comillas?????)*/
		String sql="select * from factura_e where fecha BETWEEN '?' AND '?';";
		java.sql.Date fi=new java.sql.Date(fechaInicio.getTime());
		java.sql.Date ff=new java.sql.Date(fechaFinal.getTime());
		lista=jdbc.query(sql,new Object[]{fi,ff},new FacturaEntradaRowMapper());
		return lista;
	}
}
