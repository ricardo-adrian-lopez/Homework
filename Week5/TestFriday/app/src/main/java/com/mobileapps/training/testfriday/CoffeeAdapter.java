package com.mobileapps.training.testfriday;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobileapps.training.testfriday.model.Coffee;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CoffeeAdapter extends ArrayAdapter<Coffee> {

    private final int resource;
    Context context;

    public CoffeeAdapter(Context context, int resource, ArrayList<Coffee> list) {
        super(context,resource,list);
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String title = getItem(position).getName();
        String desc = getItem(position).getDesc();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent,false);

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvDesc = convertView.findViewById(R.id.tvDescription);
        ImageView ivPicture = convertView.findViewById(R.id.ivPicture);

        tvTitle.setText(title);
        tvDesc.setText(desc);
        Glide.with(convertView).load(getItem(position).getImageUrl()).into(ivPicture);
        return convertView;

    }
}
