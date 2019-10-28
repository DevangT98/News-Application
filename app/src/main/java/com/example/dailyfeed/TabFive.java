package com.example.dailyfeed;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import static com.example.dailyfeed.R.raw.tune;

public class TabFive extends Fragment {
    MediaPlayer mp;
    ImageView btnplay, btnstop;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_5, container, false);
        btnplay = rootView.findViewById(R.id.playButton);
        btnstop = rootView.findViewById(R.id.stopButton);

        mp = new MediaPlayer();
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(getContext(), tune);
                mp.start();
                if (mp.isPlaying()){
                    btnplay.setEnabled(false);
                }

            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnplay.setEnabled(true);
                mp.stop();
            }
        });


        return rootView;

    }
}