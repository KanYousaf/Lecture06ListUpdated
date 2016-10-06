package com.example.kanwalpc.lists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView display_details_season_tv;
    int position_to_show;
    public static final String[] Season_Details={"Silicon Valley is about Richard Henricks and his company named pied piper",
            "Game of Thrones is a fantasy drama",
            "Big Bang Theory is scientific sci-fi drama",
            "Prison Break is about the story of Micheal Scofield and his brother",
            "Citizen Khan: Mr. Khan , a pakistani citizen living abroad in UK",
            "Divinci Demons: Story about Leonardo Divinci",
            "Mr. Robot is about hacker's story and how he wants to take revenge on society",
            "House of Cards is about underwood and his compaign to become president of USA",
            "Sherlock Holmes: Detective to solve crimes"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        display_details_season_tv = (TextView) this.findViewById(R.id.details_season);

        Intent receive_intent=getIntent();
        int data=receive_intent.getExtras().getInt("season_id");
        display_details_season_tv.setText(Season_Details[data]);
    }
}
