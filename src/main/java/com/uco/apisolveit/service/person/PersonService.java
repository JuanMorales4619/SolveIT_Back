package com.uco.apisolveit.service.person;

import com.uco.apisolveit.domain.person.Person;
import com.uco.apisolveit.util.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.uco.apisolveit.repository.person.IPersonRepository;
import com.uco.apisolveit.util.Constant;

import java.util.Objects;

import static com.uco.apisolveit.util.UtilString.isEmptyOrNull;


@Service
public class PersonService {
   @Autowired
    private IPersonRepository personRepository;

    public Flux<Person> get(){
        return personRepository.findAll();
    }

    public Mono<Person> get(String email){
         if(!Objects.isNull(email)){
             UtilString.requieresNoNullOrNoEmpty(email,String.format(Constant.TXT_EXPECT_VALUE, email));
             UtilString.requiresPattern(email, Constant.TXT_PATTER_EMAIL,String.format(Constant.TXT_BAD_EMAIL));
         }
        return personRepository.findByEmail(email);
    }
    public Mono<Person> save(Person person){
        validationData(person);
        return personRepository.save(person);
    }

    public Mono<Person>  put(String email,Person person){
        validationData(person);
        return personRepository.findByEmail(email).flatMap(existingPerson -> {

            existingPerson.setName(person.getName().isEmpty() ? existingPerson.getName() : person.getName());
            existingPerson.setSurname(person.getSurname().isEmpty() ? existingPerson.getSurname() : person.getSurname());
            existingPerson.setEmail(person.getEmail().isEmpty() ? existingPerson.getEmail() : person.getEmail());
            existingPerson.setPhone(Objects.isNull(person.getPhone()) ? existingPerson.getPhone() : person.getPhone());
            existingPerson.setPassword(person.getPassword().isEmpty() ? existingPerson.getPassword() : person.getPassword());
            existingPerson.setPassword(person.getEmploymentField().isEmpty() ? existingPerson.getEmploymentField() : person.getEmploymentField());

            return personRepository.save(existingPerson);
        });
    }
    public Mono<Person>  patch(String email,Person person){

        return personRepository.findByEmail(email).flatMap(existingPerson -> {

            existingPerson.setName(isEmptyOrNull( person.getName()) ? existingPerson.getName() : person.getName());
            existingPerson.setSurname(isEmptyOrNull(person.getSurname()) ? existingPerson.getSurname() : person.getSurname());
            existingPerson.setEmail(isEmptyOrNull(person.getEmail()) ? existingPerson.getEmail() : person.getEmail());
            existingPerson.setPhone(isEmptyOrNull(person.getPhone()) ? existingPerson.getPhone() : person.getPhone());
            existingPerson.setPassword(isEmptyOrNull(person.getPassword()) ? existingPerson.getPassword() : person.getPassword());
            existingPerson.setPassword(isEmptyOrNull(person.getEmploymentField()) ? existingPerson.getEmploymentField() : person.getEmploymentField());

            return personRepository.save(existingPerson);
        });
    }

    public Mono<Void> delete(String email){
        if(!isEmptyOrNull(email)){
            UtilString.requiresPattern(email, Constant.TXT_PATTER_EMAIL,String.format(Constant.TXT_BAD_EMAIL));
        }
        return personRepository.findByEmail(email).flatMap(existingPerson -> personRepository.deleteById(existingPerson.getId()));
    }


    private void validationData(Person person){
        UtilString.requieresNoNullOrNoEmpty(person.getName(),String.format(Constant.TXT_EXPECT_VALUE, person.getName()));
        UtilString.requieresNoNullOrNoEmpty(person.getEmail(),String.format(Constant.TXT_EXPECT_VALUE, person.getEmail()));
        UtilString.requieresNoNullOrNoEmpty(person.getPassword(),String.format(Constant.TXT_EXPECT_VALUE, person.getPassword()));
        UtilString.requieresNoNullOrNoEmpty(person.getPhone(),String.format(Constant.TXT_EXPECT_VALUE, person.getPhone()));
        UtilString.requieresNoNullOrNoEmpty(person.getEmploymentField(),String.format(Constant.TXT_EXPECT_VALUE, person.getEmploymentField()));
        UtilString.requieresNoNullOrNoEmpty(person.getSurname(),String.format(Constant.TXT_EXPECT_VALUE, person.getSurname()));


        UtilString.requiresPattern(person.getEmail(), Constant.TXT_PATTER_EMAIL,String.format(Constant.TXT_BAD_EMAIL));

        UtilString.requieresLength(person.getPassword(),10,10,String.format(Constant.TXT_NO_LENGTH_REQUIERED, person.getPassword()));
        UtilString.requieresLength(person.getPhone(), 10, 10,String.format(Constant.TXT_NO_LENGTH_REQUIERED, person.getPhone()));

    }

}
