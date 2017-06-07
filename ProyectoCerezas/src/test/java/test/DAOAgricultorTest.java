package test;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.DAOAgricultor;
import junit.framework.TestCase;
import modelos.Agricultor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Spring-Beans.xml"})
public class DAOAgricultorTest extends TestCase {

	@Autowired
	DAOAgricultor dao;
	
	
	
	
	@Test
	public void testCreate(){
		
		Agricultor a=new Agricultor(5, "B45263965", "Cerezas S.A.", null, "toledo", "689526341", "cerezas@gmail.com", -1, false);
		
		
		Properties p=System.getProperties();
		System.out.println(p.getProperty("java.class.path"));
		dao.create(a);
		
		Agricultor u=dao.read(a.getIdPersona());
		
		assertEquals(a.getCifNif(),u.getCifNif());
		assertEquals(a.getNombreRazonSocial(),u.getNombreRazonSocial());
		assertEquals(a.getApellidos(),u.getApellidos());
		assertEquals(a.getDireccion(),u.getDireccion());
		assertEquals(a.getTelefono(), u.getTelefono());
		assertEquals(a.getEmail(), u.getEmail());
		assertEquals(a.getnSocio(),u.getnSocio());
		assertEquals(a.isBaja(),u.isBaja());
		
		dao.delete(a.getnSocio());
		 
	}
	
	@Test
	public void testUpdate(){
		
		Agricultor a=new Agricultor(6, "B45264589", "Peras S.A.", null, "toledo", "689526341", "cerezas@gmail.com", -1, false);
		dao.create(a);
		
		//System.out.println("El numero de socio ahora es: " + a.getnSocio());
		
		Agricultor u=new Agricultor(a.getIdPersona(), "B45264589", "Perico s.a", null, "Madrid", "689516341", "cerezas@gmail.com", a.getnSocio(), false);
		dao.update(u);
		
		Agricultor v=dao.read(u.getIdPersona());
		
		assertEquals(v.getCifNif(),u.getCifNif());
		assertEquals(v.getNombreRazonSocial(),u.getNombreRazonSocial());
		assertEquals(v.getApellidos(),u.getApellidos());
		assertEquals(v.getDireccion(),u.getDireccion());
		assertEquals(v.getTelefono(), u.getTelefono());
		assertEquals(v.getEmail(), u.getEmail());
		assertEquals(v.getnSocio(),u.getnSocio());
		assertEquals(v.isBaja(),u.isBaja());
		
		dao.update(a);
		dao.delete(a.getnSocio());
	} 

	@Test
	public void testRead(){
		
		Agricultor a=new Agricultor(5, "B45263965", "Cerezas S.A.", null, "toledo", "689526341", "cerezas@gmail.com", -1, false);
		dao.create(a);
		Agricultor b=new Agricultor(6, "B45264589", "Peras S.A.", null, "toledo", "689526341", "cerezas@gmail.com", -1, false);
		dao.create(b);
		
		List<Agricultor> lista = dao.listar("689526341");
		assertTrue(lista.size()>0);
		//System.out.println("La lista tiene " + lista.size() + " elementos.");
		
		dao.delete(a.getnSocio());
		dao.delete(b.getnSocio());
	}
	
	@Test
	public void testListar(){
		
		Agricultor a=new Agricultor(5, "B45263965", "Cerezas S.A.", null, "toledo", "689526341", "cerezas@gmail.com", -1, false);
		dao.create(a);
		Agricultor b=new Agricultor(6, "B45264589", "Peras S.A.", null, "toledo", "689526341", "cerezas@gmail.com", -1, false);
		dao.create(b);
		
		b.setBaja(true);
		
		dao.baja(b);
		
		List<Agricultor> lista = dao.listar();
		assertTrue(lista.size()>0);
		//System.out.println("La lista tiene " + lista.size() + " elementos.");
		
		dao.delete(a.getnSocio());
		dao.delete(b.getnSocio());
	}
	
	
	
}
