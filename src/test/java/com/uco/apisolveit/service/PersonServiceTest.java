package com.uco.apisolveit.service;

import com.uco.apisolveit.domain.person.Person;
import com.uco.apisolveit.dto.person.PersonDTO;
import com.uco.apisolveit.repository.person.IPersonRepository;
import com.uco.apisolveit.service.person.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private IPersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    Person person = new Person();

    @BeforeEach
    private  void setUp(){
        person.setEmail("juanma@gmail.com");
        person.setName("Juan David");
        person.setId("34562");
        person.setPhone("3015584663");
        person.setSurname("Zuluaga");
        person.setEmploymentField("Trabajador");
        person.setPassword("12345678@");
    }
    @Test
    private void getAllUsers(){
        Person person1 = new Person();
        person1.setEmail("juanma@gmail.com");
        person1.setName("Juan David");
        person1.setId("34563");
        person1.setPhone("3015584663");
        person1.setSurname("Zuluaga");
        person1.setEmploymentField("Trabajador");
        person1.setPassword("12345678@");
        given(personRepository.findAll()).willReturn((Flux<Person>) List.of(person,person1));
        List<Person> list = (List<Person>) personService.get();
        assertThat(list.size()).isEqualTo(2);
    }
    @Test
    private void getOneUser(){
        given(personRepository.findByEmail(person.getEmail())).willReturn((Mono<Person>) List.of(person));
        List<Person> list = (List<Person>) personService.get(person.getEmail());
        assertThat(list.size()).isEqualTo(1);
    }

}
