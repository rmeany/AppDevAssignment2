package ie.cit.soft8020.Assignment2.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ie.cit.soft8020.Assignment2.entities.CustomerOrder;
import ie.cit.soft8020.Assignment2.entities.Person;
import ie.cit.soft8020.Assignment2.entities.Package;
import ie.cit.soft8020.Assignment2.repositories.PersonRepo;
import ie.cit.soft8020.Assignment2.utils.Worker;

@Controller
public class Controllers {
	@Autowired
	PersonRepo personDAO;
	@Autowired
	Worker worker;
	
	
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
		model.addAttribute("packages", worker.presets());
		return "presetPackage";
	}
	@GetMapping("/customPackage")
	public String customPackage(Model model)
	{
		model.addAttribute("Addons",worker.getAddons());
		model.addAttribute("Flowers",worker.getFlowers());
		return "customPackage";
	}
	@GetMapping("/deleteOrder/{orderId}")
	public String deleteOrder(@PathVariable String orderId,Model model)
	{
		worker.deleteOrder(orderId);
		return "redirect:/order";
	}
	@GetMapping("/flower")
	public String flower(Model model)
	{
		model.addAttribute("flowers", worker.getFlowers());
		return "flower";
	}
	
	@GetMapping("/cart")
	public String cart(Model model)
	{
		model.addAttribute("cart", worker.getCart());
		return "cart";
	}
	
	
	@GetMapping("/cart/checkout")
	public String checkout(CustomerOrder customerOrder,Model m)
	{
		return "checkout";
	}
	
	@PostMapping("/cart/checkout")
	public String checkoutPost(@Valid CustomerOrder ord,BindingResult bindingResult)
	{
		
		if (bindingResult.hasErrors()) {
			return "checkout"; 
		}
	
		worker.makeOrder(ord);
		return "redirect:/";
	}
	
	@GetMapping("/order")
	public String order(Model model)
	{
		model.addAttribute("orders", worker.myOrders());
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
	@PostMapping("/cart/addPackage")
	public String addToCart(Package p )
	{
		worker.addToShoppingCart(p);
		return "redirect:/cart";	
	}
	
	@GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
	
	
}

