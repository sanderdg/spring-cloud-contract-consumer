package com.example.cdcconsumerdemo.message;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private final RestTemplate restTemplate;

    public PersonRepositoryImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Person findPersonById(Long id) {
        return restTemplate.getForObject("/persons/{id}", Person.class, id);
    }
}
