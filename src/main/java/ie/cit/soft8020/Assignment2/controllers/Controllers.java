package ie.cit.soft8020.Assignment2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ie.cit.soft8020.Assignment2.entities.CustomerOrder;
import ie.cit.soft8020.Assignment2.entities.Package;
import ie.cit.soft8020.Assignment2.utils.Worker;

@Controller
public class Controllers {
	
	@Autowired
	Worker worker;

	//Index mapping
	@GetMapping("/")
	public String doWelcomeWithParams(Model model)
	{
		return "index";
	}
	
	//Preset packages mapping
	@GetMapping("/presetPackage")
	public String presetPackage(Model model)
	{
		model.addAttribute("packages", worker.presets());
		return "/presetPackage";
	}
	
	//Custom packages mapping
	@GetMapping("/customPackage")
	public String customPackage(Model model)
	{
		
		model.addAttribute("Package",new Package());
		model.addAttribute("Addons",worker.getAddons());
		model.addAttribute("Flowers",worker.getFlowers());
		return "customPackage";
	}
	@PostMapping("/customPackage")
	public String customPackage(Package p )
	{
		worker.addCustomPackageToCart(p);
		return "redirect:/cart";	
	}

	//Admin mappings
	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}
	@GetMapping("/admin/flowers")
	public String flower(Model model)
	{
		model.addAttribute("flowers", worker.getFlowers());
		return "flowers";
	}
	@GetMapping("/admin/orders")
	public String order(Model model)
	{
		model.addAttribute("orders", worker.myOrders());
		return "orders";
	}
	@GetMapping("/admin/orders/deleteOrder/{orderId}")
	public String deleteOrder(@PathVariable String orderId,Model model)
	{
		worker.deleteOrder(orderId);
		return "redirect:/admin/orders";
	}
	@GetMapping("/admin/customerorders")
	public String customerOrders(Model model)
	{
		model.addAttribute("customerOrders",worker.getCustomerOrders());
		return "customerorders";
	}
	
	//Cart mappings
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
		if (bindingResult.hasErrors())
			return "checkout"; 
		worker.makeOrder(ord);
		return "redirect:/";
	}
	@PostMapping("/cart/addPackage")
	public String addToCart(Package p)
	{
		worker.addToShoppingCart(p);
		return "redirect:/cart";	
	}
	@GetMapping("/cart/deletePackage/{packageId}")
	public String deletePackage(@PathVariable String packageId)
	{
		worker.removeFromCart(packageId);
		return "redirect:/cart";
	}
	
	
	//Authentication mapping
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}
	
	//Error mappings
	@GetMapping("/error")
	public String error() {
		return "/error";
	}
	


}

