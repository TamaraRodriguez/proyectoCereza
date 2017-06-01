package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dao.DAOLineaAlbaranEntrada.LineaAlbaranEntradaRowMapper;
import modelos.LineaAlbaranEntrada;

public class DAOLineaAlbaranEntrada {
	
	class LineaAlbaranEntradaRowMapper implements RowMapper<LineaAlbaranEntrada>{
		
		public LineaAlbaranEntrada mapRow(ResultSet rs,int numRow) throws SQLException{
			LineaAlbaranEntrada lae=new LineaAlbaranEntrada(
					rs.getInt("nAlbaran"),
					rs.getInt("idLinea"),
					rs.getString("tipo"),
					rs.getDouble("peso"),
					rs.getDouble("pesoKg"));
			
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
	
	public LineaAlbaranEntrada read(String tipo){
		LineaAlbaranEntrada v=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select * from linea_albaran_e where tipo=?";
		try{
			v=jdbc.queryForObject(sql,new Object[]{tipo},new LineaAlbaranEntradaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return v;
	}
	
	/**
	 * Modifica una variedad de cereza
	 * @param v
	 * @return true o false
	 * 
	 */
	public boolean update(LineaAlbaranEntrada v){ 
		boolean r=false;
		
		String sql="update LineaAlbaranEntrada set " 
					+ "precio_kg=?,"
					+ "peso_caja=?,"
					+ "precio_caja=?"
				+ "where tipo=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{
							v.getPrecioKg(),
							v.getPesoCaja(),
							v.getPrecioCaja(),
							v.getTipo()});
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * Listar todos los tipos de cerezas
	 * @return lista de LineaAlbaranEntrada
	 * 
	 */
	public List<LineaAlbaranEntrada> listar(){ 
		List<LineaAlbaranEntrada> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from linea_albaran_e";
		lista=jdbc.query(sql,new LineaAlbaranEntradaRowMapper());
		return lista;
	}
	
	/**
	 * Borra una variedad de cerezas y sus datos
	 * @param nAlbaran
	 * @return
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
