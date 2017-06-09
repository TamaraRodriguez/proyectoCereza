package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.DAOAlbaranSalida;
import dao.DAOCliente;
import dao.DAOFacturaSalida;
import dao.DAOLineaAlbaranSalida;
import dao.DAOPersona;
import dao.DAOVariedades;
import junit.framework.TestCase;
import modelos.AlbaranSalida;
import modelos.Cliente;
import modelos.FacturaSalida;
import modelos.LineaAlbaranSalida;
import modelos.Persona;
import modelos.Variedades;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Spring-Beans.xml"})
public class DAOFacturaSalidaTest extends TestCase{
	@Autowired
	DAOLineaAlbaranSalida daola;
	
	@Autowired
	DAOAlbaranSalida daoal;
	
	@Autowired
	DAOCliente daoc;
	
	@Autowired
	DAOPersona daop;
	
	@Autowired
	DAOVariedades daov;
	
	@Autowired
	DAOFacturaSalida daof;
	
	private static Date h;
	
	@BeforeClass
	public static void preparar(){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		h = new Date();
		
		try{
			h = formatter.parse(formatter.format(h));
		}
		catch(ParseException tpe){
			fail();
		}
	}
	
	@Test
	public void testCreate(){
		
		//Creo un Cliente
		Persona per=new Persona(-1, "B45263965", "Cerezas S.A.", null, "toledo", "689526341", "cerezas@gmail.com");
		daop.create(per);
				
		Cliente agri=new Cliente(per.getIdPersona(), per.getCifNif(), per.getNombreRazonSocial(), per.getApellidos(), per.getDireccion(), per.getTelefono(), per.getEmail(), -1, false);
		daoc.create(agri);
				
		//Creo un albaran
		AlbaranSalida a=new AlbaranSalida(-1,agri.getnCliente(),h,0);
		daoal.create(a);
				
		//Creo una varidad
		Variedades v=new Variedades("Tipo 1", 2.8, 2.5, 4.88);
		daov.create(v);
				
		//Creo una linea de albaran
		LineaAlbaranSalida las = new LineaAlbaranSalida(a.getnAlbaran(),-1,v.getTipo(),5,v.getPesoCaja(),v.getPrecioCaja());
		daola.create(las);
				
		//Calculo el importe neto de la linea
		double precio=daoal.calcularPrecio(a.getnAlbaran());
		System.out.println("El precio neto de este albaran es " + precio);
		
		//Creo una factura
		FacturaSalida fac=new FacturaSalida(-1,h,21,precio,false);
		daof.create(fac);
		
		FacturaSalida x=daof.read(fac.getnFactura());
		
		assertEquals(fac.getnFactura(),x.getnFactura());
		assertEquals(fac.getFecha(),x.getFecha());
		assertEquals(fac.getIva(),x.getIva());
		assertEquals(fac.getPrecioNeto(),fac.getPrecioNeto());
		assertEquals(fac.isAnulacion(),x.isAnulacion());
		
		daola.delete(las.getIdLinea());
		daoal.delete(a.getnAlbaran());
		daoc.delete(agri.getnCliente());
		daop.delete(per.getIdPersona());
		daov.delete(v.getTipo());
	}
}
