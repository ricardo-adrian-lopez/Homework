package com.mobileapps.training.week3daily4;


import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int JOD_ID_NORMAL_SERVICE = 10;

    Button btnStart;
    Button btnStop;
    Button btnAlarm;
    Button btnJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnAlarm = findViewById(R.id.btnAlarm);
        btnJob = findViewById(R.id.btnJob);

        //ForegroundService

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(MainActivity.this, SoundService.class);
                startService(intentStart);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStop = new Intent(MainActivity.this, SoundService.class);
                stopService(intentStop);
            }
        });

        // AlarmManager and JobScheduler

        btnJob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Job Clicked");
                ComponentName componentName = new ComponentName(getApplicationContext(),MyJob.class);

                JobInfo.Builder builder = new JobInfo.Builder(JOD_ID_NORMAL_SERVICE, componentName);
                builder.setMinimumLatency(5000);
                JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                jobScheduler.schedule(builder.build());
            }
        });


    }
}
