package com.mobileapps.training.week2daily4;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class CallNumber extends BaseActivity {
    EditText editText;
    Button buttonPhone;

    private static final String TAG = "CallNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_number);

        editText = findViewById(R.id.editTextPhone);
        buttonPhone = findViewById(R.id.btnPhoneCall);

        buttonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals("")){
                    String phoneNumber = "tel:"+ editText.getText();
                    Uri number = Uri.parse(phoneNumber);
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

                    PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent,
                            PackageManager.MATCH_DEFAULT_ONLY);
                    boolean isIntentSafe = activities.size() > 0;
                    if(isIntentSafe){
                        startActivity(callIntent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Your device is not capable of performing this action", LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Input the phone", LENGTH_SHORT).show();
                }
            }
        });
    }
}
