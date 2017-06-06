package dao;

import java.util.List;

import javax.sql.DataSource;

import modelos.Agricultor;

public interface DAOAgricultor {
	/*Base de datos*/
	public DataSource getDataSource();
	public void setDataSource(DataSource dataSource);
	/*CRUD*/
	public boolean create(final Agricultor a);
	public Agricultor read(int n_socio);
	public List<Agricultor> read(String busqueda);
	public boolean update(Agricultor c);
	public List<Agricultor> listar();
	public boolean delete (int n_socio);
	
}
