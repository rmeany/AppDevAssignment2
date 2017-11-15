package ie.cit.soft8020.Assignment2.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ie.cit.soft8020.Assignment2.entities.Flower;

public interface FlowerRepo extends MongoRepository<Flower, Integer>{

}