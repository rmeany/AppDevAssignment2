package ie.cit.soft8020.Assignment2.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ie.cit.soft8020.Assignment2.basepackage.entities.Shop;

public interface ShopRepo extends MongoRepository<Shop, Integer>{

}
