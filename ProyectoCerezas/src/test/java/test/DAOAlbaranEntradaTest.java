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

import dao.DAOAgricultor;
import dao.DAOAlbaranEntrada;
import dao.DAOFacturaEntrada;
import dao.DAOPersona;
import junit.framework.TestCase;
import modelos.Agricultor;
import modelos.AlbaranEntrada;
import modelos.FacturaEntrada;
import modelos.Persona;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Spring-Beans.xml"})
public class DAOAlbaranEntradaTest extends TestCase{
	
	@Autowired
	DAOAlbaranEntrada dao;
	
	@Autowired
	DAOAgricultor daoa;
	
	@Autowired
	DAOPersona daop;
	
	@Autowired
	DAOFacturaEntrada daof;
	
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
		
		Persona per=new Persona(-1, "B45263965", "Cerezas S.A.", null, "toledo", "689526341", "cerezas@gmail.com");
		daop.create(per);
		
		Agricultor agri=new Agricultor(per.getIdPersona(), per.getCifNif(), per.getNombreRazonSocial(), per.getApellidos(), per.getDireccion(), per.getTelefono(), per.getEmail(), -1, false);
		daoa.create(agri);
				
		AlbaranEntrada a=new AlbaranEntrada(-1,agri.getnSocio(),h,0);
		dao.create(a);
		
		AlbaranEntrada u=dao.read(a.getnAlbaran());
		
		assertEquals(a.getnAlbaran(),u.getnAlbaran());
		assertEquals(a.getnSocio(),u.getnSocio());
		assertEquals(a.getFecha(),u.getFecha());
		assertEquals(a.getnFactura(),u.getnFactura());
		
		dao.delete(a.getnAlbaran());
		dao.delete(agri.getnSocio());
		dao.delete(per.getIdPersona());
		
	}
	
	@Test
	public void testUpdate(){
		
		//Creo un agricultor
		Persona per=new Persona(-1, "B45263965", "Cerezas S.A.", null, "toledo", "689526341", "cerezas@gmail.com");
		daop.create(per);
		
		Agricultor agri=new Agricultor(per.getIdPersona(), per.getCifNif(), per.getNombreRazonSocial(), per.getApellidos(), per.getDireccion(), per.getTelefono(), per.getEmail(), -1, false);
		daoa.create(agri);
		
		//Creo otro agricultor
		Persona per2=new Persona(-1, "B45859268", "Peras S.A.", null, "Madrid", "689526341", "peras@gmail.com");
		daop.create(per2);
		
		Agricultor agri2=new Agricultor(per2.getIdPersona(), per2.getCifNif(), per2.getNombreRazonSocial(), per2.getApellidos(), per2.getDireccion(), per2.getTelefono(), per2.getEmail(), -1, false);
		daoa.create(agri2);
		
		//Creo un albaran asociado al agricultor1
		AlbaranEntrada s=new AlbaranEntrada(-1,agri.getnSocio(),h,0);
		dao.create(s);
		
		//Tomo una fecha diferente y al agricultor 2
		Date d=new Date(h.getTime()-86400000);
		
		AlbaranEntrada u=new AlbaranEntrada(s.getnAlbaran(),agri2.getnSocio(),d,s.getnFactura());
		dao.update(u);
		
		AlbaranEntrada a=dao.read(s.getnAlbaran());
		
		assertEquals(a.getnAlbaran(),u.getnAlbaran());
		assertEquals(a.getnSocio(),u.getnSocio());
		assertEquals(a.getFecha(),u.getFecha());
		assertEquals(a.getnFactura(),u.getnFactura());
		
		dao.delete(s.getnAlbaran());
		dao.delete(agri.getnSocio());
		dao.delete(per.getIdPersona());
	}
	
	@Test
	public void testFacturar(){
		
		//Creo un Agricultor
		Persona per=new Persona(-1, "B45263965", "Cerezas S.A.", null, "toledo", "689526341", "cerezas@gmail.com");
		daop.create(per);
		
		Agricultor agri=new Agricultor(per.getIdPersona(), per.getCifNif(), per.getNombreRazonSocial(), per.getApellidos(), per.getDireccion(), per.getTelefono(), per.getEmail(), -1, false);
		daoa.create(agri);
		
		//Creo un Albaran
		AlbaranEntrada a=new AlbaranEntrada(-1,agri.getnSocio(),h,0);
		dao.create(a);
		
		//Creamos una factura -- Falta meter datos
		FacturaEntrada fac=new FacturaEntrada();
		daof.create(fac);
		
		//Modificamos el número factura en nuestro albaran
		AlbaranEntrada s=new AlbaranEntrada(a.getnAlbaran(),a.getnSocio(),a.getFecha(),fac.getnFactura());
		
		dao.facturar(a.getnAlbaran(), s.getnFactura());
		a=dao.read(a.getnAlbaran());
		
		assertEquals(a.getnAlbaran(),s.getnAlbaran());
		assertEquals(a.getnSocio(),s.getnSocio());
		assertEquals(a.getFecha(),s.getFecha());
		assertEquals(a.getnFactura(),s.getnFactura());
		
		dao.delete(s.getnAlbaran());
		
		Date d=new Date(h.getTime()-86400000);
		AlbaranEntrada u=new AlbaranEntrada(-1,2,d,0);
		dao.create(u);
		
		List<AlbaranEntrada> lista = dao.listarPendientes("B45263965");
		assertTrue(lista.size()>0);
		System.out.println("La lista tiene " + lista.size() + " elementos.");
		
		dao.delete(a.getnAlbaran());
		dao.delete(u.getnAlbaran());
	}
	
	@Test
	public void testListar(){
		
		AlbaranEntrada a=new AlbaranEntrada(-1,2,h,0);
		dao.create(a);
		
		Date d=new Date(h.getTime()-86400000);
		AlbaranEntrada u=new AlbaranEntrada(-1,2,d,0);
		dao.create(u);
		
		List<AlbaranEntrada> lista = dao.listar();
		assertTrue(lista.size()>0);
		System.out.println("La lista tiene " + lista.size() + " elementos.");
		
		lista = dao.listar("B45263965");
		assertTrue(lista.size()>0);
		System.out.println("La lista tiene " + lista.size() + " elementos.");
		
		lista = dao.listar("h,d");
		assertTrue(lista.size()>0);
		System.out.println("La lista tiene " + lista.size() + " elementos.");
		
		dao.delete(a.getnAlbaran());
		dao.delete(u.getnAlbaran());
		
	}
	

}
