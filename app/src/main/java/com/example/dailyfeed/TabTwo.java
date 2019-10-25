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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.dailyfeed.Database.DailyFeedModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TabTwo extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    ArrayList<FavouriteItems> favData;
    FavouriteAdapter favouriteAdapter;
    SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        recyclerView = v.findViewById(R.id.recycler_fragment_two);
        swipeRefreshLayout = v.findViewById(R.id.swipe_tab_two);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.swipe1, R.color.swipe2, R.color.swipe3);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                DailyFeedModel.open();
                favData = DailyFeedModel.showFavourites();
                DailyFeedModel.close();
                favouriteAdapter = new FavouriteAdapter(favData, getActivity());
                recyclerView.setAdapter(favouriteAdapter);
            }
        });

      return v;
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        DailyFeedModel.open();
        favData = DailyFeedModel.showFavourites();
        DailyFeedModel.close();
        favouriteAdapter = new FavouriteAdapter(favData, getActivity());
        recyclerView.setAdapter(favouriteAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }
}
