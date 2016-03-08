package com.study.playvideotest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener{

    private VideoView videoView;
    private Button play;
    private Button pause;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        //play.setOnClickListener(this);

        pause = (Button) findViewById(R.id.pause);
        //pause.setOnClickListener(this);

        stop = (Button) findViewById(R.id.stop);
        //stop.setOnClickListener(this);

        videoView = (VideoView) findViewById(R.id.video_view);

        initVideoPath();
    }

    private void initVideoPath() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "movie.3gp");
            Toast.makeText(this, file.getPath().toString(), Toast.LENGTH_LONG).show();
            videoView.setVideoPath(file.getPath());
        } catch (Exception e) {
            //e.printStackTrace();
            Log.d("FUCK", "FUCK");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if(!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.pause:
                if(videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.stop:
                if(videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView != null) {
            videoView.suspend();
        }
    }
}
