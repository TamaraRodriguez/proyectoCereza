package dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import modelos.AlbaranEntrada;

public interface DAOAlbaranEntrada {
	/*Base de datos*/
	public DataSource getDataSource();
	public void setDataSource(DataSource dataSource);
	/*CRUD*/
	public int create(final AlbaranEntrada a); /*Hay que ver cuál de los dos métodos crear es mejor.*/
	public AlbaranEntrada read(int nAlbaran);
	public boolean update(AlbaranEntrada ae);
	public boolean facturar(int nAlbaran, int nFactura);
	public List<AlbaranEntrada> listar();
	public List<AlbaranEntrada> listar(int cifNif);
	//public List<AlbaranEntrada> listar(Date fecha);
	public List<AlbaranEntrada> buscarFecha (Date fechaInicio, Date fechaFinal);
	public List<AlbaranEntrada> listarPendientes(int cifNif);
	public boolean delete(int nAlbaran);
}
