package ie.cit.soft8020.Assignment2.entities;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CustomerOrder {
	@Id
	private String id;
	private double cost;
	private ArrayList<Package> packages;
	
	@NotNull
	@Size(min=1)
	private String fName;
	@NotNull
	@Size(min=1)
	private String lName;
	@NotNull
	@Size(min=5)
	private String address;
	@NotNull
	@CreditCardNumber
	private String creditCardNum;
	@NotNull
	@Size(min=10,max=10)
	private String phoneNumber;
	
	
	public CustomerOrder() {
	}

	
	
	public CustomerOrder(String fName, String lName,String address, String creditCardNum, String phoneNumber) {
		this.fName=fName;
		this.lName=lName;
		this.address = address;
		this.creditCardNum = creditCardNum;
		this.phoneNumber = phoneNumber;
	}



	public CustomerOrder(double cost, ArrayList<Package> packages,String fName, String lName, String address, 
			String creditCardNum,String phoneNumber) {
		this.cost = cost;
		this.packages = packages;
		this.fName=fName;
		this.lName=lName;
		this.address = address;
		this.creditCardNum = creditCardNum;
		this.phoneNumber = phoneNumber;
	}
	
	
	
	public CustomerOrder(String id, double cost, ArrayList<Package> packages,String fName, String lName, 
			String address, String creditCardNum,String phoneNumber) {
		this.id = id;
		this.cost = cost;
		this.packages = packages;
		this.fName=fName;
		this.lName=lName;
		this.address = address;
		this.creditCardNum = creditCardNum;
		this.phoneNumber = phoneNumber;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public ArrayList<Package> getPackages() {
		return packages;
	}
	public void setPackages(ArrayList<Package> packages) {
		this.packages = packages;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreditCardNum() {
		return creditCardNum;
	}
	public void setCreditCardNum(String creditCardNum) {
		this.creditCardNum = creditCardNum;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getFName() {
		return fName;
	}



	public void setFName(String fName) {
		this.fName = fName;
	}



	public String getLName() {
		return lName;
	}



	public void setLName(String lName) {
		this.lName = lName;
	}
	
	
	
	
}
