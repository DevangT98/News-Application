package com.example.dailyfeed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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
   public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
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
            Intent i = new Intent(v.getContext(),ExploreNews.class);
            i.putExtra("newsUrl",listItems.get(position).getmDetailURL());
            v.getContext().startActivity(i);
         }
      });

   }

   @Override
   public int getItemCount() {
      return listItems.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {

      public TextView heading, description,publishedAt;
      public Button share, explore;
      public ImageView newsImage;
      public LinearLayout linearLayout;


      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         heading = itemView.findViewById(R.id.titleTextView);
         description = itemView.findViewById(R.id.descTextView);
//         share = itemView.findViewById(R.id.buttonShare);
         explore = itemView.findViewById(R.id.buttonExplore);
         newsImage = itemView.findViewById(R.id.image_news);
         linearLayout = itemView.findViewById(R.id.layout);
         publishedAt = itemView.findViewById(R.id.textViewPublishedAt);

      }
   }
}
