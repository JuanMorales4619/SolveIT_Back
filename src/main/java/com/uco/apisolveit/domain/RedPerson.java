package com.uco.apisolveit.domain;

import com.uco.apisolveit.dto.redperson.RedPersonDTO;
import com.uco.apisolveit.singleton.redperson.RedPersonSingleton;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "redperson-data")
public class RedPerson {

    @Id
    private String id;
    private String username;
    private String password;

    public RedPerson(){
        //Instant the class
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RedUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public static RedPerson setData(RedPersonDTO redPersonDTO){
        RedPerson redperson = RedPersonSingleton.getInstance();
        redperson.setId(redPersonDTO.getId());
        redperson.setPassword(redPersonDTO.getPassword());
        redperson.setUsername(redPersonDTO.getUsername());
        return redperson;
    }
}
