package com.mobileapps.training.week2daily4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobileapps.training.week2daily4.model.Person;

import java.util.ArrayList;
import java.util.List;

public class AddPerson extends BaseActivity {

    EditText firstname;
    EditText lastname;
    EditText company;
    Button btnSavePerson;
    Button btnNavigateToList;
    Person person;
    ArrayList<Person> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        firstname = findViewById(R.id.editTextFirstname);
        lastname = findViewById(R.id.editTextLastname);
        company = findViewById(R.id.editTextCompany);
        btnSavePerson = findViewById(R.id.btnAddPerson);
        btnNavigateToList = findViewById(R.id.btnListPerson);
        list =  new ArrayList<>();

        btnSavePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(firstname.getText().toString()) && !"".equals(lastname.getText().toString()) && !"".equals(company.getText().toString())){
                    person = new Person();
                    person.setFirstname(firstname.getText().toString());
                    person.setLastname(lastname.getText().toString());
                    person.setCompanyName(company.getText().toString());
                    list.add(person);

                    Toast.makeText(getApplicationContext(),"Saved...", Toast.LENGTH_SHORT).show();

                    firstname.setText("");
                    lastname.setText("");
                    company.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"Input all the information and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNavigateToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(),ListPerson.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("personList", list);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
