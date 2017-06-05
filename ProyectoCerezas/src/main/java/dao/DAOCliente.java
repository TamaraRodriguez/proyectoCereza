package dao;

import java.util.List;

import javax.sql.DataSource;

import modelos.Cliente;

public interface DAOCliente {
	/*Base de Datos*/
	public DataSource getDataSource();
	public void setDataSource(DataSource dataSource);
	/*CRUD*/
	public boolean create(final Cliente c);
	public Cliente read(int nCliente);
	public List<Cliente> read(String busqueda);
	public boolean update(Cliente c);
	public List<Cliente> listar();
	public boolean delete (int nCliente);
}
