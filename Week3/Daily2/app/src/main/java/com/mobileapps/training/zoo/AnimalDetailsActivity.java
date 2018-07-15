package com.mobileapps.training.zoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobileapps.training.zoo.data.LocalDataSource;
import com.mobileapps.training.zoo.model.Animal;

import org.w3c.dom.Text;

public class AnimalDetailsActivity extends AppCompatActivity {

    private static final String TAG = "AnimalDetailsActivity";
    private TextView tvName;
    private TextView tvCategory;
    private TextView tvAge;
    private TextView tvGender;
    private TextView tvWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);
        Log.d(TAG, "onCreate: started");
        Animal animal = getIntent().getParcelableExtra("animal");
        animal = new LocalDataSource(this).getAnimal(animal.getId());
        Log.d(TAG, "onCreate: Animal: " + animal);
        tvName = findViewById(R.id.tvName);
        tvCategory = findViewById(R.id.tvCategory);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);
        tvWeight = findViewById(R.id.tvWeight);

        ImageView imageView = findViewById(R.id.ivImage);
        Glide.with(this).asBitmap().load(animal.getImage()).into(imageView);

        tvName.setText(animal.getName());
        tvCategory.setText("Belongs to: " + animal.getCategory());
        tvAge.setText("Age: " + animal.getAge());
        tvGender.setText("Geneder: " + animal.getGender());
        tvWeight.setText("Weight: " + animal.getWeight());
    }
}
