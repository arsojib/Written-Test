package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private ImageView ivPlay, ivRewind, ivForward;
    private final int rewindOrForwardLimit = 10 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_control);
        initialVideoPlayer();
        initialClickListener();
    }

    private void initialVideoPlayer() {
        videoView = findViewById(R.id.videoView);
        ivPlay = findViewById(R.id.play);
        ivRewind = findViewById(R.id.rewind);
        ivForward = findViewById(R.id.forward);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
    }

    private void initialClickListener() {
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    ivPlay.setImageResource(R.drawable.ic_play_24dp);
                } else {
                    videoView.start();
                    ivPlay.setImageResource(R.drawable.ic_pause_24dp);
                }
            }
        });

        ivRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.getCurrentPosition() - rewindOrForwardLimit <= 0) {
                    videoView.seekTo(0);
                } else {
                    videoView.seekTo(videoView.getCurrentPosition() - rewindOrForwardLimit);
                }
            }
        });

        ivForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.getCurrentPosition() + rewindOrForwardLimit >= 0) {
                    videoView.seekTo(videoView.getDuration() - 1000);
                } else {
                    videoView.seekTo(videoView.getCurrentPosition() + rewindOrForwardLimit);
                }
            }
        });
    }

}
