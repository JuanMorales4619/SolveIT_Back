package com.uco.apisolveit.domain.city;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "city_data")
public class City {
    @Id
    private String id;
    private String description;

    public City() {
        this.id = " ";
        this.description = " ";
    }

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
