package com.mobileapps.training.celebrities.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobileapps.training.celebrities.R;
import com.mobileapps.training.celebrities.model.Celebrity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends ListFragment {

    public static final String TAG = "FragmentA";

    private ListView listView;
    List<Celebrity> celebrityList;
    OnCelebritySelectedListner mListener;


    public FragmentA() {
        // Required empty public constructor
    }

    public interface OnCelebritySelectedListner{
        public void onCelebritySelected(Celebrity celebrity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: Inflating fragmentA");
        return inflater.inflate(R.layout.fragment_list_celebrities, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: Loading listview");
        listView = getActivity().findViewById(android.R.id.list);
        celebrityList = initListCelebrities();
        List<String> celebritiNamesList =  listCelebritiesNames(celebrityList);
        Log.d(TAG, "onActivityCreated: Celebrities: " + celebritiNamesList);
        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,celebritiNamesList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d(TAG, "onListItemClick:" +celebrityList.get(position).getName());
        mListener.onCelebritySelected(celebrityList.get(position));
    }

    private List<String> listCelebritiesNames(List<Celebrity> list){
        List<String> stringList = new ArrayList<>();
        for(Celebrity celebrity: list) {
            stringList.add(celebrity.getName());
        }
        return stringList;
    }


    private List<Celebrity> initListCelebrities(){
        Log.d(TAG, "initListCelebrities: Loading celebrities");
        List<Celebrity> list = new ArrayList<>();

        Celebrity celebrity = new Celebrity();
        celebrity.setName("Johnny Depp");
        celebrity.setAge("55");
        celebrity.setResidence("Los Angeles, California");
        celebrity.setDescription("John Christopher Depp II (born June 9, 1963) is an American actor, producer, and musician. He has been nominated for three Academy Awards and has won the Golden Globe and Screen Actors Guild Awards for Best Actor.");
        celebrity.setImage("johnny_depp");

        list.add(celebrity);

        Celebrity celebrity1 = new Celebrity();
        celebrity1.setName("Scarlett Johansson");
        celebrity1.setAge("33");
        celebrity1.setResidence("New York City, New York");
        celebrity1.setDescription("Scarlett Ingrid Johansson (born November 22, 1984) is an American actress and singer. She was among the world's highest-paid actresses from 2014 to 2016, has made multiple appearances in the Forbes Celebrity 100, and has a star on the Hollywood Walk of Fame.");
        celebrity1.setImage("scarlett");
        list.add(celebrity1);
        return list;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (OnCelebritySelectedListner)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnCelebritySelectedListner");
        }
    }
}
