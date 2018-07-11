package com.mobileapps.training.zoo.model;

public class Animal {

    private String id;
    private String name;
    private String category;
    private String weight;
    private String age;
    private String gender;
    private String image;

    public Animal(String id, String name, String category, String weight, String age, String gender, String urlImage) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.image = urlImage;
    }

    public Animal() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
