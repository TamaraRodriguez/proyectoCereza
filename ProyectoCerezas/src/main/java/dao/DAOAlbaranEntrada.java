package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import modelos.AlbaranEntrada;



public class DAOAlbaranEntrada {
	class AlbaranEntradaRowMapper implements RowMapper<AlbaranEntrada>{
		
		public AlbaranEntrada mapRow(ResultSet rs,int numRow) throws SQLException{
			AlbaranEntrada ae=new AlbaranEntrada(
					rs.getInt("nAlbaran"),
					rs.getInt("nSocio"),
					new java.util.Date(rs.getDate("fecha").getTime()),
					rs.getString("lugarRecogida"),
					rs.getString("nFactura"));
			
			return ae;
		}
		
	}
	
	private DataSource dataSource;
		
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int create(AlbaranEntrada ae){
		int nAlbaran=-1;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="insert into albaranes_entrada (n_socio,fecha,lugar_recogida,n_factura)"
				+ "values (?,?,?,?)";
		java.sql.Date d=new java.sql.Date(ae.getFecha().getTime());
		try{
			jdbc.update(
				sql,
				new Object[]{ae.getnSocio(),d,ae.getLugarRecogida(),ae.getnFactura()});
			
			//Recupero el n_albaran del ultimo albaran añadido para luego poder incluirlo en las lineas de albaran creadas
			
			nAlbaran=jdbc.queryForObject("SELECT MAX(n_albaran) FROM albaranes_entrada", Integer.class );
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		return nAlbaran;		
	}
	
	public AlbaranEntrada read(int nAlbaran){
		AlbaranEntrada ae=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select * from albaranes_entrada where n_albaran=?";
		try{
			ae=jdbc.queryForObject(sql,new Object[]{nAlbaran},new AlbaranEntradaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return ae;
	}
	
	public boolean update(AlbaranEntrada ae){ //Modifica un albaran
		boolean r=false;
		
		String sql="update albaranes_entrada set "
					+ "n_socio=?,"
					+ "fecha=?,"
					+ "lugar_recogida=?,"
					+ "n_factura=? "
				+ "where n_albaran=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{
							ae.getnSocio(),
							new java.sql.Date(ae.getFecha().getTime()),
							ae.getLugarRecogida(),
							ae.getnFactura(),
							ae.getnAlbaran()});
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * Modifica el nFactura para indicar así que el albarán ha sido facturado
		si queremos quitar un albaran de una factura pasar nFactura=0 y el albarán 
		volvería a quedar sin estar facturado
	 * @param nAlbaran
	 * @param nFactura
	 * @return
	 */
	public boolean facturar(int nAlbaran, int nFactura){ 
				
		boolean r=false;
		
		String sql="update albaranes_entrada set n_factura=? where n_albaran=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		try{
			int n=jdbc.update(
					sql,
					new Object[]{nFactura, nAlbaran});
			r=n>0;
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return r;
	}
	
	public List<AlbaranEntrada> listar(){ //Devuelve una lista de todos los albaranes
		List<AlbaranEntrada> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_entrada";
		lista=jdbc.query(sql,new AlbaranEntradaRowMapper());
		return lista;
	}
	
	public List<AlbaranEntrada> listar(int cifNif){ //Devuelve todos los albaranes por cifNif
		List<AlbaranEntrada> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_entrada where cif_nif=?";
		lista=jdbc.query(sql,new Object[]{cifNif},new AlbaranEntradaRowMapper());
		return lista;
	}
	
	public List<AlbaranEntrada> listar(Date fecha){ //Devuelve todos los albaranes por fecha
		List<AlbaranEntrada> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_entrada where fecha=?";
		java.sql.Date d=new java.sql.Date(fecha.getTime());
		lista=jdbc.query(sql,new Object[]{d},new AlbaranEntradaRowMapper());
		return lista;
	}
	
	public List<AlbaranEntrada> listarPendientes(int cifNif){ 
		//Devuelve todos los albaranes no facturados de un cifNif
		
		List<AlbaranEntrada> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_entrada where cif_nif=? and n_factura=0";
		lista=jdbc.query(sql,new Object[]{cifNif},new AlbaranEntradaRowMapper());
		return lista;
	}
	
	/**
	 * Borra un albaran, hay que controlar que sea un albarán que nFactura=0
	 * @param nAlbaran
	 * @return
	 */
	public boolean delete(int nAlbaran){ 
		
		boolean r=false;
		
		String sql="delete from albaranes_entrada where n_albaran=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		int n=jdbc.update(sql,new Object[]{nAlbaran});
		r=n>0;
		
		return r;
	}
	
	
	
}
