package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.mysql.jdbc.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;

import modelos.FacturaEntrada;

public class DAOFacturaEntradaImpl implements DAOFacturaEntrada {
	
class FacturaEntradaRowMapper implements RowMapper<FacturaEntrada>{
		
	public FacturaEntrada mapRow(ResultSet rs,int numRow) throws SQLException{
		FacturaEntrada fe=new FacturaEntrada(
				rs.getString("nFactura"),
				new java.util.Date(rs.getDate("fecha").getTime()),
				rs.getDouble("precioBruto"),
				rs.getInt("iva"),
				rs.getDouble("precioNeto"));
			
			return fe;
		}
		
	}
	
	private DataSource dataSource;
		
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean create(FacturaEntrada fe) {
		
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		
		final String sql="insert into numeros_facturas (numero) values (default)";
		
		GeneratedKeyHolder kh=new GeneratedKeyHolder();
		int n=jdbc.update(new PreparedStatementCreator()){
			
			public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException{
				PreparedStatement statement=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				return statement;
			}
		},kh
		);
		
		fe.setnFactura(kh.getKey().intValue());
		return true;
	}

	public FacturaEntrada read(String nFactura) {
		// TODO Auto-generated method stub
		return null;
	}

	public String ultimaFactura() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(FacturaEntrada fe) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<FacturaEntrada> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FacturaEntrada> listar(int cifNif) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FacturaEntrada> listar(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(String nFactura) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
