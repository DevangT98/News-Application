package com.example.dailyfeed;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.dailyfeed.Database.DailyFeedModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TabTwo extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FavouriteItems> favData;
    FavouriteAdapter favouriteAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        recyclerView = v.findViewById(R.id.recycler_fragment_two);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DailyFeedModel.open();
        favData = DailyFeedModel.showFavourites();
        DailyFeedModel.close();
        favouriteAdapter = new FavouriteAdapter(favData, getActivity());
        recyclerView.setAdapter(favouriteAdapter);

      return v;
    }


}
