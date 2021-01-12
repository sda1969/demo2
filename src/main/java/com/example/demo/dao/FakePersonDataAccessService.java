package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    public static List<Person> db = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        db.add(new Person(person.getName(), id));
        return 1;
    }

    @Override
    public List<Person> getAllPersons() {
        return db;
    }

    @Override
    public Optional<Person> getPersonById(UUID uuid) {
        return db.stream().filter(person -> person.getId().equals(uuid)).findFirst();
    }

    @Override
    public int updatePersonById(UUID uuid, Person update) {
        Optional<Person> personMaybe = getPersonById(uuid);
        if (personMaybe.isEmpty()){
            return 0;
        }
        else {
            int index = db.indexOf(personMaybe.get());
            if (index >= 0) {
                db.set(index, new Person(update.getName(), uuid));
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deletePersonById(UUID uuid) {
        Optional<Person> personMaybe = getPersonById(uuid);
        if (personMaybe.isEmpty()){
            return 0;
        }
        else {
           db.remove(personMaybe.get());
        }
        return 1;
    }
}
