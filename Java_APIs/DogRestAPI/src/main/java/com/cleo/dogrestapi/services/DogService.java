package com.cleo.dogrestapi.services;

import com.cleo.dogrestapi.models.Dog;

import javax.swing.*;
import java.util.List;

public interface DogService {

    String retrieveDogsByBreedAndId(Long id);
    List<Dog> retrieveAllDogs();
    List<String> retrieveDogNames();
    List<String> retrieveDogBreeds();
}
