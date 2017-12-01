package ie.cit.soft8020.Assignment2.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ie.cit.soft8020.Assignment2.entities.Flower;
import ie.cit.soft8020.Assignment2.entities.Order;
import ie.cit.soft8020.Assignment2.entities.ShoppingCart;
import ie.cit.soft8020.Assignment2.repositories.PackageRepo;
import ie.cit.soft8020.Assignment2.entities.Package;

@Service
public class Worker {
	
	@Autowired
	PackageRepo preSetDAO;
	@Autowired
	ShoppingCart cart;
	
	
	//Make these more spring like ie autowired or value injection
	private RestTemplate restTemplate;
	private String backendURI;
	private final int FLOREST_ID=3; 
	@Autowired
	private ShoppingCart shoppingCart;
	
	public Worker()
	{
		this.restTemplate=new RestTemplate();
		this.backendURI = "http://localhost:8001";
	}
	public List<Package> presets()
	{
		return preSetDAO.findAll();
	}
	public Order[] myOrders()
	{
		return restTemplate.getForObject(backendURI+"/myOrders?id="+FLOREST_ID, Order[].class);	
	}
	public Flower[] getFlowers()
	{
		Flower[] f =restTemplate.getForObject(backendURI+"/allFlowers", Flower[].class);
		return f;
	}
	public void addToShoppingCart(Package p)
	{
		shoppingCart.add(p);
	}
	public void addToShoppingCart(Flower f)
	{
		shoppingCart.add(f);
	}
	public void deleteOrder(String id)
	{
		restTemplate.delete(backendURI+"/deleteOrder/"+id);
	}
	
}
