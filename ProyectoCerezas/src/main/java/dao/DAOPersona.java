package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import modelos.Persona;

public class DAOPersona {
	
	class PersonaRowMapper implements RowMapper<Persona>{
		
		public Persona mapRow(ResultSet rs,int numRow) throws SQLException{
			
			Persona p=new Persona(
				rs.getInt("idPersona"),
				rs.getString("cifNif"),
				rs.getString("nombreRazonSocial"),
				rs.getString("apellidos"),
				rs.getString("direccion"),
				rs.getString("telefono"),
				rs.getString("email"));
			return p;
		}
	}
	
	private DataSource dataSource;

	public DAOPersona(){
		System.out.println("DAOPErsonaIniciandose");
	}

	public DataSource getDataSource(){
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int create(Persona p){ //Devuelve el ultimo id_persona de la tabla personas
		int idPersona=-1;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
				
		//añadir persona

		String sql="insert into personas (cif_nif,nombre_razon_social,apellidos,direccion,telefono,email)"
				+ "values (?,?,?,?,?,?)";
		try{
			jdbc.update(
				sql,
				new Object[]{p.getCifNif(),p.getNombreRazonSocial(),p.getApellidos(),p.getDireccion(),p.getTelefono(),p.getEmail()});
			
		//Recupero el idPersona de la ultima persona añadida para luego crear un cliente o un agricultor

			idPersona=jdbc.queryForObject("SELECT MAX(id_persona) AS id FROM personas", Integer.class );
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}		
		return idPersona;		
	}
	
	public Persona read(String cifNif){ //Busca Persona por su nie
		Persona p=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select idPersona from personas where cif_nif=?";
		try{
			p=jdbc.queryForObject(sql,new Object[]{cifNif},new PersonaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return p;
	}
	
}
