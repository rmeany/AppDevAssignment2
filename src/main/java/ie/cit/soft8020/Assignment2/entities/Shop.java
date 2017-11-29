package ie.cit.soft8020.Assignment2.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Shop {
	
	private Package preSet;
	private Package custom;
	private Flower rose;
	private Flower daisy;
	private Flower lily;
	
}

