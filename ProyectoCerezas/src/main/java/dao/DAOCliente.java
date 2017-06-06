package dao;

import java.util.List;

import javax.sql.DataSource;

import modelos.Agricultor;
import modelos.Cliente;

public interface DAOCliente {
	/*Base de Datos*/
	public DataSource getDataSource();
	public void setDataSource(DataSource dataSource);
	/*CRUD*/
	public boolean create(final Cliente c);
	public Cliente read(int idPersona);
	public List<Cliente> read(String busqueda);
	public boolean update(Cliente c);
	public List<Cliente> listar();
	public boolean baja (Cliente c);
	public boolean delete (int nCliente); //Solo se usa para los test
}
