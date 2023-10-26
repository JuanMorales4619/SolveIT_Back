package com.uco.apisolveit.controller.person;

import com.uco.apisolveit.controller.Response;
import com.uco.apisolveit.domain.person.Person;
import com.uco.apisolveit.dto.person.PersonDTO;
import com.uco.apisolveit.service.person.PersonService;
import com.uco.apisolveit.util.UtilObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/rest")
public class PersonController {
    @Autowired
    private PersonService personService;


    @GetMapping("/user")
    public ResponseEntity<Response<Person>> getPerson(){
        ResponseEntity<Response<Person>> responseEntity;
        List<String> messages = new ArrayList<>();
        Response<Person> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        try{
            response.setData(personService.get().collectList().block());
            messages.add("OK");
            statusCode = HttpStatus.OK;
        }catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);

        return responseEntity;
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<Response<Person>> getPerson(@PathVariable("email") String email){
        ResponseEntity<Response<Person>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        Response<Person> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        try{
            Person person = personService.get(email).block();
            if(!UtilObject.getUtilObject().isNull(person)){
                personList.add(person);
                response.setData(personList);
                messages.add("Person found successfully");
                statusCode = HttpStatus.OK;
            }else{
                response.setData(personList);
                messages.add("Person not found");
                statusCode = HttpStatus.NOT_FOUND;
            }

        }catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;
    }

    @PostMapping("/user")
    public Mono<ResponseEntity<String>>  postPerson(@Valid @RequestBody PersonDTO personDTO){
        return personService.save(Person.setData(personDTO)).map(savePublication ->
                        new  ResponseEntity<String>("User register Successfully",HttpStatus.CREATED))
                .defaultIfEmpty(new  ResponseEntity<>("There was an error creating user",HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<Response<Person>> putPerson(@PathVariable("email") String email, @Valid @RequestBody PersonDTO personDTO){
        ResponseEntity<Response<Person>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<Person> data = new ArrayList<>();
        Response<Person> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        try{
            data.add(personService.put(email,Person.setData(personDTO)).block());
            messages.add("Person actualized successfully");
            statusCode = HttpStatus.OK;
        }
        catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setData(data);
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;
    }
    @PatchMapping("/user/{email}")
    public ResponseEntity<Response<Person>> patchPerson( @PathVariable("email") String email,@Valid @RequestBody PersonDTO personDTO){
        ResponseEntity<Response<Person>> responseEntity;
        List<String> messages = new ArrayList<>();
        List<Person> data = new ArrayList<>();
        Response<Person> response = new Response<>();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        try{
            data.add(personService.patch(email,Person.setData(personDTO)).block());
            messages.add("Person actualized successfully");
            statusCode = HttpStatus.OK;
        }
        catch (Exception exception){
            messages.add(exception.getMessage());
        }
        response.setData(data);
        response.setMessage(messages);
        response.setStatus(statusCode);
        responseEntity = new ResponseEntity<>(response,statusCode);
        return responseEntity;

    }

    @DeleteMapping("/user/{email}")
    public Mono<ResponseEntity<Void>> deletePerson(@PathVariable("email") String email){
        return personService.delete(email).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
