package com.cleo.doggraphqlapi.web;

import com.cleo.doggraphqlapi.models.Dog;
import com.cleo.doggraphqlapi.services.DogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Dog>> listAllDogs(){
        return new ResponseEntity<>(dogService.retrieveAllDogs(), HttpStatus.OK);
    }
    @GetMapping("/names")
    public ResponseEntity<List<String>> listAllDogsByNames(){
        return new ResponseEntity<List<String>>(dogService.retrieveDogNames(), HttpStatus.OK);
    }
    @GetMapping("/breeds")
    public ResponseEntity<List<String>> listAllDogsByBreeds(){
        return new ResponseEntity<List<String>>(dogService.retrieveDogBreeds(), HttpStatus.OK);
    }
    @GetMapping("/{id}/breed")
    public ResponseEntity<String> listAllDogsByBreedAndId(@PathVariable Long id){
        return new ResponseEntity<String>(dogService.retrieveDogsByBreedAndId(id), HttpStatus.OK);
    }

}
