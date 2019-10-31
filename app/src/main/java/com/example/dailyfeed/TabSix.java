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
    VideoView videoView;


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_6, container, false);
        videoView = (VideoView) rootView.findViewById(R.id.video);
        MediaController mc = new MediaController(getContext());
        String path = "android.resource://" + getContext().getPackageName() + "/" + R.raw.shark;
        videoView.setVideoURI(Uri.parse(path));
        videoView.setMediaController(mc);
        return rootView;
    }
}