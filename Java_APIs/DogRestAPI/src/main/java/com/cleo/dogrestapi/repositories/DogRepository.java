package com.cleo.dogrestapi.repositories;

import com.cleo.dogrestapi.models.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends CrudRepository<Dog,Long> {

    @Query("SELECT d.id,d.name from Dog d where d.id=:id")
    String findByBreedAndId(Long id);
    @Query("SELECT d.id,d.name from Dog d ")

    List<String> findAllByName();


    @Query("SELECT d.id,d.breed from Dog d ")

    List<String> findAllByBreed();

}
