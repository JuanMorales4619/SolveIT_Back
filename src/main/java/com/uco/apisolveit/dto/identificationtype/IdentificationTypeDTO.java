package com.uco.apisolveit.dto.identificationtype;

public class IdentificationTypeDTO {
    private String id;
    private String description;

    public IdentificationTypeDTO(String id, String description) {
        setId(id);
        setDescription(description);
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}
