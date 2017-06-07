package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.DAOAlbaranEntrada;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Spring-Beans.xml"})
public class DAOAlbaranEntradaTest extends TestCase{
	
	@Autowired
	DAOAlbaranEntrada dao;
	
	@Test
	public void testCreate(){
		
	}

}
