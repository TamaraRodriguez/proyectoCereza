package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import modelos.Variedades;

public class DAOVariedades {

	class VariedadesRowMapper implements RowMapper<Variedades>{
		
		public Variedades mapRow(ResultSet rs,int numRow) throws SQLException{
			Variedades v=new Variedades(
					rs.getString("tipo"),
					rs.getDouble("precioKg"),
					rs.getDouble("pesoCaja"),
					rs.getDouble("precioCaja"));
			
			return v;
		}
		
	}
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public boolean create(Variedades v){
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
				
		String sql="insert into variedades (tipo,precio_kg,peso_caja,precio_caja)"
				+ "values (?,?,?,?)";

		jdbc.update(
			sql, new Object[]{
					v.getTipo(),
					v.getPrecioKg(),
					v.getPesoCaja(),
					v.getPrecioCaja()});
					
		return true;		
	}
	
	public Variedades read(String tipo){
		Variedades v=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select * from variedades where tipo=?";
		try{
			v=jdbc.queryForObject(sql,new Object[]{tipo},new VariedadesRowMapper());
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
	public boolean update(Variedades v){ 
		boolean r=false;
		
		String sql="update variedades set " 
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
	 * @return lista de variedades
	 * 
	 */
	public List<Variedades> listar(){ 
		List<Variedades> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from variedades";
		lista=jdbc.query(sql,new VariedadesRowMapper());
		return lista;
	}
	
	/**
	 * Borra una variedad de cerezas y sus datos
	 * @param nAlbaran
	 * @return
	 */
	
	public boolean delete(String tipo){ 
		
		boolean r=false;
		
		String sql="delete from variedades where tipo=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		int n=jdbc.update(sql,new Object[]{tipo});
		r=n>0;
		
		return r;
	}
	
}
