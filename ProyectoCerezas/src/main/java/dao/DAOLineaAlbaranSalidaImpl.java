package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import modelos.LineaAlbaranSalida;

public class DAOLineaAlbaranSalidaImpl implements DAOLineaAlbaranSalida{
	/**
	 * Creamos una clase RowMapper para recuperar objetos de clase LineaAlbaranSalida. 
	 */
	class LineaAlbaranSalidaRowMapper implements RowMapper<LineaAlbaranSalida>{
		
		public LineaAlbaranSalida mapRow(ResultSet rs,int numRow) throws SQLException{
			LineaAlbaranSalida las=new LineaAlbaranSalida(
					rs.getInt("n_albaran"),
					rs.getInt("id_linea"),
					rs.getString("tipo"),
					rs.getInt("numero_cajas"),
					rs.getDouble("peso_caja"),
					rs.getDouble("precio_caja"));
			
			return las;
		}
		
	}
	
	/**
	 * Conexión con la base de datos.
	 */
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Crear una nueva linea en un albaran
	 * @param LineaAlbaranEntrada
	 * @return true o false
	 */
	public boolean create(LineaAlbaranSalida las){
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
				
		String sql="insert into lineas_albaranes_s (n_albaran,tipo,numero_cajas,peso_caja,precio_caja)"
				+ "values (?,?,?,?,?)";

		int n = jdbc.update(
			sql, new Object[]{
					las.getnAlbaran(),
					las.getTipo(),
					las.getnCajas(),
					las.getPesoCaja(),
					las.getPrecioCaja()
					});
					
		return n>0;		
	}
	
	/**
	 * Leer una linea por su id
	 * @param idLinea
	 * @return Linea Albaran
	 */
	public LineaAlbaranSalida read(int idLinea){
		LineaAlbaranSalida las=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select * from lineas_albaranes_s where id_linea=?";
		try{
			las=jdbc.queryForObject(sql,new Object[]{idLinea},new LineaAlbaranSalidaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			System.out.println("Read LineaAlbaranesSalida -- Data access exception thrown when a result was not of the expected size, for example when expecting a single row but getting 0 or more than 1 rows.");

		}
		catch(DataAccessException das){
			das.printStackTrace();
			System.out.println("Read LineaAlbaranSalida -- Error acceso de datos");
		}
		
		return las;
	}
	
	/**
	 * Modifica una linea de albaran
	 * @param idLinea
	 * @return true o false
	 * 
	 */
	public boolean update(LineaAlbaranSalida las){ 
		boolean r=false;
		
		String sql="update lineas_albaranes_s set " 
					+ "n_albaran=?,"
					+ "tipo=?,"
					+ "numero_cajas=?,"
					+ "peso_caja=?,"
					+ "precio_caja=?"
				+ "where id_linea=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n = jdbc.update(
					sql, new Object[]{
							las.getnAlbaran(),
							las.getTipo(),
							las.getnCajas(),
							las.getPesoCaja(),
							las.getPrecioCaja()
							});
			r=n>0;
		}
		catch(DataAccessException das){
			das.printStackTrace();
			System.out.println("update lineasAlbaranSalida -- Data access exception thrown when a result was not of the expected size, for example when expecting a single row but getting 0 or more than 1 rows.");

		}
		
		return r;
	}
	
	/**
	 * Listar todos las lineas de albaran de un albaran
	 * @param nAlbaran
	 * @return lista de LineaAlbaranEntrada
	 * 
	 */
	public List<LineaAlbaranSalida> listar(int nAlbaran){ 
		List<LineaAlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from lineas_albaranes_s where n_albaran=?";
		lista=jdbc.query(sql,new Object[]{nAlbaran},new LineaAlbaranSalidaRowMapper());
		return lista;
	}
	
	/**
	 * Borra una linea
	 * @param idLinea
	 * @return true o false
	 */
	
	public boolean delete(int idLinea){ 
			
		String sql="delete from lineas_albaranes_s where id_linea=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		int n=jdbc.update(sql,new Object[]{idLinea});
		return n>0;
	}
	

}