package com.uco.apisolveit.domain.identificationtype;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "identificationType_data")
public class IdentificationType {

    @Id
    private String id;
    private String description;

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    @Override
    public String toString(){
        return "IdentificationType{"+
                "id='" + id + '\''+
                ", description='"+ description + '\''+
                '}';
    }
}
