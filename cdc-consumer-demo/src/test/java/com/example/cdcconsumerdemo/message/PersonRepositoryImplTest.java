package com.example.cdcconsumerdemo.message;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
// Example: Use annotation for Local
//@AutoConfigureStubRunner(ids = {"com.example:cdc-producer-demo:+:stubs:8100"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)

// Example: Use annotation for Remote
//   - When connect to github by ssh, then set repositoryRoot=git://git@github.com:sanderdg/spring-cloud-contract-producer.git
@AutoConfigureStubRunner(
    repositoryRoot = "git://https://github.com/sanderdg/spring-cloud-contract-producer.git",
    ids = {"com.example:cdc-producer-demo:+::8100"},
    stubsMode = StubRunnerProperties.StubsMode.REMOTE
)
public class PersonRepositoryImplTest {

    // Example: Use RegisterExtension. programming
    //@RegisterExtension
    public static StubRunnerExtension stubRunnerExtension = new StubRunnerExtension()
            .repoRoot("git://https://github.com/sanderdg/spring-cloud-contract-producer.git")
            .downloadStub("com.example", "cdc-producer-demo")
            .stubsMode(StubRunnerProperties.StubsMode.REMOTE)
            .minPort(8100).maxPort(8100)
            .withProperties(Map.of("git.branch", "main"))
            ;

    @Test
    public void get_person_from_service_contract() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PersonRepository.Person> responseEntity = restTemplate.getForEntity("http://localhost:8100/persons/1", PersonRepository.Person.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        PersonRepository.Person person = responseEntity.getBody();
        Assertions.assertEquals(1L, person.id);
        Assertions.assertEquals("foo", person.name);
        Assertions.assertEquals("bee", person.surname);
    }

    @Test
    public void findPersonById() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:8100");
        PersonRepositoryImpl sut = new PersonRepositoryImpl(restTemplateBuilder);

        PersonRepository.Person person = sut.findPersonById(1L);

        Assertions.assertEquals(1L, person.id);
        Assertions.assertEquals("foo", person.name);
        Assertions.assertEquals("bee", person.surname);
    }
}
