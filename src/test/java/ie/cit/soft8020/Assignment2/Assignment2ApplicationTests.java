package ie.cit.soft8020.Assignment2;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ie.cit.soft8020.Assignment2.entities.AddOn;
import ie.cit.soft8020.Assignment2.repositories.AddOnRepo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Assignment2ApplicationTests {

	@Autowired
	AddOnRepo addonDAO;

	List<AddOn> preExistingData;

	//Before and after will trigger before and after each test. Ideally they should trigger once 
	//but beforeClass and afterClass need to be static methods which causes problems
	@Before
	public void before()
	{

		System.out.println("Junit set up");
		preExistingData = addonDAO.findAll();
		addonDAO.deleteAll();
	

	}
	@After
	public void after()
	{
		System.out.println("Junit tear down");
		addonDAO.deleteAll();
		addonDAO.insert(preExistingData);
	}

	@Test
	public void testAddonInsert() {
		//Test insert
		AddOn expected = new AddOn("1a","TEST ADDON",100000);
		AddOn actual = addonDAO.insert(expected);
		assertEquals(expected.getName(),actual.getName());
		assertTrue(expected.getCost() == actual.getCost());
		assertEquals(expected.getId(),actual.getId());
	}	
	@Test
	public void testAddonFindOne()
	{
		//Test find one 
		AddOn expected = new AddOn("1a","TEST ADDON",100000);
		addonDAO.insert(expected);
		AddOn actual = addonDAO.findOne(expected.getId());
		assertEquals(expected.getName(),actual.getName());
		assertTrue(expected.getCost() == actual.getCost());
		assertEquals(expected.getId(),expected.getId());
	}
	@Test
	public void testAddonFindByName()
	{
		//Test find by name
		AddOn expected = new AddOn("1a","TEST ADDON",100000);
		addonDAO.insert(expected);
		AddOn actual = addonDAO.findByName("TEST ADDON");
		assertEquals(expected.getName(),actual.getName());
		assertTrue(expected.getCost() == actual.getCost());
		assertEquals(expected.getId(),expected.getId());
	}	
	@Test
	public void testAddonCount()
	{
		//Test count
		AddOn expected = new AddOn("1a","TEST ADDON",100000);
		addonDAO.insert(expected);
		long expectedCount = 1; 
		long actualCount = addonDAO.count();
		assertEquals(expectedCount , actualCount);
	}
	@Test
	public void testAddonDelete()
	{
		//Test delete
		AddOn expected = new AddOn("1a","TEST ADDON",100000);
		addonDAO.insert(expected);
		addonDAO.delete(expected);
		long expectedCount=0;
		long actualCount = addonDAO.count();
		assertEquals(expectedCount , actualCount);
		
	}


}
