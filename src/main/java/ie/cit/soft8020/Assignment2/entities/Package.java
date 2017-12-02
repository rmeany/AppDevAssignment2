package ie.cit.soft8020.Assignment2.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Package {

	@Id
	private int id;
	private String name;
	private double price;
	private Flower flower;
	private AddOn addon;
	
	
	public Package() {
	}



	public Package(int id, String name, double price,Flower flower,AddOn addon) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.flower=flower;
		this.addon=addon;
		
	}
	
	
	
	public Flower getFlower() {
		return flower;
	}



	public void setFlower(Flower flower) {
		this.flower = flower;
	}



	public AddOn getAddon() {
		return addon;
	}



	public void setAddon(AddOn addon) {
		this.addon = addon;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
