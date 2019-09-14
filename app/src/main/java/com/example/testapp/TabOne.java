package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TabOne extends Fragment {
   private RecyclerView recyclerView;
   private ArrayList<ListItems> listItems;
   NewsAdapter newsAdapter;
   private static final String REQUEST_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=bfdf3e0e5847437facbf4092ba190098";


   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.fragment_1, container, false);
      recyclerView = v.findViewById(R.id.recycler_view);
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      listItems = new ArrayList<>();
      recyclerView.setAdapter(newsAdapter);
      loadRecyclerViewData();
      return v;
   }

   private void loadRecyclerViewData() {

      final ProgressBar progressBar = new ProgressBar(getContext());
      progressBar.setVisibility(View.VISIBLE);

      StringRequest stringRequest = new StringRequest(Request.Method.GET, REQUEST_URL, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {

            try {
               JSONObject baseobject = new JSONObject(response);
               JSONArray articles = baseobject.getJSONArray("articles");
               for (int i = 0; i < articles.length(); i++) {
                  JSONObject jsonObject = articles.getJSONObject(i);
                  ListItems listItem =new ListItems(jsonObject.getString("title"),
                          jsonObject.getString("description"),
                          jsonObject.getString("urlToImage"),
                          jsonObject.getString("url"));
                  progressBar.setVisibility(View.GONE);
                  listItems.add(listItem);

               }

               NewsAdapter newsAdapter = new NewsAdapter(listItems,getActivity());
               recyclerView.setAdapter(newsAdapter);
            } catch (JSONException e) {
               e.printStackTrace();
            }
         }
      }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {

         }
      });

      RequestQueue requestQueue = Volley.newRequestQueue(getContext());
      requestQueue.add(stringRequest);
   }
}
