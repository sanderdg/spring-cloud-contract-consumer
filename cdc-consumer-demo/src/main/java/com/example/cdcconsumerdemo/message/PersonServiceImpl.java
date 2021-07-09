package com.example.cdcconsumerdemo.message;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findPersonById(Long id) {
        return Optional.ofNullable(personRepository.findPersonById(id))
                .map(v -> new Person(v.id, v.name, v.surname))
                .get();
    }

}
