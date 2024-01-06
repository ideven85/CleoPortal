package com.cleo.doggraphqlapi.resolvers;

import com.cleo.doggraphqlapi.exceptions.DogNotFoundException;
import com.cleo.doggraphqlapi.models.Dog;
import com.cleo.doggraphqlapi.repositories.DogRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private final DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> retrieveAllDogs(){
        return dogRepository.findAll();
    }
    public Dog retrieveDogById(Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()) {
            return optionalDog.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
