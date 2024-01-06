package com.cleo.doggraphqlapi.mutators;

import com.cleo.doggraphqlapi.exceptions.BreedNotFoundException;
import com.cleo.doggraphqlapi.exceptions.DogNotFoundException;
import com.cleo.doggraphqlapi.models.Dog;
import com.cleo.doggraphqlapi.repositories.DogRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> findAllDogs = dogRepository.findAll();
        for(Dog d: findAllDogs){
            if(d.getBreed().equals(breed)){
                dogRepository.delete(d);
                deleted=true;
            }
        }
        if(!deleted){
            throw new BreedNotFoundException("Dog with given breed not found",breed);
        }
        return deleted;
    }

    public Dog updateDogName(String newName, Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;

        }else{
            throw new DogNotFoundException("Kutta not found",id);
        }

    }

}
