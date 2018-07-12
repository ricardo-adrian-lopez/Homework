package com.mobileapps.training.celebrities.model;

import java.io.Serializable;

public class Celebrity implements Serializable {
    private String name;
    private String age;
    private String image;
    private String residence;
    private String description;

    public Celebrity() {
    }

    @Override
    public String toString() {
        return "Celebrity{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", image='" + image + '\'' +
                ", residence='" + residence + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
