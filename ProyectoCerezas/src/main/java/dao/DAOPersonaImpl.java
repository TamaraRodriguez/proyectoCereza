package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import modelos.Persona;

public class DAOPersonaImpl implements DAOPersona {
	
	class PersonaRowMapper implements RowMapper<Persona>{
		
		public Persona mapRow(ResultSet rs,int numRow) throws SQLException{
			
			Persona p=new Persona(
				rs.getInt("id_persona"),
				rs.getString("cif_nif"),
				rs.getString("nombre_razon_social"),
				rs.getString("apellidos"),
				rs.getString("direccion"),
				rs.getString("telefono"),
				rs.getString("email"));
			return p;
		}
	}
	
	private DataSource dataSource;

	public DataSource getDataSource(){
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean create(final Persona p){ //Devuelve el ultimo id_persona de la tabla personas
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		final String sql="insert into personas (cif_nif, nombre_razon_social, apellidos, direccion, telefono, email) values (?,?,?,?,?,?)";
		
		GeneratedKeyHolder kh=new GeneratedKeyHolder();
		int n = jdbc.update(new PreparedStatementCreator(){

			public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement =con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, p.getCifNif());
				statement.setString(2, p.getNombreRazonSocial());
				statement.setString(3, p.getApellidos());
				statement.setString(4, p.getDireccion());
				statement.setString(5, p.getTelefono());
				statement.setString(6, p.getEmail());
				
				return statement;
			}
				
		},kh
		);
		p.setIdPersona(kh.getKey().intValue());
		return n>0;		
	}
	
	public Persona read(String cifNif){ //Busca Persona por su nie
		Persona p=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select id_persona from personas where cif_nif=?";
		try{
			p=jdbc.queryForObject(sql,new Object[]{cifNif},new PersonaRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			System.out.println("Read Personas - Data access exception thrown when a result was not of the expected size, for example when expecting a single row but getting 0 or more than 1 rows.");
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
			System.out.println("Read Persona - Error acceso de datos");
			
		}
		
		return p;
	}
	
}
