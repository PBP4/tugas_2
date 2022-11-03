package com.example.tutorial_service2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listview);
        String listviewitem[] = getResources().getStringArray(R.array.songlist);
        ArrayAdapter myAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.songlist));
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String listviewval = listviewitem[i].toString();
                Intent intent = new Intent(getApplicationContext(), MusicPlayer.class);
                intent.putExtra("clickedsong",listviewval);
                startActivity(intent);
            }
        });
    }
}