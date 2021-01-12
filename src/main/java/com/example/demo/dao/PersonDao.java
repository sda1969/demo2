package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, Person person);
    List<Person> getAllPersons();
    Optional<Person> getPersonById(UUID uuid);
    int updatePersonById(UUID uuid, Person person);
    int deletePersonById(UUID uuid);

    default int insertPerson(Person person){
        UUID uuid = UUID.randomUUID();
        return insertPerson(uuid, person);
    }
}
