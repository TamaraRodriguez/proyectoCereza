package dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import modelos.AlbaranSalida;

public interface DAOAlbaranSalida {
	/*DataSource*/
	public DataSource getDataSource();
	public void setDataSource(DataSource dataSource);
	/*CRUD*/
	public int create(final AlbaranSalida a);
	public AlbaranSalida read(int nAlbaran);
	public boolean update(AlbaranSalida as);
	public boolean facturar(int nAlbaran, int nFactura);
	public List<AlbaranSalida> listar();
	public List<AlbaranSalida> listar(int cifNif);
	public List<AlbaranSalida> listar(Date fecha);
	public List<AlbaranSalida> listarPendientes(int cifNif);
	public boolean delete(int nAlbaran);
}
