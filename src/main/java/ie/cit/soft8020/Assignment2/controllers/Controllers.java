package ie.cit.soft8020.Assignment2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ie.cit.soft8020.Assignment2.entities.Flower;
import ie.cit.soft8020.Assignment2.entities.Person;
import ie.cit.soft8020.Assignment2.entities.Shop;
import ie.cit.soft8020.Assignment2.repositories.FlowerRepo;
import ie.cit.soft8020.Assignment2.repositories.PersonRepo;
import ie.cit.soft8020.Assignment2.repositories.ShopRepo;

@Controller
public class Controllers {
	@Autowired
	PersonRepo personRepo;
	
	@Autowired
	FlowerRepo flowerRepo;
	
	@Autowired
	ShopRepo shopRepo;
	
	/**
	* Calls index.html
	*/
	@GetMapping("/")
	public String doWelcomeWithParams(Model model)
	{
		return "index";
	}
	
	/*
	* An example of using a path variable.
	* localhost:8080/usingParameter?name=Ryan will add Ryan to the welcome
	* localhost:8080/usingParameter uses the default value of To You!
	*/
	@GetMapping("/usingParameter")
	public String doWelcomeWithParams(@RequestParam(value="name", defaultValue=
	"To	You!")String name, Model model)
	{
		String sentence = "Welcome " + name;
		model.addAttribute("message", sentence);
		return "parameter";
	}
	
	/*
	* The repository uses the in-built findAll() method of MongoRepository
	* This returns a list of People
	* This list is added to the model
	* The model is sent to the displayAll.html template.
	*/
	@GetMapping("/displayAll")
	public String displayAll(Model model)
	{
		List<Person> p = personRepo.findAll();
		model.addAttribute("people", p);
		return "displayAll";
	}
	
	@GetMapping("/presetPackage")
	public String presetPackage(Model model)
	{
		return "presetPackage";
	}
	@GetMapping("/customPackage")
	public String customPackage(Model model)
	{
		return "customPackage";
	}
	@GetMapping("/flower")
	public String flower(Model model)
	{
		List<Flower> f = flowerRepo.findAll();
		model.addAttribute("flowers", f);
		return "flower";
	}
	
	/*
	* This uses a PathVariable to specify the id being searched for.
	* findOne() is the default method to search for one record using MongoRepository.
	* It returns one record.
	* This record is added to the model.
	* The model is sent to the displayOne.html resolver.
	*/
	@GetMapping("/displayOne/{id}")
	public String showMyDetails(@PathVariable int id, Model model)
	{
		Person p = (Person) personRepo.findOne((int) id);
		model.addAttribute("person", p);
		return "displayOne";
	}
}

