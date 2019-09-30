package com.example.dailyfeed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyfeed.Database.DailyFeedModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {


    private List<ListItems> listItems;
    ArrayList<FavouriteItems> favData;
    private Context context;
    int checked = 0;

    public NewsAdapter(List<ListItems> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_one, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ListItems listItem = listItems.get(position);

        holder.heading.setText(listItem.getmHeading());
        holder.description.setText(listItem.getmDescription());
        holder.publishedAt.setText(listItem.getmPublishedAt());

        Picasso.get()
                .load(listItem.getmImageURL())
                .placeholder(R.drawable.user_placeholder)
                .into(holder.newsImage);


        holder.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ExploreNews.class);
                i.putExtra("newsUrl", listItems.get(position).getmDetailURL());
                v.getContext().startActivity(i);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, listItems.get(position).getmDetailURL() + "\n\nDownload DailyFeed for more exciting and instant news!");
                v.getContext().startActivity(i);
            }
        });
        ListItems like = listItems.get(position);
        final String url = like.getmDetailURL();
        final String desc = like.getmDescription();
        final String title = like.getmHeading();
        final String imgUrl = like.getmImageURL();
        final String date = like.getmPublishedAt();


        DailyFeedModel.getInstance(context);
        DailyFeedModel.open();
        ArrayList<String> alid = DailyFeedModel.getAllIds();
        if (alid.contains(title)) {
            holder.heart.setChecked(true);
            holder.heart.setBackgroundResource(R.drawable.like_red);
        } else {
            holder.heart.setChecked(false);
            holder.heart.setBackgroundResource(R.drawable.unlike_borderless);

        }


        DailyFeedModel.close();

        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (holder.heart.isChecked()) {
                    holder.heart.setChecked(true);


                    checked = 1;
                    DailyFeedModel.open();
                    DailyFeedModel.insert(title, desc, imgUrl, url, date, checked);

                    DailyFeedModel.close();
                    Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show();
                } else {
                    holder.heart.setChecked(false);
                    checked = 0;
                    Toast.makeText(context, "Removed from favourites", Toast.LENGTH_SHORT).show();
                }

            /*DailyFeedModel.open();
            favData = DailyFeedModel.showFavourites();
            DailyFeedModel.close();
            FavouriteItems favouriteItems = favData.get(position);
            if (favouriteItems.getChecked()==1){
               holder.heart.setChecked(true);
               holder.heart.setBackgroundResource(R.drawable.like_red);

            }
            else {
               holder.heart.setChecked(false);
               holder.heart.setBackgroundResource(R.drawable.unlike_borderless);
            }*/


            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView heading, description, publishedAt;
        public Button explore;
        public CheckBox heart;
        public ImageView newsImage;
        public LinearLayout linearLayout;
        ImageButton share;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.titleTextView);
            description = itemView.findViewById(R.id.descTextView);
            share = itemView.findViewById(R.id.sharebtn);
            explore = itemView.findViewById(R.id.buttonExplore);
            heart = itemView.findViewById(R.id.likeCheckBox);
            newsImage = itemView.findViewById(R.id.image_news);
            linearLayout = itemView.findViewById(R.id.layout);
            publishedAt = itemView.findViewById(R.id.textViewPublishedAt);


        }
    }
}
