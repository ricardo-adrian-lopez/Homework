package com.mobileapps.training.daily2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mobileapps.training.daily2.custom.LoginCompositeView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";


    private EditText mName;
    private EditText mPassword;
    private Button btnLogin;
    private CheckBox mCheckbox;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = findViewById(R.id.etName);
        mPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        mCheckbox = findViewById(R.id.checkboxR);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        checkSharedPreferences();
    }

    private void checkSharedPreferences() {
        Log.d(TAG, "checkSharedPreferences: Checking preferences");
        String checkbox = mPreferences.getString(this.getString(R.string.checkbox), "FALSE");
        String name = mPreferences.getString(this.getString(R.string.etname), "");
        String password = mPreferences.getString(this.getString(R.string.etpassword), "");

        mName.setText(name);
        mPassword.setText(password);
        if (checkbox.equals("TRUE")) {
            mCheckbox.setChecked(true);
        } else {
            mCheckbox.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        if (mCheckbox.isChecked()) { //We save the information on user request
            Log.d(TAG, "onClick: Saving login preferences");
            mEditor.putString(this.getString(R.string.checkbox), "TRUE");
            mEditor.putString(this.getString(R.string.etname), mName.getText().toString());
            mEditor.putString(this.getString(R.string.etpassword), mPassword.getText().toString());
            mEditor.commit();
        } else {
            Log.d(TAG, "onClick: Not saving login information");
            mEditor.putString(this.getString(R.string.checkbox), "FALSE");
            mEditor.commit();
        }
    }
}
