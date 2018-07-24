package com.mobileapps.training.firebaseauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FacebookActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "FacebookActivity";

    private FirebaseAuth mAuth;
    private TextView tvFacebookDetail;
    private TextView tvFacebookStatus;
    private CallbackManager mCallbackManager;
    private LoginButton loginButton;
    private Button btnSignOutFacebook;
    private Button btnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        Log.d(TAG, "onCreate: ");

        mAuth = FirebaseAuth.getInstance();

        tvFacebookStatus = findViewById(R.id.tvFacebookStatus);
        tvFacebookDetail = findViewById(R.id.tvFacebookDetail);

        btnSignOutFacebook = findViewById(R.id.sign_out_facebook_button);
        btnGoogle = findViewById(R.id.btnGoogle);

        btnSignOutFacebook.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);

        mCallbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button_facebook);
        loginButton.setReadPermissions("email","public_profile");

        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess: ");
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: ");
                updateView(null);
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError: ");
                updateView(null);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        updateView(user);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateView(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(FacebookActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateView(null);
                        }

                    }
                });
    }

    private void updateView(FirebaseUser user) {
        if(user!=null){ //user signed

            tvFacebookStatus.setText("Name: " + user.getDisplayName());
            tvFacebookDetail.setText("UID: " + user.getUid());

            loginButton.setVisibility(View.GONE);
            btnGoogle.setVisibility(View.GONE);
            btnSignOutFacebook.setVisibility(View.VISIBLE);
        }else{
            tvFacebookStatus.setText("Signed out");
            tvFacebookDetail.setText(null);

            loginButton.setVisibility(View.VISIBLE);
            btnGoogle.setVisibility(View.VISIBLE);
            btnSignOutFacebook.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_out_facebook_button:
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            updateView(null);
            break;
            case R.id.btnGoogle:
                Intent intent = new Intent(FacebookActivity.this, GoogleActivity.class);
                startActivity(intent);
                break;
        }
    }
}
