package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("mariaDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }
    public List<Person>getAllPersons(){
        return personDao.getAllPersons();
    }
    public Optional<Person>getPersonById(UUID uuid){
        return personDao.getPersonById(uuid);
    }
    public int updatePersonById(UUID uuid, Person person){
        return personDao.updatePersonById(uuid, person);
    }
    public int deletePersonById(UUID uuid){
        return personDao.deletePersonById(uuid);
    }
}
