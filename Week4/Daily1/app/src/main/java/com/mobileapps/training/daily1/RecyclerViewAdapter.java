package com.mobileapps.training.daily1;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobileapps.training.daily1.model.Animal;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private List<Animal> animalList;
    private Context context;

    public RecyclerViewAdapter(List<Animal> animalList, Context context) {
        this.animalList = animalList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(context).asBitmap().load(animalList.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(animalList.get(position).getName());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked RecyclerView Item: " + animalList.get(position).getName());
                Animal animal = animalList.get(position);
                //Intent intent = new Intent(context,AnimalDetailsActivity.class);
                //intent.putExtra("animal",animalList.get(position));
                //context.startActivity(intent);
                scheduleNotification(getNotification("Animal "+ animal.getName()), 5000);
            }
        });
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }


    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_alarm_add_black_24dp);
        return builder.build();
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cardImage);
            textView = itemView.findViewById(R.id.cardTitle);
            relativeLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
