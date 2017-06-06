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
	public Agricultor read(int idPersona);
	public List<Agricultor> read(String busqueda);
	public boolean update(Agricultor c);
	public List<Agricultor> listar();
	public boolean baja (Agricultor c);
	public boolean delete(int nSocio); //Solo se usa para los test
}
