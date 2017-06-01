package dao;

import java.util.Date;
import java.util.List;

import modelos.FacturaEntrada;

public interface DAOFacturaEntrada {
	public boolean create(FacturaEntrada fe);
	public FacturaEntrada read(String nFactura);
	public String ultimaFactura(); //Metodo que devulve la ultima factura insertada
	public boolean update(FacturaEntrada fe);
	public List<FacturaEntrada> listar();
	public List<FacturaEntrada> listar(int cifNif);
	public List<FacturaEntrada> listar(Date fecha);
	public boolean delete(String nFactura);
	
}
