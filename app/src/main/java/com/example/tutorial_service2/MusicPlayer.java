package com.example.tutorial_service2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.SeekBar;

public class MusicPlayer extends AppCompatActivity implements View.OnClickListener {
    TextView textView, txtCurrentTime;
    Button start, stop;
    SeekBar seekBar;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        textView = findViewById(R.id.txtTitle);
        start = findViewById(R.id.buttonStart);
        stop = findViewById(R.id.buttonStop);
        seekBar = findViewById(R.id.seekBar);
        txtCurrentTime = findViewById(R.id.txtCurrentTime);

        String title = getIntent().getStringExtra("clickedsong");
        textView.setText(title);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        stop.setEnabled(false);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    MusicService.player.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MusicPlayer.this, MainActivity.class);
                startActivity(intent);
                Intent intent2 = new Intent(getApplicationContext(), MusicService.class);
                stopService(intent2);
            }
        });
    }

    @Override
    public void onClick(View view) {
        String title = getIntent().getStringExtra("clickedsong");
        if (view == start) {
            Intent intent = new Intent(getApplicationContext(), MusicService.class);
            intent.putExtra("song", title);
            startService(intent);
            MusicPlayer.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (MusicService.player != null) {
                        int mCurrentPosition = MusicService.player.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPosition);
                        txtCurrentTime.setText(String.format("%02d:%02d", mCurrentPosition / 60, mCurrentPosition % 60));
                        if (!(MusicService.player.isPlaying())) {
                            start.setEnabled(true);
                            stop.setEnabled(false);
                        }
                    }
                    mHandler.postDelayed(this, 1000);
                }
            });
            start.setEnabled(false);
            stop.setEnabled(true);
        } else if (view == stop) {
            Intent intent = new Intent(getApplicationContext(), MusicService.class);
            stopService(intent);
            start.setEnabled(true);
            stop.setEnabled(false);
        }
    }
}