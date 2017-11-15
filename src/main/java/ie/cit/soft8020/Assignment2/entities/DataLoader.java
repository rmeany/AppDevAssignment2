package ie.cit.soft8020.Assignment2.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ie.cit.soft8020.Assignment2.repositories.FlowerRepo;
import ie.cit.soft8020.Assignment2.repositories.PersonRepo;

//ApplicationRunner is run before after the beans have been created and the application
//context has been completed.
//@Component makes it a bean so it is "seen" by Spring
@Component // ensures this is run because it is a bean
public class DataLoader implements ApplicationRunner {
	
	@Autowired // Find a PersonRepo bean and autowire it into personRepo
	PersonRepo personRepo;
	@Autowired
	FlowerRepo flowerRepo;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception
	{
		personRepo.save(new Person(1,"Minnie Mouse", "FG5SH7"));
		personRepo.save(new Person(2,"Daisy Duck", "KB569F"));
		personRepo.save(new Person(3,"Gru", "12DGB4"));
		
		flowerRepo.save(new Flower(1,"Red Rose", 3));
		flowerRepo.save(new Flower(2,"White Lily", 2));
		flowerRepo.save(new Flower(3,"Daffodil", 1));
	}}

