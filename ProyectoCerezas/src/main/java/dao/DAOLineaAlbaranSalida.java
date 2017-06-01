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

public class DAOLineaAlbaranSalida {
class LineaAlbaranSalidaRowMapper implements RowMapper<LineaAlbaranSalida>{
		
		public LineaAlbaranSalida mapRow(ResultSet rs,int numRow) throws SQLException{
			LineaAlbaranSalida las=new LineaAlbaranSalida(
					rs.getInt("nAlbaran"),
					rs.getInt("idLinea"),
					rs.getString("tipo"),
					rs.getInt("nCajas"),
					rs.getDouble("pesoCaja"),
					rs.getDouble("precioCaja"));
			
			return las;
		}
		
	}
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Crear una nueva linea en un albaran
	 * @param LineaAlbaranSalida
	 * @return true o false
	 */
	public boolean create(LineaAlbaranSalida las){
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
				
		String sql="insert into lineas_albaranes_s (n_albaran,tipo, n_cajas, peso_caja,precio_caja)"
				+ "values (?,?,?,?,?)";

		jdbc.update(
			sql, new Object[]{
					las.getnAlbaran(),
					las.getTipo(),
					las.getnCajas(),
					las.getPesoCaja(),
					las.getPrecioCaja()});
					
		return true;		
	}
	
	/**
	 * Leer una linea por su id
	 * @param idLinea
	 * @return Linea Albaran
	 */
	public LineaAlbaranSalida read(int idLinea){
		LineaAlbaranSalida las=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select * from linea_albaran_s where id_linea=?";
		try{
			las=jdbc.queryForObject(sql,new Object[]{idLinea},new LineaAlbaranSalidaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
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
		
		String sql="update LineaAlbaranSalida set " 
					+ "n_albaran=?,"
					+ "tipo=?,"
					+ "n_cajas,"
					+ "peso_caja=?,"
					+ "precio_caja=?"
				+ "where id_linea=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{
							las.getnAlbaran(),
							las.getTipo(),
							las.getnCajas(),
							las.getPesoCaja(),
							las.getPrecioCaja()});
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * Listar todos las lineas de albaran de un albaran
	 * @param nAlbaran
	 * @return lista de LineaAlbaranSalida
	 * 
	 */
	public List<LineaAlbaranSalida> listar(int nAlbaran){ 
		List<LineaAlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from linea_albaran_s where n_albaran=?";
		lista=jdbc.query(sql,new Object[]{nAlbaran},new LineaAlbaranSalidaRowMapper());
		return lista;
	}
	
	/**
	 * Borra una linea
	 * @param idLinea
	 * @return true o false
	 */
	
	public boolean delete(int idLinea){ 
		
		boolean r=false;
		
		String sql="delete from linea_albaran_s where id_linea=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		int n=jdbc.update(sql,new Object[]{idLinea});
		r=n>0;
		
		return r;
	}
	

}
