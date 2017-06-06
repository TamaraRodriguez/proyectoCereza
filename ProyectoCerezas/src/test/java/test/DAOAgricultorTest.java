package test;

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
		
		Agricultor a=new Agricultor(-1, "B45263965", "Cerezas S.A.", null, "toledo", "689526341", "cerezas@gmail.com", -1, false);
		
		Properties p=System.getProperties();
		System.out.println(p.getProperty("java.class.path"));
		dao.create(a);
		
		Agricultor u=dao.read(a.getnSocio());
		
		assertEquals(a.getCifNif(),u.getCifNif());
		assertEquals(a.getNombreRazonSocial(),u.getNombreRazonSocial());
		assertEquals(a.getApellidos(),u.getApellidos());
		assertEquals(a.getDireccion(),u.getDireccion());
		assertEquals(a.getTelefono(), u.getTelefono());
		assertEquals(a.getEmail(), u.getEmail());
		assertEquals(a.getnSocio(),u.getnSocio());
		assertEquals(a.isBaja(),u.isBaja());
		
		 
	}
	
}
