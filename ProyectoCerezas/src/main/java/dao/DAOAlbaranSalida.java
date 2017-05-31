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

import modelos.AlbaranSalida;

public class DAOAlbaranSalida {
class AlbaranSalidaRowMapper implements RowMapper<AlbaranSalida>{
		
		public AlbaranSalida mapRow(ResultSet rs,int numRow) throws SQLException{
			AlbaranSalida as=new AlbaranSalida(
					rs.getInt("nAlbaran"),
					rs.getInt("nCliente"),
					new java.util.Date(rs.getDate("fecha").getTime()),
					rs.getString("nFactura"));
			
			return as;
		}
		
	}
	
	private DataSource dataSource;
		
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int create(AlbaranSalida as){
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
	}
	
	public AlbaranSalida read(int nAlbaran){
		AlbaranSalida as=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select * from albaranes_salida where n_albaran=?";
		try{
			as=jdbc.queryForObject(sql,new Object[]{nAlbaran},new AlbaranSalidaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return as;
	}
	
	public boolean update(AlbaranSalida as){ //Modifica un albaran
		boolean r=false;
		
		String sql="update albaranes_salida set "
					+ "n_socio=?,"
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
		}
		
		return r;
	}
	
	public boolean facturar(int nAlbaran, int nFactura){ 
		/*Modifica el nFactura para indicar así que el albarán ha sido facturado
		si queremos quitar un albaran de una factura pasar nFactura=0 y el albarán 
		volvería a quedar sin estar facturado*/
		
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
			dae.printStackTrace();
		}
		
		return r;
	}
	
	public List<AlbaranSalida> listar(){ //Devuelve una lista de todos los albaranes
		List<AlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_salida";
		lista=jdbc.query(sql,new AlbaranSalidaRowMapper());
		return lista;
	}
	
	public List<AlbaranSalida> listar(int cifNif){ //Devuelve todos los albaranes de un cifNif
		List<AlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_salida where cif_nif=?";
		lista=jdbc.query(sql,new Object[]{cifNif},new AlbaranSalidaRowMapper());
		return lista;
	}
	
	public List<AlbaranSalida> listar(Date fecha){ //Devuelve todos los albaranes por fecha
		List<AlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_salida where fecha=?";
		java.sql.Date d=new java.sql.Date(fecha.getTime());
		lista=jdbc.query(sql,new Object[]{d},new AlbaranSalidaRowMapper());
		return lista;
	}
	
	public List<AlbaranSalida> listarPendientes(int cifNif){ 
		//Devuelve todos los albaranes no facturados de un cifNif
		
		List<AlbaranSalida> lista;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select * from albaranes_salida where cif_nif=? and n_factura=0";
		lista=jdbc.query(sql,new Object[]{cifNif},new AlbaranSalidaRowMapper());
		return lista;
	}
	
	public boolean delete(int nAlbaran){ 
		//Borra un albaran, hay que controlar que sea un albarán que nFactura=0 
		
		boolean r=false;
		
		String sql="delete from albaranes_salida where n_albaran=?";
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		int n=jdbc.update(sql,new Object[]{nAlbaran});
		r=n>0;
		
		return r;
	}
	
}
