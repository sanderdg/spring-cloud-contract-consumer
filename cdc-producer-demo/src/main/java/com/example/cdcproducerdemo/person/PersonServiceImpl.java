package com.example.cdcproducerdemo.person;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    private final Map<Long, Person> personMap;

    public PersonServiceImpl() {
        personMap = new HashMap();
        personMap.put(1L, new Person(1L, "Richard", "Gere"));
        personMap.put(2L, new Person(2L, "Emma", "Choplin"));
        personMap.put(3L, new Person(3L, "Anna", "Carolina"));
    }

    @Override
    public Person findPersonById(Long id) {
        return personMap.get(id);
    }

}
