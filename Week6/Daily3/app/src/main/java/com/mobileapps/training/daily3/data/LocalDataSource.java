package com.mobileapps.training.daily3.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobileapps.training.daily3.Animal.Animal;
import com.mobileapps.training.daily3.AnimalsProvider;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource extends SQLiteOpenHelper {

    public LocalDataSource(Context context) {
        super(context, LocalDataContract.DATABASE_NAME, null, LocalDataContract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalDataContract.DDL.CREATE_TABLE_ANIMAL);
        init(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AnimalsProvider.TABLE_NAME);
        onCreate(db);
    }

    public void init(SQLiteDatabase sqLiteDatabase) {

        //Mammals
        Animal animal = new Animal("Fox", "Mammal", "20", "2", "Male", "https://c1.staticflickr.com/1/618/33303366972_87bc5f65d8_b.jpg");
        Animal animal1 = new Animal("Panda", "Mammal", "350", "10", "Male", "https://media.treehugger.com/assets/images/2017/03/giantpanda.jpg.860x0_q70_crop-scale.jpg");
        Animal animal2 = new Animal("Grizzly Bear", "Mammal", "500", "8", "Female", "https://upload.wikimedia.org/wikipedia/commons/e/e2/Grizzlybear55.jpg");
        Animal animal3 = new Animal("Elephant", "Mammal", "1200", "12", "Male", "http://assets.worldwildlife.org/photos/11048/images/story_full_width/rsz_namibia_will_burrard_lucas_wwf_us_2.jpg?1461768823");


        //Reptiles
        Animal animal4 = new Animal("Crocodile", "Reptile", "320", "60", "Male", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Nile_crocodile_head.jpg/1200px-Nile_crocodile_head.jpg");
        Animal animal5 = new Animal("Snake", "Reptile", "10", "22", "Female", "http://ichef.bbci.co.uk/wwfeatures/wm/live/1280_640/images/live/p0/35/tj/p035tj2j.jpg");
        Animal animal6 = new Animal("Frog", "Reptile", "5", "4", "Female", "https://upload.wikimedia.org/wikipedia/commons/5/55/Atelopus_zeteki1.jpg");

        //Birds
        Animal animal7 = new Animal("Bluejay", "Bird", "4", "1", "Female", "https://upload.wikimedia.org/wikipedia/commons/0/04/Cyanocitta-cristata-004.jpg");
        Animal animal8 = new Animal("Eagle", "Bird", "75", "10", "Male", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/19/%C3%81guila_calva.jpg/1200px-%C3%81guila_calva.jpg");
        Animal animal9 = new Animal("Seahawk", "Bird", "45", "12", "Male", "http://b50ym1n8ryw31pmkr4671ui1c64.wpengine.netdna-cdn.com/wp-content/blogs.dir/11/files/2014/01/Osprey-Christopher_Beasley.jpg");
        Animal animal10 = new Animal("Sparrow", "Bird", "12", "5", "Female", "https://upload.wikimedia.org/wikipedia/commons/f/f5/House_Sparrow_mar08.jpg");

        List<Animal> list = new ArrayList<>();
        list.add(animal);
        list.add(animal1);
        list.add(animal2);
        list.add(animal3);
        list.add(animal4);
        list.add(animal5);
        list.add(animal6);
        list.add(animal7);
        list.add(animal8);
        list.add(animal9);
        list.add(animal10);

        for (Animal a : list) {
            ContentValues contentValues = new ContentValues();
            //contentValues.put(LocalDataContract.Animal.ID,a.getId());
            contentValues.put(LocalDataContract.Animal.NAME, a.getName());
            contentValues.put(LocalDataContract.Animal.CATEGORY, a.getCategory());
            contentValues.put(LocalDataContract.Animal.WEIGHT, a.getWeight());
            contentValues.put(LocalDataContract.Animal.AGE, a.getAge());
            contentValues.put(LocalDataContract.Animal.GENDER, a.getGender());
            contentValues.put(LocalDataContract.Animal.IMAGE, a.getImage());

            sqLiteDatabase.insert(LocalDataContract.TABLE_NAME, null, contentValues);
        }
        //sqLiteDatabase.close();
    }
}
