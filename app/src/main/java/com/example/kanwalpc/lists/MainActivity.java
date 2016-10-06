package com.example.kanwalpc.lists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView myFavSeasonList;
    int pos;
    private static final String[] myFavSeasonArray={
            "Silicon Valley",
            "Game of Thrones",
            "Big Bang Theory",
            "Prison Break",
            "Citizen Khan",
            "Divinci Demons",
            "Mr. Robot",
            "House of Cards",
            "Sherlock Holmes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFavSeasonList=(ListView)this.findViewById(R.id.list_of_seasons);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,myFavSeasonArray);
        myFavSeasonList.setAdapter(adapter);

        myFavSeasonList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Intent send_intent=new Intent(MainActivity.this,DetailActivity.class);
                int id=myFavSeasonList.getSelectedItemPosition();
                send_intent.putExtra("season_id",id);
                startActivity(send_intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
