package ie.cit.soft8020.Assignment2.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private RestTemplate restTemplate;
	private String backendURI;
	private final int FLOREST_ID=3; 
	private final String FLOREST_NAME="Phillie's flowers";
	@Autowired
	private ShoppingCart shoppingCart;
	
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
	public Flower getFlowerDetails(String id)
	{
		return restTemplate.getForObject(backendURI+"/flowerDetails?id="+id, Flower.class);
	}
	public List<CustomerOrder> getCustomerOrders()
	{
		return orderDAO.findAll();
	}
	public void removeFromCart(String id)
	{
		cart.removePackage(id);
	}
	public void addCustomPackageToCart(Package p )
	{
		
		String flowerId = p.getFlower().getId();
		String addonId = p.getAddon().getId();
		
		Flower f = getFlowerDetails(flowerId);
		AddOn a = addonDAO.findOne(addonId);
		
		p.getFlower().setName(f.getName());
		p.getFlower().setPrice(f.getPrice());
		
		p.getAddon().setName(a.getName());
		p.getAddon().setCost(a.getCost());
		
		p.setPrice((p.getAddon().getCost()) + (p.getFlower().getQuantity()*p.getFlower().getPrice()));
		
		String uniqueID = UUID.randomUUID().toString();
		p.setId(uniqueID);
		if(p.getName().equalsIgnoreCase(""))
			p.setName("Custom package");
		addToShoppingCart(p);
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
