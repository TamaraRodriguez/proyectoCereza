package dao;

import java.util.Date;
import java.util.List;

import modelos.FacturaEntrada;

public interface DAOFacturaEntrada {
	public int create(FacturaEntrada fe);
	public FacturaEntrada read(int nFactura);
	public boolean update(FacturaEntrada fe);
	public List<FacturaEntrada> listar();
	public List<FacturaEntrada> listar(String cifNif);
	//public List<FacturaEntrada> listar(Date fecha);
	public List<FacturaEntrada> buscarFecha (Date fechaInicio, Date fechaFinal);
	/*public boolean delete(int nFactura);*/
	
}
