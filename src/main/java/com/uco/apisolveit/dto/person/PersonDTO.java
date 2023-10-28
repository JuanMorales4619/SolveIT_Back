package com.uco.apisolveit.dto.person;

public class PersonDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String employmentField;

    public PersonDTO(String id, String name, String surname, String email, String password, String phone, String employmentField) {
        setId(id);
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPassword(password);
        setPhone(phone);
        setEmploymentField(employmentField);
    }

    public PersonDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmploymentField() {
        return employmentField;
    }

    public void setEmploymentField(String employmentField) {
        this.employmentField = employmentField;
    }
}
