package ie.cit.soft8020.Assignment2.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ie.cit.soft8020.Assignment2.repositories.AddOnRepo;
import ie.cit.soft8020.Assignment2.repositories.PackageRepo;
import ie.cit.soft8020.Assignment2.utils.Worker;

//ApplicationRunner is run before after the beans have been created and the application
//context has been completed.
//@Component makes it a bean so it is "seen" by Spring
@Component // ensures this is run because it is a bean
public class DataLoader implements ApplicationRunner {

	
	@Autowired
	PackageRepo preSetDAO;
	@Autowired
	Worker worker;
	@Autowired
	AddOnRepo addonDAO;


	@Override
	public void run(ApplicationArguments arg0) throws Exception
	{
		

		//Add addons to repo
		addonDAO.save(new AddOn("Chocolate",5.00));
		addonDAO.save(new AddOn("Teddy bear",10.00));


		//Add preset packages to the packages repo if there are enough flowers
		Flower[] flowers = worker.getFlowers();
		Flower rose=null;
		Flower lily=null;
		for(Flower flower : flowers)
		{
			if(flower.getName().equalsIgnoreCase("Rose"))
				rose = flower;
			
			if(flower.getName().equalsIgnoreCase("Lily"))
				lily = flower;
		}
		if(rose!=null)
		{
			rose.setQuantity(20);
			AddOn chocolate = addonDAO.findByName("Chocolate");
			preSetDAO.save(new Package("Valentine's Day",
					(rose.getQuantity()*rose.getPrice())+chocolate.getCost(),rose,chocolate));
		}
		if(lily!=null)
		{
			lily.setQuantity(20);
			AddOn teddyBear = addonDAO.findByName("Teddy bear");
			preSetDAO.save(new Package("Baby shower",
					(lily.getQuantity()*lily.getPrice())+teddyBear.getCost(),lily,teddyBear));
		}

	}
}


