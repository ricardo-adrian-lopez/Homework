package com.mobileapps.training.daily1.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Animal implements Parcelable {

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

    protected Animal(Parcel in) {
        id = in.readString();
        name = in.readString();
        category = in.readString();
        weight = in.readString();
        age = in.readString();
        gender = in.readString();
        image = in.readString();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(weight);
        dest.writeString(age);
        dest.writeString(gender);
        dest.writeString(image);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", weight='" + weight + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
