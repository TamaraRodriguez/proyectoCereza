package dao;

import java.util.Date;
import java.util.List;

import modelos.FacturaEntrada;

public interface DAOFacturaEntrada {
	public boolean create(FacturaEntrada fe);
	public FacturaEntrada read(int nFactura);
	public boolean update(FacturaEntrada fe);
	public List<FacturaEntrada> listar();
	public List<FacturaEntrada> listar(String cifNif);
	//public List<FacturaEntrada> listar(Date fecha);
	public List<FacturaEntrada> buscarFecha (Date fechaInicio, Date fechaFinal);
	public boolean anularFactura(FacturaEntrada fe);
	public boolean delete(int nFactura); //Se usa para los JUnit
	
}
