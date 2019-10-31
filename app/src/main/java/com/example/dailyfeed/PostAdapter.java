package com.example.dailyfeed;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    private ArrayList<PostItems> alpostItems;
    Context context;

    public PostAdapter(ArrayList<PostItems> alpostItems, Context context) {
        this.alpostItems = alpostItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_post, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostItems postItems=alpostItems.get(position);
        holder.postCaption.setText(postItems.getCaption());
        File newImageFile=new File(postItems.getImage());
        Log.i("YAY",""+postItems.getImage());
        Picasso.get().load(Uri.fromFile(newImageFile)).into(holder.postImage);



    }

    @Override
    public int getItemCount() {
        return alpostItems.size();
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView postImage;
        TextView postCaption;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postCaption = itemView.findViewById(R.id.captionTextView);
            postImage = itemView.findViewById(R.id.image_news_post);

        }
    }
}
