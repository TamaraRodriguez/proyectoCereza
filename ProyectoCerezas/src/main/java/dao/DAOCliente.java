package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import modelos.Cliente;

public class DAOCliente{
	
	class ClienteRowMapper implements RowMapper<Cliente>{
		
		public Cliente mapRow(ResultSet rs,int numRow) throws SQLException{
			
			Cliente c=new Cliente(
				rs.getInt("idPersona"),
				rs.getString("cifNif"),
				rs.getString("nombreRazonSocial"),
				rs.getString("apellidos"),
				rs.getString("direccion"),
				rs.getString("telefono"),
				rs.getString("email"),
				rs.getInt("nCliente"));
			return c;
		}
	}

	private DataSource dataSource;

	public DAOCliente(){
		System.out.println("DAOClienteIniciandose");
	}

	public DataSource getDataSource(){
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean create(int idPersona){
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		//añadir el idPersona y crear un cliente nuevo

		jdbc.update("insert into clientes (id_persona) values (?)",new Object[]{idPersona});

		return true;		
	}
	
	public Cliente read(int idPersona){ //Busca clientes por su id
		Cliente c=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select personas.*, clientes.n_cliente from personas join clientes ON (clientes.id_persona=personas.id_persona) where personas.id_persona=?";
		try{
			c=jdbc.queryForObject(sql,new Object[]{idPersona},new ClienteRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
		
		return c;
	}

	public List<Cliente> read(String busqueda){  //Busca clientes por nombre, apellido NIE o nº telefono

		List<Cliente> lista=null;
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		String sql="select personas.*, clientes.n_cliente from personas join clientes on (personas.id_persona=clientes.id_persona) where cif_nif like ? or nombre_razon_social like ? or apellido like ? or telefono like ?";
		try{
			lista=jdbc.query(sql,new Object[]{"%"+busqueda+"%"},new ClienteRowMapper());
		}
		catch(IncorrectResultSizeDataAccessException ics){
			
		}
		catch(DataAccessException dae){
			dae.printStackTrace();
		}
				
		return lista;
	}
	
	public boolean update(Cliente c){
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
	

	public List<Cliente> listar(){ 
		
		List<Cliente> lista;
				
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		String sql="select personas.*, clientes.n_cliente from personas join clientes on (personas.id_persona=clientes.id_persona) order by persona.nombre_razon_social";
		lista=jdbc.query(sql,new ClienteRowMapper());

		return lista;
	}

	//No hay metodo borrar porque si se borra una persona se borrarian todas las facturas y albaranes
	
	
}
