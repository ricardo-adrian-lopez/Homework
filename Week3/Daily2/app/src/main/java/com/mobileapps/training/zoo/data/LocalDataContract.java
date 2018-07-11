package com.mobileapps.training.zoo.data;

import android.provider.BaseColumns;

public class LocalDataContract {

    public static final String DATABASE_NAME = "Zoo.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "animal";

    public static class Animal implements BaseColumns{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String CATEGORY = "category";
        public static final String WEIGHT = "weight";
        public static final String AGE = "age";
        public static final String GENDER = "gender";
        public static final String IMAGE = "image";
    }

    public static class DDL {
        public static final String CREATE_TABLE_ANIMAL = "CREATE TABLE " + TABLE_NAME +"("+
                Animal.ID + " TEXT," +
                Animal.NAME + " TEXT," +
                Animal.CATEGORY + " TEXT,"+
                Animal.WEIGHT + " TEXT," +
                Animal.AGE + " TEXT," +
                Animal.GENDER + " TEXT," +
                Animal.IMAGE + " TEXT" +")";
    }

    public static class DML {
        public static final String GET_CATEGORIES = "SELECT DISTINCT category AS category FROM animal ORDER BY category DESC";
        public static final String GET_ANIMAL_ID =  "SELECT * FROM animal WHERE id=";
        public static final String GET_ANIMAL_CARD = "SELECT name AS name, image AS image FROM animal WHERE category = ";
    }
}
