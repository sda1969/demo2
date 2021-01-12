package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public void addPerson(@NonNull @RequestBody Person person){
        personService.addPerson(person);

    }
    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }
    @GetMapping (path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID uuid){
        return personService.getPersonById(uuid).orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id")UUID uuid){
        personService.deletePersonById(uuid);
    }
    @PutMapping (path = "{id}")
    public void updatePersonById(@PathVariable("id")UUID uuid, @NonNull @RequestBody Person person){
        personService.updatePersonById(uuid, person);
    }

}
