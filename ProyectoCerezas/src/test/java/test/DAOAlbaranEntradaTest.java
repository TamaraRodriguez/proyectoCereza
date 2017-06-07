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

import dao.DAOAlbaranEntrada;
import junit.framework.TestCase;
import modelos.AlbaranEntrada;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Spring-Beans.xml"})
public class DAOAlbaranEntradaTest extends TestCase{
	
	@Autowired
	DAOAlbaranEntrada dao;
	
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
		
		AlbaranEntrada a=new AlbaranEntrada(-1,2,h,0);
		dao.create(a);
		
		AlbaranEntrada u=dao.read(a.getnAlbaran());
		
		assertEquals(a.getnAlbaran(),u.getnAlbaran());
		assertEquals(a.getnSocio(),u.getnSocio());
		assertEquals(a.getFecha(),u.getFecha());
		assertEquals(a.getnFactura(),u.getnFactura());
		
		dao.delete(a.getnAlbaran());
		
		
	}
	
	@Test
	public void testUpdate(){
		
		AlbaranEntrada s=new AlbaranEntrada(-1,2,h,0);
		dao.create(s);
		Date d=new Date(h.getTime()-86400000);
		
		AlbaranEntrada u=new AlbaranEntrada(s.getnAlbaran(),2,d,0);
		dao.update(u);
		
		AlbaranEntrada a=dao.read(s.getnAlbaran());
		
		assertEquals(a.getnAlbaran(),u.getnAlbaran());
		assertEquals(a.getnSocio(),u.getnSocio());
		assertEquals(a.getFecha(),u.getFecha());
		assertEquals(a.getnFactura(),u.getnFactura());
		
		dao.delete(s.getnAlbaran());
	}
	
	@Test
	public void testFacturar(){
		
		AlbaranEntrada a=new AlbaranEntrada(-1,2,h,0);
		dao.create(a);
	
		AlbaranEntrada s=new AlbaranEntrada(a.getnAlbaran(),a.getnSocio(),a.getFecha(),0);
		
		dao.facturar(a.getnAlbaran(), s.getnFactura());
		
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
