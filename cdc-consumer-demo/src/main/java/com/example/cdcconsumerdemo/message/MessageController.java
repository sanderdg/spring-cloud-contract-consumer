package com.example.cdcconsumerdemo.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {

    private final PersonService personService;

    public MessageController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("persons/{personId}")
    public String getPersonById(@PathVariable("personId") Long personId) {
        Person person = personService.findPersonById(personId);
        return "Hello " + person.getName();
    }
}
