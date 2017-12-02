package ie.cit.soft8020.Assignment2.entities;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
@Component
public class ShoppingCart {

	
	
	private ArrayList<Package> packages;
	private double totalCost;
	
	
	
	public ShoppingCart() {
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
	public boolean isEmpty()
	{
		return this.packages.isEmpty();
	}
	public void empty()
	{
		packages.clear();
		totalCost=0;
	}
	
}
