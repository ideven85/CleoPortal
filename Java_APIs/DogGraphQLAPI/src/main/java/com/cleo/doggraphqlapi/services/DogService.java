package com.cleo.doggraphqlapi.services;


import com.cleo.doggraphqlapi.models.Dog;

import java.util.List;

public interface DogService {

    String retrieveDogsByBreedAndId(Long id);
    List<Dog> retrieveAllDogs();
    List<String> retrieveDogNames();
    List<String> retrieveDogBreeds();
}
