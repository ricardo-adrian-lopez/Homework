package com.mobileapps.training.daily3.data;

import android.provider.BaseColumns;

public class LocalDataContract {

    public static final String DATABASE_NAME = "Zoo.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "animal";

    public static class Animal implements BaseColumns {
        public static final String NAME = "name";
        public static final String CATEGORY = "category";
        public static final String WEIGHT = "weight";
        public static final String AGE = "age";
        public static final String GENDER = "gender";
        public static final String IMAGE = "image";
    }

    public static class DDL {
        public static final String CREATE_TABLE_ANIMAL = "CREATE TABLE " + TABLE_NAME + "(" +
                Animal._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Animal.NAME + " TEXT," +
                Animal.CATEGORY + " TEXT," +
                Animal.WEIGHT + " TEXT," +
                Animal.AGE + " TEXT," +
                Animal.GENDER + " TEXT," +
                Animal.IMAGE + " TEXT" + ")";
    }
}
