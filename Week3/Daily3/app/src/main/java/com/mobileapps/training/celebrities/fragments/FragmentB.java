package com.mobileapps.training.celebrities.fragments;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileapps.training.celebrities.R;
import com.mobileapps.training.celebrities.model.Celebrity;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {
    public static final String TAG = "FragmentB";
    private TextView nameCelebrity;
    private ImageView imageCelebrity;
    private TextView descCelebrity;
    private TextView ageCelebrity;
    private TextView residenceCelebrity;

    public FragmentB() {
        // Required empty public constructor
    }

    public static FragmentB newInstance(Celebrity celebrity){
        FragmentB fragmentB = new FragmentB();
        Bundle bundle = new Bundle();
        bundle.putSerializable("celebrity", celebrity);
        fragmentB.setArguments(bundle);
        return fragmentB;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: FragmentB started");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_b, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Celebrity celebrity = (Celebrity) this.getArguments().getSerializable("celebrity");
        nameCelebrity = getActivity().findViewById(R.id.nameCelebrity);
        imageCelebrity = getActivity().findViewById(R.id.imageCelebrity);
        descCelebrity = getActivity().findViewById(R.id.descCelebrity);
        ageCelebrity = getActivity().findViewById(R.id.ageCelebrity);
        residenceCelebrity = getActivity().findViewById(R.id.residencyCelebrity);
        putData(celebrity);
    }

    public void onUpdate(Celebrity celebrity){
        putData(celebrity);
    }

    private void putData(Celebrity celebrity){
        nameCelebrity.setText(celebrity.getName());
        descCelebrity.setText(celebrity.getDescription());
        ageCelebrity.setText("Age: " +celebrity.getAge());
        residenceCelebrity.setText("Residency: " + celebrity.getResidence());
        imageCelebrity.setImageResource(getResources().getIdentifier("@drawable/"+celebrity.getImage(),null,getActivity().getPackageName()));
    }
}
