package ie.cit.soft8020.Assignment2.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import ie.cit.soft8020.Assignment2.entities.AddOn;

public interface AddOnRepo extends MongoRepository<AddOn, String> {

	AddOn findByName(String Name);
}
