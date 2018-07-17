package com.mobileapps.training.daily1;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mobileapps.training.daily1.model.Animal;

import java.util.ArrayList;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: Sending list of animals as broadcast ");
        ArrayList<Animal> list = init();

        Intent intent1 = new Intent(MainActivity.ANIMAL_LIST);
        intent1.putParcelableArrayListExtra("animalList",list);
        sendBroadcast(intent1);
    }

    private ArrayList<Animal> init(){

            //Mammals
            Animal animal = new Animal("1","Fox", "Mammal","20","2","Male","https://c1.staticflickr.com/1/618/33303366972_87bc5f65d8_b.jpg");
            Animal animal1 = new Animal("2","Panda", "Mammal", "350", "10", "Male","https://media.treehugger.com/assets/images/2017/03/giantpanda.jpg.860x0_q70_crop-scale.jpg");
            Animal animal2 = new Animal("3","Grizzly Bear", "Mammal", "500", "8", "Female","https://upload.wikimedia.org/wikipedia/commons/e/e2/Grizzlybear55.jpg");
            Animal animal3 = new Animal("4","Elephant", "Mammal", "1200", "12","Male","http://assets.worldwildlife.org/photos/11048/images/story_full_width/rsz_namibia_will_burrard_lucas_wwf_us_2.jpg?1461768823");


            //Reptiles
            Animal animal4 = new Animal("5","Crocodile","Reptile", "320", "60", "Male","https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Nile_crocodile_head.jpg/1200px-Nile_crocodile_head.jpg");
            Animal animal5 = new Animal("6","Snake", "Reptile","10", "22","Female","http://ichef.bbci.co.uk/wwfeatures/wm/live/1280_640/images/live/p0/35/tj/p035tj2j.jpg");
            Animal animal6 = new Animal("7","Frog", "Reptile","5","4","Female","https://upload.wikimedia.org/wikipedia/commons/5/55/Atelopus_zeteki1.jpg");

            //Birds
            Animal animal7 = new Animal("8","Bluejay", "Bird","4","1","Female","https://upload.wikimedia.org/wikipedia/commons/0/04/Cyanocitta-cristata-004.jpg");
            Animal animal8 = new Animal("9","Eagle","Bird", "75","10","Male","https://upload.wikimedia.org/wikipedia/commons/thumb/1/19/%C3%81guila_calva.jpg/1200px-%C3%81guila_calva.jpg");
            Animal animal9 = new Animal("10","Seahawk", "Bird","45","12","Male","http://b50ym1n8ryw31pmkr4671ui1c64.wpengine.netdna-cdn.com/wp-content/blogs.dir/11/files/2014/01/Osprey-Christopher_Beasley.jpg");
            Animal animal10 = new Animal("11","Sparrow", "Bird","12","5","Female","https://upload.wikimedia.org/wikipedia/commons/f/f5/House_Sparrow_mar08.jpg");

            ArrayList<Animal> list = new ArrayList<>();
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

            return list;
    }
}
