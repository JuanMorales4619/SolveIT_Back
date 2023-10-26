package com.uco.apisolveit.dto.publicationtype;

import com.uco.apisolveit.domain.publicationtype.PublicationTypeEnum;

public class PublicationTypeDTO {

    private String id;
    private PublicationTypeEnum description;

    public PublicationTypeDTO(String id, PublicationTypeEnum description) {
        setId(id);
        setDescription(description);
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public PublicationTypeEnum getDescription() {return description;}

    public void setDescription(PublicationTypeEnum description) {this.description = description;}
}
