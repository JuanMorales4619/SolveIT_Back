package com.uco.apisolveit.dto.publication;



public class PublicationDTO {
    private String id;
    private String publicationTitle;
    private String description;
    private String phone;
    private String categoryDescription;
    private String userName;


    public PublicationDTO(String id, String publicationTitle, String description, String phone, String categoryDescription, String userName) {
        setId(id);
        setPublicationTitle(publicationTitle);
        setCategory(categoryDescription);
        setPhone(phone);
        setDescription(description);
        setUserName(userName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserName(){return userName;}

    public void setUserName(String userName){this.userName = userName;}

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return categoryDescription;
    }

    public void setCategory(String category) {
        this.categoryDescription = category;
    }
}
