package ie.cit.soft8020.Assignment2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ie.cit.soft8020.Assignment2.entities.Flower;
import ie.cit.soft8020.Assignment2.entities.Order;
import ie.cit.soft8020.Assignment2.entities.Person;
import ie.cit.soft8020.Assignment2.entities.Shop;
import ie.cit.soft8020.Assignment2.entities.Package;
import ie.cit.soft8020.Assignment2.repositories.FlowerRepo;
import ie.cit.soft8020.Assignment2.repositories.OrderRepo;
import ie.cit.soft8020.Assignment2.repositories.PackageRepo;
import ie.cit.soft8020.Assignment2.repositories.PersonRepo;

@Controller
public class Controllers {
	@Autowired
	PersonRepo personDAO;
	@Autowired
	FlowerRepo flowerDAO;
	@Autowired
	PackageRepo preSetDAO;
	@Autowired
	PackageRepo customDAO;
	@Autowired
	OrderRepo orderDAO;
	
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
	@GetMapping("/home")
	public String home(Model model)
	{
		doWelcomeWithParams(model);
		return "home";
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
		List<Person> p = personDAO.findAll();
		model.addAttribute("people", p);
		return "displayAll";
	}
	@GetMapping("/presetPackage")
	public String presetPackage(Model model)
	{
		List<Package> preSet = preSetDAO.findAll();
		model.addAttribute("packages", preSet);
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
		List<Flower> f = flowerDAO.findAll();
		model.addAttribute("flowers", f);
		return "flower";
	}
	
	@GetMapping("/order")
	public String order(Model model)
	{
		List<Order> o = orderDAO.findAll();
		model.addAttribute("orders", o);
		return "order";
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
		Person p = (Person) personDAO.findOne((int) id);
		model.addAttribute("person", p);
		return "displayOne";
	}
}

