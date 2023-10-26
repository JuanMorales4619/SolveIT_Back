package com.uco.apisolveit.domain.person;

import com.uco.apisolveit.dto.person.PersonDTO;
import com.uco.apisolveit.singleton.person.PersonSingleton;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "person_data")
public class        Person {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String employmentField;

    public Person(){
        this.id = " ";
        this.name = " ";
        this.surname = " ";
        this.email = " ";
        this.password = " ";
        this. phone = " ";
        this.employmentField = " ";
    }

    public String getId(){return id;}

    public void setId(String id){this.id = id;}
    public String getName(){return name;}

    public void setName(String name){this.name = name;}
    public String getSurname(){return surname;}

    public void setSurname(String surname){this.surname = surname;}
    public String getEmail(){return email;}

    public void setEmail(String email){this.email = email;}
    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}
    public String getPhone(){return phone;}

    public void setPhone(String phone){this.phone = phone;}
    public String getEmploymentField(){return employmentField;}

    public void setEmploymentField(String employmentField){this.employmentField = employmentField;}

    @Override
    public String toString(){
        return "Person{"+
                "id='" + id + '\''+
                ", name='"+ name + '\''+
                ", surname='"+ surname + '\''+
                ", email='"+ email + '\''+
                ", password='"+ password + '\''+
                ", phone='"+ phone + '\''+
                ", employmentField'"+ employmentField + '\''+
                '}';
    }
    public static Person setData(PersonDTO personDTO){
        Person person = PersonSingleton.getInstance();
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setPassword(personDTO.getPassword());
        person.setPhone(personDTO.getPhone());
        person.setEmail(personDTO.getEmail());
        person.setEmploymentField(personDTO.getEmploymentField());
        return person;
    }

}
