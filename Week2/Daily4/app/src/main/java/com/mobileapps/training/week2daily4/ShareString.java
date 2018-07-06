package com.mobileapps.training.week2daily4;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class ShareString extends BaseActivity {

    Button btnEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_string);

        btnEvent = findViewById(R.id.btnCalendar);

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2018, 7, 31, 6, 00);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2018, 7, 31, 10, 30);
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.Events.TITLE, "Training class");
                calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Mobile Apps Company");

                PackageManager packageManager =  getPackageManager();
                List<ResolveInfo> activites = packageManager.queryIntentActivities(calendarIntent,PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentSafe = activites.size() > 0;
                if (isIntentSafe){
                    startActivity(calendarIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"Your device is not capable of performing this action", LENGTH_SHORT).show();
                }
            }
        });
    }
}
