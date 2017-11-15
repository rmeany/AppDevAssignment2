package ie.cit.soft8020.Assignment2.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {
	@Id
	int id;
	
	String name;
	String eircode;
	
	
	public Person(int id, String name, String eircode) {
		super();
		this.id = id;
		this.name = name;
		this.eircode = eircode;
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
	public String getEircode() {
		return eircode;
	}
	public void setEircode(String eircode) {
		this.eircode = eircode;
	}
}

