package ie.cit.soft8020.Assignment2.entities;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
@Component
public class ShoppingCart {

	
	private ArrayList<Flower> flowers; 
	private ArrayList<Package> packages;
	private double totalCost;
	
	
	
	public ShoppingCart() {
		flowers = new ArrayList<Flower>();
		packages = new ArrayList<Package>();
		totalCost=0;
	}
	
	private void increaseCost(double amount)
	{
		totalCost +=amount;
	}
	private void decreaseCost(double amount)
	{
		totalCost-=amount;
	}
	public void add(Package p)
	{
		increaseCost(p.getPrice());
		this.packages.add(p);
	}
	public void removePackage(Package p)
	{
		decreaseCost(p.getPrice());
		this.packages.remove(p);
	}
	public void add(Flower f)
	{
		increaseCost((f.getPrice()*f.getQuantity()));
		this.flowers.add(f);
	}
	public void removeFlower(Flower f)
	{
		decreaseCost((f.getPrice()*f.getQuantity()));
		this.flowers.remove(f);
	}
	
	
	
	
	public ArrayList<Flower> getFlowers() {
		return flowers;
	}
	public void setFlowers(ArrayList<Flower> flowers) {
		this.flowers = flowers;
	}
	public ArrayList<Package> getPackages() {
		return packages;
	}
	public void setPackages(ArrayList<Package> packages) {
		this.packages = packages;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
}
