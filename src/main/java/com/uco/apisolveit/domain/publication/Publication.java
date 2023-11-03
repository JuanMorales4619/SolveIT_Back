package com.uco.apisolveit.domain.publication;

import com.uco.apisolveit.domain.publicationtype.PublicationType;
import com.uco.apisolveit.dto.publication.PublicationDTO;
import com.uco.apisolveit.singleton.publication.PublicationSingleton;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "publication_data")
public class Publication {

    @Id
    private String id;
    private String publicationTitle;
    private String description;
    private String phone;
    private PublicationType category;
    private String userName;
    private boolean status;

    public Publication(){
        this.id = " ";
        this.publicationTitle = " ";
        this.description= " ";
        this.phone=" ";
        this.category = new PublicationType();
        this.userName = " ";
        this.status = true;
    }

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public String getUserName(){return userName;}

    public void setUserName(String userName){this.userName = userName;}

    public String getPublicationTitle(){return publicationTitle;}

    public void setPublicationTitle(String publicationTitle){this.publicationTitle = publicationTitle;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public PublicationType getCategory(){return category;}

    public Boolean getStatus(){return status;}

    public void setStatus(boolean status){this.status = status;}

    public void setCategory(PublicationType category){
        this.category.setId(category.getId());
        this.category.setDescription(category.getDescription());
    }

    public String getPhone(){return phone;}

    public void setPhone(String phone){this.phone = phone;}

    @Override
    public String toString(){
        return "Publication{"+
                "id='" + id + '\''+
                ", publicationTitle='"+ publicationTitle + '\''+
                ", description='"+ description + '\''+
                ", phone='"+ phone + '\''+
                ", category='"+ category + '\''+
                ",username='" + userName + '\''+
                ",status='" + status + '\''+
                '}';
    }
    public static Publication setData(PublicationDTO publicationDTO){
        Publication publication = PublicationSingleton.getInstance();
        publication.setId(publicationDTO.getId());
        publication.setPublicationTitle(publicationDTO.getPublicationTitle());
        publication.setPhone(publicationDTO.getPhone());
        publication.setCategory(PublicationType.setData(publicationDTO.getCategory()));
        publication.setDescription(publicationDTO.getDescription());
        publication.setUserName(publicationDTO.getUserName());
        publication.setStatus(publicationDTO.getStatus());
        return publication;
    }
}


