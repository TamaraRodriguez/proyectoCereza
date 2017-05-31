package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import modelos.Agricultor;

public class DAOAgricultor{
class AgricultorRowMapper implements RowMapper<Agricultor>{
		
		public Agricultor mapRow(ResultSet rs,int numRow) throws SQLException{
			
			Agricultor c=new Agricultor(
				rs.getInt("idPersona"),
				rs.getString("cifNif"),
				rs.getString("nombreRazonSocial"),
				rs.getString("apellidos"),
				rs.getString("direccion"),
				rs.getString("telefono"),
				rs.getString("email"),
				rs.getInt("nSocio"));
			return c;
		}
	}

	private DataSource dataSource;

	public DAOAgricultor(){
		System.out.println("DAOAgricultorIniciandose");
	}

	public DataSource getDataSource(){
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean create(int idPersona){
		
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		//añadir el idPersona y crear un agricultor nuevo

		jdbc.update("insert into agricultores (id_persona) values (?)",new Object[]{idPersona});
		
		return true;		
	}
	
	public Agricultor read(int idPersona){ //Busca agricultores por su id
		Agricultor c=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select personas.*, agricultores.n_socio from personas join agricultores ON (agricultores.id_persona=personas.id_persona) where personas.id_persona=?";
		try{
			c=jdbc.queryForObject(sql,new Object[]{idPersona},new AgricultorRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return c;
	}

	public List<Agricultor> read(String busqueda){  //Busca agricultores por nombre, apellido NIE o nº telefono

		List<Agricultor> lista=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select personas.*, agricultores.n_socio from personas join agricultores on (personas.id_persona=agricultores.id_persona) where cif_nif like ? or nombre_razon_social like ? or apellido like ? or telefono like ?";
		try{
			lista=jdbc.query(sql,new Object[]{"%"+busqueda+"%"},new AgricultorRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
				
		return lista;
	}
	
	public boolean update(Agricultor c){
		boolean r=false;
		
		String sql="update personas set "
					+ "cif_nif=?,"
					+ "nombre_razon_social=?,"
					+ "apellidos=?,"
					+ "direccion=?, "
					+ "telefono=?,"
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
		}
		
		return r;
	}
	

	public List<Agricultor> listar(){ 
		
		List<Agricultor> lista;
				
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select personas.*, agricultores.n_socio from personas join agricultores on (personas.id_persona=agricultores.id_persona) order by persona.nombre_razon_social";
		lista=jdbc.query(sql,new AgricultorRowMapper());

		return lista;
	}

	//No hay metodo borrar porque si se borra una persona se borrarian todas las facturas y albaranes
	
	
}
