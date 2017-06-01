package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import modelos.LineaAlbaranEntrada;

public class DAOLineaAlbaranEntrada {
	
	class LineaAlbaranEntradaRowMapper implements RowMapper<LineaAlbaranEntrada>{
		
		public LineaAlbaranEntrada mapRow(ResultSet rs,int numRow) throws SQLException{
			LineaAlbaranEntrada lae=new LineaAlbaranEntrada(
					rs.getInt("nAlbaran"),
					rs.getInt("idLinea"),
					rs.getString("tipo"),
					rs.getDouble("peso"),
					rs.getDouble("precioKg"));
			
			return lae;
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
	 * @param LineaAlbaranEntrada
	 * @return true o false
	 */
	public boolean create(LineaAlbaranEntrada lae){
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
				
		String sql="insert into lineas_albaranes_e (n_albaran,tipo,peso,precio_kg)"
				+ "values (?,?,?,?)";

		jdbc.update(
			sql, new Object[]{
					lae.getnAlbaran(),
					lae.getTipo(),
					lae.getPeso(),
					lae.getPrecioKg()});
					
		return true;		
	}
	
	/**
	 * Leer una linea por su id
	 * @param idLinea
	 * @return Linea Albaran
	 */
	public LineaAlbaranEntrada read(int idLinea){
		LineaAlbaranEntrada lae=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select * from linea_albaran_e where id_linea=?";
		try{
			lae=jdbc.queryForObject(sql,new Object[]{idLinea},new LineaAlbaranEntradaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return lae;
	}
	
	/**
	 * Modifica una linea de albaran
	 * @param idLinea
	 * @return true o false
	 * 
	 */
	public boolean update(LineaAlbaranEntrada lae){ 
		boolean r=false;
		
		String sql="update LineaAlbaranEntrada set " 
					+ "n_albaran=?,"
					+ "tipo=?,"
					+ "peso=?,"
					+ "precio_kg=?"
				+ "where id_linea=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{
							lae.getnAlbaran(),
							lae.getTipo(),
							lae.getPeso(),
							lae.getPrecioKg()});
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
	 * @return lista de LineaAlbaranEntrada
	 * 
	 */
	public List<LineaAlbaranEntrada> listar(int nAlbaran){ 
		List<LineaAlbaranEntrada> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from linea_albaran_e where n_albaran=?";
		lista=jdbc.query(sql,new Object[]{nAlbaran},new LineaAlbaranEntradaRowMapper());
		return lista;
	}
	
	/**
	 * Borra una linea
	 * @param idLinea
	 * @return true o false
	 */
	
	public boolean delete(int idLinea){ 
		
		boolean r=false;
		
		String sql="delete from linea_albaran_e where id_linea=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		int n=jdbc.update(sql,new Object[]{idLinea});
		r=n>0;
		
		return r;
	}
	

}
