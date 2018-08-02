package com.mobileapps.training.daily3;

import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentProvider;
import android.test.mock.MockContentResolver;
import android.util.Log;

import com.mobileapps.training.daily3.Animal.Animal;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;


@RunWith(AndroidJUnit4.class)
public class MyContentProviderTest extends ProviderTestCase2<AnimalsProvider> {

    private static final String TAG = "MyContentProviderTest";

    private static MockContentResolver resolve;

    public MyContentProviderTest() {
        super(AnimalsProvider.class, "com.mobileapps.training");
    }


    @Override
    public MockContentResolver getMockContentResolver () {
        Log.d(TAG, "getMockContentResolver: ");
        MockContentResolver mockContentResolver =  new MockContentResolver();
        AnimalsProvider animalsProvider = new AnimalsProvider();
        animalsProvider.attachInfo(getMockContext(),null);
        mockContentResolver.addProvider("com.mobileapps.training", animalsProvider);
        return mockContentResolver;
    }

    @Before
    @Override
    public void setUp() throws Exception {
        Log.d(TAG, "setUp: ");
        super.setUp();
        setContext(InstrumentationRegistry.getTargetContext());
        resolve = this.getMockContentResolver();
    }

    @Override
    public void tearDown() throws Exception {
            super.tearDown();
    }

    @Test
    public void testGetAnimals(){
        Cursor cursor = resolve.query(Uri.parse("content://com.mobileapps.training/animal"),null,null,null,null,null);
        assertNotNull(cursor);
        List<Animal> list = getList(cursor);
        assertEquals(11,list.size());
    }

    public List<Animal> getList(Cursor cursor){
        List<Animal> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Animal animal = new Animal();
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                String weight = cursor.getString(cursor.getColumnIndex("weight"));
                String age = cursor.getString(cursor.getColumnIndex("age"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                animal.setName(name);
                animal.setCategory(category);
                animal.setWeight(weight);
                animal.setAge(age);
                animal.setGender(gender);
                animal.setImage(image);
                list.add(animal);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
