package ie.cit.soft8020.Assignment2.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import ie.cit.soft8020.Assignment2.entities.AddOn;
import ie.cit.soft8020.Assignment2.entities.CustomerOrder;
import ie.cit.soft8020.Assignment2.entities.Flower;
import ie.cit.soft8020.Assignment2.entities.Order;
import ie.cit.soft8020.Assignment2.entities.ShoppingCart;
import ie.cit.soft8020.Assignment2.repositories.AddOnRepo;
import ie.cit.soft8020.Assignment2.repositories.OrderRepo;
import ie.cit.soft8020.Assignment2.repositories.PackageRepo;
import ie.cit.soft8020.Assignment2.entities.Package;

@Service
public class Worker {
	
	@Autowired
	PackageRepo preSetDAO;
	@Autowired
	ShoppingCart cart;
	@Autowired
	OrderRepo orderDAO;
	@Autowired
	AddOnRepo addonDAO;
	
	//Make these more spring like ie autowired or value injection
	private RestTemplate restTemplate;
	private String backendURI;
	private final int FLOREST_ID=3; 
	private final String FLOREST_NAME="Phillie's flowers";
	@Autowired
	private ShoppingCart shoppingCart;
	
	//This works but can we put the url in a properties file?
	@Value("http://localhost:8001")
	private String test;
	
	public Worker()
	{
		this.restTemplate=new RestTemplate();
		this.backendURI = "http://localhost:8001";
	}
	public List<AddOn> getAddons()
	{
		return addonDAO.findAll();
	}
	public ShoppingCart getCart()
	{
		return this.cart;
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
	public void deleteOrder(String id)
	{
		restTemplate.delete(backendURI+"/deleteOrder/"+id);
	}
	@Transactional 
	public boolean makeOrder(CustomerOrder order)
	{
		order.setCost(cart.getTotalCost());
		order.setPackages(cart.getPackages());
		orderDAO.save(order);
		ArrayList<Flower> flowersToOrder = new ArrayList<Flower>();
		
		
		ArrayList<Flower> flowersInOrder= new ArrayList<Flower>();
		for(Package orderPackage:order.getPackages())
		{
			flowersInOrder.add(orderPackage.getFlower());		
		}
		
		
		Flower[] flowers = getFlowers();
		for (Flower flower:flowers)
		{
			flower.setQuantity(0);
			flowersToOrder.add(flower);
		}
		
		
		for(Flower flower : flowersToOrder)
		{
			for(Flower flower2 : flowersInOrder)
			{
				if(flower.getId().equalsIgnoreCase(flower2.getId()))
				{
					flower.setQuantity(flower.getQuantity()+flower2.getQuantity());
				}
			}
		}
		
		double totalCost=0;
		for(Flower flower : flowersToOrder)
		{
			totalCost+=(flower.getQuantity()*flower.getPrice());
		}
		
		
		Order florestOrder = new Order(FLOREST_ID,FLOREST_NAME,flowersToOrder,totalCost);
		
		boolean responce =restTemplate.postForObject(backendURI+"/makeOrder", florestOrder, Boolean.class);
		cart.empty();
		return responce;
	}
	
	
}
