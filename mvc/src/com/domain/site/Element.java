package com.domain.site;

public class Element {

    private String id;

    public String getId() {
        return id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String translation;

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Element(String id, String name, String description, String translation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.translation = translation;
    }


    public Element() {
    }

}
