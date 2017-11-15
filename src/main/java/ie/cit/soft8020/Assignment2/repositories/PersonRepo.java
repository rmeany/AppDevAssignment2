package ie.cit.soft8020.Assignment2.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ie.cit.soft8020.Assignment2.entities.Person;

public interface PersonRepo extends MongoRepository<Person, Integer>{

}
