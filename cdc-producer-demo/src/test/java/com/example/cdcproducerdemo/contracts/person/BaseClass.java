package com.example.cdcproducerdemo.contracts.person;

import com.example.cdcproducerdemo.person.Person;
import com.example.cdcproducerdemo.person.PersonController;
import com.example.cdcproducerdemo.person.PersonService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class BaseClass {

    @Autowired
    private PersonController personController;

    @MockBean
    private PersonService personService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(personController);

        Mockito.when(personService.findPersonById(1L))
                .thenReturn(new Person(1L, "foo", "bee"));
    }
}
