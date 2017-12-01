package ie.cit.soft8020.Assignment2.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ie.cit.soft8020.Assignment2.repositories.AddOnRepo;
import ie.cit.soft8020.Assignment2.repositories.PackageRepo;
import ie.cit.soft8020.Assignment2.repositories.PersonRepo;
import ie.cit.soft8020.Assignment2.utils.Worker;

//ApplicationRunner is run before after the beans have been created and the application
//context has been completed.
//@Component makes it a bean so it is "seen" by Spring
@Component // ensures this is run because it is a bean
public class DataLoader implements ApplicationRunner {
	
	@Autowired // Find a PersonRepo bean and autowire it into personRepo
	PersonRepo personDAO;
	@Autowired
	PackageRepo preSetDAO;
	@Autowired
	Worker worker;
	@Autowired
	AddOnRepo addonDAO;
	
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception
	{
		personDAO.save(new Person(1,"Minnie Mouse", "FG5SH7"));
		personDAO.save(new Person(2,"Daisy Duck", "KB569F"));
		personDAO.save(new Person(3,"Gru", "12DGB4"));
		
		
		
		addonDAO.save(new AddOn("Chocolate",5.00));
		
		
		
		
		Flower[] flowers = worker.getFlowers();
		Flower rose=null;
		for(Flower flower : flowers)
		{
			if(flower.getName().equalsIgnoreCase("Rose"))
			{
				rose = flower;
				break;
			}
		}
		if(rose!=null)
		{
			rose.setQuantity(20);
			AddOn chocolate = addonDAO.findByName("Chocolate");
			preSetDAO.save(new Package(1, "Valentine's Day",
					(rose.getQuantity()*rose.getPrice())+chocolate.getCost(),rose,chocolate));
		}
	}
	
}

