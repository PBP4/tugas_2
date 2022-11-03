package com.example.tutorial_service2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MusicPlayer extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        textView = findViewById(R.id.txtTitle);
        start = findViewById(R.id.buttonStart);
        stop = findViewById(R.id.buttonStop);

        String title = getIntent().getStringExtra("clickedsong");
        textView.setText(title);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(MusicPlayer.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View view) {
        String title = getIntent().getStringExtra("clickedsong");
        if(view == start){
            Intent intent = new Intent(getApplicationContext(), MusicService.class);
            intent.putExtra("song",title);
            startService(intent);
//            startService(new Intent(this, MusicService.class));
        }
        else if(view == stop){
            Intent intent = new Intent(getApplicationContext(), MusicService.class);
            stopService(intent);
//            stopService(new Intent(this, MusicService.class));
        }
    }
}