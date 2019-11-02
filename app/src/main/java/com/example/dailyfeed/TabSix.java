package com.example.dailyfeed;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class TabSix extends Fragment {
    VideoView videoView1,videoView2,videoView3;


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_6, container, false);

        videoView2 = (VideoView) rootView.findViewById(R.id.video2);
        MediaController mc1 = new MediaController(getContext());
        String path1 = "android.resource://" + getContext().getPackageName() + "/" + R.raw.videotwo;
        videoView2.setVideoURI(Uri.parse(path1));
        videoView2.setMediaController(mc1);


        videoView3 = (VideoView) rootView.findViewById(R.id.video3);
        MediaController mc2 = new MediaController(getContext());
        String path2 = "android.resource://" + getContext().getPackageName() + "/" + R.raw.videothree;
        videoView3.setVideoURI(Uri.parse(path2));
        videoView3.setMediaController(mc2);
        return rootView;
    }
}