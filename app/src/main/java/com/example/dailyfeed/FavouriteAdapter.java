package com.example.dailyfeed;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyfeed.Database.DailyFeedModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder>{

    private final Context context;
    private ArrayList<FavouriteItems> favData;

    public FavouriteAdapter(ArrayList<FavouriteItems>favData, Context context){
        this.favData = favData;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_two,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  FavouriteItems favouriteItems = favData.get(position);

        holder.titleTabTwo.setText(favouriteItems.getTitle());
        holder.descTabTwo.setText(favouriteItems.getDescription());
        holder.publishedAtTabTwo.setText(favouriteItems.getPublishedAt());
        Picasso.get().load(favouriteItems.getImageUrl()).placeholder(R.drawable.user_placeholder)
                .into(holder.imageNewsTabTwo);

        holder.btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ExploreNews.class);
                i.putExtra("newsUrl", favouriteItems.getDetailUrl());
                v.getContext().startActivity(i);
            }
        });

        holder.shareTabTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, favouriteItems.getDetailUrl()+ "\n\nDownload DailyFeed for more exciting and instant news!");
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTabTwo,descTabTwo,publishedAtTabTwo;
        Button btnExplore;
        ImageButton shareTabTwo;
        ImageView imageNewsTabTwo;
        LinearLayout layoutTabTwo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTabTwo = itemView.findViewById(R.id.titleTextView_tab_two);
            descTabTwo = itemView.findViewById(R.id.descTextView_tab_two);
            publishedAtTabTwo = itemView.findViewById(R.id.textViewPublishedAt_tab_two);
            btnExplore = itemView.findViewById(R.id.buttonExplore_tab_two);
            shareTabTwo = itemView.findViewById(R.id.sharebtn_tab_two);
            imageNewsTabTwo = itemView.findViewById(R.id.image_news_tab_two);
            layoutTabTwo = itemView.findViewById(R.id.layout_tab_two);

        }
    }
}