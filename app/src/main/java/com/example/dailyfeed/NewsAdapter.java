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
   private Context context;

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

/*
     holder.linearLayout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listItem.getmDetailURL()));
            context.startActivity(intent);
         }
      });
*/
      holder.explore.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ExploreNews.class);
            i.putExtra("newsUrl", listItems.get(position).getmDetailURL());
//            Log.i("DEV",listItems.get(position).getmSourceId());
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
      holder.heart.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if (holder.heart.isChecked()) {
              ListItems like =listItems.get(position);
               String desc = like.getmDescription();
               String title = like.getmHeading();
               String imgUrl = like.getmImageURL();
               String date =like.getmPublishedAt();
               String url =like.getmDetailURL();

               holder.heart.setChecked(true);

               DailyFeedModel.open();
               DailyFeedModel.insert(position,title,desc,imgUrl,url,date);
               DailyFeedModel.close();
               Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show();
            } else {
               holder.heart.setChecked(false);
              /* editor.putBoolean("unliked",holder.heart.isChecked());
               editor.commit();*/
               Toast.makeText(context, "Removed from favourites", Toast.LENGTH_SHORT).show();
            }
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
