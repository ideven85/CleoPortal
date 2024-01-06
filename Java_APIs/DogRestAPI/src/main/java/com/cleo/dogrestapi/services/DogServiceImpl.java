package com.cleo.dogrestapi.services;

import com.cleo.dogrestapi.models.Dog;
import com.cleo.dogrestapi.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService{

    @Autowired
    private DogRepository dogRepository;

    @Override
    public String retrieveDogsByBreedAndId(Long id) {
        Optional<String> optionalDogBreed = Optional.ofNullable(dogRepository.findByBreedAndId(id));
        String dogByBreed = optionalDogBreed.orElseThrow(DogException::new);
        return dogByBreed;
    }

    @Override
    public List<Dog> retrieveAllDogs() {
        return (List<Dog>) dogRepository.findAll();
    }

    @Override
    public List<String> retrieveDogNames() {
        return (List<String>) dogRepository.findAllByName();
    }

    @Override
    public List<String> retrieveDogBreeds() {
        return (List<String>) dogRepository.findAllByBreed();
    }
}
