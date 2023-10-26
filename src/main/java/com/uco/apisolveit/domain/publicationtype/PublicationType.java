package com.uco.apisolveit.domain.publicationtype;

import com.uco.apisolveit.singleton.publicationtype.PublicationTypeSingleton;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "publicationType_data")
public class PublicationType {

    @Id
    private String id;
    private String description;

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public String getDescription(){return description;}

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return "PublicationType{"+
                "id='" + id + '\''+
                ", description='"+ description + '\''+
                '}';
    }

    public static PublicationType setData( String description){
        PublicationType type = PublicationTypeSingleton.getInstance();
        switch (description){
            case ("GARDENING"):{
                //type.setId("6423292d6009b28ddb30b941");
                type.setDescription(description);
                break;
            }
            case("TECHNOLOGY"):{
                //type.setId("64232b836009b28ddb30b943");
                type.setDescription(description);
                break;
            }
            case("ASSISTANCE"):{
                //type.setId("64232b986009b28ddb30b944");
                type.setDescription(description);
                break;
            }
            default: {
                type.setId(null);
                type.setDescription(null);
                break;
            }
        }
    return type;
    }
}


