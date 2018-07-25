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

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class TwitterActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "TwitterActivity";

    private TextView tvTwitterStatus;
    private TextView tvTwitterDetails;
    private Button btnGoogle;
    private Button btnSignOutTwitter;
    private FirebaseAuth mAuth;
    private TwitterLoginButton mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: ");

        TwitterAuthConfig authConfig =  new TwitterAuthConfig(
                getString(R.string.twitter_consumer_key),
                getString(R.string.twitter_consumer_secret));

        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .twitterAuthConfig(authConfig)
                .build();

        Twitter.initialize(twitterConfig);

        setContentView(R.layout.activity_twitter);

        tvTwitterStatus = findViewById(R.id.tvTwitterStatus);
        tvTwitterDetails = findViewById(R.id.tvTwitterDetails);

        btnGoogle = findViewById(R.id.btnGoogleBack);
        btnSignOutTwitter = findViewById(R.id.sign_out_twitter_button);

        btnGoogle.setOnClickListener(this);
        btnSignOutTwitter.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mLoginButton = findViewById(R.id.button_twitter_login);
        mLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, "twitterLogin:success" + result);
                handleTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.w(TAG, "twitterLogin:failure", exception);
                updateView(null);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateView(currentUser);
    }


    private void updateView(FirebaseUser currentUser) {
        if(currentUser!=null){ //user signed

            tvTwitterStatus.setText("Name: " + currentUser.getDisplayName());
            tvTwitterDetails.setText("UID: " + currentUser.getUid());

            mLoginButton.setVisibility(View.GONE);
            btnGoogle.setVisibility(View.GONE);
            btnSignOutTwitter.setVisibility(View.VISIBLE);
        }else{
            tvTwitterStatus.setText("Signed out");
            tvTwitterDetails.setText(null);

            mLoginButton.setVisibility(View.VISIBLE);
            btnGoogle.setVisibility(View.VISIBLE);
            btnSignOutTwitter.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the Twitter login button.
        mLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    private void handleTwitterSession(TwitterSession session) {
        Log.d(TAG, "handleTwitterSession:" + session);


        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

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
                            Toast.makeText(TwitterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateView(null);
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_out_twitter_button:
                mAuth.signOut();
                TwitterCore.getInstance().getSessionManager().clearActiveSession();
                updateView(null);
                break;
            case R.id.btnGoogleBack:
                Intent intent = new Intent(TwitterActivity.this, GoogleActivity.class);
                startActivity(intent);
                break;
        }
    }
}
