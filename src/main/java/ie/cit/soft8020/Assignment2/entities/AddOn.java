package ie.cit.soft8020.Assignment2.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AddOn {
	@Id 
	private String id;
	private String name;
	private double cost;
	
	
	
	
	public AddOn() {
	}
	public AddOn(String name, double cost) {
		this.name = name;
		this.cost = cost;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return name;
	}
	
	

}
