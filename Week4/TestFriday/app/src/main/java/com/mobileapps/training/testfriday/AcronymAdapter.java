package com.mobileapps.training.testfriday;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobileapps.training.testfriday.model.Lf;

import java.util.ArrayList;

public class AcronymAdapter extends ArrayAdapter<Lf> {

    private static final String TAG = "AcronymAdapter";

    public AcronymAdapter(Context context, int resource, ArrayList<Lf> list) {
        super(context, resource,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.acronym_list_item,null);
        }

        Log.d(TAG, "getView: Initializing textviews");
        TextView tvLf = convertView.findViewById(R.id.tvLf);
        TextView tvFreq = convertView.findViewById(R.id.tvFreq);
        TextView tvSince = convertView.findViewById(R.id.tvSince);

        Lf lf = getItem(position);

        Log.d(TAG, "getView: Set text views");
        tvLf.setText(lf.getLf());
        tvFreq.setText(""+lf.getFreq());
        tvSince.setText(""+lf.getSince());

        Log.d(TAG, "getView: Return adapter");
        return convertView;
    }
}
