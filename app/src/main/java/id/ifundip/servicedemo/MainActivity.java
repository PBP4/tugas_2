package id.ifundip.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    Button playButton, stopButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playButtonHandler(View view){
        startService(new Intent(this, SoundService.class));
    }

    public void stopButtonHandler(View view){
        stopService(new Intent(this, SoundService.class));
    }

}